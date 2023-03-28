package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.api.CommonResult;
import com.example.demo.domain.OmsOrderDetail;
import com.example.demo.entity.OmsOrder;
import com.example.demo.entity.PmsProduct;
import com.example.demo.entity.UmsMember;
import com.example.demo.exception.ServiceException;
import com.example.demo.feign.ProductService;
import com.example.demo.feign.UserService;
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
//    @Resource
//    private RestTemplate restTemplate;
    @Resource
    private UserService userService;
    @Resource
    private ProductService productService;

    @Override
    public OmsOrderDetail detail(Long id) {
        OmsOrder omsOrder = omsOrderMapper.getOmsOrderById(id);
        if (ObjectUtil.isNotNull(omsOrder)) {
            OmsOrderDetail omsOrderDetail = BeanUtil.copyProperties(omsOrder, OmsOrderDetail.class);

            // 使用RestTemplate发送http请求
//            CommonResult commonResult = restTemplate.getForObject("http://demo-user/member/" + omsOrder.getMemberId(), CommonResult.class);
//            UmsMember umsMember = BeanUtil.toBean(commonResult.getData(), UmsMember.class);

            // 使用openFeign进行服务的远程调用,获取用户信息
            CommonResult userResult = userService.detail(omsOrder.getMemberId());
            UmsMember umsMember = BeanUtil.toBean(userResult.getData(), UmsMember.class);
            omsOrderDetail.setUmsMember(umsMember);
            // 获取商品信息
            CommonResult productResult = productService.detail(omsOrder.getProductId());
            PmsProduct pmsProduct = BeanUtil.toBean(productResult.getData(), PmsProduct.class);
            omsOrderDetail.setPmsPorduct(pmsProduct);
            return omsOrderDetail;
        }
        return null;
    }

    @Override
    @GlobalTransactional
    public int insert(OmsOrder omsOrder) {
        // 商品信息校验
        CommonResult productResult = productService.detail(omsOrder.getProductId());
        if (ObjectUtil.isNull(productResult.getData())) {
            throw new ServiceException("商品信息存在异常");
        }
        // 商品库存扣减
        PmsProduct pmsProduct = BeanUtil.toBean(productResult.getData(), PmsProduct.class);
        pmsProduct.setStock(pmsProduct.getStock() - 1);
        productService.update(pmsProduct);
        // 用户信息校验
        CommonResult userResult = userService.detail(omsOrder.getMemberId());
        if (ObjectUtil.isNull(userResult.getData())) {
            throw new ServiceException("用户信息存在异常");
        }
        // 用户余额扣减
        UmsMember umsMember = BeanUtil.toBean(userResult.getData(), UmsMember.class);
        if (umsMember.getAccount().compareTo(pmsProduct.getPrice()) < 0) {
            throw new ServiceException("用户余额不足");
        }
        umsMember.setAccount(NumberUtil.sub(umsMember.getAccount(), pmsProduct.getPrice()));
        userService.update(umsMember);
        // 新增订单信息
        return omsOrderMapper.insert(omsOrder);
    }
}
