package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.dao.ITbSysRoleDao;
import com.toquery.cleverweb.entity.po.TbSysRole;
import com.toquery.cleverweb.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-21.
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private ITbSysRoleDao sysRoleDao;


    /**
     * 通过角色id获取角色信息
     *
     * @param roleId 角色id
     * @return 角色信息
     */
    @Override
    public TbSysRole findByRoleId(String roleId) {
        return sysRoleDao.findOne(roleId);
    }

    /**
     * 获取所有权限信息
     * @return
     */
    public List<TbSysRole> findList(){
        return sysRoleDao.findAll();
    }

    /**
     * 通过父级ID获取所有权限信息
     *
     * @param parentId
     * @return
     */
    @Override
    public List<TbSysRole> findByParentId(String parentId) {
        return sysRoleDao.findByParentId(parentId);
    }
}
