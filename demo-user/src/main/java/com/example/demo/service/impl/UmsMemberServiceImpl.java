package com.example.demo.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.UmsMember;
import com.example.demo.mapper.UmsMemberMapper;
import com.example.demo.service.UmsMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YuanJW on 2023/2/15.
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {
    @Resource
    private UmsMemberMapper umsMemberMapper;

    @Override
    public int insertUmsMember(UmsMember umsMember) {
        return umsMemberMapper.insert(umsMember);
    }

    @Override
    public List<UmsMember> getUmsMembers(UmsMember umsMember) {
        return umsMemberMapper.getUmsMembers(umsMember);
    }

    @Override
    public UmsMember getUmsMemberById(Long id) {
        return umsMemberMapper.getUmsMemberById(id);
    }

    @Override
    public int updateUmsMember(UmsMember umsMember) {
        return umsMemberMapper.updateUmsMember(umsMember);
    }

    @Override
    public int deduct(Long memberId, BigDecimal deduction) {
        UmsMember umsMember = umsMemberMapper.selectById(memberId);
        Assert.notNull(umsMember, "用户不存在！");
        Assert.isTrue(umsMember.vaildAccount(deduction), "用户余额不足！");
        return umsMemberMapper.update(
                null,
                new LambdaUpdateWrapper<UmsMember>()
                        .setSql("account = account - " + deduction)
                        .eq(UmsMember::getId, memberId)
        );
    }

    @Override
    public int refund(Long memberId, BigDecimal deduction) {
        UmsMember umsMember = umsMemberMapper.selectById(memberId);
        Assert.notNull(umsMember, "用户不存在！");
        Assert.isTrue(umsMember.vaildAccount(deduction), "用户余额不足！");
        return umsMemberMapper.update(
                null,
                new LambdaUpdateWrapper<UmsMember>()
                        .setSql("account = account + " + deduction)
                        .eq(UmsMember::getId, memberId)
        );
    }
}
