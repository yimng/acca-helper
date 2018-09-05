<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
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
		<li class="active"><a href="${ctx}/content/webArticleComment/">评论列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="webArticleComment" action="${ctx}/content/webArticleComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="articleId" />
		<form:hidden path="type" />
		<ul class="ul-form">
			<li><label>评论内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文章标题</th>
				<th>评论人昵称</th>
				<th>评论人手机号</th>
				<th style="width:50%">评论内容</th>
				<%-- <shiro:hasPermission name="content:webArticleComment:edit"><th>操作</th></shiro:hasPermission> --%>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webArticleComment">
			<tr>
				<td>
					${webArticleComment.articleTitle}
				</td>
				<td>
					${webArticleComment.user.nickname}
				</td>
				<td>
					${webArticleComment.user.phone}
				</td>
				<td>
					${webArticleComment.content}
				</td>
				<td>
					<a href="${ctx}/content/webArticleComment/delete?articleCommentId=${webArticleComment.articleCommentId}&type=${webArticleComment.type}" onclick="return confirmx('确认要删除该条评论吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<div class="form-actions">
		<a class="btn" href="${ctx}/article?type=${webArticleComment.type}">返回</a>
		
	</div>
	
</body>
</html>