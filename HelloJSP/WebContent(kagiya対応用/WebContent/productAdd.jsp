<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css"/>

<title>ProductAdd</title>

<!-- css等は後に追加 -->

	<script>
		showImage(false);

		$("#loadFile").onchange = function(evt) {
			showImage(false);
			var files = evt.target.files;
			if (files.length == 0)
				return;
			var file = files[0];
			if (!file.type.match(/image/)) {
				alert('画像ファイルを選んでください');
				return;
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				$("#thumb").src = reader.result;
				showImage(true);
			}
			reader.readAsDataURL(file);
		}

		function showImage(b) {
			var val = b ? "block" : "none";
			$("#up_btn").style.display = val;
			$("#thumb").style.display = val;
		}

		function $(id) {
			return document.querySelector(id);
		}
</script>

</head>

<body>

	<div>
	<s:include value="header.jsp" />

		<div>
			<s:form method="post" action="ProductAddConfirmAction">

				<table class="tekitou">

					<tr>
						<th>商品ID:</th>
						<td><s:textfield name="productId" value="%{productId}"/></td>
							<td class="errorMessage"><s:property value="productIdError"/><s:property value="productIdCheckError"/></td>
					</tr>

					<tr>
						<th>商品名:</th>
						<td>
							<s:textfield name="productName" value="%{productName}"/></td>
							<td class="errorMessage"><s:property value="productNameError"/>
						</td>
					</tr>

					<tr>
						<th>商品名(かな):</th>
						<td>
							<s:textfield name="productNameKana" value="%{productNameKana}"/></td>
							<td class="errorMessage"><s:property value="productNameKanaError"/>
						</td>
					</tr>

					<tr>
						<th>商品詳細:</th>
						<td>
							<s:textfield name="productDescription" value="%{productDescription}"/></td>
							<td class="errorMessage"><s:property value="productDescriptionError"/>
						</td>
					</tr>

					<tr>
						<th>カテゴリーID:</th>
						<td>
							<!-- <s:textfield name="categolyId" value="%{categolyId}"/> -->
							<s:select name="categoryId" list="#session.adminCategoryDTOList" listValue="categoryName" listKey="categoryId" class="select" id="categoryId" />
						</td>
					</tr>

					<tr>
						<th>価格:</th>
						<td>
							<s:textfield name="price" value="%{price}"/></td>
							<td class="errorMessage"><s:property value="priceError" />
						</td>
					</tr>

					<tr>
						<th>発売会社:</th>
						<td>
							<s:textfield name="releaseCompany" value="%{releaseCompany}"/></td>
							<td class="errorMessage"><s:property value="releaseCompanyError"/>
						</td>
					</tr>

					<tr>
						<th>商品画像:</th>
						<td>
							<input id="imageFilePath" type="file" name="image" ></td>
							<td class="errorMessage"><s:property value="imageFilePathError"/>
						</td>
					</tr>

				</table>

				<br><s:submit class="tekitou" value="商品を追加" />

			</s:form>

		</div>
		<s:include value="footer.jsp" />

	</div>

</body>
</html>