<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>名师指导管理</title>
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
		<li class="active"><a href="${ctx}/teacher/webTeacher/">名师管理</a></li>
		<shiro:hasPermission name="teacher:webTeacher:edit"><li><a href="${ctx}/teacher/webTeacher/form">名师添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="webTeacher" action="${ctx}/teacher/webTeacher/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="chName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>头像</th>
				<th>姓名</th>
				<th>职务</th>
				<th>创建时间</th>
				<shiro:hasPermission name="teacher:webTeacher:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webTeacher" varStatus="index">
			<tr>
				<td>
					<sys:imageupload input="headId${index.index}" show="true" numbers="1" initData="${webTeacher.headStr}"/>
				</td>
				<td>
					${webTeacher.chName}
				</td>
				<td>
					${webTeacher.position}
				</td>
				<td>
					<fmt:formatDate value="${webTeacher.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="teacher:webTeacher:edit"><td>
    				<a href="${ctx}/teacher/webTeacher/form?teacherId=${webTeacher.teacherId}">修改</a>
					<a href="${ctx}/teacher/webTeacher/delete?teacherId=${webTeacher.teacherId}" onclick="return confirmx('确认要删除该名师吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>