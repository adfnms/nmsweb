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
<script src="<c:url value="/resources/js/surveillance.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

		//서비스 option 가져오기
		getServiceList(addServiceList);
		
		/* 24시간 가용률, 카테고리 정보*/
		getTotalIndexInfo(addIndexInfo, null);
		
		/*노드 정보 갖고오기  */
		getNodeTotalList(searchNodeLists, "orderBy=id&limit=0");
	
		/*surveillence 정보 갖고오기  */
		getsurveillanceLabel(searchLabels);
		
	});
	/*surveillence 정보 갖고오기 callback함수 */
	function searchLabels(jsonObj,categoryid,categoryname){
		
	
		getNodeToSurveillance(getNodeAlert,categoryid);
		var str = countStr(jsonObj,categoryid,categoryname);
		
		$('#surveillenceLabel').append(str); 
		
		//setInterval(refreshSystem, 10000);
	}

	/* function refreshSystem(){
		getsurveillanceLabel(searchLabels);

	}    */

	function getNodeAlert(jsonObj,categoryId){
		
		if(jsonObj["RegNodeItems"].length==0){
	
		}else{
			var nodeObj = jsonObj["RegNodeItems"];
			for( var i in nodeObj){
				
				var nodeId =  nodeObj[i]["nodeid"];
				var nodelabel=nodeObj[i]["nodelabel"];
				
				NodeListAjax(showRegNodeList,nodeId,nodelabel,categoryId);
			}
			
		}
	}
	function showRegNodeList(data,nodeId,nodelabel,categoryId){
		if(data["@totalCount"]>1){
			
			for( var i in data['outage']){
				
				if(data['outage'][i]['serviceLostEvent']['@severity'] == "MAJOR" || data['outage'][i]['serviceLostEvent']['@severity'] == "CRITICAL"){
					 $('#'+categoryId+'').empty();
					 $('#'+categoryId+'').append("<img src='<c:url value="/resources/images/" />red.jpg' />");
				}else{
					
				}
			}
			
		}else if(data["@totalCount"] == 1){
			
			if(data['outage']['serviceLostEvent']['@severity'] == "MAJOR" || data['outage']['serviceLostEvent']['@severity'] == "CRITICAL"){
				 $('#'+categoryId+'').empty();
				 $('#'+categoryId+'').append("<img src='<c:url value="/resources/images/" />red.jpg' />");
			}else{
				
			}
			
		}else{
			
		}
	}
	
	
	

	
	
	
	/* 감시대상목록 */
	function searchNodeLists(jsonObj) {
		$('#id').empty();
		$('#label').empty();
		$('#indexNodeList').empty();
		
		var str = getNodelistJsonObj(jsonObj);
		
		
		$('#indexNodeList').append(str);
		$('#id').append(str);
	}
	
	/* 노드목록 */
	function searchNodeLabels(jsonObj) {
		$('#nodeLabel').empty();
		
		var str = getNodeLablelistJsonObj(jsonObj);
		
		$('#nodeLabel').append(str);
	}
	
	function getNodeLablelistJsonObj(jsonObj){
		
	}
	
	//서비스 리스트 가져오기
	function addServiceList(jsonObj) {

		var optionStr = getOptiontagToServiceList(jsonObj);
		$('#serviceId').append(optionStr);

	}
	/* 24시간 가용률 , 카테고리 정보 */
	function addIndexInfo(jsonObj) {
		
		var categoryObj = jsonObj["CategoryInfo"];
		var totalAvail = 0;
		var outageTotalCount = 0;
		var serviceTotalCount = 0;

		var DIVobj =$("<div></div>");
		var ULobj = $("<ul></ul>");
		var TRobj = $("<tr></tr>");
		var TABLEobj =$("<table></table>");
		var THobj = $("<th></th>");
		var TDobj = $("<td></td>");
		var Aobj = $("<a></a>");
		var SPANobj = $("<span></span>");
		var BUTTONobj = $("<button></button>");
		var LIobj = $("<li></li>");
		var H5obj = $("<h5></h5>");
		var STRONGobj = $("<strong></strong>");
		
		// 가용률
		
		
		
		 var str = "";
		str += '<table class="table table-striped table-hover">';
		//str += '	<colgroup><col class="span6" /><col class="span3" /><col class="span3" /></colgroup>';
		str += '	<tr><th>카테고리</th><th>장애 서비스</th><th>서비스 Availability</th></tr>';
		for ( var i in categoryObj) {
			//var status = Number(categoryObj[i]["availabili"]).toFixed(2) >= 100 ? "normal" :  "critical";
			
			
			if(Number(categoryObj[i]["availabili"]).toFixed(2) > 99){
				var status = "progress-success";
				//var classStatus ="success";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) > 89 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 99){
				var status = "progress";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
				//var classStatus ="success";
				var classStatus ="";
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) >79 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 89){
				var status = "progress-info";
				//var classStatus ="info";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) > 69 && Number(categoryObj[i]["availabili"]).toFixed(2) <= 79){
				var status = "progress-warning";
				//var classStatus ="warning";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) <= 69 && Number(categoryObj[i]["availabili"]).toFixed(2) > 0.00){
				var status = "progress-danger";
				//var classStatus ="error";
				var availwidth = Number(categoryObj[i]["availabili"]).toFixed(2) ;
				
				var errorStr = "";
				
		
				$('#categoryInfo').append(DIVobj);
				
				errorStr += '	<div class="alert alert-error">';
				errorStr += '	<button type="button" class="close" data-dismiss="alert">&times;</button>';
				errorStr += '         <span class="label label-important">Warning!</span>&nbsp;&nbsp;<strong>'+categoryObj[i]["name"]+' </strong>서버의 가용률이<strong> '+availwidth+'%</strong>입니다.  ';
				errorStr += '        </div>';
				
				$('#categoryInfo').append(errorStr);
			
			}else if (Number(categoryObj[i]["availabili"]).toFixed(2) == 0.00){
				var status = "progress-danger";
				var availwidth ="18";
				var errorStr = "";
				
				if(categoryObj[i]["outageTotalCount"]==0 && categoryObj[i]["serviceTotalCount"] == 0){
					var availwidth ="100";
					var status = "progress-success";
					var avail ="100";
				}else{
					errorStr += '	<div class="alert alert-error">';
					errorStr += '	<button type="button" class="close" data-dismiss="alert">&times;</button>';
					errorStr += '         <span class="label label-important">Warning!</span>&nbsp;&nbsp;<strong>'+categoryObj[i]["name"]+' </strong>서버의 가용률이<strong> '+Number(categoryObj[i]["availabili"]).toFixed(2)+'%</strong>입니다.  ';
					errorStr += '        </div>'; 
					
					$('#categoryInfo').append(errorStr); 
				}
			
				
			}  
			
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
		
		// 전체 가용률  statusbar
		if(outageTotalCount == 0){
			var statusTotal = "success";
			
		}else{
			var statusTotal = "error";
		} 
		
		var totalAv = (totalAvail / categoryObj.length).toFixed(2);
		
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
			
			
			if(outageTotalCount == 0 && serviceTotalCount == 0){
				var totalAv = 100;
				var statustotalAvail = "progress-success";
				
			}
			
			
			
		}
		
		// 전체 가용률
		
		var tstr = "";
		tstr += '<table class="table table-striped table-hover">';
		//tstr += '	<colgroup><col class="span6" /><col class="span3" /><col class="span3" /></colgroup>';
		tstr += '	<tr><th>전체 장애 서비스 </th><th class="text-error">' + outageTotalCount + ' of ' + serviceTotalCount +'</th></tr>';
		tstr += '	<tr class="'+statusTotal+'">';
		tstr += '	<th>전체 이용률 </th>';
		tstr += '		<td class=""><div class="progress progress-striped active '+statustotalAvail+' " style="margin-bottom: 0px;width: 317px; ">';
		tstr += '		<div class="bar" style="width:' + totalAv + '%">' + (totalAvail / categoryObj.length).toFixed(2)  + '%</div>';
		tstr += '		</div></td>';
		tstr += '	</tr></table>';

		$('#totalCategoryInfo').append(tstr); 
		
		//GET 장애 목록
		var outageObj = jsonObj["Outages"];
		
		if(outageObj == "null"){
			
			$('#outageInfo').append("<strong class=text-error> 현재 장애 목록이 없습니다. </strong> ");
			
		}else{
			
			
			for ( var i in outageObj) {

				var lostTime = new Date(outageObj[i]["iflostservice"]);
				var current = new Date();
				var lastTime = dateDiff(lostTime, current);
				
				$('#outageInfo').append("<strong><a class=text-error data-toggle=modal href='#myModal' onclick=\"javascript:outagePop('"+outageObj
						[i]["outageid"]+"','"+outageObj[i]["ipaddr"]+"');\">" + outageObj[i]["ipaddr"] + "</a></strong> ("+ lastTime + ")<br/>");
			}
		}
		
	}//******//24시간 가용률 , 카테고리 정보 ****************/
	
	
	/* 장애 정보 POPUP창 */
	/* 장애 목록 클릭시 실행 */
	function outagePop(outageId, ipAdress){
		
		var data = "id="+outageId;
		getTotalOutagesList(addOutageInfo, data,ipAdress);
		
	}
	
	/* outage Info Callback */
	function addOutageInfo(jsonObj,ipaddr){
		$('#outageInfoDiv').empty();
		var outageInfoStr = getOutageInfoBox(jsonObj);
		$('#outageInfoDiv').append(outageInfoStr);
	
		$("#myModalLabel").html(ipaddr+"&nbsp;장애&nbsp;정보");
	}
	/*//outage Info Callback */
	/*// 장애 정보 POPUP창 */
	
	function showAddSurveillence(){
		$("#AddSurveillenceName input:not([protect=true]),textarea,select").val("");
		 $('#showAddSurveillence').show();
		}	
	
	function addSurveillence(){
		 
		$('#showAddSurveillence').hide();
		
		 var categoryname  = $("#AddSurveillenceName input[name=categoryname]").val();
		
		 if(categoryname ==""){
			 return;
		 };
		 
		 $.ajax({
		      	url : '<c:url value="/regSurveillenceName.do" />',
		        type:'post',
		        dataType:'json',
		        data:'categoryname='+categoryname,
		        error:function(data, status, err){
		            alert('Error, service not found');
		        },
		        success:function(res){
		        	if(res.isSuccess == false)
		       		{
		        		alert(res.errorMessage);        		
		        		return;
		       		}
		        	 alert("등록되었습니다.");
		        	 
		        	 $(location).attr('href', "/v1/index.do");
		        }
			});  
		
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
				<!-- 	<li><a href="/v1/surveillance.do">surveillance</a> <span class="divider">/</span></li> -->
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
									<h4>장애&nbsp;목록</h4>
								</div> 
							</div>
							<div class="well well-small" >
								<div class="row-fluid">
									<div class="span12" id="outageInfo"></div>
								</div>
							</div> 
							<div class="row-fluid">
								<div class="span12">
									<h4>알림&nbsp;정보</h4>
								</div>
								
							</div>
							<div class="well well-small" style="margin-bottom: 30px;">
								<div class="row-fluid">
									<div class="span12">
										나의 알림 <a  style="margin-left: 16px;" class="btn btn-mini btn-primary" type="button" href="<c:url value="/admin/notimng/mynoti.do" />">확인</a><br />
										 모든 알림<a style="margin-left: 20px;"  class="btn btn-mini btn-primary" type="button" href="<c:url value="/admin/notimng/allnoti.do" />">확인</a><br />
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<h4>System 분류</h4>
								</div>
								<div class="span3" style="margin-top: 7px;">
									<a  class="btn btn-mini btn-primary" type="button" href="<c:url value="/surveillancetotal.do" />">+ 전체</a>
								</div>
								<div class="span3" style="margin-top: 7px;">
									<a  class="btn btn-mini btn-primary" type="button" onclick="javascript:showAddSurveillence()">+ 추가</a>
								</div>
							</div>
							<div class="row-fluid" id="showAddSurveillence"  style="display:none">
								<form id="AddSurveillenceName">
									<div class="input-append" >
										<input class="span9" id="appendedInputButton" type="text"  placeholder="Add Surveillence Name" name="categoryname" value="">
										<button class="btn" type="button" onclick="javascript:addSurveillence()">등록</button>
									</div>
								</form>
							</div>
							<div class="span12" style=" height:265px; overflow-y:auto;;">
								<table class="table table-striped table-hover " id="surveillenceLabel">
								
									
								</table>
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
									<div class="well well-small" style="margin-bottom: 37px;" id="categoryInfo"></div>
									<div class="row-fluid">
									<div class="span12" >
										<h4>전체&nbsp;가용률&nbsp;정보</h4>
									</div>
							</div>
									<div class="well well-small" id="totalCategoryInfo" style ="height: 99px;"></div>
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="row-fluid">
								<div class="span9">
									<h4>감시&nbsp;대상&nbsp;목록</h4>
								</div>
								<div class="span3" style="margin-top: 7px;">
									<a  class="btn btn-mini btn-primary" type="button" href="<c:url value="/monitoring/nodelist.do" />">[More]</a>
								</div>
							</div>
							<div class="well well-small" style ="height:510px; overflow-y:auto;" >
								<div class="row-fluid">
									<div class="span12" id="indexNodeList"></div>
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
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel" >장애정보</h3>
	</div>
	<div class="modal-body">
		<div class="row-fluid" id="outageInfoDiv"></div>
	</div>
	<div class="modal-footer">
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>
<!-- Modal -->
</html>
