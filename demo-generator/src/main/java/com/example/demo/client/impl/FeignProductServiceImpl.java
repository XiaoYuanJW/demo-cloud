package com.example.demo.client.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.api.CommonResult;
import com.example.demo.api.ResultCode;
import com.example.demo.client.FeignProductService;
import com.example.demo.dto.ProductDeductDTO;
import com.example.demo.entity.PmsProduct;
import com.example.demo.feign.ProductClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * FeignProductServiceImpl
 *
 * @author YuanJW
 * @date 2023/05/16
 */
@Component
public class FeignProductServiceImpl implements FeignProductService {
    @Resource
    private ProductClient productClient;

    @Override
    public PmsProduct detail(Long productId) {
        CommonResult result = productClient.detail(productId);
        if (Objects.equals(ResultCode.FAILED.getCode(), result.getCode()) ||
                Objects.isNull(result.getData())) {
            return null;
        }
        return BeanUtil.toBean(result.getData(), PmsProduct.class);
    }

    @Override
    public int update(PmsProduct pmsProduct) {
        CommonResult result = productClient.update(pmsProduct);
        if (Objects.equals(ResultCode.FAILED.getCode(), result.getCode()) ||
                Objects.isNull(result.getData())) {
            return 0;
        }
        return (int) result.getData();
    }

    @Override
    public int deduct(Long productId, Integer count) {
        CommonResult result = productClient.deduct(new ProductDeductDTO(productId, count));
        if (Objects.equals(ResultCode.FAILED.getCode(), result.getCode()) ||
                Objects.isNull(result.getData())) {
            return 0;
        }
        return (int) result.getData();
    }
}
