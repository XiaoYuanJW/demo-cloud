package com.example.demo.controller;

import com.example.demo.api.CommonResult;
import com.example.demo.dto.ProductDeductDTO;
import com.example.demo.entity.PmsProduct;
import com.example.demo.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by YuanJW on 2023/2/15.
 */
@RestController
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {
    @Resource
    private PmsProductService pmsProductService;

    @ApiOperation(value = "新增商品信息")
    @PostMapping(value = "/insert", produces = "application/json;charset=UTF-8")
    public CommonResult insert(@RequestBody PmsProduct pmsProduct) {
        int count = pmsProductService.insertPmsProduct(pmsProduct);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "查询商品信息")
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public CommonResult detail(@PathVariable Long id) {
        PmsProduct pmsProduct = pmsProductService.getPmsProductById(id);
        return CommonResult.success(pmsProduct);
    }

    @ApiOperation(value = "修改商品信息")
    @GetMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public CommonResult update(@RequestBody PmsProduct pmsProduct) {
        int count = pmsProductService.updatePmsProduct(pmsProduct);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "扣减库存")
    @PostMapping(value = "/deduct", produces = "application/json;charset=UTF-8")
    public CommonResult deduct(@RequestBody ProductDeductDTO productDeductDTO) {
        int count = pmsProductService.deduct(productDeductDTO.getProductId(), productDeductDTO.getDeduction());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
