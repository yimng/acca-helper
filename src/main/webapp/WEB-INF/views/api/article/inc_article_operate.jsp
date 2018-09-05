<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<style>
.article-sep-line {
	width: 100%;
	height: 1px;
	background-color: #eee;
	margin-top: 5px;
	margin-bottom: 10px;
}

.article-md {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 0px;
}

.article-md-item {
	display: inline-block;
	text-align: center;
}

.article-md-img-praise {
	background: url("${ctxStatic}/images/article_praise.png") no-repeat;
	width: 50px;
	height: 50px;
	background-size: 50px 50px;
	margin: 0 auto;
}

.article-md-img-praised {
	background: url("${ctxStatic}/images/article_praised.png") no-repeat;
	width: 50px;
	height: 50px;
	background-size: 50px 50px;
	margin: 0 auto;
}

.article-md-img-comment {
	background: url("${ctxStatic}/images/article_comment.png") no-repeat;
	width: 50px;
	height: 50px;
	background-size: 50px 50px;
	margin: 0 auto;
}

.article-md-img-tel {
	background: url("${ctxStatic}/images/article_tel.png") no-repeat;
	width: 50px;
	height: 50px;
	background-size: 50px 50px;
	margin: 0 auto;
}

.article-md-btn-text {
	margin-top: 5px;
	color: #999;
}

.article-md-btn-text-ed {
	margin-top: 5px;
	color: #f33c3e;
}

.article-md-item a {
	text-decoration: none;
}

.article-ct {
	border-top: 1px solid #eee;
	height: 60px;
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	background-color: white
}

.article-ct-item {
	display: inline-block;
	text-align: center;
}

.article-ct-btn-ct {
	height: 40px;
	background-color: #f7f7f7;
	margin-top: 10px;
	margin-left: 10px;
	line-height: 40px;
	border-radius: 3px;
	color: #999;
	padding-left: 10px
}

.article-ct-img-ct {
	background: url("${ctxStatic}/images/article_collect.png") no-repeat;
	width: 30px;
	height: 30px;
	background-size: 30px 30px;
	margin: 0 auto;
}

.article-ct-img-cted {
	background: url("${ctxStatic}/images/article_collected.png") no-repeat;
	width: 30px;
	height: 30px;
	background-size: 30px 30px;
	margin: 0 auto;
}

.article-ct-btn-text {
	margin-top: 0px;
	color: #999;
}

.article-ct-in {
	border-top: 1px solid #eee;
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	background-color: white;
	height: 130px;
	background-color: #eee
}

.article-ct-in-input {
	height: 80px;
	border: 1px solid #eee;
	background-color: white;
	border-radius: 5px;
	margin: 5px 10px;
	overflow: auto;
	font-size: 13px;
	padding: 2px;
	user-modify: read-write-plaintext-only;
	-webkit-user-modify: read-write-plaintext-only;
}

.article-ct-in-btn {
	float: right;
	margin-right: 10px;
	margin-bottom: 5px;
	display: inline-block;
	height: 20px;
	line-height: 20px;
	color: white;
	background-color: #f33c3e;
	padding: 5px 10px 5px;
	border-radius: 3px
}

.article-ct-in-btn-close {
	float: left;
	margin-left: 10px;
	margin-bottom: 5px;
	display: inline-block;
	height: 18px;
	line-height: 18px;
	padding: 5px 10px 5px;
	border-radius: 3px;
	border: 1px solid #f33c3e;
	color: #f33c3e
}

.article-ct-in-input:empty:before {
	content: attr(placeholder);
	font-size: 16px;
	color: #cacacc;
}

.article-ct-in-input:focus {
	content: none;
}

.article-ft-head-img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	overflow: hidden;
}

.article-ft-p-name {
	color: #747c9f;
	font-size: 15px;
	font-weight: bold;
}

.article-ft-content {
	color: #333;
	font-size: 15px;
	margin-top: 5px;
	word-wrap: break-word;
}

.article-ft-content-no {
	width: 100%;
	text-align: center;
	height: 60px;
	line-height: 60px;
	font-size: 15px;
	color: #999;
	display: none
}

.article-ft-sub {
	background-color: #eee;
	margin-top: 5px;
	padding: 5px;
	color: #666
}

