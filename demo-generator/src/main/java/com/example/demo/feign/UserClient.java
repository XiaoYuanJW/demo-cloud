package com.example.demo.feign;

import com.example.demo.api.CommonResult;
import com.example.demo.entity.UmsMember;
import com.example.demo.feign.impl.UserClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

/**
 * Created by YuanJW on 2023/2/21.
 */
@FeignClient(value = "demo-user", fallback = UserClientFallbackFactory.class, contextId = "user-service")
public interface UserClient {
    @GetMapping(value = "/member/{id}")
    CommonResult detail(@PathVariable Long id);

    @GetMapping(value = "/member/update")
    CommonResult update(@RequestBody UmsMember umsMember);

    @PostMapping(value = "deduct")
    public CommonResult deduct(Long memberId, BigDecimal deduction);
}