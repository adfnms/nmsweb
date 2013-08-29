<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<%@page import="com.bluecapsystem.nms.define.Define"%>
<%
boolean g_isLogin 	= false;
String userId = null;
try{
	userId	= session.getAttribute(Define.USER_ID_KEY).toString(); 
}catch(Exception ex){
	g_isLogin = false;
}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="사용자등록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>

<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script src="<c:url value="/resources/js/surveillance.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	/*시스템 분류 리스트 갖고오기  */
	getsurveillanceLabel(getSystemCategory);
	
});	
	
function getSystemCategory(jsonObj,categoryid,categoryname){

	var str = categoryNameStr(jsonObj,categoryid,categoryname);
	$('#categoryName').append(str);
	getNodeToSurveillance(getSystemNode,categoryid);

}
function getSystemNode(jsonObj,categoryId){
	console.log(jsonObj);
	if(jsonObj["RegNodeItems"].length==0){
		
		var str = lengthZeroStr(nodeId,nodelabel,categoryId);
		$('#'+categoryId+'').append(str);
		
	}else{
		var nodeObj = jsonObj["RegNodeItems"];
		for( var i in nodeObj){
			
			var nodeId =  nodeObj[i]["nodeid"];
			var nodelabel=nodeObj[i]["nodelabel"];
		
			var str = nodeNameStr(nodeId,nodelabel,categoryId);
			
			$('#'+categoryId+'').append(str);
			
		NodeListAjax(NodeTotalList,nodeId,nodelabel,categoryId);
			
		}
	}
}
	
function NodeTotalList(data,nodeId,nodelabel,categoryId){
	
	var sumId=categoryId+nodeId;
	
	var str = nodeInfoStr(data,nodeId,nodelabel,categoryId);
	
	
	$('#'+sumId+'').append(str);
	
	
}
	
	
	
	
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="/v1/surveillance.do">Surveillance</a></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div>
			<div class="row-fluid">
				<div class="span12" id="categoryName"></div>
			</div>
		</div> 
	</div>
	<!-- /container -->
</body>
</html>
