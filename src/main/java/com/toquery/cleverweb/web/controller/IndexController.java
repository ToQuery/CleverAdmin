package com.toquery.cleverweb.web.controller;

import com.toquery.cleverweb.common.util.Const;
import com.toquery.cleverweb.common.util.PageData;
import com.toquery.cleverweb.common.util.RightsHelper;
import com.toquery.cleverweb.entity.po.TbSysButton;
import com.toquery.cleverweb.entity.po.TbSysRole;
import com.toquery.cleverweb.entity.vo.SysMenu;
import com.toquery.cleverweb.entity.vo.SysUserRole;
import com.toquery.cleverweb.service.ISysButtonService;
import com.toquery.cleverweb.service.ISysMenuService;
import com.toquery.cleverweb.service.ISysRoleButtonService;
import com.toquery.cleverweb.service.ISysUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysButtonService sysButtonService;
    @Autowired
    private ISysRoleButtonService sysRoleButtonService;


    /**
     * 访问系统首页
     *
     * @param changeMenu：切换菜单参数
     * @return
     */
    @RequestMapping(value = "/main/{changeMenu}")
    public ModelAndView login_index(HttpSession session, @PathVariable("changeMenu") String changeMenu) {
        ModelAndView mv = new ModelAndView();
        PageData pd = this.getPageData();
        SysUserRole sessionUserRole = (SysUserRole) session.getAttribute(Const.SESSION_USER);                //读取session中的用户信息(单独用户信息)
        if (sessionUserRole == null) {
            RedirectView rdv = new RedirectView("/login");
            return new ModelAndView(rdv);
        }
        String USERNAME = sessionUserRole.getUserName();
        TbSysRole sysRole = sessionUserRole.getRole();
        String roleRights = sysRole != null ? sysRole.getRights() : "";                //角色权限(菜单权限)
        session.setAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS, roleRights); //将角色权限存入session
        session.setAttribute(Const.SESSION_USERNAME, USERNAME);                //放入用户名到session
        List<SysMenu> sysMenuList = (List<SysMenu>) session.getAttribute(USERNAME + Const.SESSION_allmenuList);
        if (sysMenuList == null ) {
            sysMenuList = sysMenuService.findListByParentId(0);
            if (Strings.isNotBlank(roleRights)) {
                sysMenuList = this.readMenu(sysMenuList, roleRights);        //根据角色权限获取本权限的菜单列表
            }else{
                sysMenuList = new ArrayList<>();
            }
            session.setAttribute(USERNAME + Const.SESSION_allmenuList, sysMenuList);//菜单权限放入session中
        }


        //切换菜单处理=====start
        List<SysMenu> menuList = new ArrayList<SysMenu>();
        if (null == session.getAttribute(USERNAME + Const.SESSION_menuList) || ("yes".equals(changeMenu))) {
            List<SysMenu> menuList1 = new ArrayList<SysMenu>();
            List<SysMenu> menuList2 = new ArrayList<SysMenu>();
            //拆分菜单
            for (int i = 0; i < sysMenuList.size(); i++) {
                SysMenu sysMenu = sysMenuList.get(i);
                if ("1".equals(sysMenu.getMenuType())) {
                    menuList1.add(sysMenu);
                } else {
                    menuList2.add(sysMenu);
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
            menuList = (List<SysMenu>) session.getAttribute(USERNAME + Const.SESSION_menuList);
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
    public Map<String, String> getUQX(SysUserRole sessionUserRole) {
        Map<String, String> map = new HashMap<String, String>();
        TbSysRole sysRole = sessionUserRole.getRole();
        map.put("add", sysRole.getAddQx());    //增
        map.put("del", sysRole.getDelQx());    //删
        map.put("edit", sysRole.getEditQx());    //改
        map.put("cha", sysRole.getChaQx());    //查
        List<TbSysButton> buttonList = sysButtonService.findList();
        if (!"admin".equals(sessionUserRole.getUserName())) {
            buttonList = sysButtonService.findListByRoleId(sysRole.getRoleId());    //此角色拥有的按钮权限标识列表
        }
        for (int i = 0; i < buttonList.size(); i++) {
            map.put(buttonList.get(i).getQxName(), "1");        //按钮权限
        }
        return map;
    }

    /**
     * 根据角色权限获取本权限的菜单列表(递归处理)
     *
     * @param sysMenuList：传入的总菜单
     * @param roleRights：加密的权限字符串
     * @return
     */
    public List<SysMenu> readMenu(List<SysMenu> sysMenuList, String roleRights) {
        for (int i = 0; i < sysMenuList.size(); i++) {
            sysMenuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, sysMenuList.get(i).getMenuId()));
            if (sysMenuList.get(i).isHasMenu()) {        //判断是否有此菜单权限
                this.readMenu(sysMenuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
            }
        }
        return sysMenuList;
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
