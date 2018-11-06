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
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}${ctx}/web/question/">问题列表</a></li>
    <shiro:hasPermission name="web:question:edit"><li><a href="${ctx}/web/questioncategory">问题分类</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="question" action="${ctx}/web/question/" method="post"
           class="breadcrumb form-search">
    <input id="type" name="type" type="hidden" value="1"/>
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>发布时间：</label>
            <input name="start" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate"
                   value="<fmt:formatDate value='${question.start}' pattern='yyyy-MM-dd' />"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            -
            <input name="end" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value='${question.end}' pattern='yyyy-MM-dd' />"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
        </li>
        <li><label>问题名称：</label>
            <form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
        </li>
        <li><label>问题分类：</label>
            <form:select path="catergoryId">
                <form:option label="请选择:" value="" htmlEscape="false"/>
                <form:options items="${category}" itemLabel="name" itemValue="id" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>是否热门：</label>
            <form:select path="hot">
                <form:option label="请选择:" value="" htmlEscape="false"/>
                <form:option label="否" value="0" htmlEscape="false"/>
                <form:option label="是" value="1" htmlEscape="false"/>
            </form:select>
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
        <th>问题分类</th>
        <th>是否热门</th>
        <th>发布人</th>
        <th>修改人</th>
        <th>发布时间</th>
        <th>修改时间</th>
        <th>有用</th>
        <th>无用</th>
        <shiro:hasPermission name="web:question:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="question" varStatus="index">
        <tr>
            <td>
                ${question.title}
            </td>
            <td>
                ${question.category}
            </td>
            <td>
                <c:if test="${question.hot}">是</c:if>
                <c:if test="${!question.hot}">否</c:if>
            </td>
            <td>
                ${question.publisherName}
            </td>
            <td>
                ${question.modifierName}

            </td>
            <td>
                    <fmt:formatDate value="${question.publishTime}" pattern="yyyy-MM-dd"/>
            </td>
            <td>
                    <fmt:formatDate value="${question.modifyTime}" pattern="yyyy-MM-dd"/>
            </td>
            <td>
                ${question.praised}
            </td>
            <td>
                ${question.disPraised}
            </td>
            <shiro:hasPermission name="web:question:edit">
                <td>
                    <a href="${ctx}/web/question/form?id=${question.id}">编辑</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>