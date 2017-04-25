package com.cleverweb.web.controller.system.buttonrights;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.cleverweb.core.entity.vo.CWResponse;
import com.cleverweb.entity.po.TbSysButton;
import com.cleverweb.entity.po.TbSysRole;
import com.cleverweb.entity.po.TbSysRoleButton;
import com.cleverweb.service.ISysButtonService;
import com.cleverweb.service.ISysRoleButtonService;
import com.cleverweb.service.ISysRoleService;
import com.cleverweb.service.impl.SysRoleServiceImpl;
import com.cleverweb.service.system.buttonrights.ButtonrightsManager;
import com.cleverweb.service.system.fhbutton.FhbuttonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cleverweb.web.controller.BaseController;
import com.cleverweb.core.entity.system.Role;
import com.cleverweb.service.system.role.RoleManager;
import com.cleverweb.common.util.AppUtil;
import com.cleverweb.core.utils.Jurisdiction;
import com.cleverweb.common.util.PageData;

/**
 * 按钮权限
 */
@Controller
@RequestMapping(value = "/buttonrights")
public class ButtonrightsController extends BaseController {


    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysRoleButtonService sysRoleButtonService;
    @Autowired
    private ISysButtonService sysButtonService;


    /**
     * 列表
     *
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "roleId", defaultValue = "1") String roleId) {
        ModelAndView mv = new ModelAndView();
        List<TbSysRole> menuRoleList = sysRoleService.findByParentId("0");
        List<TbSysRole> roleList = sysRoleService.findByParentId(roleId);
        List<TbSysButton> sysButtonList = sysButtonService.findList();            //列出所有按钮
        List<TbSysRoleButton> sysRoleButtonList = sysRoleButtonService.findList();    //列出所有角色按钮关联数据
        TbSysRole sysRole = sysRoleService.findByRoleId(roleId);                                //取得点击的角色组(横排的)
        mv.addObject("sysRole", sysRole);
        mv.addObject("menuRoleList", menuRoleList);
        mv.addObject("roleList", roleList);
        mv.addObject("sysButtonList", sysButtonList);
        mv.addObject("sysRoleButtonList", sysRoleButtonList);
        mv.addObject("QX", Jurisdiction.getHC());    //按钮权限
        mv.setViewName("system/buttonrights/buttonrights_list");
        return mv;
    }

    /**
     * 点击按钮处理关联表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upRb")
    public CWResponse updateRolebuttonrightd(@RequestParam(value = "roleId",defaultValue = "")String roleId,
                                             @RequestParam(value = "buttonId",defaultValue = "")String buttonId) {
        if (!Jurisdiction.buttonJurisdiction("", "edit")) {
            return null;
        } //校验权限
        TbSysRoleButton syeRoleButton = sysRoleButtonService.findByRoleIdAndButtonId(roleId,buttonId);
        if (null != syeRoleButton) {    //判断关联表是否有数据 是:删除/否:新增
            sysRoleButtonService.deleteById(syeRoleButton.getRbId());        //删除
        } else {
            TbSysRoleButton saveTbSysRoleButton = new TbSysRoleButton();
            saveTbSysRoleButton.setRbId(this.get32UUID());
            saveTbSysRoleButton.setButtonId(buttonId);
            saveTbSysRoleButton.setRoleId(roleId);
            sysRoleButtonService.save(saveTbSysRoleButton);        //新增
        }
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
}
