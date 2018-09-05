<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>activity设置管理</title>
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
		<li class="active"><a href="${ctx}/activity/list?status=0">活动管理</a></li>
		<shiro:hasPermission name="activity:activity:edit"><li><a href="${ctx}/activity/form">添加活动</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="activity" action="${ctx}/activity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="nav nav-tabs">
			<li <c:if test="${activity.status == 0}">class="active"</c:if>><a href="${ctx}/activity/list?status=0"><span>正在进行</span></a></li>
			<li <c:if test="${activity.status == 2}">class="active"</c:if>><a href="${ctx}/activity/list?status=2"><span>即将开始</span></a></li>
			<li <c:if test="${activity.status == 1}">class="active"</c:if>><a href="${ctx}/activity/list?status=1"><span>已结束</span></a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>类型</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="activity:activity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="activity" varStatus="index">
			<tr>
				<td>
					${activity.activiyName}
				</td>
				<td>
					<c:if test="${activity.type == 1}">
						抽奖
					</c:if>
					<c:if test="${activity.type == 2}">
						扫码领课
					</c:if>
					<c:if test="${activity.type == 3}">
						推荐送积分
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${activity.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${activity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="activity:activity:edit"><td>
    				<a href="${ctx}/activity/form?activityId=${activity.activityId}">修改</a>
					<a href="${ctx}/activity/delete?activityId=${activity.activityId}" onclick="return confirmx('确认要删除该activity设置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>