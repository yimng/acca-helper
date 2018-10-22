<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>中博&amp;财萃网学员认证</title>
	<script src="/acca/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="/acca/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="/acca/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="/acca/static/common/mustache.min.js" type="text/javascript"></script>
	<link href="/acca/static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<script src="/acca/static/common/jeesite.js" type="text/javascript"></script>
	<script type="text/javascript">var ctx = '/acca/a', ctxStatic='/acca/static';</script>
	<script>
		$(document).ready(function() {	
			var Toast = function(config) {
				this.context = config.context == null ? $('body') : config.context;//上下文
				this.message = config.message;//显示内容
				this.time = config.time == null ? 3000 : config.time;//持续时间
				this.init();
			}
			var msgEntity;
			Toast.prototype = {
				//初始化显示的位置内容等
				init : function() {
					$("#toastMessage").remove();
					//设置消息体
					var msgDIV = new Array();
					msgDIV.push('<div id="toastMessage">');
					msgDIV.push('<span>' + this.message + '</span>');
					msgDIV.push('</div>');
					msgEntity = $(msgDIV.join('')).appendTo(this.context);
					//设置消息样式
					/* var left = this.left == null ? this.context.width() / 2
							- msgEntity.find('span').width() / 2 : this.left;
					var top = this.top == null ? '20px' : this.top; */
					var left = window.innerWidth/2 - msgEntity.find('span').width() / 2;
					msgEntity.css({
						//position : 'absolute',
						'position' : 'fixed',
						'top' : '50%',
						'left' : left,
						'z-index' : '99',
						//'background-color' : 'black',
						'background-color' : 'rgba(0, 0, 0, 0.6)',
						'color' : 'white',
						'font-size' : '13px',
						'padding' : '10px',
						'border-radius' : '3px'
					//	'opicaty':'0.5'
					});
					msgEntity.hide();
				},
				//显示动画
				show : function() {
					msgEntity.fadeIn(this.time / 2);
					msgEntity.fadeOut(this.time/ 2);
				}
			}	
	
			/*
			 * 直接调用下面的函数，显示toast提示
			 */
			function freetekToast(message){
				new Toast({message:message}).show();
			}
			
			<c:if test="${accauser.accaUserId==null}">
			$("#intro__form").hide();
			</c:if>
			
			<c:if test="${accauser.accaUserId!=null}">			
			accaUserId="${accauser.accaUserId}";
			//freetekToast(accaUserId);
			freetekToast("欢迎进入学员认证通道！");
			if(accaUserId == null || accaUserId == "" || accaUserId == "undefined"){
				
				freetekToast("请进入ACCA小助手app登陆后再进行学员认证。");
			}
			$("#btn-submit").click(function() {
	         	var registerName = $("#registerName").val();
	         	var org = $("#org").val();
	         	var registerPhone = $("#registerPhone").val();	         	
	         	var cardType = $("#cardType").val();//$("input[name=cardType]:checked").val();
	         	var cityId = $("#cityId").val();
	         	var cityName =$("#cityId").find("option:selected").text();// $("#cityId option[selected]").text();
	         	var registerCardNumber = $("#registerCardNumber").val();
	         	var classStyle = $("#classStyle").val();//$("input[name=classStyle]:checked").val();
	         	var grade = $("#grade").val();//$("input[name=grade]:checked").val();
	         	freetekToast(registerName);
	         	freetekToast(cityName);
	         	freetekToast(classStyle);
	         	if(registerName == null || registerName == "" || registerName == "undefined"){
	         		freetekToast("请填写您的真实姓名。");
					return false;
				}
	         	else if(registerPhone == null || registerPhone == "" || registerPhone == "undefined"){
	         		freetekToast("请填写您的电话。");
					return false;
				}
	         	else if(!checkContactNumber(registerPhone)){
	         		freetekToast("请填写正确的电话号码。");
					return false;
				}				
				else if(org == null || org == "" || org == "undefined"){
					freetekToast("请填写您的学校或单位名称。");
					return false;
				}				
				else if(cardType == null || cardType == "" || cardType == "undefined"){
					freetekToast("请选择身份证明类型。");
					return false;
				}
				else if(registerCardNumber == null || registerCardNumber == "" || registerCardNumber == "undefined"){
					freetekToast("请填写身份证号码。");
					return false;
				}
				/* else if(checkIdCode(registerCardNumber,cardType)){
					freetekToast("请正确填写身份证明号码。");
					return false;
				} */
				else if(classStyle == null || classStyle == "" || classStyle == "undefined"){
					freetekToast("请选择您在中博或财萃网的课程班型。");
					return false;
				}
				else if (cityId == null || cityId == "" || cityId == "undefined") {
				    freetekToast("请选择你所在的城市");
				    return false;
                }
				else if(grade == null || grade == "" || grade == "undefined"){
					freetekToast("请选择您进入大学的时间。");
					return false;
				}
	         	// var submit = function (v, h, f) {
	            // 	if (v == true){
	                        //这里写提交数据的操作，如ajax提交某个form等等..   
					$.ajax({
						url : "/acca/api/article/submitAuthen.do",
						data : {
							accaUserId:accaUserId,
				         	registerName:registerName,
				         	org:org,
				         	cityId:cityId,
				         	cityName:cityName,
				         	registerPhone:registerPhone,
				         	cardType:cardType,
				         	registerCardNumber:registerCardNumber,
				         	classStyle:classStyle,
				         	grade:grade
						},
						type: "post",
						success: function (msg) {
							freetekToast(msg);//freetekToast(msg);							  
							$("#intro__division-subtitle").classList.add();
							$(".intro__form-checkbox").hide();
							$(".intro__btn").hide();							
						}
					});
	           //  	}else{
               //         jBox.tip("已取消", 'info');
	           //  	}
               //     return true;
               // };
               // $.jBox.confirm("确定是否保存数据", "提示", submit, { buttons: { '保存': true, '取消': false } });
			});
			
			$("#classStyle").change(function() {
				if(this.value==""){
					$("#classStyle_cover").text("班型");	
				}else{
					$("#classStyle_cover").html(this.value);
				}						
			});	
			$("#cardType").change(function() {
				if(this.value==""){
					$("#cardType_cover").text("证件类型");	
				}
				if(this.value=="1"){
					$("#cardType_cover").html("身份证");	
				}
				if(this.value=="2"){
					$("#cardType_cover").html("护照");	
				}				
			});
			
			$("#cityId").change(function() {
				if(this.value==""){
					$("#cityId_cover").text("距离最近城市");	
				}else{
					$("#cityId_cover").html($("#cityId").find("option:selected").text());	
				}
			});	
			
			$("#grade").change(function() {
				if(this.value==""){
					$("#grade_cover").text("年级");	
				}else{
					$("#grade_cover").html(this.value);	
				}
			});	
			<c:if test="${accauser.cardType!=null}">
				$("#cardType").val("${accauser.cardType}");	
				$("#cardType_cover").html($("#cardType").find("option:selected").text());
			</c:if>
			<c:if test="${accauser.cityId!=null}">
				$("#cityId").val("${accauser.cityId}");
				$("#cityId_cover").html($("#cityId").find("option:selected").text());
			</c:if>
			<c:if test="${accauser.classStyle!=null}">
				$("#classStyle").val("${accauser.classStyle}");
				$("#classStyle_cover").html("${accauser.classStyle}");
			</c:if>
			<c:if test="${accauser.grade!=null}">
				$("#grade").val("${accauser.grade}");
				$("#grade_cover").html("${accauser.grade}");
			</c:if>			
			</c:if>
		});
		
		
		
		
		
		function checkContactNumber(mobile) {  
	        $("#error").css("display", "none");  
	        var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;  
	        var isPhone = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;;  
	        //var error = "<label id=\"error\" class=\"validate_input_error\">请正确填写电话号码，例如:13511111111或010-11111111</label>";  
	        //如果为1开头则验证手机号码  
	        if (mobile.substring(0, 1) == 1) {  
	            if ( !isMobile.exec(mobile) || mobile.length != 11){ //() {
	                return false;  
	            }  
	        }   //如果为0开头则验证固定电话号码  
	        else if (mobile.substring(0, 1) == 0) {  
	            if (!isPhone.test(mobile)) {
	                return false;  
	            }  
	        }  
	        //否则全部不通过  
	        else {  
	            return true;  
	        }   
	        return true;
	    } 
		
		function checkIdCode(cardnum,type) {
			return true;
			var msg = "";
		    var type = cardnum;
		    if (cardnum != '') {
		        if ("身份证" == type) { //身份证号		            
		            msg = false;
		            var re = new RegExp("(^\\d{15}|\\d{17}(\\d{1}|X|x)$)"); //(^\\d{15}$)|(^\\d{17}([0-9]|X)$)
		            var card = $(text).val();
		            card = card.toUpperCase();
		            if (re.test(card)) {
		                //15位转18位
		                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
		                var cardTemp = 0,
		                i, valnum;
		                var date;
		                if ("15" == card.length) {
		                    date = "19" + card.substr(6, 6);
		                    if (!checkDate(date, type, text)) {
		                        return false;
		                    }
		                    return true;
		                }
		                var len = card.length;
		                if (len == '18') {
		                    date = card.substr(6, 8);
		                    if (!checkDate(date, type, text)) {
		                        return false;
		                    }
		                    for (i = 0; 17 > i; i++) {
		                        cardTemp += card.substr(i, 1) * arrInt[i];
		                    }
		                    valnum = arrCh[cardTemp % 11];
		                    if (valnum == card.substr(17, 1)) {
		                        return true;
		                    } else {
		                        return false;
		                    }
		                }
		            }
		        } else if ("护照" == type) { //护照
		            msg = "请输入正确的护照号";
		            var re = new RegExp("(^([PSE]{1}\\d{7}|[GS]{1}\\d{8})$)"); //E字打头的后面不知道要跟几位
		            var card = $(text).val().toUpperCase();
		            if (re.test(card)) {
		                return true;
		            }
		        } else if ("军官证" == type) { //军官证
		            msg = "请输入正确的军官证号";
		            var re = new RegExp("^([\u4e00-\u9fa5]{1,}[\u4e00-\u9fa50-9()（）-]{5,})$");
		            if (re.test($(text).val())) {
		                return true;
		            }
		        } else {
		            return false;
		        }
		        return false;
		    }
		    return true;
		}
	</script>
	<link rel="stylesheet" type="text/css" href="/acca/static/student/files/style-videotemplate.css">
	<meta name="wxm:timeline_title" content="中博教育学员认证">
	<meta name="wxm:appmessage_title" content="中博教育网学员认证">
	<meta name="wxm:appmessage_desc" content="ACCA小助手，随身而行的中文ACCA学习工具">
	<meta name="wxm:img_url" content="">
	<meta name="wxm:link" content="">
