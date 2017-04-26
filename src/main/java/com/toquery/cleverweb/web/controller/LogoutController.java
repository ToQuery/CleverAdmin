package com.toquery.cleverweb.web.controller;

import com.toquery.cleverweb.common.util.Const;
import com.toquery.cleverweb.common.util.PageData;
import com.toquery.cleverweb.core.utils.Jurisdiction;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ToQuery on 2016-08-21.
 */
@Controller
public class LogoutController extends BaseController{

    /**
     * 用户注销
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout() {
        String USERNAME = Jurisdiction.getUsername();    //当前登录的用户名
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
//        Session session = Jurisdiction.getSession();    //以下清除session缓存
//        session.removeAttribute(Const.SESSION_USER);
//        session.removeAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS);
//        session.removeAttribute(USERNAME + Const.SESSION_allmenuList);
//        session.removeAttribute(USERNAME + Const.SESSION_menuList);
//        session.removeAttribute(USERNAME + Const.SESSION_QX);
//        session.removeAttribute(Const.SESSION_userpds);
//        session.removeAttribute(Const.SESSION_USERNAME);
//        session.removeAttribute(Const.SESSION_USERROL);
//        session.removeAttribute("changeMenu");
//        //shiro销毁登录
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
        pd.put("msg", pd.getString("msg"));
        pd.put("SYSNAME", systemName); //读取系统名称
        mv.setViewName("system/index/login");
        mv.addObject("pd", pd);
        return mv;
    }

}
