package com.example.demo.service.impl;

import com.example.demo.entity.AccountFreezeTbl;
import com.example.demo.entity.StockFreezeTbl;
import com.example.demo.mapper.StockFreezeTblMapper;
import com.example.demo.service.PmsProductService;
import com.example.demo.service.PmsProductStockTCCService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * PmsProductStockTCCServiceImpl
 *
 * @author YuanJW
 * @date 2023/05/19
 */
@Service
public class PmsProductStockTCCServiceImpl implements PmsProductStockTCCService {
    @Resource
    private PmsProductService pmsProductService;
    @Resource
    private StockFreezeTblMapper stockFreezeTblMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int prepare(Long productId, Integer deduction) {
        // 获取分布式事务Xid
        String xid = RootContext.getXID();

        // 防止业务悬挂同时保证冥等性
        StockFreezeTbl stockFreezeTbl = stockFreezeTblMapper.selectById(xid);
        if (stockFreezeTbl != null) {
            return 0;
        }

        // 扣减库存
        pmsProductService.deduct(productId, deduction);
        // 冻结库存
        stockFreezeTbl = StockFreezeTbl.builder()
                .xid(xid)
                .productId(productId)
                .freezeStock(deduction)
                .state(StockFreezeTbl.state.TRY)
                .build();
        return stockFreezeTblMapper.insert(stockFreezeTbl);
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        // 提交事务：删除冻结金额
        String xid = businessActionContext.getXid();
        return stockFreezeTblMapper.deleteById(xid) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rollback(BusinessActionContext businessActionContext) {
        // 获取分布式事务Xid
        String xid = businessActionContext.getXid();

        // 防空回滚
        StockFreezeTbl stockFreezeTbl = stockFreezeTblMapper.selectById(xid);
        if (stockFreezeTbl == null) {
            stockFreezeTbl = StockFreezeTbl.builder()
                    .xid(xid)
                    .productId(Long.valueOf(businessActionContext.getActionContext().get("productId").toString()))
                    .freezeStock(Integer.valueOf(businessActionContext.getActionContext().get("deduction").toString()))
                    .state(StockFreezeTbl.state.CANCEL)
                    .build();
            stockFreezeTblMapper.insert(stockFreezeTbl);
            return true;
        }
        // 冥等处理
        if (AccountFreezeTbl.State.CANCEL.equals(stockFreezeTbl.getState())) {
            return true;
        }

        // 回滚冻结金额
        pmsProductService.refund(stockFreezeTbl.getProductId(), stockFreezeTbl.getFreezeStock());
        // 修改冻结金额和事务状态
        stockFreezeTbl.setFreezeStock(Integer.valueOf(0))
                .setState(StockFreezeTbl.state.CANCEL);
        stockFreezeTblMapper.updateById(stockFreezeTbl);
        return false;
    }
}
