<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>常见问题列表</title>
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
		<li class="active"><a href="javascript:void(0)">常见问题列表</a></li>
		<shiro:hasPermission name="web:question:edit"><li><a href="${ctx}/web/sys/questionForm">常见问题添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qa" action="${ctx}/web/sys/findQuestionPage" method="post" class="breadcrumb form-search">
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
				<th>序号</th>
				<th>标题</th>
				<th>发布时间</th>
				<shiro:hasPermission name="web:question:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qa">
			<tr>
				<td>
					${qa.qaId}
				</td>
				<td>
					${qa.qaTitle}
				</td>
				<td>
					<fmt:formatDate value="${qa.createDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<shiro:hasPermission name="web:question:edit"><td>
					<a href="${ctx}/web/sys/questionForm?qaId=${qa.qaId}">修改</a>
					<a href="${ctx}/web/sys/delQuestion?qaId=${qa.qaId}" onclick="return confirmx('确认要删除该条记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>