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

<html lang="en">

<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="이벤트 선택" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>

<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/notification.js" />"></script>
<script src="<c:url value="/resources/js/group.js" />"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	/* GETConfirm kind of registered all event */
	 getAllEvent(getEvent); 
	 
	/* Get a list of users */
	getUserListTotal(callbackUseList);
	
	/*Get a list of groups*/
	getGroupList(getGroupNmae);
	
	/* GET list of the initial agent(destination) accept to notification */
	getPathList(getPathsName);
	
});

/* GETConfirm kind of registered all event Callback */
function getEvent(jsonObj) {

		var str = getEventJsonObj(jsonObj);
		$('#userTable').append(str);

	}
/* // GETConfirm kind of registered all event Callback */


function setDestination(obj){
	
	var object = obj.split(',');
	
	for(var i = 0 ; i < object.length; i++)
	{
		var uei= object[0];
		var name= object[1];
		
		$("#destinationFrm").find('[name=uei]:input').val(uei);
		$("#destinationFrm").find('[name=noticeQueue]:input').val(name);
		
		$("#StepOne").html("1단계&nbsp;&nbsp;<span class=\"label label-important\">이벤트 선택</span>&nbsp;&nbsp;&nbsp;"+name);
	} 
	
	
	/* 히든폼에 경로 보내기
	1.uei,메세지명,설명,메일제목,요약메세지,메세지,목적지선택,이름*/
	
	/*2.목적지 관리버튼 에서 데이터가 목적지 선택으로*/
}





/**
 * GETGet a list of users
 * 사용자 리스트 전체가져오기
 */
 	function callbackUseList(jsonObj) {
		var str =userNameStr(jsonObj);
		
		$("#userListTable").append(str);
	} 
	
/**
 * Get a list of groups
 * 그룹 리스트 전체가져오기
 */
 	function getGroupNmae(jsonObj) {
		var str =groupNameStr(jsonObj);
		
		$("#groupTable").append(str);
	} 	
	
/**
 * Get a list of Paths
 * Paths 리스트 전체가져오기
 */
 	function getPathsName(jsonObj) {
		var str =pathsNameStr(jsonObj);
		var selectStr =pathsNameSelectStr(jsonObj);
		
		$("#PathsTable").append(str);
		$("#destinationPath").append(selectStr);
	}


 	function optionValue(name){
 		
 		alert(name);
 		
 		
 	}
	
 	function destinationPathInfo(userid){
 		alert(userid);
 	}
 	
 	
 	/*get form[#destinationFrm] object*/
 	function regNotification(){
 		
 		var uei = $("#destinationFrm input[name=uei]").val();						//0.이벤트
 		var noticeQueue = $("#destinationFrm input[name=noticeQueue]").val();		//1.이벤트 라벨
 		
 		//alert("----------noticeQueue------------"+noticeQueue);
 		
 		var name = $("#destiFrm input[name=name]").val();							//2.메세지명
 		var description = $("#destiFrm input[name=description]").val();				//3.설명
 		var subject = $("#destiFrm input[name=subject]").val();						//4.메일제목
 		var numericMessage = $("#destiFrm textarea[name=numericMessage]").val();	//5.요약메세지
 		var textMessage = $("#destiFrm textarea[name=textMessage]").val();			//6.rule
 		var destinationPath = $("#destiFrm select").val();							//7.목적지
 		var status = $("#destiFrm input[name=status]").val();						//8.상태
 		var rule = $("#destiFrm input[name=rule]").val();							//9.메세지
 		
 	var str=requestBodyStr ( uei,name,description,subject,numericMessage,textMessage,destinationPath,status,rule,noticeQueue);

 	//console.log('/' + version + '/notifications/searchUser/');
 		
	 	 $.ajax({
			
			type : 'post',
			url : '/' + version + '/notifications/eventNotifications',
			contentType : 'application/json',
			data : str,
			error : function() {
				alert('공지 등록 실패');
			},
			success : function(data) {
				
			}
		}); 
 	
 	}
 	
function deletePath(pathName){
 		
 		console.log(pathName);
 		
 		/*  $.ajax({
 			
 			type : 'delete',
 			url : '/' + version + '/notifications/eventNotifications',
 			contentType : 'application/json',
 			data : str,
 			error : function() {
 				alert('공지 등록 실패');
 			},
 			success : function(data) {
 				
 			}
 		});  */
 		
 		
 	}
 	
