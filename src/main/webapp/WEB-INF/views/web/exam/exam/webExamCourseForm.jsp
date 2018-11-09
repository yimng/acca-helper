<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试科目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					price:{
						number:true,
						min : 0
					}
				},
				submitHandler: function(form){
					var i = 0;
					$(":checkbox").each(function(index){
						if(this.checked == true){
							var r = $(".examTypes input:eq("+index+")").val();
							r = parseInt(r);
							i += r;
						}
					});
					$("#examType").val(i);
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

		//异步保存版本信息
		function addToVersion(val){
			$.ajax({
				url: "${ctx}/web/webExamCourse/addExamVersion" ,
				type: 'POST',
				data:{
					versionName : val
				},
				//dataType:'json',
				success: function(data) {
					var version = "<input type='hidden' name='versions' value='" + data + "'/>";
					$("#div-version").append(version);
				}
			});
		}

		function add_prohist_form(s) {
			var fi = $(".prohist-form:last").clone().find("input:text").val("").end();
			$(".prohist-form:last .add").hide();
			$(".prohist-form:last .delete").show();
			$(".prohist-form:last").after(fi);
			$(".prohist-form:last .add").show();
			$(".prohist-form:last .delete").hide();
		}

		function delete_form(s) {
			$(s).parent("div").remove();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/webExamCourse/">考试科目列表</a></li>
		<li class="active"><a href="${ctx}/web/webExamCourse/form?id=${webExamCourse.examCourseId}">考试科目<shiro:hasPermission name="web:webExamCourse:edit">${not empty webExamCourse.examCourseId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="web:webExamCourse:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webExamCourse" action="${ctx}/web/webExamCourse/save" method="post" class="form-horizontal">
		<form:hidden path="examCourseId"/>
		<form:hidden path="examType"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">科目<font color="red">*</font></label>
			<div class="controls">
				<%--${webExamCourse.course}--%>
				<form:input path="course" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称<font color="red">*</font></label>
			<div class="controls">
				<form:input path="courseName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文名称</label>
			<div class="controls">
				<form:input path="englishName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文缩写</label>
			<div class="controls">
				<form:input path="englishShortName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报名费<font color="red">*</font></label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge required"/> 元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考试类型<font color="red">*</font></label>
			<div class="controls examTypes">
				<input type="checkbox" value="1"  <c:if test="${webExamCourse.flag1}">checked="checked"</c:if> />自有机考
				<input type="checkbox" value="2"  <c:if test="${webExamCourse.flag2}">checked="checked"</c:if> />官方机考
				<input type="checkbox" value="4"  <c:if test="${webExamCourse.flag3}">checked="checked"</c:if>/>官方笔考
			</div>
		</div>
		<div class="control-group" id="div-version">
			<label class="control-label">考试版本</label>
			<div class="controls">
				<c:if test="${not empty webExamCourse.versionList}">
					<c:forEach items="${webExamCourse.versionList }" var="version" varStatus="status">
						<div class="prohist-form">
							<input name="versions" type="hidden" value="${version.examVersionId}">
							<input name="versionName" class="input-xlarge" disabled="disabled" type="text" maxlength="20" value="${version.examVersionName}">
							<a class="loffset10" href="javascript:void(0);" onclick="delete_form(this);return false;" ><span class="glyphicon icon-remove" aria-hidden="true"></span></a>
						</div>
					</c:forEach>
				</c:if>
				<div class="prohist-form">
					<input  name="versionName" class="input-xlarge" type="text" placeholder="添加版本" onchange="addToVersion(value);" maxlength="20" value="">
					<a class="loffset10 delete" href="javascript:void(0);" onclick="delete_form(this);return false;" style="display:none;">
						<span class="glyphicon icon-remove" aria-hidden="true"></span></a>
					<a class="loffset10 add" href="javascript:void(0);" onclick="add_prohist_form(this);return false;">
						<span class="glyphicon icon-plus" aria-hidden="true"></span></a>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="web:webExamCourse:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>