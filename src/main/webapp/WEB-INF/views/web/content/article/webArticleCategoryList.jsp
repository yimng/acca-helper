<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章分类管理</title>
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
		<li class="active"><a href="${ctx}/webArticleCategory/">文章分类列表</a></li>
		<shiro:hasPermission name="articlecategory:webArticleCategory:edit"><li><a href="${ctx}/webArticleCategory/form">添加分类</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>排序</th>
				<th>名称</th>
				<shiro:hasPermission name="articlecategory:webArticleCategory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webArticleCategory">
			<tr>
				<td>${webArticleCategory.sortNum}</td>
				<td>
					${webArticleCategory.name}
				</td>
				<shiro:hasPermission name="articlecategory:webArticleCategory:edit"><td>
    				<a href="${ctx}/webArticleCategory/form?id=${webArticleCategory.articleCategoryId}">修改</a>
					<%-- 不是系统的数据,才可以删除,否则不让删.并且删除的时候,需要判断分类下是否有文章,如果有,也不让删除 --%>
					<c:if test="${webArticleCategory.sysData == 1}">
						<a href="${ctx}/webArticleCategory/delete?id=${webArticleCategory.articleCategoryId}" onclick="return confirmx('确认要删除该文章分类吗？', this.href)">删除</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>