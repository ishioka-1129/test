<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ProductAddConfirm</title>

</head>

<body>

	<div>
	<s:include value="header.jsp" />

		<div>
			<p>以下の内容でお間違いないですか？</p>

				<s:form action="ProductAddCompleteAction">

					<table>
						<tr><th>商品ID</th><td><s:property value='#session.productId' /></td></tr>
						<tr><th>商品名</th><td><s:property value='#session.productName' /></td></tr>
						<tr><th>商品名(かな)</th><td><s:property value='#session.productNameKana' /></td></tr>
						<tr><th>商品詳細</th><td><s:property value='#session.productDescription' /></td></tr>
						<tr><th>カテゴリー</th><td>
							<s:if test="#session.categoryId == 2">
								<label>手持ち花火</label>
							</s:if>
							<s:elseif test="#session.categoryId == 3">
								<label>打ち上げ花火</label>
							</s:elseif>
							<s:elseif test="#session.categoryId == 4">
								<label>花火職人</label>
							</s:elseif>

						<!-- <s:property value='#session.categoryId' /></td></tr> -->
						<tr><th>価格</th><td><s:property value='#session.price' /></td></tr>
						<tr><th>発売会社</th><td><s:property value='#session.releaseCompany' /></td></tr>
						<tr><th>画像</th><td><img src='<s:property value="#session.imageFilePath" /><s:property value="#session.imageFileName" />' style="width:200px; height:200px;"></td></tr>
					</table><br><br>

					<s:submit value="追加" />

				</s:form>

				<br><s:form action="ProductAddAction">
						<s:submit value="戻って修正する">
							<s:hidden name="productId" value="%{productId}"/>
							<s:hidden name="productName" value="%{productName}"/>
							<s:hidden name="productNameKana" value="%{productNameKana}"/>
							<s:hidden name="productDescription" value="%{productDescription}"/>
							<s:hidden name="categolyId" value="%{categolyId}"/>
							<s:hidden name="price" value="%{price}"/>
							<s:hidden name="releaseCompany" value="%{releaseCompany}"/>
						</s:submit>
					</s:form>
		</div>
		<s:include value="footer.jsp" />

	</div>

</body>
</html>