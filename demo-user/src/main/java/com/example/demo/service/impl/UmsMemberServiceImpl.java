package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.UmsMember;
import com.example.demo.mapper.UmsMemberMapper;
import com.example.demo.service.UmsMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by YuanJW on 2023/2/15.
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {
    @Resource
    private UmsMemberMapper umsMemberMapper;

    @Override
    public int insertUmsMember(UmsMember umsMember) {
        return umsMemberMapper.insert(umsMember);
    }

    @Override
    public List<UmsMember> getUmsMembers(UmsMember umsMember) {
        return umsMemberMapper.getUmsMembers(umsMember);
    }

    @Override
    public UmsMember getUmsMemberById(Long id) {
        return umsMemberMapper.getUmsMemberById(id);
    }

    @Override
    public int updateUmsMember(UmsMember umsMember) {
        return umsMemberMapper.updateUmsMember(umsMember);
    }
}
