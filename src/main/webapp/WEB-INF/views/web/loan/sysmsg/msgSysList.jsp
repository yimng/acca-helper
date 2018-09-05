<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统信息管理</title>
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
	<ul class="nav nav-tabs" style="margin-bottom: 60px;">
		<li class="active"><a href="javascript:void(0)">消息列表</a></li>
		<shiro:hasPermission name="web:msgSys:edit"><li><a href="${ctx}/web/msgSys/form">消息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="msgSys" action="${ctx}/web/msgSys/" method="post" class="breadcrumb form-search">
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
				<th>发布时间</th>
				<th>发布者</th>
				<th>内容</th>
				<th>状态</th>
				<shiro:hasPermission name="web:msgSys:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msgSys">
			<tr>
				<td>
					${msgSys.msgId}
				</td>
				<td>
					<fmt:formatDate value="${msgSys.pushTime}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${msgSys.createUserName}
				</td>
				<td>
					${msgSys.msgContent}
				</td>
				<td>
					${msgSys.pushStatusStr}
				</td>
				<shiro:hasPermission name="web:msgSys:edit"><td>
					<a href="${ctx}/web/msgSys/delete?msgId=${msgSys.msgId}" onclick="return confirmx('确认要删除该系统信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>