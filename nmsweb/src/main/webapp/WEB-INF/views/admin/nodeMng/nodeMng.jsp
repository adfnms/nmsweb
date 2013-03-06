<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.bluecapsystem.nms.define.NMSProperties"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="노드관리" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//getTotalRequisitionsList(requisitionsList);
	});
	
	function requisitionsList(jsonObj){
		
		alert(jsonObj);
		
		var objList = jsonObj["model-import"];
		
		for ( var i in objList) {

			$('#requisitionsList').append(objList[i]["@foreign-source"]);
			
		}
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span9">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li class="active">노드 관리</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			<div class="span9 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>
							기본속성 <span class="label label-info">필수 입력 사항</span>
						</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<label class="span2 control-label">필요 조건</label>
						<div class="span4 controls">
							<select id="requisitionsList"></select>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<label class="span2 control-label">IP 주소</label>
						<div class="span4 controls">
							<input type="text">
						</div>
						<label class="span2 control-label">노드명</label>
						<div class="span4 controls">
							<input type="text">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span9 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>
							카테고리
						</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<label class="span2 control-label">카테 고리</label>
						<div class="span4 controls">
							<input type="text">
						</div>
						<label class="span2 control-label">카테 고리</label>
						<div class="span4 controls">
							<input type="text">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<label class="span2 control-label">카테 고리</label>
						<div class="span4 controls">
							<input type="text">
						</div>
						<label class="span2 control-label">카테 고리</label>
						<div class="span4 controls">
							<input type="text">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span11"></div>
					<div class="span1">
						<button type="button" class="btn btn-primary" title="카테고리 추가">+</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span9 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>
							SNMP 옵션
						</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<label class="span2 control-label">카테 고리</label>
						<div class="span4 controls">
							<input type="text">
						</div>
						<label class="span2 control-label">카테 고리</label>
						<div class="span4 controls">
							<input type="text">
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<label class="span2 control-label">사용 유무</label>
						<input type="checkbox">
						<span class="label label-important">Snmp를 사용하지 않는 노드의 경우 선택해주세요.</span>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span12">
				<div class="span8"></div>
				<div class="span1">
					<button type="button" class="btn btn-primary" title="카테고리 추가">저장</button>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
