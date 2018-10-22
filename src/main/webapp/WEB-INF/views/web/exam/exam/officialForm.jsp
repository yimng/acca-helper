<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var citys = eval('${citys}');
			var places = eval('${places}');
			var ct = '${exam.examCityId}';
			var pt = '${exam.examPlaceId}';
			
			for( var i in citys){
				if(i == 0){
					$("#examCityId").append("<option value=''>请选择</option>");
					$('#s2id_examCityId .select2-chosen').text('请选择');
				}
				if(citys[i].examCityId == ct){
					$("#examCityId").append("<option value='"+citys[i].examCityId+"' selected = 'selected'>"+citys[i].cityName+"</option>");
					$('#s2id_examCityId .select2-chosen').text(citys[i].cityName);
				} else {
					$("#examCityId").append("<option value='"+citys[i].examCityId+"'>"+citys[i].cityName+"</option>");
				}
			}
			
			if(ct != null && ct != "" && ct != undefined){
				for(var i in places){
					if(i == 0){
						$("#examPlaceId").append("<option value=''>请选择</option>");
						$('#s2id_examPlaceId .select2-chosen').text('请选择');
					}
					if(places[i].examCityId == ct){
						if(places[i].examPlaceId == pt){
							$("#examPlaceId").append("<option value='"+places[i].examPlaceId+"' selected = 'selected'>"+places[i].examPlaceName+"</option>");
							$('#s2id_examPlaceId .select2-chosen').text(places[i].examPlaceName);
						} else {
							$("#examPlaceId").append("<option value='"+places[i].examPlaceId+"'>"+places[i].examPlaceName+"</option>");
						}						
					}
				}
			}
			
			$('#examCityId').change(function(){
				$("#examPlaceId").empty();
				$('#s2id_examPlaceId .select2-chosen').text('请选择');
				var examCityId = $("#examCityId option:selected").val();
				var examCityName = $("#examCityId option:selected").text();
				$("#examPlaceId").append("<option value=''>请选择</option>");
				if(examCityId != ""){
					for(var i in places){
						if(examCityId == places[i].examCityId){
							$('#examPlaceId').append("<option value='"+ places[i].examPlaceId +"'>" + places[i].examPlaceName + "</option>");
						}
					}
				}
			});
			
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')},
					examCityId: {required: true},
					examPlaceId: {required: true},
					examStartTime:{required: true},
					examEndTime:{required: true},
					examSignupEndtime:{required: true},
					examCourseIds:{required: true},
					totalSeats:{
						digits : true,
						max : 100000000
					}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					$.ajax({    
				        type :'post',  
				        async : false,  
				        dataType : 'json',
				        data : getFormJson($('#inputForm')),    
				        url: "${ctx}/web/exam/validataOfficial",    
				        success : function(data) { 
				        	if(data.result){
				        		loading('正在提交，请稍等...');
								$('#examPlaceName').val($('#examPlaceId option:selected').text());
								$('#examCityName').val($('#examCityId option:selected').text());
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
		    		//alert(getFormJson('#inputForm'));
		    		//return;
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
			var a	 = $(form).serializeArray();
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
		<li><a href="${ctx}/web/exam?examType=${exam.examType}&examFlag=${exam.now}">当前考试</a></li>
		<li><a href="${ctx}/web/exam?examType=${exam.examType}&examFlag=${exam.old}">历史考试</a></li>
		<li class="active"><a href="javascript:void(0)">添加或修改考试</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="exam" action="${ctx}/web/exam/editOfficial" method="post" class="form-horizontal">
		<form:hidden path = "examId" />
		<form:hidden path = "examType" />
		<form:hidden path = "examFlag" />
		<form:hidden path = "examCityName" />
		<form:hidden path = "examPlaceName" />
		<input type="hidden" name="oldExamPlaceId" value="${exam.examPlaceId}" />
		<input name="oldExamStartTime" type="hidden" value="<fmt:formatDate value="${exam.examStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		<sys:message content="${message}"/>
		<fieldset>
			<legend>
				官方<c:if test="${exam.examType == exam.mac}">机考</c:if><c:if test="${exam.examType == exam.pen}">笔考</c:if><c:if test="${empty exam.examId}">添加</c:if><c:if test="${!empty exam.examId}">修改</c:if>
			</legend>
			<div class="control-group">
				<label class="control-label">城市<font color="red">*</font></label>
				<div class="controls">
					<form:select path="examCityId" class="input-medium"></form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">考点<font color="red">*</font></label>
				<div class="controls">
					<form:select path="examPlaceId" class="input-medium"></form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">考季<font color="red">*</font></label>
				<div class="controls">
					<form:select path="examSeasonStr" class="input-medium required">
						<form:option label="请选择" value="" htmlEscape="false"/>
						<form:options items="${seasons}" itemLabel="examSeasonStr" itemValue="examSeasonStr" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>考试科目和考试时间</legend>
			<c:forEach items="${courses}" var="p" varStatus="i">
				<input type="hidden" name="list[${i.index}].examId" value="${p.examId}" />
				<input type="hidden" name="list[${i.index}].examCourseId" value="${p.examCourseId}" />
				<input type="hidden" name="list[${i.index}].course" value="${p.course}" />
				<div class="control-group">
					<label class="control-label">考试科目</label>
					<div class="controls"><label class="lbl">${p.course}</label></div>
				</div>
				<div class="control-group">
					<label class="control-label">考试时间<font color="red">*</font></label>
					<div class="controls">
						<input id="list[${i.index}].examStartTime" name="list[${i.index}].examStartTime" type="text"  maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${p.examStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
						-
						<input id="list[${i.index}].examEndTime" name="list[${i.index}].examEndTime" type="text"  maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${p.examEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">报名截止时间<font color="red">*</font></label>
					<div class="controls">
						<input id="list[${i.index}].examSignupEndtime" name="list[${i.index}].examSignupEndtime" type="text" maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${p.examSignupEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
					</div>
				</div>
			</c:forEach>
		</fieldset>
		<div class="form-actions">
			<shiro:hasPermission name="web:exam:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>