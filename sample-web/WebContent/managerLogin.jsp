<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>ログイン（管理者用）</title>

</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>ログイン画面（管理者用）</h1>
<s:form id="form" action="ManagerLoginAction">

<!--
	SAVED:<s:property value="%{#session.savedLoginId}"/>
	LOGINID:<s:property value="%{#session.loginId}"/>
-->
	<table class="vertical-list-table">
		<tr>
			<th scope="row"><s:label value="ログインID:"/></th>
			<td><s:textfield name="loginManagerId" class="txt" placeholder="ログインID" autocomplete="off"/></td>
		</tr>
		<tr>
			<th scope="row"><s:label value="パスワード:"/></th>
			<td><s:password name="loginPassword" class="txt" placeholder="パスワード" autocomplete="off"/></td>
		</tr>
	</table>
	<div class="submit_btn_box">
		<s:submit value="ログイン" class="submit_btn" onclick="managerLoginAction();"/>
	</div>
</s:form>
<br>
</div>


<s:include value="footer.jsp"/>
</body>
</html>