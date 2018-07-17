<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>ログイン</title>
<script>
function goLoginAction(){
	document.getElementById("form").action="LoginAction";
}
function goCreateUserAction(){
	document.getElementById("form").action="CreateUserAction";
}
function goResetPasswordAction(){
	document.getElementById("form").action="ResetPasswordAction";
}
</script>
</head>


<body>

<jsp:include page="header.jsp" />

	<div id="main">

		<h1>ログイン画面</h1>

		<div>
			<h3>ログイン</h3>

			<s:form action="LoginAction">

			<s:if test="!#session.loginIdErrorMessageList.isEmpty()">
				<s:iterator value="#session.loginIdErrorMessageList"><s:property /><br></s:iterator>
			</s:if>
			<s:if test="!#session.passwordErrorMessageList.isEmpty()">
				<s:iterator value="#session.passwordErrorMessageList"><s:property /><br></s:iterator>
			</s:if>

<!--
	SAVED:<s:property value="%{#session.savedLoginId}"/>
	LOGINID:<s:property value="%{#session.loginId}"/>
-->


			<table>
				<tr>
					<th><label>ログインID</label></th>
					<s:if test="#session.savedLoginId == true">
						<td><s:textfield name="user_id" value='%{#session.userId}' /></td>
					</s:if>
					<s:else>
						<td><s:textfield name="user_id" /></td>
					</s:else>
				</tr>


				<tr>
					<th><label>パスワード</label></th>
					<td><s:password name="password" /></td>
				</tr>
			</table>
			<div>
				<s:if test="#session.savedLoginId == true">
					<s:checkbox name="savedLoginId" checked="checked" />
				</s:if>
				<s:else>
					<s:checkbox name="savedLoginId" />
				</s:else>
				<s:label value="ログインID保存" /><br>
			</div>

			<div>
				<s:submit value="ログイン" onclick="goLoginAction();" />
			</div>
			</s:form>

			<h3>新規ユーザー登録</h3>
			<s:form action="CreateUserAction">
				<s:submit value="新規ユーザー登録"/>
			</s:form>

			<h3>パスワード再設定</h3>
			<s:form action="ResetPasswordAction">
				<s:submit value="パスワード再設定"/>
			</s:form>
		</div>

	</div>

<s:include value="footer.jsp"/>

</body>
</html>
