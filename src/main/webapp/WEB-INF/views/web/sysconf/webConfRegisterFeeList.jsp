<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>注册用户费用管理管理</title>
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
		<li class="active"><a href="${ctx}/web/webConfRegisterFee/">注册费用管理列表</a></li>
		<%-- <shiro:hasPermission name="web:webConfRegisterFee:edit"><li><a href="${ctx}/web/webConfRegisterFee/form">注册用户费用管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="webConfRegisterFee" action="${ctx}/web/webConfRegisterFee/" method="post" class="breadcrumb form-search">
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
			<th>注册类别</th>
				<th>注册费用（元）</th>
				<shiro:hasPermission name="web:webConfRegisterFee:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webConfRegisterFee">
			<tr>
				<td>
					<c:if test="${webConfRegisterFee.type == 1}">ACCA</c:if>
					<c:if test="${webConfRegisterFee.type == 2}">FIA</c:if>
				</td>
				<td>
					${webConfRegisterFee.amount}
				</td>
				<shiro:hasPermission name="web:webConfRegisterFee:edit"><td>
    				<a href="${ctx}/web/webConfRegisterFee/form?id=${webConfRegisterFee.id}">编辑</a>
					<%-- <a href="${ctx}/web/webConfRegisterFee/delete?id=${webConfRegisterFee.id}" onclick="return confirmx('确认要删除该注册用户费用管理吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>