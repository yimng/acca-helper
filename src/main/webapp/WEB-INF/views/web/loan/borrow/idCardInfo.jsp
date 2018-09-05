<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">身份信息</a></li>
		<li><a href="${ctx}/web/loanUser/blackListRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">黑名单</a></li>
		<li><a href="${ctx}/web/loanUser/socialRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">社交信息</a></li>
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
					<div class="control-group">
						<label class="control-label">姓名:</label>
						<div class="controls"><label class="lbl">${info.name}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">身份证号:</label>
						<div class="controls"><label class="lbl">${info.idNo}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">地址:</label>
						<div class="controls"><label class="lbl">${info.idAddress}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">身份证正面照:</label>
						<div class="controls">
							<sys:imageupload input="headId1" show="true" numbers="1" initData="${info.img1}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">身份证反面照:</label>
						<div class="controls">
							<sys:imageupload input="headId2" show="true" numbers="1" initData="${info.img2}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">手持身份证照片:</label>
						<div class="controls">
							<sys:imageupload input="headId3" show="true" numbers="1" initData="${info.img3}" />
						</div>
					</div>
					</c:if>
				</fieldset>
			</form:form>
</body>
</html>