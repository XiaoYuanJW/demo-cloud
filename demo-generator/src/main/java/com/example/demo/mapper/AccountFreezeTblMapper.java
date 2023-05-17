package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.AccountFreezeTbl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AccountFreezeTblMapper
 * Created by YuanJW on 2023-05-16 16:18:27
 */
@Mapper
public interface AccountFreezeTblMapper extends BaseMapper<AccountFreezeTbl> {
    /**
     * 新增AccountFreezeTbl对象
     *
     * @param accountFreezeTbl
     * @return id
     */
    Long saveAccountFreezeTbl(AccountFreezeTbl accountFreezeTbl);

    /**
     * 根据id查询AccountFreezeTbl对象
     *
     * @param id
     * @return AccountFreezeTbl
     */
    AccountFreezeTbl getAccountFreezeTblById(Long id);

    /**
     * 根据搜索条件获取AccountFreezeTbl列表
     *
     * @param accountFreezeTbl
     * @return
     */
    List<AccountFreezeTbl> getAccountFreezeTbls(AccountFreezeTbl accountFreezeTbl);

    /**
     * 修改AccountFreezeTbl对象
     *
     * @param accountFreezeTbl
     * @return
     */
    int updateAccountFreezeTbl(AccountFreezeTbl accountFreezeTbl);

    /**
     * 批量删除AccountFreezeTbl
     *
     * @param ids
     * @return
     */
    int deleteAccountFreezeTbls(List<Long> ids);

    /**
     * 统计AccountFreezeTbl
     *
     * @param accountFreezeTbl
     * @return
     */
    int countAccountFreezeTbl(AccountFreezeTbl accountFreezeTbl);
}