package com.example.demo.feign.impl;

import com.example.demo.api.CommonResult;
import com.example.demo.entity.UmsMember;
import com.example.demo.feign.UserService;
import org.springframework.stereotype.Component;

/**
 * Created by YuanJW on 2023/2/22.
 */
@Component
public class UserFallbackService implements UserService {
    @Override
    public CommonResult detail(Long id) {
        UmsMember defaultMember = new UmsMember();
        defaultMember.setId(0L)
                .setName("default");
        return CommonResult.success(defaultMember);
    }

    @Override
    public CommonResult update(UmsMember umsMember) {
        return CommonResult.failed("调用失败，服务被降级");
    }
}
