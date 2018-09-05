<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>accaUser模块管理</title>
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
		<li class="active"><a href="${ctx}/user/webAccaUser/">注册用户列表</a></li>
		<%-- <shiro:hasPermission name="user:webAccaUser:edit"><li><a href="${ctx}/user/webAccaUser/form">accaUser模块添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="webAccaUser" action="${ctx}/user/webAccaUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>考试科目：</label>
				<form:select path="examCourseId" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${courseList}" itemLabel="course" itemValue="examCourseId" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>用户身份：</label>
				<form:select path="identityType" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('acca_identity_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>用户类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('acca_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>省份：</label>
				<form:input path="provinceName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>城市：</label>
				<form:input path="cityName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>昵称</th>
				<th>手机号</th>
				<th>GPS定位城市</th>
				<th>当前考试科目</th>
				<th>用户身份</th>
				<th>用户类型</th>
				<th>创建时间</th>
				<th>状态</th>
				<shiro:hasPermission name="user:webAccaUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webAccaUser">
			<tr>
			<td>${webAccaUser.nickname}</td>
			<td>${webAccaUser.phone}</td>
			<td>${webAccaUser.provinceName}${webAccaUser.cityName}</td>
			<td>${webAccaUser.course}</td>
			
			
			<td>
				<c:if test="${webAccaUser.identityType == 1}">在校生</c:if>
				<c:if test="${webAccaUser.identityType == 2}">毕业生</c:if>
				<c:if test="${webAccaUser.identityType == 3}">国外院校毕业生</c:if>
			</td>
			<td>
				<c:if test="${webAccaUser.type == 1}">普通用户</c:if>
				<c:if test="${webAccaUser.type == 2}">学习达人</c:if>
				<c:if test="${webAccaUser.type == 3}">名师</c:if>
			</td>
			<td>
				<fmt:formatDate value="${webAccaUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
				<c:if test="${webAccaUser.userStatus ==1}"><font color="green">正常</font></c:if>
				<c:if test="${webAccaUser.userStatus ==2}"><font color="red">冻结</font></c:if>
			</td>
				<shiro:hasPermission name="user:webAccaUser:edit"><td>
					<a href="${ctx}/user/webAccaUser/detail?id=${webAccaUser.accaUserId}">查看</a>
    				<a href="${ctx}/user/webAccaUser/form?id=${webAccaUser.accaUserId}">编辑</a>
					<c:if test="${webAccaUser.userStatus ==1}"><a href="${ctx}/user/webAccaUser/freeze?id=${webAccaUser.accaUserId}&userStatus=${webAccaUser.userStatus}" onclick="return confirmx('确认要禁用该用户吗？', this.href)">禁用</a></c:if>
					<c:if test="${webAccaUser.userStatus ==2}"><a href="${ctx}/user/webAccaUser/freeze?id=${webAccaUser.accaUserId}&userStatus=${webAccaUser.userStatus}" onclick="return confirmx('确认要恢复该用户吗？', this.href)">恢复</a></c:if>
					<a href="${ctx}/user/webAccaUser/forward?id=${webAccaUser.accaUserId}">修改ACCA信息</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>