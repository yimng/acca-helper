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
		<li><a href="${ctx}/web/loanUser/socialRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">社交信息</a></li>
		<li><a href="${ctx}/web/loanUser/creditImgRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">信用信息</a></li>
		<li class="active"><a href="javascript:void(0)">手机信息</a></li>
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
					<legend>基本信息</legend>
					<c:if test="${empty info}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty info}">
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">真实姓名:</label>
							<div class="controls"><label class="lbl">${info.userName}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">手机号:</label>
							<div class="controls"><label class="lbl">${info.mobilePhone}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">当前余额:</label>
							<div class="controls"><label class="lbl">暂无</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">账户积分:</label>
							<div class="controls"><label class="lbl">暂无</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">地址:</label>
							<div class="controls"><label class="lbl">${info.belongProvince}</label></div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">会员等级:</label>
							<div class="controls"><label class="lbl">${info.certNum}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">登录邮箱:</label>
							<div class="controls"><label class="lbl">暂无</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">身份证号码:</label>
							<div class="controls"><label class="lbl">暂无</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">开户日期:</label>
							<div class="controls"><label class="lbl"><fmt:formatDate value="${info.openDate}" pattern="yyyy-MM-dd"/></label></div>
						</div>
						<div class="control-group">
							<label class="control-label">通话详单:</label>
							<div class="controls"><label class="lbl">
							<a href="${ctx}/web/loanUser/exportExcel?loanUserId=${id}&loanApplyId=${applyId}">下载</a>
							</label></div>
						</div>
					</div>
					</c:if>
				</fieldset>
			</form:form>
</body>
</html>