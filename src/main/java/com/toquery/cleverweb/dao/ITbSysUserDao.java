package com.toquery.cleverweb.dao;

import com.toquery.cleverweb.entity.po.TbSysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by toquery on 2017/4/25.
 */
public interface ITbSysUserDao extends JpaRepository<TbSysUser, String> {

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
    List<TbSysUser> findByRoleId(String roleId);

}
