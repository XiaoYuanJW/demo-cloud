package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * MemberDeductDTO    用户余额扣减参数
 *
 * @author YuanJW
 * @date 2023/05/17
 */
@Data
@AllArgsConstructor
public class MemberDeductDTO {
    /**
     * 商品id
     */
    private Long userId;
    /**
     * 扣减量
     */
    private BigDecimal deduction;
}
