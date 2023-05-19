package com.example.demo.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * PmsProductStockTCCService
 *
 * @author YuanJW
 * @date 2023/05/18
 */
@LocalTCC
public interface PmsProductStockTCCService {
    /**
     * 第一阶段【尝试】方法
     *
     * @param productid
     * @param deduction
     * @return
     */
    @TwoPhaseBusinessAction(name = "prepare", commitMethod = "commit", rollbackMethod = "rollback")
    int prepare(@BusinessActionContextParameter(paramName = "productId") Long productid,
                @BusinessActionContextParameter(paramName = "deduction") Integer deduction);

    /**
     * 第二阶段【提交】方法
     *
     * @param businessActionContext
     * @return
     */
    boolean commit(BusinessActionContext businessActionContext);

    /**
     * 第二阶段【回滚】方法
     *
     * @param businessActionContext
     * @return
     */
    boolean rollback(BusinessActionContext businessActionContext);
}
