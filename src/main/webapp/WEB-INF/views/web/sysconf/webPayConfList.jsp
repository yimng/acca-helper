<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收款账户管理管理</title>
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
		<li class="active"><a href="${ctx}/web/webPayConf/">收款账户管理列表</a></li>
		<shiro:hasPermission name="web:webPayConf:edit"><li><a href="${ctx}/web/webPayConf/form">收款账户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="webPayConf" action="${ctx}/web/webPayConf/" method="post" class="breadcrumb form-search">
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
				<th>账户名称</th>
				<th>收款方式名称</th>
				<th>收款方式图片</th>
				<th>收款信息</th>
				<shiro:hasPermission name="web:webPayConf:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webPayConf" varStatus="index">
			<tr>
				<td>${webPayConf.name}</td>
				<td>${webPayConf.payName}</td>
				<td>
					<sys:imageupload input="imageId${index.index}" show="true" numbers="1" initData="${webPayConf.imageStr}" imgWidth="100" imgHeight="100"/>
				</td>
				<td>
					<c:forEach items="${webPayConf.receivePayList}" var="receive">
						<p><span>${receive.key}：</span>
						${receive.value}
						</p>
					</c:forEach>
				</td>
				<shiro:hasPermission name="web:webPayConf:edit"><td>
    				<a href="${ctx}/web/webPayConf/form?id=${webPayConf.id}">修改</a>
					<a href="${ctx}/web/webPayConf/delete?id=${webPayConf.id}" onclick="return confirmx('确认要删除该收款账户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>