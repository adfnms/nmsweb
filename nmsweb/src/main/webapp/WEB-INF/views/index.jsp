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
	<jsp:param value="노드관리" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script type="text/javascript">
	$(document).ready(function(){
		getTotalRequisitionsList(requisitionsList);
	});
	
	function requisitionsList(jsonObj){
		
		var objList = jsonObj["model-import"];
		
		for ( var i in objList) {

			$('#requisitionsList').append(
					"<option value="+objList[i]["@foreign-source"]+">"+objList[i]["@foreign-source"]+"</option>"
			);
			
		}
		
	}
	
	function addCategories(){
		
		$('#cateDiv').after($('#cateDiv').clone().removeAttr("id"));
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span9">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