.article-ft-sub-p-name {
	color: #747c9f;
	font-size: 13px;
}

.article-ft-sub-content {
	margin-top: 5px;
	word-wrap: break-word;
}

.article-ft-foot {
	margin-top: 5px;
	color: #999;
	font-size: 15px;
	overflow: hidden;
}

.article-ft-foot-time {
	float: left;
	height: 25px;
	line-height: 25px
}

.article-ft-foot-answer {
	background: url("${ctxStatic}/images/article_answer.png") no-repeat;
	background-size: 25px 25px;
	margin: 0 auto;
	float: right;
	padding-left: 25px;
	height: 25px;
	line-height: 25px;
	margin-right: 10px;
	padding-top: 2px
}

/**
 *
 * Pull down styles
 *
 */
#pullDown, #pullUp {
	background: #fff;
	height: 40px;
	line-height: 40px;
	padding: 5px 10px;
	/* border-bottom:1px solid #ccc; */
	font-weight: bold;
	font-size: 14px;
	color: #888;
}

#pullDown .pullDownIcon, #pullUp .pullUpIcon {
	display: block;
	float: left;
	width: 40px;
	height: 40px;
	background: url("${ctxStatic}/images/pull-icon.png") 0 0 no-repeat;
	-webkit-background-size: 40px 80px;
	background-size: 40px 80px;
	-webkit-transition-property: -webkit-transform;
	-webkit-transition-duration: 250ms;
}

#pullDown .pullDownIcon {
	-webkit-transform: rotate(0deg) translateZ(0);
}

#pullUp .pullUpIcon {
	-webkit-transform: rotate(-180deg) translateZ(0);
}

#pullDown.flip .pullDownIcon {
	-webkit-transform: rotate(-180deg) translateZ(0);
}

#pullUp.flip .pullUpIcon {
	-webkit-transform: rotate(0deg) translateZ(0);
}

#pullDown.loading .pullDownIcon, #pullUp.loading .pullUpIcon {
	background-position: 0 100%;
	-webkit-transform: rotate(0deg) translateZ(0);
	-webkit-transition-duration: 0ms;
	-webkit-animation-name: loading;
	-webkit-animation-duration: 2s;
	-webkit-animation-iteration-count: infinite;
	-webkit-animation-timing-function: linear;
}

@
-webkit-keyframes loading {from { -webkit-transform:rotate(0deg)translateZ(0);
	
}

to {
	-webkit-transform: rotate(360deg) translateZ(0);
}
}
</style>


