<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考点类管理</title>
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
		<li class="active"><a href="javascript:void(0);">考点列表</a></li>
		<shiro:hasPermission name="web:place:edit"><li><a href="${ctx}/web/place/form?menuExamType=${webExamPlace.menuExamType}">考点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="webExamPlace" action="${ctx}/web/place/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="examType"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考点城市名称</th>
				<th>详细地址</th>
				<th>考点名称</th>
				<th>考点编号</th>
				<th>经纬度</th>
				<th>联系人</th>
				<th>联系电话</th>
				<shiro:hasPermission name="web:place:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="p">
			<tr>
				<td>
					${p.examCityName}
				</td>
				<td>
					${p.examDetailAddress}
				</td>
				<td>
					${p.examPlaceName}
				</td>
				<td>
					${p.examPlaceSn}
				</td>
				<td>
					${p.lng},${p.lat}
				</td>
				<td>
					${p.examPlaceContantName}
				</td>
				<td>
					${p.examPlaceContantPhone}
				</td>
				<shiro:hasPermission name="web:place:edit"><td>
    				<a href="${ctx}/web/place/form?examPlaceId=${p.examPlaceId}&menuExamType=${webExamPlace.menuExamType}">修改</a>
					<a href="${ctx}/web/place/delete?examPlaceId=${p.examPlaceId}&menuExamType=${webExamPlace.menuExamType}" onclick="return confirmx('确认要删除该考点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>