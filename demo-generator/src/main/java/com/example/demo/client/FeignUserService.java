package com.example.demo.client;

import com.example.demo.entity.UmsMember;

import java.math.BigDecimal;

/**
 * FeignUserService
 *
 * @author YuanJW
 * @date 2023/05/16
 */
public interface FeignUserService {
    /**
     * 获取用户详细信息
     *
     * @param id
     * @return
     */
    UmsMember detail(Long id);

    /**
     * 修改用户信息
     *
     * @param umsMember
     * @return
     */
    int update(UmsMember umsMember);

    /**
     * 扣减用户余额
     *
     * @param memberId
     * @param deduction
     * @return
     */
    int deduct(Long memberId, BigDecimal deduction);
}

