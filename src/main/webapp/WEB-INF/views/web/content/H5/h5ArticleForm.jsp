<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>名师文章管理</title>
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
		<li><a href="${ctx}/article/list?type=5">内容专题</a></li>
		<li class="active"><a href="${ctx}/article/form?type=5&articleId=${webArticle.articleId}">文章<shiro:hasPermission name="article:webArticle:edit">${not empty webArticle.articleId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="article:webArticle:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webArticle" action="${ctx}/article/save" method="post" class="form-horizontal">
		<form:hidden path="articleId"/>
		<%-- 有资有料文章,类型为1 --%>
		<input name="type" type="hidden" value="5"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题<span class="help-inline"><font color="red">*</font> </span></label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>				
		<div class="control-group">
			<label class="control-label">内容<span class="help-inline"><font color="red">*</font> </span></label>
			<div class="controls">
				<form:textarea id="htmlContent" htmlEscape="false" path="htmlContent" rows="4" maxlength="200" class="input-xxlarge required"/>
				<sys:ckeditor replace="htmlContent" uploadPath="/acca/upload" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序</label>
			<div class="controls">
				<form:input path="sortNum" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
				<span class="help-inline">请输入1-255之间的数字;数字越小,越靠前显示</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设置</label>
			<div class="controls">
				<form:radiobuttons path="publish" items="${fns:getDictList('article_publish_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">页面标题</label>
			<div class="controls">
				<form:input path="pageTitle" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="article:webArticle:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确 定"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>