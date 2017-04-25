package com.toquery.cleverweb.service;

import com.toquery.cleverweb.entity.po.TbSysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by ToQuery on 2016-08-20.
 */
public interface ISysUserService {
    /**
     * 通过用户名查找用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    TbSysUser findByUserName(String userName);

    /**
     * 修改用户的最后登录信息
     *
     * @param user 用户名
     */
    void updateLastLogin(TbSysUser user);

    /**
     * 通过用户ID删除用户数据
     *
     * @param userId
     */
    void deleteByUserId(String userId);

    /**
     * 分页查询数据
     *
     * @return
     */
    Page<TbSysUser> findListByPage();

    /**
     * 根据角色ID获取该角色下所有的用户
     *
     * @param roleId 角色ID
     * @return 所有的用户
     */
    List<TbSysUser> findAllByRoleId(String roleId);

    /**
     * 获取所有的用户
     *
     * @return  所有的用户
     */
    Page<TbSysUser> findList(Pageable pageable);
}
