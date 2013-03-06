<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<table width="100%" border="0" >
	<tr> 
		<td width="85%" align="center">
			<c:if test="${count > 0}">
			   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
			   
			   <c:choose>
      				<c:when test="${empty pageBlock}"> 
      					<c:set var="pageBlock" value="${10}"/>
      				</c:when>
			   		<c:otherwise>
						<c:set var="pageBlock" value="${pageBlock}"/>
					</c:otherwise>
			   </c:choose>
			   
			   <fmt:parseNumber var="result" value="${currentPage / pageBlock}" integerOnly="true" />
			    <c:set var="startPage" value="${result * pageBlock + 1}" />
			   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
			   <fmt:formatNumber var="page" value="${endPage}" maxFractionDigits="1" minFractionDigits="1"/> 
			   <c:if test="${endPage > pageCount}">
			        <c:set var="endPage" value="${pageCount}"/>
			   </c:if> 
			   
			   <c:if test="${startPage > pageBlock}">
			       <a href="JavaScript:list_pageview('${startPage - pageBlock }')">◀</a>
			   </c:if>
			 
			   <c:forEach var="i" begin="${startPage}" end="${endPage}">
			   	   <c:if test="${pageNum == i }">
			       	<a href="JavaScript:list_pageview('${i}')" id="${i}" ><font color="red"><b>[${i}]</b></font></a>
			   	   </c:if>
			   	   <c:if test="${pageNum != i }">
			       	<a href="JavaScript:list_pageview('${i}')" id="${i}" >[${i}]</a>
			   	   </c:if>	
			   </c:forEach>
			
			   <c:if test="${endPage < pageCount}">
			        <a href="JavaScript:list_pageview('${startPage + pageBlock}')">▶</a>
			   </c:if>
			</c:if>
				
		</td>
		
		<!--  
		<td valign="middle" align="left">
			<a href="JavaScript:pageview('${pageNum-1}','${FDroot}','${type}')"><img src="image/content/arrow_pre_off.gif"></a>&nbsp;
			<a href="JavaScript:pageview('${pageNum+1}','${FDroot}','${type}')"><img src="image/content/arrow_next_off.gif"></a>
		</td>
		-->
	</tr>
</table>
