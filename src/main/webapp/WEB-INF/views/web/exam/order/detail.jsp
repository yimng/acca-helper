<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单详情页</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function refuse(){
			window.location.href = "${ctx}/web/order/refuse?orderId=${webOrder.orderId}";
		}
	</script>
	<script type="text/javascript">
		$(document).ready(
		function() {
			showDesc(1);
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
					flag:{
						required:true
					}
				},
				messages:{
					flag:{
						required:"请选择审核状态!"
					}
				},
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

		function showDesc(val){
			if (val == 0 || val == 2){
				$(".reasonDesc").show();
				$('html, body').animate({scrollTop: $(document).height()}, 'slow');
			} else {
				$(".reasonDesc").hide();
			}
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
		
		function changeExam(val){
			var examId = $("#signup"+val+" #examId").val();
			var courseId= $("#signup"+val+" input[name=examCourseIds]:checked").val();
         	var versionStr= $("#signup"+val+" input[name=examVersionStrs]:checked").val();	
			if(val == null || val == "" || val == "undefined"){
				alert("您还没有选择更新哪一场考试。");
				return false;
			}
			if(examId == null || examId == "" || examId == "undefined"){
				alert("您还没有选择更新哪一场考试。");
				return false;
			}
			if(courseId == null || courseId == "" || courseId == "undefined"){
				alert("您还没有选择考试科目。");
				return false;
			}
			if((versionStr == null || versionStr == "" || versionStr == "undefined")&&courseId!=4){
				alert("请选择对应的考试版本。");
				return false;
			}
			if(window.confirm("您确定要修改本次考试信息吗？")){
					$.ajax({
						url : "${ctx}/web/order/changeExam",
						data : {
							examSignupId:val,
							targetexamId:examId,
							courseId:courseId,
							versionStr:versionStr							
						},
						type: "post",
						success: function (msg) {
							//alert("考试延期或修改更新成功");
							alert(msg);
							window.location.href = "${ctx}/web/order/detail?orderId=${webOrder.orderId}";
						}
					});
			}else{	                 
	                 return false;
	        }
		}
		
		function getExamCourse(val,sid){
			if(val == null || val == "" || val == "undefined"){
				alert("没有匹配的科目可选择");
				return false;
			}else{
				$.ajax({
					url : "${ctx}/web/exam/getExamCourse",
					data : {examId:val},
					type: "post",
					success: function (msg) {
						var ih="";
						for(var c in msg){
							ih = ih + "<input name=\"examCourseIds\" type=\"radio\" value=\""
								+ msg[c].examCourseId +"\" onclick=\"checkcourse("+sid+");\" />" + msg[c].course;
								if(msg[c].examVersions!=null&&msg[c].examVersions!= ""){
									ih = ih + "&nbsp;(";
									for(var c1 in msg[c].examVersions){
										ih = ih + "<input name=\"examVersionStrs\" type=\"radio\" value=\""+
										msg[c].examCourseId + "," + 
										msg[c].examVersions[c1].examVersionId+","+
										msg[c].examVersions[c1].examVersionName + "\" onclick=\"checkversion(value,"+sid+");\" />" + 
										msg[c].examVersions[c1].examVersionName;
									}									
									ih = ih + "&nbsp;) <br />";
								}								
						}
						$("#courses"+sid).html(ih);
					}
				});
			}		
			
		}
		
		function checkversion(val,sid){		
			var a=val.substr(0,val.indexOf(','));
			
			$("#signup"+sid+" input[value=" + a +"]").attr('checked','true');			
		}
		
		function checkcourse(sid){
			//alert(o2s($("signup"+sid+" input[name=examVersionStrs]")));
			$("#signup"+sid+" input[name=examVersionStrs]").attr("checked",false);
		}
		
		
		function o2s(o){ 
		    var r=[]; 
		    if(typeof o=="string"){ 
		        return "\""+o.replace(/([\'\"\\])/g,"\\$1").replace(/(\n)/g,"\\n").replace(/(\r)/g,"\\r").replace(/(\t)/g,"\\t")+"\""; 
		    } 
		    if(typeof o=="object"){ 
		        if(!o.sort){ 
		            for(var i in o){ 
		                r.push(i+":"+obj2string(o[i])); 
		            } 
		            if(!!document.all&&!/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)){ 
		                r.push("toString:"+o.toString.toString()); 
		            } 
		            r="{"+r.join()+"}"; 
		        }else{ 
		            for(var i=0;i<o.length;i++){ 
		                r.push(obj2string(o[i])) 
		            } 
		            r="["+r.join()+"]"; 
		        }  
		        return r; 
		    }  
		    return o.toString(); 
		} 
		
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">订单详情页</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="webOrder" action="${ctx}/web/order/audit" class="form-horizontal">
		<sys:message content="${message}"/>
		<input type="hidden" name="orderId" value="${webOrder.orderId}"/>
		<c:if test="${webOrder.orderStatus == webOrder.checkSuccess or webOrder.orderStatus == webOrder.checkFail or webOrder.orderStatus == webOrder.checkSupplement}">
			<fieldset>
				<legend>审核记录</legend>
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>审核人</th>
							<th>审核时间</th>
							<th>审核结果</th>
							<th>原因</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								${webOrder.checkPersonName}
							</td>
							<td>
								<fmt:formatDate value="${webOrder.checkTime}" pattern="yyyy-MM-dd HH:mm"/>
							</td>
							<td>
								${webOrder.orderStatusName}
							</td>
							<td>
								<c:out value="${webOrder.checkReason}" escapeXml="false"  default="无" />
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</c:if>
		<fieldset>
			<legend>身份信息</legend>
			<div style="float: left;width: 40%;">
				<div class="control-group">
					<label class="control-label">姓名:</label>
					<div class="controls"><label class="lbl">${webOrder.registerName}</label></div>
				</div>
				<div class="control-group">
					<label class="control-label">手机号:</label>
					<div class="controls"><label class="lbl">${webOrder.registerPhone}</label></div>
				</div>
				<div class="control-group">
					<label class="control-label">ACCA学员号:</label>
					<div class="controls"><label class="lbl">${webOrder.accaRegisterName}</label></div>
				</div>
				<c:if test="${webOrder.examType != 1}">
					<div class="control-group">
						<label class="control-label">ACCA密码:</label>
						<div class="controls"><label class="lbl">${webOrder.accaRegisterPassword}</label></div>
					</div>
				</c:if>
				<div class="control-group">
					<label class="control-label">2寸白底彩色照片:</label>
					<div class="controls"><label class="lbl">
					<sys:imageupload input="image1" show="true" numbers="1" initData="${webOrder.img1}" imgWidth="100" imgHeight="100"/>
					</label></div>
				</div>
			</div>
			<div style="float: left;width: 60%;">
				<div class="control-group">
					<label class="control-label">身份证号:</label>
					<div class="controls"><label class="lbl">${webOrder.registerCardNumber}</label></div>
				</div>
				<div class="control-group">
					<label class="control-label">注册邮箱:</label>
					<div class="controls"><label class="lbl">${webOrder.registerEmail}</label></div>
				</div>
				<div class="control-group">
					<label class="control-label">学校/单位:</label>
					<div class="controls"><label class="lbl">${webOrder.org}</label></div>
				</div>
			</div>
		</fieldset>
		<c:if test="${webOrder.orderStatus == webOrder.uncheck or webOrder.orderStatus == webOrder.checkSuccess or webOrder.orderStatus == webOrder.checkFail or webOrder.orderStatus == webOrder.checkSupplement}">
			<fieldset>
				<legend>支付信息</legend>
				<div style="float: left;width: 40%;">
					<div class="control-group">
						<label class="control-label">需支付报考费用:</label>
						<div class="controls"><label class="lbl" style="width: 120px;height: 120px"><span style="width: 100px;height: 100px">${webOrder.amount}元</span></label></div>
					</div>
				</div>
				<div style="float: left;width: 60%;">
					<div class="control-group">
						<label class="control-label">支付凭证:</label>
						<div class="controls"><label class="lbl" style="width: 120px;height: 120px">
						<sys:imageupload input="image2" show="true" numbers="1" initData="${webOrder.img2}" imgWidth="100" imgHeight="100"/>
						</label></div>
					</div>
				</div>
			</fieldset>
		</c:if>

		<fieldset>
			<legend>账户信息</legend>
			<div style="float: left;width: 40%;">
				<div class="control-group">
					<label class="control-label">注册手机号:</label>
					<div class="controls"><label class="lbl">${webOrder.phone}</label></div>
				</div>
			</div>
		</fieldset>

		<fieldset>
			<legend>报考信息</legend>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>城市</th>
						<th>考点</th>
						<th>考试时间</th>
						<th>考试科目</th>
						<th>考试版本</th>
						<th>考试费用</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${webOrder.signups}" var="p">
					<tr>
						<td>
							${p.examCityName}
						</td>
						<td>
							${p.examPlaceName}
						</td>
						<td>
							${p.timeScope}
						</td>
						<td>
							${p.course}
						</td>
						<td>
							${p.versionName}
						</td>
						<td>
							${p.price}
						</td>						
					</tr>
					<c:if test="${webOrder.orderStatus == webOrder.checkSuccess&&webOrder.examType==1}">
							<tr id="examlist">
							<td colspan="6" align="right" id="signup${p.examSignupId}">
								<br><Strong>-------修改延期--------&nbsp;&nbsp;</Strong><br><br>
								<form:select path="examId" class="input-xxlarge required" onclick="getExamCourse(value,${p.examSignupId})"  htmlEscape="false">
									<form:option value="" label="选择考试"/>
									<c:forEach items="${exams}" var="ems">
										<form:option value="${ems.key}" label="${ems.value}"/>
									</c:forEach>							
								</form:select>
								<br /><br /><Strong>--------考试科目--------</Strong><br /><br />
								<div id="courses${p.examSignupId}">
									<br />
									考试科目及对应版本
								</div>
								<br />								
								<input type="button" class="btn" onclick="changeExam(${p.examSignupId});" value="确认修改延期考试" /><br />		
								<br />					
								</td>
							</tr>
					</c:if>
				</c:forEach>
				<tr>
					<td colspan="6" align="center">	
						<font color="red">各位同事，关于延期考试,修改考试科目和对应版本，由分部负责同事自主管理，根据各分部情况予以定夺。辛苦啦！</font>
					</td>
				</tr>
				</tbody>
			</table>
		</fieldset>
		<br />
		<c:if test="${webOrder.orderStatus == webOrder.uncheck}">
			<shiro:hasPermission name="web:order:edit">
			<fieldset>
				<legend>审核</legend>
				<div style="margin-left: 40%;">
					<font size="3">
						<input type="radio" value="1" name="flag" onclick="showDesc(value);" checked/> 通过
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="0" name="flag" onclick="showDesc(value);"/> 不通过
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="2" name="flag" onclick="showDesc(value);"/> 补传信息

					</font>
				</div>
				<br/>
				<div style="margin-left: 18%;display:inline-block;" class="reasonDesc">
					<label class="control-label">不通过原因:</label>
					<div class="controls">
						<form:textarea path="checkReason" htmlEscape="false" rows="6" maxlength="800" class="input-xxlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<br/>
				<div style="margin-left: 40%;">
					<input class="btn btn-primary"type="submit" value="确 认"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>
			</fieldset>
			</shiro:hasPermission>
		</c:if>
	</form:form>
	<br />
</body>
</html>