<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="./css/style.css">

<title>パスワード再設定完了</title>

<!-- 3秒後にhome.jspに戻すアクション -->
<meta http-equiv="refresh" content="3;URL=home.jsp">

</head>
<body>

<jsp:include page="header.jsp"/>

<div id="contents">
<h1>パスワード再設定完了画面</h1>
パスワード再設定が完了しました。
3秒後にホーム画面に戻ります。
</div>

<s:include value="footer.jsp"/>

</body>
</html>