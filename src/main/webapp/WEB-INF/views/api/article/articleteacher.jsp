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
}
</style>
</head>
<body>
	<div class="container-fluid" style="padding-left:10px;padding-right: 10px">
		<div class="row-fluid">
			<div class="box span12">
				<!-- <div class="box-header well"> -->
				<div style="position: relative;">
					<div class="span12" style="margin-top:-10px;margin-bottom: 10px">
						<div class="span2" style="border-radius:50%;overflow:hidden;">
							<!-- <img alt="" src="http://120.24.67.92:8585/acca/upload/image/1472183782399462033.png" style="width:100%" /> -->
							<c:if test="${article.teacher.headImg!=null}">
								<c:if test="${article.teacher.headImg.fileUrl!=null}">
									<img alt="" src="${article.teacher.headImg.fileUrl}" style="width:100%" />	
								</c:if>
								<c:if test="${article.teacher.headImg.fileUrl==null}">
									<img alt="" src="${ctxStatic}/images/acca_default_head.png" style="width:100%" />
								</c:if>
							</c:if>
							<c:if test="${article.teacher.headImg==null}">
								<img alt="" src="${ctxStatic}/images/acca_default_head.png" style="width:100%" />
							</c:if>
						</div>
						<div class="span10" style="margin-top:4px;">
							
							<c:if test="${article.teacher.accaLevel==1}">
								<div style="font-size:15px;color:#7b80a5">${article.teacher.chName} <img style="margin-left:14px;width:20%" alt="" src="${ctxStatic}/images/artcile_teacher_acca2.png" /></div>
							</c:if>
							<c:if test="${article.teacher.accaLevel==2}">
								<div style="font-size:15px;color:#7b80a5">${article.teacher.chName} <img style="margin-left:14px;width:20%" alt="" src="${ctxStatic}/images/artcile_teacher_acca.png" /></div>
							</c:if>
							<c:if test="${article.teacher.accaLevel==3}">
								<div style="font-size:15px;color:#7b80a5">${article.teacher.chName} <img style="margin-left:14px;width:20%" alt="" src="${ctxStatic}/images/artcile_teacher_fcca.png" /></div>
							</c:if>
						
							
							<div style="font-size:12px;margin-top:6px;color:#999">${article.teacher.position}</div>
						</div>
					</div>
				
				
					<div class="text-info " style="color: #323232;margin-top: 25px;margin-bottom: 10px;font-size:24px;font-weight: bold;">${article.title}</div>
					
					<div style="margin-top: 10px; margin-bottom: 5px;color:#999;overflow: hidden;">
						<!-- <div style="float: left;font-size: 13px;"><i class="icon-user"></i><span style="margin-left:5px">作者</span></div> -->
						<div style="float: left;margin-left:0px;font-size: 13px;"><i class="icon-time"></i><span style="margin-left:5px"><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/></span></div> 
						<div style="float: right;margin-left:10px;font-size: 13px;"><i class="icon-thumbs-up"></i><span style="margin-left:5px" id="iconPraiseNumId">${article.praiseNumStr}</span></div>
						<div style="float: right;margin-left:10px;font-size: 13px;"><i class="icon-eye-open"></i><span style="margin-left:5px">${article.viewNumStr}</span></div>
					</div>
					<div style="width: 100%;height: 1px;background-color: #eee;margin-top: 5px;margin-bottom: 10px;"></div>
					<div style="color:#666;font-size:16px;">
						 ${article.htmlContent }
					</div>

					
					<%@include file="/WEB-INF/views/api/article/inc_article_operate.jsp"%>

				</div>

				<div class="clearfix"></div>
				
			</div>

		</div>
	</div>

</body>
</html>