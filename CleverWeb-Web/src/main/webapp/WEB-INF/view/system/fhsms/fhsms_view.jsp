<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="padding-top: 13px;">
								<c:if test="${pd.TYPE != '2' }">
								发信人：${pd.TO_USERNAME}&nbsp;&nbsp;
								收信人：${pd.FROM_USERNAME}&nbsp;&nbsp;
								</c:if>
								<c:if test="${pd.TYPE == '2' }">
								发信人：${pd.FROM_USERNAME}&nbsp;&nbsp;
								收信人：${pd.TO_USERNAME}&nbsp;&nbsp;
								</c:if>
								发信时间：${pd.SEND_TIME}
							</tr>
							<tr>
								<td>${pd.CONTENT}</td>
							</tr>
						</table>
						</div>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#CONTENT").val()==""){
				$("#CONTENT").tips({
					side:3,
		            msg:'请输入内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTENT").focus();
			return false;
			}
			if($("#TO_USERNAME").val()==""){
				$("#TO_USERNAME").tips({
					side:3,
		            msg:'请输入收信人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TO_USERNAME").focus();
			return false;
			}
			if($("#FROM_USERNAME").val()==""){
				$("#FROM_USERNAME").tips({
					side:3,
		            msg:'请输入发信人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#FROM_USERNAME").focus();
			return false;
			}
			if($("#SEND_TIME").val()==""){
				$("#SEND_TIME").tips({
					side:3,
		            msg:'请输入发信时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SEND_TIME").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>