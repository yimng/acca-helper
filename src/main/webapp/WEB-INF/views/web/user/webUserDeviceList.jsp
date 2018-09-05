<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>AppMobileDeviceContact管理</title>
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
	<style>
		.mytable th{
			width:20%;
		}
	</style>
</head>
<body>
<ul class="nav nav-tabs">
		<li ><a href="${ctx}/user/webAccaUser/detail?id=${mobileDeviceContact.accaUserId}">用户信息</a></li>
		<li class="active"><a href="${ctx}/user/mobileDeviceContact/userDeviceList?deviceId=${mobileDeviceContact.deviceId}&accaUserId=${mobileDeviceContact.accaUserId}">设备信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mobileDeviceContact" action="${ctx}/common/mobileDeviceContact/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	
	<h5>手机信息</h5>
	<table id="contentTable" class="table mytable table-bordered table-condensed">
		<thead>
			
		</thead>
		<tbody>
			<tr>
				<th>机型</th>
				<td>${webMobileDevice.agentModel}</td>
				<th>操作系统</th>
				<td>${webMobileDevice.mobileOs}</td>
			</tr>
			<tr>
				<th>当前手机号</th>
				<td>${webMobileDevice.phone}</td>
				<td colspan="2"></td>
			</tr>
		</tbody>
	</table>
	
	<h5>通讯录信息</h5>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>序号</th> -->
				<th>联系人</th>
				<th>联系电话</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mobileDeviceContact">
			<tr>
				<%-- <td>${mobileDeviceContact.id}</td> --%>
				<td>${mobileDeviceContact.contactName}</td>
				<td>${mobileDeviceContact.contactPhone}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>