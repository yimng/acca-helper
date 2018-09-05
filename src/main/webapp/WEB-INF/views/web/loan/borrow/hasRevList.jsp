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
		<li class="active"><a href="javascript:void(0);">已审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="loanUser" action="${ctx}/web/loanUser/hasRevList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>手机号：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>审核状态：</label>
				<form:select path="loanStatus" class="input-medium">
					<form:option value="-1" label="请选择"/>
					<form:option value="3" label="已通过"/>
					<form:option value="4" label="已拒绝"/>
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
				<th>序号</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>审核时间</th>
				<th>状态</th>
				<shiro:hasPermission name="web:hasRevUser:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" varStatus="v" var="loanUser">
			<tr>
				<td>${v.index + 1}</td>
				<td>
					${loanUser.name}
				</td>
				<td>
					${loanUser.mobile}
				</td>
				<td>
					<fmt:formatDate value="${loanUser.updateDateApply}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${loanUser.loanStatusInfo}
				</td>
				<shiro:hasPermission name="web:hasRevUser:view"><td>
    				<a href="${ctx}/web/loanUser/hasRevDetail?loanUserId=${loanUser.loanUserId}&loanApplyId=${loanUser.loanApplyId}&flag=2">查看</a>
					<%-- <a href="${ctx}/loanuser/loanUser/delete?id=${loanUser.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>