package com.example.demo.feign.impl;

import com.example.demo.api.CommonResult;
import com.example.demo.dto.ProductDeductDTO;
import com.example.demo.entity.PmsProduct;
import com.example.demo.feign.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by YuanJW on 2023/2/24.
 */
@Slf4j
@Component
public class ProductClientFallbackFactory implements FallbackFactory<ProductClient> {
    @Override
    public ProductClient create(Throwable cause) {
        log.error("服务调用失败：{}", cause.getMessage());
        return new ProductClient() {
            @Override
            public CommonResult detail(Long id) {
                return CommonResult.failed("调用失败，服务被降级");
            }

            @Override
            public CommonResult update(PmsProduct pmsProduct) {
                return CommonResult.failed("服务降级");
            }

            @Override
            public CommonResult deduct(ProductDeductDTO productDeductDTO) {
                return CommonResult.failed("服务降级");
            }
        };
    }
}
