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
	<jsp:param value="Index" name="title" />
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
		$('#indexNodeList').empty();
		
console.log(jsonObj);
		var str = getSearchSelectJsonObj(jsonObj);
		var strNode = getSearchSelectNodeJsonObj(jsonObj);
		var str = getNodelistJsonObj(jsonObj);
		
		$('#indexNodeList').append(str);
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
		str += '<table class="table table-striped table-hover">';
		//str += '	<colgroup><col class="span6" /><col class="span3" /><col class="span3" /></colgroup>';
		str += '	<tr><th>카테고리</th><th>중단 서비스</th><th>서비스 Availability</th></tr>';

		for ( var i in categoryObj) {
			//var status = Number(categoryObj[i]["availabili"]).toFixed(2) >= 100 ? "normal" :  "critical";
			
			
			if(Number(categoryObj[i]["availabili"]).toFixed(2) >= 100){
				var status = "progress-success";
				//var classStatus ="success";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) >= 90 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 99){
				var status = "progress";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
				//var classStatus ="success";
				var classStatus ="";
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) >= 80 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 89){
				var status = "progress-info";
				//var classStatus ="info";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) >= 70 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 79){
				var status = "progress-warning";
				//var classStatus ="warning";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) <= 69 && Number(categoryObj[i]["availabili"]).toFixed(2) > 0.00){
				var status = "progress-danger";
				//var classStatus ="error";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
				
				var errorStr = "";
				errorStr += '	<div class="alert alert-error">';
				errorStr += '	<button type="button" class="close" data-dismiss="alert">&times;</button>';
				errorStr += '         <span class="label label-important">Warning!</span>&nbsp;&nbsp;<strong>'+categoryObj[i]["name"]+' </strong>서버의 가용률이<strong> '+availwidth+'%</strong>입니다.  ';
				errorStr += '        </div>';
				
				$('#categoryInfo').append(errorStr);
			
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) == 0.00){
				var status = "progress-danger";
				var availwidth ="18";
				
				var errorStr = "";
				errorStr += '	<div class="alert alert-error">';
				errorStr += '	<button type="button" class="close" data-dismiss="alert">&times;</button>';
				errorStr += '         <span class="label label-important">Warning!</span>&nbsp;&nbsp;<strong>'+categoryObj[i]["name"]+' </strong>서버의 가용률이<strong> '+Number(categoryObj[i]["availabili"]).toFixed(2)+'%</strong>입니다.  ';
				errorStr += '        </div>';
				
				$('#categoryInfo').append(errorStr);
				//var classStatus ="error";
			}  
			
			//alert("availabili : "+Number(categoryObj[i]["availabili"]).toFixed(2)+"%  status : "+status);
		/* 	 if(outageCount == 0){
				var statusCount = "normal";
				alert("normal"+outageCount);
			}else{
				var statusCount = "critical";
				alert("critical"+outageCount);
			}  */
			
			var avail = Number(categoryObj[i]["availabili"]).toFixed(2) ;
			
			var outageCount = categoryObj[i]["outageTotalCount"];
			var serviceCount = categoryObj[i]["serviceTotalCount"];

			
			
			str += '	<tr class='+classStatus+' id="errorstr">';
			str += '		<th><a href="<c:url value="/category/nodeList?cateNm=" />'+categoryObj[i]["name"]+'">' + categoryObj[i]["name"] + '</a></th>';
			str += '		<th class="text-error">&nbsp;&nbsp;&nbsp;&nbsp;' + outageCount + ' of ' + serviceCount + '</th>';
			str += '		<td class=""><div class="progress progress-striped active '+status+' " style="margin-bottom: 0px;width: 202px; ">';
			str += '		<div class="bar" style="width:' + availwidth + '%">' + avail + '%</div>';
			str += '		</div></td>';
			str += '	</tr>';
			totalAvail += parseInt(avail);
			outageTotalCount += parseInt(outageCount);
			serviceTotalCount += parseInt(serviceCount);
			
		}

		str += '</table>';

		$('#categoryInfo').append(str);
		
		if(outageTotalCount == 0){
			
			var statusTotal = "success";
			
		}else{
			var statusTotal = "error";
		} 
		
		if((totalAvail / categoryObj.length).toFixed(2) >= 100){
			var statustotalAvail = "progress-success";
		}else if ((totalAvail / categoryObj.length).toFixed(2) >= 90 && (totalAvail / categoryObj.length).toFixed(2) <= 99){
			var statustotalAvail = "progress";
		}else if ((totalAvail / categoryObj.length).toFixed(2) >= 80 && (totalAvail / categoryObj.length).toFixed(2) <= 89){
			var statustotalAvail = "progress-info";
		}else if ((totalAvail / categoryObj.length).toFixed(2) >= 70 && (totalAvail / categoryObj.length).toFixed(2) <= 79){
			var statustotalAvail = "progress-warning";
		}else if ((totalAvail / categoryObj.length).toFixed(2) <= 69 && (totalAvail / categoryObj.length).toFixed(2) > 0.00){
			var statustotalAvail = "progress-danger";
			
			
		} else if ((totalAvail / categoryObj.length).toFixed(2)== 0.00){
			var statustotalAvail = "progress-danger";
			var availwidth ="18";
			
			
			//var classStatus ="error";
		}
		
		
		
		
		
		// 전체 가용률
		var tstr = "";
		tstr += '<table class="table table-striped table-hover">';
		//tstr += '	<colgroup><col class="span6" /><col class="span3" /><col class="span3" /></colgroup>';
		tstr += '	<tr><th>전체 중단 서비스 </th><th class="text-error">' + outageTotalCount + ' of ' + serviceTotalCount +'</th></tr>';
		tstr += '	<tr class="'+statusTotal+'">';
		tstr += '	<th>전체 이용률 </th>';
		tstr += '		<td class=""><div class="progress progress-striped active '+statustotalAvail+' " style="margin-bottom: 0px;width: 317px; ">';
		
		tstr += '		<div class="bar" style="width:' + (totalAvail / categoryObj.length).toFixed(2) + '%">' + (totalAvail / categoryObj.length).toFixed(2) + '%</div>';
		
		tstr += '		</div></td>';
		//tstr += '	<td class="'+statustotalAvail+'">' + (totalAvail / categoryObj.length).toFixed(2)