<script type="text/javascript">
	var total_startIndex = 0;
	var total_pageSize = 10;

	var isPageEnd = false;

	var isAddMore = true;

	var u = navigator.userAgent, app = navigator.appVersion;
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

	/* var widthW = window.innerWidth;
	var heightW = window.innerHeight; */

	$(document)
			.ready(
					function() {

						$("#pullUp").show();
						getCommentListFromServer(total_startIndex);
						//$("#pullUp").hide();
						if (isPageEnd) {
							$("#pullUp").hide();
						}

						$(window)
								.scroll(
										function() {
											//alert($(document).scrollTop()+"...."+$(document).height()+"...."+$(window).height());   
											if (isAddMore) {
												if ($(document).scrollTop() >= $(
														document).height()
														- $(window).height()) {
													//alert("滚动条已经到达底部为" + $(document).scrollTop());
													if (!isPageEnd) {
														$("#pullUp").show();
														//alert("total_startIndex:"+total_startIndex);
														getCommentListFromServer(total_startIndex);
														if (isPageEnd) {
															$("#pullUp").hide();
														}
													}

												}
											}

										});

						//$(window).on('scroll', scrollA);

						//$(window).off('scroll', scrollA);

					});

	//获取用户useriD
	function getAccaUserId() {
		var accaUserId = $("#accaUserId").val();
		return accaUserId;
	}
	function getArticleId() {
		var articleId = $("#articleId").val();
		return articleId;
	}

	function checkAppLogin() {
		var message = {
			'message' : 'login'
		};
		//alert(window.webkit.messageHandlers);
		//alert(AndroidJSI);
		if (typeof (AndroidJSI) != "undefined") {
			//alert("AndroidJSI");
			AndroidJSI.login();
		} else {

			window.webkit.messageHandlers.observe.postMessage(message);
		}
		return;
	}

	//***********************************************************************************************
	//跳转到下载app页面
	function appdown() {
		//window.open("http://a.app.qq.com/o/simple.jsp?pkgname=com.chinaife.acca");
		window.open("${ctxStatic}/appfile/share/index.html");
		return;
	}
	//点赞或取消点赞
	function praise() {
		var accaUserId = getAccaUserId();
		var articleId = getArticleId();
		if (accaUserId == 0) {
			//confirmself("ACCA Helper是首款中文ACCA学习工具，帮助ACCA学员安排ACCA学习规划，提供学生注册、报名机考和笔考的服务。是否确定下载?",'下载ACCA Helper手机客户端',appdown);
			appdown();
			return;
		}

		if (accaUserId == -1) {
			checkAppLogin();
			return;

		}
		if (!articleId || articleId == 0) {
			freetekToast("参数错误");
			return;
		}

		var articlePraiseFlagId = $("#articlePraiseFlagId").val();

		var requestData = {
			accaUserId : accaUserId,
			articleId : articleId,
			praiseType : articlePraiseFlagId
		}

		$.ajax({
			url : "${ctxapi}/article/praise.do",
			data : requestData,
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.respCode == 0) {
					//alert("提交成功!");
					if (articlePraiseFlagId == 1) {
						changePraiseShowState(false, data.obj);
					} else {
						changePraiseShowState(true, data.obj);
					}

				} else {
					freetekToast(data.respDesc);
				}
			},
			"error" : function() {
				freetekToast("加载失败，请刷新页面！");
			}
		});

	}

	//改变点赞相关的状态  flag=true,表示点赞后状态 ;flag=false,笔试取消点赞后的状态
	//praiseNum,点赞或取消点赞后的点赞数目
	function changePraiseShowState(flag, praiseNum) {
		if (flag) {
			$("#articlePraiseImgId").removeClass();
			$("#articlePraiseImgId").addClass("article-md-img-praised");
			$("#articlePraiseTextId").text("已赞");
			$("#articlePraiseTextAllId").css("color", "#fa6b5b");
			$("#articlePraiseFlagId").val(1);
			$("#articlePraiseNumId").text(praiseNum);
			$("#iconPraiseNumId").text(praiseNum);
		} else {
			$("#articlePraiseImgId").removeClass();
			$("#articlePraiseImgId").addClass("article-md-img-praise");
			$("#articlePraiseTextId").text("点赞");
			$("#articlePraiseTextAllId").css("color", "#999");
			$("#articlePraiseFlagId").val(2);
			$("#articlePraiseNumId").text(praiseNum);
			$("#iconPraiseNumId").text(praiseNum);
		}
	}

	//***********************************************************************************************
	//收藏
	function collect() {
		var accaUserId = getAccaUserId();
		var articleId = getArticleId();
		if (accaUserId == 0) {
			//confirmself("ACCA Helper是首款中文ACCA学习工具，帮助ACCA学员安排ACCA学习规划，提供学生注册、报名机考和笔考的服务。是否确定下载?",'下载ACCA Helper手机客户端',appdown);
			appdown();
			return;
		}
		if (accaUserId == -1) {
			checkAppLogin();
			return;

		}
		if (!articleId || articleId == 0) {
			freetekToast("参数错误");
			return;
		}

		var articleCollectFlagId = $("#articleCollectFlagId").val();

		var requestData = {
			accaUserId : accaUserId,
			articleId : articleId,
			collectType : articleCollectFlagId
		}

		$.ajax({
			url : "${ctxapi}/article/collect.do",
			data : requestData,
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.respCode == 0) {
					//alert("提交成功!");
					if (articleCollectFlagId == 1) {
						changeCollectShowState(false);
					} else {
						changeCollectShowState(true);
					}
				} else {
					freetekToast(data.respDesc);
				}
			},
			"error" : function() {
				freetekToast("加载失败，请刷新页面！");
			}
		});

	}

	//改变点赞相关的状态  flag=true,表示点赞后状态 ;flag=false,笔试取消点赞后的状态
	function changeCollectShowState(flag) {
		if (flag) {
			$("#articleCollectImgId").removeClass();
			$("#articleCollectImgId").addClass("article-ct-img-cted");
			$("#articleCollectTextId").text("已收藏");
			$("#articleCollectTextId").css("color", "#f33c3e");
			$("#articleCollectFlagId").val(1);
		} else {
			$("#articleCollectImgId").removeClass();
			$("#articleCollectImgId").addClass("article-ct-img-ct");
			$("#articleCollectTextId").text("收藏");
			$("#articleCollectTextId").css("color", "#999");
			$("#articleCollectFlagId").val(2);
		}
	}

	//***********************************************************************************************			

	function scrollBottom() {

		//var top = $("#praiseId").offset().top;
		//alert(top + "   " + $('body').height());
		var top = $('body').height();
		//alert($('body').height()+"..."+$(document).height()+"..."+$(window).height());
		//window.scrollTo(0, $('body').height());

		//alert($('body').height()+"..."+$(document).height());

		//var top1 = $(document).height() - $(window).height()-300;

		//alert(top1+"....."+$(document).height()+" ... "+$(window).height());

		//var top2 = $("#articleCommentInputDivId").offset.top;
		//$("#articleCommentInputId").css('position','absolute');
		//$("#articleCommentInputId").css('bottom',0);
		//alert($("#articleCommentInputId").css('bottom'));
		//alert($("#articleCommentInputDivId").offset().top);	

		//var top = $(document).scrollTop();

		window.scrollTo(0, top);

		/* var bottomY = $("#articleCommentInputId").offset().top-$("body").scrollTop()+$("#articleCommentInputId").height;
		var isNotInView = false;
		if (window.innerHeight - bottom < 0) {
		    isNotInView =  true;
		}else{
			isNotInView = false;        	
		}
		
		if(isNotInView){
			var bottomAdjust;
			if (widthW != 750) {
				bottomAdjust = (heightW - window.innerHeight - 88) + 'px';
		    }else {
		        // 'iphone 6 6s, 需要额外减去键盘高度432(见下图), 还算有良心, 高度和原生保持一致')
		        bottomAdjust = (heightW - window.innerHeight - 88 - 432) + 'px';
		    }
			$("#articleCommentInputId").css('bottom',bottomAdjust);	
		} */

		//window.scrollTo(0, top);
	}

	function inputFocus() {
		//alert(app);
		//var t=setTimeout("alert('5 seconds!')",5000);
		//alert("aaa");
		//$("#articleCommentInputId").css("top","0px");
		if (isiOS) {

			if ($('body').height() >= $(document).height()) {
				$("#articleCommentInputId").css('position', 'absolute');
				$("#articleCommentInputId").css('bottom', 0);
				$("#articleCommentInputId").width($('body').width());
				$("#articleCommentInputId").css('margin-left', -10);
				//$("#articleCommentInputId").css('marginRight',-10);
			}

			window.setTimeout('scrollBottom()', 600);
		}
		//$("#articleCommentInputId").css("left","0px");
	}

	function inputClick() {
		$("#articleCommentInputDivId").focus();
	}

	function showOrHideCommentInput(flag, userId, placeStr) {
		var accaUserId = getAccaUserId();
		var articleId = getArticleId();
		if (accaUserId == 0) {
			//confirmself("ACCA Helper是首款中文ACCA学习工具，帮助ACCA学员安排ACCA学习规划，提供学生注册、报名机考和笔考的服务。是否确定下载?",'下载ACCA Helper手机客户端',appdown);
			appdown();
			return;
		}
		if (accaUserId == -1) {
			checkAppLogin();
			return;

		}
		if (articleId == 0) {
			freetekToast("参数错误");
			return;
		}
		if (flag) {

			$("#articleCommentInputDivId").attr("placeholder", placeStr);
			$("#articleCommnetId").hide();
			$("#blank60Id").hide();
			isAddMore = false;
			$("#articleCommentInputId").show();
			$("#zhezhao").width($('body').width());
			$("#zhezhao").show();
			$("#articleCommentUserId").val(userId);
			$("#articleCommentInputDivId").focus();
		} else {
			isAddMore = true;
			$("#articleCommnetId").show();
			$("#blank60Id").show();
			$("#articleCommentInputId").hide();
			$("#zhezhao").hide();
			$("#articleCommentUserId").val(0);
			$("#articleCommentInputDivId").text('');
		}
	}

	function getCommentListFromServer(startIndex, pageSize) {

		var accaUserId = getAccaUserId();
		var articleId = getArticleId();
		if (!articleId || articleId == 0) {
			freetekToast("参数错误");
			return;
		}

		var requestData = {
			articleId : articleId,
			startIndex : startIndex,
			pageSize : total_pageSize
		}

		$.ajax({
			url : "${ctxapi}/article/getCommentList.do",
			data : requestData,
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.respCode == 0) {

					var len = data.list.length;

					if (startIndex == 0) {
						$("#articleCommentListNodeId").html('');
						if (len == 0) {
							$("#pullUp").hide();
							$("#articleCommentNoId").show();

						}

					}

					for (var i = 0; i < data.list.length; i++) {
						//alert(data.list[i].content);
						$("#articleCommentListNodeId").append(
								getCommentNoeItem(data.list[i]));
					}

					if (len < total_pageSize) {
						isPageEnd = true;
					} else {
						total_startIndex += total_pageSize;
					}

				} else {
					alert(data.respDesc);
				}
			},
			"error" : function() {
				freetekToast("加载失败，请刷新页面！");
			}
		});
	}

	function getCommentNoeItem(data) {
		if (!data) {
			return '';
		}
		var html = '';

		html += '<div class="article-ft-item">';
		html += '	<div class="row-fluid">';
		html += '		<div class="box span12">';
		html += '			<div class="span2">';
		html += '				<div class="article-ft-head-img">';

		if (data.headImgUrl) {
			html += '					<img alt="" src="';
		html += data.headImgUrl ;
		html += '"/>';
		} else {
			html += '					<img alt="" src="${ctxStatic}/images/acca_default_head.png"/>';
		}
		html += '				</div>';
		html += '			</div>';
		html += '			<div class="span10">';
		html += '				<div class="article-ft-p-name">';
		html += data.nickname;
		html += '               </div>';
		html += '				<div class="article-ft-content">';
		html += data.content;
		html += '               </div>';
		if (data.pComment) {
			html += '				<div class="article-ft-sub">';
			html += '					<div>';
			html += '						<span class="article-ft-sub-p-name">';
			html += data.pComment.nickname;
			html += '                       </span>于<span>';
			html += data.pComment.createDateShowStr;
			html += '                       </span>评论';
			html += '					</div>';
			html += '					<div class="article-ft-sub-content">';
			html += data.pComment.content;
			html += '                   </div>';
			html += '				</div>';
		}

		html += '				<div class="article-ft-foot">';
		html += '					<div class="article-ft-foot-time">';
		html += data.createDateShowStr;
		html += '                   </div>';
		html += '					<div class="article-ft-foot-answer" onclick="showOrHideCommentInput(true,';
		html += data.articleCommentId;
		html += ',\'';
		html += '回复';
		html += data.nickname;
		html += '\'';
		html += ')">回复</div>';
		html += '				</div>';
		html += '				<div class="article-sep-line"></div>';
		html += '			</div>';
		html += '		</div>';
		html += '	</div>';
		html += '</div>';

		return html;
	}

	//提交评论
	function submitComment() {
		var accaUserId = getAccaUserId();
		var articleId = getArticleId();
		if (!articleId || articleId == 0) {
			freetekToast("文章不存在");
			return;
		}
		if (accaUserId == 0 || accaUserId == -1) {
			freetekToast("用户不存在");
			return;
		}

		var content = $("#articleCommentInputDivId").text();
		if (!content || content.length == 0) {
			freetekToast("评论不能为空！");
			return;
		}

		var parentId = $("#articleCommentUserId").val();

		var requestData = {
			articleId : articleId,
			accaUserId : accaUserId,
			content : content,
			parentId : parentId
		}

		$.ajax({
			url : "${ctxapi}/article/submitComment.do",
			data : requestData,
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.respCode == 0) {
					showOrHideCommentInput(false, 0);
					//$("#messageBox").text("评论成功！");
					//alert("评论成功！");
					freetekToast("评论成功");
					$("#articleCommentNumId").text(data.commentNumStr);
					if (isPageEnd) {
						//$("#articleCommentListNodeId").append(getCommentNoeItem(data.comment));
						$("#articleCommentNoId").hide();
					}
					$("#articleCommentListNodeId").prepend(
							getCommentNoeItem(data.comment));

				} else {
					alert(data.respDesc);
				}
			},
			"error" : function() {
				freetekToast("加载失败，请刷新页面！");
			}
		});

	}
