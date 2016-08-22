package com.cleverweb.service.impl;

import com.cleverweb.dao.TbSysUserMapper;
import com.cleverweb.entity.po.TbSysUser;
import com.cleverweb.entity.vo.TbSysUserRole;
import com.cleverweb.service.ISysUserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param page
     * @return
     */
    @Override
    public Page<TbSysUser> findListByPage(Page<TbSysUser> page) {
        return null;
    }
}
