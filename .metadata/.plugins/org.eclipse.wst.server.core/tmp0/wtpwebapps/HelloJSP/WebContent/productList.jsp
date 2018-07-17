<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/style.css">
		<title>ProductList</title>
	</head>
<body>

	<!-- ヘッダー -->
	<jsp:include page="header.jsp"/>

	<div>
		<!-- productInfoDTOListに商品情報がない場合の表示 -->
		<s:if test="productInfoDTOList == null">
			<div class="info">
				検索結果がありません。
			</div>
		</s:if>

		<!-- 商品一覧を表示 -->
		<s:else>
			<div>
				<s:iterator value="#session.productInfoDTOList">
					<div>
						<ul>
							<li>
								<!-- 商品画像 -->
								<a href='<s:url action="ProductDetailsAction"><s:param name="productId" value="%{productId}"/></s:url>'>
									<img src='<s:property value="imageFilePath"/><s:property value="imageFileName"/>'/>
								</a><br>
								<!-- 商品名 -->
								<s:property value="productName"/><br>
								<!-- 商品名（かな） -->
								<s:property value="productNameKana"/><br>
								<!-- 価格 -->
								<s:property value="price"/>円<br>
							</li>
						</ul>
					</div>
				</s:iterator>

				<!-- ページ番号 -->
				<div>
					<!-- 前へボタン -->
					<s:if test="#session.hasPreviousPage">
						<a href='<s:url action="SearchItemAction">
						<s:param name="pageNo" value="#session.previousPageNo" />
						</s:url>'>&laquo;</a>
					</s:if>

					<s:iterator begin="1" end="#session.totalPageSize" status="pageNo">
						<s:if test="#session.currentPageNo == #pageNo.count">
							<s:property value="%{#pageNo.count}"/>
						</s:if>
						<s:else>
							<a href="<s:url action='SearchItemAction'><s:param name='pageNo' value='%{#pageNo.count}'/>
							<s:param name='categoryId' value='%{categoryId}'/><s:param name='keywords' value='%{keywords}'/></s:url>"><s:property value="%{#pageNo.count}"/></a>
						</s:else>
					</s:iterator>

					<!-- 次へボタン -->
					<s:if test="#session.hasNextPage">
						<a href='<s:url action="SearchItemAction">
						<s:param name="pageNo" value="#session.nextPageNo" />
						</s:url>'>&raquo;</a>
					</s:if>
				</div>
			</div>
		</s:else>
	</div>

	<!-- フッター -->
	<jsp:include page="footer.jsp"/>

</body>
</html>