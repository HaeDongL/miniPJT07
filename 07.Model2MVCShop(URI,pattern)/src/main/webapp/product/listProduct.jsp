<%@page import="com.model2.mvc.service.domain.Product"%>
<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.List"  %>
<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	List<Product> list= (List<Product>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");
	//==> null 을 ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
	String menu = (String)request.getAttribute("menu");
	System.out.println("listProduct.jsp menu : "+menu);
--%>

<html>
<head>
<title>회원 목록 조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncGetList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">


<form name="detailForm" action="/product/listProduct?menu=${menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<c:if test="${menu == 'manage'}">
					<td width="93%" class="ct_ttl01">상품 관리</td>
				</c:if>
				<c:if test="${menu == 'search'}">
					<td width="93%" class="ct_ttl01">상품 목록조회</td>
				</c:if>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${search.searchCondition == "0" ? "selected" : "" }>상품번호</option>
				<option value="1" ${search.searchCondition == "1" ? "selected" : ""}>상품명</option>
				<option value="2" ${search.searchCondition == "2" ? "selected" : ""}>상품가격</option>
			</select><%-- 어떤조건으로 검색하던 그 조건이 검색이후에도 남아있음. --%>
			<input 	type="text" name="searchKeyword" value="${search.searchKeyword }"  class="ct_input_g" 
							style="width:200px; height:20px" > <%-- 검색조건을 유지시켜주면 바끼지않음. --%>
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >
			전체  ${resultPage.totalCount} 건수,	현재 ${resultPage.currentPage} 페이지
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="i" value="0" /><%--pageScope.setAttribute("i",0); --%>
	<c:forEach var="product" items="${list}"><%--Start forEach --%>
		<c:set var="i" value="${ i+1 }" /><%--~Scoep.i+1 현재 i에 0 저장 for문이 돌때마다 1식증가--%>
	<tr class="ct_list_pop">
		<td align="center">${i}</td>
		<td></td>
		<td align="left">
			<c:if test="${menu == 'manage' }">
				<a href="/product/getProduct?prodNo=${product.prodNo}&menu=${menu}">${product.prodName }</a>
			</c:if>
			<c:if test="${menu == 'search' }">
				<a href="/product/getProduct?prodNo=${product.prodNo}&menu=${menu}">${product.prodName }</a>
			</c:if>
		</td>
		<td></td>
		<td align="left">${product.price }</td>
		<td></td>
		<td align="left">${product.regDate}</td>
		<td></td>
		<c:if test = "${menu == 'manage'}">
			<c:if test="${product.proTranCode == 0}">
			<td align="left">판매중</td> <%-- 추후 상품구매관리할때 변경할것. --%>
			</c:if>
			<c:if test="${product.proTranCode == 1}">
			<td align="left">구매완료&nbsp;<a href="/purchase/updateTranCode?tranCode=2&prodNo=${product.prodNo }&menu=${menu}">배송하기</a></td> <%-- 추후 상품구매관리할때 변경할것. --%>
			</c:if>
			<c:if test="${product.proTranCode == 2}">
			<td align="left">배송중</td> <%-- 추후 상품구매관리할때 변경할것. --%>
			</c:if>
			<c:if test="${product.proTranCode == 3}">
			<td align="left">배송완료</td> <%-- 추후 상품구매관리할때 변경할것. --%>
			</c:if>			
		</c:if>
		
		<c:if test = "${menu == 'search'}">
			<c:if test="${product.proTranCode == 0}">
			<td align="left">판매중</td> <%-- 추후 상품구매관리할때 변경할것. --%>
			</c:if>
			<c:if test="${product.proTranCode == 1 || product.proTranCode == 2 || product.proTranCode == 3}">
			<td align="left">재고없음</td> <%-- 추후 상품구매관리할때 변경할것. --%>
			</c:if>
		</c:if>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach><%--End forEach --%>
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