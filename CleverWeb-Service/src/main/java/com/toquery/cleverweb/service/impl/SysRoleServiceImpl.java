package com.cleverweb.service.impl;

import com.cleverweb.core.entity.system.Role;
import com.cleverweb.dao.TbSysRoleMapper;
import com.cleverweb.entity.po.TbSysRole;
import com.cleverweb.entity.po.TbSysUser;
import com.cleverweb.service.ISysRoleService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 获取所有权限信息
     * @return
     */
    public List<TbSysRole> findList(){
        return sysRoleMapper.findList();
    }

    /**
     * 通过父级ID获取所有权限信息
     *
     * @param parentId
     * @return
     */
    @Override
    public List<TbSysRole> findByParentId(String parentId) {
        return sysRoleMapper.findByParentId(parentId);
    }
}
