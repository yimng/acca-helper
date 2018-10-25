<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>回答问题</title>
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

<form id="inputForm" action="${ctx}/web/ecquestion/saveanswer" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <input name="questionId" type="hidden" value="${examCenterQuestion.id}"/>
    <div class="control-group">
        <label class="control-label">问题名称:<span class="help-inline"></span></label>
        <div class="controls">
            <%--<input name="title" value="${examCenterQuestion.titile}" htmlEscape="false" maxlength="200" class="input-xlarge required"/>--%>
                ${examCenterQuestion.titile}
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">问题答案:</label>
        <div class="controls">
            <textarea name="content" rows="4" maxlength="200" class="txt required" style="width:400px;"></textarea>
        </div>
    </div>

    <div class="form-actions">
        <shiro:hasPermission name="web:coupon:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>