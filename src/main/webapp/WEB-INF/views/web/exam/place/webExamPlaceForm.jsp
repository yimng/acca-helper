<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考点类管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')},
					examCityId: {required: true},
					examPlaceId: {required: true},
					examStartTime:{required: true},
					examEndTime:{required: true},
					examSignupEndtime:{required: true},
					examCourseIds:{required: true},
					examPlaceContantPhone:{
						required: true,
						maxlength : 30,
						minlength : 1
					},
					examPlaceImageId:{
						required: true
					}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					if($('#examPlaceImageId').val() == null || $('#examPlaceImageId').val() == ''){
						$('#pMsg').html('图片不能为空！');
						return;
					}
					loading('正在提交，请稍等...');
					$('#examCityName').val($('#examCityId option:selected').text());
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
		<li><a href="${ctx}/web/place?examType=${webExamPlace.menuExamType}">考点列表</a></li>
		<li class="active"><a href="javascript:void(0);">考点<shiro:hasPermission name="web:place:edit">${not empty webExamPlace.examPlaceId?'修改':'添加'}</shiro:hasPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webExamPlace" action="${ctx}/web/place/save" method="post" class="form-horizontal">
		<form:hidden path="examPlaceId"/>
		<form:hidden path="examCityName"/>
		<form:hidden path="examType"/>
		<form:hidden path="menuExamType"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">考点城市名称<font color="red">*</font></label>
			<div class="controls">
				<form:select path="examCityId" class="input-medium required">
					<form:option value="" label="请选择城市"/>
					<form:options items="${citys}" itemLabel="cityName" itemValue="examCityId" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址<font color="red">*</font></label>
			<div class="controls">
				<form:input path="examDetailAddress" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline">例如：北京市海淀区北三环西路甲30号双天大厦308室</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考点名称<font color="red">*</font></label>
			<div class="controls">
				<form:input path="examPlaceName" htmlEscape="false" maxlength="30" class="input-medium required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考点编号<font color="red">*</font></label>
			<div class="controls">
				<form:input path="examPlaceSn" htmlEscape="false" maxlength="30" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考点位置<font color="red">*</font></label>
			<div class="controls">
				<sys:imageupload input="examPlaceImageId" show="false" numbers="1" initData="${webExamPlace.imageStr}" imgWidth="200" imgHeight="100"/>
				<span class="help-inline">1242*672,大小在500k以内</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联科目<font color="red">*</font></label>
			<div class="controls">
				<c:forEach items="${courses}" varStatus="v" var="p">
					<input name="courses" type="checkbox" class="examCourseId" value="${p.examCourseId}" <c:if test="${p.checked == true}">checked="checked"</c:if> />${p.course}&nbsp;
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经度<font color="red">*</font></label>
			<div class="controls">
				<form:input path="lng" htmlEscape="false" class="input-medium required"/>
				<span class="help-inline">经纬度为百度地图的经纬度，经纬度获取地址：http://api.map.baidu.com/lbsapi/getpoint/index.html</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纬度<font color="red">*</font></label>
			<div class="controls">
				<form:input path="lat" htmlEscape="false" class="input-medium required"/>
				<span class="help-inline">经纬度为百度地图的经纬度，经纬度获取地址：http://api.map.baidu.com/lbsapi/getpoint/index.html</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人<font color="red">*</font></label>
			<div class="controls">
				<form:input path="examPlaceContantName" htmlEscape="false" maxlength="20" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话<font color="red">*</font></label>
			<div class="controls">
				<form:input path="examPlaceContantPhone" htmlEscape="false" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分部管理员</label>
			<div class="controls">
				<form:select path="sysUserId" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${userList}" itemLabel="showName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="web:place:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>