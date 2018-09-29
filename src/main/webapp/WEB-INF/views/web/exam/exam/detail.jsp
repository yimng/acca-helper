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
		<li><a href="${ctx}/web/exam?examType=${webSignup.examType}&examFlag=${exam.now}">当前考试</a></li>
		<li><a href="${ctx}/web/exam?examType=${webSignup.examType}&examFlag=${exam.old}">历史考试</a></li>
		<li class="active"><a href="javascript:void(0)">报名详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="exam" action="${ctx}/web/exam/editSeat" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<fieldset>
			<legend>报名情况</legend>
			<div class="control-group">
				<label class="control-label">考点:</label>
				<div class="controls"><label class="lbl">${exam.examPlaceName}</label></div>
			</div>
			<div class="control-group">
				<label class="control-label">考试时间:</label>
				<div class="controls"><label class="lbl"><fmt:formatDate value="${exam.examStartTime}" pattern="yyyy-MM-dd HH:mm" />-<fmt:formatDate value="${exam.examEndTime}" pattern="HH:mm" /></label></div>
			</div>
			<div class="control-group">
				<label class="control-label">考试科目:</label>
				<div class="controls"><label class="lbl">${exam.courseStr}</label></div>
			</div>
			<div class="control-group">
				<label class="control-label">已报名人数:</label>
				<div class="controls"><label class="lbl">${counts[0]}</label></div>
			</div>
			<c:if test="${exam.examType == exam.self}">
			<div class="control-group">
				<label class="control-label">剩余考位:</label>
				<div class="controls"><label class="lbl">${exam.totalSeats-exam.usedSeats}</label></div>
			</div>
			</c:if>
		</fieldset>
	</form:form>
	<ul class="nav nav-tabs">
		<li <c:if test="${empty webSignup.examSignupStatus}">class="active"</c:if>>
			<a href="${ctx}/web/exam/detail?examId=${exam.examId}">全部(${counts[0]})</a>
		</li>
		<c:forEach items="${maps}" var="item" varStatus="i">
			<li <c:if test="${webSignup.examSignupStatus == item.key}">class="active"</c:if>>
				<a href="${ctx}/web/exam/detail?examId=${exam.examId}&examSignupStatus=${item.key}">${item.value}</a>
			</li>
		</c:forEach>
	</ul><br/>
	<form:form id="searchForm" modelAttribute="webSignup" action="${ctx}/web/exam/detail" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="examSignupStatus" />
		<input type="hidden" name="examId" value="${exam.examId}" />
		<input type="hidden" name="examType" value="${exam.examType}" />
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>证件照</th>
				<th>身份信息</th>
				<th>ACCA学员号</th>
				<th>学校/单位</th>
				<th>状态</th>
				<th>考试科目</th>
				<th>考试版本</th>
				<th>报名时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" varStatus="index" var="p">
			<tr>
				<td><sys:imageupload input="imageId${index.index}" show="true" numbers="1" initData="${p.imageStr}" imgWidth="100" imgHeight="100"/></td>
				<td>
					姓名：${p.registerName}<br/>
					身份证号：${p.registerCardNumber}<br/>
					手机号：${p.registerPhone}<br/>
					注册邮箱：${p.registerEmail}
				</td>
				<td>${p.accaRegisterName}</td>
				<td>${p.org}</td>
				<td>${p.orderStatusName}</td>
				<td>${p.examCourse}</td>
				<td>${p.versionName}</td>
				<td>
					<fmt:formatDate value="${p.examSignupTime}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>