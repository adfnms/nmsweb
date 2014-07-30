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
	<jsp:param value="노드 상세보기" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/events.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/* Node base info */
		getSpecificNode(addNodeDesc, "${nodeId}");

		/* Recent Outages */
		getOutagesForNode(addOutages, "${nodeId}", "10");

		/* Recent Events */
		getEventsForNode(addEvents, "${nodeId}", "5");
		
		/* Node Availability */
		getNodeAvailability(addNodeAvailability, "${nodeId}");
		
		
		/* Interface Availability */
		getInterfacesFromNodeId(addInterfaceAvailability, "${nodeId}");
		
		
	});

	/* Node base info Callback */
	function addNodeDesc(jsonObj) {
		var str = "<a href='/" + version + "/search/node/nodeDesc.do?nodeId=" + jsonObj['@id'] + "'>" + jsonObj['@label']+ "</a>";
		$('#nodeLabel').append(str);
	
		/* Surveillance Category Memberships */
		//addCategories(jsonObj["categories"]);

	}

	/* Surveillance Category Memberships Callback*/
	/*감시 카테고리 회원*/
	/* function addCategories(jsonObj) {

		var str = getTabletagToCategoryJsonObj(jsonObj);
		$('#rightDiv').append(str);

	} */
	/*//Surveillance Category MembershipsCallback */

	/* Recent Outages Callback */
	function addOutages(jsonObj) {
		var str = getTabletagToOutageJsonObj(jsonObj,"${nodeId}");
		$('#rightUnderDiv2').append(str);

	}
	/*//Recent Outages */

	/* Recent Events Callback */
	function addEvents(jsonObj) {

		var str = getTabletagToEventJsonObj(jsonObj);
		$('#rightUnderDiv').append(str);

	}
	/*//Recent Events Callback*/

	
	/* Availability Callback */
	
	function addNodeAvailability(jsonObj){

		var nodeAvail = Number(jsonObj["nodeAvailability"][0]["avail"]).toFixed(3); 
		
		$('#availNode').append("가용성[&nbsp;"+nodeAvail+"%&nbsp;]");
		
	}

	function addInterfaceAvailability(jsonObj){
		var strAv ="";
		var str ="";
		var strcoll ="";
		
		alert(jsonObj["@count"]);
		if(jsonObj["@count"] > 0){

			if(jsonObj["@count"] > 1){
				interfaceObj = jsonObj["ipInterface"];
				var ipAddrs = interfaceAvail[i]["ipAddress"];
				
				for(var i in interfaceObj){
					//인터페이스 가용성
					var interfaceAvail = Number(getInterfaceAvailability("${nodeId}", ipAddrs)).toFixed(3)+"%";
					 var headStr = '<td><h5><a href="javascript:goInterfaceDescPage(\'${nodeId}\', \''+ipAddrs+'\');">' + ipAddrs + '&nbsp;[&nbsp;'+interfaceAvail+'&nbsp;]&nbsp;</a></h5></td><td style="position: relative;left: 10%;top: 0px;">'+statsToStringFromStatoCode(jsonObj["@isManaged"])+'</hd>'; 
					//var headStr = '<h5><a href="javascript:InterfaceInfo(\'${nodeId}\', \''+ipAddrs+'\');">' + ipAddrs + '&nbsp;[&nbsp;'+interfaceAvail+'&nbsp;]&nbsp;</a></h5>';
					//서비스 가용성
					var serviceAvailSte = getTabletagToAvailJsonObj("${nodeId}", ipAddrs);
					getInterfaceInfo(addInterfaceInfo,"${nodeId}", ipAddrs);
					$('#interInfo').append("[ " +  ipAddrs + " ]");
					str += headStr;
					
					strAv += serviceAvailSte;
					
				
				}
			}else{
				var ipAddrs =jsonObj["ipInterface"]["ipAddress"];
				//인터페이스 가용성
 				var interfaceAvail = Number(getInterfaceAvailability("${nodeId}", ipAddrs)).toFixed(3)+"%";
 				var headStr = '<td><h5><a href="javascript:goInterfaceDescPage(\'${nodeId}\', \''+ipAddrs+'\');">' + ipAddrs + '&nbsp;[&nbsp;'+interfaceAvail+'&nbsp;]&nbsp;</a></h5></td><td style="position: relative;left: 10%;top: 0px;">'+statsToStringFromStatoCode(jsonObj["@isManaged"])+'</td>'; 
 				//var headStr = '<h5><a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"  onclick=\"show();\">' + ipAddrs + '&nbsp;[&nbsp;'+interfaceAvail+'&nbsp;]&nbsp;</a></h5>';
 				
 				//서비스 가용성
				var serviceAvailSte = getTabletagToAvailJsonObj("${nodeId}", ipAddrs);
				getInterfaceInfo(addInterfaceInfo,"${nodeId}", ipAddrs);
				$('#interInfo').append("[ " +  ipAddrs + " ]");
				str += headStr;
				
				//strAv += serviceAvailSte;
				strAv = serviceAvailSte;
			}
		}else{
			
			var headStr = '<h5 class ="text-error">인터페이스가 없습니다.</h5>'; 
			var interfaceAvailSte = '<h5 class ="text-error">제공되는 인터페이스 정보가 없습니다.</h5>'; 
			var serviceAvailSte = '<h5 class ="text-error">서비스가 없습니다.</h5>'; 
			
			strcoll += interfaceAvailSte;
			str += headStr;
			strAv += serviceAvailSte;
			
		}
		
		$('#collapsible').append(strcoll);
		$("#leftDiv").append(str);
		$("#leftUnderDiv").append(strAv);
	}
	
