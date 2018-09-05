<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>banner设置管理</title>
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
		<li class="active"><a href="${ctx}/banner/list?status=0">Banner管理</a></li>
		<shiro:hasPermission name="banner:banner:edit"><li><a href="${ctx}/banner/form">添加Banner</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="banner" action="${ctx}/banner/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="nav nav-tabs">
			<li <c:if test="${banner.status == 0}">class="active"</c:if>><a href="${ctx}/banner/list?status=0"><span>正在显示</span></a></li>
			<li <c:if test="${banner.status == 2}">class="active"</c:if>><a href="${ctx}/banner/list?status=2"><span>即将显示</span></a></li>
			<li <c:if test="${banner.status == 1}">class="active"</c:if>><a href="${ctx}/banner/list?status=1"><span>已结束</span></a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>排序</th>
				<th>图片</th>
				<th>名称</th>
				<th>类型</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="banner:banner:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="banner" varStatus="index">
			<tr>
				<td>
					${banner.sort}
				</td>
				<td>
					<sys:imageupload input="imgId${index.index}" show="true" numbers="1" initData="${banner.imgStr}" imgWidth="200" imgHeight="100"/>
				</td>
				<td>
					${banner.title}
				</td>
				<td>
					<c:if test="${banner.type == 1}">
						链接
					</c:if>
					<c:if test="${banner.type == 2}">
						富文本
					</c:if>
					<c:if test="${banner.type == 3}">
						有资有料
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${banner.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${banner.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="banner:banner:edit"><td>
    				<a href="${ctx}/banner/form?bannerId=${banner.bannerId}">修改</a>
					<a href="${ctx}/banner/delete?bannerId=${banner.bannerId}" onclick="return confirmx('确认要删除该banner设置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>