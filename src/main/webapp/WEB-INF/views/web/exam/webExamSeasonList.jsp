<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebExamSeason管理</title>
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
		<li class="active"><a href="${ctx}/exam/webExamSeason/">列表</a></li>
		<shiro:hasPermission name="exam:webExamSeason:edit"><li><a href="${ctx}/exam/webExamSeason/form">添加</a></li></shiro:hasPermission>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="webExamSeason" action="${ctx}/exam/webExamSeason/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考季</th>
				<th>考试版本</th>
				<shiro:hasPermission name="exam:webExamSeason:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webExamSeason">
			<tr>
				<td>${webExamSeason.examSeasonStr }</td>
				<td>
					<c:forEach items="${webExamSeason.examCourselist}" var="examCourse">
						${examCourse.examCourse} ：${examCourse.showVersionStr} <br/>
					</c:forEach>
				</td>
				<td>
					<shiro:hasPermission name="exam:webExamSeason:edit">
	    				<a href="${ctx}/exam/webExamSeason/form?examSeasonStr=${webExamSeason.examSeasonStr}">修改</a>
						<a href="${ctx}/exam/webExamSeason/delete?examSeasonStr=${webExamSeason.examSeasonStr}" onclick="return confirmx('确认要删除该选项吗？', this.href)">删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>