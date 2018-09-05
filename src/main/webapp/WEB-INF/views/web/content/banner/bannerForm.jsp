<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>banner设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var articleId = '${banner.articleId}';
		$(document).ready(function() {
			var articleCategoryId = '${articleCategoryId}';
			var articleCategoryName = '${articleCategoryName}';
			$("#articleCategory").append("<option value='"+articleCategoryId+"' selected = 'selected'>"+articleCategoryName+"</option>");
			$('#s2id_articleCategory .select2-chosen').text(articleCategoryName);

			//获取下拉文章的数据
			getArticleList(articleCategoryId);
			var bannerType = $("[name='type']:checked").val();
			//加载显示的内容
			showContent(bannerType);

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

		//显示类型需要录入的信息
		function showContent(value){
			if(value == null) value = 1;
			if (value == 1){
				//1的时候,显示录入url
				$("#banner-common").show();
				$("#banner-content").hide();
				$("#banner-article").hide();
			} else if(value == 2){
				//2的时候,显示录入富文本
				$("#banner-common").hide();
				$("#banner-content").show();
				$("#banner-article").hide();
			} else {
				//3的时候,显示录入有资有料
				$("#banner-common").hide();
				$("#banner-content").hide();
				$("#banner-article").show();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/banner/list?status=0">Banner管理</a></li>
		<li class="active"><a href="${ctx}/banner/form?bannerId=${banner.bannerId}">Banner<shiro:hasPermission name="banner:banner:edit">${not empty banner.bannerId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="banner:banner:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="banner" action="${ctx}/banner/save" method="post" class="form-horizontal">
		<form:hidden path="bannerId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">名称<font color="red">*</font></label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片<font color="red">*</font></label>
			<div class="controls">
				<sys:imageupload input="imgId" show="false" numbers="1" initData="${banner.imgStr}" imgWidth="200" imgHeight="100"/>
				<span class="help-inline">建议分辨率为1242*600,大小在500k以内</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型<font color="red">*</font></label>
			<div class="controls">
				<form:radiobuttons path="type" items="${fns:getDictList('banner_type')}" class="required" onclick="showContent(value);" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"> </span>
			</div>
		</div>
		<%-- 普遍的banner,加链接的 --%>
		<div id="banner-common">
			<div class="control-group">
				<label class="control-label">外链地址<font color="red">*</font></label>
				<div class="controls">
					<form:input path="linkUrl" htmlEscape="false" maxlength="512" class="input-xlarge required"/>
					<span class="help-inline"> </span>
				</div>
			</div>
		</div>

		<%-- 富文本banner --%>
		<div id="banner-content" style="display: none;">
			<div class="control-group">
				<label class="control-label">内容<font color="red">*</font></label>
				<div class="controls">
					<form:textarea id="htmlContent" htmlEscape="false" path="htmlContent" rows="4" maxlength="200" class="input-xxlarge required"/>
					<sys:ckeditor replace="htmlContent" uploadPath="/acca/upload" />
					<span class="help-inline"> </span>
				</div>
			</div>
		</div>

		<%--有资有料--%>
		<div id="banner-article" style="display: none;">
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

		<div class="control-group">
			<label class="control-label">开始时间<font color="red">*</font></label>
			<div class="controls">
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${banner.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间<font color="red">*</font></label>
			<div class="controls">
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${banner.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序<font color="red">*</font></label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="input-xlarge digits required"/>
				<span class="help-inline">请输入1-255之间的数字;数字越小,越靠前显示</span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="banner:banner:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>