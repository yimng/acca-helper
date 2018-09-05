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
					<legend>淘宝基本信息</legend>
					<c:if test="${empty tbInfo}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty tbInfo}">
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">真实姓名:</label>
							<div class="controls"><label class="lbl">${tbInfo.name}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">手机号码:</label>
							<div class="controls"><label class="lbl">${tbInfo.phoneBinded}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">身份证号:</label>
							<div class="controls"><label class="lbl">${tbInfo.idNum}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">当前等级:</label>
							<div class="controls"><label class="lbl">${tbInfo.memDegree}</label></div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">身份证认证状态:</label>
							<div class="controls"><label class="lbl">${tbInfo.identityAuth}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">成长值:</label>
							<div class="controls"><label class="lbl">${tbInfo.growthValue}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">登录邮箱地址:</label>
							<div class="controls"><label class="lbl">${tbInfo.loginEmail}</label></div>
						</div>
					</div>
					</c:if>
				</fieldset>
				<fieldset>
					<legend>支付宝基本信息</legend>
					<c:if test="${empty apInfo}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty apInfo}">
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">实名认证:</label>
							<div class="controls"><label class="lbl">${apInfo.verified}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">会员等级:</label>
							<div class="controls"><label class="lbl">${apInfo.alipayRate}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">认证通过时间:</label>
							<div class="controls"><label class="lbl">${apInfo.authenPassTime}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">手机号:</label>
							<div class="controls"><label class="lbl">${apInfo.phoneBinded}</label></div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">淘宝账户名:</label>
							<div class="controls"><label class="lbl">${apInfo.taobaoAccount}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">天猫等级:</label>
							<div class="controls"><label class="lbl">${apInfo.tmDegree}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">蚂蚁花呗-消费额度:</label>
							<div class="controls"><label class="lbl">${apInfo.tmDegree}</label></div>
						</div>
					</div>
					</c:if>
				</fieldset>
				<fieldset>
					<legend>收货地址</legend>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>收货人</th>
								<th>电话</th>
								<th>收货地址</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${raList}" var="ra">
							<tr>
								<td>
									${ra.receivePerson}
								</td>
								<td>
									${ra.tel}
								</td>
								<td>
									${ra.detailedAddress}
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</fieldset>
				<fieldset>
					<legend>订单记录</legend>
					<c:if test="${empty tranInfo}">
						<div class="control-group">
							暂无数据！
						</div>
					</c:if>
					<c:if test="${!empty tranInfo}">
					<div class="control-group">
						<label class="control-label">商品名称:</label>
						<div class="controls"><label class="lbl">${tranInfo.goodsName}</label></div>
					</div>
					<div class="control-group">
						<label class="control-label">收货地址:</label>
						<div class="controls"><label class="lbl">${tranInfo.receiveAddress}</label></div>
					</div>
					<div style="float: left;width: 30%;">
						<div class="control-group">
							<label class="control-label">订单时间:</label>
							<div class="controls"><label class="lbl">${tranInfo.date}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">收货人:</label>
							<div class="controls"><label class="lbl">${tranInfo.receiveName}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">订单状态:</label>
							<div class="controls"><label class="lbl">${tranInfo.babyStatus}</label></div>
						</div>
					</div>
					<div style="float: left;">
						<div class="control-group">
							<label class="control-label">订单金额:</label>
							<div class="controls"><label class="lbl">${tranInfo.totalPrice}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">收货人手机号:</label>
							<div class="controls"><label class="lbl">${tranInfo.receivePhone}</label></div>
						</div>
						<div class="control-group">
							<label class="control-label">支付类型:</label>
							<div class="controls"><label class="lbl">暂无</label></div>
						</div>
					</div>
					</c:if>
				</fieldset>
			</form:form>
</body>
</html>