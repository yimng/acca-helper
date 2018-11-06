<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>反馈问题分类编辑</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {

            //$("#name").focus();
            $("#inputForm").validate({
                rules: {
                    price:{
                        number:true,
                        min : 0
                    }
                },
                submitHandler: function(form){

                    loading('正在提交，请稍等...');
                    //alert(0);
                    form.submit();
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/user/webFeedbackCategory/">反馈分类列表</a></li>
    <shiro:hasPermission name="user:webFeedback:edit"><li class="active"><a href="${ctx}/user/webFeedbackCategory/form">反馈分类添加修改</a></li></shiro:hasPermission>
</ul>
<form:form id="inputForm" modelAttribute="fbcategory" action="${ctx}/user/webFeedbackCategory/save" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <form:input path="id" type="hidden"/>
    <div class="control-group">
        <label class="control-label">名称:</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>

    <div class="form-actions">
        <shiro:hasPermission name="user:webFeedback:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>