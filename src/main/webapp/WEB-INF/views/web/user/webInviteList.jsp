<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>邀请记录</title>
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

<form:form id="searchForm" modelAttribute="invite" action="${ctx}/user/invite/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>发起时间：</label>
            <input id="start" name="inviteStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                        value="<fmt:formatDate value='${invite.inviteStart}' pattern='yyyy-MM-dd' />"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            -
            <input id="end" name="inviteEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                        value="<fmt:formatDate value='${invite.inviteEnd}' pattern='yyyy-MM-dd' />"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
        </li>
        <li><label>邀请状态：</label>
            <select name="status" value="${status}">
                <option value="">邀请状态</option>
                <option value="1" <c:if test="${'1' eq status}">selected</c:if>>邀请成功</option>
                <option value="0" <c:if test="${'0' eq status}">selected</c:if>>受邀中</option>
                <option value="2" <c:if test="${'2' eq status}">selected</c:if>>邀请失败</option>
            </select>
        </li>
        <li>
            <label>邀请人手机:</label>
            <form:input path="inviterPhone" htmlEscape="false" maxlength="11" class="input-medium" />
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
        <li class="clearfix"></li>
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
        <th>邀请人手机号</th>
        <th>被邀请人手机号</th>
        <th>邀请状态</th>
        <th>发起时间</th>
        <th>邀请成功时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="invite" varStatus="index">
        <tr>
            <td>
                    ${invite.inviterPhone}
            </td>
            <td>
                    ${invite.inviteePhone}
            </td>
            <td>
                    ${invite.inviteStatus}
            </td>
            <td>
                    <fmt:formatDate value="${invite.inviteTime}" pattern="yyyy年MM月dd日 HH:MM"/>
            </td>
            <td>
                    <fmt:formatDate value="${invite.successTime}" pattern="yyyy年MM月dd日 HH:MM"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>