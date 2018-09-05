<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>常见问题列表</title>
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
	<ul class="nav nav-tabs" style="margin-bottom: 60px;">
		<li class="active"><a href="javascript:void(0)">用户协议列表</a></li>
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>标题</th>
				<shiro:hasPermission name="web:protocol:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>借易贷用户协议</td>
				<shiro:hasPermission name="web:protocol:edit"><td>
					<a href="${ctx}/web/sys/protocolForm?type=1">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td>2</td>
				<td>手机授权协议</td>
				<shiro:hasPermission name="web:protocol:edit"><td>
					<a href="${ctx}/web/sys/protocolForm?type=2">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td>3</td>
				<td>淘宝授权协议</td>
				<shiro:hasPermission name="web:protocol:edit"><td>
					<a href="${ctx}/web/sys/protocolForm?type=3">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td>4</td>
				<td>京东授权协议</td>
				<shiro:hasPermission name="web:protocol:edit"><td>
					<a href="${ctx}/web/sys/protocolForm?type=4">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td>5</td>
				<td>信用卡邮箱授权协议</td>
				<shiro:hasPermission name="web:protocol:edit"><td>
					<a href="${ctx}/web/sys/protocolForm?type=5">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td>6</td>
				<td>学信网授权协议</td>
				<shiro:hasPermission name="web:protocol:edit"><td>
					<a href="${ctx}/web/sys/protocolForm?type=6">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</tbody>
	</table>
</body>
</html>