package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.AccountFreezeTbl;
import com.example.demo.mapper.AccountFreezeTblMapper;
import com.example.demo.service.UmsMemberService;
import com.example.demo.service.UmsMemberTCCService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * UmsMemberTCCSeriveImpl
 *
 * @author YuanJW
 * @date 2023/04/14
 */
@Slf4j
@Service
public class UmsMemberTCCSeriveImpl implements UmsMemberTCCService {
    @Resource
    private UmsMemberService umsMemberService;
    @Resource
    private AccountFreezeTblMapper accountFreezeTblMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int prepare(Long memberId, BigDecimal deduction) {
        // 获取XID
        String xid = RootContext.getXID();
        // 防止业务悬挂同时防止冥等性
        AccountFreezeTbl accountFreezeTbl = accountFreezeTblMapper.selectById(xid);
        if (accountFreezeTbl != null) {
            return 0;
        }
        // 扣减会员金额
        umsMemberService.deduct(memberId, deduction);
        // 新增冻结金额
        accountFreezeTbl = AccountFreezeTbl.builder()
                .xid(xid)
                .memberId(memberId)
                .freezeAmount(deduction)
                .state(AccountFreezeTbl.State.TRY)
                .build();
        return accountFreezeTblMapper.insert(accountFreezeTbl);
    }

    @Override
    public boolean confirm(BusinessActionContext businessActionContext) {
        // 删除冻结金额
        String xid = businessActionContext.getXid();
        return accountFreezeTblMapper.delete(
                new LambdaQueryWrapper<AccountFreezeTbl>().eq(AccountFreezeTbl::getXid, xid)
        ) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(BusinessActionContext businessActionContext) {
        String xid = businessActionContext.getXid();
        AccountFreezeTbl accountFreezeTbl = accountFreezeTblMapper.selectById(xid);
        // 防止空回滚
        if (accountFreezeTbl == null) {
            accountFreezeTbl = AccountFreezeTbl.builder()
                    .xid(xid)
                    .memberId(Long.valueOf(businessActionContext.getActionContext().get("memberId").toString()))
                    .freezeAmount(new BigDecimal(businessActionContext.getActionContext().get("deduction").toString()))
                    .state(AccountFreezeTbl.State.CANCEL)
                    .build();
            accountFreezeTblMapper.insert(accountFreezeTbl);
            return true;
        }
        // 冥等处理
        if (AccountFreezeTbl.State.CANCEL.equals(accountFreezeTbl.getState())) {
            return true;
        }
        // 回滚冻结金额
        umsMemberService.refund(accountFreezeTbl.getMemberId(), accountFreezeTbl.getFreezeAmount());
        // 修改冻结金额和事务状态
        accountFreezeTbl.setFreezeAmount(BigDecimal.ZERO)
                .setState(AccountFreezeTbl.State.CANCEL);
        return accountFreezeTblMapper.updateById(accountFreezeTbl) > 0;
    }
}
