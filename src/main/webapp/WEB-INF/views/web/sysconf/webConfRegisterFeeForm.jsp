<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>注册用户费用管理管理</title>
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
		<li><a href="${ctx}/web/webConfRegisterFee/">注册费用管理列表</a></li>
		<li class="active"><a href="${ctx}/web/webConfRegisterFee/form?id=${webConfRegisterFee.id}">注册费用<shiro:hasPermission name="web:webConfRegisterFee:edit">${not empty webConfRegisterFee.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="web:webConfRegisterFee:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webConfRegisterFee" action="${ctx}/web/webConfRegisterFee/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">注册类别：</label>
			<div class="controls">
			<c:if test="${webConfRegisterFee.type == 1}">
				<input type="text" value="ACCA"  readOnly="true" maxlength="4" class="input-xlarge "/>
			</c:if>
					<c:if test="${webConfRegisterFee.type == 2}">
						<input type="text" value="FIA" readOnly="true" maxlength="4" class="input-xlarge"/>
					</c:if>
				<%-- <form:input path="type" htmlEscape="false" maxlength="4" class="input-xlarge required digits"/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册费用：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" class="input-xlarge required  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="web:webConfRegisterFee:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>