function show(){
	
	$('#accordion2').show();
}

	
function addInterfaceInfo(jsonObj) {
		
		var str = getInterfaceInfoBox(jsonObj);
		$('#collapsible').append(str);
		
	}

	function addServiceAvailability(jsonObj, nodeId, ipAddress, interfaceAvail){
		
		var headStr = '<a href="<c:url value="/search/node/interfaceDesc.do?nodeId=${nodeId}&intf='
			+ ipAddress + '" />"><h5>' + ipAddress + '</h5></a>';
			
		$("#leftDiv").prepend(headStr);
		
		var str = getTabletagToAvailJsonObj(jsonObj, ipAddress);
		
		$("#leftDiv").append(str);
		
	}
	
	/*//Availability Callback*/
	
	
	function goInterfaceDescPage(nodeId,intf){
	//location.href ="/"+version+"/search/node/interfaceDesc.do?nodeId="+nodeId+"&intf="+intf;
	/* Recent Outages */
	getOutagesForInterface(addOutagesForInterface, nodeId, intf,"5");

	/* Recent Events */
	getEventsForInterface(addEventsForInterface,nodeId, intf,"5");
	
}
	
		/* Recent Outages Callback For Interface*/
	function addOutagesForInterface(jsonObj ,nodeId ,ipAddrs) {
		$('#rightUnderDiv2').empty();
		var str = getTabletagToInterfaceOutageJsonObj(jsonObj,nodeId, ipAddrs);
		$('#rightUnderDiv2').append(str);

	}
	/*//Recent Outages Callback For Interface */
	
	/* Recent Events Callback ForInterface */
	function addEventsForInterface(jsonObj, notiId, ipAddress, serviceNm) {
		
		$('#rightUnderDiv').empty();
		var str = getTabletagToInterfaceEventJsonObj(jsonObj, notiId, ipAddress, serviceNm);
		$('#rightUnderDiv').append(str);

	}
	/*//Recent Events Callback For Interface*/
	

function goServiceDiv(nodeId,intf,serviceNm){
		
		/* Recent Outages */
		getOutagesForInterface(addOutagesForService, nodeId, intf,"5", serviceNm);
		
		/* Recent Events */
		getEventsForInterface(addEventsForService,nodeId, intf,"5", serviceNm);
		
	}

	
	/* Recent Outages Callback For Service*/
function addOutagesForService(jsonObj ,ipaddr,nodeId , serviceNm) {
	
	$('#rightUnderDiv2').empty();
	var str = getTabletagToServiceOutageJsonObj(jsonObj,serviceNm);
	$('#rightUnderDiv2').append(str);

}
/*//Recent Outages Callback For Service*/

/* Recent Events Callback For Service*/
function addEventsForService(jsonObj,notiId, ipaddr, serviceNm) {
	$('#rightUnderDiv1').empty();
	var str = getTabletagToServiceEventJsonObj(jsonObj, serviceNm);
	$('#rightUnderDiv1').append(str);

}
/*//Recent Events Callback For Service*/
	
	
	
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" >Home</a> <span
						class="divider">/</span></li>
					<li><a href="<c:url value="/search/node.do" />" >노드검색</a>
						<span class="divider">/</span></li>
					<li class="active">노드정보<span class="divider">/</span></li>
					<li><a href="#" onclick="javascript:beforeUrl()">이전 화면</a><span class="divider">/</span></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
				<div class="row-fluid">
					<div class="span12">
						<ul class="breadcrumb well well-small" style="height: 0px;">
							<li class="span4"><h5 style="margin-top: 0px;" id="nodeLabel"></h5></li>
							<li class="span8"><h5 style="margin-top: 0px;margin-left: -6px;" id="availNode"></h5></li>
							<!-- <li class="span2"><h5 style="margin-top: 0px;" id="availNode"></h5></li> -->
						</ul>
						<div style="width: 478px;position: relative;left: 514px;top:-40px">
							<jsp:include page="/include/statsBar.jsp"/>
						</div>
					</div>
				</div>
		</div>
				​
		<div class="row-fluid" style="margin-top: -55px;">
			<div class="span4">
				<!-- <div class="row-fluid" style="margin-top: -10px;">
					<h5 class="span12 well well-small" id="availNode"></h5>
				</div> -->
				<div class="row-fluid">
					<h5>인터페이스</h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small" id="leftDiv"></div>
				</div>
				<!-- <div class="row-fluid">
					<div class="span12 well well-small" id="collapsible"></div>
				</div> -->
				<!-- <div class="row-fluid">
					<h5>서비스&nbsp;목록</h5>
				</div> -->
				<div class="row-fluid">
					<div class="span12 well well-small" id="leftUnderDiv" style="margin-left: 0px;height: 498px;margin-top: 21px;"></div>
				</div>
			</div>
			
			<!-- <div class="span8" id="rightDiv">
				<div class="accordion" id="accordion2" style="display:none">
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" id="interInfo">
							      인터페이스 정보
							</a>
						</div>
						<div id="collapseOne" class="accordion-body collapse">
						<div id="collapseOne" class="accordion-body collapse in"> 내용 펼쳐저서 보임
							<div class="accordion-inner" id="">
							</div>
						</div>
					</div>
				</div>
              </div> -->
              <div class="span8" id="rightUnderDiv"></div>
              <div class="span8" id="rightUnderDiv2"></div>
		</div>

	</div>
	
	
	<hr>
	<!-- /container -->
</body>
</html>
