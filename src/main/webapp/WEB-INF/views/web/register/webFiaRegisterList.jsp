<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代注册管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/register/webAccaRegister/list?registerType=2&status=1">FIA注册审核</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="webAccaRegister" action="${ctx}/register/webAccaRegister/list?registerType=2&status=${webAccaRegister.status}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="nav nav-tabs">
			<c:forEach items="${countList}" var="count">
				<c:if test="${count.status == 1}">
					<li <c:if test="${webAccaRegister.status == 1}">class="active"</c:if>><a href="${ctx}/register/webAccaRegister/list?status=1&registerType=2"><span>待支付(${count.num})</span></a></li>
				</c:if>
				<c:if test="${count.status == 2}">
					<li <c:if test="${webAccaRegister.status == 2}">class="active"</c:if>><a href="${ctx}/register/webAccaRegister/list?status=2&registerType=2"><span>待审核(${count.num})</span></a></li>
				</c:if>
				<c:if test="${count.status == 3}">
					<li <c:if test="${webAccaRegister.status == 3}">class="active"</c:if>><a href="${ctx}/register/webAccaRegister/list?status=3&registerType=2"><span>审核通过(${count.num})</span></a></li>
				</c:if>
				<c:if test="${count.status == 4}">
					<li <c:if test="${webAccaRegister.status == 4}">class="active"</c:if>><a href="${ctx}/register/webAccaRegister/list?status=4&registerType=2"><span>审核不通过(${count.num})</span></a></li>
				</c:if>
				<c:if test="${count.status == 5}">
					<li <c:if test="${webAccaRegister.status == 5}">class="active"</c:if>><a href="${ctx}/register/webAccaRegister/list?status=5&registerType=2"><span>注册成功(${count.num})</span></a></li>
				</c:if>
			</c:forEach>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns">&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>证件类型</th>
				<th>证件号</th>
				<th>手机号</th>
				<th>邮箱</th>
				<th>距离最近城市</th>
				<th>注册时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webAccaRegister">
			<tr>
				<td>
					${webAccaRegister.name}
				</td>
				<td>
					<c:choose>
						<c:when test="${webAccaRegister.sex == 1}">
							男
						</c:when>
						<c:otherwise>
							女
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${webAccaRegister.cardType == 1}">
							身份证
						</c:when>
						<c:otherwise>
							护照
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					${webAccaRegister.cardNumber}
				</td>
				<td>
					${webAccaRegister.phone}
				</td>
				<td>
					${webAccaRegister.email}
				</td>
				<td>
					${webAccaRegister.cityName}
				</td>
				<td>
					<fmt:formatDate value="${webAccaRegister.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>
						<c:when test="${webAccaRegister.status == 2}">
							<a href="${ctx}/register/webAccaRegister/form?accaRegisterId=${webAccaRegister.accaRegisterId}">进行审核</a>
						</c:when>
						<c:when test="${webAccaRegister.status == 3}">
							<a href="${ctx}/register/webAccaRegister/form?accaRegisterId=${webAccaRegister.accaRegisterId}">录入注册信息</a>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/register/webAccaRegister/form?accaRegisterId=${webAccaRegister.accaRegisterId}">查看</a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>