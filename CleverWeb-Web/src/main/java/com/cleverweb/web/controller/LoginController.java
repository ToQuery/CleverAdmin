package com.cleverweb.web.controller;

import com.cleverweb.common.util.Const;
import com.cleverweb.core.entity.vo.CWResponse;
import com.cleverweb.core.exception.LoginException;
import com.cleverweb.core.utils.Jurisdiction;
import com.cleverweb.service.ISysUserService;
import com.cleverweb.web.controller.base.BaseController;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;





    @ResponseBody
    @ExceptionHandler(value = LoginException.class)
    public CWResponse catchLoginException(LoginException loginException) {
        return loginException.getResponse();
    }

    @ResponseBody
    @ExceptionHandler(value = UnknownAccountException.class)
    public CWResponse catchUnknownAccountException(UnknownAccountException unknownAccountException) {
        return new CWResponse("1102", unknownAccountException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {AccountException.class, IncorrectCredentialsException.class})
    public CWResponse catchAccountException(Exception exception) {
        return new CWResponse("1103", "密码错误");
    }


    /**
     * 访问登录页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLogin() throws Exception {
        ModelAndView mv = new ModelAndView("system/index/login", "systemName", systemName);
        return mv;
    }

    /**
     * 提交登录数据，验证用户
     *
     * @return
     * @throws Exception
     */
    @Validated
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CWResponse login(@RequestParam(value = "loginName", defaultValue = "") String loginName,
                            @RequestParam(value = "password", defaultValue = "") String password,
                            @RequestParam(value = "checkCode", defaultValue = "") String checkCode) {
        if (Strings.isBlank(loginName)) {
            return new CWResponse("1101", "用户名不能为空");
        }
        if (Strings.isBlank(password)) {
            return new CWResponse("1101", "密码不能为空");
        }
        if (Strings.isBlank(checkCode)) {
            return new CWResponse("1101", "验证码不能为空");
        }
        Session session = Jurisdiction.getSession();
        String sessionCheckCode = (String) session.getAttribute(Const.SESSION_SECURITY_CODE);        //获取session中的验证码
        if (!checkCode.equalsIgnoreCase(sessionCheckCode)) {
            return new CWResponse("1104", "验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        subject.login(token);
        return new CWResponse("0000", "登录成功");
    }

}
