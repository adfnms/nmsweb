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
<script src="<c:url value="/resources/js/userMng.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		//전체 리스트 
		getUserListTotal(callbackUseList);

	});

	function callbackUseList(jsonObj) {

		var str = "";

		for ( var i in jsonObj.user) {

			str += "<tr  onclick=\"javascript:getUserDetail('"+jsonObj.user[i].user_id+"');\">";
			str += "	<td>";
			str += jsonObj.user[i].user-id;
			str += "	</td>";
			str += "	<td>";
			str += jsonObj.user[i].full-name;
			str += "	</td>";
			str += "	<td>";
			str += jsonObj.user[i].user-comments;
			str += "	</td>";
// 			str += "	<td>";
// 			str += jsonObj.user[i].password;
// 			str += "	</td>";
			str += "</tr>";
		}

		$("#userListTable").append(str);
	}
	
	function getUserDetail(user_id){
		
		
		
		alert("-------------------user_id--------------------"+user_id);
		
		$("#userIdFrm").find('[name=user_id]:input').val(user_id);
		
		var frm = document.getElementById("userIdFrm");
		
		frm.action = "/v1/admin/userMng/userModify.do?user-Id="+user_id;
		frm.submit();
		
		/*  $.ajax({
			
			 type:'post',
		 	url:'<c:url value="/admin/userMng/userModify.do" />',
			data:'user-Id='+user_id,
			dataType:'json',
			error:function(res){
				
				alert("서비스 실패");
					
	        },
	        success: function(res){
	        	
	        	if(res.result == false){
	        	
	        		alert(res.message);
	        		
		   		}else{
		   			var url = "/v1/admin/userMng/userModify.do?user-Id="+user_id;
		   			window.open(url,'userModify','width=1160,height=640,scrolling=no, scrollbars=no');
 			
		   		}
			}		
	});  */
		
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span9">
				<h2 class="muted">운영관리 > 사용자 관리</h2>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			<div class="span9">
			<form  id="userIdFrm" name="userIdFrm" method="post">
				<input type="hidden" id ="user_id" name="user_id" value="" />
			</form>
				<table class="table table-striped" id="userListTable">
					<colgroup>
						<col class="span2"/>
						<col class="span3"/>
						<col class="span4"/>
					</colgroup>
					<thead>
						<tr>
							<th>사용자 ID</th>
							<th>이름(FullName)</th>
							<th>소개</th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
