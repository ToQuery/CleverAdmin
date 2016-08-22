package com.cleverweb.web.shiro;


import com.cleverweb.common.util.Const;
import com.cleverweb.entity.po.TbSysRole;
import com.cleverweb.entity.po.TbSysUser;
import com.cleverweb.entity.vo.TbSysUserRole;
import com.cleverweb.service.ISysRoleService;
import com.cleverweb.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author fh
 *         2015-3-6
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    /*
     * 登录信息和用户验证信息验证(non-Javadoc)
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();                //得到用户名
        String password = new String((char[]) token.getCredentials());    //得到密码
        TbSysUser dbUser = sysUserService.findByUserName(username);
        if (dbUser == null) {
            throw new UnknownAccountException("用户不存在");
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.removeAttribute(Const.SESSION_SECURITY_CODE);    //清除登录验证码的session
        TbSysUserRole userRole = new TbSysUserRole().convert(dbUser);
        TbSysRole sysRole = sysRoleService.findByRoleId(dbUser.getRoleId());
        userRole.setRole(sysRole);
        session.setAttribute(Const.SESSION_USER, userRole);            //把用户信息放session中
        return new SimpleAuthenticationInfo(userRole.getUserName(), userRole.getPassword(), getName());
    }

    /*
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法(non-Javadoc)
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("========2");
        return null;
    }


}
