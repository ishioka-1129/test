<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel = "stylesheet" href = "./css/style.css">
<title>登録内容確認</title>
</head>
<body>

<h1>登録内容確認画面</h1>

<!-- createUserで入力された値を表示。登録ボタンを押下すると
		CreateUserCompleteActionの結果に応じてcreateUserComplete.jspまたはにcreateUser.jsp遷移する -->
<s:form action = "CreateUserCompleteAction">
	<table class = "vertical-list-table">
		<tr>
			<th scope = "row"><s:label value = "姓"/></th>
			<td><s:property value = "family_name"/></td>
		</tr>

		<tr>
			<th scope = "row"><s:label value = "名"/></th>
			<td><s:property value = "first_name"/></td>
		</tr>

		<tr>
			<th scope = "row"><s:label value = "姓ふりがな"/></th>
			<td><s:property value = "family_name_kana"/></td>
		</tr>

		<tr>
			<th scope = "row"><s:label value = "名ふりがな"/></th>
			<td><s:property value = "first_name_kana"/></td>
		</tr>

		<tr>
			<th scope = "row"><s:label value = "性別"/></th>
			<td><s:property value = "sex"/></td>
		</tr>

		<tr>
			<th scope = "row"><s:label value = "メールアドレス"/></th>
			<td><s:property value = "email"/></td>
		</tr>

		<tr>
			<th scope = "row"><s:label value = "ログインID"/></th>
			<td><s:property value = "user_id"/></td>
		</tr>

		<tr>
			<th scope = "row"><s:label value = "パスワード"/></th>
			<td><s:property value = "password"/></td>
		</tr>
	</table>

	<div class = "submit_btn_box">
	<div id = ".contents-btn-set">
	<s:submit value = "登録" class = "submit_btn"/>
	</div>
	</div>

<!-- //入力された値をそれぞれのデータに渡す -->
	<s:hidden name = "family_name" value = "%{family_name}"/>
	<s:hidden name = "first_name" value = "%{first_name}"/>
	<s:hidden name = "family_name_kana" value = "%{family_name_kana}"/>
	<s:hidden name = "first_name_kana" value = "%{first_name_kana}"/>
	<s:if test = 'sex.equals("男性")'>
	<s:hidden name = "sex" value = "0"/>
	</s:if>
	<s:if test = 'sex.equals("女性")'>;
	<s:hidden name = "sex" value = "1"/>
	</s:if>
	<s:hidden name = "email" value = "%{email}"/>
	<s:hidden name = "user_id" value = "%{user_id}"/>
	<s:hidden name = "password" value = "%{password}"/>
</s:form>

</body>


</html>