
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
<title>���ſ�û������</title>

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

<form name="detailForm" action="/purchase/requestPurchaseList" method="post">
<input type="hidden" name="menu" value="manage" />
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���ſ�û ���</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ǰ�̸�</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">���Ű���</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">����ݾ�</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="count" value="0"/>
	<c:forEach var="purchase" items="${list}"><%--StartFor --%>
	
	<tr class="ct_list_pop">
		<td align="center">
		<c:set var="count" value="${count+1}"/>
			<a href="/purchase/getPurchase?tranNo=${purchase.tranNo }">${count}</a>
		</td>
		<td></td>
		<td align="left">
			<a href="/user/getUser?userId=${purchase.buyer.userId }">${purchase.buyer.userId }</a>
		</td>
		<td></td>
		<td align="left">${purchase.receiverName }</td>
		<td></td>
		<td align="left">${purchase.receiverPhone }</td>
		<td></td>
		<td align="left">${purchase.purchaseProd.prodName }</td>
		<td></td>
		<td align="left">${purchase.buyQuantity }</td>
		<td></td>
		<td align="left">${purchase.buyQuantity * purchase.purchaseProd.price }</td>
		<td></td>
		
		<c:if test="${purchase.tranCode == 1}">
			<td align="left">���ſϷ�&nbsp;<a href="/purchase/updateTranCode?tranNo=${purchase.tranNo }&menu=${menu}&tranCode=2">����ϱ�</a></td> 
			</c:if>
			<c:if test="${purchase.tranCode == 2}">
			<td align="left">�����</td> 
			</c:if>
			<c:if test="${purchase.tranCode == 3}">
			<td align="left">��ۿϷ�</td> 
		</c:if>
		</td>
		<td></td>
		<td align="left">
			
		</td>
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
			���� ���ԿϷ� ���� �Դϴ�.
		<%}else if(purchase.getTranCode().equals("2")){ %>
			���� ���ԿϷ� ���� �Դϴ�.<a href="/updateTranCode.do?prodNo=<%=purchase.getPurchaseProd().getProdNo()%>&tranCode=3">���ǵ���</a>
		<%}else{ %>
			���� ��ۿϷ� ���� �Դϴ�.
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
	<%-- /////////////////////// EL / JSTL �������� �ּ� ó�� //////////////////////// 		   
	<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
			�� ����
	<% }else{ %>
			<a href="javascript:fncGetUserList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
	<% } %>

	<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
			<a href="javascript:fncGetUserList('<%=i %>');"><%=i %></a>
	<% 	}  %>
	
	<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
			���� ��
	<% }else{ %>
			<a href="javascript:fncGetUserList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
	<% } %>
	 /////////////////////// EL / JSTL �������� �ּ� ó�� //////////////////////// --%>
	
		<jsp:include page="../common/pageNavigator.jsp" />	
			
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->

</form>
</div>

</body>
</html>
