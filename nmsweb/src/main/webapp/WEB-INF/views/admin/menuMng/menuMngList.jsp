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

<script type="text/javascript">
	
	var pageNum = 1;
	var limit = 10;
	var offset = 0;
	var orderBy = "id";
  	
	$(document).ready(function(){
		
		getNodeTotalList(addNodeLists, "orderBy="+orderBy+"&limit="+limit);
		
	});
	
	//callback 함수 jsonObj를 이용 파싱 후 append
	function addNodeLists(jsonObj) {
		
		//총 갯수를 입력해준다.
		$('#nodeCount').empty();
		$('#nodeCount').append("메뉴&nbsp;목록&nbsp;["+jsonObj["@totalCount"]+"]");
		//총 갯수를 입력해준다.
		
		$('#nodeListTable').empty();

		var str = getTabletagToSearchJsonObj(jsonObj,"Y");
		
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
					<li><a href="#" onclick="javascript:beforeUrl()">이전 화면</a><span class="divider">/</span></li>
					<li class="active">메뉴 관리 목록</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span10">
						<h4 id="nodeCount">메뉴&nbsp;목록&nbsp;[0]</h4>
					</div>
					<div class="span2">
						<a type="button" class="btn btn-primary" title="" href="#popupRegMethod" data-toggle="modal">새 메뉴 등록</a> 
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped" id="nodeListTable"></table>
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
	
	<!-- ------------------------화면 등록 Popup창----------------------- -->	
 <div id="popupRegMethod" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">새&nbsp;그룹&nbsp;등록</h3>
	</div>
	<form id ="groupInfoFrm" name= "groupInfoFrm">
		<div class="modal-body" style="max-height: 800px;">
			<div class="row-fluid">
				<div class="span12">
					<label class="span3 control-label"><h4>그룹&nbsp;명</h4></label>
					<input type="text"   id="name"   name="name" class="span12"   placeholder=""> 
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<label class="span3 control-label"><h4>comments</h4></label>
					<textarea  rows="4" id="comments"  name="comments" class="span12"   placeholder=""></textarea> 
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<input type="hidden"   id="user"   name="user" class="span12"   placeholder=""> 
				</div>
			</div>
		</div>
	</form>
	<div class="modal-footer">
		<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="javascript:regGroup()">등록</button>
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div> 
	
	
</body>
</html>
