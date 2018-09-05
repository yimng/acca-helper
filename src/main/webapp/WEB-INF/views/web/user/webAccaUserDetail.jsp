<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代注册管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.identity-info {
			align-content : center;
			width: 90%;
			margin:30px auto ;
		}
		.identity-info th {
			width: 15%;
			height: auto;
		}
		.identity-info td {
			width: 30%;
			height: auto;
			text-align: center;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		//审核不通过
		function form(accaRegisterId){
			var iframeId = window.frameElement.id;
			top.$.jBox(
					'iframe:${ctx}/register/webAccaRegister/notPass?&iframeId=' + iframeId +"&status=4&registerType=1&accaRegisterId="+accaRegisterId,
					{title: '审核不通过',width: 650,height: 400,buttons: { '关闭': true }});
		}
		function chg(){
			window.location.href = "list?registerType=1&status=4";
			return false;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/user/webAccaUser/detail?id=${webAccaUser.accaUserId}">用户信息</a></li>
		<li><a href="${ctx}/user/mobileDeviceContact/userDeviceList?deviceId=${webAccaUser.deviceId}&accaUserId=${webAccaUser.accaUserId}">设备信息</a></li>
	</ul>
	<div class="identity-info">
		<table id="contentTable"  class="table table-striped table-bordered table-condensed">
				<thead><tr><th><h5>基本信息</h5></th></tr></thead>
				<tbody>
					<tr>
						<th>昵称</th>
						<td>${webAccaUser.nickname}</td>
						<th>手机号</th>
						<td>${webAccaUser.phone}</td>
					</tr>
					<tr>
						<th>GPS定位城市</th>
						<td>${webAccaUser.provinceName}${webAccaUser.cityName}</td>
						<th>当前考试科目</th>
						<td>${webAccaUser.course}</td>
					</tr>
					<tr>
						<th>用户身份</th>
						<td>
							<c:if test="${webAccaUser.identityType == 1}">在校生</c:if>
							<c:if test="${webAccaUser.identityType == 2}">毕业生</c:if>
							<c:if test="${webAccaUser.identityType == 3}">国外院校毕业生</c:if>
						</td>
						<th>用户类型</th>
						<td>
							<c:if test="${webAccaUser.type == 1}">普通用户</c:if>
							<c:if test="${webAccaUser.type == 2}">学习达人</c:if>
							<c:if test="${webAccaUser.type == 3}">名师</c:if>
						</td>
					</tr>
					<tr>
						<th>创建时间</th>
						<td><fmt:formatDate value="${webAccaUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<th>状态</th>
						<td>
							<c:if test="${webAccaUser.userStatus ==1}"><font color="green">正常</font></c:if>
							<c:if test="${webAccaUser.userStatus ==2}"><font color="red">冻结</font></c:if>
						</td>
					</tr>
					<tr>
						<th>头像</th>
						<td style="align:center;">
							<sys:imageupload input="headId" show="true" numbers="1" initData="${webAccaUser.imageStr}" imgWidth="200" imgHeight="100"/>
						</td>
						
						<td colspan="2"></td>
					</tr>
				</tbody>
		</table>
	</div>
		<div class="identity-info">
			<table id="contentTable"  class="table table-striped table-bordered table-condensed">
				<thead><tr><th ><h5>注册信息</h5></th></tr></thead>
				<tbody>
					<tr>
						<th>姓名</th>
						<td>${webAccaRegister.name}</td>
						<th>性别</th>
						<td>
							<c:choose>
								<c:when test="${webAccaRegister.sex == 1}">
									男
								</c:when>
								<c:when test="${webAccaRegister.sex == 2}">
									女
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>证件类型</th>
						<td>
							<c:choose>
								<c:when test="${webAccaRegister.cardType == 1}">
									身份证
								</c:when>
								<c:when test="${webAccaRegister.cardType == 2}">
									护照
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
							</c:choose>
						</td>
						<th>证件号</th>
						<td>${webAccaRegister.cardNumber}</td>
					</tr>
					<tr>
						<th>手机号</th>
						<td>${webAccaRegister.phone}</td>
						<th>邮箱</th>
						<td>${webAccaRegister.email}</td>
					</tr>
					<tr>
						<th>距离最近城市</th>
						<td>${webAccaRegister.cityName}</td>
						<th>当前身份</th>
						<td>
							<c:choose>
								<c:when test="${webAccaRegister.identityType == 1}">
									在校生
								</c:when>
								<c:when test="${webAccaRegister.identityType == 2}">
									毕业生
								</c:when>
								<c:when test="${webAccaRegister.identityType == 3}">
									国外院校毕业生
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<c:if test="${webAccaRegister.identityType == 1}">
						<tr>
							<th>学校</th>
							<td>${webAccaRegister.readingSchool}</td>
							<th>专业</th>
							<td>${webAccaRegister.readingMajor}</td>
						</tr>
						<tr>
							<th>年级</th>
							<td>${webAccaRegister.readingGrade}</td>
						</tr>
					</c:if>
					<tr>
						<th>两寸白底照</th>
						<td>
							<sys:imageupload input="whiteColorImgId" show="true" numbers="1" initData="${webAccaRegister.whiteColorImgStr}" imgWidth="200" imgHeight="100"/>
						</td>
						<th>身份证明原件</th>
						<td>
							<sys:imageupload input="identityCardOgImgId" show="true" numbers="1" initData="${webAccaRegister.identityCardOgImgStr}" imgWidth="200" imgHeight="100"/>
						</td>
					</tr>
					<tr>
						<th>身份证明翻译件</th>
						<td>
							<sys:imageupload input="identityCardTrImgId" show="true" numbers="1" initData="${webAccaRegister.identityCardTrImgStr}" imgWidth="200" imgHeight="100"/>
						</td>
						<c:choose>
							<c:when test="${webAccaRegister.identityType == 1}">
								<th>在读证明</th>
								<td>
									<sys:imageupload input="readingCertificateImgId" show="true" numbers="1" initData="${webAccaRegister.readingCertificateImgStr}" imgWidth="200" imgHeight="100"/>
								</td>
							</c:when>
							<c:otherwise>
								<th>毕业证书原件</th>
								<td>
									<sys:imageupload input="graduateCertifcateOgImgId" show="true" numbers="1" initData="${webAccaRegister.graduateCertifcateOgImgStr}" imgWidth="200" imgHeight="100"/>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
					<c:choose>
						<c:when test="${webAccaRegister.identityType == 1}">
							<th>在读证明翻译件</th>
							<td>
								<sys:imageupload input="readingCertificateTrImgId" show="true" numbers="1" initData="${webAccaRegister.readingCertificateTrImgStr}" imgWidth="200" imgHeight="100"/>
							</td>
							<th>成绩单原件</th>
							<td>
								<sys:imageupload input="examsResultOgImgId" show="true" numbers="1" initData="${webAccaRegister.examsResultOgImgStr}" imgWidth="200" imgHeight="100"/>
							</td>
						</c:when>
						<c:when test="${webAccaRegister.identityType == 2}">
							<th>毕业证书翻译件</th>
							<td>
								<sys:imageupload input="graduateCertifcateTrImgId" show="true" numbers="1" initData="${webAccaRegister.graduateCertifcateTrImgStr}" imgWidth="200" imgHeight="100"/>
							</td>
							<td colspan="2"></td>
						</c:when>
						<c:otherwise>
							<th>毕业证书翻译件</th>
							<td>
								<sys:imageupload input="graduateCertifcateTrImgId" show="true" numbers="1" initData="${webAccaRegister.graduateCertifcateTrImgStr}" imgWidth="200" imgHeight="100"/>
							</td>
							<th>成绩单原件</th>
							<td>
								<sys:imageupload input="examsResultOgImgId" show="true" numbers="1" initData="${webAccaRegister.examsResultOgImgStr}" imgWidth="200" imgHeight="100"/>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<c:if test="${webAccaRegister.identityType == 1}">
					<tr>
						<th>成绩单翻译件</th>
						<td>
							<sys:imageupload input="examsResultTrImgId" show="true" numbers="1" initData="${webAccaRegister.examsResultTrImgStr}" imgWidth="200" imgHeight="100"/>
						</td>
						<td colspan="2"></td>
					</tr>
				</c:if>
				<c:if test="${webAccaRegister.identityType == 3}">
					<tr style="display:none;">
						<th>成绩单翻译件</th>
						<td>
							<sys:imageupload input="examsResultTrImgId" show="true" numbers="1" initData="${webAccaRegister.examsResultTrImgStr}" imgWidth="200" imgHeight="100"/>
						</td>
						<td colspan="2"></td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
		<!-- <div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> -->
</body>
</html>