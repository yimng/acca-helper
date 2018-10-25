<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>问题列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>

<form:form id="searchForm" modelAttribute="examCenterQuestion" action="${ctx}/web/ecquestion/" method="post"
           class="breadcrumb form-search">
    <input id="type" name="type" type="hidden" value="1"/>
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>问题名称：</label>
            <form:input path="titile" htmlEscape="false" maxlength="200" class="input-medium"/>
        </li>
        <li><label>机考中心：</label>
            <form:input path="examPlaceName" htmlEscape="false" maxlength="200" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>

<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>问题名称</th>
        <th>机考中心</th>
        <th>提问者昵称</th>
        <th>提问者手机号</th>
        <th>提问时间</th>
        <shiro:hasPermission name="web:ecquestion:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="question" varStatus="index">
        <tr>
            <td>
                    ${question.titile}
            </td>
            <td>
                    ${question.examPlaceName}
            </td>
            <td>
                    ${question.accaUserNickname}
            </td>
            <td>
                    ${question.accaUserPhone}
            </td>
            <td>
                <fmt:formatDate value="${question.createTime}" pattern="yyyy-MM-dd"/>
            </td>
            <shiro:hasPermission name="web:question:edit">
                <td>
                    <a href="${ctx}/web/ecquestion/view?id=${question.id}">查看回答</a>
                    <a href="${ctx}/web/ecquestion/form?id=${question.id}">回答</a>
                    <a href="${ctx}/web/ecquestion/delete?id=${question.id}">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>