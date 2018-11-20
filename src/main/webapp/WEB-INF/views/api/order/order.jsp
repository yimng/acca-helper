
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>准考证</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="target-densitydpi=device-dpi,width=device-width,initial-scale=1.0, minimum-scale=0.1, maximum-scale=2.0, user-scalable=yes"/>

<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".mytable2 td").css("white-space","nowrap");
		$(".mytable2 td").css("font-size","12px");
		var a = $(".mytable2").width();
       // alert(a);
        $(".div2").css("width",a);
        $(".mytable2").css("width",a);
        //$("#imagetable").css("width",a);
        
		//$(".mytable td").css("white-space","nowrap");
		//print();
		var str ="";
		str =$("#imagetable").html();
		//str =encodeURIComponent(str);
		var userId= $("#userId").val();
		var userbean={"htmlSrc":str,"accaUserId":userId}; 
		var s = JSON.stringify(userbean);
		//alert(s);
		//alert(str);
		//alert(userId);
		$.ajax({
		    type: 'POST',
		    contentType : "application/json ; charset=utf-8",  
		    url:"${ctxapi}"+"/exam/examTicketImageUpload.do",
		    data: s,
		    
		    dataType: "json",
	    	success:function(data) {    
	            if(data.msg =="true" ){    
	                // view("修改成功！");    
	                alert("修改成功！");    
	                window.location.reload();    
	            }else{    
	                view(data.msg);    
	            }    
	         }, 
	         error : function() {    
	             // view("异常！");    
	             alert("异常！");    
	        }

		});
	});
	
	
         
</script>
<style type="text/css">
body {
	color: #000;
	line-height: inherit;
}

.mytbale{
    margin: 20px auto;
    font-size: 14px;
}
</style>
</head>

<body>
<input type="hidden" id="userId" value="${accaUserId}" />
<div class="mydiv" style="background: #fff;">
<h3 align="center"><font color="#000">ACCA机考准考证</font></h3>
<table class="mytable table table-bordered" style=" border-collapse:collapse">
    <tbody>
	 <tr>
            <td>ACCA student ID:</td>
            <td>${order.accaRegisterName}</td>
			<td rowspan="6" align="center"><img src="${order.cardImgUrl}" width="110" height="80"/></td>
        </tr>
        <tr>
            <td>Date of Birth:<br />DD/MM/YY:</td>
            <td>${birthday}</td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td>
                ${sex}
            </td>
        </tr>
        <tr>
            <td>Chinese Name:<br />(E.g. 张红)</td>
            <td>${order.registerName}</td>
        </tr>
        <tr>
            <td>Given Name:<br />(E.g Hong)</td>
            <td>${givenName}</td>
        </tr>
        <tr>
            <td>Surname:<br />(E.g Zhang)</td>
            <td>${surname}</td>
        </tr>
        <c:if test="${not empty order.signupList}">
        	<c:forEach items="${order.signupList}" var="sign">
        	<tr>
        		<td>机考科目：<br />${sign.examCourse}
                    <c:if test="${not empty sign.examVersion.examVersionName}">
                        (${sign.examVersion.examVersionName})
                    </c:if>
                </td>
        		<td colspan="2">
        			考试时间：${sign.showTime}<br/>
        			考试地点：${sign.examDetailAddress}
        		</td>
        	</tr>
			
			</c:forEach>
			
		</c:if>
        
        <tr>
            <td>ID/Passport No. <br />身份证/护照:</td>
            <td colspan="2">${order.registerCardNumber} </td>
        </tr>
        <tr>
            <td>Company/School <br />单位名:</td>
            <td colspan="2">${order.org}</td>
        </tr>
        <tr>
            <td>Tel/Mobile <br />电话:</td>
            <td colspan="2">${order.registerPhone}</td>
        </tr>
        <tr>
            <td>Email <br />邮箱:</td>
            <td colspan="2">${order.registerEmail}</td>
        </tr>
        <tr>
            <td>Signature <br />签名:</td>
            <td colspan="2"></td>
        </tr>
    </tbody>

