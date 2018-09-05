<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
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
		<li class="active"><a href="javascript:void(0);">待审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="loanUser" action="${ctx}/web/loanUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>手机号：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>申请时间</th>
				<th>用户状态</th>
				<shiro:hasPermission name="web:loanUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" varStatus="v" var="loanUser">
			<tr>
				<td>${v.index+1}</td>
				<td>
					${loanUser.name}
				</td>
				<td>
					${loanUser.mobile}
				</td>
				<td>
					<fmt:formatDate value="${loanUser.loanDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${loanUser.blacklistStatusStr}
				</td>
				<shiro:hasPermission name="web:loanUser:edit"><td>
    				<a href="${ctx}/web/loanUser/startRev?loanUserId=${loanUser.loanUserId}&loanApplyId=${loanUser.loanApplyId}&flag=1">开始审核</a>
					<%-- <a href="${ctx}/loanuser/loanUser/delete?id=${loanUser.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>