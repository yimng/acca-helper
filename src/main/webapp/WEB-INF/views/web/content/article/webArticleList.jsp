<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章列表</title>
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
		<li class="active"><a href="${ctx}/article/list?type=1">文章列表</a></li>
		<shiro:hasPermission name="article:webArticle:edit"><li><a href="${ctx}/article/form?type=1">文章添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="webArticle" action="${ctx}/article/" method="post" class="breadcrumb form-search">
		<input id="type" name="type" type="hidden" value="1"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文章标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>所属分类：</label>
				<form:select path="articleCategoryId" htmlEscape="false" maxlength="200" class="input-medium">
					<form:option value="" label="全部分类"/>
					<form:options items="${categoryList}" itemLabel="name" itemValue="articleCategoryId" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>排序</th>
				<th>图片</th>
				<th>标题</th>
				<th>所属分类</th>
				<th>关联科目</th>
				<th>是否发布</th>
				<th>创建时间</th>
				<shiro:hasPermission name="article:webArticle:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="coupon" varStatus="index">
			<tr>
				<td>
					${coupon.sortNum}
				</td>
				<td>
					<sys:imageupload input="htmlImageId${index.index}" show="true" numbers="1" initData="${coupon.htmlImageStr}" imgWidth="200" imgHeight="100"/>
				</td>
				<td>
					${coupon.title}
				</td>
				<td>
					${coupon.categoryName}
				</td>
				<td>
					${coupon.relationCourse}
				</td>
				<td>
					<c:if test="${coupon.publish == 1}">
						是
					</c:if>
					<c:if test="${coupon.publish == 2}">
						否
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="article:webArticle:edit"><td>
					<a href="${ctxapi}/article/detail?articleId=${coupon.articleId}">查看</a>
    				<a href="${ctx}/article/form?type=1&articleId=${coupon.articleId}">编辑</a>
					<a href="${ctx}/article/delete?type=1&articleId=${coupon.articleId}" onclick="return confirmx('确认要删除该文章吗？', this.href)">删除</a>
					
					<a href="${ctx}/content/webArticleComment?articleId=${coupon.articleId}&type=${coupon.type}">查看评论</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>