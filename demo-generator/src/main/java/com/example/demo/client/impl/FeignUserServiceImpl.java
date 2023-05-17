package com.example.demo.client.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.api.CommonResult;
import com.example.demo.api.ResultCode;
import com.example.demo.client.FeignUserService;
import com.example.demo.entity.UmsMember;
import com.example.demo.feign.UserClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * FeignUserServiceImpl
 *
 * @author YuanJW
 * @date 2023/05/16
 */
@Component
public class FeignUserServiceImpl implements FeignUserService {
    @Resource
    private UserClient userClient;

    @Override
    public UmsMember detail(Long id) {
        CommonResult result = userClient.detail(id);
        if (Objects.equals(ResultCode.FAILED.getCode(), result.getCode()) ||
                Objects.isNull(result.getData())) {
            return null;
        }
        return BeanUtil.toBean(result.getData(), UmsMember.class);
    }

    @Override
    public int update(UmsMember umsMember) {
        CommonResult result = userClient.update(umsMember);
        if (Objects.equals(ResultCode.FAILED.getCode(), result.getCode()) ||
                Objects.isNull(result.getData())) {
            return 0;
        }
        return (int) result.getData();
    }

    @Override
    public int deduct(Long memberId, BigDecimal deduction) {
        CommonResult result = userClient.deduct(memberId, deduction);
        if (Objects.equals(ResultCode.FAILED.getCode(), result.getCode()) ||
                Objects.isNull(result.getData())) {
            return 0;
        }
        return (int) result.getData();
    }
}
