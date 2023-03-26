package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.PmsProduct;

/**
 * Created by YuanJW on 2023/2/15.
 */
public interface PmsProductService extends IService<PmsProduct> {
    /**
     * 新增商品信息
     * @param pmsProduct
     * @return
     */
    int insertPmsProduct(PmsProduct pmsProduct);

    /**
     * 根据id获取商品详情
     * @param id
     * @return
     */
    PmsProduct getPmsProductById(Long id);

    /**
     * 修改商品信息
     * @param pmsProduct
     * @return
     */
    int updatePmsProduct(PmsProduct pmsProduct);
}
