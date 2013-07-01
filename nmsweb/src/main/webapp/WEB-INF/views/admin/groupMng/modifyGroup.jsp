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
<script src="<c:url value="/resources/selectbox/js/mySelect.class.js" />"></script>
<script src="<c:url value="/resources/selectbox/js/myRoleSelect.class.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<!--dhtmlx-->
<link rel="STYLESHEET" type="text/css" href="<c:url value="/dhtmlx/dhtmlx.css"/>">
<script  src="<c:url value="/dhtmlx/dhtmlxGrid/codebase/dhtmlxcommon.js"/>"></script>
<script  src="<c:url value="/dhtmlx/dhtmlx.js"/>"></script>
<!--dhtmlx-->


<script type="text/javascript">
	var g_menuItems = null;
	$(document).ready(function() {
		 	
		/* 그룹 전체 리스트 */
		/*	Get a list of group */
		//getGroupList(getGroup);
		
		/* Get a list of users */
		getUserListTotal(userList);
		
		/* Get a specific group, by group name*/
		getGroupMember(getGroupMemberList,"${name}");
		
		initMenuTree();
		
	});
	
	/*********************************dhtmlx*tree*********************************************/
	function initMenuTree()
	{
		g_menuItems = new dhtmlXTreeObject('divMenuTree', '100%', '100%', 0);
		g_menuItems.setImagePath('<c:url value="/dhtmlx/imgs/csh_bluebooks/"/>');
		g_menuItems.enableTreeLines(true);
		g_menuItems.enableCheckBoxes(true); 
		g_menuItems.enableDragAndDrop('disable');
		//g_menuItems.attachEvent("onClick", onMenuTreeSelected);
		g_menuItems.enableKeyboardNavigation(true);
		g_menuItems.enableSmartRendering(1);
		g_menuItems.enableThreeStateCheckboxes(true);
		
		loadMenuItems();
		
		//console.log("------------menuItems----------"+menuItems);
		
	}

	function loadMenuItems(){
		
		/**************************************************************************/	
		
		  $.ajax({
		      	url : '<c:url value="/admin/groupMng/getMenuTree.do" />',
		        type:'post',
		        dataType:'json',
		        async:false,
		        error:function(data, status, err){
		            alert('Error, service not found');
		            alert(res.errorMessage);
		        },
		        success:function(res){
		        	console.log(res);
		        
		        	//g_menuItems.setCheck(res.menuItems.menuId,false);
		         if(res.isSuccess == true){
		       			
		        	 if(res.isSuccess == false)
		        		{
		         		alert(res.errorMessage);        		
		         		return;
		        		}
		         	
		         	for(var i = 0; i < res.menuItems.length; i++)
		         	{
		 	        	var parent = 0;
		 	        	if(res.menuItems[i].upperMenuId != null)
		         		{
		         			parent = res.menuItems[i].upperMenuId;
		         		}
		 	        	g_menuItems.insertNewChild(parent, res.menuItems[i].menuId, res.menuItems[i].menuNm, null);
		 	        	g_menuItems.setUserData(res.menuItems[i].menuId, 'menuItem', res.menuItems[i]);
		 	        	
		         	}
		       		}
		        }
		  
			});  
		
	/**************************************************************************/
		
		/* var menuItem = null;

	 	 <c:forEach var="menuItem" items="${menuItems}">
		
			<c:set var="parentId" value="0" />
			<c:if test="${not empty menuItem.upperMenuId}">
				<c:set var="parentId" value="${menuItem.upperMenuId}" />	
			</c:if>
			
			menuItem = {
				menuId : ${menuItem.menuId},
				menuLev : ${menuItem.menuLev},
				menuOrd : ${menuItem.menuOrd},
				upperMenuId : ${parentId},
				menuNm : '${menuItem.menuNm}',
				url : '${menuItem.url}',
				openYn : '${menuItem.openYn}'
			};
			console.log(menuItem);
			g_menuItems.insertNewChild(${parentId}, ${menuItem.menuId}, '${menuItem.menuNm}', null);
    		g_menuItems.setUserData(menuItem.menuId, 'menuItem', menuItem); 
		
		</c:forEach>  */
		
		selectMeunId();
		
	}

