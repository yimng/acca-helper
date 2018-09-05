<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>官方考试订单类管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style>
		.course1{
			color:green;
		}
		.course2{
			color:blue;
		}
		.course4{
			color:red;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0);">报考缴费审核</a></li>
	</ul>
	<ul class="nav nav-tabs">
		<c:forEach items="${webOrder.orderMaps}" var="item">
			<li <c:if test="${webOrder.orderStatus == item.key}">class="active"</c:if>><a href="${ctx}/web/order?orderStatus=${item.key}">${item.value}</a></li>
		</c:forEach>
	</ul><br/>
	<form:form id="searchForm" modelAttribute="webOrder" action="${ctx}/web/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="orderStatus" />
		<ul class="ul-form">
			<li><label>注册手机号:</label>
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>证件照</th>
				<th>身份信息</th>
				<th>注册手机号</th>
				<th>报考类型</th>
				<th>报考科目</th>
				<th>报考费用</th>
				<th>支付凭证</th>
				<th>状态</th>
				<th>报名时间</th>
				<shiro:hasPermission name="web:order:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" varStatus="index" var="order">
			<tr>
				<td><sys:imageupload input="image${index.index}" show="true" numbers="1" initData="${order.img1}" imgWidth="100" imgHeight="100"/></td>
				<td>
					姓名：${order.registerName}<br/>
					身份证号：${order.registerCardNumber}<br/>
					手机号：${order.registerPhone}<br/>
					注册邮箱：${order.registerEmail}<br/>
					ACCA学员号：${order.accaRegisterName}<br/>
					学校/单位：${order.org}
				</td>
				<td>${order.phone}</td>
				<td class="coursetype${order.examType}">${order.examTypeName}</td>
				<td>${order.courseNames}</td>
				<td>${order.amount}元</td>
				<td><sys:imageupload input="file${index.index}" show="true" numbers="1" initData="${order.img2}" imgWidth="100" imgHeight="100"/></td>
				<td>${order.orderStatusName}</td>
				<td>
					<fmt:formatDate value="${order.examSignupTime}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<shiro:hasPermission name="web:order:edit"><td>
    				<a href="${ctx}/web/order/detail?orderId=${order.orderId}">${order.operaName}</a>
    				<a href="${ctx}/web/order/detail?orderId=${order.orderId}">${order.operaName}</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>