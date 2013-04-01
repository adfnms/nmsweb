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
	<jsp:param value="자원별 리포트" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/graph.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		//getTotalGraphList(addGraphsList);
		addGraphsList(null);
	});
	
	function addGraphsList(jsonObj){
		var jsonObj = '{"total":"27","records":[{"id":"node[129]","value":"Node:61.78.35.200","type":"Node"},{"id":"node[145]","value":"Node:192.168.0.17","type":"Node"},{"id":"node[142]","value":"Node:SEC001599771AE7.local","type":"Node"},{"id":"node[138]","value":"Node:192.168.1.101","type":"Node"},{"id":"node[140]","value":"Node:KTH-pc.local","type":"Node"},{"id":"node[162]","value":"Node:192.168.0.75","type":"Node"},{"id":"node[155]","value":"Node:SEC0015997417A9.local","type":"Node"},{"id":"node[160]","value":"Node:192.168.0.22","type":"Node"},{"id":"node[126]","value":"Node:192.168.0.4","type":"Node"},{"id":"node[135]","value":"Node:192.168.0.200","type":"Node"},{"id":"node[139]","value":"Node:192.168.0.14","type":"Node"},{"id":"node[146]","value":"Node:192.168.0.29","type":"Node"},{"id":"node[104]","value":"Node:NewNode","type":"Node"},{"id":"node[165]","value":"Node:192.168.0.77","type":"Node"},{"id":"node[157]","value":"Node:192.168.0.28","type":"Node"},{"id":"node[109]","value":"Node:192.168.1.254","type":"Node"},{"id":"node[136]","value":"Node:192.168.0.1","type":"Node"},{"id":"node[97]","value":"Node::label","type":"Node"},{"id":"node[23]","value":"Node:192.168.0.25","type":"Node"},{"id":"node[167]","value":"Node:zzzzzzz","type":"Node"},{"id":"node[158]","value":"Node:192.168.0.30","type":"Node"},{"id":"node[124]","value":"Node:debianOPENNMS.local","type":"Node"},{"id":"node[141]","value":"Node:192.168.0.20","type":"Node"},{"id":"node[113]","value":"Node:127.0.0.3","type":"Node"},{"id":"node[144]","value":"Node:192.168.0.10","type":"Node"},{"id":"node[9]","value":"Node:test_Test","type":"Node"},{"id":"node[170]","value":"Node:kamynote.local","type":"Node"}]}';
		jsonObj = JSON.parse(jsonObj);

		var recordObj = jsonObj["records"];
		console.log(jsonObj);
		var str = "";
		var regExp = /[^0-9]/gi;
		
		for(var i in recordObj){
			
			str += "<tr>";
			str += "	<td>";
			str += '		<a href="javascript:setOption(\''+recordObj[i]["id"].replace(regExp,"")+'\');">';			
			str += 				recordObj[i]["value"];
			str += "		</a>";
			str += "	</td>";
			str += "</tr>";
			
		}
		
		$('#recodTable').append(str);
	}
	
	function setOption(nodeId){
		//var graphObj = getGraphInfoToNodeId(nodeId);
		var jsonObj = '{"total":"3","records":[{"id":"node[142].nodeSnmp[]","value":"Node-levelPerformanceData","type":"SNMPNodeData"},{"id":"node[142].interfaceSnmp[Embedded_Ethernet_Controller__10_100_Mbps__v1_0__UTP_RJ_45__connector_A1__100_full_duplex-001599771ae7]","value":"EmbeddedEthernetController,10/100Mbps,v1.0,UTPRJ-45,connectorA1,100fullduplex(192.168.0.27,100Mbps)","type":"SNMPInterfaceData"},{"id":"node[142].responseTime[192.168.0.27]","value":"192.168.0.27","type":"ResponseTime"}]}';
		graphObj = JSON.parse(jsonObj);
		console.log(graphObj);
		var records = graphObj["records"];
		
		var str = "";
		
		for( var i in records){
			str += "<tr>";
			str += "	<th>";
			str += records[i]["type"];
			str += "	</th>";
			str += "</tr>";
			str += "<tr>";
			str += "	<td>";
			str += "		<a href='javascript:setGraphUrls(\""+records[i]["id"]+"\");'>";
			str += 				records[i]["value"];
			str += "		</a>";
			str += "	</td>";
			str += "</tr>";
			
		}
		$('#recodDiv').hide("slow");
		$('#recodInfoTable').append(str);
		$("#deth2").show("show");
		
	}
	
	function setGraphUrls(val){
		
		var graphUrlObj = getGraphUrls(val);
		var str = "";
		for(var i in graphUrlObj){
			str += "<img src='"+graphUrlObj[i]+"'/>";
		}
		
		$('#grephDiv').empty();
		$('#grephDiv').append(str);
	}
	
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value='/index.do'/>">Home</a> <span class="divider">/</span></li>
					<li><a href="<c:url value='/report/resource.do'/>">리포트</a> <span class="divider">/</span></li>
					<li class="active">자원별&nbsp;리포트</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4 id="nodeLabel">자원별&nbsp;그래프</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4 id="nodeLabel">자원별&nbsp;노드&nbsp;목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12" id="recodDiv">
						<table class="table table-striped table-hover table-condensed table-stacked" id="recodTable"></table>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4 id="nodeLabel">자원별&nbsp;그래프&nbsp;목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12" id="recodInfoDiv">
						<table class="table table-striped table-hover table-condensed table-stacked" id="recodInfoTable"></table>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4 id="nodeLabel">그래프&nbsp;목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12" id="grephDiv">
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<!-- /container -->
</body>
</html>