//chackbox reg
function saveMenuItems()
{
	var checkItemId = g_menuItems.getAllChecked();
	var checkItemIds = checkItemId.split(',');
	
	$("#SaveMenuItemsFrm").children().remove();
	
	for(var i = 0; i < checkItemIds.length; i++)
	{
		
		var tmpTr = $('<tr></tr>');
		var tmpTd = $('<td><input type="hidden" name="menuId" value="" /></td>'); 
		var tmpTd2 = $('<td><input type="hidden" name="groupName" value="${name}" /></td>'); 
		
		tmpTr.append(tmpTd);
		tmpTr.append(tmpTd2);
		
		tmpTd.find('input[name=menuId]').val(checkItemIds[i]);
		
		$("#SaveMenuItemsFrm").append(tmpTr);
		
	}
	
	alert('요청이 완료되었습니다.');
	
	var data = $("#SaveMenuItemsFrm").serialize();

	
	
	 $.ajax({
      	url : '<c:url value="/admin/groupMng/regGroupMenu.do" />',
        type:'post',
        dataType:'json',
        data:data,
        error:function(data, status, err){
            alert('Error, service not found');
            
            $("#SaveMenuItemsFrm").children().remove();
            
        },
        success:function(res){
        	if(res.isSuccess == false)
       		{
        		alert(res.errorMessage);        		
        		return;
       		}
        	
        }
	}); 
	
}
	
	function selectMeunId(){

		/**************************************************************************/	
			var groupNm = ('${name}');// 그룹명
			
			  $.ajax({
			      	url : '<c:url value="/admin/groupMng/selectMeunId.do?groupNm=" />'+groupNm,
			        type:'post',
			        dataType:'json',
			        async:false,
			        error:function(data, status, err){
			            alert('Error, service not found');
			            alert(res.errorMessage);
			        },
			        success:function(res){
			        	console.log(res);
			        
			        	//g_menuItems.setCheck(res.menuItems.menuId,false);
			         if(res.isSuccess == true){
			       			
			       			for(var i = 0; i < res.menuItems.length; i++)
				        	{
				        		//alert("res.menuItems[i].menuId : "+res.menuItems[i].menuId);
				        		
				        		g_menuItems.setCheck(res.menuItems[i].menuId,true);
				        	}	
			       			
			       		}
			        }
			  
				});  
			
		/**************************************************************************/
		
		
		
		
	}
	
	
	/*********************************dhtmlx*tree*********************************************/
	
	/**
	 * GETGet a list of users
	 * 사용자 리스트 전체가져오기
	 */
	 	function userList(jsonObj) {
			var selectStr =userNameSelectStr(jsonObj);
			
			
			$("#userListSelect").append(selectStr);
		} 
	
	function getGroupMemberList(jsonObj){
		
		var selectStr =groupMemberSelectStr(jsonObj);
		
		
		$("#groupMemberListSelect").append(selectStr);
	}
	
	
	
	
	
	/****************SelectBox Script*****************/
	 var test=new mySelect();
	/*  var roleTest=new myRoleSelect(); */
	/****************SelectBox Script*****************/	
	
	
	
	
	
	
	function regInGroup(){
		
		var groupName = ('${name}');// 그룹명
		var userName = $("#left select[name=userListSelect]").val();//사용자name
		
		 
		 
 		$.ajax({
				
				type : 'put',
				url : '/' + version + '/groups/'+groupName+'/users/'+userName,
				contentType : 'application/json',
				dataType:'json',
				error : function(data) {
					alert('그룹등록 실패');
				},
				success : function(data) {
					console.log("그룹등록 성공");
					regUserTbl(userName,groupName);
				}
			});  
		}
	
	function delInGroup(userName){

		var groupName = ('${name}');// 그룹명
		
		 $.ajax({
		   				
   				type : 'delete',
   				url : '/' + version + '/groups/'+groupName+'/users/'+userName,
   				contentType : 'application/json',
   				dataType:'json',
   				error : function(data) {
   					//alert('그룹 내  사용자 삭제 실패');
   				},
   				success : function(data) {
   					deleteUserTbl(userName,groupName);
   				}
   			});  
		
		
	}
	
	
	
</script>

