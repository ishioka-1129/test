<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css"/>

<title>管理者画面</title>

<!-- css等は後に追加 -->

</head>

<body>

	<div>
	<s:include value="header.jsp" />

		<div>
			<h1>管理者画面</h1>

			<div>
			<!-- class名等は後で調整します -->

				<form action="ProductAddAction" method="post">
					<s:submit value="商品追加" class="tekitou">
					</s:submit>
				</form>
				<br><br>
				<form action="ProductDeleteAction" method="post">
					<input type="hidden" name="pageNo" value="1">
					<s:submit value="商品削除" class="tekitou">
					</s:submit>
				</form>

			</div>

		</div>
		<s:include value="footer.jsp" />
	</div>

</body>
</html>