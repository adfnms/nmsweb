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
		
		$("#groupFrm").find('[name=name]:input').val(obj);
		
		var frm = document.getElementById("groupFrm");
		
		frm.action = "/v1/admin/groupMng/modifyGroup.do";
		
		frm.submit();
		
	}
	
	function regGroup(){
		
		var groupName = $("#groupInfoFrm input[name=name]").val();
		var comments = $("#groupInfoFrm textarea[name=comments]").val();
		var user = $("#groupInfoFrm input[name=user]").val();
		
		str = groupStr (groupName,comments,user);
		
		$.ajax({
			type : 'post',
			url : '/' + version + '/groups/',
			dataType : 'json',
			data : str,
			contentType : 'application/json', 
			error : function(data) {
				//console.log(data);
				alert('그룹 등록 서비스 실패');
			},
			success : function(data) {
				
				console.log("그룹 등록 서비스 성공");
				
				$(location).attr('href', "/v1/admin/groupMng/modifyGroup.do?name="+groupName);
			}
		}); 
		
	}
	
	function deleteGroup(name){
		
		$.ajax({
			type : 'delete',
			url : '/' + version + '/groups/'+name,
			dataType : 'json',
			contentType : 'application/json', 
			error : function(data) {
				//console.log(data);
				alert('그룹 삭제 서비스 실패');
			},
			success : function(data) {
				
				console.log("그룹 삭제 서비스 성공");
				
				$(location).attr('href', "/v1/admin/groupMng.do");
			}
		}); 
		
		
		
		
		
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
				
				<table class="table table-striped table-hover table-condensed" id="groupTable" >
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
							<a type="button" class="btn btn-primary" title="" href="#popupRegMethod" data-toggle="modal">새 그룹 등록</a> 
						</div>
					</div>
					
				</div>
			
				
		</div>
		<hr>
	</div>
	<!-- /container -->
	
	<!-- ------------------------그룹 등록 Popup창----------------------- -->	
 <div id="popupRegMethod" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">새&nbsp;그룹&nbsp;등록</h3>
	</div>
	<form id ="groupInfoFrm" name= "groupInfoFrm">
		<div class="modal-body" style="max-height: 800px;">
			<div class="row-fluid">
				<div class="span12">
					<label class="span3 control-label"><h4>그룹&nbsp;명</h4></label>
					<input type="text"   id="name"   name="name" class="span12"   placeholder=""> 
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<label class="span3 control-label"><h4>comments</h4></label>
					<textarea  rows="4" id="comments"  name="comments" class="span12"   placeholder=""></textarea> 
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<input type="hidden"   id="user"   name="user" class="span12"   placeholder=""> 
				</div>
			</div>
		</div>
	</form>
	<div class="modal-footer">
		<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="javascript:regGroup()">등록</button>
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div> 
	
</body>
</html>
