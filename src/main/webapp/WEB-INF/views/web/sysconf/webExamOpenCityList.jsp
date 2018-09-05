<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>城市管理管理</title>
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
	<style type="text/css">
		.yes{
			color:green;
		}
		.no{
			color:red;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/web/webExamOpenCity/">城市列表</a></li>
		<shiro:hasPermission name="web:webExamOpenCity:edit"><li><a href="${ctx}/web/webExamOpenCity/form">城市添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="webExamOpenCity" action="${ctx}/web/webExamOpenCity/" method="post" class="breadcrumb form-search">
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
				<th>城市</th>
				<th>自有机考中心</th>
				<th>官方机考中心</th>
				<th>官方笔考中心</th>
				<th>中博诚通分部</th>
				<shiro:hasPermission name="web:webExamOpenCity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webExamOpenCity">
			<tr>
				<td>${webExamOpenCity.cityName}</td>
				<td>
					<c:if test="${webExamOpenCity.flag1}"><span class="yes">是</span></c:if>
					<c:if test="${!webExamOpenCity.flag1}"><span class="no">否</span></c:if>
				</td>
				<td>
					<c:if test="${webExamOpenCity.flag2}"><span class="yes">是</span></c:if>
					<c:if test="${!webExamOpenCity.flag2}"><span class="no">否</span></c:if>
				</td>
				<td>
					<c:if test="${webExamOpenCity.flag3}"><span class="yes">是</span></c:if>
					<c:if test="${!webExamOpenCity.flag3}"><span class="no">否</span></c:if>
				</td>
				<td>
					<c:if test="${webExamOpenCity.flag4}"><span class="yes">是</span></c:if>
					<c:if test="${!webExamOpenCity.flag4}"><span class="no">否</span></c:if>
				</td>
				
				<shiro:hasPermission name="web:webExamOpenCity:edit"><td>
    				<a href="${ctx}/web/webExamOpenCity/form?id=${webExamOpenCity.id}">编辑</a>
					<a href="${ctx}/web/webExamOpenCity/delete?id=${webExamOpenCity.id}" onclick="return confirmx('确认要删除该城市管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>