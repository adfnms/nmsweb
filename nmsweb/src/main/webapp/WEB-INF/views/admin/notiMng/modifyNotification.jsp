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
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
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
	
	/* Method to accept notification*/
	/* /v1/notifications/commands */
	 //getAllCommends(getCommends); 
	
});

function getCommends(jsonObj) {

	console.log(jsonObj);
	 str = getCommendsJsonObj(jsonObj);
}


/* GETConfirm kind of registered all event Callback */
function getEvent(jsonObj) {

		 str = getEventJsonObj(jsonObj);
		 
		//$("#eventListTable").append(str);
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
		
		$("#changeOne").html("&nbsp;&nbsp;<span class=\"label label-important\">변경&nbsp;uei</span>&nbsp;&nbsp;&nbsp;"+name);
	} 
}

/**
 * GETGet a list of users
 * 사용자 리스트 전체가져오기
 */
 	function callbackUseList(jsonObj) {
 		
	var str =userNameStr(jsonObj);
		$("#userTable").append(str);
	} 
	
/**
 * Get a list of groups
 * 그룹 리스트 전체가져오기
 */
 	function getGroupNmae(jsonObj) {
		var str =groupNameStr(jsonObj);
		
		$("#groupTable").append(str);
	} 	
	
/**  메세지  
 * Get a list of Paths
 * Paths 리스트 전체가져오기
 */
 	function getPathsName(jsonObj) {
		var str =pathsNameStr(jsonObj);
		var selectStr =pathsNameSelectStr(jsonObj);
		
		$("#PathsTable").append(str);
		$("#destinationPath").append(selectStr);
		
		modifyNoti( modiNoti ,"${name}");
	}
 	
 	/* Destination Path 목적지 등록 */
 	function pathRegister(){
 		
 		//var destiName = ('${name}');
 		//var roleName = $("#destiFrm select[name=status]").val();
 		//var roleAutoNotify = $("#desinationSettingFrm select[name=roleAutoNotify]").val();
 		//var roleCommand = $("#desinationSettingFrm select[name=roleCommand]").val();
 		//var roleInterval = $("#destiPathInfoFrm select[name=roleInterval]").val();
 		
 		var userName = $("#PathFrm input[name=userName]").val();
 		var groupName = $("#PathFrm input[name=groupName]").val();
 		var email = $("#emailTable input[name=email]").val();
 		var name = $("#destiPathInfoFrm input[name=Name]").val();
 		
 		
 		if($("#destiPathInfoFrm input[name=Name]").val() == "")
 	  	{
 			alert("Destnation Name 에 Path Name을 입력해주세요");
 	  		
 	  		return false;
 	  	}
 		
 		
 		var initialDelay = $("#destiPathInfoFrm select[name=initialDelay]").val();
 		var userInterval = $("#destiPathInfoFrm select[name=userInterval]").val();
 		var groupInterval = $("#destiPathInfoFrm select[name=groupInterval]").val();
 		var emailInterval = $("#destiPathInfoFrm select[name=emailInterval]").val();
 		var userAutoNotify = $("#desinationSettingFrm select[name=userAutoNotify]").val();
 		var userCommand = $("#desinationSettingFrm select[name=userCommand]").val();
 		var groupAutoNotify = $("#desinationSettingFrm select[name=groupAutoNotify]").val();
 		var groupCommand = $("#desinationSettingFrm select[name=groupCommand]").val();
 		var emailAutoNotify = $("#desinationSettingFrm select[name=emailAutoNotify]").val();
 		var emailCommand = $("#desinationSettingFrm select[name=emailCommand]").val();
 		
 		var roleName = ("");
 		var roleAutoNotify =("");
 		var roleCommand = ("");
 		var roleInterval =("");

 		str	=	getPathName(name,initialDelay,userInterval,userName,userAutoNotify,userCommand,
 							groupInterval,groupName,groupAutoNotify,groupCommand,
 							roleInterval,roleName,roleAutoNotify,roleCommand,
 							emailInterval,emailCommand,emailAutoNotify,email); 
 		
 		registerSetPathAjax();
 		
 	}
 	
	function destinationGroup(GroupNm){
		/*Set Destination Target*/
		$("#groupTr").html("<th class=\"span3 control-label  text-success\">selected : "+GroupNm+"</th>");
		$("#groupTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\">"+GroupNm+"</h4>");
		
		/*Set Destination Setting*/
		$("#groupSelect").html("<label class=\"span5 control-label  text-success\"><h4>"+GroupNm+"</h4></label>");
		
		/*Set GroupName Hidden [PathFrm] Form*/
		$("#PathFrm").find('[name=groupName]:input').val(GroupNm);
		
	}
	
 	function destinationPathInfo(userid){
 		
 		$("#userTr").html("<th class=\"span3 control-label text-success\">selected :"+userid+"</th>");
 		
 		
 		$("#userTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\">"+userid+"</h4>");
 		$("#userSelect").html("<label class=\"span5 control-label  text-success\"><h4>"+userid+"</h4></label>");
 		
 		$("#PathFrm").find('[name=userName]:input').val(userid);
 	}
 	function addEmail(){
 		var email = $("#emailTable input[name=email]").val();	
 		//$("#emailTableDiv").html("<label class=\"span3 control-label\">e-mail Targets :</label><div class=\"span9 controls\" > <h4 style= \"margin-top: 0px;\" class=\"text-success\">"+email+"</h4> <input type=\"hidden\" id=\"\"  name=\"\" class=\"span12\" value=\""+email+"\"></div>");
 		
 		$("#emailTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\">"+email+"</h4>");
 		$("#emailSelect").html("<label class=\"span6 control-label  text-success\"><h4>"+email+"</h4></label>");
 	
 	}
 	
 	/*get form[#destinationFrm] object*/
 	function regNotification(){
 		var uei = $("#destinationFrm input[name=uei]").val();						//0.이벤트
 		var noticeQueue = $("#destinationFrm input[name=noticeQueue]").val();		//1.이벤트 라벨
 		var name = $("#destiFrm input[name=name]").val();							//2.메세지명
 		var description = $("#destiFrm input[name=description]").val();				//3.설명
 		var subject = $("#destiFrm input[name=subject]").val();						//4.메일제목
 		var numericMessage = $("#destiFrm textarea[name=numericMessage]").val();	//5.요약메세지
 		var textMessage = $("#destiFrm textarea[name=textMessage]").val();			//6.rule
 		var destinationPath = $("#destiFrm select").val();							//7.목적지
 		var status = $("#destiFrm input[name=status]").val();						//8.상태
 		var rule = $("#destiFrm input[name=rule]").val();							//9.메세지
 	 
 		str = requestBodyStr(uei,noticeQueue,name,description,subject,numericMessage,textMessage,destinationPath,status,rule);
 		
 		regNotificationAjax(str);
 	 
 	}
 	
 	/*정보 수정 하기 위해 데이터 폼에 전송  */
 	function modiNoti(data){
 		
 		//console.log(data);
 		
 		textMessage=data["textMessage"];
 		subject=data["subject"];
 		uei=data["uei"];
 		name=data["name"];
 		destinationPath=data["destinationPath"];
 		numericMessage=data["numericMessage"];
 		rule=data["rule"];
 		status=data["status"];
 		description=data["description"];
 		noticeQueue=data["noticeQueue"];
 		
 		$("#StepOne").html("&nbsp;&nbsp;<span class=\"label label-important\">기존&nbsp;uei</span>&nbsp;&nbsp;&nbsp;"+noticeQueue);
 		$("#StepTwo").html("&nbsp;&nbsp;<span class=\"label label-important\">메세지&nbsp;수정</span>&nbsp;&nbsp;&nbsp;"+name);
 		document.getElementById('description').value = (description==null?'':description);
 		document.getElementById('textMessage').value = (textMessage==null?'':textMessage);
 		document.getElementById('subject').value = (subject==null?'':subject);
 		document.getElementById('name').value = (name==null?'':name);
 		document.getElementById('numericMessage').value = (numericMessage==null?'':numericMessage);
 		document.getElementById('rule').value = (rule==null?'':rule);
 		$("input[name=status]").filter('input[value='+status+']').attr("checked", true);
 		//document.getElementById('destinationPath').value = (destinationPath==null?'':destinationPath);
 		$("select[name=destinationPath] option[value="+destinationPath+"]").attr("selected",true);
 	
 	}
 	
 	/* Destination Path 목적지 삭제 */
 	function deletePath(PathName){
 		
 		//var destiName = ('${name}');
 		var option = confirm(" 삭제 하시겠습니까? ");
		
		if(option == true )
		{
			deletePathAjax(PathName);
	 		
		}else if(option == false ){
		 	
			alert("취소 되었습니다.");
			}
 	}
 	
 	/* Destination Path 목적지 수정 */
 	function modifyPath(PathName){
 		
 		$('#pathRegister').hide();
 		$('#pathRegi').hide();
 		$('#pathModify').show();
 		$('#pathReSet').show();
 		
 		$('#regPathName').hide();
 		$('#modiPathName').show();
 		
 		modifyPathAjax(PathName);
 		
 	}
 	
 	/*Destination Path 목적지 수정시 데이터 SET*/
 	function modifyPathstr(data){
 		
 		//console.log("data[name]");
 		target=data["target"];
 		
 		if(target.length > 1){
 			
 			for ( var i in  target){
 				userCommand = target[i]["command"];
 			}
 		
 		userName = target[0]["name"];
 		$("#userTr").children().remove();
 		$("#userTableDiv").children().remove();
 		$("#userSelect").children().remove();
		$("#userTr").html("<th class=\"span3 control-label text-success\">selected :"+userName+"</th>");
 		$("#userTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\">"+userName+"</h4>");
 		$("#userSelect").html("<label class=\"span5 control-label  text-success\"><h4>"+userName+"</h4></label>");
 		$("#PathFrm").find('[name=userName]:input').val(userName);
 		
 		groupName = target[1]["name"];
 		$("#groupTr").children().remove();
 		$("#groupTableDiv").children().remove();
 		$("#groupSelect").children().remove();
 		$("#groupTr").html("<th class=\"span3 control-label  text-success\">selected : "+groupName+"</th>");
		$("#groupTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\">"+groupName+"</h4>");
		$("#groupSelect").html("<label class=\"span5 control-label  text-success\"><h4>"+groupName+"</h4></label>");
		$("#PathFrm").find('[name=groupName]:input').val(groupName);
 		
 		email = target[3]["name"];
 		$("#emailTableDiv").children().remove();
 		$("#emailSelect").children().remove();
 		$("#emailTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\">"+email+"</h4>");
 		$("#emailSelect").html("<label class=\"span5 control-label  text-success\"><h4>"+email+"</h4></label>");
 		$("#emailTable").find('[name=email]:input').val(email);
 		
 		userAutoNotify = target[0]["autoNotify"];
 		$("select[name=userAutoNotify] option[value="+userAutoNotify+"]").attr("selected",true);
 		groupAutoNotify = target[1]["autoNotify"];
 		$("select[name=groupAutoNotify] option[value="+groupAutoNotify+"]").attr("selected",true);
 		emailAutoNotify = target[3]["autoNotify"];
 		$("select[name=emailAutoNotify] option[value="+emailAutoNotify+"]").attr("selected",true);
 		
 		userCommand =target[0]["command"];
 		$("select[name=userCommand] option[value="+userCommand+"]").attr("selected",true);
 		groupCommand = target[1]["command"];
 		$("select[name=groupCommand] option[value="+groupCommand+"]").attr("selected",true);
 		emailCommand = target[3]["command"];
 		$("select[name=emailCommand] option[value="+emailCommand+"]").attr("selected",true);
 		
 		name = data["name"];
 		$("#destiPathInfoFrm").find('[name=Name]:input').val(name);
 		
 		initialDelay = data["initialDelay"];
 		$("select[name=initialDelay] option[value="+initialDelay+"]").attr("selected",true);
 		
 		userInterval = target[0]["interval"];
 		$("select[name=userInterval] option[value="+userInterval+"]").attr("selected",true);
 		groupInterval = target[1]["interval"];
 		$("select[name=groupInterval] option[value="+groupInterval+"]").attr("selected",true);
 		emailInterval = target[3]["interval"];
 		$("select[name=emailInterval] option[value="+emailInterval+"]").attr("selected",true);
 		//roleInterval =target[2]["interval"];
 		//roleCommand =target[2]["command"];
 		//roleAutoNotify =target[2]["autoNotify"];
 		//roleName = target[2]["name"];
 		
 		}
 	}
 	
 	function PathModify(){
 		
 		$('#pathModify').hide();
 		$('#pathRegister').show();
 		$('#modiPathName').hide();
 		$('#regPathName').show();
 		
 		var userName = $("#PathFrm input[name=userName]").val();
 		var groupName = $("#PathFrm input[name=groupName]").val();
 		var email = $("#emailTable input[name=email]").val();
 		var name = $("#destiPathInfoFrm input[name=Name]").val();
 		var initialDelay = $("#destiPathInfoFrm select[name=initialDelay]").val();
 		var userInterval = $("#destiPathInfoFrm select[name=userInterval]").val();
 		var groupInterval = $("#destiPathInfoFrm select[name=groupInterval]").val();
 		var emailInterval = $("#destiPathInfoFrm select[name=emailInterval]").val();
 		var userAutoNotify = $("#desinationSettingFrm select[name=userAutoNotify]").val();
 		var userCommand = $("#desinationSettingFrm select[name=userCommand]").val();
 		var groupAutoNotify = $("#desinationSettingFrm select[name=groupAutoNotify]").val();
 		var groupCommand = $("#desinationSettingFrm select[name=groupCommand]").val();
 		var emailAutoNotify = $("#desinationSettingFrm select[name=emailAutoNotify]").val();
 		var emailCommand = $("#desinationSettingFrm select[name=emailCommand]").val();
 		
 		var roleName = ("");
 		var roleAutoNotify =("");
 		var roleCommand = ("");
 		var roleInterval =("");
 		
 		str	=	getPathName(name,initialDelay,userInterval,userName,userAutoNotify,userCommand,
 							groupInterval,groupName,groupAutoNotify,groupCommand,
 							roleInterval,roleName,roleAutoNotify,roleCommand,
 							emailInterval,emailCommand,emailAutoNotify,email); 
 		
 		console.log(str);
 		
 		modifySetPathAjax();
 		
 	}
 	
 	function PathReSet(){
 		
 		$("#PathFrm input:not([protect=true]),textarea,select").val("");
 		$("#emailTable input:not([protect=true]),textarea,select").val("");
 		$("#userTr").html("<th class=\"span3 control-label text-success\">selected :</th>");
 		$("#groupTr").html("<th class=\"span3 control-label  text-success\">selected :</th>");
 		$("#destiPathInfoFrm input:not([protect=true]),textarea,select").val("");
 		$("#userTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\"></h4>");
 		$("#userSelect").html("<label class=\"span5 control-label  text-success\"><h4></h4></label>");
 		$("#groupTr").html("<th class=\"span3 control-label  text-success\">selected :</th>");
		$("#groupTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\"></h4>");
		$("#groupSelect").html("<label class=\"span5 control-label  text-success\"><h4></h4></label>");
		$("#emailTableDiv").html("<h4 style= \"margin-top: 0px; margin-left: 12px;\" class=\"text-success\"></h4>");
 		$("#emailSelect").html("<label class=\"span5 control-label  text-success\"><h4></h4></label>");
 		$('#pathModify').hide();
 		
 		$('#pathRegister').show();
 		$('#modiPathName').hide();
 		$('#regPathName').show();
 	}
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		<form action="" id="destinationFrm" name="destinationFrm">	
			<input type="hidden" id="noticeQueue" name="noticeQueue" value="" />			
			<input type="hidden" id="uei" name="uei" value="" />							
		</form>
		<form action="" id="PathFrm" name="PathFrm">	
			<input type="hidden" id="userName" name="userName" value="" />	
			<input type="hidden" id="initialDelay" name="initialDelay" value="" />			
			<input type="hidden" id="groupName" name="groupName" value="" />
		</form>
		
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li>알림<span class="divider">/</span></li>
					<li><a href="/v1/admin/notimng/configureNotification.do">알림 설정</a> <span class="divider">/</span></li>
					<li class="active">알림 수정</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<!-- test -->
			<div class="accordion" id="accordion2">
			  <div class="accordion-group">
			    <div class="accordion-heading">
					<div class="row-fluid">
						<div class="span12"> 
						    <h3>
						      <a class="accordion-toggle muted" data-toggle="collapse" data-parent="" href="#eventStepOne" id=StepOne >
						        &nbsp;&nbsp;<span class="label label-important">기존&nbsp;uei</span>
						      </a>
						       <a class="accordion-toggle text-warning" data-toggle="collapse" data-parent="#accordion2" href="#eventStepOne" id=changeOne >
						        &nbsp;&nbsp;<span class="label label-important">변경&nbsp;uei</span>
						      </a>
					      	</h3>
						</div>
			      	</div>
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
								
									<!-- <tr>
										<th>even List</th>
									</tr> -->
								
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
			    <div class="row-fluid accordion-heading ">
					<div class="accordion-toggle muted span12" data-toggle="collapse" data-parent="#accordion2" href="#eventStepTwo" >
						<div class="row-fluid" class="span12"> 
				      		<div class="span12" >
					      		<h3>
							      <a class="accordion-toggle" data-toggle="collapse" data-parent="" style="margin-left: -20px;" href="#eventStepOne" id=StepTwo >
							        1단계&nbsp;&nbsp;<span class="label label-important">이벤트 선택</span></a>
						      	</h3>
					      	</div> 
						</div>
					</div>
			    </div>
			    <div id="eventStepTwo" class="accordion-body collapse">
			      <div class="accordion-inner">
					<div class="span12 well well-small">
						<form id="destiFrm" name = "destiFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted">메시지 명</label>
									<div class="span10 controls" >
										<input  type="text"   id="name"   name="name" class="span11"  readonly  placeholder=""> 
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
									<div class="span3 controls" >
										<select   id="destinationPath" name="destinationPath">
		               					
		               					</select>
									</div>
									<div class= "span2"><a type="button" class="btn" title="" href="#popDestinationPaths" data-toggle="modal">목적지관리</a></div>
									<div class="span4 controls">
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label muted" style ="font-size:16px;">status</label>
									<label class="radio span2 ">    
										<input type="radio" id="statusOn" name="status" value="on">on</label>
									<label class="radio span2 ">    
										<input type="radio" id="statusOff" name="status" value="off">off</label>
									<label class="span2 control-label muted">&nbsp;</label>
									<div class="span4 controls" >
										<input  type="hidden"   id="rule"   name="rule" class=""   placeholder="" value="(IPADDR IPLIKE *.*.*.*)"> 
									</div>
									
								</div>
							</div>
						</form>
						
					</div>
					
			      </div>
			     
			    </div>
			     
			  </div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<!-- <div class = "span2"></div>
					<div class="span4 controls">
						<a type="button" class="btn" title="" href="#popupRegMethod" data-toggle="modal">입력방법</a> 
					</div> -->
					<div class = "span10"></div>
					<div class="span2" style="width: 99px; margin-left: 71px;">
						<a type="button" class="btn btn-primary" title="" href="javascript:regNotification()">+ 공지등록</a>
					</div>
				</div>
			</div>
			<!-- test -->
		</div>
	</div>
			
	<!-- /container -->
	
	<!-- --------------------------목적지 설정popup--------------------- -->	
					
	
 <div id="popDestinationPaths" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">close</button>
		<h3 id="myModalLabel">DestinationPath</h3>
	</div>
	<div class="modal-body" >
		<div>
			<div class="accordion" id="accordion3">
			<!-- --------------------------목적지 설정 1단계 popup--------------------- -->
			  <div class="accordion-group">
			    <div class="accordion-heading">
			    	<h4>
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseOne">
			        	Destination&nbsp;Configration&nbsp;&nbsp;<span class="label label-info">Destination&nbsp;Modify</span>
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
							<a type="button" class="accordion-toggle btn btn-success" data-toggle="collapse" data-parent="#accordion3" href="#collapseThree">
								 Add&nbsp;New&nbsp;Path
						  </a></h4>
					</div>
			      </div>
			    </div>
			  </div>
			  			  <!-- --------------------------목적지 설정 2단계 popup--------------------- -->
			  <div class="accordion-group">
			    <div class="accordion-heading">
			    <h4>
			      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseThree">
			        Destination&nbsp;Target&nbsp;&nbsp;<span class="label label-info">Target Select</span>
			      </a></h4>
			    </div>
			    <div id="collapseThree" class="accordion-body collapse">
			      <div class="accordion-inner">
				    <div class="span5" style="margin-left: 5px;">
						<div>
							<table>
								<tr>
									<td>
										<div class="span3" style="margin-left: 0px; width: 233px; height:200px; overflow-y:auto;">
											<table class="table table-striped table-condensed" id="userTable">
													<tr id ="userTr">
														<th>user</th>
													</tr>
											</table>
										</div>
									</td>
									<td>
										<div class="span3" style="margin-left: 0px; width: 242px; height:200px; overflow-y:auto;">
											<table class="table table-striped table-condensed" id="groupTable">
													<tr id ="groupTr">
														<th>group</th>
													</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</div>
						<div>
							<div class="span5" style="">
								<table class="table table-condensed" id="emailTable">
									<tr>
										<th>e-mail</th>
									</tr>
									<tr>
										<td>
											<input type="text"  style="width: 477px; margin-top: -5px; margin-bottom: -3px; margin-left: -7px;" id="email"   name="email" class="span3" value=""  placeholder=" ex) OpenNms@google.com">
										</td>			
									</tr>
									<tr>
										<td>
											<h4>
											<a type="button" class="accordion-toggle btn btn-primary" data-toggle="collapse" data-parent="#accordion3" onclick="javascript:addEmail()">
						        				e-mail&nbsp;등록
						      				</a></h4>
										</td>			
									</tr>
								</table>
							</div>
						</div>
	      				<div class="row-fluid">
							<div class="span12">
								<div class="span10 controls" >
									<h4>
										<a type="button" class="accordion-toggle btn" data-toggle="collapse" data-parent="#accordion3" href="#collapseOne">
					        				BEFORE
					      				</a>
				      				</h4>
								</div>
									<!-- <div class="span3 controls" ></div> -->
								<div class="span2 controls" >
									<h4>
										<a type="button" class="accordion-toggle btn" data-toggle="collapse" data-parent="#accordion3" href="#collapseTwo">
					        				NEXT
					      				</a>
				      				</h4>
								</div>
							</div>
						</div>
					</div>
			      </div>
			    </div>
			  </div>
			  <!-- --------------------------목적지 설정 3단계 popup--------------------- -->
			  <div class="accordion-group">
			    <div class="accordion-heading">
				     <h4>
					     <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseTwo">
					      Destination&nbsp;Name&nbsp;&nbsp;<span class="label label-info">Destination info</span>
					     </a>
				     </h4>
			    </div>
			    <div id="collapseTwo" class="accordion-body collapse">
			      <div class="accordion-inner">
					<div class="span5" style="margin-left: 5px;">
						<form id="destiPathInfoFrm" name = "destiPathInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span3 control-label">Path Name</label>
									<div class="span9 controls" id="regPathName">
										<input type="text"   id="Name"   name="Name" class="span12"  value="" placeholder=""> 
									</div>
									<div class="span9 controls" style="display:none" id="modiPathName">
										<input type="text"   id="Name"   name="Name" class="span12"  value="" placeholder="" readonly> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span3 control-label">Initial Delay  :</label>
									<div class="span9 controls" >
										<select id="initialDelay" name="initialDelay">
											<option value="0s">0s</option>
											<option value="1s">1s</option>
											<option value="2s">2s</option>
											<option value="5s">5s</option>
											<option value="10s">10s</option>
											<option value="15s">15s</option>
											<option value="30s">30s</option>
											<option value="0m">0m</option>
											<option value="1m">1m</option>
											<option value="2m">2m</option>
											<option value="5m">5m</option>
											<option value="10m">10m</option>
											<option value="15m">15m</option>
											<option value="30m">30m</option>
											<option value="0h">0h</option>
											<option value="1h">1h</option>
											<option value="2h">2h</option>
											<option value="3h">3h</option>
											<option value="6h">6h</option>
											<option value="12h">12h</option>
											<option value="1d">1d</option>
										</select>
									</div>
								</div>
							</div>
							
							<!-- <div class="row-fluid">
								<div class="span12" id="emailTableDiv">
									<label class="span3 control-label">email Targets :</label>
									<div class="span9 controls" ><h4 style= "margin-top: 0px;"></h4>
										<input type="hidden" id=""  name="" class="span12"   placeholder="">
									</div>
								</div>
								<div class="span12" id="emailTableDiv">
									<div class="span9 controls" ><h4 style= "margin-top: 0px;"></h4></div>
								</div>
							</div> -->
							
							<!-- test -->
							
							<div class="row-fluid span12" style="width: 509px;">
								<div class="span3">
									<label class="control-label">User Targets  :</label>
								</div>
								<div class="span6" id="userTableDiv" style ="margin-left: -9px; width: 260px;">
									
								</div>
								<select style =" width: 79px; margin-left: 27px;" id="userInterval" name="userInterval">
											<option value="0s">0s</option>
											<option value="1s">1s</option>
											<option value="2s">2s</option>
											<option value="5s">5s</option>
											<option value="10s">10s</option>
											<option value="15s">15s</option>
											<option value="30s">30s</option>
											<option value="0m">0m</option>
											<option value="1m">1m</option>
											<option value="2m">2m</option>
											<option value="5m">5m</option>
											<option value="10m">10m</option>
											<option value="15m">15m</option>
											<option value="30m">30m</option>
											<option value="0h">0h</option>
											<option value="1h">1h</option>
											<option value="2h">2h</option>
											<option value="3h">3h</option>
											<option value="6h">6h</option>
											<option value="12h">12h</option>
											<option value="1d">1d</option>
								</select>
							</div>
							<div class="row-fluid span12" style="width: 509px;">
								<div class="span3">
									<label class="control-label">Group Targets  :</label>
								</div>
								<div class="span6" id="groupTableDiv" style ="margin-left: -9px; width: 260px;">
									
								</div>
								<select style =" width: 79px; margin-left: 27px;" id="groupInterval" name="groupInterval">
											<option value="0s">0s</option>
											<option value="1s">1s</option>
											<option value="2s">2s</option>
											<option value="5s">5s</option>
											<option value="10s">10s</option>
											<option value="15s">15s</option>
											<option value="30s">30s</option>
											<option value="0m">0m</option>
											<option value="1m">1m</option>
											<option value="2m">2m</option>
											<option value="5m">5m</option>
											<option value="10m">10m</option>
											<option value="15m">15m</option>
											<option value="30m">30m</option>
											<option value="0h">0h</option>
											<option value="1h">1h</option>
											<option value="2h">2h</option>
											<option value="3h">3h</option>
											<option value="6h">6h</option>
											<option value="12h">12h</option>
											<option value="1d">1d</option>
								</select>
							</div>
							<div class="row-fluid span12" style="width: 509px;">
								<div class="span3">
									<label class="control-label">e-mail Targets  :</label>
								</div>
								<div class="span6" id="emailTableDiv" style ="margin-left: -9px; width: 260px;">
									
								</div>
								<select style =" width: 79px; margin-left: 27px;" id="emailInterval" name="emailInterval">
											<option value="0s">0s</option>
											<option value="1s">1s</option>
											<option value="2s">2s</option>
											<option value="5s">5s</option>
											<option value="10s">10s</option>
											<option value="15s">15s</option>
											<option value="30s">30s</option>
											<option value="0m">0m</option>
											<option value="1m">1m</option>
											<option value="2m">2m</option>
											<option value="5m">5m</option>
											<option value="10m">10m</option>
											<option value="15m">15m</option>
											<option value="30m">30m</option>
											<option value="0h">0h</option>
											<option value="1h">1h</option>
											<option value="2h">2h</option>
											<option value="3h">3h</option>
											<option value="6h">6h</option>
											<option value="12h">12h</option>
											<option value="1d">1d</option>
								</select>
							</div>
							
							<!-- test -->
							
						</form>
						<div class="row-fluid">
							<div class="span12">
								<div class="span10 controls" >
									<h4><a type="button" class="accordion-toggle btn" data-toggle="collapse" data-parent="#accordion3" href="#collapseThree">
				        				BEFORE
				      				</a></h4>
								</div>
									<!-- <div class="span3 controls" ></div> -->
								<div class="span2 controls" >
									<h4><a type="button" class="accordion-toggle btn" data-toggle="collapse" data-parent="#accordion3" href="#collapseFour">
			        					NEXT
			      					</a></h4>
								</div>
							</div>
						</div>
					</div>
			      </div>
			    </div>
			  </div>
 <!-- --------------------------목적지 설정 4단계 popup--------------------- -->

			<div class="accordion-group">
			    <div class="accordion-heading">
			   <h4>
			      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseFour">
			        Destination&nbsp;Setting&nbsp;&nbsp;<span class="label label-info">Setting</span>
			      </a></h4>
			    </div>
			    <div id="collapseFour" class="accordion-body collapse">
			      <div class="accordion-inner">
				    <div class="span5" style="margin-left: 5px;">
						<form id="desinationSettingFrm" name = "desinationSettingFrm" method="post">
							<div class="row-fluid span12" style="width: 509px;">
								<div class="span6" id ="userSelect">
									<label class="span6 control-label">USER</label>
								</div>
								<div class="span4">
									<select style ="margin-left: -17px; width: 179px;" id="userCommand" name="userCommand">
										<option value="javaPagerEmail">javaPagerEmail</option>
										<option value="javaEmail">javaEmail</option>
										<option value="textPage">textPage</option>
										<option value="numericPage">numericPage</option>
										<option value="xmppMessage">xmppMessage</option>
										<option value="xmppGroupMessage">xmppGroupMessage</option>
										<option value="ircCat">ircCat</option>
										<option value="callWorkPhone">callWorkPhone</option>
										<option value="callHomePhone">callHomePhone</option>
										<option value="microblogUpdate">microblogUpdate</option>
										<option value="microblogReply">microblogReply</option>
										<option value="microblogDM">microblogDM</option>
									</select>
								</div>
								<select style =" width: 65px; margin-left: 14px;" id="userAutoNotify" name="userAutoNotify">
									<option value="on">on</option>
									<option value="off">off</option>
									<option value="auto">auto</option>
								</select>
							</div>
							<div class="row-fluid span12" style="width: 509px;">
								<div class="span6" id ="groupSelect">
									<label class="span5 control-label">GROUP</label>
								</div>
								<div class="span4">
									<select style ="margin-left: -17px; width: 179px;" id="groupCommand" name="groupCommand">
										<option value="javaPagerEmail">javaPagerEmail</option>
										<option value="javaEmail">javaEmail</option>
										<option value="textPage">textPage</option>
										<option value="numericPage">numericPage</option>
										<option value="xmppMessage">xmppMessage</option>
										<option value="xmppGroupMessage">xmppGroupMessage</option>
										<option value="ircCat">ircCat</option>
										<option value="callWorkPhone">callWorkPhone</option>
										<option value="callHomePhone">callHomePhone</option>
										<option value="microblogUpdate">microblogUpdate</option>
										<option value="microblogReply">microblogReply</option>
										<option value="microblogDM">microblogDM</option>
									</select>
								</div>
								<select style =" width: 65px; margin-left: 14px;" id="groupAutoNotify" name="groupAutoNotify">
									<option value="on">on</option>
									<option value="off">off</option>
									<option value="auto">auto</option>
								</select>
							</div>
							<!-- <div class="row-fluid span12" style="width: 509px;">
								<div class="span5" id ="roleSelect">
									<label class="span5 control-label">ROLE</label>
								</div>
								<div class="span5">
									<select style ="margin-left: -17px; width: 221px;" id="roleCommand" name="roleCommand">
										<option value="javaPagerEmail">javaPagerEmail</option>
										<option value="javaEmail">javaEmail</option>
										<option value="textPage">textPage</option>
										<option value="numericPage">numericPage</option>
										<option value="xmppMessage">xmppMessage</option>
										<option value="xmppGroupMessage">xmppGroupMessage</option>
										<option value="ircCat">ircCat</option>
										<option value="callWorkPhone">callWorkPhone</option>
										<option value="callHomePhone">callHomePhone</option>
										<option value="microblogUpdate">microblogUpdate</option>
										<option value="microblogReply">microblogReply</option>
										<option value="microblogDM">microblogDM</option>
									</select>
								</div>
								<select style =" width: 65px; margin-left: 14px;" id="roleAutoNotify" name="roleAutoNotify">
									<option>on</option>
									<option>off</option>
									<option>auto</option>
								</select>
							</div> -->
							<div class="row-fluid span12" style="width: 509px;">
								<div class="span6" id ="emailSelect">
									<label class="span6 control-label">E-MAIL</label>
								</div>
								<div class="span4">
									<select style ="margin-left: -17px; width: 179px;" id="emailCommand" name="emailCommand">
										<option value="javaPagerEmail">javaPagerEmail</option>
										<option value="javaEmail">javaEmail</option>
										<option value="textPage">textPage</option>
										<option value="numericPage">numericPage</option>
										<option value="xmppMessage">xmppMessage</option>
										<option value="xmppGroupMessage">xmppGroupMessage</option>
										<option value="ircCat">ircCat</option>
										<option value="callWorkPhone">callWorkPhone</option>
										<option value="callHomePhone">callHomePhone</option>
										<option value="microblogUpdate">microblogUpdate</option>
										<option value="microblogReply">microblogReply</option>
										<option value="microblogDM">microblogDM</option>
									</select>
								</div>
								<select style =" width: 65px; margin-left: 14px;" id="emailAutoNotify" name="emailAutoNotify">
									<option value="on">on</option>
									<option value="off">off</option>
									<option value="auto">auto</option>
								</select>
							</div>	
						</form>
						<div class="row-fluid">
							<div class="span12">
								<div class="span4 controls" >
									<h4>
										<a type="button" class="accordion-toggle btn" data-toggle="collapse" data-parent="#accordion3" href="#collapseTwo">
				        				BEFORE
				      					</a>
				      				</h4>
								</div>
								<!-- <div class="span3 controls" ></div> -->
								
								<div class="span4 controls" id= "pathReSet" >
									<h4>
										<a type="button" class="accordion-toggle btn btn-inverse" data-toggle="collapse" data-parent="#accordion3" onclick="javascript:PathReSet()" href="#collapseOne">
				        					PATH RESET
				      					</a>
				      				</h4>
								</div>
								<div class="span4 controls" id= "pathRegister" >
									<h4 style="width: 158px;">
										<a type="button" class="accordion-toggle btn btn-primary" data-toggle="collapse" data-parent="#accordion3" onclick="javascript:pathRegister()" href="#collapseOne">
				        					PATH REGISTER
				      					</a>
				      				</h4>
								</div>
								<div class="span4 controls" id= "pathModify" style="display:none" >
									<h4>
										<a type="button" class="accordion-toggle btn btn-warning" data-toggle="collapse" data-parent="#accordion3" onclick="javascript:PathModify()" href="#collapseOne">
				        					PATH MODIFY
				      					</a>
				      				</h4>
								</div>
							</div>
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
		<h3 id="myModalLabel">수정&nbsp;방법</h3>
	</div>
	<div class="modal-body" style="max-height: 800px;">
		<p>알아서 수정 잘해봥!
		
		
		</p>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div> 
				
	
</body>
</html>
