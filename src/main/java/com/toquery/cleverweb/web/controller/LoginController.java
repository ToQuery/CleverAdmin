package com.toquery.cleverweb.web.controller;

import com.toquery.cleverweb.core.entity.vo.CWResponse;
import com.toquery.cleverweb.core.exception.LoginException;
import com.toquery.cleverweb.service.ISysUserService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Validated
@Controller
public class LoginController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;


    @ResponseBody
    @ExceptionHandler(value = LoginException.class)
    public CWResponse catchLoginException(LoginException loginException) {
        return loginException.getResponse();
    }

   /* @ResponseBody
    @ExceptionHandler(value = UnknownAccountException.class)
    public CWResponse catchUnknownAccountException(UnknownAccountException unknownAccountException) {
        return new CWResponse("1102", unknownAccountException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {AccountException.class, IncorrectCredentialsException.class})
    public CWResponse catchAccountException(Exception exception) {
        return new CWResponse("1103", "密码错误");
    }*/


    /**
     * 访问登录页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLogin() throws Exception {
        return new ModelAndView("system/index/login", "systemName", systemName);
    }

    /**
     * 提交登录数据，验证用户
     *
     * @return
     * @throws Exception
     */

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CWResponse login(@NotBlank(message = "用户名不能为空") @RequestParam(value = "loginName", defaultValue = "") String loginName,
                            @NotBlank(message = "密码不能为空") @RequestParam(value = "password", defaultValue = "") String password,
                            @NotBlank(message = "验证码不能为空") @RequestParam(value = "checkCode", defaultValue = "") String checkCode) {
        return new CWResponse("0000", "登录成功");
    }

}