</script>

<input type="hidden" value="${accaUserId}" id="accaUserId">
<input type="hidden" value="${article.articleId}" id="articleId">

<div class="article-md">
	<div class="article-md-item" onclick="praise()" id="praiseId">
		<c:if test="${praiseFlag==true}">
			<div class="article-md-img-praised" id="articlePraiseImgId"></div>
			<div class="article-md-btn-text" id="articlePraiseTextAllId"
				style="color: #fa6b5b">
				<span id="articlePraiseTextId">已赞</span>(<span
					id="articlePraiseNumId">${article.praiseNumStr}</span>)
			</div>
			<input type="hidden" value="1" id="articlePraiseFlagId">
		</c:if>
		<c:if test="${praiseFlag==false}">
			<div class="article-md-img-praise" id="articlePraiseImgId"></div>
			<div class="article-md-btn-text" id="articlePraiseTextAllId">
				<span id="articlePraiseTextId">点赞</span>(<span
					id="articlePraiseNumId">${article.praiseNumStr}</span>)
			</div>
			<input type="hidden" value="2" id="articlePraiseFlagId">
		</c:if>

	</div>
	<div class="article-md-item" style="margin-left: 50px;"
		onclick="showOrHideCommentInput(true,0,'请输入评论内容')">
		<div class="article-md-img-comment"></div>
		<div class="article-md-btn-text">
			评论(<span id="articleCommentNumId">${article.commentNumStr}</span>)
		</div>
	</div>
	<c:if test="${article.type==2 or article.type==1}">
		<div class="article-md-item" style="margin-left: 50px;">
			<a href="tel:4006261716"><div class="article-md-img-tel"></div>
				<div class="article-md-btn-text">电话咨询</div></a>
		</div>
	</c:if>

