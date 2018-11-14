<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${article.pageTitle}</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
<style type="text/css">
body {
	color: #000;
	line-height: inherit;
	max-width: 800px;
	margin: 0 auto;
	word-wrap: break-word;
}
</style>
</head>
<body>

	<div class="container-fluid" style="padding-left:10px;padding-right: 10px">
		<div class="row-fluid" >
			<div class="box span12" >
				<!-- <div class="box-header well"> -->
				<div style="position: relative;">
					<div class="text-info " style="color: #323232;margin-top: 15px;margin-bottom: 10px;font-size:24px;font-weight: bold;">${article.title}</div>
					
					<div style="margin-top: 10px; margin-bottom: 5px;color:#999;overflow: hidden;">
						<div style="float: left;font-size: 13px;"><i class="icon-tag"></i><span style="margin-left:5px">${article.articleCategoryName}</span></div>
						<div style="float: left;margin-left:10px;font-size: 13px;"><i class="icon-time"></i><span style="margin-left:5px"><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/></span></div> 
						<div style="float: right;margin-left:10px;font-size: 13px;"><i class="icon-thumbs-up"></i><span style="margin-left:5px" id="iconPraiseNumId">${article.praiseNumStr}</span></div>
						<div style="float: right;margin-left:10px;font-size: 13px;"><i class="icon-eye-open"></i><span style="margin-left:5px" >${article.viewNumStr}</span></div>
					</div>
					<div class="article-sep-line"></div>
					
					<div style="color:#666;font-size:16px;word-wrap:break-word">
						${article.htmlContent} 
					</div>
					
					<%@include file="/WEB-INF/views/api/article/inc_article_operate.jsp"%>
					
					
				</div>

				<div class="clearfix"></div>
				
			</div>

		</div>
	</div>
	

</body>
</html>