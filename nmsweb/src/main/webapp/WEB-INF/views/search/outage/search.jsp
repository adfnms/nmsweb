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
	<jsp:param value="장애 검색" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	var pageNum = 1;
	var limit =20;
	var offset = 0;
	var pageSize = 10;
	$(document).ready(function() {
		
		/* 장애 목록 검색 */
		getTotalOutagesList(addOutage, "&orderBy=ifLostService&order=desc&limit="+limit+"&offset="+offset);
		
	});
		/* 장애 목록*/
	function addOutage(jsonObj){
			var str = getTabletagToOutageSearchJsonObj(jsonObj);
			$('#outageListTable').empty();
			$('#outageListTable').append(str);
			
		getPagingHtml($('#pagingDiv'), "goSearchPageing", jsonObj["@totalCount"], pageNum, limit, pageSize );
	}
	
		/* 장애 ID 검색*/
	function searchOutageId(){
		seachOutageToOutageId(addOutage, $('#outageId').val());
	}
		/* 노드 ID 검색 */
	function searchNodeId(){
		
		// var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice >
		// '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
		
		
// 		seachOutageToNodeId(addOutage, $('#nodeId').val());
		pageNum = 1;
		offset = 0;
		
		var nodeId =  $('#nodeId').val();
		if (nodeId == null) {
			alert("노드 아이디가 없습니다.");
			return;
		}
		
		var query ="query=this_.nodeId='" + nodeId+ "'";
		var filter = "&orderBy=ifLostService&order=desc&limit=" + limit+"&offset="+offset;
		
		getTotalOutagesList(addOutage, query + filter);
		
	}
	
	//페이징 처리 스크립트
	function goSearchPageing(pageNm){
		pageNum = pageNm;
		offset = (parseInt(pageNm)-1) * limit;
		var query="";
		var nodeId = $('#nodeId').val();
		
		if(nodeId!=null)
		{
			 query ="query=this_.nodeId='" + nodeId+ "'";
		}
		var filter = "&orderBy=ifLostService&order=desc&limit="+limit+"&offset="+offset;
		
		getTotalOutagesList(addOutage, query + filter);
		
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
					<li class="active">장애 검색<span class="divider">/</span></li>
					<li><a href="#" onclick="javascript:beforeUrl()">이전 화면</a><span class="divider">/</span></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<form id="searchOutageFrm" name="searchOutageFrm">
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<div class="span12">
							<h4>장애&nbsp;검색</h4>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label" for="serviceId">장애 ID</label>
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
						<h4>장애&nbsp;목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped table-condensed table-hover" id="outageListTable">
							<tr>
								<th>ID</th>
								<th>인터페이스</th>
								<th>서비스</th>
								<th>장애 시간</th>
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
		<hr/>
	</div>
	<!-- /container -->
</body>
</html>
