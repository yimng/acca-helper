<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>ACCA财经词汇</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
<style type="text/css">
body {
	color: #000;
	line-height: inherit;
}
	.word-name {
		margin-top :50px;
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class = "word-name" align="left">
			<font size="5" color="#f33c3e">${word.name}</font>
		</div>
		<div class = "word-name" align="left">
			<font color="#999">中文翻译:</font>
			<div><font color="#333" style="word-wrap:break-word; overflow:hidden;">${word.chineseName}</font></div>
		</div>
		<div class = "word-name" align="left">
			<font color="#999">详细解释/例子:</font>
			<br/>
			<div>
				<font color="#333">
					<c:if test="${empty word.description}">暂无!</c:if>
					<c:if test="${!empty word.description}">${word.description}</c:if>
				</font>
			</div>
		</div>
	</div>
</body>
</html>