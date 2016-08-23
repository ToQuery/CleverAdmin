package com.cleverweb.web.controller.system.dictionaries;

import com.alibaba.fastjson.JSON;
import com.cleverweb.common.util.AppUtil;
import com.cleverweb.core.utils.Jurisdiction;
import com.cleverweb.common.util.PageData;
import com.cleverweb.core.entity.Page;
import com.cleverweb.service.system.dictionaries.DictionariesManager;
import com.cleverweb.web.controller.BaseController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 说明：数据字典
 * 创建人：FH Q313596790
 * 创建时间：2015-12-16
 */
@Controller
@RequestMapping(value = "/dictionaries")
public class DictionariesController extends BaseController {

    String menuUrl = "dictionaries/list.do"; //菜单地址(权限用)
    @Resource(name = "dictionariesService")
    private DictionariesManager dictionariesService;

    /**
     * 保存
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/save")
    public ModelAndView save() throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "新增Dictionaries");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("DICTIONARIES_ID", this.get32UUID());    //主键
        dictionariesService.save(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 删除
     *
     * @param DICTIONARIES_ID
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String DICTIONARIES_ID) throws Exception {
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            return null;
        } //校验权限
        logBefore(logger, Jurisdiction.getUsername() + "删除Dictionaries");
        Map<String, String> map = new HashMap<String, String>();
        PageData pd = new PageData();
        pd.put("DICTIONARIES_ID", DICTIONARIES_ID);
        String errInfo = "success";
        if (dictionariesService.listSubDictByParentId(DICTIONARIES_ID).size() > 0) {//判断是否有子级，是：不允许删除
            errInfo = "false";
        } else {
            pd = dictionariesService.findById(pd);//根据ID读取
            if (null != pd.get("TBSNAME") && !"".equals(pd.getString("TBSNAME"))) {
                String[] table = pd.getString("TBSNAME").split(",");
                for (int i = 0; i < table.length; i++) {
                    pd.put("thisTable", table[i]);
                    try {
                        if (Integer.parseInt(dictionariesService.findFromTbs(pd).get("zs").toString()) > 0) {//判断是否被占用，是：不允许删除(去排查表检查字典表中的编码字段)
                            errInfo = "false";
                            break;
                        }
                    } catch (Exception e) {
                        errInfo = "false2";
                        break;
                    }
                }
            }
        }
        if ("success".equals(errInfo)) {
            dictionariesService.delete(pd);    //执行删除
        }
        map.put("result", errInfo);
        return AppUtil.returnObject(new PageData(), map);
    }

    /**
     * 修改
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit() throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "修改Dictionaries");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        dictionariesService.edit(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 列表
     *
     * @param page
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "列表Dictionaries");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String keywords = pd.getString("keywords");                    //检索条件
        if (null != keywords && !"".equals(keywords)) {
            pd.put("keywords", keywords.trim());
        }
        String DICTIONARIES_ID = null == pd.get("DICTIONARIES_ID") ? "" : pd.get("DICTIONARIES_ID").toString();
        if (null != pd.get("id") && !"".equals(pd.get("id").toString())) {
            DICTIONARIES_ID = pd.get("id").toString();
        }
        pd.put("DICTIONARIES_ID", DICTIONARIES_ID);                    //上级ID
        page.setPd(pd);
        List<PageData> varList = dictionariesService.list(page);    //列出Dictionaries列表
        mv.addObject("pd", dictionariesService.findById(pd));        //传入上级所有信息
        mv.addObject("DICTIONARIES_ID", DICTIONARIES_ID);            //上级ID
        mv.setViewName("system/dictionaries/dictionaries_list");
        mv.addObject("varList", varList);
        mv.addObject("QX", Jurisdiction.getHC());                    //按钮权限
        return mv;
    }

    /**
     * 显示列表ztree
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/listAllDict")
    public ModelAndView listAllDict(Model model, String DICTIONARIES_ID) throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            String json = JSON.toJSONString(dictionariesService.listAllDict("0"));
            json = json.replaceAll("DICTIONARIES_ID", "id").replaceAll("PARENT_ID", "pId").replaceAll("NAME", "name").replaceAll("subDict", "nodes").replaceAll("hasDict", "checked").replaceAll("treeurl", "url");
            model.addAttribute("zTreeNodes", json);
            mv.addObject("DICTIONARIES_ID", DICTIONARIES_ID);
            mv.addObject("pd", pd);
            mv.setViewName("system/dictionaries/dictionaries_ztree");
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 去新增页面
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String DICTIONARIES_ID = null == pd.get("DICTIONARIES_ID") ? "" : pd.get("DICTIONARIES_ID").toString();
        pd.put("DICTIONARIES_ID", DICTIONARIES_ID);                    //上级ID
        mv.addObject("pds", dictionariesService.findById(pd));        //传入上级所有信息
        mv.addObject("DICTIONARIES_ID", DICTIONARIES_ID);            //传入ID，作为子级ID用
        mv.setViewName("system/dictionaries/dictionaries_edit");
        mv.addObject("msg", "save");
        return mv;
    }

    /**
     * 去修改页面
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/goEdit")
    public ModelAndView goEdit() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String DICTIONARIES_ID = pd.getString("DICTIONARIES_ID");
        pd = dictionariesService.findById(pd);    //根据ID读取
        mv.addObject("pd", pd);                    //放入视图容器
        pd.put("DICTIONARIES_ID", pd.get("PARENT_ID").toString());            //用作上级信息
        mv.addObject("pds", dictionariesService.findById(pd));                //传入上级所有信息
        mv.addObject("DICTIONARIES_ID", pd.get("PARENT_ID").toString());    //传入上级ID，作为子ID用
        pd.put("DICTIONARIES_ID", DICTIONARIES_ID);                            //复原本ID
        mv.setViewName("system/dictionaries/dictionaries_edit");
        mv.addObject("msg", "edit");
        return mv;
    }

    /**
     * 判断编码是否存在
     *
     * @return
     */
    @RequestMapping(value = "/hasBianma")
    @ResponseBody
    public Object hasBianma() {
        Map<String, String> map = new HashMap<String, String>();
        String errInfo = "success";
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            if (dictionariesService.findByBianma(pd) != null) {
                errInfo = "error";
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        map.put("result", errInfo);                //返回结果
        return AppUtil.returnObject(new PageData(), map);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
}
