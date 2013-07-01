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
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/* 그룹 전체 리스트 */
		/*	Get a list of group */
		getGroupList(getGroup);
		
		//getAuth(getGroup);
	});
	
	
/**
 * Get a list of group
 * 그룹 리스트 전체가져오기
 */
 	/* function getGroup(jsonObj) {
		console.log(jsonObj);
		
		var str = groupListStr(jsonObj);
		
		$("#groupTable").append(str);

	}  */
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
		var comments = $("#groupInfoFrm input[name=comments]").val();
		var user = $("#groupInfoFrm input[name=user]").val();
		
		str = groupStr (groupName,comments,user);
		 $.ajax({
			type : 'post',
			url : '<c:url value="/groups/" />',
			dataType : 'json',
			data : str,
			contentType : 'application/json', 
			error : function(data) {
				//console.log(data);
				alert('그룹 등록 서비스 실패');
			},
			success : function(data) {
				
				console.log("그룹 등록 서비스 성공");
				
				regGroupTbl(groupName,comments);
			}
		}); 
		
	} 
	
	function deleteGroup(name){
		
		var option = confirm(" 삭제 하시겠습니까? ");
		
		if(option == true )
		{
			if(name == "Admin" ||name=="visitorGroup"){
				
				alert("삭제할수 없는 그룹입니다.");
				return false;
			}else{
				$.ajax({
					type : 'delete',
					url : '<c:url value="/groups/" />'+name,
					dataType : 'json',
					contentType : 'application/json', 
					error : function(data) {
						//console.log(data);
						alert('그룹 삭제 서비스 실패');
					},
					success : function(data) {
						
						console.log("그룹 삭제 서비스 성공");
						deleteGroupTbl(name);
						
					}
				}); 
			}
				 
			 
			}else if(option == false ){
			 	
				alert("취소 되었습니다.");
				}
		 
	}
	
	 function regGroupTbl(groupName,comments){
		// alert("groupName : "+groupName);
		// alert("comments : "+comments);
		
	      $.ajax({
			type : 'get',
			url : '<c:url value="/admin/groupMng/regGroup.do" />',
			data: 'groupName='+groupName+'&comments='+comments,
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
	
	function deleteGroupTbl(groupName){
		
		
		$.ajax({
			type : 'get',
			url : '<c:url value="/admin/groupMng/deleteGroupTbl.do" />',
			data: 'groupName='+groupName,
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
			<form  id="groupFrm" name="groupFrm">
				<input type="hidden" id ="name" name="name" value="" />
			</form>
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
			
			
				
				<table class="table table-striped table-hover table-condensed" id="groupTable" >
					
					
						<tr>
							<th><h4><abbr title="그룹 이름">GroupName</abbr></h4></th>
							<th><h4><abbr title="그룹 설명">GroupComments</abbr></h4></th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
					
				</table>
			
			</div>
				<div class="row-fluid">
					<div class="span12">
					<div class="span10"></div>
						<div class="span2 ">
							<abbr title="새 그룹 등록 버튼"><a type="button" class="btn btn-primary" title="" href="#popupRegMethod" data-toggle="modal">새 그룹 등록</a></abbr>
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
				<div class="span5 well well-small" style="width: 540px;" >
					<div class="row-fluid">
						<div class="span9">
							<label class="span4 control-label">Group&nbsp;Name</label>
							<div class="span7 controls">
								<input style="width: 367px;" type="text"   id="name"   name="name"   placeholder="Group&nbsp;Name" value="" > 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span9">
							<label class="span4 control-label">Group&nbsp;comments</label>
							<div class="span7 controls">
								<input style="width: 367px;" type="text"   id="comments"   name="comments"   placeholder="Group&nbsp;comments" value="" > 
							</div>
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
