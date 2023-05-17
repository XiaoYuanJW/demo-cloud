package com.example.demo.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * UmsMemberTCCService
 *
 * @author YuanJW
 * @date 2023/04/14
 */
@LocalTCC
public interface UmsMemberTCCService {
    /**
     * 一阶段尝试方法，保证与@TwoPhaseBussinessAction中的Try一致
     *
     * @param @BusinessActionContextParameter注解中的paramName参数设置参数名，参数可以在二阶段方法中的BusinessActionContext中获取
     */
    @TwoPhaseBusinessAction(name = "prepare", commitMethod = "confirm", rollbackMethod = "cancel")
    void prepare(@BusinessActionContextParameter(paramName = "memberId") Long memberId,
                 @BusinessActionContextParameter(paramName = "deduction") BigDecimal deduction);

    /**
     * @param businessActionContext 上下文（可以传递try方法的参数）
     * @return 是否执行成功
     */
    boolean confirm(BusinessActionContext businessActionContext);

    /**
     * 二阶段回滚方法 保证与@TwoPhaseBussinessAction中的rollbackMethod一致
     *
     * @param businessActionContext 上下文
     * @return 是否执行成功
     */
    boolean cancel(BusinessActionContext businessActionContext);
}