</table>
<p>注：以上表格内信息必须如实填写，否则会影响您的报考是否成功。 </p>
<p> 注意事项：</p>
<p> 1：考试费用一旦缴付，如因考生自身原因缺考，作弃权处理，不须考虑退款事宜；</p>
<p>  2：具体考试时间安排请以邮件通知为准；</p>
<p> 3：考试须在考试开始前 30分钟到达考点，由监考老师对考生进行核查考生本人身份证、准考证；</p>
<p> 4：考生在考试开始前 15分钟经过监考老师批准方可进入考场。逾期不得再进入考场； </p>
<p>5：中博教育机考中心保留因不可抗力因素（如网络问题、停电等）调整机考时间或取消考试的权利。</p>
</div>

<div id="imagetable" style="visibility:hidden; position: absolute; top:0;display:none;">
<!-- <div id="imagetable" style=""> -->
<div class="div2"  style="padding:100 50;margin:50 25;">
<h3  class="title" align="center"><font size="16" color="#000">ACCA机考准考证</font></h3>
<table border="1" cellpadding="5" cellspacing="5" style="margin-bottom:10px;" class="mytable mytable2">
    <tbody>
        <tr>
            <td>ACCA student ID:</td>
            <td>${order.accaRegisterName}</td>
			<td rowspan="6" align="center"><img src="${order.cardImgUrl}" width="110" height="80"/></td>
        </tr>
        <tr>
            <td>Date of Birth:<br />DD/MM/YY:</td>
            <td>${birthday}</td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td>
                ${sex}
            </td>
        </tr>
        <tr>
            <td>Chinese Name:<br />(E.g. 张红)</td>
            <td>${order.registerName}</td>
        </tr>
        <tr>
            <td>Given Name:<br />(E.g Hong)</td>
            <td>${givenName}</td>
        </tr>
        <tr>
            <td>Surname:<br />(E.g Zhang)</td>
            <td>${surname}</td>
        </tr>
        <c:if test="${not empty order.signupList}">
        	<c:forEach items="${order.signupList}" var="sign">
        	<tr>
        		<td>机考科目：<br />${sign.examCourse}
                    <c:if test="${not empty sign.examVersion.examVersionName}">
                        (${sign.examVersion.examVersionName})
                    </c:if>
                </td>
        		<td colspan="2">
        			考试时间：${sign.showTime}<br/>
        			考试地点：${sign.examDetailAddress}
        		</td>
        	</tr>
			
			</c:forEach>
			
		</c:if>
        
        <tr>
            <td>ID/Passport No. <br />身份证/护照:</td>
            <td colspan="2">${order.registerCardNumber} </td>
        </tr>
        <tr>
            <td>Company/School <br />单位名:</td>
            <td colspan="2">${order.org}</td>
        </tr>
        <tr>
            <td>Tel/Mobile <br />电话:</td>
            <td colspan="2">${order.registerPhone}</td>
        </tr>
        <tr>
            <td>Email <br />邮箱:</td>
            <td colspan="2">${order.registerEmail}</td>
        </tr>
        <tr>
            <td>Signature <br />签名:</td>
            <td colspan="2"></td>
        </tr>
    </tbody>

</table>

<p>注：以上表格内信息必须如实填写，否则会影响您的报考是否成功。 </p>
<p> 注意事项：</p>
<p> 1：考试费用一旦缴付，如因考生自身原因缺考，作弃权处理，不须考虑退款事宜；</p>
<p>  2：具体考试时间安排请以邮件通知为准；</p>
<p> 3：考试须在考试开始前 30分钟到达考点，由监考老师对考生进行核查考生本人身份证、准考证；</p>
<p> 4：考生在考试开始前 15分钟经过监考老师批准方可进入考场。逾期不得再进入考场； </p>
<p>5：中博诚通机考中心保留因不可抗力因素（如网络问题、停电等）调整机考时间或取消考试的权利。</p>


</div>

</div>

</body>
</html>