</head>
<body>

<div id="container" style="height:100%;">
	<img src="/acca/static/student/files/0596da8c7dc279862733b71de41abbcd.jpg" class="source-img" crossorigin="anonymous">
	<div class="back-bg" style="background: rgba(40, 40, 40, 0.2) none repeat scroll 0% 0%;">
	    <!-- source-canvas 用以 从banner图中 getImageData -->
	    <!-- blur-canvas 用以 绘制背景, putImageData -->
	    <canvas class="source-canvas" width="522" height="471"></canvas>
	    <canvas class="blur-canvas blur-canvas_show" width="522" height="471"></canvas>

    <div class="intro" style="padding-top: 0px;">
        <!-- 类.intro__below为下方深色背景内容-->
        <div class="intro__below intro__below_form">
            <div class="intro__below-header">中博&amp;财萃网学员认证</div>
            <div id="intro__form" class="intro__form">
                <div class="intro__form-name">
                    <input id="registerName" name="registerName" placeholder="姓名"  value="${accauser.registerName}" class="intro__form-input" type="text">
                </div> 
                <div class="intro__form-name">
                	<input id="registerPhone" name="registerPhone"  placeholder="手机"  value="${accauser.registerPhone}" class="intro__form-input" maxlength="11" type="text" />
                </div> 
                <div class="intro__form-name">
                	<input id="org" name="org" class="intro__form-input" placeholder="学校/单位"  value="${accauser.org}" type="text" />
                </div>
                <div class="intro__form-region">
                    <div class="intro__select-cover" id="cardType_cover">证件类型</div>
                   	<select name="cardType" id="cardType">
							<option value="" >证件类型</option>
							<option value="1" >身份证</option>
							<option value="2" >护照</option>	
					</select>
                </div> 
                <div class="intro__form-name">
                	<input name="registerCardNumber" id="registerCardNumber" value="${accauser.registerCardNumber}"  class="intro__form-input" placeholder="身份证号" type="text" />
                </div>
                <div class="intro__form-region"id="classStyle_div">
                    <div class="intro__select-cover" id="classStyle_cover">班型</div>
                   	<select name="classStyle" id="classStyle">
							<option value="">班型</option>
							<option value="面授精英班" >面授精英班</option>
							<option value="面授标准班" >面授标准班</option>
							<option value="网络保过班" >网络保过班</option>
							<option value="网络标准班" >网络标准班</option>		
					</select>
                </div> 		
                <div class="intro__form-region" id="cityId_div">
                    <div class="intro__select-cover" id="cityId_cover">距离最近/签约分部</div>
                   	<select name=cityId id="cityId">
						<!--option value="340800">安庆市</option-->
                        <option value="" >城市</option>
						<option value="451000">百色市</option>
						<option value="340300">蚌埠市</option>
						<option value="130600">保定市</option>
						<option value="110100">北京市</option>
						<option value="220100">长春市</option>
						<option value="430100">长沙市</option>
						<option value="510100">成都市</option>
						<option value="210200">大连市</option>
						<option value="350100">福州市</option>
						<option value="440100">广州市</option>
						<option value="520100">贵阳市</option>
						<option value="450300">桂林市</option>
						<option value="230100">哈尔滨市</option>
						<option value="460100">海口市</option>
						<option value="330100">杭州市</option>
						<option value="340100">合肥市</option>
						<option value="150100">呼和浩特市</option>
						<option value="370100">济南市</option>
						<option value="410200">开封市</option>
						<option value="530100">昆明市</option>
						<option value="620100">兰州市</option>
						<option value="450200">柳州市</option>
						<option value="360100">南昌市</option>
						<option value="320100">南京市</option>
						<option value="450100">南宁市</option>
						<option value="330200">宁波市</option>
						<option value="130300">秦皇岛市</option>
						<option value="370200">青岛市</option>
						<option value="310100">上海市</option>
						<option value="440300">深圳市</option>
						<option value="210100">沈阳市</option>
						<option value="130100">石家庄市</option>
						<option value="320500">苏州市</option>
						<option value="140100">太原市</option>
						<option value="130200">唐山市</option>
						<option value="120100">天津市</option>
						<option value="371000">威海市</option>
						<option value="650100">乌鲁木齐市</option>
						<option value="320200">无锡市</option>
						<option value="340200">芜湖市</option>
						<option value="420100">武汉市</option>
						<option value="610100">西安市</option>
						<option value="350200">厦门市</option>
						<option value="320300">徐州市</option>
						<option value="370600">烟台市</option>
						<option value="130700">张家口市</option>
						<option value="321100">镇江市</option>
						<option value="410100">郑州市</option>
						<option value="500100">重庆市</option>
						<option value="440400">珠海市</option>																
					</select> 
                </div>                
                <div class="intro__form-region" id="grade_div">
                    <div class="intro__select-cover" id="grade_cover">年级</div>
                   	<select name=grade id="grade">
							<option value="">年级</option>
							<option value="2013级" >2013级</option>
							<option value="2014级" >2014级</option>
							<option value="2015级" >2015级</option>
							<option value="2016级" >2016级</option>
							<option value="2017级" >2017级</option>
							<option value="2018级" >2018级</option>
							<option value="已毕业" >已毕业</option>
							
					</select> 
                </div>
                <c:if test="${accauser.iszbg==1}">
					<div id="box-tip" class="intro__division-subtitle">
	                    <div id="box-tip-text" class="mod-submit-form__result-text">
	                        您的资料已成功提交,请等待机考老师审核！
	                    </div>                  
                	</div>
				</c:if>
				<c:if test="${accauser.iszbg==3}">
					<div id="box-tip" class="intro__division-subtitle">
	                    <div id="box-tip-text" class="mod-submit-form__result-text">
	                        您的认证审核已通过，机考报名价格为中博&财萃学员价格！
	                    </div>                  
                	</div>
				</c:if>
				<c:if test="${accauser.iszbg==0}">
				<div id="box-tip" class="intro__division-subtitle hid">
	                    <div id="box-tip-text" class="mod-submit-form__result-text">
	                        您的资料已成功提交,请等待机考老师审核！
	                    </div>                  
                </div>
                <div class="intro__form-checkbox">
                    <input checked="checked" id="agree" type="checkbox">
                    <label for="agree">认证提交后分部老师会进行审核，审核通过后报名机考自动显示学员价格。</label>
                </div>
                <div class="intro__btn" style="margin: 30px 0 70px;" id="btn-submit">
                    立即认证
                </div>
                </c:if>
                <c:if test="${accauser.iszbg==2}">               		
                <div id="box-tip" class="intro__division-subtitle">
	                    <div id="box-tip-text" class="mod-submit-form__result-text">
	                        您的认证没能通过审核，如内容有误，请编辑正确后再重新提交。
	                    </div>
				</div>
                <div class="intro__btn" style="margin: 30px 0 70px;" id="btn-submit">
                    重新提交资料
                </div>
                </c:if>
                
                <br /><br />
                <!--<div id="box-tip" class="mod-submit-form__result ui-d-n">
                    <div id="box-tip-text" class="mod-submit-form__result-text">
                        您的资料已成功提交！
                    </div>
                    <div class="mod-submit-form__footer">
                        <div id="btn-close" class="mod-submit-form__btn-cancel mod-submit-form__btn-cancel_single">关闭</div>
                    </div>
                </div>-->
            </div>
        </div>
    </div>
