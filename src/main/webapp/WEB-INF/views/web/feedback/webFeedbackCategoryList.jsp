<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>WebFeedback管理</title>
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
    <li class="active"><a href="${ctx}/user/webFeedbackCategory/">反馈分类列表</a></li>
    <shiro:hasPermission name="user:webFeedback:edit"><li><a href="${ctx}/user/webFeedbackCategory/form">反馈分类添加</a></li></shiro:hasPermission>
</ul>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th width="15%">ID</th>
        <th width="15%">名称</th>
        <shiro:hasPermission name="user:webFeedback:view">
            <th width="5%">操作</th>
        </shiro:hasPermission>
        <%-- <shiro:hasPermission name="user:webFeedback:edit"><th>操作</th></shiro:hasPermission> --%>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="fbcategory">
        <tr>
            <td>${fbcategory.id}</td>
            <td>${fbcategory.name}</td>
            <shiro:hasPermission name="web:question:edit">
                <td>
                    <a href="${ctx}/user/webFeedbackCategory/form?id=${fbcategory.id}">编辑</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>