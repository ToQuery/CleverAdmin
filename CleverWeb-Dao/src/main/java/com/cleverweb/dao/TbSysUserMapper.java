package com.cleverweb.dao;

import com.cleverweb.core.repository.MyBatisRepository;
import com.cleverweb.entity.po.TbSysUser;

import java.util.List;

@MyBatisRepository
public interface TbSysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(TbSysUser record);

    int insertSelective(TbSysUser record);

    TbSysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(TbSysUser record);

    int updateByPrimaryKey(TbSysUser record);
    /**
     * 通过用户名查找用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    TbSysUser findByUserName(String userName);

    /**
     * 根据角色ID获取该角色下所有的用户
     *
     * @param roleId 角色ID
     * @return 所有的用户
     */
    List<TbSysUser> findAllByRoleId(String roleId);
}