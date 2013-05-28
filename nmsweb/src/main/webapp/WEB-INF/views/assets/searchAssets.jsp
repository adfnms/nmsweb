<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="SearchAssets" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/assets.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/* ASSETS 데이터를 검색해서 가져온다 */
		getSearchAssetsList(searchAssetsList, "${category}");
		
	});
	
	//callback 함수 jsonObj를 이용 파싱 후 append
	function searchAssetsList(jsonObj) {
		var str = "<tr><th>ASSETS</th><th>ASSET LINK</th><th>NODE LINK</th></tr>";
		 str += assetsListStr(jsonObj);
		 $('#assetsListTable').empty();
		 $('#assetsListTable').append(str);
	}
	
	
	
	
</script>
</head>

<body>
	<div class="container">
		<jsp:include page="/include/menu.jsp" />
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" >Home</a> <span class="divider">/</span></li>
					<li><a href="/v1/assets.do">ASSETS</a><span class="divider">/</span></li>
					<li class="active">Search&nbsp;ASSETS</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<form id="searchAssetsFrm" name="searchAssetsFrm">
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<div class="span12">
							<h4>ASSETS</h4>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped" id="assetsListTable">
							<tr>
								<th>ASSETS</th>
								<th>ASSET LINK</th>
								<th>NODE LINK</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>