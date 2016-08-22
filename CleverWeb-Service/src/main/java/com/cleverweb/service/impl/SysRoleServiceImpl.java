package com.cleverweb.service.impl;

import com.cleverweb.core.entity.system.Role;
import com.cleverweb.dao.TbSysRoleMapper;
import com.cleverweb.entity.po.TbSysRole;
import com.cleverweb.entity.po.TbSysUser;
import com.cleverweb.service.ISysRoleService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ToQuery on 2016-08-21.
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private TbSysRoleMapper sysRoleMapper;


    /**
     * 通过角色id获取角色信息
     *
     * @param roleId 角色id
     * @return 角色信息
     */
    @Override
    public TbSysRole findByRoleId(String roleId) {
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }

    /**
     * 分页查询权限数据
     *
     * @param page
     * @return
     */
    @Override
    public Page<Role> findListByPage(Page<TbSysUser> page) {
        return null;
    }
}
