<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#btnSubmit').click(function(){
				var list = $('.required');
				var array = $.makeArray(list);
				for(var i in array){
					if($.trim(array[i].value) == ''){
						alert("每个输入框都不可为空！");
						return;
					}
				}
				loading('正在提交，请稍等...');
				$('#inputForm').submit();				
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0);">基本设置</a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/web/sys/edit" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<fieldset>
			<div class="control-group">
				<label class="control-label">客服热线:</label>
				<div class="controls">
					<input type="text" name="list[0].confValue" value="${list[0].confValue}" class="required" />
					<input type="hidden" name="list[0].confId" value="${list[0].confId}" />
					<input type="hidden" name="list[0].confType" value="0" />
					<input type="hidden" name="list[0].confKey" value="客服热线" />
					<input type="hidden" name="list[0].createUserId" value="${list[0].createUserId}" />
					<input type="hidden" name="list[0].createDate" value="<fmt:formatDate value="${list[0].createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">工作时间:</label>
				<div class="controls">
					<input type="text" name="list[1].confValue" value="${list[1].confValue}" class="required" />
					<input type="hidden" name="list[1].confId" value="${list[1].confId}" />
					<input type="hidden" name="list[1].confType" value="1" />
					<input type="hidden" name="list[1].confKey" value="工作时间" />
					<input type="hidden" name="list[1].createUserId" value="${list[1].createUserId}" />
					<input type="hidden" name="list[1].createDate" value="<fmt:formatDate value="${list[1].createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">微信公众号:</label>
				<div class="controls">
					<input type="text" name="list[2].confValue" value="${list[2].confValue}" class="required" />
					<input type="hidden" name="list[2].confId" value="${list[2].confId}" />
					<input type="hidden" name="list[2].confType" value="2" />
					<input type="hidden" name="list[2].confKey" value="微信公众号" />
					<input type="hidden" name="list[2].createUserId" value="${list[2].createUserId}" />
					<input type="hidden" name="list[2].createDate" value="<fmt:formatDate value="${list[2].createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">借款服务QQ:</label>
				<div class="controls">
					<input type="text" name="list[3].confValue" value="${list[3].confValue}" class="required" />
					<input type="hidden" name="list[3].confId" value="${list[3].confId}" />
					<input type="hidden" name="list[3].confType" value="3" />
					<input type="hidden" name="list[3].confKey" value="借款服务QQ" />
					<input type="hidden" name="list[3].createUserId" value="${list[3].createUserId}" />
					<input type="hidden" name="list[3].createDate" value="<fmt:formatDate value="${list[3].createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">兑现服务QQ:</label>
				<div class="controls">
					<input type="text" name="list[4].confValue" value="${list[4].confValue}" class="required" />
					<input type="hidden" name="list[4].confId" value="${list[4].confId}" />
					<input type="hidden" name="list[4].confType" value="4" />
					<input type="hidden" name="list[4].confKey" value="兑现服务QQ" />
					<input type="hidden" name="list[4].createUserId" value="${list[4].createUserId}" />
					<input type="hidden" name="list[4].createDate" value="<fmt:formatDate value="${list[4].createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</div>
			</div>
		</fieldset>
		<div class="form-actions">
			<shiro:hasPermission name="web:sys:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
</body>
</html>