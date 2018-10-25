<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>查看回答</title>
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
<form:form id="searchForm" modelAttribute="examCenterQuestion" action="${ctx}/web/ecquestion/view" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <form:input path="id" value="${id}" type="hidden"/>
<label>问题名称：</label>${examCenterQuestion.titile}
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>回答者昵称</th>
        <th>回答者手机号</th>
        <th>回答时间</th>
        <th>回答者内容</th>
        <shiro:hasPermission name="web:ecquestion:view">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="answer" varStatus="index">
        <tr>
            <td>
                    ${answer.sysUserName}
            </td>
            <td>
                    ${answer.sysUserPhone}
            </td>
            <td>
                    <fmt:formatDate value="${answer.createTime}" pattern="yyyy-MM-dd"/>
            </td>
            <td>
                    ${answer.content}
            </td>
            <shiro:hasPermission name="web:question:view">
                <td>
                    <a href="${ctx}/web/ecquestion/deleteanswer?id=${answer.id}">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>