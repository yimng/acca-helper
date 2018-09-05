<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<legend>
	<c:if test="${flag == 1}">
		<shiro:hasPermission name="web:loanUser:review">
			<input id="okSubmit" class="btn btn-primary" type="button" onclick="pass()" value="通过"/>
			<input id="noSubmit" class="btn btn-primary" style="background: red;" onclick="refuse()" type="button" value="拒绝"/>
		</shiro:hasPermission>
	</c:if>
	<c:if test="${flag == 2}">
		${revMsg}
	</c:if>
</legend>

<script type="text/javascript">
	function pass(){
		window.location.href = "${ctx}/web/loanUser/pass?loanUserId=${id}&loanApplyId=${applyId}";
	}
	
	function refuse(){
		window.location.href = "${ctx}/web/loanUser/refuse?loanUserId=${id}&loanApplyId=${applyId}";
	}
</script>