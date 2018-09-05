<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>accaUser模块管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
		<li><a href="${ctx}/user/webAccaUser/">注册用户列表列表</a></li>
		<li class="active"><a href="${ctx}/user/webAccaUser/forward?id=${webAccaUser.id}">修改ACCA信息<shiro:hasPermission name="user:webAccaUser:edit">${not empty webAccaUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:webAccaUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webAccaUser" action="${ctx}/user/webAccaUser/updateRegister" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="64" readOnly="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="registerName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ACCA注册手机号：</label>
			<div class="controls">
				<form:input path="registerPhone" htmlEscape="false" maxlength="32" readOnly="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ACCA注册邮箱：</label>
			<div class="controls">
				<form:input path="registerEmail" htmlEscape="false" maxlength="32" readOnly="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ACCA学员号：</label>
			<div class="controls">
				<form:input path="accaRegisterName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证件类型：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${webAccaUser.cardType == 2}">
						护照
					</c:when>
					<c:otherwise>
						身份证
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证件号：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${webAccaUser.cardType == 1}">
						<form:input path="registerCardNumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</c:when>
					<c:otherwise>
						<form:input path="registerCardNumber" htmlEscape="false" maxlength="32" readOnly="true" class="input-xlarge "/>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="user:webAccaUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>