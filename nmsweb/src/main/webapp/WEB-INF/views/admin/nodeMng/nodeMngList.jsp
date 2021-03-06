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
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
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

		var str = getTabletagToSearchJsonObj(jsonObj,"<c:out value='${GROUP_NM}'/>");
		
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
	
	
	function getTotalRequisitions(jsonObj){
		$("#requisitionsListTable").empty();
		var str = getTableToRequisitionsJsonObj(jsonObj);
		$("#requisitionsListTable").append(str);
	}
	
	function deleteRequisitionInNode(nodeId,foreignSource,foreignId)
	{
// 		deleteNode(nodeId);
		deleteRequisitionNode(foreignSource,foreignId);
		synRequisition(foreignSource);
		location.reload();
	}
	
	function deleteRequisitionNode(foreignSource,foreignId)
	{
		$.ajax({
			type : 'delete',
			url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId,
			datatype : 'json',
			async:false,
			contentType : 'application/json',
			error : function(data) {
				alert("요구 삭제 실패");
			},
			success : function(data) {
			}
		});
	}
	
</script>
</head>

<body>

	<div class="container">
<!-- hidden -->	
		<form id="hiddenForm">
			<input type="hidden" id="foreignIdValue" name="foreignId" placeholder="" value="" >
			<input type="hidden" id="foreignSourceValue" name="foreignSource" placeholder="" value="" >
			<input type="hidden" id="foreignIdsValue" name="foreignIds" placeholder="" value="" >
			<input type="hidden" id="interfacesValue" name="interfaces" placeholder="" value="" >
			<input type="hidden" id="interfaceValue" name="interface" placeholder="" value="" >
			<input type="hidden" id="categoriesValue" name="categories" placeholder="" value="" >
			<input type="hidden" id="categoryValue" name="category" placeholder="" value="" >
			<input type="hidden" id="assetsValue" name="assets" placeholder="" value="" >
			<input type="hidden" id="assetValue" name="asset" placeholder="" value="" >
			<input type="hidden" id="interfacesFirstValue" name="interfacesFirst" placeholder="" value="" >
			<input type="hidden" id="interfaceFirstValue" name="interfaceFirst" placeholder="" value="" >
			<input type="hidden" id="categoriesFirstValue" name="categoriesFirst" placeholder="" value="" >
			<input type="hidden" id="categoryFirstValue" name="categoryFirst" placeholder="" value="" >
			<input type="hidden" id="assetsFirstValue" name="assetsFirst" placeholder="" value="" >
			<input type="hidden" id="assetFirstValue" name="assetFirst" placeholder="" value="" >
			<input type="hidden" id="getAddServiceValue" name="getAddService" placeholder="" value="" >
			<input type="hidden" id="getAddServiceFirstValue" name="getAddServiceFirst" placeholder="" value="" >
			<input type="hidden" id="setServiceTotalItemValue" name="setServiceTotalItem" placeholder="" value="" >
		</form>
<!-- hidden -->		
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
					<div class="span8">
						<h4 id="nodeCount">노드&nbsp;목록&nbsp;[0]</h4>
					</div>
					<div class="span2">
						<button type="button" class="btn btn-primary span12" title="노드 추가" onclick="javascript:addNodePop();">+&nbsp;노드 추가</button>
					</div>
					<div class="span2">
						<button type="button" class="btn btn-primary span12" title="노드 추가" onclick="javascript:addProvisioningRequisition();">노드 그룹 설정</button>
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
		
		<div class="row-fluid" id="requisitionsBox"  style="display:none" >
			<div class="span12 well well-small">
				<div class="row-fluid" >
					<div class="span3">
						<input type="text" id="requisitions" name="requisitions" placeholder="" value="" > 
					</div>
					<div class="span3">
						<button type="button" class="btn btn-primary" style="width:220px" title="" onclick="javascript:addRequisition();">새로운 노드 그룹 추가</button>
					</div>
					<div class="span3">
						<button type="button" class="btn btn-primary " style="width:220px" title="" data-toggle="modal" href="#defaultRequisitionPop" onclick="javascript:editDefaultRequisition();">기본외부소스 편집</button>
					</div>
					<div class="span3">
						<button type="button" class="btn btn-primary" style="width:220px" title="" onclick="javascript:resetRequisition();">기본외부소스 초기화</button>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="table  table-hover" id="requisitionsListTable"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<hr>	
	<!-- /container -->
</body>
<!-- modal -->
<div id="editRequisitionPop" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" 
style="display: none; width: 500px; height: 600px;margin-top: 50px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">Close</button>
		<h3 id="editRequisitionPopTitle"><!-- 제목 부분--></h3>
	</div>
	<div class="modal-body" style="width: 470px;height: 600px;">
		<div>
			<div class="accordion" id="accordion3">
				<div class="accordion-group">
					<div id="collapseOne" class="accordion-body collapse in" style="height: 480px;overflow-y: auto">
						<div class="accordion-inner">
							 <div class="span8" style="margin-left: -14px;" data-toggle="collapse">
								<form id="memberInfoFrm" name="memberInfoFrm" method="post">
									<a type="button" class="btn btn-primary" style="margin-top:-9px;" onclick="javascript:nodeListPop();">+&nbsp;노드&nbsp;추가</a>
									<table class="table table-striped table-hover" style="width: 467px;" id="requisitionListTable">
									<!-- 리스트 부분-->
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal -->
<!-- modal -->
<div id="defaultRequisitionPop" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" 
style="display: none; width: 1365px; margin-left: -685px;margin-top: -9px;height: 745px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">Close</button>
		<h3 id="editDefaultRequisitionPopTitle">Foreign Source Name: default</h3>
	</div>
	<div class="modal-body" style="width: 1336px;height: 600px;">
		<div>
			<div class="accordion" id="accordion3">
				<div class="accordion-group">
					<div id="collapseOne" class="accordion-body collapse in" style="height: 578px;overflow-y: auto">
						<div class="accordion-inner">
							 <div class="span8" style="margin-left: -14px;" data-toggle="collapse">
								<form id="memberInfoFrm" name="memberInfoFrm" method="post">
									<table>	
										<tr>	
											<td><h4 id="editDefaultRequisitionTitle1">Detectors</h4></td>
											<td><a type="button" class="btn btn-primary btn-mini" style="margin-top:0px;margin-left: 10px;" onclick="addDetector()">Add Detector</a></td>
										</tr>
									</table>
									<div style="width: 1334px;" id="defaultRequisitionListTable1">
									<!-- 리스트 부분-->
									</div>
								</form>
								<form id="memberInfoFrm" name="memberInfoFrm" method="post">
									<table>	
										<tr>	
											<td><h4 id="editDefaultRequisitionTitle2">Policies</h4></td>
											<td><a type="button" class="btn btn-primary btn-mini" style="margin-top:0px;margin-left: 10px;" onclick="addPolicy()">Add Policy</a></td>
										</tr>
									</table>
									<div style="width: 1334px;" id="defaultRequisitionListTable2">
									<!-- 리스트 부분-->
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal -->
</html>