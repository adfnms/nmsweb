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
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

		//서비스 option 가져오기
		getServiceList(addServiceList);

		getTotalIndexInfo(addIndexInfo, null);
		
		/*노드 정보 갖고오기  */
		getNodeTotalList(searchNodeLists, "orderBy=id&limit=0");
	});

	
	function searchNodeLists(jsonObj) {
		$('#id').empty();
		$('#label').empty();

		var str = getSearchSelectJsonObj(jsonObj);
		var strNode = getSearchSelectNodeJsonObj(jsonObj);
		
		$('#id').append(str);
		$('#label').append(strNode);
	}

	
	
	
	//서비스 리스트 가져오기
	function addServiceList(jsonObj) {

		var optionStr = getOptiontagToServiceList(jsonObj);
		$('#serviceId').append(optionStr);

	}

	function addIndexInfo(jsonObj) {
		console.log(jsonObj);
		var categoryObj = jsonObj["CategoryInfo"];

		var totalAvail = 0;
		var outageTotalCount = 0;
		var serviceTotalCount = 0;

		// 가용률
		var str = "";
		str += '<table class="table table-striped">';
		//str += '	<colgroup><col class="span6" /><col class="span3" /><col class="span3" /></colgroup>';
		str += '	<tr><th>카테고리</th><th>중단</th><th></th><th>이용률</th></tr>';

		for ( var i in categoryObj) {
			//var status = Number(categoryObj[i]["availabili"]).toFixed(2) >= 100 ? "normal" :  "critical";
			
			
			if(Number(categoryObj[i]["availabili"]).toFixed(2) >= 100){
				var status = "normal";
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) >= 90 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 99){
				var status = "warning";
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) >= 80 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 89){
				var status = "minor";
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) >= 70 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 79){
				var status = "major";
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) <= 69){
				var status = "critical";
			} 
			
		/* 	 if(outageCount == 0){
				var statusCount = "normal";
				alert("normal"+outageCount);
			}else{
				var statusCount = "critical";
				alert("critical"+outageCount);
			}  */
			
			var avail = Number(categoryObj[i]["availabili"]).toFixed(2);
			var outageCount = categoryObj[i]["outageTotalCount"];
			var serviceCount = categoryObj[i]["serviceTotalCount"];

			str += '	<tr>';
			str += '		<td style="width: 178px;"><a href="<c:url value="/category/nodeList?cateNm=" />'+categoryObj[i]["name"]+'">' + categoryObj[i]["name"] + '</a></td>';
			str += '		<td class="" style=" width: 110px;">' + outageCount + ' of ' + serviceCount + '</td>';
			str += '	<td style="width: 1px;";></td>';
			str += '		<th class="'+status+'" style="width: 122px;">' + avail + '%</th>';
			str += '	</tr>';

			totalAvail += parseInt(avail);
			outageTotalCount += parseInt(outageCount);
			serviceTotalCount += parseInt(serviceCount);
		}

		str += '</table>';

		$('#categoryInfo').append(str);
		
		if(outageTotalCount == 0){
			
			var statusTotal = "normal";
			
		}else{
			var statusTotal = "critical";
		} 
		
		if((totalAvail / categoryObj.length).toFixed(2) >= 100){
			var statustotalAvail = "normal";
			alert("normal");
		}else if ((totalAvail / categoryObj.length).toFixed(2) >= 90 && (totalAvail / categoryObj.length).toFixed(2) <= 99){
			var statustotalAvail = "warning";
			alert("warning");
		}else if ((totalAvail / categoryObj.length).toFixed(2) >= 80 && (totalAvail / categoryObj.length).toFixed(2) <= 89){
			var statustotalAvail = "minor";
			alert("minor");
		}else if ((totalAvail / categoryObj.length).toFixed(2) >= 70 && (totalAvail / categoryObj.length).toFixed(2) <= 79){
			var statustotalAvail = "major";
			alert("major");
		}else if ((totalAvail / categoryObj.length).toFixed(2) <= 69){
			var statustotalAvail = "critical";
		} 
		// 전체 가용률
		var tstr = "";
		tstr += '<table class="table table-striped">';
		//tstr += '	<colgroup><col class="span6" /><col class="span3" /><col class="span3" /></colgroup>';
		tstr += '	<tr><th>전체</th><th>중단</th><th></th><th>이용률</th></tr>';
		tstr += '	<tr><td>전체 가용률</td>';
		tstr += '	<td class="'+statusTotal+'">' + outageTotalCount + ' of ' + serviceTotalCount
				+ '</td>';
		tstr += '	<td style="width: 1px;";></td>';
		tstr += '	<td class="'+statustotalAvail+'">' + (totalAvail / categoryObj.length).toFixed(2)
				+ '%</td>';
		tstr += '	</tr></table>';

		$('#totalCategoryInfo').append(tstr);

		//중단 목록
		var outageObj = jsonObj["Outages"];

		for ( var i in outageObj) {

			var lostTime = new Date(outageObj[i]["iflostservice"]);
			var current = new Date();
			var lastTime = dateDiff(lostTime, current);

			$('#outageInfo').append("<a href='<c:url value='/search/outage/outageDesc?outageId=' />"+outageObj[i]["outageid"]+"'>" + outageObj[i]["ipaddr"] + "</a> ("+ lastTime + ")<br/>");
		}
		
	}
