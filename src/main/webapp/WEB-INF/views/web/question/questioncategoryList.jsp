<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>问题分类管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {

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
<ul class="nav nav-tabs">
    <li><a href="${ctx}/web/question/">问题列表</a></li>
    <shiro:hasPermission name="web:question:edit"><li class="active"><a href="${ctx}/web/questioncategory">问题分类</a></li></shiro:hasPermission>
</ul>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th width="15%">ID</th>
        <th width="15%">名称</th>
        <shiro:hasPermission name="user:webFeedback:edit">
            <th width="5%">操作</th>
        </shiro:hasPermission>
        <%-- <shiro:hasPermission name="user:webFeedback:edit"><th>操作</th></shiro:hasPermission> --%>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="category">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <shiro:hasPermission name="web:question:edit">
                <td>
                    <a href="${ctx}/web/questioncategory/form?id=${category.id}">编辑</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>