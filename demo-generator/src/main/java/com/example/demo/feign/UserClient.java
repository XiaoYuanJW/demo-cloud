package com.example.demo.feign;

import com.example.demo.api.CommonResult;
import com.example.demo.dto.MemberDeductDTO;
import com.example.demo.entity.UmsMember;
import com.example.demo.feign.impl.UserClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by YuanJW on 2023/2/21.
 */
@FeignClient(value = "demo-user", path = "/member", fallbackFactory = UserClientFallbackFactory.class, contextId = "user-service")
public interface UserClient {
    @GetMapping(value = "/{id}")
    CommonResult detail(@PathVariable Long id);

    @GetMapping(value = "/update")
    CommonResult update(@RequestBody UmsMember umsMember);

    @PostMapping(value = "deduct")
    CommonResult deduct(@RequestBody MemberDeductDTO memberDeductDTO);
}
