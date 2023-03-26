package com.example.demo.feign;

import com.example.demo.api.CommonResult;
import com.example.demo.entity.PmsProduct;
import com.example.demo.feign.impl.ProductFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by YuanJW on 2023/2/21.
 */
@FeignClient(value = "demo-product", fallback = ProductFallbackService.class)
public interface ProductService {
    @GetMapping(value = "/product/{id}")
    CommonResult update(@RequestBody PmsProduct pmsProduct);

    @GetMapping(value = "/{id}")
    public CommonResult detail(@PathVariable Long id);
}
