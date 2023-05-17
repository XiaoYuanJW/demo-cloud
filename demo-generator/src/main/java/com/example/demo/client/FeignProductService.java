package com.example.demo.client;

import com.example.demo.entity.PmsProduct;

/**
 * FeignProductService
 *
 * @author YuanJW
 * @date 2023/05/16
 */
public interface FeignProductService {

    /**
     * 获取商品详情
     *
     * @param productId
     * @return
     */
    PmsProduct detail(Long productId);

    /**
     * 修改商品信息
     *
     * @param pmsProduct
     * @return
     */
    int update(PmsProduct pmsProduct);

    /**
     * 扣减商品库存
     *
     * @param productId
     * @param count
     * @return
     */
    int deduction(Long productId, Integer count);
}
