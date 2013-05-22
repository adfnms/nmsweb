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
		
		getTotalGraphList(addGraphsList);
		//addGraphsList(null);
		
	});
	
	
	//자원별 노드목록
	function addGraphsList(jsonObj){
		
		//var jsonObj = '{"total":"27","records":[{"id":"node[129]","value":"Node:61.78.35.200","type":"Node"},{"id":"node[145]","value":"Node:192.168.0.17","type":"Node"},{"id":"node[142]","value":"Node:SEC001599771AE7.local","type":"Node"},{"id":"node[138]","value":"Node:192.168.1.101","type":"Node"},{"id":"node[140]","value":"Node:KTH-pc.local","type":"Node"},{"id":"node[162]","value":"Node:192.168.0.75","type":"Node"},{"id":"node[155]","value":"Node:SEC0015997417A9.local","type":"Node"},{"id":"node[160]","value":"Node:192.168.0.22","type":"Node"},{"id":"node[126]","value":"Node:192.168.0.4","type":"Node"},{"id":"node[135]","value":"Node:192.168.0.200","type":"Node"},{"id":"node[139]","value":"Node:192.168.0.14","type":"Node"},{"id":"node[146]","value":"Node:192.168.0.29","type":"Node"},{"id":"node[104]","value":"Node:NewNode","type":"Node"},{"id":"node[165]","value":"Node:192.168.0.77","type":"Node"},{"id":"node[157]","value":"Node:192.168.0.28","type":"Node"},{"id":"node[109]","value":"Node:192.168.1.254","type":"Node"},{"id":"node[136]","value":"Node:192.168.0.1","type":"Node"},{"id":"node[97]","value":"Node::label","type":"Node"},{"id":"node[23]","value":"Node:192.168.0.25","type":"Node"},{"id":"node[167]","value":"Node:zzzzzzz","type":"Node"},{"id":"node[158]","value":"Node:192.168.0.30","type":"Node"},{"id":"node[124]","value":"Node:debianOPENNMS.local","type":"Node"},{"id":"node[141]","value":"Node:192.168.0.20","type":"Node"},{"id":"node[113]","value":"Node:127.0.0.3","type":"Node"},{"id":"node[144]","value":"Node:192.168.0.10","type":"Node"},{"id":"node[9]","value":"Node:test_Test","type":"Node"},{"id":"node[170]","value":"Node:kamynote.local","type":"Node"}]}';
		//jsonObj = JSON.parse(jsonObj);

		var recordObj = jsonObj["records"];
		
		var str = "";
		var regExp = /[^0-9]/gi;
		
		for(var i in recordObj){
			
			str += "<tr>";
			str += "	<td style=\"text-align: center\">";
			str += '		<a href="javascript:setOption(\''+recordObj[i]["id"].replace(regExp,"")+','+recordObj[i]["value"]+'\');">';			
			str += 				recordObj[i]["value"];
			str += "		</a>";
			str += "	</td>";
			str += "</tr>";
			
		}
		$('#recodTable').empty();
		$('#recodTable').append(str);
	}
	
	
	//자원별 그래프목록
	function setOption(nodeId){
		
		var nodeIdsp=nodeId.split(",");
		
			
			var id = nodeIdsp[0];
			var value = nodeIdsp[1];
		
		$("#nodeInfo").html("<div class=\"span12 text-success\"><h5>"+value+"</h5></div>");
		
		var graphObj = getGraphInfoToNodeId(id);
		
		//var jsonObj = '{"total":"3","records":[{"id":"node[129].nodeSnmp[]","value":"Node-levelPerformanceData","type":"SNMPNodeData"},{"id":"node[129].interfaceSnmp[Embedded_Ethernet_Controller__10_100_Mbps__v1_0__UTP_RJ_45__connector_A1__100_full_duplex-001599771ae7]","value":"EmbeddedEthernetController,10/100Mbps,v1.0,UTPRJ-45,connectorA1,100fullduplex(192.168.0.27,100Mbps)","type":"SNMPInterfaceData"},{"id":"node[129].responseTime[192.168.0.23]","value":"192.168.0.23","type":"ResponseTime"}]}';
		//graphObj = JSON.parse(jsonObj);
		
		console.log(graphObj);
		
		var records = graphObj["records"];
		
		var str = "";
		
		for( var i in records){
			str += "<tr>";
			str += "	<th style=\"text-align: center\">";
			str += records[i]["type"];
			str += "	</th>";
			str += "</tr>";
			str += "<tr>";
			str += "	<td style=\"text-align: center\">";
			str += "		<a href='javascript:setGraphUrls(\""+records[i]["id"]+","+records[i]["value"]+"\");'>";
			str += 				records[i]["value"];
			str += "		</a>";
			str += "	</td>";
			str += "</tr>";
		}
		
		
	 	var now = new Date();
		var year= now.getFullYear();
		var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
		var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
		
		$("#fromDate").html("<div class=\"span12\" ><h5 id=\"fromDate\" class=\"text-success\">FromDate :"+year+'년'+mon+'월'+day+'일'+"</h5></div>");
		var beforeOneDay=yesterday(); //하루전
		$("#toDate").html("<div class=\"span12\" ><h5 id=\"toDate\" class=\"text-success\">ToDate :"+beforeOneDay+"</h5></div>");
		
		
		$('#trHide').hide();
		$('#recodInfoTable').empty();
		$('#recodInfoTable').append(str);
		$("#deth2").show("show");
		
	}
	//그래프 목록
	function setGraphUrls(val){
			var valsp=val.split(",");
			var val = valsp[0];
			var resource = valsp[1];
			
			var dayString="day";
			$("select[name=rtstatus] option[value="+dayString+"]").attr("selected",true);
			var beforeOneDay=yesterday(); //하루전
			$("#toDate").html("<div class=\"span12\" ><h5 id=\"toDate\" class=\"text-success\">ToDate :"+beforeOneDay+"</h5></div>");
			
			$("#ResourceInfo").html("<div class=\"span12\"><h5 class=\"text-success\">Resource : "+resource+"</h5>");
			$('#timePeriod').show();
		
			var graphUrlObj = getGraphUrls(val);
		
			$('#graphValueFrm input[name=graphValue]').val(val);
	
			var str = "";

			if(graphUrlObj.length == 0){
			
				var str = "";
			
			str += "<h1  class=\"text-error text-center\">NO DATA</h1>";
				$('#grephDiv').empty();
				$('#grephDiv').append(str);
			}else{
			
				for(var i in graphUrlObj){
				//alert(graphUrlObj[i]);
		
				str += "<img src='"+graphUrlObj[i]+"'/>";
			}
		
		$('#grephDiv').empty();
		$('#grephDiv').append(str);
		}
		
	}
	
	function relativeTimeFormChange() {

		for (i = 0; i < document.reltimeform.rtstatus.length; i++) {
            if (document.reltimeform.rtstatus[i].selected) {
                var value = document.reltimeform.rtstatus[i].value;
               /*value	=	day,
							week,
							month,
							year
               */
                var beforeOneDay=yesterday(); //하루전
                var beforeWeek=beforeOneWeek(); //일주일전
        		var beforeMonth=beforeOneMonth(); //한달전
        		var beforeyear=beforeOneyear(); //일년전
               
             
        		if(value=="day"){
        			//alert(beforeOneDay);
        			$("#toDate").html("<div class=\"span12\" ><h5 id=\"toDate\" class=\"text-success\">ToDate :"+beforeOneDay+"</h5></div>");
        		}else if(value=="week"){
        			//alert(beforeWeek);
        			$("#toDate").html("<div class=\"span12\" ><h5 id=\"toDate\" class=\"text-success\">ToDate :"+beforeWeek+"</h5></div>");
        		}else if(value=="month"){
        			 //alert(beforeMonth);
        			 $("#toDate").html("<div class=\"span12\" ><h5 id=\"toDate\" class=\"text-success\">ToDate :"+beforeMonth+"</h5></div>");
        		}else if(value=="year"){
        			// alert(beforeyear);
        			 $("#toDate").html("<div class=\"span12\" ><h5 id=\"toDate\" class=\"text-success\">ToDate :"+beforeyear+"</h5></div>");
        		}
               
               goRelativeTime(value);
            }
        }
    }
	 function goRelativeTime(relativeTime) {
		 var graphVal = $("#graphValueFrm input[name=graphValue]").val();
		 var graphUrlObj =getTimePeriodGraphUrls(graphVal,relativeTime);
		 var str = "";
		 
		 if(graphUrlObj.length == 0){
			 
			 var str = "";
				
				str += "<h1  class=\"text-error text-center\">NO DATA</h1>";
			 
		 }else{
		 
		 
		 for(var i in graphUrlObj){
				//alert(graphUrlObj[i]);
			
				str += "<img src='"+graphUrlObj[i]+"'/>";
			}
			
			$('#grephDiv').empty();
			$('#grephDiv').append(str);
			
		 }
	    }

