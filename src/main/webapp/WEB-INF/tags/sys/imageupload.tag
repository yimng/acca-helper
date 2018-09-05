<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="input" type="java.lang.String" required="true" description="对应字段name，和entity中字段名保持一致"%>
<%@ attribute name="numbers" type="java.lang.Integer"  required="false" description="允许的图片最大数目，默认为1"%>
<%@ attribute name="show" type="java.lang.Boolean"  required="true" description="编辑还是显示，true为显示（详情页使用），false为编辑（增加or修改页使用）"%>
<%@ attribute name="initData" type="java.lang.String"  required="false" description="初始化数据，如果为空表示增加操作，json格式数据"%>
<%@ attribute name="imgWidth" type="java.lang.Integer"  required="false" description="图片显示宽度，单位px"%>
<%@ attribute name="imgHeight" type="java.lang.Integer"  required="false" description="图片显示高度，单位px"%>
<c:if test="${empty imgWidth}">
	<c:set var="imgWidth" value="100" />
</c:if>
<c:if test="${empty imgHeight}">
	<c:set var="imgHeight" value="100" />
</c:if>
<!-- 自定义标签，用于图片上传和显示。 -->
<style>
.${input}ajaxuploadfile{
	margin: 5px;
}
.${input}ajaxuploadfile .fl{
	display: inline;
	float:left;
	border: 1px solid #e6e6e6;
	margin-right: 10px;
	
	position:relative;
}

.${input}ajaxuploadfile .fl img{
	width: ${imgWidth}px;
	height:${imgHeight}px;
}
.${input}ajaxuploadfile .fl:hover{
	border: 1px solid red;
	cursor: pointer;
}
.${input}ajaxuploadfile .fl .delete{
	position: absolute;
    right: 0;
    top: 0;
    z-index: 999;
    color: red;
    font-size: 16px;
    display: none;
}
.${input}ajaxuploadfile .btn{
	display: inline;
	float:left;
	border: 1px solid #e6e6e6;
	margin-right: 10px;
	margin-top:10px;
	display:none;
}
.${input}ajaxuploadfile .btn img{
	width: ${imgwidth}px;
	height:${imgHeight}px;
}

</style>

<input type="file" style="display:none" id="${input}File" onchange="${input}FileChange(this.id)" name="files"/>
<div class="${input}ajaxuploadfile" id="${input}ajaxuploadfile_place">
	<div  class="btn" id="${input}fileBtn" onclick="${input}FileBtnClick()">
		<img alt="" src="${ctxStatic}/images/image_upload.png" />
	</div>
</div>


<link href="${ctxStatic}/jquery-jbox/Source/jBox.css" rel="stylesheet">
<link href="${ctxStatic}/jquery-jbox/Source/themes/ModalBorder.css" rel="stylesheet">
<script src="${ctxStatic}/jquery-jbox/Source/jBox.min.js"></script>


<script type="text/javascript">

	
	var ${input}numbers = ${numbers};
	var ${input}initData = eval(${initData});
	//var ${input}initData = ${initData};
	var ${input}show = ${show};
	if(!${input}numbers){
		${input}numbers = 1;
	}
	
	function ${input}initPreview(){
		var html='';
		if(${input}numbers==1){
			html = ${input}divInitShow(${input}initData);
		}else{
			for(var i=0;i<${input}initData.length;i++){
				html += ${input}divInitShow(${input}initData);
			}
		}
		$("#${input}fileBtn").before(html);
		${input}checkFileBtnShow();
	}
	function ${input}divInitShow(data){
		if(!data){
			return '';
		}
		var editFlag = '';
		if(!${input}show){
			editFlag = 'onmouseover="${input}delImgShow(this)" onmouseout="${input}delImgHide(this)"';
		}
		//${input}show?'':'onmouseover="${input}delImgShow(this)" onmouseout="${input}delImgHide(this)"'
		var html = 	 '<div class="fl" '+editFlag+'>';
		if(!${input}show){
			html+=    '<input type="hidden" id="${input}" name="${input}"  value="'+data.id+'"/>';
		}
					
		html	+=    '<a href="';
		html    +=    data.fileUrl;
		html    +=      '" data-jbox-image="gallery${input}"><img   src="';
		html    +=    data.fileUrl;
		//html += 'http://pic10.nipic.com/20100927/2457331_105358511000_2.jpg';
		html    +=    '" /></a>';
		html	+=     '<div class="delete" onclick="${input}delImg(this)"><i  class="icon-remove"></i></div>';
		html	+=  '</div>';
		//alert(html);
		return html;
	}
	
	function ${input}FileBtnClick(){
		${input}UploadPicClick("${input}File");
	}
	
	//上传按钮点击事件函数，用于上传图片使用
	function ${input}UploadPicClick(fileId){
		$("#"+fileId).click();
		return ;
	}
	
	function ${input}FileChange(fileId){
		var fileValue = $("#"+fileId).val();
		
		if(!fileValue){
			return;
		}
		
		${input}AjaxFileUpload(fileId); 
	}
	
	function ${input}AjaxFileUpload(fileId){
		//alert(fileId);
		${input}showLoadingMask("文件上传中...");
		$.ajaxFileUpload({
			url:"${ctxapi}"+"/files/uploadFiles.do",
			secureuri: false,
			fileElementId:fileId,
			dataType:"json",
			beforeSend: function() {  
	               //alert("bbbb");  
	           },
			success:function(data, status){
				if(data.respCode==0){
					//alert("ccccc");
					/* var html = '<div  class="fl">'
								+'<input type="hidden" name="${input}" value=""/>'
								+'<img alt="" src="http://pic002.cnblogs.com/images/2011/267998/2011122517261812.png" />'
								+'</div>'; */
					var html = ${input}divInitShow(data.fileList[0]);
					$("#${input}fileBtn").before(html);
					${input}checkFileBtnShow();
					new jBox('Image');
					
				}else{
					//alert("cccccdddd");
					alert(data.respDesc);
					$("#"+fileId).val("");
				}
			},
			complete: function() {  
				//alert("complete");  
				${input}closeLoadingMask();
				$("#"+fileId).val("");
	           },
			error:function(data, status, e){
				alert(data);
				alert(e); 
				$("#"+fileId).val("");
				${input}closeLoadingMask();
			},
			//type:"POST"
		});
		
	}
	
	function ${input}delImgShow(obj){
		$(obj).find("div.delete").show();
	}

	function ${input}delImgHide(obj){
		$(obj).find("div.delete").hide();
	}
	
	function ${input}delImg(obj){
		$(obj).parent().remove();
		${input}checkFileBtnShow();
	}
	
	function ${input}checkFileBtnShow(){
		if(${input}show){
			$("#${input}fileBtn").hide();
			return;
		}
		if($("#${input}ajaxuploadfile_place .fl img").length>=${input}numbers){
			$("#${input}fileBtn").hide();
		}else{
			$("#${input}fileBtn").show();
		}
	}
	
	${input}initPreview();
	
	
	
	
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


<script type="text/javascript">
	new jBox('Image');
</script>