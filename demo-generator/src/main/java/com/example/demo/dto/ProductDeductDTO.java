package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ProductDeductDTO     商品库存扣减参数
 *
 * @author YuanJW
 * @date 2023/05/17
 */
@Data
@AllArgsConstructor
public class ProductDeductDTO {
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 扣减量
     */
    private Integer deduction;
}
