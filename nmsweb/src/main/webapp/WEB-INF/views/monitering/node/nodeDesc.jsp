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
<script type="text/javascript">
	$(document).ready(function() {

		getSpecificNode(addNodeDesc, "${nodeId}");

		getOutagesForNode(addOutages, "${nodeId}", "10");

		getEventsForNode(addEvents, "${nodeId}", "10");
		
		getServiceFromNodeId(addAvailability, "${nodeId}");
		
	});

	function addNodeDesc(jsonObj) {

		$('#nodeLabel').append("[ " + jsonObj["@label"] + " ]");

		addCategories(jsonObj["categories"]);

	}

	/**
	Surveillance Category Memberships
	 */
	function addCategories(jsonObj) {

		var categories = jsonObj;
		console.log(jsonObj);
		var scmStr = "	<div class='row-fluid'>"
				+ "		<h5>감시&nbsp;카테고리&nbsp;회원&nbsp;</h5></div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>";

		if( categories != undefined ){
			if (categories.length > 1) {
	
				for ( var i in categories) {
					scmStr += "<div class='row-fluid'>" + "	<a href='#'>"
							+ categories[i]["@name"] + "</a>" + "</div>";
				}
	
			} else {
				scmStr += "<div class='row-fluid'>" + "	<a href='#'>"
						+ categories["@name"] + "</a>" + "</div>";
			}
	
			scmStr += "</div>";
			$('#rightDiv').append(scmStr);
		}

		return true;

	}
	/*//Surveillance Category Memberships */

	/* Recent Outages */
	function addOutages(jsonObj) {

		var outages = jsonObj["outage"];

		if (jsonObj["@count"] > 0) {

			var scmStr = "	<div class='row-fluid'>"
						+ "		<h5>중단&nbsp;목록&nbsp;["+ jsonObj["@count"]+ "]</h5>"
						+ "	</div>"
						+ "	<div class='row-fluid'>"
						+ "		<div class='span12 well well-small'>"
						+ "<table class='table table-striped'>"
						+ "<tr><th>Interface</th><th>service</th><th>Lost</th><th>Regained</th><th>Id</th></tr>";
			if (jsonObj["@count"] > 1) {

				for ( var i in outages) {

					scmStr += "<tr>";
					scmStr += "<td><a href='#'>" + outages[i]["ipAddress"]
							+ "</a></td>";
					scmStr += "<td><a href='#'>"
							+ nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"], "name")
							+ "</a></td>";
					scmStr += "<td>"
							+ new Date(nullCheckJsonObject(outages[i]["serviceLostEvent"], "time"))
									.format('yy-MM-dd hh:mm:ss') + "</td>";
					scmStr += "<td>"
							+ new Date(
									nullCheckJsonObject(outages[i]["serviceRegainedEvent"], "time"))
									.format('yy-MM-dd hh:mm:ss') + "</td>";
					scmStr += "<td><a href='#'>" + outages[i]["@id"]
							+ "</a></td>";
					scmStr += "</tr>";
				}

			} else {
				scmStr += "<tr>";
				scmStr += "<td><a href='#'>" + outages["ipAddress"]
						+ "</a></td>";
				scmStr += "<td><a href='#'>"
						+ nullCheckJsonObject(outages["serviceLostEvent"]["serviceType"], "name")
						+ "</a></td>";
				scmStr += "<td>"
						+ new Date(nullCheckJsonObject(outages["serviceLostEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				scmStr += "<td>"
						+ new Date(
								nullCheckJsonObject(outages["serviceRegainedEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				scmStr += "<td><a href='#'>" + outages["@id"]
						+ "</a></td>";
				scmStr += "</tr>";
			}

			scmStr += "</table></div>";
			$('#rightDiv').append(scmStr);

		}

		return true;
	}
	/*//Recent Outages */

	/* Recent Outages */
	function addEvents(jsonObj) {

		var events = jsonObj["event"];

		if (jsonObj["@count"] > 0) {

			var scmStr = "	<div class='row-fluid'>"
					+ "		<h5>이벤트&nbsp;목록&nbsp;["
					+ jsonObj["@count"]
					+ "]</h5>"
					+ "	</div>"
					+ "	<div class='row-fluid'>"
					+ "		<div class='span12 well well-small'>"
					+ "<table class='table'>"
					+ "<tr><th>EventId</th><th>time</th><th>stats</th><th class='span4'>Message</th></tr>";
			if (jsonObj["@count"] > 1) {

				for ( var i in events) {
					scmStr += "<tr>";
					scmStr += "<td><a href='#'>" + events[i]["@id"]
							+ "</a></td>";
					scmStr += "<td>"
							+ new Date(events[i]["createTime"])
									.format('yy-MM-dd hh:mm:ss') + "</td>";
					scmStr += "<th class='"+events[i]["@severity"].toLowerCase()+"'>" + events[i]["@severity"] + "</th>";
					scmStr += "<td>" + events[i]["logMessage"].replace(/<p>|<\/p>/gi,'') + "</td>";
					scmStr += "</tr>";
				}

			} else {
				scmStr += "<tr>";
				scmStr += "<td><a href='#'>" + events["@id"] + "</a></td>";
				scmStr += "<td>"
						+ new Date(events["createTime"])
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				scmStr += "<td>" + events["@severity"] + "</td>";
				scmStr += "<td>" + events["logMessage"] + "</td>";
				scmStr += "</tr>";
			}

			scmStr += "</table></div>";
			$('#rightDiv').append(scmStr);

		}

		return true;
	}
	/*//Recent Outages */
	
	/* Availability */
	function addAvailability(jsonObj, ipAddress){
		
		var scmStr = '<table class="table table-striped">';
		
		if(jsonObj["@count"] > 0){
			
			var serviceObj = jsonObj["service"];
			
			if(jsonObj["@count"] > 1){
				
				for(var i in serviceObj){
					scmStr += '<tr>';
					if(i == 0){
						scmStr += '	<th rowspan="'+jsonObj["@count"]+'">';
						scmStr += ipAddress;
						scmStr += '	</th>';
					}
					scmStr += '	<td>';
					scmStr += serviceObj[i]["serviceType"]["name"];
					scmStr += '	</td>';
					scmStr += '	<td>';
					scmStr += '100.000%';
					scmStr += '	</td>';
					scmStr += '</tr>';
				}
				
			}else{
				
				scmStr += '<tr>';
				scmStr += '	<th>';
				scmStr += ipAddress;
				scmStr += '	</th>';
				scmStr += '	<td>';
				scmStr += serviceObj["serviceType"]["name"];
				scmStr += '	</td>';
				scmStr += '	<td>';
				scmStr += '100.000%';
				scmStr += '	</td>';
				scmStr += '</tr>';
				
			}
			
		}
		
		scmStr += '</table>';
		
		$("#leftDiv").append(scmStr);
		
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
					<li><a href="<c:url value="/monitering/node/search.do" />" />노드검색</a> <span class="divider">/</span></li>
					<li class="active">노드 상세보기</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4 id="nodeLabel">노드정보</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span9"></div>
			<div class="span3">
				<jsp:include page="/include/statsBar.jsp" />
			</div>
		</div>
		<div class="row-fluid">
			<div class="span4">
				<div class="row-fluid">
					<h5>가용성</h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small" id="leftDiv"></div>
				</div>
			</div>
			<div class="span8" id="rightDiv"></div>
		</div>

	</div>
	<hr>
	<!-- /container -->
</body>
</html>
