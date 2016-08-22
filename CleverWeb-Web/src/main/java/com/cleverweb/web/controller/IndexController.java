package com.cleverweb.web.controller;

import com.cleverweb.common.util.Const;
import com.cleverweb.common.util.PageData;
import com.cleverweb.common.util.RightsHelper;
import com.cleverweb.core.entity.system.Menu;
import com.cleverweb.core.utils.Jurisdiction;
import com.cleverweb.entity.po.TbSysRole;
import com.cleverweb.entity.vo.TbSysUserRole;
import com.cleverweb.service.ISysUserService;
import com.cleverweb.service.system.appuser.AppuserManager;
import com.cleverweb.service.system.buttonrights.ButtonrightsManager;
import com.cleverweb.service.system.fhbutton.FhbuttonManager;
import com.cleverweb.service.system.menu.MenuManager;
import com.cleverweb.service.system.role.RoleManager;
import com.cleverweb.web.controller.base.BaseController;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ToQuery on 2016-08-21.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @Resource(name = "menuService")
    private MenuManager menuService;
    @Resource(name = "roleService")
    private RoleManager roleService;
    @Resource(name = "buttonrightsService")
    private ButtonrightsManager buttonrightsService;
    @Resource(name = "fhbuttonService")
    private FhbuttonManager fhbuttonService;
    @Resource(name = "appuserService")
    private AppuserManager appuserService;

    /**
     * 访问系统首页
     *
     * @param changeMenu：切换菜单参数
     * @return
     */
    @RequestMapping(value = "/main/{changeMenu}")
    public ModelAndView login_index(@PathVariable("changeMenu") String changeMenu) {
        ModelAndView mv = new ModelAndView();
        PageData pd = this.getPageData();
        Session session = Jurisdiction.getSession();
        TbSysUserRole sessionUserRole = (TbSysUserRole) session.getAttribute(Const.SESSION_USER);                //读取session中的用户信息(单独用户信息)
        if (sessionUserRole == null) {
            RedirectView rdv = new RedirectView("/login");
            return new ModelAndView(rdv);
        }
        String USERNAME = sessionUserRole.getUserName();
        TbSysRole sysRole = sessionUserRole.getRole();
        String roleRights = sysRole != null ? sysRole.getRights() : "";                //角色权限(菜单权限)
        session.setAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS, roleRights); //将角色权限存入session
        session.setAttribute(Const.SESSION_USERNAME, USERNAME);                //放入用户名到session
        List<Menu> allmenuList = new ArrayList<Menu>();
        if (null == session.getAttribute(USERNAME + Const.SESSION_allmenuList)) {
            allmenuList = menuService.listAllMenuQx("0");                    //获取所有菜单
            if (Strings.isNotBlank(roleRights)) {
                allmenuList = this.readMenu(allmenuList, roleRights);        //根据角色权限获取本权限的菜单列表
            }
            session.setAttribute(USERNAME + Const.SESSION_allmenuList, allmenuList);//菜单权限放入session中
        } else {
            allmenuList = (List<Menu>) session.getAttribute(USERNAME + Const.SESSION_allmenuList);
        }
        //切换菜单处理=====start
        List<Menu> menuList = new ArrayList<Menu>();
        if (null == session.getAttribute(USERNAME + Const.SESSION_menuList) || ("yes".equals(changeMenu))) {
            List<Menu> menuList1 = new ArrayList<Menu>();
            List<Menu> menuList2 = new ArrayList<Menu>();
            //拆分菜单
            for (int i = 0; i < allmenuList.size(); i++) {
                Menu menu = allmenuList.get(i);
                if ("1".equals(menu.getMENU_TYPE())) {
                    menuList1.add(menu);
                } else {
                    menuList2.add(menu);
                }
            }
            session.removeAttribute(USERNAME + Const.SESSION_menuList);
            if ("2".equals(session.getAttribute("changeMenu"))) {
                session.setAttribute(USERNAME + Const.SESSION_menuList, menuList1);
                session.removeAttribute("changeMenu");
                session.setAttribute("changeMenu", "1");
                menuList = menuList1;
            } else {
                session.setAttribute(USERNAME + Const.SESSION_menuList, menuList2);
                session.removeAttribute("changeMenu");
                session.setAttribute("changeMenu", "2");
                menuList = menuList2;
            }
        } else {
            menuList = (List<Menu>) session.getAttribute(USERNAME + Const.SESSION_menuList);
        }
        //切换菜单处理=====end
        if (null == session.getAttribute(USERNAME + Const.SESSION_QX)) {
            session.setAttribute(USERNAME + Const.SESSION_QX, this.getUQX(sessionUserRole));    //按钮权限放到session中
        }
        mv.setViewName("system/index/main");
        mv.addObject("user", sessionUserRole);
        mv.addObject("menuList", menuList);


        pd.put("SYSNAME", systemName); //读取系统名称
        mv.addObject("pd", pd);
        return mv;
    }

    /**
     * 获取用户权限
     *
     * @return
     */
    public Map<String, String> getUQX(TbSysUserRole sessionUserRole) {
        Map<String, String> map = new HashMap<String, String>();
        TbSysRole sysRole = sessionUserRole.getRole();
        map.put("adds", sysRole.getAddQx());    //增
        map.put("dels", sysRole.getDelQx());    //删
        map.put("edits", sysRole.getEditQx());    //改
        map.put("chas", sysRole.getChaQx());    //查
        List<PageData> buttonQXnamelist = new ArrayList<PageData>();
        if ("admin".equals(sessionUserRole.getUserName())) {
            buttonQXnamelist = fhbuttonService.listAll(pd);                    //admin用户拥有所有按钮权限
        } else {
            buttonQXnamelist = buttonrightsService.listAllBrAndQxname(pd);    //此角色拥有的按钮权限标识列表
        }
        for (int i = 0; i < buttonQXnamelist.size(); i++) {
            map.put(buttonQXnamelist.get(i).getString("QX_NAME"), "1");        //按钮权限
        }
        return map;
    }

    /**
     * 根据角色权限获取本权限的菜单列表(递归处理)
     *
     * @param menuList：传入的总菜单
     * @param roleRights：加密的权限字符串
     * @return
     */
    public List<Menu> readMenu(List<Menu> menuList, String roleRights) {
        for (int i = 0; i < menuList.size(); i++) {
            menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMENU_ID()));
            if (menuList.get(i).isHasMenu()) {        //判断是否有此菜单权限
                this.readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
            }
        }
        return menuList;
    }


    /**
     * 进入首页后的默认页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/defaultIndex")
    public ModelAndView defaultPage() throws Exception {
        ModelAndView mv = new ModelAndView("system/index/default");
        return mv;
    }
}
