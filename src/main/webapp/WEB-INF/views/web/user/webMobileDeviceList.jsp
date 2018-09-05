<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebMobileDevice管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/user/webMobileDevice/">用户设备列表</a></li>
		<%-- <shiro:hasPermission name="common:webMobileDevice:edit"><li><a href="${ctx}/common/webMobileDevice/form">WebMobileDevice添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="webMobileDevice" action="${ctx}/user/webMobileDevice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机型</th>
				<th>操作系统</th>
				<th>手机号</th>
				<th>通讯录</th>
				<shiro:hasPermission name="user:webMobileDevice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webMobileDevice">
			<tr>
			<td>${webMobileDevice.agentModel}</td>
			<td>${webMobileDevice.mobileOs}</td>
			<td>${webMobileDevice.phone}</td>
				<td>
					<c:if test="${webMobileDevice.authStatus == 1}"><font color="red">未获取</font></c:if>
					<c:if test="${webMobileDevice.authStatus == 2}"><font color="green">已获取</font></c:if>
				</td>
				<shiro:hasPermission name="user:webMobileDevice:edit"><td>
    				<a href="${ctx}/user/mobileDeviceContact/?deviceId=${webMobileDevice.deviceId}">查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>