<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebApkVersion管理</title>
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
		<li class="active"><a href="${ctx}/web/webApkVersion/">APP版本升级</a></li>
		<%-- <shiro:hasPermission name="web:webApkVersion:edit"><li><a href="${ctx}/web/webApkVersion/form">WebApkVersion添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="webApkVersion" action="${ctx}/web/webApkVersion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>APP名称</th>
				<th>版本号</th>
				<th>VersionCode</th>
				<th>更新说明</th>
				<th>APP下载地址</th>
				<shiro:hasPermission name="web:webApkVersion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webApkVersion">
			<tr>
				<td>ACCA Helper</td>
				<td>${webApkVersion.version}</td>
				<td>${webApkVersion.buildNumber}</td>
				<td>${webApkVersion.description}</td>
				<td>${webApkVersion.image.fileUrl}</td>
				<shiro:hasPermission name="web:webApkVersion:edit"><td>
    				<a href="${ctx}/web/webApkVersion/form?id=${webApkVersion.id}">编辑</a>
					<%-- <a href="${ctx}/web/webApkVersion/delete?id=${webApkVersion.id}" onclick="return confirmx('确认要删除该WebApkVersion吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>