</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		<form id="graphValueFrm" name="graphValueFrm">
			<input type="hidden" id="graphValue" name="graphValue" value="">
		</form>
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value='/index.do'/>">Home</a> <span class="divider">/</span></li>
					<li><a href="<c:url value='/report/resource.do'/>">리포트</a> <span class="divider">/</span></li>
					<li class="active">자원별&nbsp;리포트</li>
				</ul>
			</div>
		</div>
		
<!----------------------------------->

		<div class="row-fluid">	
		<div class=" span6 well">
		<div class="row-fluid">
					<div class="span12 text-center">
						<h4 id="nodeLabel">Node&nbsp;List</h4>
					</div>
				</div>
			<div class="span12 well well-small" style=" height:152px; overflow-y:auto;">
				
				<div class="row-fluid" >
					<div class="span12 text-center" id="recodDiv">
						<table class="table table-striped table-hover table-condensed table-stacked" id="recodTable" ></table>
					</div>
				</div>
			</div>
			<div class="row-fluid">
					<div class="span12 text-center">
						<h4 id="nodeLabel">Resource&nbsp;List</h4>
					</div>
				</div>
			 <div class="span12 well well-small" style=" height:180px; overflow-y:auto;">
				<div class="row-fluid">
					<div class="span12" id="recodInfoDiv">
						<table class="table table-striped table-hover table-condensed table-stacked" id="recodInfoTable">
							<tr id = "trHide">
								
								<td><h4 class="text-error text-center">Node&nbsp;List를&nbsp;선택해&nbsp;주세요.</h4></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			</div>
			<!-- tree -->
			 <div class="span6 well ">
			 <div class="span12 well well-small">
		 		<div class="span12 text-center">
					<h4 id="nodeLabel">Node&nbsp;노드</h4>
				</div>
				<div class="row-fluid text-center" id="nodeInfo">
					<!-- <div class="span6" ></div> -->
					<!-- <div class="span12 " id="nodeInfo"><h5></h5></div> -->
					<!-- <div class="span6" ></div> -->
				</div>
				<div class="span12 text-center" style="margin-left: 0px;">
					<h4 id="nodeLabel">선택한&nbsp;Resource</h4>
				</div>
				<div class="row-fluid text-center" id="ResourceInfo">
				</div>
				<div class="span12 text-center"  style="margin-left: 0px;" id="nodeLabel">
					<h4 id="nodeLabel">오늘&nbsp;날짜</h4>
				</div>
				<div class="row-fluid text-center" id="fromDate">
				</div>
				<div class="span12 text-center"  style="margin-left: 0px;">
					<h4 id="nodeLabel">검색&nbsp;날짜</h4>
				</div>
				<div class="row-fluid text-center" id="toDate">
				</div>
				<div class="span12 text-center"  style="margin-left: 0px;">
					<h4 id="nodeLabel">조건&nbsp;기간</h4>
				</div>
				<div class="row-fluid span12"  style="display:none" id="timePeriod">
					<div class="span2">
						<label class="control-label"></label>
					</div>
					<div class="span4">
						<label class="control-label" style="margin-left: 14px;">Time period  :</label>
					</div>
					<form name="reltimeform" action="">
						<select style =" width: 145px; margin-left: -31px; " id="rtstatus" name="rtstatus" onchange="relativeTimeFormChange();">
									<option value="day" selected>Last Day</option>
									<option value="week">Last Week</option>
									<option value="month" >Last Month</option>
									<option value="year" >Last Year</option>
						</select>
					</form>
				</div>
			</div>
		      </div>  
			
			<!-- tree -->
			
			
			</div>


