package com.example.demo.feign.impl;

import com.example.demo.api.CommonResult;
import com.example.demo.dto.MemberDeductDTO;
import com.example.demo.entity.UmsMember;
import com.example.demo.feign.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by YuanJW on 2023/2/22.
 */
@Slf4j
@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        log.error("服务调用失败：{}", cause.getMessage());
        return new UserClient() {
            @Override
            public CommonResult detail(Long id) {
                UmsMember umsMember = UmsMember.builder().id(0L).build();
                return CommonResult.success(umsMember);
            }

            @Override
            public CommonResult update(UmsMember umsMember) {
                return CommonResult.failed("调用失败，服务被降级");
            }

            @Override
            public CommonResult deduct(MemberDeductDTO memberDeductDTO) {
                return CommonResult.failed("调用失败，服务被降级");
            }
        };
    }
}
