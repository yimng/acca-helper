<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>常见问题添加或修改</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate({
			rules: {
				loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
			},
			messages: {
				loginName: {remote: "用户登录名已存在"},
				confirmNewPassword: {equalTo: "输入与上面相同的密码"}
			},
			submitHandler: function(form){
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/sys/findQuestionPage">常见问题列表</a></li>
		<li class="active"><a href="javascript:void(0)">常见问题<c:if test="${empty question.qaId}">添加</c:if><c:if test="${!empty question.qaId}">修改</c:if></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="question" action="${ctx}/web/sys/editQuestion" method="post" class="form-horizontal">
				<sys:message content="${message}"/>
				<fieldset>
					<form:hidden path = "qaId" />
					<%-- <input id="registerTime" name="registerTime" type="hidden" value="<fmt:formatDate value="${kindergarten.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /> --%>
					<div class="control-group">
						<label class="control-label">标题:</label>
						<div class="controls">
							<form:input path="qaTitle" htmlEscape="false" maxlength="50" class="required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">内容:</label>
						<div class="controls">
							<form:textarea path="qaContent" htmlEscape="false" rows="6" maxlength="200" class="input-large required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">顺序：</label>
						<div class="controls">
							<form:input path="sort" htmlEscape="false" maxlength="50" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="form-actions">
						<shiro:hasPermission name="web:msgSys:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="发 布"/>&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</fieldset>
			</form:form>
</body>
</html>