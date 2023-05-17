package com.example.demo.feign;

import com.example.demo.api.CommonResult;
import com.example.demo.entity.PmsProduct;
import com.example.demo.feign.impl.ProductClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by YuanJW on 2023/2/21.
 */
@FeignClient(value = "demo-product", path = "/product", fallback = ProductClientFallbackFactory.class, contextId = "product-service")
public interface ProductClient {
    @GetMapping(value = "/{id}")
    CommonResult detail(@PathVariable Long id);

    @GetMapping(value = "/update")
    CommonResult update(@RequestBody PmsProduct pmsProduct);
}
