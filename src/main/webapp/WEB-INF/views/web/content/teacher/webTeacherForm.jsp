<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>名师指导管理</title>
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
		<li><a href="${ctx}/teacher/webTeacher/">名师管理</a></li>
		<li class="active"><a href="${ctx}/teacher/webTeacher/form?teacherId=${webTeacher.teacherId}">名师<shiro:hasPermission name="teacher:webTeacher:edit">${not empty webTeacher.teacherId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="teacher:webTeacher:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webTeacher" action="${ctx}/teacher/webTeacher/save" method="post" class="form-horizontal">
		<form:hidden path="teacherId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">中文名 <font color="red">*</font></label>
			<div class="controls">
				<form:input path="chName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文名</label>
			<div class="controls">
				<form:input path="enName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头像</label>
			<div class="controls">
				<sys:imageupload input="headId" show="false" numbers="1" initData="${webTeacher.headStr}"/>
				<span class="help-inline">建议分辨率为180*180</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职务<font color="red">*</font></label>
			<div class="controls">
				<form:input path="position" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ACCA会员等级<font color="red">*</font></label>
			<div class="controls">
				<form:radiobuttons path="accaLevel" items="${fns:getDictList('article_teacher_level')}" class="required" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">履历及个人简介<font color="red">*</font></label>
			<div class="controls">
				<form:textarea path="description" class="required" htmlEscape="false" cols="500" rows="5"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="teacher:webTeacher:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>