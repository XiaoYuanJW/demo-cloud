package com.example.demo.mapper;

import com.example.demo.entity.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* PmsProductMapper
* Created by YuanJW on 2023-02-15 10:15:15
*/
@Mapper
public interface PmsProductMapper extends BaseMapper<PmsProduct> {
    /**
    * 新增PmsProduct对象
    * @param pmsProduct
    * @return id
    */
    Long savePmsProduct(PmsProduct pmsProduct);
    /**
    * 根据id查询PmsProduct对象
    * @param id
    * @return PmsProduct
    */
    PmsProduct getPmsProductById(Long id);
    /**
    * 根据搜索条件获取PmsProduct列表
    * @param pmsProduct
    * @return
    */
    List<PmsProduct> getPmsProducts(PmsProduct pmsProduct);
    /**
    * 修改PmsProduct对象
    * @param pmsProduct
    * @return
    */
    int updatePmsProduct(PmsProduct pmsProduct);
    /**
    * 批量删除PmsProduct
    * @param ids
    * @return
    */
    int deletePmsProducts(List<Long> ids);
    /**
    * 统计PmsProduct
    * @param pmsProduct
    * @return
    */
    int countPmsProduct(PmsProduct pmsProduct);
}