<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>添加优惠券</title>
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

<form:form id="inputForm" modelAttribute="question" action="${ctx}/web/question/save" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <input name="id" type="hidden" value="${question.id}"/>
    <div class="control-group">
        <label class="control-label">问题名称<span class="help-inline"><font color="red">*</font> </span></label>
        <div class="controls">
            <form:input path="title" value="${question.title}" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">问题答案<span class="help-inline"><font color="red">*</font> </span></label>
        <div class="controls">
            <form:textarea path="answer" htmlEscape="false" maxlength="1024" class="input-xxlarge required" cols="200" rows="15"/>

                <%-- <form:input path="officialExamProcess" htmlEscape="false" maxlength="256" class="input-xlarge "/> --%>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <c:forEach items="${category}" varStatus="v" var="p">
                <input name="catergoryId" type="radio" value="${p.id}" <c:if test="${question.catergoryId == p.id}">checked="checked"</c:if> />${p.name}&nbsp;
            </c:forEach>
        </div>
    </div>

    <div class="control-group">
        <div class="controls assign">
            <form:checkbox  path="hot" />是否热门
        </div>
    </div>

    <div class="form-actions">
        <shiro:hasPermission name="web:question:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>