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
	<jsp:param value="중단 검색" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
	var pageNum = 1;
	var limit =20;
	var offset = 0;
	$(document).ready(function() {
		
		getTotalOutagesList(addOutage, "limit="+limit+"&offset="+offset);
		
	});
	
	function addOutage(jsonObj){
		
		console.log("addOutage");
		console.log(jsonObj);
		
		var str = "<tr><th>ID</th><th>인터페이스</th><th>중단 시간</th><th>회복 시간</th><th>서비스</th></tr>";
		str += getTabletagToOutageSearchJsonObj(jsonObj);
		$('#outageListTable').empty();
		$('#outageListTable').append(str);
		
		getPagingHtml($('#pagingDiv'), "goSearchPageing", jsonObj["@totalCount"], pageNum, "10", "10" );
	}
	
	function searchOutageId(){
		seachOutageToOutageId(addOutage, $('#outageId').val());
	}
	
	function searchNodeId(){
		seachOutageToNodeId(addOutage, $('#nodeId').val());
	}
	
	//페이징 처리 스크립트
	function goSearchPageing(pageNm){
		
		pageNum = pageNm;
		
		offset = (parseInt(pageNm)-1) * limit;
		
		getTotalOutagesList(addOutage, "limit="+limit+"&offset="+offset);
		
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" />Home</a> <span class="divider">/</span></li>
					<li class="active">중단 검색</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<form id="searchOutageFrm" name="searchOutageFrm">
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<div class="span12">
							<h4>중단&nbsp;검색</h4>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label" for="serviceId">중단 ID</label>
									<div class="span3 controls">
										<input type="text" id="outageId"
											class="span12" name="outageId" value="" />
									</div>
									<div class="span1">
										<button type="button" class="btn btn-primary span12" title="검색"
											onclick="javascript:searchOutageId();">검색</button>
									</div>
									
									<label class="span2 control-label" for="ipAddress">노드 ID</label>
									<div class="span3 controls">
										<input type="text" id="nodeId"
											class="span12" name="nodeId" value="" />
									</div>
									<div class="span1">
										<button type="button" class="btn btn-primary span12" title="검색"
											onclick="javascript:searchNodeId();">검색</button>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="span10"></div>
									<div class="span2">
										<button type="button" class="btn btn-primary span12" title="검색"
											onclick="javascript:location.reload();">Clean</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>중단&nbsp;목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped table-condensed" id="outageListTable">
							<tr>
								<th>ID</th>
								<th>인터페이스</th>
								<th>서비스</th>
								<th>중단 시간</th>
								<th>회복 시간</th>
							</tr>
						</table>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12" id="pagingDiv"></div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
