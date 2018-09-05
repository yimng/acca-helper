<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/loanUser/idCardRev?loanUserId=${id}&flag=${flag}&flag=${flag}">身份信息</a></li>
		<li><a href="${ctx}/web/loanUser/blackListRev?loanUserId=${id}&flag=${flag}&flag=${flag}">黑名单</a></li>
		<li><a href="${ctx}/web/loanUser/socialRev?loanUserId=${id}&flag=${flag}&flag=${flag}">社交信息</a></li>
		<li><a href="${ctx}/web/loanUser/creditImgRev?loanUserId=${id}&flag=${flag}&flag=${flag}">信用信息</a></li>
		<li><a href="${ctx}/web/loanUser/mobileInfoRev?loanUserId=${id}&flag=${flag}&flag=${flag}">手机信息</a></li>
		<li class="active"><a href="javascript:void(0)">授权信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="info" class="form-horizontal">
				<!-- <ul class="ul-form">
					<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
					<li class="clearfix"></li>
				</ul> -->
				<sys:message content="${message}"/>
				<%@include file="/WEB-INF/views/web/loan/common/passAndRefuseBtn.jsp" %>
				<fieldset>
					<legend>学籍信息(School)</legend>
					<c:if test="${empty xxSchoolInfo}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty xxSchoolInfo}">
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">生日:</label>
							<div class="controls"><label class="lbl"><fmt:formatDate value="${xxSchoolInfo.birth}" pattern="yyyy-MM-dd"/></label></div>
						</div>
						<div class="control-group">
							<label class="control-label">班级:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.classes}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">入学时间:</label>
							<div class="controls"><label class="lbl"><fmt:formatDate value="${xxSchoolInfo.entranceTime}" pattern="yyyy-MM-dd"/></label></div>
						</div>
						<div class="control-group">
							<label class="control-label">系:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.faculty}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">身份证号码:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.idNo}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">专业:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.major}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">民族:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.nation}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">学号:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.studentNo}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">学制:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.schooling}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">学习形式:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.studyStyle}</label></div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">分院:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.branchSchool}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">学籍状态:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.educationStatus}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">考号:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.examNo}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">离校时间:</label>
							<div class="controls"><label class="lbl"><fmt:formatDate value="${xxSchoolInfo.graduationTime}" pattern="yyyy-MM-dd"/></label></div>
						</div>
						<div class="control-group">
							<label class="control-label">层次:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.level}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">姓名:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.name}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">学校名称:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.schoolName}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">性别:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.sex}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">学历类别:</label>
							<div class="controls"><label class="lbl">${xxSchoolInfo.studyMode}</label></div>
						</div>
					</div>
					</c:if>
				</fieldset>
				<fieldset>
					<legend>学籍信息(Education)</legend>
					<c:if test="${empty xxEducationInfo}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty xxEducationInfo}">
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">生日:</label>
							<div class="controls"><fmt:formatDate value="${xxEducationInfo.birth}" pattern="yyyy-MM-dd"/></div>
						</div>
						<div class="control-group">
							<label class="control-label">学历级别:</label>
							<div class="controls">${xxEducationInfo.degreeLevel}</div>
						</div>
						<div class="control-group">
							<label class="control-label">入学时间:</label>
							<div class="controls"><fmt:formatDate value="${xxEducationInfo.entranceTime}" pattern="yyyy-MM-dd"/></div>
						</div>
						<div class="control-group">
							<label class="control-label">毕业时间:</label>
							<div class="controls"><fmt:formatDate value="${xxEducationInfo.graduationTime}" pattern="yyyy-MM-dd"/></div>
						</div>
						<div class="control-group">
							<label class="control-label">专业:</label>
							<div class="controls">${xxEducationInfo.major}</div>
						</div>
						<div class="control-group">
							<label class="control-label">学校地址:</label>
							<div class="controls">${xxEducationInfo.schoolLocated}</div>
						</div>
						<div class="control-group">
							<label class="control-label">学习形式:</label>
							<div class="controls">${xxEducationInfo.studyMode}</div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">证书编号:</label>
							<div class="controls">${xxEducationInfo.certificateNo}</div>
						</div>
						<div class="control-group">
							<label class="control-label">学历层次:</label>
							<div class="controls">${xxEducationInfo.educationLevel}</div>
						</div>
						<div class="control-group">
							<label class="control-label">毕业院校:</label>
							<div class="controls">${xxEducationInfo.graduateSchool}</div>
						</div>
						<div class="control-group">
							<label class="control-label">毕业结论:</label>
							<div class="controls">${xxEducationInfo.isGraduate}</div>
						</div>
						<div class="control-group">
							<label class="control-label">姓名:</label>
							<div class="controls">${xxEducationInfo.name}</div>
						</div>
						<div class="control-group">
							<label class="control-label">性别:</label>
							<div class="controls">${xxEducationInfo.sex}</div>
						</div>
					</div>
					</c:if>
				</fieldset>
			</form:form>
</body>
</html>