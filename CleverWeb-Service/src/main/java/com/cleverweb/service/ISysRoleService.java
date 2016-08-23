package com.cleverweb.service;

import com.cleverweb.core.entity.system.Role;
import com.cleverweb.entity.po.TbSysRole;
import com.cleverweb.entity.po.TbSysUser;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-21.
 */
public interface ISysRoleService {

    /**
     * 通过角色id获取角色信息
     * @param roleId    角色id
     * @return  角色信息
     */
    TbSysRole findByRoleId(String roleId);

    /**
     * 获取所有权限信息
     * @return
     */
    List<TbSysRole> findList();


}
