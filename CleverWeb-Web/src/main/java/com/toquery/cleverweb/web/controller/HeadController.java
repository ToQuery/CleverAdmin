package com.cleverweb.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.cleverweb.common.util.mail.SimpleMailSender;
import com.cleverweb.core.entity.vo.CWResponse;
import com.cleverweb.entity.vo.SysUserRole;
import com.cleverweb.service.ISysUserService;
import com.cleverweb.service.system.fhsms.FhsmsManager;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cleverweb.common.util.AppUtil;
import com.cleverweb.common.util.Const;
import com.cleverweb.core.utils.Jurisdiction;
import com.cleverweb.common.util.PageData;
import com.cleverweb.common.util.Tools;

/**
 *
 */
@Controller
@RequestMapping(value = "/head")
public class HeadController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    //	@Resource(name="userService")
//	private UserManager userService;
    @Resource(name = "fhsmsService")
    private FhsmsManager fhsmsService;

    /**
     * 获取头部信息
     *
     * @return
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public CWResponse getList(HttpSession session) {
        Map<String, Object> data = new HashedMap();
        SysUserRole sysUserRole = (SysUserRole) session.getAttribute(Const.SESSION_USER);
        data.put("sysUser", sysUserRole);
        data.put("fhsmsCount", fhsmsService.findFhsmsCount(sysUserRole.getUserName()).get("fhsmsCount").toString());//站内信未读总数
        String strWEBSOCKET = Tools.readTxtFile(Const.WEBSOCKET);//读取WEBSOCKET配置
        if (null != strWEBSOCKET && !"".equals(strWEBSOCKET)) {
            String strIW[] = strWEBSOCKET.split(",fh,");
            if (strIW.length == 5) {
                data.put("wimadress", strIW[0] + ":" + strIW[1]);    //即时聊天服务器IP和端口
                data.put("oladress", strIW[2] + ":" + strIW[3]);        //在线管理和站内信服务器IP和端口
                data.put("FHsmsSound", strIW[4]);                //站内信提示音效配置
            }
        }
        return new CWResponse<Map>("0000","成功",data);
    }

    /**
     * 获取站内信未读总数
     *
     * @return
     */
    @RequestMapping(value = "/getFhsmsCount")
    @ResponseBody
    public Object getFhsmsCount() {
        PageData pd = new PageData();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("fhsmsCount", fhsmsService.findFhsmsCount(Jurisdiction.getUsername()).get("fhsmsCount").toString());//站内信未读总数
        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {
            logAfter(logger);
        }
        return AppUtil.returnObject(pd, map);
    }

    /**
     * 去发送邮箱页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editEmail")
    public ModelAndView editEmail() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.setViewName("system/head/edit_email");
        mv.addObject("pd", pd);
        return mv;
    }

    /**
     * 去发送短信页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goSendSms")
    public ModelAndView goSendSms() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.setViewName("system/head/send_sms");
        mv.addObject("pd", pd);
        return mv;
    }


    /**
     * 去发送电子邮件页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goSendEmail")
    public ModelAndView goSendEmail() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.setViewName("system/head/send_email");
        mv.addObject("pd", pd);
        return mv;
    }

    /**
     * 发送电子邮件
     *
     * @return
     */
    @RequestMapping(value = "/sendEmail")
    @ResponseBody
    public Object sendEmail() {
        PageData pd = new PageData();
        pd = this.getPageData();
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = "ok";        //发送状态
        int count = 0;            //统计发送成功条数
        int zcount = 0;            //理论条数
        String strEMAIL = Tools.readTxtFile(Const.EMAIL);        //读取邮件配置
        List<PageData> pdList = new ArrayList<PageData>();
        String toEMAIL = pd.getString("EMAIL");                    //对方邮箱
        String TITLE = pd.getString("TITLE");                    //标题
        String CONTENT = pd.getString("CONTENT");                //内容
        String TYPE = pd.getString("TYPE");                        //类型
        String isAll = pd.getString("isAll");                    //是否发送给全体成员 yes or no
        String fmsg = pd.getString("fmsg");                        //判断是系统用户还是会员 "appuser"为会员用户
        if (null != strEMAIL && !"".equals(strEMAIL)) {
            String strEM[] = strEMAIL.split(",fh,");
            if (strEM.length == 4) {
                if ("yes".endsWith(isAll)) {
                    try {
                        List<PageData> userList = new ArrayList<PageData>();
                        zcount = userList.size();
                        try {
                            for (int i = 0; i < userList.size(); i++) {
                                if (Tools.checkEmail(userList.get(i).getString("EMAIL"))) {        //邮箱格式不对就跳过
                                    SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], userList.get(i).getString("EMAIL"), TITLE, CONTENT, TYPE);//调用发送邮件函数
                                    count++;
                                } else {
                                    continue;
                                }
                            }
                            msg = "ok";
                        } catch (Exception e) {
                            msg = "error";
                        }
                    } catch (Exception e) {
                        msg = "error";
                    }
                } else {
                    toEMAIL = toEMAIL.replaceAll("；", ";");
                    toEMAIL = toEMAIL.replaceAll(" ", "");
                    String[] arrTITLE = toEMAIL.split(";");
                    zcount = arrTITLE.length;
                    try {
                        for (int i = 0; i < arrTITLE.length; i++) {
                            if (Tools.checkEmail(arrTITLE[i])) {        //邮箱格式不对就跳过
                                SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], arrTITLE[i], TITLE, CONTENT, TYPE);//调用发送邮件函数
                                count++;
                            } else {
                                continue;
                            }
                        }
                        msg = "ok";
                    } catch (Exception e) {
                        msg = "error";
                    }
                }
            } else {
                msg = "error";
            }
        } else {
            msg = "error";
        }
        pd.put("msg", msg);
        pd.put("count", count);                        //成功数
        pd.put("ecount", zcount - count);                //失败数
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    /**
     * 去系统设置页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goSystem")
    public ModelAndView goEditEmail() throws Exception {
        if (!"admin".equals(Jurisdiction.getUsername())) {
            return null;
        }    //非admin用户不能修改
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("YSYNAME", Tools.readTxtFile(Const.SYSNAME));     //读取系统名称
        pd.put("COUNTPAGE", Tools.readTxtFile(Const.PAGE));         //读取每页条数
        String strEMAIL = Tools.readTxtFile(Const.EMAIL);         //读取邮件配置
        String strSMS1 = Tools.readTxtFile(Const.SMS1);             //读取短信1配置
        String strSMS2 = Tools.readTxtFile(Const.SMS2);             //读取短信2配置
        String strFWATERM = Tools.readTxtFile(Const.FWATERM);     //读取文字水印配置
        String strIWATERM = Tools.readTxtFile(Const.IWATERM);     //读取图片水印配置
        pd.put("Token", Tools.readTxtFile(Const.WEIXIN));         //读取微信配置
        String strWEBSOCKET = Tools.readTxtFile(Const.WEBSOCKET);//读取WEBSOCKET配置
        if (null != strEMAIL && !"".equals(strEMAIL)) {
            String strEM[] = strEMAIL.split(",fh,");
            if (strEM.length == 4) {
                pd.put("SMTP", strEM[0]);
                pd.put("PORT", strEM[1]);
                pd.put("EMAIL", strEM[2]);
                pd.put("PAW", strEM[3]);
            }
        }
        if (null != strSMS1 && !"".equals(strSMS1)) {
            String strS1[] = strSMS1.split(",fh,");
            if (strS1.length == 2) {
                pd.put("SMSU1", strS1[0]);
                pd.put("SMSPAW1", strS1[1]);
            }
        }
        if (null != strSMS2 && !"".equals(strSMS2)) {
            String strS2[] = strSMS2.split(",fh,");
            if (strS2.length == 2) {
                pd.put("SMSU2", strS2[0]);
                pd.put("SMSPAW2", strS2[1]);
            }
        }
        if (null != strFWATERM && !"".equals(strFWATERM)) {
            String strFW[] = strFWATERM.split(",fh,");
            if (strFW.length == 5) {
                pd.put("isCheck1", strFW[0]);
                pd.put("fcontent", strFW[1]);
                pd.put("fontSize", strFW[2]);
                pd.put("fontX", strFW[3]);
                pd.put("fontY", strFW[4]);
            }
        }
        if (null != strIWATERM && !"".equals(strIWATERM)) {
            String strIW[] = strIWATERM.split(",fh,");
            if (strIW.length == 4) {
                pd.put("isCheck2", strIW[0]);
                pd.put("imgUrl", strIW[1]);
                pd.put("imgX", strIW[2]);
                pd.put("imgY", strIW[3]);
            }
        }
        if (null != strWEBSOCKET && !"".equals(strWEBSOCKET)) {
            String strIW[] = strWEBSOCKET.split(",fh,");
            if (strIW.length == 5) {
                pd.put("WIMIP", strIW[0]);
                pd.put("WIMPORT", strIW[1]);
                pd.put("OLIP", strIW[2]);
                pd.put("OLPORT", strIW[3]);
                pd.put("FHsmsSound", strIW[4]);
            }
        }
        mv.setViewName("system/head/sys_edit");
        mv.addObject("pd", pd);
        return mv;
    }

    /**
     * 保存系统设置1
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveSys")
    public ModelAndView saveSys() throws Exception {
        if (!"admin".equals(Jurisdiction.getUsername())) {
            return null;
        }    //非admin用户不能修改
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        Tools.writeFile(Const.SYSNAME, pd.getString("YSYNAME"));    //写入系统名称
        Tools.writeFile(Const.PAGE, pd.getString("COUNTPAGE"));    //写入每页条数
        Tools.writeFile(Const.EMAIL, pd.getString("SMTP") + ",fh," + pd.getString("PORT") + ",fh," + pd.getString("EMAIL") + ",fh," + pd.getString("PAW"));    //写入邮件服务器配置
        Tools.writeFile(Const.SMS1, pd.getString("SMSU1") + ",fh," + pd.getString("SMSPAW1"));    //写入短信1配置
        Tools.writeFile(Const.SMS2, pd.getString("SMSU2") + ",fh," + pd.getString("SMSPAW2"));    //写入短信2配置
        mv.addObject("msg", "OK");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 保存系统设置2
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveSys2")
    public ModelAndView saveSys2() throws Exception {
        if (!"admin".equals(Jurisdiction.getUsername())) {
            return null;
        }    //非admin用户不能修改
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        Tools.writeFile(Const.FWATERM, pd.getString("isCheck1") + ",fh," + pd.getString("fcontent") + ",fh," + pd.getString("fontSize") + ",fh," + pd.getString("fontX") + ",fh," + pd.getString("fontY"));    //文字水印配置
        Tools.writeFile(Const.IWATERM, pd.getString("isCheck2") + ",fh," + pd.getString("imgUrl") + ",fh," + pd.getString("imgX") + ",fh," + pd.getString("imgY"));    //图片水印配置
        mv.addObject("msg", "OK");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 保存系统设置3
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveSys3")
    public ModelAndView saveSys3() throws Exception {
        if (!"admin".equals(Jurisdiction.getUsername())) {
            return null;
        }    //非admin用户不能修改
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        Tools.writeFile(Const.WEIXIN, pd.getString("Token"));    //写入微信配置
        Tools.writeFile(Const.WEBSOCKET, pd.getString("WIMIP") + ",fh," + pd.getString("WIMPORT") + ",fh," + pd.getString("OLIP") + ",fh," + pd.getString("OLPORT") + ",fh," + pd.getString("FHsmsSound"));    //websocket配置
        mv.addObject("msg", "OK");
        mv.setViewName("save_result");
        return mv;
    }

}
