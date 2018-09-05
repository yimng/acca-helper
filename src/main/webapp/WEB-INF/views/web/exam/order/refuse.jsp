<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核不通过</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')},
					checkReason:{required: true, maxlength : 50},
					totalSeats:{
						digits : true,
						max : 100000000
					}
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
		<li class="active"><a href="javascript:void(0)">审核不通过</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="order" action="${ctx}/web/order/audit" class="form-horizontal">
		<form:hidden path="orderId"/>
		<sys:message content="${message}"/>
		<fieldset>
			<legend>请填写审核不通过的原因</legend>
			<div class="control-group">
				<label class="control-label">详细原因:</label>
				<div class="controls">
					<form:textarea path="checkReason" htmlEscape="false" rows="6" maxlength="200" class="input-large" />
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</fieldset>
	</form:form>
</body>
</html>