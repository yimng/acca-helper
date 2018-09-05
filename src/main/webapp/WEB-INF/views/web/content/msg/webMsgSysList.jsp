<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息推送</title>
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
		<li class="active"><a href="${ctx}/sysmsg/webMsgSys/">消息推送</a></li>
		<shiro:hasPermission name="sysmsg:webMsgSys:edit"><li><a href="${ctx}/sysmsg/webMsgSys/form">添加推送</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="webMsgSys" action="${ctx}/sysmsg/webMsgSys/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>消息内容：</label>
				<form:input path="msgContent" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>消息内容</th>
				<th>消息标题</th>
				<th>推送人群</th>
				<th>推送时间</th>
				<shiro:hasPermission name="sysmsg:webMsgSys:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webMsgSys">
			<tr>
				<td>
					${webMsgSys.msgContent}
				</td>
				<td>
					${webMsgSys.msgTitle}
				</td>
				<td>
					<c:if test="${webMsgSys.pushPeople == 0}">
						所有人
					</c:if>
					<c:if test="${webMsgSys.pushPeople == 1}">
						报考${webMsgSys.courseName}
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${webMsgSys.pushTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sysmsg:webMsgSys:edit"><td>
    				<a href="${ctx}/sysmsg/webMsgSys/form?msgId=${webMsgSys.msgId}">查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>