</div>

<div class="article-sep-line"></div>


<div class="article-ft">

	<%-- <div class="article-ft-item"> 
		<div class="row-fluid">
			<div class="box span12">
				<div class="span2">
					<div class="article-ft-head-img">
						<img alt="" src="${ctxStatic}/images/acca_default_head.png"/>
					</div>
				</div>
				<div class="span10">
					<div class="article-ft-p-name">诸葛亮</div>
					<div class="article-ft-content">视频不错，对我帮助很大，希望以后能有更多这种免费视频。视频不错，对我帮助很大，希望以后能有更多这种免费视频。视频不错，对我帮助很大，希望以后能有更多这种免费视频。</div>
					<div class="article-ft-sub">
						<div>
							<span class="article-ft-sub-p-name">司马懿</span>于<span>07-02 18:02</span>评论
						</div>
						<div class="article-ft-sub-content">视频不错，对我帮助很大，希望以后能有更多这种免费视频。视频不错，对我帮助很大，希望以后能有更多这种免费视频。</div>
					</div>
					<div class="article-ft-foot">
						<div class="article-ft-foot-time">07-02 18:02</div>
						<div class="article-ft-foot-answer" onclick="showOrHideCommentInput(true,1)">回复</div>
					</div>
					
					<div class="article-sep-line"></div>
				</div>
			</div>
		</div>
	</div> --%>
	<!-- <div id="pullDown">
			<span class="pullDownIcon"></span><span class="pullDownLabel">Pull down to refresh...</span>
		</div> -->
	<div id="articleCommentListNodeId"></div>

	<div class="article-ft-content-no" id="articleCommentNoId">暂无评论，速来抢沙发</div>

	<div style="text-align: center;">
		<div id="pullUp" class="loading"
			style="display: none; display: inline-block;">
			<span class="pullUpIcon"></span><span class="pullUpLabel">加载更多...</span>
		</div>
	</div>

