<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统消息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var articleId = '${webMsgSys.articleId}';
		var articleCategoryName = '${articleCategoryName}';
		$(document).ready(function() {
			//$("#name").focus();
			var articleCategoryId = '${articleCategoryId}';
			//获取下拉文章的数据
			getArticleList(articleCategoryId);
			var pushPeople = $("[name='pushPeople']:checked").val();
			if (pushPeople == null || pushPeople == "" || pushPeople == "undefind"){
				pushPeople = 0;
			}
			//加载显示的内容
			showContent(pushPeople);

			var jumpType = $("[name='jumpType']:checked").val();
			if (jumpType == null || jumpType == "" || jumpType == "undefind"){
				jumpType = 0;
			}
			showCourse(jumpType);
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

		//获取分类下的文章列表
		function getArticleList(articleCategoryId){
			if(articleCategoryId == null || articleCategoryId == "" || articleCategoryId == "undefined"){
				$("#s2id_articleCategory").append("<option value='"+articleCategoryId+"' selected = 'selected'>"+articleCategoryName+"</option>");
				$('#s2id_articleCategory .select2-chosen').text(articleCategoryName);
			}
			$.ajax({
				url : "${ctx}/banner/getArticlesByCategoryId",
				data : {articleCategoryId:articleCategoryId},
				type: "post",
				success: function (articles) {
					//先清空下拉框
					$("#articleId").empty();
					$('#s2id_articleId .select2-chosen').empty();
					//遍历获取到的数据
					for( var i in articles){
						if(articles[i].articleId == articleId){
							$("#articleId").append("<option value='"+articles[i].articleId+"' selected = 'selected'>"+articles[i].title+"</option>");
							$('#s2id_articleId .select2-chosen').text(articles[i].title);
						} else {
							$("#articleId").append("<option value='"+articles[i].articleId+"'>"+articles[i].title+"</option>");
							$('#s2id_articleId .select2-chosen').text(articles[i].title);
						}
					}
				}
			});
		}
		function showCourse(value){
			if(value == null) value = 1;
			if (value == 1){
				$("#course-div").show();
			} else {
				$("#course-div").hide();
			}
		}

		//显示类型需要录入的信息
		function showContent(value){
			if(value == null) value = 1;
			if (value == 1){
				$("#course-content").show();
			} else {
				$("#course-content").hide();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sysmsg/webMsgSys/">消息推送</a></li>
		<li class="active"><a href="${ctx}/sysmsg/webMsgSys/form?msgId=${webMsgSys.msgId}">添加推送</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webMsgSys" action="${ctx}/sysmsg/webMsgSys/save" method="post" class="form-horizontal">
		<form:hidden path="msgId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">标题<font color="red">*</font></label>
			<div class="controls">
				<form:input path="msgTitle" htmlEscape="false" maxlength="256" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容<font color="red">*</font></label>
			<div class="controls">
				<form:textarea path="msgContent" htmlEscape="false" rows="4" class="input-xxlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推送对象<font color="red">*</font></label>
			<div class="controls">
				<form:radiobuttons path="pushPeople" items="${fns:getDictList('push_people_type')}" onclick="showCourse(value);" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div id="course-div" style="display: none;">
			<div class="control-group">
				<label class="control-label">待考试科目<font color="red">*</font></label>
				<div class="controls">
					<c:forEach items="${examCourses}" var="course">
						<c:set var="flag" value="false"/>
						<c:forEach items="${webMsgSys.examCourse}" var="courseId">
							<c:if test="${courseId == course.examCourseId}">
								<c:set var="flag" value="true"/>
							</c:if>
						</c:forEach>
						<input name="examCourse" <c:if test="${flag == true}">
							checked = "checked"
						</c:if> value="${course.examCourseId}" type="checkbox"/> ${course.course}
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">点击消息跳转方式<font color="red">*</font></label>
			<div class="controls">
				<form:radiobuttons path="jumpType" items="${fns:getDictList('msg_open_type')}" onclick="showContent(value);" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>

		<div id="course-content" style="display: none;">
			<div class="control-group">
				<label class="control-label">文章分类<font color="red">*</font></label>
				<div class="controls">
					<form:select path="" id="articleCategory" htmlEscape="false" maxlength="200" onclick="getArticleList(value);" class="input-medium required">
						<form:options items="${categoryList}" itemLabel="name" itemValue="articleCategoryId" htmlEscape="false"/>
					</form:select>
						<span class="help-inline"> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">选择文章<font color="red">*</font></label>
				<div class="controls">
					<select id="articleId" name="articleId" class="input-medium required"></select>
					<span class="help-inline"> </span>
				</div>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="sysmsg:webMsgSys:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="开始推送"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>