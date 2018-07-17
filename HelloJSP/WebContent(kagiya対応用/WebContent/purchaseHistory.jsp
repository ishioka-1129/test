<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>商品購入履歴画面</title>
	</head>


	<body>
		<div>
			<h3>商品購入履歴画面</h3>
			<s:if test="#session.purchaseHistoryInfoDTOList.size()>0">
			<table>
				<tr>
					<th>商品名</th>
					<th>ふりがな</th>
					<th>値段</th>
					<th>発売会社名</th>
					<th>発売年月日</th>
				</tr>
				<s:iterator value="#session.purchaseHistoryInfoDTOList">
				<tr>
					<td><s:property value="productName"/></td>
					<td><s:property value="productNameKana"/></td>
					<td><s:property value="Price"/></td>
					<td><s:property value="ReleaseCompany"/></td>
					<td><s:property value="ReleaseDate"/></td>
					<td><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' width="50px" height="50px"/></td>

				</tr>
				</s:iterator>
			</table>
			<div>
			<s:form action="DeletePurchaseHistoryAction">
				<s:submit value="削除" />
			</s:form>
			</div>
			</s:if>
			<s:else>
				<div>商品購入履歴はありません。</div>
			</s:else>
		</div>
	</body>
</html>

