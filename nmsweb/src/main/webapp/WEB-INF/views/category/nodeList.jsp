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
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		getNodeListToCategoryName(addNodeLists, "${cateNm}");
		
		
	});
	
	//callback 함수 jsonObj를 이용 파싱 후 append
	function addNodeLists(jsonObj) {
		availabilityChange();
	}
	
	function outagesChange(){
		
		getNodeListToCategoryName(addNodeOutageLists, "${cateNm}");
		
	}
	function allChange(value){
		
		getNodeListToCategoryName(addNodeAllLists, "${cateNm}");
		
	}
	function availabilityChange(){
		
		getNodeListToCategoryName(addNodeAvailLists, "${cateNm}");
		
	}
	function addNodeAvailLists(jsonObj){
		
		if(jsonObj["Detail"]=="null"){
			var nodeObj = jsonObj["Detail"];
			$('#nodeListTable').empty();
			var str = "";
			str += '<table class="table table-striped ">';
			str += '	<tr>';
			str += '		<td class="span3"></td>';
			str += '		<td class="span6" style ="text-align: center;" >해당되는 노드가 없습니다.</td>';
			str += '		<td class="span3"></td>';
			str += '	</tr>';
			str += '</table>';
			$('#nodeListTable').append(str);
		}else{
			$('#nodeListTable').empty();
			
			var nodeObj = jsonObj["Detail"];
			var str = "";
			str += '<table class="table table-striped">';
			for( var i in nodeObj){
				var status = Number(nodeObj[i]["availavili"]).toFixed(2) >= 100 ? "normal" :  "critical";
				if(Number(nodeObj[i]["availavili"]).toFixed(2)<100){
				str += '	<tr >';
				str += '		<td class="span7">'+nodeObj[i]["nodeLabel"]+'</td>';
				str += '		<td class="span3">'+nodeObj[i]["outageCount"]+' of '+nodeObj[i]["serviceCount"]+'</td>';
				str += '		<td class="span2 '+status+'">'+Number(nodeObj[i]["availavili"]).toFixed(2)+'%</td>';
				str += '	</tr>';
				}
			}
			str += '</table>';
			$('#nodeListTable').append(str);	
		}
	}
		
		
function addNodeOutageLists(jsonObj){
		
		if(jsonObj["Detail"]=="null"){
			$('#nodeListTable').empty();
			var nodeObj = jsonObj["Detail"];
			var str = "";
			str += '<table class="table table-striped ">';
			str += '	<tr>';
			str += '		<td class="span3"></td>';
			str += '		<td class="span6" style ="text-align: center;" >해당되는 노드가 없습니다.</td>';
			str += '		<td class="span3"></td>';
			str += '	</tr>';
			str += '</table>';
			$('#nodeListTable').append(str);
		}else{
			$('#nodeListTable').empty();
			
			var nodeObj = jsonObj["Detail"];
			var str = "";
			str += '<table class="table table-striped">';
			for( var i in nodeObj){
				var status = Number(nodeObj[i]["availavili"]).toFixed(2) >= 100 ? "normal" :  "critical";
				if(nodeObj[i]["outageCount"] !=0){
				str += '	<tr >';
				str += '		<td class="span7">'+nodeObj[i]["nodeLabel"]+'</td>';
				str += '		<td class="span3">'+nodeObj[i]["outageCount"]+' of '+nodeObj[i]["serviceCount"]+'</td>';
				str += '		<td class="span2 '+status+'">'+Number(nodeObj[i]["availavili"]).toFixed(2)+'%</td>';
				str += '	</tr>';
				}
			}
			str += '</table>';
			$('#nodeListTable').append(str);	
		}
	}
	
	
function addNodeAllLists(jsonObj){
	
	if(jsonObj["Detail"]=="null"){
		$('#nodeListTable').empty();
		var nodeObj = jsonObj["Detail"];
		var str = "";
		str += '<table class="table table-striped ">';
		str += '	<tr>';
		str += '		<td class="span3"></td>';
		str += '		<td class="span6" style ="text-align: center;" >해당되는 노드가 없습니다.</td>';
		str += '		<td class="span3"></td>';
		str += '	</tr>';
		str += '</table>';
		$('#nodeListTable').append(str);
	}else{
		$('#nodeListTable').empty();
		
		var nodeObj = jsonObj["Detail"];
		var str = "";
		str += '<table class="table table-striped">';
		for( var i in nodeObj){
			var status = Number(nodeObj[i]["availavili"]).toFixed(2) >= 100 ? "normal" :  "critical";
			str += '	<tr >';
			str += '		<td class="span7">'+nodeObj[i]["nodeLabel"]+'</td>';
			str += '		<td class="span3">'+nodeObj[i]["outageCount"]+' of '+nodeObj[i]["serviceCount"]+'</td>';
			str += '		<td class="span2 '+status+'">'+Number(nodeObj[i]["availavili"]).toFixed(2)+'%</td>';
			str += '	</tr>';
		}
		str += '</table>';
		$('#nodeListTable').append(str);	
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
					<li><a href="<c:url value="/index.do" />" >Home</a> <span class="divider">/</span></li>
					<li class="active">노드 목록</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
				<li class="active"><h4><a href="<c:url value="/index.do" />">Category : </a></h4></li>
				<li><h4 class="text-success">${cateNm}</h4></li>
				</ul>
			</div>
			
		</div>
		<div class="row-fluid">
				<div class="span12 breadcrumb well well-small">
					<label class="span2 control-label muted" style ="font-size:16px;">Show interfaces:</label>
					<label class="radio span1 ">    
						<input type="radio" id="All" name="interfaces" value="All" onchange="allChange();">All</label>
					<label class="radio span2 ">    
						<input type="radio" id="outages" name="interfaces" value="outages" onchange="outagesChange();">With outages</label>
					<label class="radio span3 ">    
						<input type="radio" id="availability" name="interfaces" value="availability" checked onchange="availabilityChange();">With availability < 100%</label>
				</div>
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