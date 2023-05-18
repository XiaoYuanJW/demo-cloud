package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.client.FeignProductService;
import com.example.demo.client.FeignUserService;
import com.example.demo.domain.OmsOrderDetail;
import com.example.demo.entity.OmsOrder;
import com.example.demo.entity.PmsProduct;
import com.example.demo.entity.UmsMember;
import com.example.demo.mapper.OmsOrderMapper;
import com.example.demo.service.OmsOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by YuanJW on 2023/2/15.
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {
    @Resource
    private OmsOrderMapper omsOrderMapper;
    @Resource
    private FeignUserService feignUserService;
    @Resource
    private FeignProductService feignProductService;
//    @Resource
//    private RestTemplate restTemplate;

    @Override
    public OmsOrderDetail detail(Long id) {
        OmsOrder omsOrder = omsOrderMapper.getOmsOrderById(id);
        if (ObjectUtil.isNotNull(omsOrder)) {
            OmsOrderDetail omsOrderDetail = BeanUtil.copyProperties(omsOrder, OmsOrderDetail.class);

            //  使用RestTemplate发送http请求
//            CommonResult commonResult = restTemplate.getForObject("http://demo-user/member/" + omsOrder.getMemberId(), CommonResult.class);
//            UmsMember umsMember = BeanUtil.toBean(commonResult.getData(), UmsMember.class);

            // 获取用户信息
            UmsMember umsMember = feignUserService.detail(omsOrder.getMemberId());
            omsOrderDetail.setUmsMember(umsMember);
            // 获取商品信息
            PmsProduct pmsProduct = feignProductService.detail(omsOrder.getProductId());
            omsOrderDetail.setPmsPorduct(pmsProduct);
        }
        return null;
    }

    @Override
    @GlobalTransactional
    public int insert(OmsOrder omsOrder) {
        // 商品信息校验
        PmsProduct pmsProduct = feignProductService.detail(omsOrder.getProductId());
        Assert.notNull(pmsProduct, "商品信息存在异常");

        UmsMember umsMember = feignUserService.detail(omsOrder.getProductId());
        Assert.notNull(umsMember, "会员信息存在异常");
        // 用户余额扣减
        Assert.isTrue(
                feignUserService.deduct(omsOrder.getMemberId(), pmsProduct.getPrice()) > 0,
                "用户余额扣减失败"
        );

        // 商品库存扣减
        Assert.isTrue(
                feignProductService.deduct(omsOrder.getProductId(), 1) > 0,
                "商品库存扣减失败"
        );

        // 新增订单信息
        return omsOrderMapper.insert(omsOrder);
    }
}
