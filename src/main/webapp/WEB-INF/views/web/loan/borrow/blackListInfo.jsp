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
		<li class="active"><a href="javascript:void(0)">黑名单</a></li>
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
					<legend>黑名单信息一</legend>
					<c:if test="${empty info}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty info}">
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">姓名:</label>
							<div class="controls"><label class="lbl">${info.name}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">身份证号:</label>
							<div class="controls"><label class="lbl">${info.idNo}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">身份证上的地址:</label>
							<div class="controls"><label class="lbl">${info.idNoAddress}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">父母所在地:</label>
							<div class="controls"><label class="lbl">${info.parentsAddress}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">单位名称:</label>
							<div class="controls"><label class="lbl">${info.companyName}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">单位地址:</label>
							<div class="controls"><label class="lbl">${info.companyAddress}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">电子邮箱:</label>
							<div class="controls"><label class="lbl">${info.email}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">电话:</label>
							<div class="controls"><label class="lbl">${info.mobile}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">家庭电话:</label>
							<div class="controls"><label class="lbl">${info.telephoneFamily}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">工作电话:</label>
							<div class="controls"><label class="lbl">${info.telephoneWork}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">第二联系人:</label>
							<div class="controls"><label class="lbl">${info.secondaryContact}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">与第二联系人关系:</label>
							<div class="controls"><label class="lbl">${info.secondaryContactRelation}</label></div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">第二联系人手机号:</label>
							<div class="controls"><label class="lbl">${info.secondaryContactMobile}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">逾期未还款期数:</label>
							<div class="controls"><label class="lbl">${info.overdueCount}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">最长逾期天数:</label>
							<div class="controls"><label class="lbl">${info.overdueDaysMax}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">逾期待还款总额:</label>
							<div class="controls"><label class="lbl">${info.overdueAmount}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">未还款总额:</label>
							<div class="controls"><label class="lbl">${info.noRepaymentAmount}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">网站垫还期数:</label>
							<div class="controls"><label class="lbl">${info.webPayCount}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">网站垫付金额:</label>
							<div class="controls"><label class="lbl">${info.webPayAmount}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">更新时间:</label>
							<div class="controls"><label class="lbl"><fmt:formatDate value="${info.renewalTime}" pattern="yyyy-MM-dd"/></label></div>
						</div>
						<div class="control-group">
							<label class="control-label">共同借款人:</label>
							<div class="controls"><label class="lbl">${info.coborrower}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">共同借款人身份证号码:</label>
							<div class="controls"><label class="lbl">${info.coborrowerIdNo}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">共同借款人电话:</label>
							<div class="controls"><label class="lbl">${info.coborrowerMobile}</label></div>
						</div>
					</div>
					</c:if>
				</fieldset>
				<fieldset>
					<legend>黑名单信息二</legend>
					<%-- <div class="control-group">
						<label class="control-label">姓名:</label>
						<div class="controls">${info.friendName}</div>
					</div>
					<div class="control-group">
						<label class="control-label">身份证号:</label>
						<div class="controls">${info.friendMobile}</div>
					</div> --%>
				</fieldset>
			</form:form>
</body>
</html>