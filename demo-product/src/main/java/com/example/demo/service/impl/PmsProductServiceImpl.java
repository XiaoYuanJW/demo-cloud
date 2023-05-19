package com.example.demo.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.PmsProduct;
import com.example.demo.mapper.PmsProductMapper;
import com.example.demo.service.PmsProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by YuanJW on 2023/2/15.
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
    @Resource
    private PmsProductMapper pmsProductMapper;

    @Override
    public int insertPmsProduct(PmsProduct pmsProduct) {
        return pmsProductMapper.insert(pmsProduct);
    }

    @Override
    public PmsProduct getPmsProductById(Long id) {
        return pmsProductMapper.getPmsProductById(id);
    }

    @Override
    public int updatePmsProduct(PmsProduct pmsProduct) {
        return pmsProductMapper.updatePmsProduct(pmsProduct);
    }

    @Override
    public int deduct(Long productId, Integer deduction) {
        PmsProduct pmsProduct = pmsProductMapper.selectById(productId);
        Assert.notNull(productId, "商品不存在！");
        Assert.isTrue(pmsProduct.vaildStock(deduction), "商品库存不足！");
        return pmsProductMapper.update(
                null,
                new LambdaUpdateWrapper<PmsProduct>()
                        .setSql("stock = stock - " + deduction)
                        .eq(PmsProduct::getId, productId)
        );
    }

    @Override
    public int refund(Long productId, Integer deduction) {
        PmsProduct pmsProduct = pmsProductMapper.selectById(productId);
        Assert.notNull(productId, "商品不存在！");
        Assert.isTrue(pmsProduct.vaildStock(deduction), "商品库存不足！");
        return pmsProductMapper.update(
                null,
                new LambdaUpdateWrapper<PmsProduct>()
                        .setSql("stock = stock + " + deduction)
                        .eq(PmsProduct::getId, productId)
        );
    }
}
