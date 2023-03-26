package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.UmsMember;

import java.util.List;

/**
 * Created by YuanJW on 2023/2/15.
 */
public interface UmsMemberService extends IService<UmsMember> {
    /**
     * 新增用户信息
     * @param umsMember
     * @return
     */
    int insertUmsMember(UmsMember umsMember);

    /**
     * 获取会员列表
     * @param umsMember
     * @return
     */
    List<UmsMember> getUmsMembers(UmsMember umsMember);

    /**
     * 根据id获取会员信息
     * @param id
     * @return
     */
    UmsMember getUmsMemberById(Long id);

    /**
     * 更新用户信息
     * @param umsMember
     * @return
     */
    int updateUmsMember(UmsMember umsMember);
}
