
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@page import="com.model2.mvc.common.Search"%>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%

List<Purchase> list= (List<Purchase>)request.getAttribute("list");
Page resultPage=(Page)request.getAttribute("resultPage");
Search search = (Search)request.getAttribute("search");

%>
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script type="text/javascript">
	function fncGetList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/purchase/listPurchase" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">구매 수량</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">결재금액</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="count" value="0"/>
	<c:forEach var="purchase" items="${list}"><%--StartFor --%>
	
	<tr class="ct_list_pop">
		<td align="center">
		<c:set var="count" value="${count+1}"/>
			${count}
		</td>
		<td></td>
		<td align="left">
			<a href="/product/getProduct?prodNo=${purchase.purchaseProd.prodNo }">${purchase.purchaseProd.prodName }</a>
		</td>
		<td></td>
		<td align="left">${purchase.buyQuantity }</td>
		<td></td>
		<td align="left">${purchase.purchaseProd.price * purchase.buyQuantity }원</td>
		<td></td>
			
		<td align="left">
		<c:if test="${purchase.tranCode == 1 }">
			현재 구입완료 상태 입니다.
		</c:if>
		<c:if test="${purchase.tranCode == 2 }">
			현재 구입완료 상태 입니다.<a href="/purchase/updateTranCode?tranNo=${purchase.tranNo }&tranCode=3">물건도착</a>
		</c:if>
		<c:if test="${purchase.tranCode == 3 }">
			현재 배송완료 상태 입니다.
		</c:if>
		</td>
		<td></td>
		<c:if test="${purchase.tranCode == 1 }">	
		<td align="left">
			<a href="/purchase/getPurchase?tranNo=${purchase.tranNo }">정보수정</a>
		</td>
		</c:if>
		<c:if test="${purchase.tranCode == 2 || purchase.tranCode == 3 }">	
		<td align="left">
			수정불가
		</td>
		</c:if>
	</tr>
	</c:forEach><%--end For --%>
	
	<%--<%for(int i=0; i<list.size(); i++) {
	
	<%Purchase purchase = list.get(i);%>
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%=purchase.getTranNo()%>"><%=i %></a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=user02"><%=purchase.getBuyer().getUserId() %></a>
		</td>
		<td></td>
		<td align="left"><%=purchase.getReceiverName() %></td>
		<td></td>
		<td align="left"><%=purchase.getReceiverPhone() %></td>
		<td></td>
		
		<td align="left">
		
		
		<%if(purchase.getTranCode().equals("1")) {%>
			현재 구입완료 상태 입니다.
		<%}else if(purchase.getTranCode().equals("2")){ %>
			현재 구입완료 상태 입니다.<a href="/updateTranCode.do?prodNo=<%=purchase.getPurchaseProd().getProdNo()%>&tranCode=3">물건도착</a>
		<%}else{ %>
			현재 배송완료 상태 입니다.
		<%} %>
		</td>
		<td></td>
		<td align="left">
			
		</td>
	</tr>
	<%} %>
	--%>
	
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	
</table>

<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
	<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// 		   
	<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
			◀ 이전
	<% }else{ %>
			<a href="javascript:fncGetUserList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
	<% } %>

	<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
			<a href="javascript:fncGetUserList('<%=i %>');"><%=i %></a>
	<% 	}  %>
	
	<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
			이후 ▶
	<% }else{ %>
			<a href="javascript:fncGetUserList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
	<% } %>
	 /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
	
		<jsp:include page="../common/pageNavigator.jsp" />	
			
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->

</form>
</div>

</body>
</html>
