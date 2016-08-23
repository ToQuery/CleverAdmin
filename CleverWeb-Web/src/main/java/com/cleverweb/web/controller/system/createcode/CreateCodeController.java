package com.cleverweb.web.controller.system.createcode;

import com.cleverweb.common.util.*;
import com.cleverweb.core.entity.Page;
import com.cleverweb.core.utils.Jurisdiction;
import com.cleverweb.service.system.createcode.CreateCodeManager;
import com.cleverweb.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;


/** 
 * 类名称： 代码生成器
 * 创建人：FH Q313596790
 * 修改时间：2015年11月23日
 * @version
 */
@Controller
@RequestMapping(value="/createCode")
public class CreateCodeController extends BaseController {
	
	String menuUrl = "createcode/list.do"; //菜单地址(权限用)
	@Resource(name="createcodeService")
	private CreateCodeManager createcodeService;
	
	/**列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");	//检索条件
		if(null != keywords && !"".equals(keywords)){
			keywords = keywords.trim();
			pd.put("keywords", keywords);
		}
		page.setPd(pd);
		List<PageData>	varList = createcodeService.list(page);	//列出CreateCode列表
		mv.setViewName("system/createcode/createcode_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去代码生成器页面(进入弹窗)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goProductCode")
	public ModelAndView goProductCode() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String CREATECODE_ID = pd.getString("CREATECODE_ID");
		if(!"add".equals(CREATECODE_ID)){
			pd = createcodeService.findById(pd);
			mv.addObject("pd", pd);
			mv.addObject("msg", "edit");
		}else{
			mv.addObject("msg", "add");
		}
		mv.setViewName("system/createcode/productCode");
		return mv;
	}
	
	/**生成代码
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/proCode")
	public void proCode(HttpServletResponse response) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){} 		//校验权限
		logBefore(logger, Jurisdiction.getUsername()+"执行代码生成器");
		PageData pd = new PageData();
		pd = this.getPageData();
		save(pd);	//保存到数据库
		/* ============================================================================================= */
		String TITLE = pd.getString("TITLE");  						//说明				========参数0
		String packageName = pd.getString("packageName");  			//包名				========参数1
		String objectName = pd.getString("objectName");	   			//类名				========参数2
		String tabletop = pd.getString("tabletop");	   				//表前缀				========参数3
		tabletop = null == tabletop?"":tabletop.toUpperCase();		//表前缀转大写
		String zindext = pd.getString("zindex");	   	   			//属性总数
		int zindex = 0;
		if(null != zindext && !"".equals(zindext)){
			zindex = Integer.parseInt(zindext);
		}
		List<String[]> fieldList = new ArrayList<String[]>();   	//属性集合			========参数4
		for(int i=0; i< zindex; i++){
			fieldList.add(pd.getString("field"+i).split(",fh,"));	//属性放到集合里面
		}
		Map<String,Object> root = new HashMap<String,Object>();		//创建数据模型
		root.put("fieldList", fieldList);
		root.put("TITLE", TITLE);									//说明
		root.put("packageName", packageName);						//包名
		root.put("objectName", objectName);							//类名
		root.put("objectNameLower", objectName.toLowerCase());		//类名(全小写)
		root.put("objectNameUpper", objectName.toUpperCase());		//类名(全大写)
		root.put("tabletop", tabletop);								//表前缀	
		root.put("nowDate", new Date());							//当前日期
		
		DelAllFile.delFolder(PathUtil.getClasspath()+"admin/ftl"); //生成代码前,先清空之前生成的代码
		/* ============================================================================================= */
		
		String filePath = "admin/ftl/code/";						//存放路径
		String ftlPath = "createCode";								//ftl路径
		
		/*生成controller*/
		Freemarker.printFile("controllerTemplate.ftl", root, "controller/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Controller.java", filePath, ftlPath);
		
		/*生成service*/
		Freemarker.printFile("serviceTemplate.ftl", root, "service/"+packageName+"/"+objectName.toLowerCase()+"/impl/"+objectName+"Service.java", filePath, ftlPath);
		
		/*生成manager*/
		Freemarker.printFile("managerTemplate.ftl", root, "service/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Manager.java", filePath, ftlPath);

		/*生成mybatis xml*/
		Freemarker.printFile("mapperMysqlTemplate.ftl", root, "mybatis_mysql/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
		Freemarker.printFile("mapperOracleTemplate.ftl", root, "mybatis_oracle/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
		Freemarker.printFile("mapperSqlserverTemplate.ftl", root, "mybatis_sqlserver/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
		
		/*生成SQL脚本*/
		Freemarker.printFile("mysql_SQL_Template.ftl", root, "mysql数据库脚本/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
		Freemarker.printFile("oracle_SQL_Template.ftl", root, "oracle数据库脚本/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
		Freemarker.printFile("sqlserver_SQL_Template.ftl", root, "sqlserver数据库脚本/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
		
		/*生成jsp页面*/
		Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_list.jsp", filePath, ftlPath);
		Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_edit.jsp", filePath, ftlPath);
		
		/*生成说明文档*/
		Freemarker.printFile("docTemplate.ftl", root, "部署说明.doc", filePath, ftlPath);
		
		//this.print("oracle_SQL_Template.ftl", root);  控制台打印
		
		/*生成的全部代码压缩成zip文件*/
		if(FileZip.zip(PathUtil.getClasspath()+"admin/ftl/code", PathUtil.getClasspath()+"admin/ftl/code.zip")){
			/*下载代码*/
			FileDownload.fileDownload(response, PathUtil.getClasspath()+"admin/ftl/code.zip", "code.zip");
		}
	}
	
	/**保存到数据库
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception{
		pd.put("PACKAGENAME", pd.getString("packageName"));	//包名
		pd.put("OBJECTNAME", pd.getString("objectName"));	//类名
		pd.put("TABLENAME", pd.getString("tabletop")+",fh,"+pd.getString("objectName").toUpperCase());	//表名
		pd.put("FIELDLIST", pd.getString("FIELDLIST"));		//属性集合
		pd.put("CREATETIME", DateUtil.getTime());			//创建时间
		pd.put("TITLE", pd.getString("TITLE"));				//说明
		pd.put("CREATECODE_ID", this.get32UUID());			//主键
		createcodeService.save(pd);
	}
	
	/**删除
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除CreateCode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		createcodeService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception {
		logBefore(logger, Jurisdiction.getUsername()+"批量删除CreateCode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				createcodeService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
}
//FHQ 3 1 3 5 9 6 7 9 0