<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代注册管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.identity-info {
			align-content : center;
			width: 90%;
			margin:0 auto;
			text-align:left;
		}
		
		.identity-info th {
			width: 15%;
			height: auto;
		}
		.identity-info td {
			width: 30%;
			height: auto;
			text-align: left;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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

		//审核不通过
		function form(accaRegisterId){
			var iframeId = window.frameElement.id;
			top.$.jBox(
					'iframe:${ctx}/register/webAccaRegister/notPass?&iframeId=' + iframeId +"&status=4&registerType=2&accaRegisterId="+accaRegisterId,
					{title: '审核不通过',width: 650,height: 400,buttons: { '关闭': true }});
		}
		function chg(){
			window.location.href = "list?pageNo=0&pageSize=20&registerType=2&status=4";
			return false;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/register/webAccaRegister/list?registerType=2&status=1">FIA注册审核</a></li>
		<li class="active"><a href="${ctx}/register/webAccaRegister/form?accaRegisterId=${webAccaRegister.accaRegisterId}">查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webAccaRegister" action="${ctx}/register/webAccaRegister/updateRegisterPass?registerType=1" method="post" class="form-horizontal">
		<form:hidden path="accaRegisterId"/>
		<sys:message content="${message}"/>
		<div class="control-group" align="center">
			<div class="control-status">当前状态</div>
			<div class="control-status">
				<h3>
					<c:if test="${webAccaRegister.status == 1}"><font color="#ffcc00">待支付</font></c:if>
					<c:if test="${webAccaRegister.status == 2}"><font color="#ffcc00">待审核</font></c:if>
					<c:if test="${webAccaRegister.status == 3}"><font color="#00c965">审核通过</font></c:if>
					<c:if test="${webAccaRegister.status == 4}"><font color="#ff3737">审核不通过</font></c:if>
					<c:if test="${webAccaRegister.status == 5}"><font color="#00c965">注册成功</font></c:if>
				</h3>
			</div>
			<a class="control-status"></a>
				<c:if test="${webAccaRegister.status == 1}">&nbsp;</c:if>
				<c:if test="${webAccaRegister.status == 2}">
					<a class="btn btn-primary" href="${ctx}/register/webAccaRegister/updateRegisterAudit?accaRegisterId=${webAccaRegister.accaRegisterId}&status=3&registerType=2">审核通过</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="btn btn-primary" href="javascript:void(0);" onclick="form(${webAccaRegister.accaRegisterId});">审核不通过</a>
				</c:if>
				<c:if test="${webAccaRegister.status == 3}">
					<table align="center" width="90%">
						<tbody>
						<tr>
							<th>ACCA学员号</th>
							<td><form:input path="accaRegisterName" htmlEscape="false" maxlength="20" class="input-xlarge required"/></td>
							<th>ACCA密码</th>
							<td><form:input path="accaRegisterPassword" htmlEscape="false" maxlength="20" class="input-xlarge required"/></td>
						</tr>
						</tbody>
					</table>
				</c:if>
				<c:if test="${webAccaRegister.status == 4}">
				<div class="identity-info">
					<table border="1px" align="center" width="90%" class="table table-striped table-bordered table-condensed">
						<thead>审核记录</thead>
						<tbody>
							<tr>
								<th width="10%">审核人</th>
								<th width="10%">审核时间</th>
								<th width="10%">审核结果</th>
								<th>原因</th>
							</tr>
							<tr>
								<td width="10%" align="center">${webAccaRegister.checkPersonName}</td>
								<td width="10%" align="center"><fmt:formatDate value="${webAccaRegister.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td width="10%" align="center">
									<c:if test="${webAccaRegister.status == 1}">待支付</c:if>
									<c:if test="${webAccaRegister.status == 2}">待审核</c:if>
									<c:if test="${webAccaRegister.status == 3}">审核通过</c:if>
									<c:if test="${webAccaRegister.status == 4}">审核不通过</c:if>
									<c:if test="${webAccaRegister.status == 5}">注册成功</c:if>
								</td>
								<td align="center">${webAccaRegister.checkReason}</td>
							</tr>
						</tbody>
					</table>
				</div>
				</c:if>
				<c:if test="${webAccaRegister.status == 5}">
					<div class="identity-info">
						<table border="1px" align="center" width="90%" class="table table-striped table-bordered table-condensed">
							<thead>ACCA学员信息</thead>
							<tbody>
								<tr>
									<th>ACCA学员号</th>
									<td align="center">${webAccaRegister.accaRegisterName}</td>
									<th>ACCA密码</th>
									<td align="center">${webAccaRegister.accaRegisterPassword}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
		<div class="identity-info">
			<table align="center" width="90%" class="table table-striped table-bordered table-condensed">
				<thead>身份信息</thead>
				<tbody>
					<tr>
						<th>姓名</th>
						<td>${webAccaRegister.name}</td>
						<th>性别</th>
						<td>
							<c:choose>
								<c:when test="${webAccaRegister.sex == 1}">
									男
								</c:when>
								<c:otherwise>
									女
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>证件类型</th>
						<td>
							<c:choose>
								<c:when test="${webAccaRegister.cardType == 1}">
									身份证
								</c:when>
								<c:otherwise>
									护照
								</c:otherwise>
							</c:choose>
						</td>
						<th>证件号</th>
						<td>${webAccaRegister.cardNumber}</td>
					</tr>
					<tr>
						<th>手机号</th>
						<td>${webAccaRegister.phone}</td>
						<th>邮箱</th>
						<td>${webAccaRegister.email}</td>
					</tr>
					<tr>
						<th>距离最近城市</th>
						<td colspan="3">${webAccaRegister.cityName}</td>
					</tr>
					<tr>
						<th>两寸白底照</th>
						<td>
							<sys:imageupload input="whiteColorImgId" show="true" numbers="1" initData="${webAccaRegister.whiteColorImgStr}" imgWidth="200" imgHeight="100"/>
						</td>
						<th>身份证明原件</th>
						<td>
							<sys:imageupload input="identityCardOgImgId" show="true" numbers="1" initData="${webAccaRegister.identityCardOgImgStr}" imgWidth="200" imgHeight="100"/>
						</td>
					</tr>
					<tr>
						<th>身份证明翻译件</th>
						<td>
							<sys:imageupload input="identityCardTrImgId" show="true" numbers="1" initData="${webAccaRegister.identityCardTrImgStr}" imgWidth="200" imgHeight="100"/>
						</td>
						<td colspan="2"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<c:if test="${webAccaRegister.status > 1}">
			<div class="identity-info">
				<table border="1px" align="center" width="90%" class="table table-striped table-bordered table-condensed">
					<thead>支付信息</thead>
					<tbody>
						<tr>
							<th>注册费</th>
							<td>${webAccaRegister.payAmout}</td>
							<th>支付凭证</th>
							<td>
								<sys:imageupload input="payImgId" show="true" numbers="1" initData="${webAccaRegister.payImgStr}" imgWidth="200" imgHeight="100"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		<div class="identity-info">
			<c:if test="${webAccaRegister.status == 3}"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/></c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>