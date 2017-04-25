package com.cleverweb.service.impl;

import com.cleverweb.dao.TbSysUserMapper;
import com.cleverweb.entity.po.TbSysUser;
import com.cleverweb.service.ISysUserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-20.
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private TbSysUserMapper sysUserMapper;

    /**
     * 通过用户名查找用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public TbSysUser findByUserName(String userName) {
        return sysUserMapper.findByUserName(userName);
    }

    /**
     * 修改用户的最后登录信息
     *
     * @param user 用户名
     */
    @Override
    public void updateLastLogin(TbSysUser user) {

    }

    /**
     * 通过用户ID删除用户数据
     *
     * @param userId
     */
    @Override
    public void deleteByUserId(String userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
    }


    /**
     * 分页查询数据
     *
     * @return
     */
    @Override
    public Page<TbSysUser> findListByPage() {
//        return sysUserMapper.findList();
        return null;
    }

    /**
     * 根据角色ID获取该角色下所有的用户
     *
     * @param roleId 角色ID
     * @return 所有的用户
     */
    @Override
    public List<TbSysUser> findAllByRoleId(String roleId) {
        return sysUserMapper.findAllByRoleId(roleId);
    }

    /**
     * 获取所有的用户
     *
     * @return 所有的用户
     */
    @Override
    public List<TbSysUser> findList() {
        return sysUserMapper.findList();
    }
}
