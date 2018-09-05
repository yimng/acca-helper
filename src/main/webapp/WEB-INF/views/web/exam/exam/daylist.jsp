<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#bt").click = function() {
				printdiv($("#div_print"));
			}
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
						max : 1000
					}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
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
		
		function printdiv(v) {
			printpage=$("#"+v)
			var newstr = printpage.html();
			winname = window.open('', "_blank",'');
			winname.document.title="<fmt:formatDate value="${webExam.examStartTime}" pattern="yyyy年MM月dd日"/>机考签到表";
			winname.document.body.innerHTML=newstr;
			winname.print(); 
			/* var oldstr = document.body.innerHTML;
			document.body.innerHTML = newstr;
			window.print();
			document.body.innerHTML = oldstr; */
			return false;
		}
	</script> 
	

</head>
<body>	
	<sys:message content="${message}"/>
	
	<form:form id="searchForm" modelAttribute="webExam" action="${ctx}/web/exam/daylist" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>考点：</label>
				<form:select path="examPlaceId" class="input-medium">
					<form:option label="请选择" value="" htmlEscape="false"/>
					<form:options items="${places}" itemLabel="examPlaceName" itemValue="examPlaceId" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>考试时间：</label>
					<input id="examStartTime" name="examStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${webExam.examStartTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input name="bt" type="button" id="bt" value="打印签到表" onClick="printdiv('div_print')" /> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	
	
	<div id="div_print"> 
	<style type="text/css">
td {
	border: 1px solid #000;
	text-align: center;
}
th.h1{
	border: 1px solid #000;
	font-family: 黑体;
	font-size: 24px;
	font-style: normal;
	line-height: 35px;
}
th{
	border: 1px solid #000;
	font-family: 黑体;
	font-size: 14px;
	font-style: normal;
	line-height: 20px;
}

</style>
	<c:forEach items="${examList}" varStatus="idx" var="exam">	
		<table width="1200" border="0" cellpadding="2" cellspacing="0">
		  <thead>
		  <tr height="35">
		    <th colspan="17" class="h1"><fmt:formatDate value="${exam.examStartTime}" pattern="yyyy年MM月dd日"/> ${exam.examPlaceName} 机考名单(${exam.usedSeats}/${exam.totalSeats})</th>
		  </tr>
		  </thead>
		  <tr bgcolor="#f0f0f0"  height="20">
		    <th colspan="3">基本信息</th>
		    <th colspan="6">学生信息</th>
		    <th colspan="8">考试信息</th>
		  </tr>
		  <tr  bgcolor="#f0f0f0"  height="40">
		    <th width="46">场次</th>
		    <th width="33">序号</th>
		    <th width="67">报考分部</th>
		    <th width="64">姓名</th>
		    <th width="93">ACCA学员号</th>
		    <th width="145">身份证号</th>
		    <th width="145">学校/单位</th>
		    <th width="91">手机</th>
		    <th width="67">是否为<br />
		    中博学员</th>
		    <th width="70">考试科目</th>
		    <th width="67">第几次<br />
		    参加考试</th>
		    <th width="36">成绩</th>
		    <th width="66">学习<br />
		    城市</th>
		    <th width="96">班型</th>
		    <th width="39">培训年份</th>
		    <th width="66">授课讲师</th>
		    <th width="90">考生签字</th>
		  </tr>
		<c:forEach items="${signups[idx.index]}" varStatus="s" var="sig">
		  <tr>
		  <c:if test="${s.count==1}">
		    <td rowspan="${exam.signupNum}">
		    <fmt:formatDate value="${exam.examStartTime}" pattern="HH:mm"/><br />
		    -<br />
		    <fmt:formatDate value="${exam.examEndTime}" pattern="HH:mm"/>
		    </td>
		  </c:if>
		    <td>${s.count} </td>
		    <td>${sig.examCityName}</td>
		    <td>${sig.registerName}</td>
		    <td>${sig.accaRegisterName}</td>
		    <td>${sig.registerCardNumber}&nbsp;</td>
		    <td>${sig.org}&nbsp;</td>
		    <td>${sig.registerPhone}&nbsp;</td>
		    <td> <c:if test="${sig.iszbg==3}">是</c:if></td>
		    <td>${sig.versionName}<c:if test="${sig.examCourse=='F4'}">${sig.examCourse}</c:if></td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		</c:forEach>		  
	</table>
	<br>
</c:forEach>	
</div>

</body>
</html>