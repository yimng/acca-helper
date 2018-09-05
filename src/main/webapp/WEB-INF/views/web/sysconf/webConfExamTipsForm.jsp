<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebConfExamTips管理</title>
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
		<li><a href="${ctx}/sysconf/webConfExamTips/">列表</a></li>
		<li class="active"><a href="${ctx}/sysconf/webConfExamTips/form?id=${webConfExamTips.id}"><shiro:hasPermission name="sysconf:webConfExamTips:edit">${not empty webConfExamTips.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sysconf:webConfExamTips:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webConfExamTips" action="${ctx}/sysconf/webConfExamTips/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类别</label>
			<div class="controls">
				${webConfExamTips.name}
				<%-- <form:input path="officialExamProcess" htmlEscape="false" maxlength="256" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容<span class="help-inline"><font color="red">*</font> </span></label>
			<div class="controls">
				<form:textarea path="officialExamProcess" htmlEscape="false" maxlength="1024" class="input-xxlarge required" cols="200" rows="15"/>
				
				<%-- <form:input path="officialExamProcess" htmlEscape="false" maxlength="256" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sysconf:webConfExamTips:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>