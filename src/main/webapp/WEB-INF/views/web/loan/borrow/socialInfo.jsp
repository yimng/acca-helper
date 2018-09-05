<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/loanUser/idCardRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">身份信息</a></li>
		<li><a href="${ctx}/web/loanUser/blackListRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">黑名单</a></li>
		<li class="active"><a href="javascript:void(0)">社交信息</a></li>
		<li><a href="${ctx}/web/loanUser/creditImgRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">信用信息</a></li>
		<li><a href="${ctx}/web/loanUser/mobileInfoRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">手机信息</a></li>
		<li><a href="${ctx}/web/loanUser/giveAuthRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">授权信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="info" class="form-horizontal">
				<!-- <ul class="ul-form">
					<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
					<li class="clearfix"></li>
				</ul> -->
				<sys:message content="${message}"/>
				<%@include file="/WEB-INF/views/web/loan/common/passAndRefuseBtn.jsp" %>
				<fieldset>
					<c:if test="${empty info}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty info}">
					<legend>父母或直系亲属</legend>
					<div class="control-group">
						<label class="control-label">姓名:</label>
						<div class="controls"><label class="lbl">${info.familyName}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">手机号码:</label>
						<div class="controls"><label class="lbl">${info.familyMobile}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">与本人关系:</label>
						<div class="controls"><label class="lbl">${info.familyType}</label></div>
					</div>
				</fieldset>
				<fieldset>
					<legend>朋友或同事</legend>
					<div class="control-group">
						<label class="control-label">姓名:</label>
						<div class="controls"><label class="lbl">${info.friendName}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">身份证号:</label>
						<div class="controls"><label class="lbl">${info.friendMobile}</label></div>
					</div>
				</fieldset>
				<fieldset>
					<legend>QQ或微信</legend>
					<div class="control-group">
						<label class="control-label">${info.socialTypeStr}:</label>
						<div class="controls"><label class="lbl">${info.socialAccount}</label></div>
					</div>
				</fieldset>
				</c:if>
			</form:form>
</body>
</html>