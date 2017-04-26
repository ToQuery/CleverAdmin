package com.toquery.cleverweb.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import com.alibaba.fastjson.JSON;
import com.toquery.cleverweb.common.util.*;
import com.toquery.cleverweb.core.entity.system.Menu;
import com.toquery.cleverweb.core.utils.Jurisdiction;
import com.toquery.cleverweb.entity.po.TbSysMenu;
import com.toquery.cleverweb.entity.vo.SysMenu;
import com.toquery.cleverweb.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.toquery.cleverweb.web.controller.BaseController;

/**
 * 菜单处理
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {


    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 显示菜单列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        try {
            String MENU_ID = (null == pd.get("MENU_ID") || "".equals(pd.get("MENU_ID").toString())) ? "0" : pd.get("MENU_ID").toString();
            List<TbSysMenu> menuList = sysMenuService.findListByParentId(MENU_ID);
            mv.addObject("pd", sysMenuService.findListByMenuId(MENU_ID));    //传入父菜单所有信息
            mv.addObject("MENU_ID", MENU_ID);
            mv.addObject("MSG", null == pd.get("MSG") ? "list" : pd.get("MSG").toString()); //MSG=change 则为编辑或删除后跳转过来的
            mv.addObject("menuList", menuList);
            mv.addObject("QX", Jurisdiction.getHC());    //按钮权限
            mv.setViewName("system/menu/menu_list");
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 请求新增菜单页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public ModelAndView toAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        try {
            PageData pd = new PageData();
            String MENU_ID = (null == pd.get("MENU_ID") || "".equals(pd.get("MENU_ID").toString())) ? "0" : pd.get("MENU_ID").toString();//接收传过来的上级菜单ID,如果上级为顶级就取值“0”
            pd.put("MENU_ID", MENU_ID);
            mv.addObject("pds", sysMenuService.findListByMenuId(MENU_ID));    //传入父菜单所有信息
            mv.addObject("MENU_ID", MENU_ID);                    //传入菜单ID，作为子菜单的父菜单ID用
            mv.addObject("MSG", "add");                            //执行状态 add 为添加
            mv.setViewName("system/menu/menu_edit");
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 保存菜单信息
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(Menu menu) throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        try {
//            menu.setMENU_ID(String.valueOf(Integer.parseInt(sysMenuService.findMaxId(pd).get("MID").toString()) + 1));
//            menu.setMENU_ICON("menu-icon fa fa-leaf black");//默认菜单图标
//            menuService.saveMenu(menu); //保存菜单
        } catch (Exception e) {
            logger.error(e.toString(), e);
            mv.addObject("msg", "failed");
        }
        mv.setViewName("redirect:?MSG='change'&MENU_ID=" + menu.getPARENT_ID()); //保存成功跳转到列表页面
        return mv;
    }

    /**
     * 删除菜单
     *
     * @param MENU_ID
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String MENU_ID) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String errInfo = "";
        try {
            //if (menuService.listSubMenuByParentId(MENU_ID).size() > 0) {//判断是否有子菜单，是：不允许删除
           //     errInfo = "false";
           // } else {
                sysMenuService.delete(MENU_ID);
                errInfo = "success";
          //  }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        map.put("result", errInfo);
        return AppUtil.returnObject(new PageData(), map);
    }

    /**
     * 请求编辑菜单页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(@RequestParam("MENU_ID") String menuId) throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        try {
            pd.put("MENU_ID", menuId);                //接收过来的要修改的ID
            List<TbSysMenu> list = sysMenuService.findListByMenuId(menuId);    //读取此ID的菜单数据
            mv.addObject("pd", pd);                //放入视图容器
            pd.put("MENU_ID", pd.get("PARENT_ID").toString());            //用作读取父菜单信息
            mv.addObject("pds", sysMenuService.findListByMenuId(menuId));            //传入父菜单所有信息
            mv.addObject("MENU_ID", pd.get("PARENT_ID").toString());    //传入父菜单ID，作为子菜单的父菜单ID用
            mv.addObject("MSG", "edit");
            pd.put("MENU_ID", menuId);            //复原本菜单ID
            mv.addObject("QX", Jurisdiction.getHC());    //按钮权限
            mv.setViewName("system/menu/menu_edit");
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 保存编辑
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit(TbSysMenu menu) throws Exception {
        ModelAndView mv = new ModelAndView();
        try {
            sysMenuService.saveAndFlush(menu);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        mv.setViewName("redirect:?MSG='change'&MENU_ID=" + menu.getParentId()); //保存成功跳转到列表页面
        return mv;
    }

    /**
     * 请求编辑菜单图标页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/toEditicon")
    public ModelAndView toEditicon(String MENU_ID) throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        try {
            pd.put("MENU_ID", MENU_ID);
            mv.addObject("pd", pd);
            mv.setViewName("system/menu/menu_icon");
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 保存菜单图标
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/editicon")
    public ModelAndView editicon() throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        try {
            sysMenuService.saveAndFlush(null);
            mv.addObject("msg", "success");
        } catch (Exception e) {
            logger.error(e.toString(), e);
            mv.addObject("msg", "failed");
        }
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 显示菜单列表ztree(菜单管理)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/listallmenu")
    public ModelAndView listAllMenu(Model model, @RequestParam(value = "menuId", defaultValue = "0") String menuId) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<TbSysMenu> sysMenuList = sysMenuService.findListByParentId(menuId);
        String zTreeJSON = JSON.toJSONString(sysMenuList).replaceAll("menuId", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("menuUrl", "url");
        model.addAttribute("zTreeNodes", zTreeJSON);
        mv.addObject("MENU_ID", menuId);
        mv.setViewName("system/menu/menu_ztree");
        return mv;
    }

    /**
     * 显示菜单列表ztree(拓展左侧四级菜单)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/otherlistMenu")
    public ModelAndView otherlistMenu(Model model, String MENU_ID) throws Exception {
        ModelAndView mv = new ModelAndView();
        try {
            PageData pd = new PageData();
            pd.put("MENU_ID", MENU_ID);
            String MENU_URL = sysMenuService.getById(MENU_ID).getMenuUrl();
            if ("#".equals(MENU_URL.trim()) || "".equals(MENU_URL.trim()) || null == MENU_URL) {
                MENU_URL = "login_default.do";
            }
            String roleRights = Jurisdiction.getSession().getAttribute(Jurisdiction.getUsername() + Const.SESSION_ROLE_RIGHTS).toString();    //获取本角色菜单权限
            List<Menu> athmenuList = new ArrayList<>(); //= menuService.listAllMenuQx(MENU_ID);                    //获取某菜单下所有子菜单
            athmenuList = this.readMenu(athmenuList, roleRights);                            //根据权限分配菜单
            String json = JSON.toJSONString(athmenuList);
            json = json.replaceAll("MENU_ID", "id").replaceAll("PARENT_ID", "pId").replaceAll("MENU_NAME", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("MENU_URL", "url").replaceAll("#", "");
            model.addAttribute("zTreeNodes", json);
            mv.addObject("MENU_URL", MENU_URL);        //本ID菜单链接
            mv.setViewName("system/menu/menu_ztree_other");
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
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
            if (menuList.get(i).isHasMenu() && "1".equals(menuList.get(i).getMENU_STATE())) {    //判断是否有此菜单权限并且是否隐藏
                this.readMenu(menuList.get(i).getSubMenu(), roleRights);                    //是：继续排查其子菜单
            } else {
                menuList.remove(i);
                i--;
            }
        }
        return menuList;
    }

}
