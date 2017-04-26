package com.toquery.cleverweb.web.controller;

import com.toquery.cleverweb.common.util.UuidUtil;
import com.toquery.cleverweb.core.entity.vo.CWResponse;
import com.toquery.cleverweb.core.utils.Jurisdiction;
import com.toquery.cleverweb.entity.po.TbSysButton;
import com.toquery.cleverweb.entity.po.TbSysRole;
import com.toquery.cleverweb.entity.po.TbSysRoleButton;
import com.toquery.cleverweb.service.ISysButtonService;
import com.toquery.cleverweb.service.ISysRoleButtonService;
import com.toquery.cleverweb.service.ISysRoleService;
import com.toquery.cleverweb.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 按钮权限
 */
@Controller
@RequestMapping(value = "/buttonrights")
public class ButtonrightsController extends BaseController {


    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysRoleButtonService sysRoleButtonService;
    @Resource
    private ISysButtonService sysButtonService;


    /**
     * 列表
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
    public CWResponse updateRolebuttonrightd(@RequestParam(value = "roleId", defaultValue = "") String roleId,
                                             @RequestParam(value = "buttonId", defaultValue = "") String buttonId) {
        if (!Jurisdiction.buttonJurisdiction("", "edit")) {
            return null;
        } //校验权限
        TbSysRoleButton syeRoleButton = sysRoleButtonService.findByRoleIdAndButtonId(roleId, buttonId);
        if (null != syeRoleButton) {    //判断关联表是否有数据 是:删除/否:新增
            sysRoleButtonService.deleteById(syeRoleButton.getRbId());        //删除
        } else {
            TbSysRoleButton saveTbSysRoleButton = new TbSysRoleButton();
            saveTbSysRoleButton.setRbId(UuidUtil.get32UUID());
            saveTbSysRoleButton.setButtonId(buttonId);
            saveTbSysRoleButton.setRoleId(roleId);
            sysRoleButtonService.save(saveTbSysRoleButton);        //新增
        }
        return null;
    }
}
