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
		<li><a href="${ctx}/web/sys/protocolList">用户协议列表</a></li>
		<li class="active"><a href="javascript:void(0)">用户协议编辑</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="protocol" action="${ctx}/web/sys/editProtocol" method="post" class="form-horizontal">
				<sys:message content="${message}"/>
				<fieldset>
					<legend>
						<c:if test="${protocol.type == 1}">借易贷用户协议<input type="hidden" name="title" value="借易贷用户协议" /></c:if>
						<c:if test="${protocol.type == 2}">手机授权协议<input type="hidden" name="title" value="手机授权协议" /></c:if>
						<c:if test="${protocol.type == 3}">淘宝授权协议<input type="hidden" name="title" value="淘宝授权协议" /></c:if>
						<c:if test="${protocol.type == 4}">京东授权协议<input type="hidden" name="title" value="京东授权协议" /></c:if>
						<c:if test="${protocol.type == 5}">信用卡邮箱授权协议<input type="hidden" name="title" value="信用卡邮箱授权协议" /></c:if>
						<c:if test="${protocol.type == 6}">学信网授权协议<input type="hidden" name="title" value="学信网授权协议" /></c:if>
					</legend>
					<form:hidden path = "protocolId" />
					<form:hidden path = "type" />
					<%-- <input id="registerTime" name="registerTime" type="hidden" value="<fmt:formatDate value="${kindergarten.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /> --%>
					<div class="control-group">
						<label class="control-label">内容:</label>
						<div class="controls">
							<form:textarea path="content" htmlEscape="false" rows="6" maxlength="200" class="input-large required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="form-actions">
						<shiro:hasPermission name="web:protocol:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="发 布"/>&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</fieldset>
			</form:form>
</body>
</html>