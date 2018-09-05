<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>中博&财萃学员审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function(){
				$("a.tooltip1111").each(function() {
					var a = $(this);
					a.attr("datatheme", a.attr('title')) ;
					a.removeAttr('title');
				});
				$("a.tooltip1111").mouseover(function(e) {			
					$("#tooltip").html($(this).attr("datatheme"));
					var lx= $(this).offset().left - $("#tooltip").width() -20;
			        var ly=  $(this).offset().top - $("#tooltip").height()/2;			        
					$("#tooltip")
			                .css({
			                	"position":"absolute",
			                	"display":"block",	
			                    "top": ly + "px",
			                    "left": lx + "px"			                   
			                });
			     }).mouseout(function() {
			    	 $("#tooltip")
		                .css({
		                	"display":"none"		                   
		                }).html("");
			     });

		});
		function anther(user,v){
			var s=""; 
			if(v==3){
				s="您确认该用户通过学员审核吗？";				
			}else{
				s="您确认该用户不通过学员审核吗？";
			}
			if(window.confirm(s)){
				$.ajax({
					url : "${ctx}/user/webAccaUser/authorUser",
					data : {
						id:user,
						iszbg:v
					},
					type: "GET",
					success: function (msg) {
						//alert("考试延期或修改更新成功");
						$("#user"+user).remove();
					}
				});
			}else{	                 
		         return false;
		    }
			
		}
		
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
	<style type="text/css">
	#tooltip{		
		width:25%;
		background-color:rgba(255,255,255,1);
		border:2px solid #999;
		padding:8px;
	}
	a.cbtn{
	    border: 1px solid #DDD;
	}
	a.cbtn:hover{
    	background: #ECECEC;
	}
	.cbtn{
		display: block;
	    padding: 2px;
	    text-align: center;
	    margin: 2px;
	}
	.ck {
	    height: 40px;
	    background: #ECECEC;
	    line-height: 20px;
	    padding: 5px;
	    width: 80px;
	}
	a.tooltip1111 {
	    display: block;
	    background: ghostwhite;
	    line-height: 30px;
	    text-align: center;
	    text-decoration: none;
	    cursor: default;
	}
	a.tooltip1111 hover{
    	background: #999;
    	color:white;
	}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/user/webAccaUser/author">注册用户列表</a></li>
		<%-- <shiro:hasPermission name="user:webAccaUser:edit"><li><a href="${ctx}/user/webAccaUser/form">accaUser模块添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="webAccaUser" action="${ctx}/user/webAccaUser/author" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="iszbg" />
		<ul class="ul-form">	
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>城市：</label>
				<form:input path="cityName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>昵称</th>
				<th>注册姓名</th>
				<th>手机号</th>
				<th>身份证号</th>
				<th>所属分部/距离最近城市</th>
				<th>用户身份</th>
				<th>课程类型</th>
				<th>学校/单位</th>				
				<th>年级</th>
				<th>财萃课程订单</th>
				<shiro:hasPermission name="user:webAccaUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webAccaUser">
			<tr id="user${webAccaUser.accaUserId}">
			<td>${webAccaUser.nickname}</td>
			<td>${webAccaUser.registerName}</td>
			<td>${webAccaUser.phone}</td>
			<td>${webAccaUser.registerCardNumber}</td>
			<td>${webAccaUser.provinceName}${webAccaUser.cityName}</td>
			<td>
				<c:if test="${webAccaUser.identityType == 1}">在校生</c:if>
				<c:if test="${webAccaUser.identityType == 2}">毕业生</c:if>
				<c:if test="${webAccaUser.identityType == 3}">国外院校毕业生</c:if>
			</td>
			<td>${webAccaUser.classStyle}</td>			
			<td>${webAccaUser.org}</td>			
			<td>${webAccaUser.grade}</td>			
			<td><a class="tooltip1111"  title="${webAccaUser.course}" ><div class="ck">${webAccaUser.registerName}<br>财萃课程参考</div></a></td>
				<shiro:hasPermission name="user:webAccaUser:edit">				
				<td>
					<c:if test="${webAccaUser.iszbg==3}"><span  class="cbtn" >已审核</span></c:if>
					<c:if test="${webAccaUser.iszbg==1}"><a class="cbtn" href="#" onclick="anther('${webAccaUser.accaUserId}',3)">通过审核</a></c:if>
					<a class="cbtn" href="${ctx}/user/webAccaUser/aurhorDetail?id=${webAccaUser.accaUserId}">查看详细信息</a>
					<a class="cbtn" href="#" onclick="anther('${webAccaUser.accaUserId}',2)"">不通过审核</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div id='tooltip' style="display:none;"></div>
</body>
</html>