function selectPath(pathName){
		
		console.log(pathName);
		
		
	}
 	
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		<form action="" id="destinationFrm" name="destinationFrm">	
			<input type="hidden" id="noticeQueue" name="noticeQueue" value="" />			<!--uei 이름 -->
			<input type="hidden" id="uei" name="uei" value="" />							<!-- uei -->
		</form>
		
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li><a href="/v1/admin/setting.do">사용자 설정</a> <span class="divider">/</span></li>
					<li><a href="/v1/admin/setting/configureNotification.do">공지 설정</a> <span class="divider">/</span></li>
					<li class="active">공지추가</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<!-- test -->
			<div class="accordion" id="accordion2">
			  <div class="accordion-group">
			    <div class="accordion-heading">
				    <h3>
				      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#eventStepOne" id=StepOne >
				        1단계&nbsp;&nbsp;<span class="label label-important">이벤트 선택</span>
				      </a>
			      	</h3>
			    </div>
			    <div id="eventStepOne" class="accordion-body collapse in">
			      <div class="accordion-inner">
			        <div class="span12 well well-small">
						<form id="eventInfoFrm" name = "memberInfoFrm" method="post">
							<!--리스트 시작  -->	
						<div class="span12" style="height:347px;  overflow-y:auto;">
							<table class="table table-striped table-hover table-condensed table-stacked"  id="eventListTable">
								<colgroup>
									<col class="span12"/>
								</colgroup>
								
									<tr>
										<th>even List</th>
									</tr>
								
							</table>
							
						</div>
						
						<!-- 끝 -->	
						</form>
						<hr>
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-group">
			    <div class="accordion-heading">
				    <h3>
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#eventStepTwo">
				        	2단계&nbsp;&nbsp;<span class="label label-important">공지 메시지 정의</span>
						</a>
					</h3>
			    </div>
			    <div id="eventStepTwo" class="accordion-body collapse">
			      <div class="accordion-inner">
					<div class="span12 well well-small">
						<form id="destiFrm" name = "destiFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted">메시지 명</label>
									<div class="span10 controls" >
										<input  type="text"   id="name"   name="name" class="span11"   placeholder=""> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted">설명</label>
									<div class="span10 controls" >
										<input  type="text"   id="description"   name="description" class="span11"   placeholder=""> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted">메일 제목</label>
									<div class="span10 controls" >
										<input  type="text"   id="subject"   name="subject" class="span11"   placeholder=""> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted">요약 메세지</label>
									<div class="span10 controls" >
										<textarea rows="3"    id="numericMessage"   name="numericMessage" class="span11"   placeholder=""></textarea>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted">메세지</label>
									<div class="span10 controls" >
										<textarea rows="3"    id="textMessage"   name="textMessage" class="span11"   placeholder=""></textarea>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted">목적지 선택</label>
									<div class="span4 controls" >
										<select   id="destinationPath" name="destinationPath">
		               					
		               					</select>
									</div>
									<div class= "span2"></div>
									<div class="span4 controls">
										<a type="button" class="btn" title="" href="#popDestinationPaths" data-toggle="modal">목적지관리</a>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted">Rule</label>
									<div class="span4 controls" >
										<input  type="text"   id="rule"   name="rule" class=""   placeholder="" value="(IPADDR IPLIKE *.*.*.*)"> 
									</div>
									<label class="span2 control-label muted" style ="font-size:16px;">status</label>
									<label class="radio span2 ">    
										<input type="radio" id="statusOn" name="status" value="on">on</label>
									<label class="radio span2 ">    
										<input type="radio" id="statusOff" name="status" value="off">off</label>
								</div>
							</div>
						</form>
						<div class="row-fluid">
							<div class="span12">
								<div class = "span2"></div>
								<div class="span4 controls">
									<a type="button" class="btn" title="" href="#popupRegMethod" data-toggle="modal">입력방법</a> 
								</div>
								<div class = "span2"></div>
								<div class="span4">
									<a type="button" class="btn btn-primary" title="" href="javascript:regNotification()">+ 공지등록</a>
								</div>
							</div>
						</div>
					</div>
			      </div>
			    </div>
			  </div>
			</div>
			<!-- test -->
		</div>
	</div>
			
	<!-- /container -->
	
	<!-- --------------------------목적지 설정 1단계 popup--------------------- -->	
					
	
 <div id="popDestinationPaths" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">DestinationPath</h3>
	</div>
	<div class="modal-body" >
		<div>
			<div class="accordion" id="accordion3">
			  <div class="accordion-group">
			    <div class="accordion-heading">
			    	<h4>
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseOne">
			        1단계&nbsp;[목적지&nbsp;선택]&nbsp;&nbsp;<span class="label label-info">공지 메시지 정의</span>
			      </a></h4>
			    </div>
			    <div id="collapseOne" class="accordion-body collapse in" style="height:400px;  overflow-y:auto;">
			      <div class="accordion-inner">
			      <div class="span5" style="margin-left: 5px;" data-toggle="collapse">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<table class="table table-striped table-hover table-condensed" id="PathsTable">
								<colgroup>
									<col class="span5"/>
									<col class="span2"/>
									<col class="span2"/>
								</colgroup>
								<thead>
									<tr>
										<th>Existing Paths</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
							</table>
						</form>
						<h4>
							<a class="accordion-toggle text-success" data-toggle="collapse" data-parent="#accordion3" href="#collapseTwo">
								 [Add&nbsp;New&nbsp;Path]
						  </a></h4>
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-group">
			    <div class="accordion-heading">
				    <h4>
					     <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseTwo">
					        2단계&nbsp;[목적지&nbsp;명]&nbsp;&nbsp;<span class="label label-info">목적지 관리</span>
					     </a>
				     </h4>
			    </div>
			    <div id="collapseTwo" class="accordion-body collapse">
			      <div class="accordion-inner">
			    <div class="span5" style="margin-left: 5px;">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span3 control-label">메시지 명</label>
									<div class="span9 controls" >
										<input type="text"   id=""   name="" class="span12"   placeholder=""> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span3 control-label">Initial Delay:</label>
									<div class="span9 controls" >
										<select>
											<option>0s</option>
											<option>1s</option>
											<option>2s</option>
											<option>5s</option>
											<option>10s</option>
											<option>15s</option>
											<option>30s</option>
											<option>0m</option>
											<option>1m</option>
											<option>2m</option>
											<option>5m</option>
											<option>10m</option>
											<option>15m</option>
											<option>30m</option>
											<option>0h</option>
											<option>1h</option>
											<option>2h</option>
											<option>3h</option>
											<option>6h</option>
											<option>12h</option>
											<option>1d</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span3 control-label">Initial Targets</label>
										<textarea  rows="4" id=""  name="" class="span12"   placeholder=""></textarea> 
								</div>
								<h4>
								 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseThree">
			        				[add&nbsp;targets]
			      				</a></h4>
							</div>
						</form>
						<!-- <a type="button" class="btn btn-primary" href="#popupShow3" onclick="pop3Togling()" class="pop3Togling">3단계로</a> --> 
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-group">
			    <div class="accordion-heading">
			    <h4>
			      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseThree">
			        3단계&nbsp;[목적지&nbsp;대상&nbsp;관리]&nbsp;&nbsp;<span class="label label-info">목적지 관리</span>
			      </a></h4>
			    </div>
			    <div id="collapseThree" class="accordion-body collapse">
			      <div class="accordion-inner">
				    <div class="span5" style="margin-left: 5px;">
						<div>
							<table>
								<tr>
									<td>
										<div class="span3" style="margin-left: 0px; width: 233px; height:200px;overflow-y:auto;" >
											<table class="table table-striped table-condensed" id="userTable">
												<colgroup>
													<col class="span3"/>
													
												</colgroup>
													<tr>
														<th>user</th>
													</tr>
											</table>
										</div>
									</td>
									<td>
										<div class="span3" style="margin-left: 0px; width: 242px; height:200px;overflow-y:auto;">
											<table class="table table-striped table-condensed" id="groupTable">
												<colgroup>
													<col class="span3"/>
												</colgroup>
													<tr>
														<th>group</th>
													</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</div>
						<div>
							<table>
								<tr>
									<td>
										<div class="span3" style="height:200px; margin-left: 0px; width: 233px; overflow-y:auto;"  >
											<table class="table table-striped table-condensed" id="roleTable">
												<colgroup>
													<col class="span3"/>
													
												</colgroup>
													<tr>
														<th>role</th>
													</tr>
											</table>
										</div>
									</td>
									<td>
										<div class="span3" style="margin-left: 0px; width: 242px; height:200px;overflow-y:auto;">
											<table class="table table-striped table-condensed" id="emailTable">
												<colgroup>
													<col class="span3"/>
												</colgroup>
													<tr>
														<th>e-mail</th>
													</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
	</div>
 </div>
	<!-- ------------------------입력방법 설명 Popup창----------------------- -->	
 <div id="popupRegMethod" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">입력방법</h3>
	</div>
	<div class="modal-body" style="max-height: 800px;">
		<p>
		
		
		</p>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div> 
				
	
</body>
</html>
