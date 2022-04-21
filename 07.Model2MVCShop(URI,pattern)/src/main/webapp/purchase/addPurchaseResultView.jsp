
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	Purchase purchase = (Purchase)request.getAttribute("purchase");
	System.out.println("addPurchaseResultView.jsp purchaseVO : "+purchase);
%><%--데이터 이동 확인용 --%>




<html>
<head>
<title>Insert title here</title>
</head>

<body>


다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td>${purchase.purchaseProd.prodNo }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>${purchase.buyer.userId }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
			<c:if test="${purchase.paymentOption == 1 }">
				현금구매
			</c:if>
			<c:if test="${purchase.paymentOption == 2 }">
				신용구매
			</c:if>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${purchase.receiverName }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${purchase.receiverPhone }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${purchase.receiverAddr }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매요청사항</td>
		<td>${purchase.receiverRequest }</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${purchase.receiverDate }</td>
		<td></td>
	</tr>
	<tr>
		<td>구매개수</td>
		<td>${purchase.buyQuantity }개</td>
		<td></td>
	</tr>
	<tr>
		<td>결재금액</td>
		<td>${purchase.purchaseProd.price * purchase.buyQuantity }원</td>
		<td></td>
	</tr>
</table>


</body>
</html>