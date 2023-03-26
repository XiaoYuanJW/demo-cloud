package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.OmsOrderDetail;
import com.example.demo.entity.OmsOrder;

/**
 * Created by YuanJW on 2023/2/15.
 */
public interface OmsOrderService extends IService<OmsOrder> {
    /**
     * 获取订单详情
     * @param id
     * @return
     */
     OmsOrderDetail detail(Long id);

    /**
     * 新增订单信息
     * @param omsOrder
     * @return
     */
     int insert(OmsOrder omsOrder);
}
