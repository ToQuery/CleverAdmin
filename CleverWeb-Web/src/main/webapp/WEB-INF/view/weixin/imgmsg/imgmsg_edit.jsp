<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#KEYWORD").val()==""){
			$("#KEYWORD").tips({
				side:3,
	            msg:'请输入关键词',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEYWORD").focus();
			return false;
		}
		if($("#BZ").val()==""){
			$("#BZ").tips({
				side:3,
	            msg:'请输入备注',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BZ").focus();
			return false;
		}
		if($("#TITLE1").val()==""){
			$("#TITLE1").tips({
				side:3,
	            msg:'请输入标题1',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TITLE1").focus();
			return false;
		}
		if($("#DESCRIPTION1").val()==""){
			$("#DESCRIPTION1").tips({
				side:3,
	            msg:'请输入描述1',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DESCRIPTION1").focus();
			return false;
		}
		if($("#IMGURL1").val()==""){
			$("#IMGURL1").tips({
				side:3,
	            msg:'请输入图片地址1',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IMGURL1").focus();
			return false;
		}
		if($("#TOURL1").val()==""){
			$("#TOURL1").tips({
				side:3,
	            msg:'请输入超链接1',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TOURL1").focus();
			return false;
		}

		if($("#STATUS").val()==""){
			$("#form-field-radio1").tips({
				side:3,
	            msg:'请输入状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATUS").focus();
			return false;
		}
		hasK();
	}
	
	//判断关键词是否存在
	function hasK(){
		var KEYWORD = $("#KEYWORD").val();
		var IMGMSG_ID = "${pd.IMGMSG_ID}";
		$.ajax({
			type: "POST",
			url: '<%=basePath%>textmsg/hasK.do',
	    	data: {KEYWORD:KEYWORD,IMGMSG_ID:IMGMSG_ID,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
					$("#Form").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					$("#KEYWORD").tips({
						side:3,
			            msg:'此关键词已存在(全局)!',
			            bg:'#AE81FF',
			            time:3
			        });
					$("#KEYWORD").focus();
					return false;
				 }
			}
		});
	}
	
	function setType(value){
		$("#STATUS").val(value);
	}
</script>
	</head>
<body>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">


					<form action="imgmsg/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="IMGMSG_ID" id="IMGMSG_ID" value="${pd.IMGMSG_ID}"/>
						<input type="hidden" name="STATUS" id="STATUS" value="${pd.STATUS}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover" style="width:100%;">
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">关键词:</td>
								<td><input style="width:95%;" type="text" name="KEYWORD" id="KEYWORD" value="${pd.KEYWORD}" maxlength="200" placeholder="这里输入关键词" title="关键词"/></td>
								<td style="width:70px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input style="width:95%;" type="text" name="BZ" id="BZ" value="${pd.BZ}" maxlength="200" placeholder="这里输入备注" title="备注"/></td>
							</tr>
						</table>
						<table id="table_report" class="table table-striped table-bordered table-hover" style="width:100%;">
							<tr>
								<td>
									<ul class="unstyled spaced2">
										<li class="text-warning orange"><i class="icon-star blue"></i>
											第一行必录, 总共可以添加8条图文, 只录入第一条是显示单条图文, 录入其它则显示多条图文
										</li>
									</ul>
								</td>
							</tr>
						</table>
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td><input type="text" name="TITLE1" id="TITLE1" value="${pd.TITLE1}" maxlength="200" placeholder="这里输入标题1" title="标题1"/></td>
								<td><input type="text" name="DESCRIPTION1" id="DESCRIPTION1" value="${pd.DESCRIPTION1}" maxlength="200" placeholder="这里输入描述1" title="描述1"/></td>
								<td><input type="text" name="IMGURL1" id="IMGURL1" value="${pd.IMGURL1}" maxlength="200" placeholder="这里输入图片地址(完整路径)1" title="图片地址1"/></td>
								<td><input type="text" name="TOURL1" id="TOURL1" value="${pd.TOURL1}" maxlength="200" placeholder="这里输入超链接1" title="超链接1"/></td>
							</tr>
							<tr>
								<td><input type="text" name="TITLE2" id="TITLE2" value="${pd.TITLE2}" maxlength="200" placeholder="这里输入标题2" title="标题2"/></td>
								<td><input type="text" name="DESCRIPTION2" id="DESCRIPTION2" value="${pd.DESCRIPTION2}" maxlength="200" placeholder="这里输入描述2" title="描述2"/></td>
								<td><input type="text" name="IMGURL2" id="IMGURL2" value="${pd.IMGURL2}" maxlength="200" placeholder="这里输入图片地址(完整路径)2" title="图片地址2"/></td>
								<td><input type="text" name="TOURL2" id="TOURL2" value="${pd.TOURL2}" maxlength="200" placeholder="这里输入超链接2" title="超链接2"/></td>
							</tr>
							<tr>
								<td><input type="text" name="TITLE3" id="TITLE3" value="${pd.TITLE3}" maxlength="200" placeholder="这里输入标题3" title="标题3"/></td>
								<td><input type="text" name="DESCRIPTION3" id="DESCRIPTION3" value="${pd.DESCRIPTION3}" maxlength="200" placeholder="这里输入描述3" title="描述3"/></td>
								<td><input type="text" name="IMGURL3" id="IMGURL3" value="${pd.IMGURL3}" maxlength="200" placeholder="这里输入图片地址(完整路径)3" title="图片地址3"/></td>
								<td><input type="text" name="TOURL3" id="TOURL3" value="${pd.TOURL3}" maxlength="200" placeholder="这里输入超链接3" title="超链接3"/></td>
							</tr>
							<tr>
								<td><input type="text" name="TITLE4" id="TITLE4" value="${pd.TITLE4}" maxlength="200" placeholder="这里输入标题4" title="标题4"/></td>
								<td><input type="text" name="DESCRIPTION4" id="DESCRIPTION4" value="${pd.DESCRIPTION4}" maxlength="200" placeholder="这里输入描述4" title="描述4"/></td>
								<td><input type="text" name="IMGURL4" id="IMGURL4" value="${pd.IMGURL4}" maxlength="200" placeholder="这里输入图片地址(完整路径)4" title="图片地址4"/></td>
								<td><input type="text" name="TOURL4" id="TOURL4" value="${pd.TOURL4}" maxlength="200" placeholder="这里输入超链接4" title="超链接4"/></td>
							</tr>
							<tr>
								<td><input type="text" name="TITLE5" id="TITLE5" value="${pd.TITLE5}" maxlength="200" placeholder="这里输入标题5" title="标题5"/></td>
								<td><input type="text" name="DESCRIPTION5" id="DESCRIPTION5" value="${pd.DESCRIPTION5}" maxlength="200" placeholder="这里输入描述5" title="描述5"/></td>
								<td><input type="text" name="IMGURL5" id="IMGURL5" value="${pd.IMGURL5}" maxlength="200" placeholder="这里输入图片地址(完整路径)5" title="图片地址5"/></td>
								<td><input type="text" name="TOURL5" id="TOURL5" value="${pd.TOURL5}" maxlength="200" placeholder="这里输入超链接5" title="超链接5"/></td>
							</tr>
							<tr>
								<td><input type="text" name="TITLE6" id="TITLE6" value="${pd.TITLE6}" maxlength="200" placeholder="这里输入标题6" title="标题6"/></td>
								<td><input type="text" name="DESCRIPTION6" id="DESCRIPTION6" value="${pd.DESCRIPTION6}" maxlength="200" placeholder="这里输入描述6" title="描述6"/></td>
								<td><input type="text" name="IMGURL6" id="IMGURL6" value="${pd.IMGURL6}" maxlength="200" placeholder="这里输入图片地址(完整路径)6" title="图片地址6"/></td>
								<td><input type="text" name="TOURL6" id="TOURL6" value="${pd.TOURL6}" maxlength="200" placeholder="这里输入超链接6" title="超链接6"/></td>
							</tr>
							<tr>
								<td><input type="text" name="TITLE7" id="TITLE7" value="${pd.TITLE7}" maxlength="200" placeholder="这里输入标题7" title="标题7"/></td>
								<td><input type="text" name="DESCRIPTION7" id="DESCRIPTION7" value="${pd.DESCRIPTION7}" maxlength="200" placeholder="这里输入描述7" title="描述7"/></td>
								<td><input type="text" name="IMGURL7" id="IMGURL7" value="${pd.IMGURL7}" maxlength="200" placeholder="这里输入图片地址(完整路径)7" title="图片地址7"/></td>
								<td><input type="text" name="TOURL7" id="TOURL7" value="${pd.TOURL7}" maxlength="200" placeholder="这里输入超链接7" title="超链接7"/></td>
							</tr>
							<tr>
								<td><input type="text" name="TITLE8" id="TITLE8" value="${pd.TITLE8}" maxlength="200" placeholder="这里输入标题8" title="标题8"/></td>
								<td><input type="text" name="DESCRIPTION8" id="DESCRIPTION8" value="${pd.DESCRIPTION8}" maxlength="200" placeholder="这里输入描述8" title="描述8"/></td>
								<td><input type="text" name="IMGURL8" id="IMGURL8" value="${pd.IMGURL8}" maxlength="200" placeholder="这里输入图片地址(完整路径)8" title="图片地址8"/></td>
								<td><input type="text" name="TOURL8" id="TOURL8" value="${pd.TOURL8}" maxlength="200" placeholder="这里输入超链接8" title="超链接8"/></td>
							</tr>
						</table>
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">状态:</td>
								<td>
									<label style="float:left;padding-left: 12px;"><input class="ace" name="form-field-radio" id="form-field-radio1" onclick="setType('1');" <c:if test="${pd.STATUS == '1' }">checked="checked"</c:if> type="radio" value="icon-edit"><span class="lbl">有效</span></label>
									<label style="float:left;padding-left: 5px;"><input class="ace" name="form-field-radio" id="form-field-radio2" onclick="setType('2');" <c:if test="${pd.STATUS == '2' }">checked="checked"</c:if> type="radio" value="icon-edit"><span class="lbl">无效</span></label>
								</td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>	
</div>
<!-- 页面底部js¨ -->
<%@ include file="../../system/index/foot.jsp"%>
<!-- ace scripts -->
<script src="static/ace/js/ace/ace.js"></script>
<!-- ace scripts -->
<script src="static/ace/js/ace/ace.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>	
	<script type="text/javascript">
	$(top.hangge());
	</script>
</body>
</html>