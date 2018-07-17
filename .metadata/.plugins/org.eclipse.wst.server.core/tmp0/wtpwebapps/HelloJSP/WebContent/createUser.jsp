<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel = "stylesheet" href = "./css/style.css">
<title>ユーザー情報入力</title>
</head>
<body>

<h1>ユーザー情報入力画面</h1>

<!-- family_nameが空欄の場合にエラーをだす -->
<s:if test = "!#session.family_nameErrorMessageList.isEmpty()">
	<div class = "error">
	<div class = "error-message">
		<s:iterator value = "#session.family_nameErrorMessageList"><s:property/><br></s:iterator>
	</div>
	</div>
</s:if>

<!-- first_nameが空欄の場合にエラーをだす -->
<s:if test = "!#session.first_nameErrorMessageList.isEmpty()">
	<div class = "error">
	<div class = "error-message">
		<s:iterator value = "#session.first_nameErrorMessageList"><s:property/><br></s:iterator>
	</div>
	</div>
</s:if>

<!-- family_name_kanaが空欄の場合にエラーをだす -->
<s:if test = "!#session.family_name_kanaErrorMessageList.isEmpty()">
	<div class = "error">
	<div class = "error-message">
		<s:iterator value = "#session.family_name_kanaErrorMessageList"><s:property/><br></s:iterator>
	</div>
	</div>
</s:if>

<!-- first_name_kanaが空欄の場合にエラーをだす -->
<s:if test = "!#session.first_name_kanaErrorMessageList.isEmpty()">
	<div class = "error">
	<div class = "error-message">
		<s:iterator value = "#session.first_name_kanaErrorMessageList"><s:property/><br></s:iterator>
	</div>
	</div>
</s:if>

<!-- emailが空欄の場合にエラーをだす -->
<s:if test = "!#session.emailErrorMessageList.isEmpty()">
	<div class = "error">
	<div class = "error-message">
		<s:iterator value = "#session.emailErrorMessageList"><s:property/><br></s:iterator>
	</div>
	</div>
</s:if>

<!-- user_idが空欄の場合にエラーをだす -->
<s:if test = "!#session.user_idErrorMessageList.isEmpty()">
	<div class = "error">
	<div class = "error-message">
		<s:iterator value = "#session.user_idErrorMessageList"><s:property/><br></s:iterator>
	</div>
	</div>
</s:if>

<!-- passwordが空欄の場合にエラーをだす -->
<s:if test = "!#session.passwordErrorMessageList.isEmpty()">
	<div class = "error">
	<div class = "error-message">
		<s:iterator value = "#session.passwordErrorMessageList"><s:property/><br></s:iterator>
	</div>
	</div>
</s:if>

<!-- 入力テキストフォーム -->
<s:form action = "CreateUserConfirmAction">
	<table class = "vertical-list-table">
		<tr>
			<th scope ="row">姓</th>
			<td><s:textfield name = "family_name" value = "%{#session.family_name}" label = "姓" placeholder = "姓" class = "txt"/></td>
		</tr>

		<tr>
			<th scope ="row">名</th>
			<td><s:textfield name = "first_name" value = "%{#session.first_name}" label = "名" placeholder = "名" class = "txt"/></td>
		</tr>

		<tr>
			<th scope ="row">姓ふりがな</th>
			<td><s:textfield name = "family_name_kana" value = "%{#session.family_name_kana}" label = "姓ふりがな" placeholder = "姓ふりがな" class = "txt"/></td>
		</tr>

		<tr>
			<th scope ="row">名ふりがな</th>
			<td><s:textfield name = "first_name_kana" value = "%{#session.first_name_kana}" label = "名ふりがな" placeholder = "名ふりがな" class = "txt"/></td>
		</tr>

		<tr>
			<th scope ="row">性別</th>
			<td><s:radio name = "sex" list = "%{#session.sexList}" value = "%{#session.sex}" label = "性別" placeholder = "性別"/></td>
		</tr>

		<tr>
			<th scope ="row">メールアドレス</th>
			<td><s:textfield name = "email" value = "%{#session.email}" label = "メールアドレス" placeholder = "メールアドレス" class = "txt"/></td>
		</tr>

		<tr>
			<th scope ="row">ログインID</th>
			<td><s:textfield name = "user_id" value = "%{#session.user_id}" label = "ログインID" placeholder = "ログインID" class = "txt"/></td>
		</tr>

		<tr>
			<th scope ="row">パスワード</th>
			<td><s:password name = "password" value = "" label = "パスワード" placeholder = "パスワード" class = "txt"/></td>
		</tr>

	</table>

	<div class = "submit_btn_box">
	<div id = ".contents-btn-set">
	<s:submit value = "登録" class = "submit_btn"/>
	</div>
	</div>
</s:form>

</body>
</html>