</div>
<style type="text/css">
	@-webkit-keyframes rotation {
	    10% {
	        transform: rotate(90deg);
	        -webkit-transform: rotate(90deg)
	    }
	    50%, 60% {
	        transform: rotate(0deg);
	        -webkit-transform: rotate(0deg)
	    }
	    90% {
	        transform: rotate(90deg);
	        -webkit-transform: rotate(90deg)
	    }
	    100% {
	        transform: rotate(90deg);
	        -webkit-transform: rotate(90deg)
	    }
	}
	
	@keyframes rotation {
	    10% {
	        transform: rotate(90deg);
	        -webkit-transform: rotate(90deg)
	    }
	    50%, 60% {
	        transform: rotate(0deg);
	        -webkit-transform: rotate(0deg)
	    }
	    90% {
	        transform: rotate(90deg);
	        -webkit-transform: rotate(90deg)
	    }
	    100% {
	        transform: rotate(90deg);
	        -webkit-transform: rotate(90deg)
	    }
	}
	
	@media only screen
	    and (min-width : 1023px){
	        html{
	            width:414px;
	            margin: 0 auto;
	            background-color: #333!important;
	            position: absolute;
	            left: 50%;
	            margin-left: -207px;
	            max-height: 672px;
	            height: 100%;
	        }
	 
	    }
</style>
</div>
</body>
</html>