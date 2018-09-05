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
				        data : $('#inputForm').serialize(),    
				        url: "${ctx}/web/exam/validataFree",    
				        success : function(data) { 
				        	if(data.result){
				        		loading('正在提交，请稍等...');
								$('#examPlaceName').val($('#examPlaceId option:selected').text());
								$('#examCityName').val($('#examCityId option:selected').text());
								form.submit();
							}else{
								alert(data.msg);
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
	
			bandcheck();
			
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
		
		function bandcheck(){
			var CheckAll=document.getElementById('All');
			var UnCheck=document.getElementById('uncheck');
			var OtherCheck=document.getElementById('othercheck');
			var div=document.getElementById('courses');
			var    CheckBox=div.getElementsByTagName('input');
			CheckAll.onclick=function(){
					for(i=0;i<CheckBox.length;i++){
							CheckBox[i].checked=true;
						};
				};
			UnCheck.onclick=function(){
					for(i=0;i<CheckBox.length;i++){
							CheckBox[i].checked=false;
						};
				};
			othercheck.onclick=function(){
					for(i=0;i<CheckBox.length;i++){
							if(CheckBox[i].checked==true){
									CheckBox[i].checked=false;
								}
							else{
								CheckBox[i].checked=true
								}
							
						};
				};
		
		}	
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/exam?examType=${exam.self}&examFlag=${exam.now}">当前考试</a></li>
		<li><a href="${ctx}/web/exam?examType=${exam.self}&examFlag=${exam.old}">历史考试</a></li>
		<li class="active"><a href="javascript:void(0)">添加或修改考试</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="exam" action="${ctx}/web/exam/edit" method="post" class="form-horizontal">
				<form:hidden path = "examId" />
				<form:hidden path = "examType" />
				<form:hidden path = "examFlag" />
				<form:hidden path = "examCityName" />
				<form:hidden path = "examPlaceName" />
				<%-- <form:hidden path = "totalSeats" /> --%>
				<sys:message content="${message}"/>
				<fieldset>
					<legend>自有考试添加或修改</legend>
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
						<label class="control-label">考试时间<font color="red">*</font></label>
						<div class="controls">
							<input id="examStartTime" name="examStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${exam.examStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
							-
							<input id="examEndTime" name="examEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${exam.examEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">考试科目<font color="red">*</font></label>
						<div class="controls" id="courses">
							<c:forEach items="${courses}" varStatus="v" var="p">
								<input name="examCourseIds" type="checkbox" value="${p.examCourseId}" <c:if test="${p.checked == true}">checked="checked"</c:if> />${p.course}&nbsp;
								<c:if test="${!empty p.examVersions and p.examVersions != '[]'}">
								(<c:forEach items="${p.examVersions}" varStatus="v2" var="p2">
								<input name="examVersionStrs" type="checkbox" value="${p.examCourseId},${p2.examVersionId},${p2.examVersionName}"
								 <c:if test="${p2.checked == true}">checked="checked"</c:if> />${p2.examVersionName}&nbsp;
								</c:forEach>)
								</c:if>
								<br/>
							</c:forEach>
							
						</div>
						<div class="controls">
						<br />
						<input type="button" id="All" value="全选" /> <input type="button" id="uncheck" value="不选" /> <input type="button" id="othercheck" value="反选" /><br />
						</div>
					</div>
					<div class="control-group">
						<c:if test="${!empty exam.examId}">
							<label class="control-label">剩余考位数<font color="red">*</font></label>
						</c:if>
						<c:if test="${empty exam.examId}">
							<label class="control-label">可报名考位数<font color="red">*</font></label>
						</c:if>
						<div class="controls">
							<c:if test="${!empty exam.examId}">
								<input value="${exam.totalSeats - exam.usedSeats}" htmlEscape="false" maxlength="11" readonly="readonly" class="required" />
							</c:if>
							<c:if test="${empty exam.examId}">
								<form:input path="totalSeats" htmlEscape="false" maxlength="11" readonly="readonly" class="required" />
							</c:if>
						</div>
					</div> 
					<div class="control-group">
						<label class="control-label">报名截止时间<font color="red">*</font></label>
						<div class="controls">
							<input id="examSignupEndtime" name="examSignupEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${exam.examSignupEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
						</div>
					</div>
					<div class="form-actions">
			<shiro:hasPermission name="web:exam:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
				</fieldset>
			</form:form>
</body>
</html>