package com.example.demo.feign.impl;

import com.example.demo.api.CommonResult;
import com.example.demo.entity.PmsProduct;
import com.example.demo.feign.ProductService;
import org.springframework.stereotype.Component;

/**
 * Created by YuanJW on 2023/2/24.
 */
@Component
public class ProductFallbackService implements ProductService {
    @Override
    public CommonResult update(PmsProduct pmsProduct) {
        return CommonResult.failed("调用失败，服务被降级");
    }

    @Override
    public CommonResult detail(Long id) {
        return CommonResult.failed("服务降级");
    }
}
