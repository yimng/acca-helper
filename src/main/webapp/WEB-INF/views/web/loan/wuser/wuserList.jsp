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
	<form:form id="searchForm" modelAttribute="loanUser" action="${ctx}/web/wuser/" method="post" class="breadcrumb form-search">
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
				<th>最后登录时间</th>
				<shiro:hasPermission name="web:wuser:edit"><th>操作</th></shiro:hasPermission>
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
					<fmt:formatDate value="${loanUser.loginDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<shiro:hasPermission name="web:wuser:edit"><td>
    				<a href="${ctx}/web/loanUser/idCardRev?loanUserId=${loanUser.loanUserId}&loanApplyId=${loanUser.loanApplyId}&flag=3">查看</a>
    				<c:if test="${loanUser.userStatus == 1}">
    					<a href="${ctx}/web/wuser/freezeUser?loanUserId=${loanUser.loanUserId}&userStatus=${loanUser.userStatus}" onclick="return confirmx('确认要解禁吗？', this.href)">
    					<span style="color: red;">解禁</span></a>
    				</c:if>
    				<c:if test="${loanUser.userStatus != 1}">
    					<a href="${ctx}/web/wuser/freezeUser?loanUserId=${loanUser.loanUserId}" onclick="return confirmx('确认要禁用吗？', this.href)">禁用</a>
    				</c:if>
					<%-- <a href="${ctx}/loanuser/loanUser/delete?id=${loanUser.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>