<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//页面加载的时候,判断文章分类,选择要展示的内容
			getCourseType();
			var courseType = $("[name='courseType']:checked").val();
			weatherShowUrl(courseType);

			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
					coursePrice:{
						min:0
					},
					courseTeacherPosition :{
						required :true
					},
					htmlImageId:{
						required :true
					}
				},
				messages:{
					coursePrice:{
						min:"价格不能为负数"
					},
					courseTeacherPosition :{
						required:"讲师职务不能为空"
					},
					htmlImageId:{
						required :"封面图片不能为空"
					}
				},
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

		//判断是否显示url
		function weatherShowUrl(value){
			//地面讲座的时候,不用显示url
			if (value == 2){
				$("#courseLinkUrlDiv").hide();
			} else {
				$("#courseLinkUrlDiv").show();
				if (value == 3){
					$("#url-name").text('CC视频ID')
				} else {
					$("#url-name").text('课程链接地址');
				}
			}
		}

		function getCourseType(){
			//获取分类id
			var categoryId = $("#articleCategoryId").val();
			$.ajax({
				url : "${ctx}/article/getCourseType",
				data : {articleCategoryId:categoryId},
				type: "post",
				success: function (type) {
					if (type == 1){
						$("#article-content").show();
						$("#course-content").hide();
					} else {
						$("#article-content").hide();
						$("#course-content").show();
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/article/list?type=1">文章列表</a></li>
		<li class="active"><a href="${ctx}/article/form?type=1&articleId=${webArticle.articleId}">文章<shiro:hasPermission name="article:webArticle:edit">${not empty webArticle.articleId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="article:webArticle:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webArticle" action="${ctx}/article/save" method="post" class="form-horizontal">
		<form:hidden path="articleId"/>
		<input name="type" type="hidden" value="1"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">标题<span class="help-inline"><font color="red">*</font> </span></label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文章分类<span class="help-inline"><font color="red">*</font> </span></label>
			<div class="controls">
				<form:select path="articleCategoryId" onclick="getCourseType();" htmlEscape="false" maxlength="20" class="input-medium">
					<form:options items="${categoryList}" itemLabel="name" itemValue="articleCategoryId" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">封面图片<span class="help-inline"><font color="red">*</font> </span></label>
			<div class="controls">
				<sys:imageupload input="htmlImageId" show="false" numbers="1" initData="${webArticle.htmlImageStr}"/>
				<span class="help-inline">建议分辨率为300*300</span>
			</div>
		</div>

		<!-- 文章部分开始 -->
		<div id="article-content" >
			<div class="control-group">
				<label class="control-label">内容<span class="help-inline"></span></label>
				<div class="controls">
					<form:textarea id="htmlContent" htmlEscape="false" path="htmlContent" rows="4" maxlength="200" class="input-xxlarge required"/>
					<sys:ckeditor replace="htmlContent" uploadPath="/acca/upload" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">关联科目</label>
				<div class="controls">
					<input name="courseIdList" <c:if test="${fn:contains(courseIdList,-1)}">
						checked = "checked"
					</c:if> value="-1" type="checkbox"/> 前导
					<c:forEach items="${examCourses}" var="course">
						<c:set var="flag" value="false"/>
						<c:forEach items="${courseIdList}" var="courseId">
							<c:if test="${courseId == course.examCourseId}">
								<c:set var="flag" value="true"/>
							</c:if>
						</c:forEach>
						<input name="courseIdList" <c:if test="${flag == true}">
							checked = "checked"
						</c:if> value="${course.examCourseId}" type="checkbox"/> ${course.course}
					</c:forEach>
				</div>
			</div>
		</div>
		<!-- 文章部分结束 -->

		<!-- 公开课部分开始 -->
		<div id="course-content" style="display: none;">
			<div class="control-group">
				<label class="control-label">讲师<span class="help-inline"><font color="red">*</font></span></label>
				<div class="controls">
					<form:input path="courseTeacher" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">讲师职务<span class="help-inline"><font color="red">*</font></span></label>
				<div class="controls">
					<form:input path="courseTeacherPosition" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">授课地址</label>
				<div class="controls">
					<form:input path="courseAddress" htmlEscape="false" maxlength="200" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">授课时间</label>
				<div class="controls">
					<input id="courseStartTime" name="courseStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="<fmt:formatDate value="${webArticle.courseStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'courseEndTime\')}',isShowClear:false});"/>
					-
					<input id="courseEndTime" name="courseEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="<fmt:formatDate value="${webArticle.courseEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'courseStartTime\')}',isShowClear:false});"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">价格</label>
				<div class="controls">
					<form:input path="coursePrice" htmlEscape="false" class="input-xlarge number"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">课程形式<span class="help-inline"><font color="red">*</font></span></label>
				<div class="controls">
					<form:radiobuttons path="courseType" items="${fns:getDictList('article_course_type')}" itemLabel="label" onclick="weatherShowUrl(value);" itemValue="value" htmlEscape="false"/>
				</div>
			</div>
			<div class="control-group" id="courseLinkUrlDiv">
				<label class="control-label"><span id="url-name">课程链接地址</span><font color="red">*</font></label>
				<div class="controls">
					<form:input path="courseLinkUrl" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">图片<span class="help-inline"><font color="red">*</font></span></label>
				<div class="controls">
					<sys:imageupload input="courseImageId" show="false" numbers="1" initData="${webArticle.courseImageStr}" imgWidth="200" imgHeight="100"/>
					<span class="help-inline">建议分辨率为1242*715</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">内容</label>
				<div class="controls">
					<form:textarea id="courseContent" htmlEscape="false" path="courseContent" rows="4" maxlength="200" class="input-xxlarge required"/>
					<sys:ckeditor replace="courseContent" uploadPath="/acca/upload" />
				</div>
			</div>
		</div>
		<!-- 公开课部分结束 -->

		<div class="control-group">
			<label class="control-label">排序</label>
			<div class="controls">
				<form:input path="sortNum" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
				<span class="help-inline">请输入1-255之间的数字;数字越小,越靠前显示</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设置<font color="red">*</font></label>
			<div class="controls">
				<form:radiobuttons path="publish" items="${fns:getDictList('article_publish_flag')}" itemLabel="label" class="required" itemValue="value" htmlEscape="false"/>
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