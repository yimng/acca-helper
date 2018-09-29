<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${webExam.examFlag == webExam.now}">class="active"</c:if>>
			<a href="${ctx}/web/exam?examFlag=${webExam.now}&examType=${webExam.examType}">当前考试</a>
		</li>
		<li <c:if test="${webExam.examFlag == webExam.old}">class="active"</c:if>>
			<a href="${ctx}/web/exam?examFlag=${webExam.old}&examType=${webExam.examType}">历史考试</a>
		</li>
		<li><a href="${ctx}/web/exam/form?examType=${webExam.examType}">添加考试</a></li>
	</ul><br/>
	<form:form id="searchForm" modelAttribute="webExam" action="${ctx}/web/exam/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="examType" />
		<form:hidden path="examFlag" />
		<ul class="ul-form">
			<li><label>考点：</label>
				<form:select path="examPlaceId" class="input-medium">
					<form:option label="请选择" value="" htmlEscape="false"/>
					<form:options items="${places}" itemLabel="examPlaceName" itemValue="examPlaceId" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>考试时间：</label>
					<input id="start" name="start" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${webExam.start}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
					-
					<input id="end" name="end" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${webExam.end}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>城市</th>
				<th>考点</th>
				<th>考试时间</th>
				<th>考试科目</th>
				<c:if test="${webExam.examType == webExam.self}">
					<th>剩余席位</th>
				</c:if>
				<c:if test="${webExam.examType == webExam.mac or webExam.examType == webExam.pen}">
					<th>已报名人数</th>
				</c:if>
				<th>报名截止时间</th>
				<shiro:hasPermission name="web:exam:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" varStatus="index" var="exam">
			<tr>
				<td>${exam.examCityName}</td>
				<td>${exam.examPlaceName}</td>
				<td><fmt:formatDate value="${exam.examStartTime}" pattern="yyyy-MM-dd HH:mm" />-<fmt:formatDate value="${exam.examEndTime}" pattern="HH:mm" /></td>
				<td>${exam.courseStr}</td>
				<c:if test="${webExam.examType == webExam.self}">
					<td>${exam.totalSeats - exam.usedSeats}</td>
				</c:if>
				<c:if test="${webExam.examType == webExam.mac or webExam.examType == webExam.pen}">
					<td>${exam.signupNum}</td>
				</c:if>
				<td><fmt:formatDate value="${exam.examSignupEndtime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>
					<shiro:hasPermission name="web:exam:edit">
						<c:if test="${webExam.examType == webExam.self and webExam.examFlag == webExam.now}">
							<a href="${ctx}/web/exam/seatForm?examId=${exam.examId}">修改考位</a>
						</c:if>
						<a href="${ctx}/web/exam/detail?examId=${exam.examId}&examType=${webExam.examType}&examFlag=${webExam.examFlag}">报名情况</a>
						<c:if test="${webExam.examFlag == webExam.now}">
							<a href="${ctx}/web/exam/form?examId=${exam.examId}&examType=${webExam.examType}&examFlag=${webExam.examFlag}">编辑</a>
						</c:if>
					</shiro:hasPermission>
					<c:if test="${webExam.examType == webExam.self}">
						<shiro:hasPermission name="web:exam:delSelf">
							<a href="${ctx}/web/exam/delete?examId=${exam.examId}&examType=${webExam.examType}&examFlag=${webExam.examFlag}"
							   onclick="return confirmx('确认要删除该考试吗？', this.href)">删除</a>
						</shiro:hasPermission>
					</c:if>
					<c:if test="${webExam.examType == webExam.mac}">
						<shiro:hasPermission name="web:exam:delMac">
							<a href="${ctx}/web/exam/delete?examId=${exam.examId}&examType=${webExam.examType}&examFlag=${webExam.examFlag}"
							   onclick="return confirmx('确认要删除该考试吗？', this.href)">删除</a>
						</shiro:hasPermission>
					</c:if>
					<c:if test="${webExam.examType == webExam.pen}">
						<shiro:hasPermission name="web:exam:delPen">
							<a href="${ctx}/web/exam/delete?examId=${exam.examId}&examType=${webExam.examType}&examFlag=${webExam.examFlag}"
							   onclick="return confirmx('确认要删除该考试吗？', this.href)">删除</a>
						</shiro:hasPermission>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>