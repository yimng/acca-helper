<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>WebApkVersion管理</title>
	<meta name="decorator" content="default"/>
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
		
		
		function fileBtnClick(){
			${input}UploadPicClick("${input}File");
		}
		
		//上传按钮点击事件函数，用于上传图片使用
		function uploadPicClick(){
			//alert("aaa");
			
			$("#file").click();
			return ;
		}
		
		function fileChange(fileId){
			//var fileId= "$uploadFile";
			var fileValue = $("#file").val();
			//alert(fileValue);
			if(!fileValue){
				return;
			}
			
			AjaxFileUpload(fileId); 
		}
		
		function AjaxFileUpload(fileId){
			//alert(fileId);
			
			${input}showLoadingMask("文件上传中...");
			
			$.ajaxFileUpload({
				url:"${ctxapi}"+"/files/uploadApk.do",
				secureuri: false,
				fileElementId:fileId,
				dataType:"json",
				beforeSend: function() {  
		              // alert("bbbb");  
					//loading('正在提交，请稍等...');
		           },
				success:function(data, status){
					if(data.respCode==0){
						//alert("修改成功");
						var version=data.version;
						var buildNumber=data.buildNumber;
						var packageName=data.packageName;
						var fileUrl=data.fileList[0].fileUrl;
						var id=data.fileList[0].id;
						//alert(version);
						//alert(buildNumber);
						//alert(packageName);
						$("#downloadId").val(id);
						$("#fileUrl").val(fileUrl);
						$("#version").val(version);
						$("#buildNumber").val(buildNumber);
						$("#packageName").val(packageName);
						
					}else{
						//alert("cccccdddd");
						alert(data.respDesc);
						$("#"+fileId).val("");
					}
				},
				complete: function() {  
					//alert("complete");  
					//closeLoadingMask();
					${input}closeLoadingMask();
					$("#uploadFile").val("");
		           },
				error:function(data, status, e){
					alert(data);
					alert(e); 
					$("#uploadFile").val("");
					//closeLoadingMask();
					${input}closeLoadingMask();
				},
				//type:"POST"
			});
			
		}
		
		
		/*
		 * 以下为ajax请求时候的loading动画
		*/

		function ${input}showLoadingMask(text){
			
			var imgUrl = "${ctxStatic}/images/ajax_load.gif";
			
			var str = '<div id="uploadLoadingDivId${input}" style="position: fixed;display:none;top: 0px; left: 0px; right:0px; bottom:0px; background-color: #000; -moz-opacity: 0.4; opacity:0.4; z-index:999999;filter: alpha(opacity=30);">';
				str += '	<div style="position:fixed;top:50%;left:50%;text-align:center">';
				str += '		<img src="';
				str +=	imgUrl;
				str += 			'" />';
				str += '		<div id="uploadLoadingDivTextId${input}" style="margin:10px 0px;color:#fff">';
				str += text;
				str += '		</div>';
				str += '	</div>';
				str += '</div>';
			
				
			$("body").append(str);
			
			
			var windowWidth = $(window).width();
			var windowHeight =$(window).height()
			
			var popupHeight = $("#uploadLoadingDivId${input}").height();
			var popupWidth = $("#uploadLoadingDivId${input}").width();
			
			
			$("#uploadLoadingDivId${input}").css({
				"position": "fixed",
			    "top": windowHeight/2-popupHeight/2,
			    "left": windowWidth/2-popupWidth/2
			});
			$("#uploadLoadingDivId${input}").show();
			
		}

		function ${input}closeLoadingMask(){
			$("#uploadLoadingDivId${input}").remove();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/web/webApkVersion/">APP版本升级</a></li>
		<li class="active"><a href="${ctx}/web/webApkVersion/form?id=${webApkVersion.id}">APP<shiro:hasPermission name="web:webApkVersion:edit">${not empty webApkVersion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="web:webApkVersion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webApkVersion" action="${ctx}/web/webApkVersion/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="downloadId" id="downloadId" />
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">安装包：</label>
			<div class="controls">
				<input value=${webApkVersion.image.fileUrl} id="fileUrl" type="text" readOnly="true" class="input-xlarge "/>
				<input type="file" style="display:none" id="file" onchange="fileChange(this.id)" name="files"/>
				<input type="button" class="btn" value="上传文件" onClick="uploadPicClick()" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本号：</label>
			<div class="controls">
				<form:input path="version" id="version" readOnly="true" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本编号：</label>
			<div class="controls">
				<form:input path="buildNumber" id="buildNumber" readOnly="true" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">包名：</label>
			<div class="controls">
				<form:input path="packageName" readOnly="true" id="packageName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新描述：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="web:webApkVersion:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>