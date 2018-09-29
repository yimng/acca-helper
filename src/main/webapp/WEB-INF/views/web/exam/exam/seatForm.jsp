<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')},
					examCityId: {required: true},
					examPlaceId: {required: true},
					examStartTime:{required: true},
					examEndTime:{required: true},
					examSignupEndtime:{required: true},
					examCourseIds:{required: true},
					totalSeats:{
						digits : true,
						max : 1000
					}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
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
		
		function chg(i){
			if(i == 1){
				$('#seatText').html('增加考位数:');
			} else {
				$('#seatText').html('减少考位数:');
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/exam?examType=${exam.self}&examFlag=${exam.now}">当前考试</a></li>
		<li><a href="${ctx}/web/exam?examType=${exam.self}&examFlag=${exam.old}">历史考试</a></li>
		<li class="active"><a href="javascript:void(0)">修改考位</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="exam" action="${ctx}/web/exam/editSeat" method="post" class="form-horizontal">
				<form:hidden path = "examId" />
				<form:hidden path = "examType" />
				<form:hidden path = "totalSeats" />
				<sys:message content="${message}"/>
				<fieldset>
					<legend>修改考位</legend>
					<div class="control-group">
						<label class="control-label">城市:</label>
						<div class="controls"><label class="lbl">${exam.examCityName}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">考点:</label>
						<div class="controls"><label class="lbl">${exam.examPlaceName}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">考试时间:</label>
						<div class="controls"><label class="lbl"><fmt:formatDate value="${exam.examStartTime}" pattern="yyyy-MM-dd HH:mm" />-<fmt:formatDate value="${exam.examEndTime}" pattern="HH:mm" /></label></div>
					</div>
					<div class="control-group">
						<label class="control-label">剩余考位:</label>
						<div class="controls"><label class="lbl">${exam.totalSeats - exam.usedSeats}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">操作:</label>
						<div class="controls">
							<input type="radio" name="addOrNo" value="1" onclick="chg(1)" checked="checked" />增加考位&nbsp;
							<input type="radio" name="addOrNo" value="2" onclick="chg(2)" />减少考位
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" id="seatText">增加考位数:</label>
						<div class="controls">
							<input id="addSeats" name="addSeats" htmlEscape="false" maxlength="11" class="required" />
						</div>
					</div>
					<div class="form-actions">
			<shiro:hasPermission name="web:exam:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
				</fieldset>
			</form:form>
</body>
</html>