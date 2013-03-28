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
	<jsp:param value="노드목록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		getNodeListToCategoryName(addNodeLists, "${cateNm}");
	});
	
	//callback 함수 jsonObj를 이용 파싱 후 append
	function addNodeLists(jsonObj) {
		console.log(jsonObj);
		$('#nodeListTable').empty();
		
		var nodeObj = jsonObj["Detail"];
		var str = "";
		str += '<table class="table table-striped">';
		for( var i in nodeObj){
			var status = Number(nodeObj[i]["availavili"]).toFixed(2) >= 100 ? "normal" :  "critical";
			
			str += '	<tr>';
			str += '		<td class="span7">'+nodeObj[i]["nodeLabel"]+'</td>';
			str += '		<td class="span3">'+nodeObj[i]["outageCount"]+' of '+nodeObj[i]["serviceCount"]+'</td>';
			str += '		<td class="span2 '+status+'">'+Number(nodeObj[i]["availavili"]).toFixed(2)+'%</td>';
			str += '	</tr>';
			
		}
		str += '</table>';
		$('#nodeListTable').append(str);
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" >Home</a> <span class="divider">/</span></li>
					<li class="active">노드 목록</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped" id="nodeListTable"></table>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>