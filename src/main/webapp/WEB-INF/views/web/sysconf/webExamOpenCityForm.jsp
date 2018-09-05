<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>城市管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    var i = 0;
                    $(":checkbox").each(function (index) {
                        if (this.checked == true) {
                            var r = $(".examTypes input:eq(" + index + ")").val();
                            r = parseInt(r);
                            //var r = this.val();
                            i += r;
                            //alert(i);
                        }
                    });
                    $("#examType").val(i);

                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });


            var pt = '${webExamOpenCity.provinceId}';
            var ct = '${webExamOpenCity.cityId}';

            var pList = eval('${pList}');
            var cList = eval('${cList}');
            var coList = eval('${coList}');

            for (var i in pList) {
                if (i == 0) {
                    $("#provinceId").append("<option value=''>省份</option>");
                    $('#s2id_provinceId .select2-chosen').text('省份');
                }
                if (pList[i].provinceId == pt) {
                    $("#provinceId").append("<option value='" + pList[i].provinceId + "' selected = 'selected'>" + pList[i].provinceName + "</option>");
                    $('#s2id_provinceId .select2-chosen').text(pList[i].provinceName);
                } else {
                    $("#provinceId").append("<option value='" + pList[i].provinceId + "'>" + pList[i].provinceName + "</option>");
                }
            }

            if (pt != null && pt != "" && pt != undefined) {
                for (var i in cList) {
                    if (i == 0) {
                        $("#cityId").append("<option value=''>城市</option>");
                        $('#s2id_cityId .select2-chosen').text('城市');
                    }
                    if (cList[i].provinceId == pt) {
                        if (cList[i].cityId == ct) {
                            $("#cityId").append("<option value='" + cList[i].cityId + "' selected = 'selected'>" + cList[i].cityName + "</option>");
                            $('#s2id_cityId .select2-chosen').text(cList[i].cityName);
                        } else {
                            $("#cityId").append("<option value='" + cList[i].cityId + "'>" + cList[i].cityName + "</option>");
                        }
                    }
                }
            }

            if (ct != null && ct != "" && ct != undefined) {
                for (var i in coList) {
                    if (i == 0) {
                        $("#countyId").append("<option value=''>区域</option>");
                        $('#s2id_countyId .select2-chosen').text('区域');
                    }
                    if (coList[i].cityId == ct) {
                        if (coList[i].countyId == cot) {
                            $("#countyId").append("<option value='" + coList[i].countyId + "' selected = 'selected'>" + coList[i].countyName + "</option>");
                            $('#s2id_countyId .select2-chosen').text(coList[i].countyName);
                        } else {
                            $("#countyId").append("<option value='" + coList[i].countyId + "'>" + coList[i].countyName + "</option>");
                        }
                    }
                }
            }

            $('#provinceId').change(function () {
                $("#cityId").empty();
                $("#countyId").empty();
                $('#s2id_cityId .select2-chosen').text('城市');
                $('#s2id_countyId .select2-chosen').text('');
                var provinceId = $("#provinceId option:selected").val();
                var provinceName = $("#provinceId option:selected").text();
                $('#provinceName').val(provinceName);
                $("#cityId").append("<option value=''>城市</option>");
                if (provinceId != "") {
                    for (var i in cList) {
                        if (provinceId == cList[i].provinceId) {
                            $('#cityId').append("<option value='" + cList[i].cityId + "'>" + cList[i].cityName + "</option>");
                        }
                    }
                }
            });
        });

        function add_prohist_form1(s) {
            //获取id
            var userId = $("#mysel1").val();
            var flag = false;
            $.each($("[name='userIds']"),function(){
                var id = $(this).val();
                if (userId == id && userId != null && userId != "" ){
                    alert("已经绑定该用户!");
                    flag = true;
                }
            });
            if (userId == null || userId == ""){
                alert("请选择要添加的用户!");
                flag = true;
            }
            if (flag){return;}
            //获取值
            var V = $("#mysel1").find("option:selected").text();
            var fi = $(".prohist-form1:last").clone().css("display","block");
            fi.find("input:text").val(V).end();
            fi.find("#userIds").val(userId).end();
            $(".prohist-form1:last .add").hide();
            $(".prohist-form1:last .delete").show();
            $(".prohist-form1:last").after(fi);
            $(".prohist-form1:last .add").show();
        }
        
        
        function add_prohist_form(s) {
            //获取id
            var accountId = $("#mysel").val();
            var flag = false;
            $.each($("[name='accountIds']"),function(){
                var id = $(this).val();
                if (accountId == id && accountId != null && accountId != "" ){
                    alert("已经添加该帐户!");
                    flag = true;
                }
            });
            if (accountId == null || accountId == ""){
                alert("请选择要添加的帐户!");
                flag = true;
            }
            if (flag){return;}
            //获取值
            var accountVal = $("#mysel").find("option:selected").text();
            var fi = $(".prohist-form:last").clone().css("display","block");
            fi.find("input:text").val(accountVal).end();
            fi.find("#accountIds").val(accountId).end();
            $(".prohist-form:last .add").hide();
            $(".prohist-form:last .delete").show();
            $(".prohist-form:last").after(fi);
            $(".prohist-form:last .add").show();
        }

        function delete_form(s) {
            $(s).parent("div").remove();
        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/web/webExamOpenCity/">城市列表</a></li>
    <li class="active"><a href="${ctx}/web/webExamOpenCity/form?id=${webExamOpenCity.id}">城市<shiro:hasPermission
            name="web:webExamOpenCity:edit">${not empty webExamOpenCity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="web:webExamOpenCity:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="webExamOpenCity" action="${ctx}/web/webExamOpenCity/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="examType" id="examType"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">城市:</label>
        <div class="controls">
            <select id="provinceId" name="provinceId" class="input-medium required"></select>
            <select id="cityId" name="cityId" class="input-medium required"></select>
            <!-- <select id="countyId" name="countyId" class="input-medium"></select> -->
            <span id="sMsg" style="color: red;"></span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">属性：</label>
        <div class="controls examTypes">
            <input type="checkbox" value="8"
                   <c:if test="${webExamOpenCity.flag4}">checked="checked"</c:if> />中博诚通分部
            <input type="checkbox" value="1"
                   <c:if test="${webExamOpenCity.flag1}">checked="checked"</c:if> />自有机考中心
            <input type="checkbox" value="2"
                   <c:if test="${webExamOpenCity.flag2}">checked="checked"</c:if> />官方机考中心
            <input type="checkbox" value="4" <c:if test="${webExamOpenCity.flag3}">checked="checked"</c:if>/>官方笔考中心
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">代注册管理员：</label>
        <div class="controls examTypes">
            <form:select path="sysUserId" class="input-medium">
                <form:option value="" label="请选择"/>
                <form:options items="${userList}" itemLabel="showName" itemValue="id" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">绑定管理员<font color="red">*</font>：</label>
        <div class="controls account">
            <form:select path="" id="mysel1" class="input-medium">
                <form:option value="" label="请选择"/>
                <form:options items="${userList}" itemLabel="showName" itemValue="id" htmlEscape="false"/>
            </form:select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a class="loffset10 add" href="javascript:void(0);" onclick="add_prohist_form1(this);return false;">
                <span class="icon-plus" aria-hidden="true"></span></a>

            <div class="prohist-form1" style="display:none;">
                <input name="addUser" class="input-xlarge" type="text" disabled="disabled" maxlength="20" value="" />
                <input name="userIds" id="userIds" type="hidden" maxlength="20" value="" />
                <a class="loffset10 delete" href="javascript:void(0);" onclick="delete_form(this);return false;">
                    <span class="icon-remove" aria-hidden="true"></span></a>
            </div>
            <c:if test="${not empty userRelationList}">
                <c:forEach items="${userRelationList}" var="relation" varStatus="status">
                    <div class="prohist-form1">
                        <input name="addUser" class="input-xlarge" disabled="disabled" type="text" maxlength="20" value="${relation.userName}" />
                        <input name="userIds" id="userIds" type="hidden" maxlength="20" value="${relation.sysUserId}" />
                        <a class="loffset10 delete" href="javascript:void(0);" onclick="delete_form(this);return false;">
                            <span class="icon-remove" aria-hidden="true"></span></a>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">帐户名称<font color="red">*</font>：</label>
        <div class="controls account">
            <form:select path="" id="mysel" class="input-medium">
                <form:option value="" label="请选择"/>
                <form:options items="${payConfList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
            </form:select>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a class="loffset10 add" href="javascript:void(0);" onclick="add_prohist_form(this);return false;">
                <span class="icon-plus" aria-hidden="true"></span></a>

            <div class="prohist-form" style="display:none;">
                <input name="addAccount" class="input-xlarge" type="text" disabled="disabled" maxlength="20" value="" />
                <input name="accountIds" id="accountIds" type="hidden" maxlength="20" value="" />
                <a class="loffset10 delete" href="javascript:void(0);" onclick="delete_form(this);return false;">
                    <span class="icon-remove" aria-hidden="true"></span></a>
            </div>
            <c:if test="${not empty relationList}">
                <c:forEach items="${relationList }" var="relation" varStatus="status">
                    <div class="prohist-form">
                        <input name="addAccount" class="input-xlarge" disabled="disabled" type="text" maxlength="20" value="${relation.payConfName}" />
                        <input name="accountIds" id="accountIds" type="hidden" maxlength="20" value="${relation.payConfId}" />
                        <a class="loffset10 delete" href="javascript:void(0);" onclick="delete_form(this);return false;">
                            <span class="icon-remove" aria-hidden="true"></span></a>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="web:webExamOpenCity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                                    value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>