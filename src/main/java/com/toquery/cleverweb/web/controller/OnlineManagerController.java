package com.toquery.cleverweb.web.controller;

import com.toquery.cleverweb.core.utils.Jurisdiction;
import com.toquery.cleverweb.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/** 
 * 类名称：在线管理列表
 * 创建人：FH 
 * 创建时间：2015-05-25
 */
@Controller
@RequestMapping(value="/onlinemanager")
public class OnlineManagerController extends BaseController {
	
	String menuUrl = "onlinemanager/list.do"; //菜单地址(权限用)
	
	/**列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(){
//		if(!Jurisdiction.buttonJurisdiction("", "cha")){return null;} //校验权限
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/onlinemanager/onlinemanager_list");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
}