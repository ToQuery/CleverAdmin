package com.toquery.cleverweb.web.controller.system.role;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import com.alibaba.fastjson.JSON;
import com.toquery.cleverweb.entity.po.TbSysRole;
import com.toquery.cleverweb.entity.po.TbSysUser;
import com.toquery.cleverweb.entity.vo.SysMenu;
import com.toquery.cleverweb.service.ISysMenuService;
import com.toquery.cleverweb.service.ISysRoleService;
import com.toquery.cleverweb.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.toquery.cleverweb.web.controller.BaseController;
import com.toquery.cleverweb.core.entity.system.Role;
import com.toquery.cleverweb.service.system.role.RoleManager;
import com.toquery.cleverweb.common.util.AppUtil;
import com.toquery.cleverweb.core.utils.Jurisdiction;
import com.toquery.cleverweb.common.util.PageData;
import com.toquery.cleverweb.common.util.RightsHelper;
import com.toquery.cleverweb.common.util.Tools;

/**
 * 类名称：RoleController 角色权限管理
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysMenuService sysMenuService;


    @Resource(name = "roleService")
    private RoleManager roleService;

    /**
     * 进入权限首页
     *
     * @param   roleId
     */
    @RequestMapping
    public ModelAndView list(@RequestParam(value = "roleId", defaultValue = "1") String roleId) {
        ModelAndView mv = new ModelAndView();
        // 获取一级菜单
        List<TbSysRole> roleList = sysRoleService.findByParentId("0");        //列出组(页面横向排列的一级组)
        List<TbSysRole> roleList_z = sysRoleService.findByParentId(roleId);        //列出此组下架角色
        TbSysRole sysRole = sysRoleService.findByRoleId(roleId);                            //取得点击的角色组(横排的)
        mv.addObject("roleId", roleId);
        mv.addObject("sysRole", sysRole);
        mv.addObject("roleList", roleList);
        mv.addObject("roleList_z", roleList_z);
        mv.addObject("QX", Jurisdiction.getHC());    //按钮权限
        mv.setViewName("system/role/role_list");
        return mv;
    }

    /**
     * 去新增页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            mv.addObject("msg", "add");
            mv.setViewName("system/role/role_edit");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 保存新增角色
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add() throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "add")) {
            return null;
        } //校验权限
        logBefore(logger, Jurisdiction.getUsername() + "新增角色");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            String parent_id = pd.getString("PARENT_ID");        //父类角色id
            pd.put("ROLE_ID", parent_id);
            if ("0".equals(parent_id)) {
                pd.put("RIGHTS", "");                            //菜单权限
            } else {
                String rights = roleService.findObjectById(pd).getString("RIGHTS");
                pd.put("RIGHTS", (null == rights) ? "" : rights);    //组菜单权限
            }
            pd.put("ROLE_ID", this.get32UUID());                //主键
            pd.put("ADD_QX", "0");    //初始新增权限为否
            pd.put("DEL_QX", "0");    //删除权限
            pd.put("EDIT_QX", "0");    //修改权限
            pd.put("CHA_QX", "0");    //查看权限
            roleService.add(pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            mv.addObject("msg", "failed");
        }
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 请求编辑
     * @param roleId
     */
    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(@RequestParam(value = "roleId", defaultValue = "") String roleId) {
        ModelAndView mv = new ModelAndView("system/role/role_edit");
        TbSysRole sysRole = sysRoleService.findByRoleId(roleId);
        mv.addObject("sysRole", sysRole);
        mv.addObject("msg", "edit");
        return mv;
    }

    /**
     * 保存修改
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit() throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "edit")) {
            return null;
        } //校验权限
        logBefore(logger, Jurisdiction.getUsername() + "修改角色");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            roleService.edit(pd);
            mv.addObject("msg", "success");
        } catch (Exception e) {
            logger.error(e.toString(), e);
            mv.addObject("msg", "failed");
        }
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 删除角色
     *
     * @param ROLE_ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteRole(@RequestParam String ROLE_ID) throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "del")) {
            return null;
        } //校验权限
        logBefore(logger, Jurisdiction.getUsername() + "删除角色");
        Map<String, String> map = new HashMap<String, String>();
        PageData pd = new PageData();
        String errInfo = "";
        try {
            pd.put("ROLE_ID", ROLE_ID);
            List<Role> roleList_z = roleService.listAllRolesByPId(pd);        //列出此部门的所有下级
            if (roleList_z.size() > 0) {
                errInfo = "false";                                            //下级有数据时，删除失败
            } else {
                List<TbSysUser> userlist = sysUserService.findAllByRoleId(ROLE_ID);            //此角色下的用户
                if (userlist.size() > 0) {                        //此角色已被使用就不能删除
                    errInfo = "false2";
                } else {
                    roleService.deleteRoleById(ROLE_ID);    //执行删除
                    errInfo = "success";
                }
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        map.put("result", errInfo);
        return AppUtil.returnObject(new PageData(), map);
    }

    /**
     * 显示菜单列表ztree(菜单授权菜单)
     *
     * @return
     */
    @RequestMapping(value = "/menuqx")
    public ModelAndView listAllMenu(@RequestParam(value = "roleId", defaultValue = "0") String roleId) {
        ModelAndView mv = new ModelAndView();
        TbSysRole sysRole = sysRoleService.findByRoleId(roleId);            //根据角色ID获取角色对象
        String roleRights = sysRole.getRights();                    //取出本角色菜单权限
        List<SysMenu> menuList = sysMenuService.findListByParentId(0);    //获取所有菜单
        menuList = this.readMenu(menuList, roleRights);            //根据角色权限处理菜单权限状态(递归处理)
        String json = JSON.toJSONString(menuList).replaceAll("menuId", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
        mv.addObject("zTreeNodes", json);
        mv.addObject("roleId", roleId);
        mv.setViewName("system/role/menuqx");
        return mv;
    }

    /**
     * 保存角色菜单权限
     *
     * @param ROLE_ID 角色ID
     * @param menuIds 菜单ID集合
     * @param out
     * @throws Exception
     */
    @RequestMapping(value = "/saveMenuqx")
    public void saveMenuqx(@RequestParam String ROLE_ID, @RequestParam String menuIds, PrintWriter out) throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "edit")) {
        } //校验权限
        PageData pd = new PageData();
        try {
            if (null != menuIds && !"".equals(menuIds.trim())) {
                BigInteger rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds));//用菜单ID做权处理
                Role role = roleService.getRoleById(ROLE_ID);    //通过id获取角色对象
                role.setRIGHTS(rights.toString());
                roleService.updateRoleRights(role);                //更新当前角色菜单权限
                pd.put("rights", rights.toString());
            } else {
                Role role = new Role();
                role.setRIGHTS("");
                role.setROLE_ID(ROLE_ID);
                roleService.updateRoleRights(role);                //更新当前角色菜单权限(没有任何勾选)
                pd.put("rights", "");
            }
            pd.put("ROLE_ID", ROLE_ID);
            roleService.setAllRights(pd);                    //更新此角色所有子角色的菜单权限
            out.write("success");
            out.close();
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
    }

    /**
     * 请求角色按钮授权页面(增删改查)
     *
     * @param roleId： 角色ID
     * @param msg：    区分增删改查
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/b4Button")
    public ModelAndView b4Button(@RequestParam(value = "roleId", defaultValue = "0") String roleId, @RequestParam String msg, Model model) {
        ModelAndView mv = this.getModelAndView();
        List<SysMenu> sysMenuList = sysMenuService.findListByMenuId(0); //获取所有菜单
        TbSysRole sysRole = sysRoleService.findByRoleId(roleId);          //根据角色ID获取角色对象
        String roleRights = "";
        if ("add_qx".equals(msg)) {
            roleRights = sysRole.getAddQx();    //新增权限
        } else if ("del_qx".equals(msg)) {
            roleRights = sysRole.getDelQx();    //删除权限
        } else if ("edit_qx".equals(msg)) {
            roleRights = sysRole.getEditQx();    //修改权限
        } else if ("cha_qx".equals(msg)) {
            roleRights = sysRole.getChaQx();    //查看权限
        }
        sysMenuList = this.readMenu(sysMenuList, roleRights);        //根据角色权限处理菜单权限状态(递归处理)
        String json = JSON.toJSONString(sysMenuList).replaceAll("menuId", "id").replaceAll("parentId", "pId").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
        model.addAttribute("zTreeNodes", json);
        mv.addObject("roleId", roleId);
        mv.addObject("msg", msg);
        mv.setViewName("system/role/b4Button");
        return mv;
    }

    /**
     * 根据角色权限处理权限状态(递归处理)
     *
     * @param sysMenuList：传入的总菜单
     * @param roleRights：加密的权限字符串
     * @return
     */
    public List<SysMenu> readMenu(List<SysMenu> sysMenuList, String roleRights) {
        for (SysMenu sysMenu : sysMenuList) {
            sysMenu.setHasMenu(RightsHelper.testRights(roleRights, sysMenu.getMenuId()));
            this.readMenu(sysMenu.getSubMenu(), roleRights);
        }
        return sysMenuList;
    }

    /**
     * 保存角色按钮权限
     */
    /**
     * @param ROLE_ID
     * @param menuIds
     * @param msg
     * @param out
     * @throws Exception
     */
    @RequestMapping(value = "/saveB4Button")
    public void saveB4Button(@RequestParam(value = "roleId", defaultValue = "") String roleId,
                             @RequestParam String menuIds, @RequestParam String msg, PrintWriter out) throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "edit")) {
        } //校验权限
        PageData pd = new PageData();
        pd = this.getPageData();
        if (null != menuIds && !"".equals(menuIds.trim())) {
            BigInteger rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds));
            pd.put("value", rights.toString());
        } else {
            pd.put("value", "");
        }
        pd.put("roleId", roleId);
        roleService.saveB4Button(msg, pd);
        out.write("success");
        out.close();
    }

}