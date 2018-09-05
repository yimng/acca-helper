<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拒绝借款</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function chg(i){
			if(i == 1 || i == 3){
				$('#as').hide();
			} else {
				$('#as').show();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">拒绝</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="info" action="${ctx}/web/loanUser/passConfirm" class="form-horizontal">
				<!-- <ul class="ul-form">
					<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
					<li class="clearfix"></li>
				</ul> -->
				<input id="loanUserId" name="loanUserId" type="hidden" value="${info.loanUserId}"/>
				<input id="loanApplyId" name="loanApplyId" type="hidden" value="${info.loanApplyId}"/>
				<!-- <input id="auditStatus" name="auditStatus" type="hidden" value="0"/> -->
				<input id="loanStatus" name="loanStatus" type="hidden" value="4"/>
				<sys:message content="${message}"/>
				<fieldset>
					<legend>请选择拒绝原因</legend>
					<div class="control-group">
						<label class="control-label">原因:</label>
						<div class="controls">
						<form:radiobutton path="auditStatus" onchange="chg(1)" checked="true" label="黑名单" value="1" htmlEscape="false"/>
						<form:radiobutton path="auditStatus" onchange="chg(2)" label="基本信息" value="4" htmlEscape="false"/>
						<form:radiobutton path="auditStatus" onchange="chg(3)" label="其他" value="7" htmlEscape="false"/>
						</div>
					</div>
					<div class="control-group">
						<div class="controls" id="as" style="display: none;">
							身份认证<input name="asItem" checked="checked" type="checkbox" value="2" />&nbsp;
							信用认证<input name="asItem" checked="checked" type="checkbox" value="3" />&nbsp;
							社交认证<input name="asItem" checked="checked" type="checkbox" value="4" />&nbsp;
							手机号认证<input name="asItem" checked="checked" type="checkbox" value="5" />&nbsp;
							账户授权<input name="asItem" checked="checked" type="checkbox" value="6" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">详细原因：</label>
						<div class="controls">
							<form:textarea path="auditRemarks" htmlEscape="false" rows="6" maxlength="200" class="input-large" />
						</div>
					</div>
					<div class="form-actions">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
					</div>
				</fieldset>
			</form:form>
</body>
</html>