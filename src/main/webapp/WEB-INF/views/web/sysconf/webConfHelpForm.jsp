<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebConfHelp管理</title>
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
		<li><a href="${ctx}/sysconf/webConfHelp/">列表</a></li>
		<li class="active"><a href="${ctx}/sysconf/webConfHelp/form?id=${webConfHelp.id}"><shiro:hasPermission name="sysconf:webConfHelp:edit">${not empty webConfHelp.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sysconf:webConfHelp:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webConfHelp" action="${ctx}/sysconf/webConfHelp/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="display:none">
			<label class="control-label">类型：1.注册acca的条件，2.什么是fia，3.ACCA官方授权机考中心免责声明，4.代报考免责协议及注意事项：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="4" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">富文本内容：
			<span class="help-inline"><font color="red">*</font> </span>
			</label>
			
			<div class="controls">
				<form:textarea id="htmlContent" htmlEscape="false" path="htmlContent" rows="4" maxlength="200" class="input-xxlarge required"/>
				<sys:ckeditor replace="htmlContent" uploadPath="/acca/upload" />
				
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sysconf:webConfHelp:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>