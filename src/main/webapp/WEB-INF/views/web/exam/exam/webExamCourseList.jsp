<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试科目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var len = $(".examTypes").length;
			for(var i = 0;i < len;i++){
				var courseType = $("#examCourse_"+ i).text().trim();
				courseType = courseType.substring(0,courseType.length-1);
				$("#examCourse_"+ i).text(courseType);
			}
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
		<li class="active"><a href="${ctx}/web/webExamCourse/">考试科目列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="webExamCourse" action="${ctx}/web/webExamCourse/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>科目</th>
				<th>名称</th>
				<th>报名费(元)</th>
				<th>考试类型</th>
				<shiro:hasPermission name="web:webExamCourse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webExamCourse" varStatus="index">
			<tr class="examTypes">
				<td>
					${webExamCourse.course}
				</td>
				<td>
					${webExamCourse.courseName}
				</td>
				<td>
					${webExamCourse.price}
				</td>
				<td id="examCourse_${index.index}">
					<c:if test="${webExamCourse.flag1}">自有机考/</c:if>
					<c:if test="${webExamCourse.flag2}">官方机考/</c:if>
					<c:if test="${webExamCourse.flag3}">官方笔考/</c:if>
				</td>
				<shiro:hasPermission name="web:webExamCourse:edit"><td>
    				<a href="${ctx}/web/webExamCourse/form?id=${webExamCourse.examCourseId}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>