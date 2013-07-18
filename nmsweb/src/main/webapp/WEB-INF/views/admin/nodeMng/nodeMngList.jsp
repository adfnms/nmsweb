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
	<jsp:param value="노드관리" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	
	var pageNum = 1;
	var limit = 10;
	var offset = 0;
	var orderBy = "id";
  	
	$(document).ready(function(){
		
		getNodeTotalList(addNodeLists, "orderBy="+orderBy+"&limit="+limit);
		
	});
	
	//getNodeTotalList callback 함수 
	function addNodeLists(jsonObj) {
		
		//총 갯수를 입력해준다.
		$('#nodeCount').empty();
		$('#nodeCount').append("노드&nbsp;목록&nbsp;["+jsonObj["@totalCount"]+"]");
		//총 갯수를 입력해준다.
		
		$('#nodeListTable').empty();

		var str = getTabletagToSearchJsonObj(jsonObj,"Admin");
		
		$('#nodeListTable').append(str);
		
		//페이징 HTML 가져오기
		getPagingHtml($('#pagingDiv'), "goSearchPageing", jsonObj["@totalCount"], pageNum, "20", "5" );

	}
	
	//페이징 처리 스크립트
	function goSearchPageing(pageNm){
		
		pageNum = pageNm;
		
		offset = (parseInt(pageNm)-1) * limit;
		
		getNodeTotalList(addNodeLists, "orderBy="+orderBy+"&limit="+limit+"&offset="+offset);
		
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />">Home</a> <span class="divider">/</span></li>
					<li><a href="<c:url value="/admin/node.do" />">운영관리</a> <span class="divider">/</span></li>
					<li class="active">노드 관리 목록</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span10">
						<h4 id="nodeCount">노드&nbsp;목록&nbsp;[0]</h4>
					</div>
					<div class="span2">
						<button type="button" class="btn btn-primary span12" title="노드 추가"
							onclick="javascript:addNodePop();">+&nbsp;노드 추가</button>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped table-hover" id="nodeListTable"></table>
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
