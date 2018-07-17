<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- 1秒後にhome.jspに遷移させる -->
 		<meta http-equiv="refresh" content="0;URL='StartAction'">
		<title>スタート画面</title>
	</head>
<body>

	<!-- ヘッダー -->
	<jsp:include page="header.jsp"/>

	<div>
		スタート画面
	</div>

	<!-- フッター -->
	<jsp:include page="footer.jsp"/>

</body>
</html>