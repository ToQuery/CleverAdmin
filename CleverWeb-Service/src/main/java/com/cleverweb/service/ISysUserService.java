package com.cleverweb.service;

import com.cleverweb.entity.po.TbSysUser;
import com.cleverweb.entity.vo.TbSysUserRole;
import com.github.pagehelper.Page;

/**
 * Created by ToQuery on 2016-08-20.
 */
public interface ISysUserService {
    /**
     * 通过用户名查找用户信息
     * @param userName 用户名
     * @return  用户信息
     */
    TbSysUser findByUserName(String userName);

    /**
     * 修改用户的最后登录信息
     * @param user 用户名
     */
    void updateLastLogin(TbSysUser user);

    /**
     * 通过用户ID删除用户数据
     * @param userId
     */
    void deleteByUserId(String userId);

    /**
     * 分页查询数据
     * @param page
     * @return
     */
    Page<TbSysUser> findListByPage(Page<TbSysUser> page);
}
