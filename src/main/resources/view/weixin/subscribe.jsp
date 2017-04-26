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
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../system/index/top.jsp"%>

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
		
							<div class="widget-box">
								<div class="widget-header widget-header-blue widget-header-flat wi1dget-header-large">
									<h4 class="lighter">关注回复</h4>
								</div>
								<div class="widget-body">
								 <div class="widget-main">
								 
								 <div class="step-content row-fluid position-relative">
													<div class="widget-box" style="width:50%;">
														<div class="widget-header widget-header-flat widget-header-small">
															<h5><i class="icon-credit-card"></i> 
																回复类型
															</h5>
															<div class="widget-toolbar no-border">
															</div>
														</div>
														<div class="widget-body">
														 <div class="widget-main">
														 	${msg}
														 </div><!--/widget-main-->
														</div><!--/widget-body-->
													</div><!--/widget-box-->
													<div class="widget-box" style="width:50%;">
														<div class="widget-header widget-header-flat widget-header-small">
															<h5><i class="icon-credit-card"></i> 
																回复内容
															</h5>
															<div class="widget-toolbar no-border">
															</div>
														</div>
														<div class="widget-body">
														 <div class="widget-main">
														 	${content}
														 </div><!--/widget-main-->
														</div><!--/widget-body-->
													</div><!--/widget-box-->
												 &nbsp;
												 <div class="step-content row-fluid position-relative">
														<ul class="unstyled spaced2">
															<li class="text-warning green"><i class="icon-star blue"></i>
																在文本回复、图文回复、应用命令中
															</li>
															<li class="text-warning green"><i class="icon-star blue"></i>
																添加一个关键词为"关注"的记录
															</li>
															<li class="text-warning green"><i class="icon-star blue"></i>
																即为关注回复内容
															</li>
														</ul>
												</div>
											
								</div>
								 <div class="step-content row-fluid position-relative">
									 <input type="hidden" value="no" id="hasTp1" />
								 </div> 
								 
								 
								 
								 
								 </div><!--/widget-main-->
								</div><!--/widget-body-->
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

	<!-- 返回顶部 -->
	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../system/index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
	$(top.hangge());
	</script>
</body>
</html>

