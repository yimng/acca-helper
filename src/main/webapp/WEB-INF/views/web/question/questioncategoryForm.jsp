<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>问题分类编辑</title>
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
<form:form id="inputForm" modelAttribute="category" action="${ctx}/web/questioncategory/save" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <form:input path="id" type="hidden"/>
    <div class="control-group">
        <label class="control-label">名称:</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>

    <div class="form-actions">
        <shiro:hasPermission name="web:question:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>