<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/style.css">
		<title>ProductDetails</title>
	</head>
<body>

	<!-- ヘッダー -->
	<jsp:include page="header.jsp"/>

	<!-- 商品詳細情報表示 -->
	<div>
		<s:form action="AddCartAction">
			<!-- 商品画像 -->
			<img src='<s:property value="#session.imageFilePath"/>/<s:property value="#session.imageFileName"/>' class="item-image-box-320"/><br>

			<table>
				<tr>
					<th>商品名</th>
					<td><s:property value="#session.productName"/></td>
				</tr>
				<tr>
					<th>商品名(かな)</th>
					<td><s:property value="#session.productNameKana"/></td>
				</tr>
				<tr>
					<th>値段</th>
					<td><s:property value="#session.price"/>円</td>
				</tr>
				<tr>
					<th>購入個数</th>
					<td>
						<select name="productCount">
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
					</td>
				</tr>
				<tr>
					<th>発売会社名</th>
					<td><s:property value="#session.releaseCompany"/></td>
				</tr>
				<tr>
					<th>発売年月日</th>
					<td><s:property value="#session.releaseDate"/></td>
				</tr>
				<tr>
					<th>商品詳細情報</th>
					<td><s:property value="#session.productDescription"/></td>
				</tr>
				<tr>
					<td></td>
					<td><s:submit value="カートに追加"/></td>
				</tr>
			</table>

			<s:hidden name="productId" value="%{#session.productId}"/>
			<s:hidden name="productName" value="%{#session.productName}"/>
			<s:hidden name="productNameKana" value="%{#session.productNameKana}"/>
			<s:hidden name="imageFilePath" value="%{#session.imageFilePath}"/>
			<s:hidden name="imageFileName" value="%{#session.imageFileName}"/>
			<s:hidden name="price" value="%{#session.price}"/>
			<s:hidden name="releaseCompany" value="%{#session.releaseCompany}"/>
			<s:hidden name="releaseDate" value="%{#session.releaseDate}"/>
			<s:hidden name="productDescription" value="%{#session.productDescription}"/>
		</s:form>
	</div>

	<!-- 同カテゴリ別の別商品を表示 -->
	<div>
		<s:iterator value="#session.productInfoDTOList">
		<div>
			<a href='<s:url action="ProductDetailsAction"> <s:param name="productId" value="%{productId}"/></s:url>'>
			<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'></a>
			<s:property value="productName"/><br>
		</div>
		</s:iterator>
	</div>

	<!-- フッター -->
	<jsp:include page="footer.jsp"/>


</body>
</html>