<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>邀请排名</title>
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

<form:form id="searchForm" action="${ctx}/user/invite/listrank" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>邀请成功时间</label>
            <input id="start" name="start" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${start}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            -
            <input id="end" name="end" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${end}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
        </li>
        <li><label>成功数量排序</label>
            <select name="sort" value="${sort}">
                <option value ="">请选择</option>
                <option value ="0" <c:if test="${'0' eq sort}">selected</c:if>>降序</option>
                <option value="1" <c:if test="${'1' eq sort}">selected</c:if>>升序</option>
            </select>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>

<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>邀请成功次数</th>
        <th>邀请人手机号</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="inviteRank" varStatus="index">
        <tr>
            <td>
                    ${inviteRank.successCount}
            </td>
            <td>
                    ${inviteRank.phone}
            </td>
        </tr>s
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>