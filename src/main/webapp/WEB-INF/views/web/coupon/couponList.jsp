<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>优惠券列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</head>
<body>

<form:form id="searchForm" modelAttribute="coupon" action="${ctx}/web/coupon/" method="post"
           class="breadcrumb form-search">
    <input id="type" name="type" type="hidden" value="1"/>
    <ul class="ul-form">
        <li><label>发布时间：</label>
            <input id="start" name="activityStart" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate"
                   value="${coupon.activityStart}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            -
            <input id="end" name="activityEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${coupon.activityEnd}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
        </li>
        <li><label>活动名称：</label>
            <form:input path="activityName" htmlEscape="false" maxlength="200" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
            <%--<tr>--%>
            <%--<th>页码：</th>--%>
            <%--<td><input type="text" name="page" value="${page}"/></td>--%>
            <%--<th>页面大小：</th>--%>
            <%--<td><input type="text" name="rows" value="${rows}"/></td>--%>
            <%--</tr>--%>
    </ul>
</form:form>
<%--<form:form id="searchForm2" modelAttribute="coupon" action="${ctx}/web/coupon/listbyname" method="post" class="breadcrumb form-search">--%>
<%--<input id="type" name="type" type="hidden" value="1"/>--%>
<%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>--%>
<%--<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
<%--<ul class="ul-form">--%>
<%--<li><label>发布人：</label>--%>
<%--<input name="name" value="${coupon.createBy.name}" htmlEscape="false" maxlength="200" class="input-medium"/>--%>
<%--</li>--%>
<%--<li class="btns"><input id="btnSubmit2" class="btn btn-primary" type="submit" value="查询"/></li>--%>
<%--<li class="clearfix"></li>--%>
<%--</ul>--%>
<%--</form:form>--%>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>ID</th>
        <th>活动名称</th>
        <th>优惠金额</th>
        <th>限制数量</th>
        <th>优惠券有效期</th>
        <th>发布状态</th>
        <th>发布人</th>
        <th>领取数量</th>
        <th>使用数量</th>
        <th></th>
        <shiro:hasPermission name="web:couupon:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageInfo.list}" var="coupon" varStatus="index">
        <tr>
            <td>
                    ${index.index}
            </td>
            <td>
                    ${coupon.activityName}
            </td>
            <td>
                    ${coupon.price}
            </td>
            <td>
                    ${coupon.number}
            </td>
            <td>
                <fmt:formatDate value="${coupon.validityStart}" pattern="yyyy年MM月dd日 HH:MM"/> 至 <fmt:formatDate
                    value="${coupon.validityEnd}" pattern="yyyy年MM月dd日 HH:MM"/>
            </td>
            <td><c:set var="currentTime" value="<%= new Date()%>"></c:set>
                <c:if test="${coupon.activityStart.time < currentTime.time and currentTime.time < coupon.activityEnd.time}">
                    已发布
                </c:if>
                <c:if test="${coupon.activityEnd.time < currentTime.time}">
                    未发布
                </c:if>
                <c:if test="${coupon.activityStart.time > currentTime.time}">
                    已过期
                </c:if>
            </td>
            <td>
                    ${coupon.creator}
            </td>
            <td>
                    ${coupon.received}
            </td>
            <td>
                    ${coupon.consumed}
            </td>
            <shiro:hasPermission name="web:coupon:edit">
                <td>
                    <a href="${ctx}/web/coupon/form?id=${coupon.id}">编辑</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">
    <table>
        <tr>
            <c:if test="${pageInfo.hasPreviousPage}">
                <td>
                    <a href="${ctx}/web/coupon/list?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}&countryname=${queryParam.countryname}&countrycode=${queryParam.countrycode}">前一页</a>
                </td>
            </c:if>
            <c:forEach items="${pageInfo.navigatepageNums}" var="nav">
                <c:if test="${nav == pageInfo.pageNum}">
                    <td style="font-weight: bold;">${nav}</td>
                </c:if>
                <c:if test="${nav != pageInfo.pageNum}">
                    <td>
                        <a href="${ctx}/web/coupon/list?page=${nav}&rows=${pageInfo.pageSize}&countryname=${queryParam.countryname}&countrycode=${queryParam.countrycode}">${nav}</a>
                    </td>
                </c:if>
            </c:forEach>
            <c:if test="${pageInfo.hasNextPage}">
                <td>
                    <a href="${ctx}/web/coupon/list?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}&countryname=${queryParam.countryname}&countrycode=${queryParam.countrycode}">下一页</a>
                </td>
            </c:if>
        </tr>
    </table>
</div>
</body>
</html>