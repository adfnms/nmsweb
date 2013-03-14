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
	<jsp:param value="노드 검색" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

		getNodeTotalList(addNodeLists, null);

		getServiceList(addServiceList);

	});

	//callback 함수 jsonObj를 이용 파싱 후 append
	function addNodeLists(jsonObj) {
		console.log()
		$('#nodeListTable').empty();

		var nodeObj = jsonObj["node"] != null ? jsonObj["node"] : jsonObj["nodes"];
		var str = "<tr>";

		if (1 == jsonObj["@totalCount"]) {
			str += "<td><a href='<c:url value="/monitering/node/nodeDesc.do" />?nodeId="
					+ nodeObj["@id"] + "'>" + nodeObj["@id"] + "</a></td>";
			str += "<td><a href='<c:url value="/monitering/node/nodeDesc.do" />?nodeId="
					+ nodeObj["@id"] + "'>" + nodeObj["@label"] + "</a></td>";
		} else {

			for ( var i in nodeObj) {

				str += "<td><a href='<c:url value="/monitering/node/nodeDesc.do" />?nodeId="
						+ nodeObj[i]["@id"]
						+ "'>"
						+ nodeObj[i]["@id"]
						+ "</a></td>";
				str += "<td><a href='<c:url value="/monitering/node/nodeDesc.do" />?nodeId="
						+ nodeObj[i]["@id"]
						+ "'>"
						+ nodeObj[i]["@label"]
						+ "</a></td>";

				if (i % 2 == 1) {
					str += "</tr><tr>";
				}

			}

		}

		str += "</tr>";

		$('#nodeListTable').append(str);

		//페이징 HTML 가져오기
		//getPagingHtml($('#pagingDiv'), "goSearchPageing", jsonObj["@totalCount"], pageNum, "10", "10" );
	}

	
	//서비스 리스트 가져오기
	function addServiceList(jsonObj) {

		var services = jsonObj["services"];

		var optionStr = "";
		if (services.length > 1) {

			for ( var i in services) {
				optionStr += "<option value='"+services[i]["serviceid"]+"'>"
						+ services[i]["servicename"] + "</option>";
			}

		} else {
			optionStr += "<option value='"+services["serviceid"]+"'>"
					+ services["servicename"] + "</option>";

		}

		$('#serviceId').append(optionStr);
	}

	//페이징 처리 스크립트
	/* function goSearchPageing(pageNm){
		
		pageNum = pageNm;
		
		$('#searchNodeFrm input[name=offset]').val(
				(parseInt(pageNm)-1) * $('#searchNodeFrm input[name=limit]').val()
		);
		
		getNodeList();
		
	} */

	//노드명 & 노드 ID 검색 버튼
	function schNodeFromNameId() {

	 	var id = $('#searchNodeFrm input[name=id]').val() == "" ? "" : "id="+$('#searchNodeFrm input[name=id]').val();
	 	var label = $('#searchNodeFrm input[name=label]').val() == "" ? "" : "&label="+$('#searchNodeFrm input[name=label]').val();
		//var serviceId = $('#searchNodeFrm select[name=serviceId]').val();
		//var ipAddress = $('#searchNodeFrm input[name=ipAddress]').val();

		var data = id+label;
		
		getNodeTotalList(addNodeLists, data);

	}
	
	//서비스 검색 버튼
	function schNodeFromService() {

		var serviceId = $('#searchNodeFrm select[name=serviceId]').val();
		
		searchNodeFromServiceId(addNodeLists, serviceId);

	}
	
	//아이피 검색 버튼
	function schNodeFromIpAddress() {

		var ipAddress = $('#searchNodeFrm input[name=ipAddress]').val();
		
		searchNodeFromIpAddress(addNodeLists, ipAddress);

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
					<li class="active">노드검색</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<form id="searchNodeFrm" name="searchNodeFrm">
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<div class="span12">
							<h4>노드 검색</h4>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">노드명</label>
									<div class="span4 controls">
										<input type="text" id="label" class="span12" name="label"
											value="" />
									</div>
									<label class="span2 control-label">노드 ID</label>
									<div class="span3 controls">
										<input type="text" id="id" class="span12" name="id" value="" />
									</div>
									<div class="span1">
										<button type="button" class="btn btn-primary span12" title="검색"
											onclick="javascript:schNodeFromNameId();">검색</button>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">제공 서비스</label>
									<div class="span3 controls">
										<select class="span12" id="serviceId" name="serviceId">
											<option value="">선택해주세요.</option>
										</select>
									</div>
									<div class="span1">
										<button type="button" class="btn btn-primary span12" title="검색"
											onclick="javascript:schNodeFromService();">검색</button>
									</div>
									
									<label class="span2 control-label">TCP/IP 주소</label>
									<div class="span3 controls">
										<input type="text" placeholder="*.*.*.*" id="ipAddress"
											class="span12" name="ipAddress" value="" />
									</div>
									<div class="span1">
										<button type="button" class="btn btn-primary span12" title="검색"
											onclick="javascript:schNodeFromIpAddress();">검색</button>
									</div>
								</div>
							</div>
							<!-- <div class="row-fluid" id="cateDiv">
								<div class="span12">
									<label class="span2 control-label">MAC 주소</label>
									<div class="span4 controls">
										<input type="text" />
									</div>
									<label class="span2 control-label">외부 별명</label>
									<div class="span4 controls">
										<input type="text" />
									</div>
								</div>
							</div>
							<div class="row-fluid" id="cateDiv">
								<div class="span12">
									<label class="span2 control-label">날짜</label>
									<div class="span3 controls">
										<input type="text" class="span12" />
									</div>
									<div class="span1" style="text-align: center;">~</div>
									<div class="span3 controls">
										<input type="text" class="span12" />
									</div>
									<div class="span3">
										<button type="button" class="btn btn-primary span12"
											title="검색" onclick="javascript:searchNode();">검색</button>
									</div>
								</div>
							</div> -->
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
						<h4>노드 목록</h4>
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
</body>
</html>
