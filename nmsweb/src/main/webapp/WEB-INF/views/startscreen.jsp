<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.bluecapsystem.nms.define.NMSProperties"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="Index" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {


		getTotalIndexInfo(addIndexInfo, null);
		
	});

	function addIndexInfo(jsonObj) {
	
		var categoryObj = jsonObj["CategoryInfo"];
		var totalAvail = 0;
		var outageTotalCount = 0;
		var serviceTotalCount = 0;

		for ( var i in categoryObj) {
			
			var avail = Number(categoryObj[i]["availabili"]).toFixed(2) ;
			var outageCount = categoryObj[i]["outageTotalCount"];
			var serviceCount = categoryObj[i]["serviceTotalCount"];
			
			totalAvail += parseInt(avail);
			outageTotalCount += parseInt(outageCount);
			serviceTotalCount += parseInt(serviceCount);
			
		}

		
		if(outageTotalCount == 0){
			
			var statusTotal = "success";
			$('#smile').append("<a  href = '<c:url value="/index.do" />'><img style='margin-left: -150px;margin-top: 100px;' src='<c:url value="/resources/images/" />smilie.jpg' /></a>");
			setTimeout(refresh, 10000);
			
		}else{
			
			var statusTotal = "error";
			$('#smile').append("<a  href = '<c:url value="/index.do" />'><img style='margin-left: -150px;margin-top: 100px;' src='<c:url value="/resources/images/" />angry.jpg' /></a>");
			setTimeout(refresh, 10000);
		} 
		
		
		//document.location.href = '<c:url value="/index.do" />';
		
		
		// 전체 가용률
	}
	
	
	
	function refresh(){
		document.location.href = '<c:url value="/startscreen.do" />';
		
	}
</script>
</head>

<body>
	<div class="container">
		<jsp:include page="/include/menu.jsp" /> 
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value='/index.do'/>">Home</a><span class="divider">/</span></li>
				<!-- 	<li><a href="/v1/surveillance.do">surveillance</a> <span class="divider">/</span></li> -->
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span12" id= smile>
						<div class="span6">
						
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<hr>
	</div>
	<!-- /container -->
</body>

</html>
