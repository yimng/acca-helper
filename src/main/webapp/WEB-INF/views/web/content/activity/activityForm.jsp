<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>activity设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var articleId = '${activity.activityId}';
		$(document).ready(function() {
			var activityType = '${articleCategoryId}';
			var articleCategoryName = '${articleCategoryName}';
			$("#articleCategory").append("<option value='"+articleCategoryId+"' selected = 'selected'>"+articleCategoryName+"</option>");
			$('#s2id_articleCategory .select2-chosen').text(articleCategoryName);

			//获取下拉文章的数据
			getArticleList(articleCategoryId);
			var activityType = $("[name='type']:checked").val();
			//加载显示的内容
			showContent(activityType);

			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules:{
					files :{
						required : true
					},
					startTime:{
						required : true
					},
					endTime:{
						required : true
					},
					sort :{
						min : 1,
						max : 255
					},
					imgId :{
						required : true
					}
				},
				message:{
					files :{
						required : "图片不能为空"
					},
					startTime:{
						required : "开始时间不能为空"
					},
					endTime:{
						required : "结束时间不能为空"
					},
					sort :{
						min : "数字不合法",
						max : "数字不合法"
					},
					imgId :{
						required : "图片不能为空"
					}
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
				articleCategoryId = $("#articleCategory").val();
			}
			$.ajax({
				url : "${ctx}/activity/getArticlesByCategoryId",
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

		//显示类型需要录入的信息		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/list?status=0">活动管理</a></li>
		<li class="active"><a href="${ctx}/activity/form?activityId=${activity.activityId}">activity<shiro:hasPermission name="activity:activity:edit">${not empty activity.activityId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="activity:activity:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="activity" action="${ctx}/activity/save" method="post" class="form-horizontal">
		<form:hidden path="activityId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">活动名称<font color="red">*</font></label>
			<div class="controls">
				<form:input path="activity_name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>		
		<div id="activity-content" style="display: none;">
			<div class="control-group">
				<label class="control-label">活动内容<font color="red">*</font></label>
				<div class="controls">
					<form:textarea id="activityDesc" htmlEscape="false" path="activityDesc" rows="4" maxlength="1000" class="input-xxlarge required"/>
					<sys:ckeditor replace="htmlContent" uploadPath="/acca/upload" />
					<span class="help-inline"> </span>
				</div>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">开始时间<font color="red">*</font></label>
			<div class="controls">
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${activity.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间<font color="red">*</font></label>
			<div class="controls">
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${activity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="activity:activity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>