</div>


<div style="height: 60px" id="blank60Id"></div>

<c:if test="${accaUserId!=0}">
	<div class="article-ct" id="articleCommnetId">
		<div class="row-fluid">
			<div class="box span12">
				<div class="span10"
					onclick="showOrHideCommentInput(true,0,'请输入评论内容')">
					<div class="article-ct-btn-ct" id="articleCommentBtnId">我要评论</div>
				</div>
				<div class="span2" style="text-align: center;">
					<div class="article-ct-item" style="margin-top: 5px"
						onclick="collect()" id="collectId">
						<c:if test="${collectFlag==true}">
							<div class="article-ct-img-cted" id="articleCollectImgId"></div>
							<div class="article-ct-btn-text" id="articleCollectTextId"
								style="color: #f33c3e">已收藏</div>
							<input type="hidden" value="1" id="articleCollectFlagId">
						</c:if>
						<c:if test="${collectFlag==false}">
							<div class="article-ct-img-ct" id="articleCollectImgId"></div>
							<div class="article-ct-btn-text" id="articleCollectTextId">收藏</div>
							<input type="hidden" value="2" id="articleCollectFlagId">
						</c:if>

					</div>
				</div>

			</div>
		</div>
	</div>
</c:if>
<c:if test="${accaUserId==0}">>
<div class="article-ct">
		<div class="row-fluid">
			<div class="box span12">
				<%-- <div class="span2" style="text-align: center;">
				<div style="width: 50px;height: 50px;margin-top: 5px;margin-left: 10px;">
					<img alt="" src="${ctxStatic}/images/acca_logo57.png" style="width:100%;border-radius: 10px;" />
				</div>
				
			</div>
			<div class="span6">
				<div style="margin-top: 5px;margin-left: 10px;">
					<div style="font-size: 16px;color: #666;margin-top:7px">ACCA Helper</div>
					<div style="font-size: 14px;color: #999;margin-top: 5px">首款中文ACCA学习工具</div>
				</div>
				
			</div>
			<div class="span4" style="text-align: center;">
				<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.chinaife.acca" style="text-decoration: none;display: inline-block;border: 1px solid red;color:red;padding:5px 10px; border-radius: 3px;height: 15px;line-height: 15px;margin-top: 15px;margin-right: 10px;">下载APP</a>
			</div>	 --%>

				<div style="text-align: center; float: left">
					<div
						style="width: 50px; height: 50px; margin-top: 5px; margin-left: 10px;">
						<img alt="" src="${ctxStatic}/images/acca_logo57.png"
							style="width: 100%; border-radius: 10px;" />
					</div>

				</div>
				<div style="float: left">
					<div style="margin-top: 5px; margin-left: 10px;">
						<div style="font-size: 16px; color: #666; margin-top: 7px">ACCA
							Helper</div>
						<div style="font-size: 14px; color: #999; margin-top: 5px">首款中文ACCA学习工具</div>
					</div>

				</div>
				<div style="text-align: center; float: right">
					<a
						href="http://a.app.qq.com/o/simple.jsp?pkgname=com.chinaife.acca"
						style="text-decoration: none; display: inline-block; border: 1px solid red; color: red; padding: 5px 10px; border-radius: 3px; height: 15px; line-height: 15px; margin-top: 15px; margin-right: 10px;">下载APP</a>
				</div>

			</div>
		</div>
	</div>
</c:if>

<div id="zhezhao" onclick="showOrHideCommentInput(false,0)"
	style="width: 100%; height: 100%; background-color: #000; filter: alpha(opacity = 50); -moz-opacity: 0.5; opacity: 0.5; position: absolute; left: 0px; top: 0px; display: none; z-index: 1000; margin-left: -10px;"></div>

<div class="article-ct-in" id="articleCommentInputId"
	style="display: none; z-index: 999999" onfocus="inputFocus()">
	<div contenteditable="true" class="article-ct-in-input"
		id="articleCommentInputDivId" placeholder="请输入评论内容"
		onclick="inputClick()"></div>
	<!-- <a class="btn btn-primary article-ct-in-btn" onclick="submitComment()">发表</a> -->
	<div class="article-ct-in-btn" onclick="submitComment()">发表</div>
	<!-- <a class="btn" style="float: left;margin-left: 10px" onclick="showOrHideCommentInput(false,0)">关闭</a> -->
	<div class="article-ct-in-btn-close"
		onclick="showOrHideCommentInput(false,0)">关闭</div>
	<input type="hidden" value="0" id="articleCommentUserId" />
</div>