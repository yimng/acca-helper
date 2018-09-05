<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息添加</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#btnSubmit').click(function(){
				$('#mcMsg').html('');
				var msgContent = $.trim($('#msgContent').val());
				if(msgContent == ""){
					$('#mcMsg').html('消息内容不能为空！');
					return;
				}
				loading('正在提交，请稍等...');
				$('#inputForm').submit();				
			});
		});
		
		function chg(i){
			if(i == 0){
				$('#pushTimeDiv').hide();
			} else {
				$('#pushTimeDiv').show();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/plat/kindergarten/">消息列表</a></li>
		<li class="active"><a href="javascript:void(0)">消息添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="msgSys" action="${ctx}/web/msgSys/edit" method="post" class="form-horizontal">
				<sys:message content="${message}"/>
				<fieldset>
					<legend>消息添加</legend>
					<form:hidden path = "msgId" />
					<%-- <input id="registerTime" name="registerTime" type="hidden" value="<fmt:formatDate value="${kindergarten.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /> --%>
					<div class="control-group">
						<label class="control-label">消息内容:</label>
						<div class="controls">
							<form:textarea path="msgContent" htmlEscape="false" rows="6" maxlength="150" class="input-large" />
							<span id="mcMsg" style="color: red;"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">发布方式:</label>
						<div class="controls">
							<form:radiobutton path="pushType" checked="true" onchange="chg(0)" label="立即发布" value="0" htmlEscape="false"/>
							<form:radiobutton path="pushType" onchange="chg(1)" label="定时发布" value="1" htmlEscape="false"/>
						</div>
					</div>
					<div class="control-group" id="pushTimeDiv" style="display: none;">
						<label class="control-label">发布时间：</label>
						<div class="controls">
							<input id="pushTime" name="pushTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${msgSys.pushTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
						</div>
					</div>
					<div class="form-actions">
						<shiro:hasPermission name="web:msgSys:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="发 布"/>&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</fieldset>
			</form:form>
</body>
</html>