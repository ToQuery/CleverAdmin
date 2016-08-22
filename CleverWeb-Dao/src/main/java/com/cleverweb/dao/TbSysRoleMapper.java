package com.cleverweb.dao;

import com.cleverweb.core.repository.MyBatisRepository;
import com.cleverweb.entity.po.TbSysRole;

@MyBatisRepository
public interface TbSysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(TbSysRole record);

    int insertSelective(TbSysRole record);

    TbSysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(TbSysRole record);

    int updateByPrimaryKey(TbSysRole record);
}