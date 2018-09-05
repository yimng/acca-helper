<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章分类管理</title>
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
	<style type="text/css">
	.mycontrols{
		padding-top:3px;
	}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/webArticleCategory/">文章分类列表</a></li>
		<li class="active"><a href="${ctx}/webArticleCategory/form?id=${webArticleCategory.articleCategoryId}">文章分类<shiro:hasPermission name="articlecategory:webArticleCategory:edit">${not empty webArticleCategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="articlecategory:webArticleCategory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webArticleCategory" action="${ctx}/webArticleCategory/save" method="post" class="form-horizontal">
		<form:hidden path="articleCategoryId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">名称<font color="red">*</font></label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序<font color="red">*</font></label>
			<div class="controls">
				<form:input path="sortNum" htmlEscape="false" maxlength="11" class="input-xlarge  digits required"/>
				<span class="help-inline">请输入1-255之间的数字;数字越小越靠前显示</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型<font color="red">*</font></label>
			<div class="controls mycontrols">
				<c:choose>
					<c:when test="${empty webArticleCategory.articleCategoryId}">
						<form:radiobuttons path="type" items="${fns:getDictList('article_category_type')}" class="required" itemLabel="label" itemValue="value" htmlEscape="false"/>
						<span class="help-inline">注意类型确定后,就不可修改</span>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="type" value="${webArticleCategory.type}"/>
						<c:if test="${webArticleCategory.type == 1}">文章</c:if>
						<c:if test="${webArticleCategory.type == 2}">公开课</c:if>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="articlecategory:webArticleCategory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>