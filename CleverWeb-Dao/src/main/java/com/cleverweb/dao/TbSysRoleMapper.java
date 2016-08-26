package com.cleverweb.dao;

import com.cleverweb.core.entity.system.Role;
import com.cleverweb.core.repository.MyBatisRepository;
import com.cleverweb.entity.po.TbSysRole;

import java.util.List;

@MyBatisRepository
public interface TbSysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(TbSysRole record);

    int insertSelective(TbSysRole record);

    TbSysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(TbSysRole record);

    int updateByPrimaryKey(TbSysRole record);
    /**
     * 获取所有权限信息
     * @return
     */
    List<TbSysRole> findList();

    /**
     * 通过父级ID获取所有权限信息
     *
     * @param parentId
     * @return
     */
    List<TbSysRole> findByParentId(String parentId);
}