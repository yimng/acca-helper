<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>添加优惠券</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            if ($("#isassign").is(':checked')) {
                $("#inputForm").attr("enctype", "multipart/form-data");
                $("#inputForm").attr("action", "${ctx}/web/coupon/saveimport");
                $("#file").show();
            } else {
                $("#inputForm").attr("enctype", "application/x-www-form-urlencoded");
                $("#inputForm").attr("action", "${ctx}/web/coupon/save");
                $("#file").hide();
            }
            $("#isassign").click(function () {
                if ($(this).is(':checked')) {
                    $("#inputForm").attr("enctype", "multipart/form-data");
                    $("#inputForm").attr("action", "${ctx}/web/coupon/saveimport");
                    $("#file").show();
                } else {
                    $("#inputForm").attr("enctype", "application/x-www-form-urlencoded");
                    $("#inputForm").attr("action", "${ctx}/web/coupon/save");
                    $("#file").hide();
                }
            })
            //$("#name").focus();
            $("#inputForm").validate({
                rules: {
                    price:{
                        number:true,
                        min : 0
                    }
                },
                submitHandler: function(form){
                    var i = 0;
                    $(".couponTypes :checkbox").each(function(index){
                        if(this.checked == true){
                            var r = $(".couponTypes input:eq("+index+")").val();
                            r = parseInt(r);
                            i += r;
                        }
                    });
                    $("#couponType").val(i);

                    if ($("#isassign").is(':checked')) {
                        $("#assign").val(true);
                    } else {
                        $("#assign").val(false)
                    }

                    loading('正在提交，请稍等...');
                    //alert(0);
                    form.submit();
                }
            });
        });
    </script>
</head>
<body>

<form:form id="inputForm" modelAttribute="coupon" action="${ctx}/web/coupon/save" method="post" enctype="multipart/form-data" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="couponType"/>
    <form:hidden path="assign" />
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">活动名称<span class="help-inline"><font color="red">*</font> </span></label>
        <div class="controls">
            <form:input path="activityName" value="${coupon.activityName}" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">优惠金额<span class="help-inline"><font color="red">*</font> </span></label>
        <div class="controls">
            <form:input path="price" value="${coupon.price}" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">优惠券有效期<font color="red">*</font></label>
        <div class="controls">
            <input id="validityStart" name="validityStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${coupon.validityStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
            -
            <input id="validityEnd" name="validityEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${coupon.validityEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">数量限制<span class="help-inline"><font color="red">*</font></span></label>
        <div class="controls">
            <form:input path="number" value="${coupon.number}" htmlEscape="false" maxlength="200" class="input-xlarg required"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">说明<span class="help-inline"><font color="red">*</font> </span></label>
        <div class="controls">
            <form:textarea path="description" htmlEscape="false" maxlength="1024" class="input-xxlarge required" cols="100" rows="10"/>

                <%-- <form:input path="officialExamProcess" htmlEscape="false" maxlength="256" class="input-xlarge "/> --%>
        </div>
    </div>

    <div class="control-group">
        <div class="controls assign">
            <input id="isassign" name="assign" type="checkbox"  <c:if test="${coupon.assign}">checked="checked"</c:if> />指定用户
        </div>
        <div id="file" class="controls assign">
            <input  type="file" name="file"/>
            <a target="_self" href="/static/template/template.xlsx" >下载office模版</a>
        </div>
    </div>

    <div class="control-group">
        <div class="controls couponTypes">
            <input type="checkbox" value="1"  <c:if test="${coupon.flag1}">checked="checked"</c:if> />新注册用户
            <input type="checkbox" value="2"  <c:if test="${coupon.flag2}">checked="checked"</c:if> />报考用户
            <input type="checkbox" value="4"  <c:if test="${coupon.flag3}">checked="checked"</c:if>/>邀请者
            <input type="checkbox" value="8"  <c:if test="${coupon.flag4}">checked="checked"</c:if>/>被邀请者
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">活动时间<font color="red">*</font></label>
        <div class="controls">
            <input id="startTime" name="activityStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${coupon.activityStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
            -
            <input id="endTime" name="activityEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${coupon.activityEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
        </div>
    </div>

    <div class="form-actions">
        <shiro:hasPermission name="web:coupon:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>