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
	<jsp:param value="그룹관리" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/notification.js" />"></script>
<script src="<c:url value="/resources/js/group.js" />"></script>
<%-- <script src="<c:url value="/resources/selectbox/js/jquery-1.3.2.min.js" />"></script> --%>
<script src="<c:url value="/resources/selectbox/js/mySelect.class.js" />"></script>
<%-- <link rel="stylesheet"  href="<c:url value="/resources/selectbox/css/layoutstyle.css"/>" /> --%>


<script type="text/javascript">
	$(document).ready(function() {
		 	
		/* 그룹 전체 리스트 */
		/*	Get a list of group */
		//getGroupList(getGroup);
		
		/* Get a list of users */
		getUserListTotal(userList);
		
		/* Get a specific group, by group name*/
		getGroupMember(getGroupMemberList,"${name}");
		
	});
	
	
	/**
	 * GETGet a list of users
	 * 사용자 리스트 전체가져오기
	 */
	 	function userList(jsonObj) {
			var str =userNameSelectStr(jsonObj);
			
			$("#userListSelect").append(str);
		} 
	
	function getGroupMemberList(jsonObj){
		
		var str =groupMemberSelectStr(jsonObj);
		
		$("#groupMemberListSelect").append(str);
	}
	
	
	
	
	
	/****************SelectBox Script*****************/
	 var test=new mySelect();
	/****************SelectBox Script*****************/	
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
		<div class="accordion-heading">
				    <h3>
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#eventStepTwo">
				        	${name}&nbsp;&nbsp;<span class="label label-info">그룹&nbsp;명</span>
						</a>
					</h3>
			    </div>
			<div class="span12 well well-small">
			
			<form  id="groupFrm" name="groupFrm">
				<input type="hidden" id ="name" name="name" value="${name}" />
			</form>
				 <form action="###" method="post">
		            <div style="float:left;">
		                 <select id="userListSelect" name="userListSelect" ondblclick="test.movetoright();" multiple="multiple" class="select_left" style="display:block;width:150px;height:200px;overflow:visible;">
		                </select> 
		            </div>
		            <div style=" float:left; padding:8px;">
		            	<p style=" padding-bottom:12px;"><input onclick="test.movetoright()" type="button"  class="button_movetoright btn-warning" value="&gt;&gt;" /></p>
		                <p><input onclick="test.movetoleft();"  type="button" class="button_movetoleft btn-info" value="&lt;&lt;" /></p>
		            </div>
		            <div style=" float:left;">
		                <select  id="groupMemberListSelect" name="groupMemberListSelect" ondblclick="test.movetoleft();" style=" width:150px; border:1px solid #ccc; overflow:visible; height:200px;" class="select_right" multiple="multiple">
		                </select>
		                <p>
		                    <strong><a href="javascript:test.top();" title="">최상위</a></strong>
		                    <strong><a href="javascript:test.up();" title="">위로</a></strong>
		                    <strong><a href="javascript:test.down();" title="">아래로</a></strong>
		                    <strong><a href="javascript:test.bottom();" title="">최하위</a></strong>
		                </p>
		            </div>
					<input type="hidden" id="res" name="res" class="select_input">
		      </form>
		      
		      
		      
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