</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		<form name="SaveMenuItemsFrm" id="SaveMenuItemsFrm">
		</form>
		<div class="row-fluid">
			<div class="span12" style="height: 0px;" >
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li class="active"><a href="/v1/admin/groupMng.do">그룹관리</a></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			<div class="span4">
				<div class="accordion-heading">
				    <h3>
						<abbr title="선택한 그룹이름"><a  href="/v1/admin/groupMng.do">
				        	&nbsp;&nbsp;${name}&nbsp;&nbsp;<span class="label label-info">그룹&nbsp;명</span>
						</a></abbr>
					</h3>
			    </div>
			</div> 
			<div class="span5 "></div>
			<!-- <div class="span3">
				<a type="button" class="btn btn-primary" title="" href="/v1/admin/groupMng.do" style="margin-top: 29px;">FINISH</a>
			</div>  --> 
		</div>
			
		<div class="row-fluid">	
		<div class=" span6 well">
			
			<form  id="groupFrm" name="groupFrm">
				<input type="hidden" id ="name" name="name" value="${name}" />
			</form>
				 <form action="###" method="post" id="left" name ="left">
					<div class="row-fluid" style="margin-top: -39px; ">
						<!-- <div class="span2 "></div> -->
						<div class="span5 ">
							<h4><abbr title="해당 그룹에 추가시킬 사용자 목록"><a type="text"  title="" href="/v1/admin/userMng.do">Users</a></abbr></h4>
						</div>
						<div class="span2 "></div>
						<div class="span3 ">
							<h4><abbr title="해당 그룹"><a type="text"  title="" href="/v1/admin/groupMng.do">${name}</a></abbr></h4>
						</div>
					</div>
		            <div style="float:left;">
		                 <select id="userListSelect" name="userListSelect" ondblclick="test.movetoright();" multiple="multiple" class="select_left" style="display:block;width:180px;height:290px;overflow:visible;">
			                <!--------<option str>----------->
			                <!--------<option str>----------->
			                <!--------<option str>----------->
		                </select> 
		            </div>
		            </form>
		            <div style=" float:left; padding:8px;">
		            	<p style=" padding-bottom:12px;"><input onclick="test.movetoright()" type="button"  class="button_movetoright btn-warning" value="&gt;&gt;" style ="margin-top: 70px;margin-left: 18px;margin-right: 18px;" /></p>
		                <p><input onclick="test.movetoleft();"  type="button" class="button_movetoleft btn-info" value="&lt;&lt;" style ="margin-top: 50px; margin-left: 18px;margin-right: 18px;"  /></p>
		            </div>
		            
		            
		            
		           <form action="###" method="post" id="right" name ="right"> 
		            <div style=" float:left;">
		                <select  id="groupMemberListSelect" name="groupMemberListSelect" ondblclick="test.movetoleft();" style=" margin-top: -20px; width:180px ; border:1px solid #ccc; overflow:visible; height:290px;" class="select_right" multiple="multiple">
			                <!--------<option str>----------->
			                <!--------<option str>----------->
			                <!--------<option str>----------->
		                </select>
		               <!--  <p>&nbsp;&nbsp;&nbsp;
		                    <strong><a class="icon-chevron-up" href="javascript:test.top();" title=""></a></strong>&nbsp;&nbsp;&nbsp;
		                    <strong><a class="icon-arrow-up" href="javascript:test.up();" title=""></a></strong>&nbsp;&nbsp;&nbsp;
		                    <strong><a class="icon-arrow-down" href="javascript:test.down();" title=""></a></strong>&nbsp;&nbsp;&nbsp;
		                    <strong><a class="icon-chevron-down" href="javascript:test.bottom();" title=""></a></strong>
		                </p> -->
		            </div>
					<input type="hidden" id="res" name="res" class="select_input">
		      </form>
      
		      
			</div>
			
			<!-- tree -->
			 <div class="span6 well ">
			<h4 style="margin-top: -7px; margin-bottom: -15px;"><abbr title="그룹에 추가할 메뉴 항목">메뉴&nbsp;항목&nbsp;트리</abbr></h4>
			<form  id="groupFrm" name="groupFrm">
				<input type="hidden" id ="name" name="name" value="${name}" />
			</form>
		        <table height="300px">
					<tr>
						<td>
							<div id="divMenuTree" style="border:1px solid #ccc; width: 446px; height: 268px; float:left;"></div>
						</td>
					</tr>
					<tr>
						<td>
							<p class="btn" style="margin-bottom: -14px; margin-left: 362px;"><a href="javascript:saveMenuItems();">저장</a></p>
						</td>
					</tr>
				</table>    
		      </div>  
			
			<!-- tree -->
			
			
			</div>
		
		

		
	</div>
	<!-- /container -->
</body>
</html>
