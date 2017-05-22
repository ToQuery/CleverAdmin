package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.core.secruity.JwtTokenUtil;
import com.toquery.cleverweb.dao.jpa.ITbSysUserDao;
import com.toquery.cleverweb.entity.po.TbSysUser;
import com.toquery.cleverweb.entity.vo.LoginSuccess;
import com.toquery.cleverweb.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private ITbSysUserDao sysUserDao;

    /**
     * 通过用户名查找用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public TbSysUser findByUserName(String userName) {
        return sysUserDao.findByUserName(userName);
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
        sysUserDao.delete(userId);
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
        return sysUserDao.findByRoleId(roleId);
    }

    /**
     * 获取所有的用户
     *
     * @return 所有的用户
     */
    @Override
    public Page<TbSysUser> findList(Pageable pageable) {
        return sysUserDao.findAll(pageable);
    }

    @Override
    public TbSysUser registerUser(TbSysUser registerUser) {
        if (sysUserDao.findByUserName(registerUser.getUserName()) != null) {
            return null;
        }
        registerUser.setCreateTime(new Date());
        registerUser.setLastTime(new Date());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        registerUser.setPassword(encoder.encode(registerUser.getPassword()));
        registerUser.setStatus("1");
        return sysUserDao.save(registerUser);
    }


}
