<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>授权管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/loanUser/idCardRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">身份信息</a></li>
		<li><a href="${ctx}/web/loanUser/blackListRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">黑名单</a></li>
		<li><a href="${ctx}/web/loanUser/socialRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">社交信息</a></li>
		<li><a href="${ctx}/web/loanUser/creditImgRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">信用信息</a></li>
		<li><a href="${ctx}/web/loanUser/mobileInfoRev?loanUserId=${id}&loanApplyId=${applyId}&flag=${flag}">手机信息</a></li>
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
					<legend>信用卡邮箱账单基本信息</legend>
					<c:if test="${empty ccBasInfo}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty ccBasInfo}">
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">邮箱:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.idNum}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">信用卡卡号:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.creditCardNumber}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">信用额度(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.creditLimitUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期还款总额(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.newBalanceUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">预借现金额度(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.cashAdvanceLimitUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期最低还款额(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.minPaymentUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期账单金额(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.newChargesUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">上期账单金额(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.lastStatementBalanceUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">上期还款金额(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.lastPaymentUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期账单金额(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.newChargesUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期调整金额(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.adjustmentUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">循环利息(美元):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.interestUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">上期可用积分余额:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.previousPoints}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期新增积分:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.newPoints}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期奖励积分:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.bonusPoints}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">固定电话:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.fixedPhone}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">账单地址:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.billAddress}</label></div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">用户名:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.userName}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">账单周期:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.statementCycle}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">信用额度(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.creditLimitRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期还款总额(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.newBalanceRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">预借现金额度(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.cashAdvanceLimitRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期最低还款额(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.minPaymentRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期账单金额(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.newChargesRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">上期账单金额(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.lastStatementBalanceRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">上期还款金额(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.lastPaymentRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期账单金额(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.newChargesRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期调整金额(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.adjustmentRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">循环利息(人民币):</label>
							<div class="controls"><label class="lbl">${ccBasInfo.interestRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">可用积分余额:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.availablePoints}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期调整积分:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.adjustedPoints}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">本期兑换积分总数:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.redeemedPoints}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">性别:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.sex}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">账期:</label>
							<div class="controls"><label class="lbl">${ccBasInfo.statementCycle}</label></div>
						</div>
					</div>
					</c:if>
				</fieldset>
				<fieldset>
					<legend>信用卡邮箱账单交易列表信息</legend>
					<c:if test="${empty ccTransInfo}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty ccTransInfo}">
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">用户姓名:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.userName}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">账号:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.accountId}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">记账日:</label>
							<div class="controls"><label class="lbl"><fmt:formatDate value="${ccTransInfo.billedDate}" pattern="yyyy-MM-dd"/></label></div>
						</div>
						<div class="control-group">
							<label class="control-label">交易币种:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.currencyType}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">美元金额:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.amountUsd}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">交易地点:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.area}</label></div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">账期:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.statementMth}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">交易日:</label>
							<div class="controls"><label class="lbl"><fmt:formatDate value="${ccTransInfo.transactionDate}" pattern="yyyy-MM-dd"/></label></div>
						</div>
						<div class="control-group">
							<label class="control-label">交易摘要:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.transactionDetails}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">人民币金额:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.amountRmb}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">信用卡卡号(或后四位):</label>
							<div class="controls"><label class="lbl">${ccTransInfo.creditCardNumber}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">交易金额:</label>
							<div class="controls"><label class="lbl">${ccTransInfo.originalTransAmount}</label></div>
						</div>
					</div>
					</c:if>
				</fieldset>
			</form:form>
</body>
</html>