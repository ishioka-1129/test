<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ProductDelete</title>

</head>

<body>

	<div>
	<s:include value="header.jsp" />

		<div>

			<s:if test="errorMessage !=null">
				<h1><s:property value="errorMessage" /></h1>
			</s:if>

			<p>削除する商品を選択してください</p>

			<s:form action="ProductDeleteConfirmAction">

				<ul>
					<s:iterator value="#session.productInfoDTOList">
						<li><img style="width:200px; height:200px;" src='<s:property value="imageFilePath"/><s:property value="imageFileName"/>'><br>
							<s:property value="productName" /><br>
							<s:property value="price" /><span>円</span><br>
							<input type="checkbox" name="deleteName" value="<s:property value='productName'/>">
						</li>
					</s:iterator>
				</ul>

				<tr>
					<td><s:submit value="削除する" /></td>
				</tr><br>

			</s:form>

				<s:iterator begin="1" end="#session.totalPageSize" status="pageNo">
						<s:if test="#session.currentPageNo == #pageNo.count">
							<s:property value="%{#pageNo.count}"/>
						</s:if>
						<s:else>
							<a href="<s:url action='AdminPaginationAction'><s:param name='pageNo' value='%{#pageNo.count}'/>
							<s:param name='categoryId' value='%{categoryId}'/><s:param name='keywords' value='%{keywords}'/><s:param name='type' value='1'/></s:url>"><s:property value="%{#pageNo.count}"/></a>
						</s:else>
					</s:iterator>


		</div>
		<s:include value="footer.jsp" />
	</div>

</body>
</html>