package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.StockFreezeTbl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * StockFreezeTblMapper
 * Created by YuanJW on 2023-05-18 14:25:36
 */
@Mapper
public interface StockFreezeTblMapper extends BaseMapper<StockFreezeTbl> {
    /**
     * 新增StockFreezeTbl对象
     *
     * @param stockFreezeTbl
     * @return id
     */
    Long saveStockFreezeTbl(StockFreezeTbl stockFreezeTbl);

    /**
     * 根据id查询StockFreezeTbl对象
     *
     * @param id
     * @return StockFreezeTbl
     */
    StockFreezeTbl getStockFreezeTblById(Long id);

    /**
     * 根据搜索条件获取StockFreezeTbl列表
     *
     * @param stockFreezeTbl
     * @return
     */
    List<StockFreezeTbl> getStockFreezeTbls(StockFreezeTbl stockFreezeTbl);

    /**
     * 修改StockFreezeTbl对象
     *
     * @param stockFreezeTbl
     * @return
     */
    int updateStockFreezeTbl(StockFreezeTbl stockFreezeTbl);

    /**
     * 批量删除StockFreezeTbl
     *
     * @param ids
     * @return
     */
    int deleteStockFreezeTbls(List<Long> ids);

    /**
     * 统计StockFreezeTbl
     *
     * @param stockFreezeTbl
     * @return
     */
    int countStockFreezeTbl(StockFreezeTbl stockFreezeTbl);
}