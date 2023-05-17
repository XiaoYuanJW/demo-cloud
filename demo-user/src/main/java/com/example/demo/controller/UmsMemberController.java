package com.example.demo.controller;

import com.example.demo.api.CommonResult;
import com.example.demo.entity.UmsMember;
import com.example.demo.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by YuanJW on 2023/2/15.
 */
@RestController
@Api(tags = "UmsMemberController", description = "会员管理")
@RequestMapping("/member")
public class UmsMemberController {
    @Resource
    private UmsMemberService umsMemberService;

    @ApiOperation(value = "新增会员信息")
    @PostMapping(value = "/insert", produces = "application/json;charset=UTF-8")
    public CommonResult insert(@RequestBody UmsMember umsMember) {
        int count = umsMemberService.insertUmsMember(umsMember);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "查询会员信息")
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult detail(@PathVariable Long id) {
        UmsMember umsMember = umsMemberService.getUmsMemberById(id);
        return CommonResult.success(umsMember);
    }

    @ApiOperation(value = "更新会员信息")
    @GetMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public CommonResult update(@RequestBody UmsMember umsMember) {
        int count = umsMemberService.updateUmsMember(umsMember);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "扣减会员余额")
    @PostMapping(value = "deduct", produces = "application/json;charset=UTF-8")
    public CommonResult deduct(Long memberId, BigDecimal deduction) {
        int count = umsMemberService.deduct(memberId, deduction);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
