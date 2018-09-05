<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebAccaClub模块管理</title>
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
		<li class="active"><a href="${ctx}/web/webAccaClub/">ACCA俱乐部列表</a></li>
		<shiro:hasPermission name="web:webAccaClub:edit"><li><a href="${ctx}/web/webAccaClub/form">添加QQ群</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="webAccaClub" action="${ctx}/web/webAccaClub/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <ul class="ul-form">
			<li><label>qq群名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>QQ群名称</th>
				<th>QQ群号</th>
				<shiro:hasPermission name="web:webAccaClub:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webAccaClub">
			<tr>
				
				<td>
					${webAccaClub.name}
				</td>
				<td>
					${webAccaClub.qqNo}
				</td>
				<shiro:hasPermission name="web:webAccaClub:edit"><td>
    				<a href="${ctx}/web/webAccaClub/form?id=${webAccaClub.accaClubId}">编辑</a>
					<a href="${ctx}/web/webAccaClub/delete?id=${webAccaClub.accaClubId}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>