<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户统计</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${flag == 1}">class="active"</c:if>><a href="${ctx}/web/count?flag=1">总计</a></li>
		<li <c:if test="${flag == 2}">class="active"</c:if>><a href="${ctx}/web/count?flag=2">今日统计</a></li>
		<li <c:if test="${flag == 3}">class="active"</c:if>><a href="${ctx}/web/count?flag=3">本周统计</a></li>
		<li <c:if test="${flag == 4}">class="active"</c:if>><a href="${ctx}/web/count?flag=4">本月统计</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="info" class="form-horizontal">
				<!-- <ul class="ul-form">
					<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
					<li class="clearfix"></li>
				</ul> -->
				<sys:message content="${message}"/>
				<fieldset>
					<legend>用户统计</legend>
					<div class="control-group">
						<label class="control-label">注册用户:</label>
						<div class="controls"><label class="lbl">${countReg}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">申请用户:</label>
						<div class="controls"><label class="lbl">${countApply}</label></div>
					</div>
				</fieldset>
			</form:form>
</body>
</html>