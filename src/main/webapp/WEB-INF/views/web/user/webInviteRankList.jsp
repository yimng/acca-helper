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

<form:form id="searchForm" modelAttribute="inviteRank" action="${ctx}/user/invite/listrank" method="post" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>邀请成功时间：</label>
            <input id="start" name="start" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            -
            <input id="end" name="end" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
        </li>
        <li><label>邀请成功数量排序：</label>
            <select name="sor">
                <option value ="">请选择</option>
                <option value ="0">降序</option>
                <option value="1">升序</option>
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
    <c:forEach items="${inviteRankList}" var="inviteRank" varStatus="index">
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
</body>
</html>