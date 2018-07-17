<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>パスワード再設定確認画面</title>
</head>

<body>

<jsp:include page="header.jsp"/>

<div id="contents">
<h1>パスワード再設定画面</h1>

<!-- IDと新PWを表示。PWの＊＊表示はUserInfoDAOみてね -->
<s:form action="ResetPasswordCompleteAction">
<s:property value="#session.loginId"/><br>
<s:property value="#session.concealPassword"/><br>
<s:submit value="再設定" class="submit_btn"/>
</s:form>

</div>

<s:include value="footer.jsp"/>

</body>
</html>