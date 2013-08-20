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
	<jsp:param value="노드목록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/surveillance.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		// getNodeListToCategoryName(addNodeLists, "${cateNm}"); 
		
		//해당 카테고리에 등록되어있는 노드정보를 가져온다.
		getNodeToSurveillance(getRegNodeList, "${categoryId}");
	});
	
	
	function getRegNodeList(jsonObj) {
		
		var str = regNodeListStr(jsonObj);
		$('#nodeListTable').append(str);
	}
	
	function addNodeCategory(){
		
		
		
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
					<li class="active">노드 목록</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
				<li class="active"><h4><a href="<c:url value="/index.do" />">surveillance : </a></h4></li>
				<li><h4 class="text-success">${categoryname}</h4></li>
				</ul>
			</div>
			
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped" id="nodeListTable"></table>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="span10">
				</div>
				<div class="span2">
				<!--  -->
				
					 <a type="button" class="btn btn-primary span12" data-toggle="modal" title="노드추가"
						href="#mySurvaillenceModal" onclick="javascript:addNodeCategory();">Add Node</a>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
<!-- Modal -->
<div id="mySurvaillenceModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalSurvaillence" aria-hidden="true" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalSurvaillence" >장애정보</h3>
	</div>
	<div class="modal-body">
		<div class="row-fluid" id=""></div>
	</div>
	<div class="modal-footer">
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>
<!-- Modal -->
</html>