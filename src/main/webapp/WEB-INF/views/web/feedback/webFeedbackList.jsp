<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebFeedback管理</title>
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
	<li class="active"><a href="${ctx}/user/webFeedback/">反馈列表</a></li>
	<%-- <shiro:hasPermission name="user:webFeedback:edit"><li><a href="${ctx}/user/webFeedback/form">WebFeedback添加</a></li></shiro:hasPermission> --%>
</ul>
<form:form id="searchForm" modelAttribute="webFeedback" action="${ctx}/user/webFeedback/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">
		<li><label>反馈内容：</label>
			<form:input path="content" htmlEscape="false" maxlength="512" class="input-medium"/>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>意见</th>
		<th>图片</th>
		<th width="15%">手机号</th>
		<th>类型</th>
		<th>分类</th>
		<th width="15%">时间</th>
		<%-- <shiro:hasPermission name="user:webFeedback:edit"><th>操作</th></shiro:hasPermission> --%>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" varStatus="index" var="webFeedback">
		<tr>
			<td>${webFeedback.content}</td>
			<td><sys:imageupload input="image${index.index}" show="true" numbers="1" initData="${webFeedback.img}" imgWidth="100" imgHeight="100"/></td>
			<td>${webFeedback.phone}</td>
			<td>${webFeedback.type}</td>
			<td>${webFeedback.category}</td>
			<td>
				<fmt:formatDate value="${webFeedback.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
				<%-- <shiro:hasPermission name="user:webFeedback:edit"><td>
    				<a href="${ctx}/user/webFeedback/form?id=${webFeedback.id}">修改</a>
					<a href="${ctx}/user/webFeedback/delete?id=${webFeedback.id}" onclick="return confirmx('确认要删除该WebFeedback吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>