//				+ '%</td>';
		tstr += '	</tr></table>';

		$('#totalCategoryInfo').append(tstr);

		//중단 목록
		var outageObj = jsonObj["Outages"];

		for ( var i in outageObj) {

			var lostTime = new Date(outageObj[i]["iflostservice"]);
			var current = new Date();
			var lastTime = dateDiff(lostTime, current);

			$('#outageInfo').append("<strong><a class=text-error href='<c:url value='/search/outage/outageDesc?outageId=' />"+outageObj[i]["outageid"]+"'>" + outageObj[i]["ipaddr"] + "</a></strong> ("+ lastTime + ")<br/>");
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
							<div class="progress progress-success progress-striped active" style="margin-bottom: 9px;">
								<div class="bar" style="width: 100%">100%</div>
							</div>
							<div class="progress progress-striped active" style="margin-bottom: 9px;">
								<div class="bar" style="width: 90%">90%~99%</div>
							</div>
							<div class="progress progress-striped progress-info active" style="margin-bottom: 9px;">
								<div class="bar" style="width: 80%">80%~89%</div>
							</div>
							
							<div class="progress progress-warning progress-striped active" style="margin-bottom: 9px;">
								<div class="bar" style="width: 70%">70%~79%</div>
							</div>
							<div class="progress progress-danger progress-striped active">
								<div class="bar" style="width: 60%">0%~69%</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<h4>노드&nbsp;목록</h4>
								</div>
							</div>
							<div class="well well-small">
								<div class="row-fluid">
									<div class="span12" id="indexNodeList"></div>
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
