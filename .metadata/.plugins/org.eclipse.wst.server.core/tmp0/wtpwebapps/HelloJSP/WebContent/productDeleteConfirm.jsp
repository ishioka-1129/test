<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ProductDeleteConfirm</title>

</head>

<body>

	<div>
	<s:include value="header.jsp" />

		<div>
			<h2>以下の商品を削除してもよろしいですか？</h2>

			<s:form action="ProductDeleteCompleteAction" method="post">

				<s:iterator value="productDeleteList">
					<br><s:property value="productName" />
					<br><img src='<s:property value="imageFilePath" /><s:property value="imageFileName"/>'style="width:150px; height:150px;">
				</s:iterator>

				<br><p><s:submit value="削除する" /></p>

			</s:form>

			<form action="ProductDeleteAction" method="post">

			<input type="hidden" name="pageNo" value="1">
			<s:submit value="戻る"/>
			</form>
		</div>
		<s:include value="footer.jsp" />
	</div>

</body>
</html>