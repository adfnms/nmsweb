<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.bluecapsystem.nms.define.NMSProperties"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="회원가입" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/js/userMng.js" />"></script>
<script type="text/javascript">

	$(document).ready(function(){
		//전체 리스트를 가져온다.
		getUserListTotal();

	});
	
	function callbackUseList(data){
		alert(data);
	}
	
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<div class="row-fluid">
			<div class="span9">
				<h2 class="muted">운영관리 > 사용자 관리</h2>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		
		<div class="row-fluid">
			<div class="span9">
				
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
