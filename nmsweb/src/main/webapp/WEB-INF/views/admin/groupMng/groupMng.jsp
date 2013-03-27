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
	<jsp:param value="회원가입" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/notification.js" />"></script>
<script src="<c:url value="/resources/js/group.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/* 그룹 전체 리스트 */
		/*	Get a list of group */
		getGroupList(getGroup);
	});
	
	
/**
 * Get a list of group
 * 그룹 리스트 전체가져오기
 */
 	function getGroup(jsonObj) {
		console.log(jsonObj);
		
		var str = groupListStr(jsonObj);
		
		$("#groupTable").append(str);

	} 
	
	
	/*그룹 상세정보 갖고오기*/
	
	function getGroupInfo(obj){
		
		alert(obj);
		
		$("#groupFrm").find('[name=name]:input').val(obj);
		
		var frm = document.getElementById("groupFrm");
		
		frm.action = "/v1/admin/groupMng/modifyGroup.do";
		
		frm.submit();
		
	}
	
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li class="active"><a href="/v1/admin/groupMng.do">그룹관리</a></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			<div class="span12 well well-small">
			
			<form  id="groupFrm" name="groupFrm">
				<input type="hidden" id ="name" name="name" value="" />
			</form>
				
				<table class="table table-striped table-hover" id="groupTable" >
					<colgroup>
						<col class="span4"/>
						<col class="span5"/>
						<col class="span2"/>
					</colgroup>
					<thead>
						<tr>
							<th>Group(GroupName)</th>
							<th>소개(Comment)</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
				</table>
			
			</div>
				<div class="row-fluid">
					<div class="span12">
					<div class="span10"></div>
						<div class="span2 ">
							<a type="button" class="btn btn-primary" title="" href="/v1/admin/userMng/userReg.do">+ 새 그룹 추가</a>
						</div>
					</div>
					
				</div>
			
				
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
