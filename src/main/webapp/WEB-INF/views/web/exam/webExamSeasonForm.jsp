<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebExamSeason管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					/* loading('正在提交，请稍等...');
					form.submit(); */
					$.ajax({    
				        type :'post',  
				        async : false,
				        data : $('#inputForm').serialize(),    
				        url: "${ctx}/exam/webExamSeason/validata",    
				        success : function(data) { 
				        	if(data.result){
				        		loading('正在提交，请稍等...');
								form.submit(); 
							}else{
								alert(data.msg);
								return;
							}
				        }, 
				        error : function(data) {
			        		alert("网络异常！");
						}
		    		}); 
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
		
		function getFormJson(form) {
			var o = {};
			var a = $(form).serializeArray();
			$.each(a, function () {
			if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
			o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
			} else {
			o[this.name] = this.value || '';
			}
			});
			return o;
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam/webExamSeason/">列表</a></li>
		<li class="active"><a href="javascript:void(0);">添加或修改</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="examSeason" action="${ctx}/exam/webExamSeason/save" method="post" class="form-horizontal">
		<form:hidden path="examSeasonId"/>
		<input name="oldSeasonStr" type="hidden" value="${examSeason.examSeasonStr}" />
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">考季：<span class="help-inline"><font color="red">*</font></span></label>
			<div class="controls">
				<input id="examSeasonStr" name="examSeasonStr" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${examSeason.examSeasonStr}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考试版本：<span class="help-inline"><font color="red">*</font> </span></label>
			<div class="controls">
				<c:forEach items="${courses}" varStatus="v" var="p">
					<input name="examCourseIds" type="hidden" value="${p.examCourseId}" />
					<input disabled="disabled" type="checkbox" checked="checked" />${p.course}&nbsp;
					<c:if test="${!empty p.examVersions and p.examVersions != '[]'}">
					(<c:forEach items="${p.examVersions}" varStatus="v2" var="p2">
					<input name="examVersionStrs" type="checkbox" value="${p.examCourseId},${p2.examVersionId},${p2.examVersionName}"
					 <c:if test="${p2.checked == true}">checked="checked"</c:if> />${p2.examVersionName}&nbsp;
					</c:forEach>)
					</c:if>
					<c:if test="${empty p.examVersions or p.examVersions == '[]'}">
						无可选择的考试版本
					</c:if>
					<br/>
				</c:forEach>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="exam:webExamSeason:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>