</script>
</head>

<body>
	<div class="container">
		<jsp:include page="/include/menu.jsp" /> 
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value='/index.do'/>">Home</a><span class="divider">/</span></li>
					<li><a href="/v1/surveillance.do">surveillance</a> <span class="divider">/</span></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span12">
						<div class="span3">
							<div class="row-fluid">
								<div class="span12">
									<h4>중단&nbsp;목록</h4>
								</div>
							</div>
							<div class="well well-small">
								<div class="row-fluid">
									<div class="span12" id="outageInfo"></div>
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="row-fluid">
								<div class="span12">
									<h4>24시간&nbsp;가용률</h4>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="well well-small" id="categoryInfo"></div>
									<div class="well well-small" id="totalCategoryInfo" style ="height: 99px;"></div>
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="row-fluid">
								<div class="span12">
									<h4>알림&nbsp;정보</h4>
								</div>
							</div>
							<div class="well well-small">
								<div class="row-fluid">
									<div class="span12">
										나의 알림(2,314) <a href="<c:url value="/admin/setting.do?myNotification=My" />">[확인]</a><br /> 모든 알림(2,314) <a
											href="<c:url value="/admin/setting.do?totalNotification=Total" />">[확인]</a><br />
									</div>
								</div>
							</div>
							<!-- 							<div class="row-fluid"> -->
							<!-- 								<div class="span12"> -->
							<!-- 									<h4>그래프&nbsp;검색</h4> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
							<!-- 							<div class="well well-small"> -->
							<!-- 								<div class="row-fluid"> -->
							<!-- 									<div class="span12"> -->
							<!-- 										<form> -->
							<!-- 											<fieldset> -->
							<!-- 												<label for="">사용자 그래프</label><input type="text" class="span12"> -->
							<!-- 											</fieldset> -->
							<!-- 											<fieldset> -->
							<!-- 												<label for="">자원 그래프</label><input type="text"  class="span12"> -->
							<!-- 											</fieldset> -->
							<!-- 											<fieldset> -->
							<!-- 												<button type="button" class="btn btn-primary span12" title="Quick Search" onclick="#">검색</button> -->
							<!-- 											</fieldset> -->
							<!-- 										</form> -->
							<!-- 									</div> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
							<div class="row-fluid">
								<div class="span12">
									<h4>Quick&nbsp;Search</h4>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<form action="<c:url value="/search/node.do" />">
										<div class="well well-small">
											<fieldset>
												<label for="id" class="span6">노드 ID</label>
												<select class="span6" id="id" name="id">
													<option value="">선택</option>
												</select>
											</fieldset>
											<fieldset>
												<label for="label" class="span6">노드명</label>
												<!-- <input type="text" id="label" name="label" class="span6"> -->
												<select class="span6" id="label" name="label">
													<option value="">선택</option>
												</select>
											</fieldset>
											<fieldset>
												<div class="span6"></div>
												<input type="submit" class="btn btn-primary span6" title="Quick Search" value="검색">
											</fieldset>
										</div>
									</form>
									<form action="<c:url value="/search/node.do" />">
										<div class="well well-small">
											<fieldset>
												<label for="ipAddress" class="span6">TCP/IP</label><input
													type="text" name="ipAddress" id="ipAddress" class="span6">
											</fieldset>
											<fieldset>
												<div class="span6"></div>
												<input type="submit" class="btn btn-primary span6"
													title="Quick Search" value="검색">
											</fieldset>
										</div>
									</form>
									<form action="<c:url value="/search/node.do" />">
										<div class="well well-small">
											<fieldset>
												<label for="serviceId" class="span6">서비스</label>
												<select class="span6" id="serviceId" name="serviceId">
													<option value="">선택</option>
												</select>
											</fieldset>
											<fieldset>
												<div class="span6"></div>
												<input type="submit" class="btn btn-primary span6"
													title="Quick Search" value="검색">
											</fieldset>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
