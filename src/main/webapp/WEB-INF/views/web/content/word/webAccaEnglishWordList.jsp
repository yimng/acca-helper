<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ACCA财经词汇管理</title>
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
		<li class="active"><a href="${ctx}/englishword/webAccaEnglishWord/">ACCA财经词汇列表</a></li>
		<shiro:hasPermission name="englishword:webAccaEnglishWord:edit"><li><a href="${ctx}/englishword/webAccaEnglishWord/form">ACCA财经词汇添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="webAccaEnglishWord" action="${ctx}/englishword/webAccaEnglishWord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>单词：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>中文解释：</label>
				<form:input path="chineseName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>证书：</label>
				<form:input path="certificate" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>科目：</label>
				<form:input path="subject" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单词</th>
				<th>中文翻译</th>
				<shiro:hasPermission name="englishword:webAccaEnglishWord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="webAccaEnglishWord">
			<tr>
				<td>
					${webAccaEnglishWord.name}
				</td>
				<td>
					${webAccaEnglishWord.chineseName}
				</td>
				<td>
					${webAccaEnglishWord.certificate}
				</td>
				<td>
					${webAccaEnglishWord.subject}
				</td>
				<shiro:hasPermission name="englishword:webAccaEnglishWord:edit"><td>
    				<a href="${ctx}/englishword/webAccaEnglishWord/form?id=${webAccaEnglishWord.id}">修改</a>
					<a href="${ctx}/englishword/webAccaEnglishWord/delete?id=${webAccaEnglishWord.id}" onclick="return confirmx('确认要删除该ACCA财经词汇吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>