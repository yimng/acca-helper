<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ACCA财经词汇管理</title>
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
		<li><a href="${ctx}/englishword/webAccaEnglishWord/">ACCA财经词汇列表</a></li>
		<li class="active"><a href="${ctx}/englishword/webAccaEnglishWord/form?id=${webAccaEnglishWord.id}">ACCA财经词汇<shiro:hasPermission name="englishword:webAccaEnglishWord:edit">${not empty webAccaEnglishWord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="englishword:webAccaEnglishWord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webAccaEnglishWord" action="${ctx}/englishword/webAccaEnglishWord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">单词<font color="red">*</font></label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文翻译<font color="red">*</font></label>
			<div class="controls">
				<form:input path="chineseName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文释义<!-- <font color="red">*</font> --></label>
			<div class="controls">
				<form:textarea htmlEscape="false" path="endescription" rows="4" maxlength="200" class="input-xxlarge required"/>
				<sys:ckeditor replace="endescription" uploadPath="/acca/upload" />
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文释义/详细解释/例子<!-- <font color="red">*</font> --></label>
			<div class="controls">
				<form:textarea htmlEscape="false" path="description" rows="4" maxlength="200" class="input-xxlarge required"/>
				<sys:ckeditor replace="description" uploadPath="/acca/upload" />
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">音标</label>
			<div class="controls">
				<form:input path="yinbiao" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">记忆方法</label>
			<div class="controls">
				<form:textarea path="jiyifangfa" htmlEscape="false" maxlength="1024" class="input-xxlarge required" cols="200" rows="15"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单词mp3地址<font color="red">*</font></label>
			<div class="controls">
				<form:input path="nameurl" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">音标mp3地址<font color="red">*</font></label>
			<div class="controls">
				<form:input path="yinbiaourl" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文释义mp3地址<font color="red">*</font></label>
			<div class="controls">
				<form:input path="endescriptionurl" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文释义mp3地址<font color="red">*</font></label>
			<div class="controls">
				<form:input path="descriptionurl" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">记忆方法mp3地址<font color="red">*</font></label>
			<div class="controls">
				<form:input path="jiyifangfaurl" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">证书</label>
			<div class="controls">
				<form:input path="certificate" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目</label>
			<div class="controls">
				<form:input path="subject" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="englishword:webAccaEnglishWord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>