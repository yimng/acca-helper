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
		#checkReason{
			margin-top:20px;
			width:90%;
		}
		.btn{
			margin-top:20px;
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
		function closeForm(){
			var accaRegisterId = $("#accaRegisterId").val();
			var status = $("#status").val();
			var registerType = $("#registerType").val();
			var checkReason = $("#checkReason").val();
			var id = '${iframeId}';

			//document.getElementById('ifrtest').contentWindow.location.href = "list?pageNo=0&pageSize=20&registerType=" + registerType + "&status=" + status;
			//top.$.jBox.close(true);
			$.ajax({
				url: "${ctx}/register/webAccaRegister/updateRegisterAudit" ,
				type: 'POST',
				data:{
					accaRegisterId : accaRegisterId,
					status : status,
					registerType : registerType,
					checkReason : checkReason
			    },
				//dataType:'json',
				success: function(data) {
					 parent.document.getElementById(id).contentWindow.chg();
					 top.$.jBox.close(true);
				}
			});
		}
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="webAccaRegister" action="${ctx}/register/webAccaRegister/updateRegisterAudit" method="post" class="form-horizontal">
		<form:hidden path="accaRegisterId"/>
		<form:hidden path="status"/>
		<form:hidden path="registerType"/>
		<form:textarea path="checkReason" maxLength="100" cols="500" rows="10"/>
		<br/>
		<input id="btnSubmit" class="btn btn-primary" onclick="closeForm();" type="button" value="确 定"/>
		<input class="btn btn-primary" type="button" onclick="top.$.jBox.close(true)" value="取消"/>
	</form:form>
	
</body>
</html>