<!----------------------------------->
		
		
		
		<div class="row-fluid">
			<!-- <div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12 text-center">
						<h4 id="nodeLabel">자원별&nbsp;노드&nbsp;목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12 text-center" id="recodDiv">
						<table class="table table-striped table-hover table-condensed table-stacked" id="recodTable"></table>
					</div>
				</div>
			</div> -->
		</div>
		<div class="row-fluid">
			<!-- <div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12 text-center">
						<h4 id="nodeLabel">자원별&nbsp;그래프&nbsp;목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12" id="recodInfoDiv">
						<table class="table table-striped table-hover table-condensed table-stacked" id="recodInfoTable"></table>
					</div>
				</div>
			</div> -->
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<!--  <div class="row-fluid text-center">
					<div class="span3" ></div>
					<div class="span6 " id="nodeInfo"><h5></h5></div>
					<div class="span3" ></div>
				</div>
				<div class="row-fluid text-center">
					<div class="span3" ></div>
					<div class="span6" id="ResourceInfo"><h5></h5></div>
					<div class="span3" ></div>
				</div>
				<div class="row-fluid text-center">
					<div class="span3" ></div>
					<div class="span6 " id="fromDate"><h5>fromDate</h5></div>
					<div class="span3" ></div>
				</div>
				<div class="row-fluid text-center">
					<div class="span3" ></div>
					<div class="span6 " id="toDate"><h5>toDate</h5></div>
					<div class="span3" ></div>
				</div>
				<div class="row-fluid span12"  style="display:none" id="timePeriod">
					<div class="span4">
						<label class="control-label"></label>
					</div>
					<div class="span2">
						<label class="control-label" style="margin-left: 14px;">Time period  :</label>
					</div>
					<form name="reltimeform" action="">
						<select style =" width: 145px; margin-left: -31px; " id="rtstatus" name="rtstatus" onchange="relativeTimeFormChange();">
									<option value="day" selected>Last Day</option>
									<option value="week">Last Week</option>
									<option value="month" >Last Month</option>
									<option value="year" >Last Year</option>
						</select>
					</form>
				</div> -->
				<div class="row-fluid">
					<div class="span12">
						<h4 id="nodeLabel"></h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span3" ></div>
					<div class="span6" id="grephDiv"></div>
					<div class="span3" ></div>
				</div> 
			</div>
		</div>
	</div>
	<hr>
	<!-- /container -->
</body>
</html>