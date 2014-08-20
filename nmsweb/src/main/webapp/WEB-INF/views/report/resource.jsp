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
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap-datetimepicker.min.css" />"
	rel="stylesheet">
<script
	src="<c:url value="/resources/bootstrap/js/bootstrap-datetimepicker.min.js" />"></script>

<script type="text/javascript">
var startDate = '';
var endDate = '';
	$(document).ready(function() {

		getTotalGraphList(addGraphsList);
		//addGraphsList(null);
		relativeTimeFormChange();
		bootstrapCalendar();
	});

	/*부트스크랩 날짜 이용*/
	function bootstrapCalendar() {
		var startCalendar = $('#startDate').datetimepicker({
			format : 'yyyy년MM월dd일',
			language : 'pt-BR',
			pickTime : false,
		}).on('changeDate', function(ev) {
			var sDate	   = new Date(ev.date).valueOf();
			var eDate	   = new Date(endCalendar._date).valueOf();
			
			if(sDate >= eDate)
				endCalendar.setDate(new Date(Date.parse(ev.date)+1*1000*60*60*24));
			
			endCalendar.setStartDate(new Date(Date.parse(ev.date)+1*1000*60*60*24));
			startCalendar.hide();
			
		}).data('datetimepicker');
		
		var endCalendar = $('#endDate').datetimepicker({
			format : 'yyyy년MM월dd일',
			language : 'pt-BR',
			pickTime : false,
			startDate: new Date(Date.parse(startCalendar._date)+1*1000*60*60*24),
			
		}).on('changeDate', function(ev) {
			endCalendar.hide();
			
		}).data('datetimepicker');
	}

	//자원별 노드목록
	function addGraphsList(jsonObj) {

		var str = "";
		if (jsonObj["total"] == 0) {

			str += "<tr>";
			str += "	<td style=\"text-align: center\">";
			str += " 				노드리스트가 없습니다.";
			str += "	</td>";
			str += "</tr>";
		}

		var recordObj = jsonObj["records"];
		recordObj.sort(graphSort);

		var regExp = /[^0-9]/gi;

		var TableObj = $("<table></table>");
		var TrObj = $("<tr></tr>");
		var TdObj = $("<td></td>");
		var FONTobj = $("<font></font>");

		TableObj
				.attr('class',
						'table table-striped table-hover table-condensed table-stacked');
		TableObj.attr('id', 'recodTable');

		for ( var i in recordObj) {
			var id = recordObj[i]["id"];

// 			TableObj.append(TrObj.clone().attr('onclick','javascript:setOption(\''+ recordObj[i]["id"].replace(regExp, "") + '\',\''+ recordObj[i]["value"] + '\');').append(
			TableObj.append(TrObj.clone().attr('onclick','javascript:setOption(\''+ recordObj[i]["id"] + '\',\''+ recordObj[i]["value"] + '\');').append(
				TdObj.clone().attr('style', 'text-align: center;').append(
// 					AObj.clone().attr('href','javascript:setOption(\''+recordObj[i]["id"].replace(regExp,"")+'\',\''+recordObj[i]["value"]+'\');').text(recordObj[i]["value"])
					FONTobj.clone().attr("color", "blue").text(recordObj[i]["value"].replace("Node:", "")),
					FONTobj.clone().attr("color", "black").text(" [ "),
					FONTobj.clone().attr("color", "black").text("노드 ID : "),
					FONTobj.clone().attr("color", "black").text(id.substring(id.indexOf('[') + 1,id.length - 1)),
					FONTobj.clone().attr("color", "black").text(" ]"))
			));
		}

		$('#recodDiv').empty();
		$('#recodDiv').append(TableObj);
	}

	//자원별 그래프목록
	function setOption(nodeId, nodeValue) {

		var id = nodeId;

		var value = nodeValue;

		$("#nodeInfo").find('h5').empty();
		$("#nodeInfo").find('h5').append(value.replace('Node:', ''));

		var graphObj = getGraphInfoToNodeId(id);

		var records = graphObj["records"];

		var str = "";

		for ( var i in records) {
			str += "<tr>";
			str += "	<td style=\"text-align: center\">";
			str += "		<a href='javascript:setGraphUrls(\"" + records[i]["id"]+ "," + records[i]["value"] + "\");'>";
			str += records[i]["value"];
			str += "		</a>";
			str += "	</td>";
			str += "</tr>";
		}

		$('#trHide').hide();
		$('#recodInfoTable').empty();
		$('#recodInfoTable').append(str);

		$("#deth2").show("show");
		$("#ResourceInfo").find('h5').html('-');
		$("#graphValueFrm input[name=graphValue]").val('');
		$('#grephDiv').empty();

	}
	//그래프 목록
	function setGraphUrls(val) {

		var valsp = val.split(",");
		var val = valsp[0];
		var resource = valsp[1];
		var dayString = "day";

		$("select[name=rtstatus] option[value=" + dayString + "]").attr(
				"selected", true);
		$("#ResourceInfo").find('h5').html(resource);

		var graphUrlObj = getGraphUrls(val);

		$('#graphValueFrm input[name=graphValue]').val(val);

		var str = "";

		if (graphUrlObj.length == 0) {

			str += "<h1  class=\"text-error text-center\">NO DATA</h1>";
			$('#grephDiv').empty();
			$('#grephDiv').append(str);
		} else {
			
			for ( var i in graphUrlObj) {
				str += "<img src='"+graphUrlObj[i]+"'/>";
			}

			$('#grephDiv').empty();
			$('#grephDiv').append(str);
		}
	}

	function relativeTimeFormChange() {

		if ($('#rtstatus').val() == '')
			return;
		
		var value = $('#rtstatus').val();

		$('#endDateValue').attr('disabled', 'disabled');
		$('#endDateICon').hide();

		$('#startDateValue').attr('disabled', 'disabled');
		$('#startDateICon').hide();

		$('#customSearchBtn').hide();
		if (value == 'custom') {
			$('#endDateValue').removeAttr('disabled');
			$('#endDateICon').show();

			$('#startDateValue').removeAttr('disabled');
			$('#startDateICon').show();
			
			$('#customSearchBtn').show();
		}

		var now = new Date();
		var year = now.getFullYear();
		var mon = (now.getMonth() + 1) > 9 ? '' + (now.getMonth() + 1) : '0'
				+ (now.getMonth() + 1);
		var day = now.getDate() > 9 ? '' + now.getDate() : '0' + now.getDate();

		$("#endDateValue").val(year + '년' + mon + '월' + day + '일');

		var time = relativeTime(value);
		
		$("#startDateValue").val(time);

		
		goRelativeTime(value);
	}
	
	function goRelativeTime(relativeTime) {

		if ($("#graphValueFrm input[name=graphValue]").val() == '')
			return;
		

		if($('#rtstatus').val() == 'custom')
			return;

		var graphVal = $("#graphValueFrm input[name=graphValue]").val();
		var graphUrlObj = getTimePeriodGraphUrls(graphVal, relativeTime);
		var str = "";

		if (graphUrlObj.length == 0) {
			str += "<h1  class=\"text-error text-center\">NO DATA</h1>";

		} else {
			for ( var i in graphUrlObj) {
				
				str += "<img src='"+graphUrlObj[i]+"'/>";
			}
			
			$('#grephDiv').empty();
			$('#grephDiv').append(str);
		}
	}
	
	function replaceCalendar(str)
	{
		return str.replace('년',':').replace('월',':').replace('일',':') + '01';
	}
	
	
	function searchGraphUrls()
	{
		if ($("#graphValueFrm input[name=graphValue]").val() == '')
		{
			alert('노드 및 리소스를 선택해주세요');
			return;
		}
		
		var graphVal = $("#graphValueFrm input[name=graphValue]").val();
		var endDate = replaceCalendar($('#endDateValue').val());
		var startDate = replaceCalendar($('#startDateValue').val());
		var relativeTime = $('#rtstatus').val();
		
		var graphUrlObj = getTimePeriodGraphUrlsCustom(graphVal, relativeTime,startDate,endDate);

		var str = "";

		if (graphUrlObj.length == 0) {
			str += "<h1  class=\"text-error text-center\">NO DATA</h1>";

		} else {
			for ( var i in graphUrlObj) {
				
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
			<div class="span12" style="margin-top: -10px;">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value='/index.do'/>">Home</a> <span
						class="divider">/</span></li>
					<li><a href="<c:url value='/report/resource.do'/>">리포트</a> <span
						class="divider">/</span></li>
					<li class="active">자원별&nbsp;리포트<span class="divider">/</span></li>
					<li><a href="#" onclick="javascript:beforeUrl()">이전 화면</a><span
						class="divider">/</span></li>
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
				<div class="span12 well well-small"
					style="height: 152px; overflow-y: auto;">

					<div class="row-fluid">
						<div class="span12 text-center" id="recodDiv"></div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12 text-center">
						<h4 id="nodeLabel">Resource&nbsp;List</h4>
					</div>
				</div>
				<div class="span12 well well-small"
					style="height: 180px; overflow-y: auto;">
					<div class="row-fluid">
						<div class="span12" id="recodInfoDiv">
							<table
								class="table table-striped table-hover table-condensed table-stacked"
								id="recodInfoTable">
								<tr id="trHide">

									<td><h4 class="text-error text-center">Node&nbsp;List를&nbsp;선택해&nbsp;주세요.</h4></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- tree -->
			<div class="span6 well " style="padding-bottom: 8px;">
				<div class="span13 well " style="padding-bottom: 20px; margin-bottom: 2px;">
					<div class="span12 text-center">
						<h4 id="nodeLabel">Node&nbsp;명</h4>
					</div>
					<div class="row-fluid text-center">
						<div class="span12 text-success" id="nodeInfo">
							<h5>-</h5>
						</div>
					</div>

					<div class="span12 text-center" style="margin-left: 0px;">
						<h4 id="nodeLabel">Select&nbsp;Resource</h4>
					</div>
					<div class="row-fluid text-center" id="ResourceInfo">
						<div class="span12">
							<h5 class="text-success">-</h5>
						</div>

						<div class="span12 text-center" style="margin-left: 0px;">
							<h4 id="nodeLabel">기간&nbsp; 설정</h4>
						</div>

						<div class="span12" id="timePeriod">
							<form name="reltimeform" action="" style="height: 0px;">
								<select style="width: 145px;" id="rtstatus" name="rtstatus"
									onchange="relativeTimeFormChange();">
									<option value="day" selected>Last Day</option>
									<option value="week">Last Week</option>
									<option value="month">Last Month</option>
									<option value="year">Last Year</option>
									<option value="custom">custom</option>
								</select>
							</form>
						</div>

						<div class="span12 text-center" style="margin-left: 0px;"
							id="nodeLabel">
							<h4 id="nodeLabel">시작&nbsp; 날짜</h4>
						</div>
						<div class="row-fluid text-center" id="startDate">
							<input class="text-center" id="startDateValue" type="text"
								disabled="disabled" value=""> <span class="add-on"
								id="startDateICon" style="display: none;"> <i
								data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
							</span>
						</div>

						<div class="span12 text-center" style="margin-left: 0px;">
							<h4 id="nodeLabel">종료&nbsp; 날짜</h4>
						</div>
						<div class="row-fluid text-center" id="endDate">
							<input class="text-center" id="endDateValue" type="text"
								disabled="disabled" value=""> <span class="add-on"
								id="endDateICon" style="display: none;"> <i
								data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
							</span>
						</div>
						
					</div>
			
				</div>
				<div class="span1" style="width: 100%; height: 20px; padding-right: 0px; padding-left: 0px; margin-left: 0px;">
					<button type="button" id="customSearchBtn" style="display:none;" class="btn btn-primary span12" title="날짜 검색" onclick="javascript:searchGraphUrls();">검색</button>
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
						<div class="span3"></div>
						<div class="span6" id="grephDiv"></div>
						<div class="span3"></div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<!-- /container -->
</body>
</html>