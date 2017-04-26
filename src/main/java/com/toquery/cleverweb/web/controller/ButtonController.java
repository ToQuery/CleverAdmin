package com.toquery.cleverweb.web.controller;

import com.toquery.cleverweb.common.util.AppUtil;
import com.toquery.cleverweb.common.util.PageData;
import com.toquery.cleverweb.common.util.UuidUtil;
import com.toquery.cleverweb.core.utils.Jurisdiction;
import com.toquery.cleverweb.entity.po.TbSysButton;
import com.toquery.cleverweb.service.ISysButtonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：按钮管理
 * 创建人：FH Q313596790
 * 创建时间：2016-01-15
 */
@Controller
@RequestMapping(value = "/fhbutton")
public class ButtonController extends BaseController {


    @Resource
    private ISysButtonService sysButtonService;

    /**
     * 保存
     *
     */
    @RequestMapping(value = "/save")
    public ModelAndView save(@ModelAttribute TbSysButton sysButton) {
       /* if (!Jurisdiction.buttonJurisdiction("", "add")) {
            return null;
        }*/ //校验权限
        ModelAndView mv = new ModelAndView("save_result");
        String buttonId = UuidUtil.get32UUID();
        sysButton.setButtonId(buttonId);
        sysButtonService.save(sysButton);
        mv.addObject("msg", "success");
        return mv;
    }

    /**
     * 删除
     *
     */
    @RequestMapping(value = "/delete")
    public void delete(PrintWriter out) throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "del")) {
            return;
        } //校验权限
        PageData pd = new PageData();
        // sysButtonService.delete(pd);
        out.write("success");
        out.close();
    }

    /**
     * 修改
     *
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit() throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "edit")) {
            return null;
        } //校验权限
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        sysButtonService.saveAndFlush(new TbSysButton());
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        ModelAndView mv = new ModelAndView();
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<TbSysButton> page = sysButtonService.findList(pageable);
        mv.setViewName("system/fhbutton/fhbutton_list");
        mv.addObject("sysButtonList", page.getContent());
        mv.addObject("QX", Jurisdiction.getHC());    //按钮权限
        mv.addObject("page", page);
        return mv;
    }

    /**
     * 去新增页面
     *
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() throws Exception {
        ModelAndView mv = new ModelAndView("system/fhbutton/fhbutton_edit");
        mv.addObject("msg", "save");
        return mv;
    }

    /**
     * 去修改页面
     *
     */
    @RequestMapping(value = "/goEdit")
    public ModelAndView goEdit() throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        List<TbSysButton> listByRoleId = sysButtonService.findListByRoleId("");    //根据ID读取
        mv.setViewName("system/fhbutton/fhbutton_edit");
        mv.addObject("msg", "edit");
        mv.addObject("pd", pd);
        return mv;
    }

    /**
     * 批量删除
     *
     */
    @RequestMapping(value = "/deleteAll")
    @ResponseBody
    public Object deleteAll() throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "del")) {
            return null;
        } //校验权限
        PageData pd = new PageData();
        Map<String, Object> map = new HashMap<String, Object>();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if (null != DATA_IDS && !"".equals(DATA_IDS)) {
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            //sysButtonService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        } else {
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    /**
     * 导出到excel
     *
     */
    @RequestMapping(value = "/excel")
    public ModelAndView exportExcel() throws Exception {
        if (!Jurisdiction.buttonJurisdiction("", "cha")) {
            return null;
        }
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("名称");    //1
        titles.add("权限标识");    //2
        titles.add("备注");    //3
        dataMap.put("titles", titles);
        Page<TbSysButton> varOList = sysButtonService.findList(null);
        List<PageData> varList = new ArrayList<PageData>();
        for (int i = 0; i < varOList.getContent().size(); i++) {
            PageData vpd = new PageData();
            vpd.put("var1", varOList.getContent().get(i).getButtonName());    //1
            vpd.put("var2", varOList.getContent().get(i).getQxName());    //2
            vpd.put("var3", varOList.getContent().get(i).getRemark());    //3
            varList.add(vpd);
        }
        dataMap.put("varList", varList);
//        ObjectExcelView erv = new ObjectExcelView();
//        mv = new ModelAndView(erv, dataMap);
        return mv;
    }
}
