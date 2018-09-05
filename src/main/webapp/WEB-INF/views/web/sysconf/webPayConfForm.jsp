<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收款账户管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var objJson = [];
					var len = $ (".prohist-form").length;
					$(".prohist-form").each(
						function(index){
							
								
								
								var title =$(".prohist-form:eq("+index+") #key").val();
								var content = $(".prohist-form:eq("+index+") #value").val();
								//alert(title);
								//alert(content);
								if(title != "" && title != null){
									objJson.push(jQuery.parseJSON('{\"key\":\"' +title + '\",\"value\":\"' + content + '\"}'));
								}
								//objJson.push(jQuery.parseJSON('{"PID":' + 1 + ',"Cnt":' + 2 + '}'));
							
						}		
					);
					var str = JSON.stringify(objJson);
					//var str2 = str.replaceall('\"','\\"');
					//var str2 = escapeHtml(str);
					//alert(str);
					str = str.trim();
					$("#receivePayJson").val(str);
					//alert("1"+str+"2");
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
		
			function escapeHtml(string) {
			    var entityMap = {
			        "&": "&amp;",
			        "<": "&lt;",
			        ">": "&gt;",
			        '"': '&quot;',
			        "'": '&#39;',
			        "/": '&#x2F;'
			    };
			    return String(string).replace(/[&<>"'\/]/g, function (s) {
			        return entityMap[s];
			    });
			}
		
		function add_prohist_form(s)
		{
			var fi = $(".prohist-form:last").clone().find("input:text").val("").end();		
			$(".prohist-form:last .add").hide();
			$(".prohist-form:last .delete").show();
			$(".prohist-form:last").after(fi);
			$(".prohist-form:last .add").show();
			$(".prohist-form:last .delete").hide();
		}
		
		function delete_form(s) {
			$(s).parent("div").remove();
		}
	</script>
	
	<style>
		.prohist-form input{
			margin:10px;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/webPayConf/">收款账户管理列表</a></li>
		<li class="active"><a href="${ctx}/web/webPayConf/form?id=${webPayConf.id}">收款账户<shiro:hasPermission name="web:webPayConf:edit">${not empty webPayConf.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="web:webPayConf:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webPayConf" action="${ctx}/web/webPayConf/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">账户名称：
			<span class="help-inline"><font color="red">*</font> </span>
			</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款方式名称：
			<span class="help-inline"><font color="red">*</font> </span>
			</label>
			<div class="controls">
				<form:input path="payName" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款方式图片：
			<span class="help-inline"><font color="red">*</font> </span>
			</label>
			<div class="controls">
				<sys:imageupload input="imageId" show="false" numbers="1" initData="${webPayConf.imageStr}" />
				<span>建议分辨率为150*150</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款信息：
			<span class="help-inline"><font color="red">*</font> </span>
			</label>
			
			<div class="controls ">
				<%-- <form:input path="receivePayJson" htmlEscape="false" maxlength="256" class="input-xlarge "/> --%>
				
				<c:if test="${not empty webPayConf.receivePayList}">
				
					<c:forEach items="${webPayConf.receivePayList }" var="hist" varStatus="status">
					<div class="prohist-form">
						<input id="key" class="input-xlarge" type="text" maxlength="50" placeholder="标题" value="${hist.key}">
						<input  id="value" class="input-xlarge" type="text" maxlength="50" placeholder="内容" value="${hist.value}">
						<a class="loffset10" href="javascript:void(0);" onclick="delete_form(this);return false;" ><span class="glyphicon icon-remove" aria-hidden="true"></span></a>
					</div>
					</c:forEach>
					
				</c:if>
			
			<div class="prohist-form">
				<input  id="key" class="input-xlarge" type="text" maxlength="50" placeholder="标题" value="">
				<input  id="value" class="input-xlarge" type="text" maxlength="50" placeholder="内容" value="">
				
				<a class="loffset10 delete" href="javascript:void(0);" onclick="delete_form(this);return false;" style="display:none;">
					<span class="glyphicon icon-remove" aria-hidden="true"></span></a>
				<a class="loffset10 add" href="javascript:void(0);" onclick="add_prohist_form(this);return false;">
					<span class="glyphicon icon-plus" aria-hidden="true"></span></a>
					
			</div>
			<input name="receivePayJson" class="required " id="receivePayJson" type="hidden" value="" />
			
			</div>
			
		</div>
		
		
		<div class="control-group">
			<label class="control-label">账户类型：
			<span class="help-inline"><font color="red">*</font> </span>
			</label>
			<div class="controls">
				<form:radiobutton path="accountType" value="1" />总部账户
				<form:radiobutton path="accountType" value="2" />分部账户
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="web:webPayConf:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>