package com.cleverweb.web.controller.system.tools;


import com.cleverweb.common.util.AppUtil;
import com.cleverweb.common.util.Const;
import com.cleverweb.common.util.DelAllFile;
import com.cleverweb.common.util.FileDownload;
import com.cleverweb.common.util.Freemarker;
import com.cleverweb.common.util.MapDistance;
import com.cleverweb.common.util.PageData;
import com.cleverweb.common.util.PathUtil;
import com.cleverweb.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/** 
 * 类名称：ToolController 系统工具
 * 创建人：FH Q313596790
 * 修改时间：2015年11月23日
 * @version
 */
@Controller
@RequestMapping(value="/tool")
public class ToolController extends BaseController {
	
	/**去接口测试页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/interfaceTest")
	public ModelAndView editEmail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/interfaceTest");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 *	接口内部请求
	 * @param 
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	@RequestMapping(value="/severTest")
	@ResponseBody
	public Object severTest() throws UnsupportedEncodingException{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success",str = "",rTime="";
		try{
			long startTime = System.currentTimeMillis(); 					//请求起始时间_毫秒
			URL url = new URL(pd.getString("serverUrl"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(pd.getString("requestMethod"));		//请求类型  POST or GET	
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			long endTime = System.currentTimeMillis(); 						//请求结束时间_毫秒
			String temp = "";
			while((temp = in.readLine()) != null){ 
				str = str + temp;
			}
			rTime = String.valueOf(endTime - startTime); 
		}
		catch(Exception e){
			errInfo = "error";
		}
		map.put("errInfo", errInfo);//状态信息
		map.put("result", str);		//返回结果
		map.put("rTime", rTime);	//服务器请求时间 毫秒
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**发送邮件页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goSendEmail")
	public ModelAndView goSendEmail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/email");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**表单构建页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goFormbuilder")
	public ModelAndView goFormbuilder() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/form_builder");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**生成文件并下载（生成的表单构建页面代码放到jsp页面）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadFormCode")
	public void downloadFormCode(HttpServletResponse response) throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> root = new HashMap<String,Object>();		//创建数据模型
		root.put("htmlCode", pd.getString("htmlCode"));
		DelAllFile.delFolder(PathUtil.getClasspath()+"admin/ftl"); //生成代码前,先清空之前生成的代码
		String filePath = "admin/ftl/code/";						//存放路径
		String ftlPath = "createCode";								//ftl路径
		/*生成controller*/
		Freemarker.printFile("newJsp.ftl", root, "newJsp.jsp", filePath, ftlPath);
		FileDownload.fileDownload(response, PathUtil.getClasspath()+"admin/ftl/code/newJsp.jsp", "newJsp.jsp");
	}
	
	/**二维码页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goTwoDimensionCode")
	public ModelAndView goTwoDimensionCode() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/twoDimensionCode");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 *	生成二维码
	 * @throws Exception
	 */
	@RequestMapping(value="/createTwoDimensionCode")
	@ResponseBody
	public Object createTwoDimensionCode(){
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success", encoderImgId = this.get32UUID()+".png"; //encoderImgId此处二维码的图片名
		String encoderContent = pd.getString("encoderContent");				//内容
		if(null == encoderContent){
			errInfo = "error";
		}else{
			try {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + encoderImgId;  //存放路径
//				TwoDimensionCode.encoderQRCode(encoderContent, filePath, "png");							//执行生成二维码
			} catch (Exception e) {
				errInfo = "error";
			}
		}
		map.put("result", errInfo);						//返回结果
		map.put("encoderImgId", encoderImgId);			//二维码图片名
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 *	解析二维码
	 * @throws Exception
	 */
	@RequestMapping(value="/readTwoDimensionCode")
	@ResponseBody
	public Object readTwoDimensionCode(){
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success",readContent="";
		String imgId = pd.getString("imgId");//内容
		if(null == imgId){
			errInfo = "error";
		}else{
			try {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + imgId;  //存放路径
//				readContent = TwoDimensionCode.decoderQRCode(filePath);//执行读取二维码
			} catch (Exception e) {
				errInfo = "error";
			}
		}
		map.put("result", errInfo);						//返回结果
		map.put("readContent", readContent);			//读取的内容
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**地图页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/map")
	public ModelAndView map() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/map");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**获取地图坐标页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mapXY")
	public ModelAndView mapXY() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/mapXY");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 *	根据经纬度计算距离
	 * @throws Exception
	 */
	@RequestMapping(value="/getDistance")
	@ResponseBody
	public Object getDistance(){
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success",distance="";
		try {
			distance  = MapDistance.getDistance(pd.getString("ZUOBIAO_Y"),pd.getString("ZUOBIAO_X"),pd.getString("ZUOBIAO_Y2"),pd.getString("ZUOBIAO_X2"));
		} catch (Exception e) {
			errInfo = "error";
		}
		map.put("result", errInfo);				//返回结果
		map.put("distance", distance);			//距离
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**图表报表demo页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fusionchartsdemo")
	public ModelAndView fusionchartsdemo() throws Exception{
		ModelAndView mv = this.getModelAndView();
		//FusionCharts 报表demo  用的时候，循环拼字符串即可
	 	String strXML = "<graph caption='前12个月订单销量柱状图' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'>"
	 					+"<set name='2013-05' value='4' color='AFD8F8'/>"
	 					+"<set name='2013-04' value='0' color='AFD8F8'/>"
	 					+"<set name='2013-03' value='0' color='AFD8F8'/>"
	 					+"<set name='2013-02' value='0' color='AFD8F8'/>"
	 					+"<set name='2013-01' value='0' color='AFD8F8'/>"
	 					+"<set name='2012-01' value='0' color='AFD8F8'/>"
	 					+"<set name='2012-11' value='0' color='AFD8F8'/>"
	 					+"<set name='2012-10' value='0' color='AFD8F8'/>"
	 					+"<set name='2012-09' value='0' color='AFD8F8'/>"
	 					+"<set name='2012-08' value='0' color='AFD8F8'/>"
	 					+"<set name='2012-07' value='0' color='AFD8F8'/>"
	 					+"<set name='2012-06' value='0' color='AFD8F8'/>"
	 					+"</graph>" ;
	 	mv.addObject("strXML", strXML);
		mv.setViewName("system/tools/fusionchartsdemo");
		return mv;
	}
	
	/**打印测试页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/printTest")
	public ModelAndView printTest() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/printTest");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**打印预览页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/printPage")
	public ModelAndView printPage() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/printPage");
		mv.addObject("pd", pd);
		return mv;
	}
}
// 创建人：FH Q313596790