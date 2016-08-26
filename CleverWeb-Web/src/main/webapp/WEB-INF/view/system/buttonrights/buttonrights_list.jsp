<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<%@ include file="../index/top.jsp"%>
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
							<table style="margin-top: 8px;">
								<tr height="35">
									<td style="width:50px;">角色组:</td>
										<c:choose>
										<c:when test="${not empty menuRoleList}">
										<!-- 角色组循环 -->
										<c:forEach items="${menuRoleList}" var="menuRole" varStatus="vs">
											<td style="width:100px;" class="center" <c:choose><c:when test="${sysRole.roleId == menuRole.roleId}">bgcolor="#FFC926" onMouseOut="javascript:this.bgColor='#FFC926';"</c:when><c:otherwise>bgcolor="#E5E5E5" onMouseOut="javascript:this.bgColor='#E5E5E5';"</c:otherwise></c:choose>  onMouseMove="javascript:this.bgColor='#FFC926';" >
												<a href="buttonrights/list.do?roleId=${menuRole.roleId }" style="text-decoration:none; display:block;"><i class="menu-icon fa fa-users"></i><font color="#666666">${menuRole.roleName }</font></a>
											</td>
											<td style="width:5px;"></td>
										</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
											<td colspan="100">没有相关数据</td>
											</tr>
										</c:otherwise>
										</c:choose>
									<td></td>
								</tr>
							</table>
							<table id="dynamic-table" class="table table-striped table-bordered table-hover" style="margin-top:7px;">
								<thead>
								<tr>
									<th class="center" style="width: 50px;">序号</th>
									<th class='center'>角色</th>
									<c:if test="${QX.edit == 1 }">
										<!-- 按钮循环 -->
										<c:forEach items="${sysButtonList}" var="button" varStatus="vsb">
											<th class='center'>${button.buttonName }</th>
										</c:forEach>
									</c:if>
								</tr>
								</thead>
								<c:choose>
									<c:when test="${not empty roleList}">
										<c:if test="${QX.cha == 1 }">
										<!-- 角色循环 -->
										<c:forEach items="${roleList}" var="role" varStatus="vs">
										<tr>
										<td class='center' style="width:30px;">${vs.index+1}</td>
										<td class='center' id="ROLE_NAMETd${role.roleId }">${role.roleName }</td>
										<c:if test="${QX.edit == 1 }">
											<!-- 按钮循环 -->
											<c:forEach items="${sysButtonList}" var="button" varStatus="vsb">
												<!-- 关联表循环 -->
												<c:forEach items="${sysRoleButtonList}" var="roleButton" varStatus="vsRb">
													<c:if test="${role.roleId == roleButton.roleId && button.buttonId == roleButton.buttonId }">
														<c:set value="1" var="rbvalue"></c:set>
													</c:if>
												</c:forEach>
												<td class='center' style="height: 20px;">
													<label>
														<input name="switch-field-1" onclick="upRb('${role.roleId}','${button.buttonId}')" class="ace ace-switch ace-switch-3" type="checkbox" <c:if test="${rbvalue == 1 }">checked="checked"</c:if> >
														<span class="lbl"></span>
													</label>
												</td>
												<c:set value="0" var="rbvalue"></c:set>
											</c:forEach>
										</c:if>
										</tr>
										</c:forEach>
										</c:if>
										<c:if test="${QX.cha == 0 }">
											<tr>
												<td colspan="100" class="center">您无权查看</td>
											</tr>
										</c:if>
									</c:when>
									<c:otherwise>
										<tr>
										<td colspan="100" class="center" >没有相关数据</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</table>
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
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(top.hangge());
		
		//处理按钮点击
		function upRb(roleId,buttonId){
			$.ajax({
				type: "POST",
				url: "<%=basePath%>buttonrights/upRb.do?roleId="+roleId+"&buttonId="+buttonId+"&guid="+new Date().getTime(),
		    	data: encodeURI(""),
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
				}
			});
		}
	</script>

</body>
</html>