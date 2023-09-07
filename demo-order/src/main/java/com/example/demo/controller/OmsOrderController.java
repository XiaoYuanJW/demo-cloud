package com.example.demo.controller;

import com.example.demo.api.CommonResult;
import com.example.demo.domain.OmsOrderDetail;
import com.example.demo.entity.OmsOrder;
import com.example.demo.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by YuanJW on 2023/2/15.
 */
@RestController
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {
    @Resource
    private OmsOrderService omsOrderService;

    @ApiOperation(value = "查询订单信息")
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult detail(@PathVariable Long id) {
        OmsOrderDetail omsOrderDetail = omsOrderService.detail(id);
        return CommonResult.success(omsOrderDetail);
    }

    @ApiOperation(value = "新增订单信息")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public CommonResult add(@RequestBody OmsOrder omsOrder) {
        int count = omsOrderService.insert(omsOrder);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "关联流控模式-查询")
    @GetMapping(value = "/query")
    public CommonResult query() {
        return CommonResult.success("查询成功");
    }

    @ApiOperation(value = "关联流控模式-更新")
    @PostMapping(value = "/update")
    public CommonResult update() {
        return CommonResult.success("更新成功");
    }
}
