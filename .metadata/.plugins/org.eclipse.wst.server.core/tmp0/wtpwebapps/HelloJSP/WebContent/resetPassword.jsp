<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="./css/style.css">

<title>パスワード再設定画面</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div id="contents">
<h1>パスワード再設定画面</h1>


<!-- LoginIDが空欄の場合にエラーをだす -->
<s:if test="!#session.loginIdErrorMessageList.isEmpty()">
<div class="error">
<div class="error-message">
<s:iterator value="#session.loginIdErrorMessageList">
<s:property />
<br>
</s:iterator>
</div>
</div>
</s:if>


<!-- PWが空欄の場合にエラーをだす -->
<s:if test="!#session.passwordErrorMessageList.isEmpty()">
<div class="error">
<div class="error-message">
<s:iterator value="#session.passwordErrorMessageList">
<s:property />
<br>
</s:iterator>
</div>
</div>
</s:if>


<!-- PW相違の場合にエラーをだす -->
<s:if test="!#session.passwordIncorrectErrorMessageList.isEmpty()">
<div class="error">
<div class="error-message">
<s:iterator value="#session.passwordIncorrectErrorMessageErrorMessageList">
<s:property />
<br>
</s:iterator>
</div>
</div>
</s:if>


<!-- 新PWが空欄の場合にエラーをだす -->
<s:if test="!#session.newPasswordErrorMessageList.isEmpty()">
<div class="error">
<div class="error-message">
<s:iterator value="#session.newPasswordErrorMessageList">
<s:property />
<br>
</s:iterator>
</div>
</div>
</s:if>


<!-- 確認用PWが空欄の場合にエラーをだす -->
<s:if test="!#session.reConfirmPasswordErrorMessageList.isEmpty()">
<div class="error">
<div class="error-message">
<s:iterator value="#session.reConfirmPasswordErrorMessageList">
<s:property />
<br>
</s:iterator>
</div>
</div>
</s:if>


<!-- 新PW相違の場合にエラーをだす -->
<s:if test="!#session.newPasswordIncorrectErrorMessageList.isEmpty()">
<div class="error">
<div class="error-message">
<s:iterator value="#session.newPasswordIncorrectErrorMessageErrorMessageList">
<s:property />
<br>
</s:iterator>
</div>
</div>
</s:if>


<!-- 入力テキストフォーム -->
<s:form action="ResetPasswordConfirmAction">
<table class="vertical-list-table">

<tr>
<th scope="row">
<s:label value="ログインID"/></th>
<td>
<s:textfield name="loginId" placeholder="ログインID" class="txt"/></td>
</tr>


<tr>
<th scope="row">
<s:label value="現在のパスワード"/></th>
<td>
<s:password name="password" placeholder="現在のパスワード" class="txt"/></td>
</tr>

<tr>
<th scope="row">
<s:label value="新しいパスワード"/></th>
<td>
<s:password name="newPassword" placeholder="新しいパスワード" class="txt"/></td>
</tr>

<tr>
<th scope="row">
<s:label value="(確認用)"/></th>
<td>
<s:password name="reConfirmationPassword" placeholder="新しいパスワード(確認用)" class="txt"/>
</td>

</table>

<s:submit value="パスワード再設定" class="submit_btn"/>

</s:form>
</div>

<s:include value="footer.jsp"/>

</body>
</html>