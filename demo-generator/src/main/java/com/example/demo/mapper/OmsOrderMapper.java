package com.example.demo.mapper;

import com.example.demo.entity.OmsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* OmsOrderMapper
* Created by YuanJW on 2023-02-15 10:38:43
*/
@Mapper
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {
    /**
    * 新增OmsOrder对象
    * @param omsOrder
    * @return id
    */
    Long saveOmsOrder(OmsOrder omsOrder);
    /**
    * 根据id查询OmsOrder对象
    * @param id
    * @return OmsOrder
    */
    OmsOrder getOmsOrderById(Long id);
    /**
    * 根据搜索条件获取OmsOrder列表
    * @param omsOrder
    * @return
    */
    List<OmsOrder> getOmsOrders(OmsOrder omsOrder);
    /**
    * 修改OmsOrder对象
    * @param omsOrder
    * @return
    */
    int updateOmsOrder(OmsOrder omsOrder);
    /**
    * 批量删除OmsOrder
    * @param ids
    * @return
    */
    int deleteOmsOrders(List<Long> ids);
    /**
    * 统计OmsOrder
    * @param omsOrder
    * @return
    */
    int countOmsOrder(OmsOrder omsOrder);
}