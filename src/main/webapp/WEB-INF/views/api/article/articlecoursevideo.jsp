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
.head-banner{
	position:relative;
}
.banner-btn{
	position:absolute;
	bottom:15px;
	right:15px;

}
.banner-btn input{
	width:100%;
	background:#fff;
}
</style>
</head>
<body>
	<div class="container-fluid" style="padding-left:10px;padding-right: 10px">
		<div class="row-fluid">
			<div class="box span12">
				<!-- <div class="box-header well"> -->
				<div style="position: relative;">
					<div style="margin-left:-10px;margin-right: -10px;" class="head-banner">
						<!-- <img alt="" src="http://120.24.67.92:8585/acca/upload/image/1472115067348240531.png" style="width:100%" />  -->
						<%-- <img alt="" src="${article.courseImage.fileUrl }" style="width:100%" /> --%>
						<%-- <script src="${article.courseLinkUrl}" type="text/javascript" width="100%"></script> --%>

						<script
							src="http://p.bokecc.com/player?vid=${article.courseLinkUrl}&siteid=D550E277598F7D23&autoStart=true&width=100%&height=250&playerid=015FEF5A2D3B93A8&playertype=2"
							type="text/javascript" ></script>

						<%-- <a style="display:block;position:absolute;top:calc(50% - 35px);left:calc(50% - 35px);" href="${article.courseLinkUrl}" ><img style="width:70px;height: 70px"  alt="" src="${ctxStatic}/images/play.png"></a> --%>
						
					</div>
				
				
					<div class="head-banner">
						<div  style="width:80%;color: #323232;margin-top: 15px;font-size:24px;font-weight: bold;">${article.title}</div>
						<div style="margin-top: 6px">
							<span>${article.courseTeacher}</span><span style="color:#999;margin-left:10px">${article.courseTeacherPosition}</span>
						</div>
						<c:if test="${article.courseStartTime !=null}">
							<c:if test="${article.courseEndTime !=null}">
								<div  style="margin-top: 6px;color:#999">
									<i class="icon-time"></i><span style="margin-left:5px"><fmt:formatDate value="${article.courseStartTime}" pattern="yyyy-MM-dd HH:mm"/><fmt:formatDate value="${article.courseEndTime}" pattern="-HH:mm"/></span>
								</div>
							</c:if>
						</c:if>
						
						<c:if test="${not empty article.courseAddress}">
							<div  style="margin-top: 6px;color:#999">
								<i class="icon-map-marker"></i><span style="margin-left:5px">${article.courseAddress }</span>
							</div>
						</c:if>
						
						<div style="position:absolute;top:0px;right:0px;border: 1px solid #f99d9e;color:#f99d9e;width:10%;text-align: center;height:15px;border-radius:3px;line-height: 15px;padding-top:2px">免费</div>
					</div>
					
					<div style="width: 100%;height: 1px;background-color: #eee;margin-top: 5px;margin-bottom: 10px;"></div>
					<div style="color:#666;font-size:16px;">
						 
						 ${article.courseContent }
					</div>


					<%@include file="/WEB-INF/views/api/article/inc_article_operate.jsp"%>


				</div>

				<div class="clearfix"></div>
				
			</div>

		</div>
	</div>

</body>
</html>