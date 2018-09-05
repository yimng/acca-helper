<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function pass(){
			window.location.href = "${ctx}/web/loanUser/pass?loanUserId=${id}&loanApplyId=${applyId}";
		}
		
		function refuse(){
			window.location.href = "${ctx}/web/loanUser/refuse?loanUserId=${id}&loanApplyId=${applyId}";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">审核通过</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="info" action="${ctx}/web/loanUser/passConfirm" class="form-horizontal">
				<!-- <ul class="ul-form">
					<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
					<li class="clearfix"></li>
				</ul> -->
				<input id="loanUserId" name="loanUserId" type="hidden" value="${info.loanUserId}"/>
				<input id="loanApplyId" name="loanApplyId" type="hidden" value="${info.loanApplyId}"/>
				<input id="auditStatus" name="asItem" type="hidden" value="0"/>
				<input id="loanStatus" name="loanStatus" type="hidden" value="3"/>
				<sys:message content="${message}"/>
				<fieldset>
					<legend>请选择借款额度</legend>
					<div class="control-group">
						<label class="control-label">借款额度:</label>
						<div class="controls">
						<form:radiobutton path="loanLimit" checked="true" label="500" value="500" htmlEscape="false"/>
						<form:radiobutton path="loanLimit" label="600" value="600" htmlEscape="false"/>
						<form:radiobutton path="loanLimit" label="700" value="700" htmlEscape="false"/>
						<form:radiobutton path="loanLimit" label="800" value="800" htmlEscape="false"/>
						<form:radiobutton path="loanLimit" label="900" value="900" htmlEscape="false"/>
						<form:radiobutton path="loanLimit" label="1000" value="1000" htmlEscape="false"/>
						</div>
					</div>
					<div class="control-group">
						<input id="btnCancel" class="btn" type="submit" value="确认" />
					</div>
				</fieldset>
			</form:form>
</body>
</html>