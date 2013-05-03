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
	
	
	modifyNoti( modiNoti ,"${name}");
	
	
	/* GET list of the initial agent(destination) accept to notification */
	getPathList(getPathsName);
	
	/* GETConfirm kind of registered all event */
	 getAllEvent(getEventSelect); 
	
});

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
	//noticeQueue=data["noticeQueue"];
	noticeQueue=("uei.opennms.org/internal/capsd/discPause");
	//alert(destinationPath);
	//alert(uei);
	//alert(noticeQueue);
	
	document.getElementById('description').value = (description==null?'':description);
	document.getElementById('textMessage').value = (textMessage==null?'':textMessage);
	document.getElementById('subject').value = (subject==null?'':subject);
	document.getElementById('name').value = (name==null?'':name);
	document.getElementById('numericMessage').value = (numericMessage==null?'':numericMessage);
	document.getElementById('rule').value = (rule==null?'':rule);
	//document.getElementById('destinationPath').value = (destinationPath==null?'':destinationPath);
	$("select[name=destinationPath] option[value="+destinationPath+"]").attr("selected",true);
	$("input[name=status]").filter('input[value='+status+']').attr("checked", "checked");
	document.getElementById('uei').value = (noticeQueue==null?'':noticeQueue);
	//$("select[name=uei] option[value="+noticeQueue+"]").attr("selected",true);
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
 	
 	
 	function getEventSelect(jsonObj){
 		//console.log(jsonObj);
 		
 		var str = getEventSelectJsonObj(jsonObj);
		$("#uei").append(str);
 	}
 	
function regNotification(){
 		
 		
 		var uei = $("#destiFrm select[name=uei]").val();							//7.uei+eventRabel
 		
 		var object = uei.split(',');
 		for(var i = 0 ; i < object.length; i++)
 			{
 			
 			}
 			
	 		var name = $("#destiFrm input[name=name]").val();							//2.메세지명
	 		var description = $("#destiFrm input[name=description]").val();				//3.설명
	 		var subject = $("#destiFrm input[name=subject]").val();						//4.메일제목
	 		var numericMessage = $("#destiFrm textarea[name=numericMessage]").val();	//5.요약메세지
	 		var textMessage = $("#destiFrm textarea[name=textMessage]").val();			//6.rule
	 		var destinationPath = $("#destiFrm select[name=destinationPath]").val();	//7.목적지
	 		var status = $("#destiFrm input[name=status]").val();						//8.상태
	 		var rule = $("#destiFrm input[name=rule]").val();							//9.메세지
 		
 			var uei= object[0];
 			var eventRabel= object[1];
 			
 			//alert("--------------uei----------------------"+uei);// uei
 			//alert("--------------eventRabel---------------"+eventRabel); //eventRabel
 		
 		
 		
 		
 		/* alert("--------------name----------------------"+name);
 		alert("--------------description----------------------"+description);
 		alert("--------------subject----------------------"+subject);
 		alert("--------------numericMessage----------------------"+numericMessage);
 		alert("--------------textMessage----------------------"+textMessage);
 		alert("--------------destinationPath----------------------"+destinationPath);
 		alert("--------------status----------------------"+status);
 		alert("--------------rule----------------------"+rule);  */
 		
 		
}
 	
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li><a href="/v1/admin/setting.do">사용자 설정</a> <span class="divider">/</span></li>
					<li><a href="/v1/admin/setting/configureNotification.do">공지 설정</a> <span class="divider">/</span></li>
					<li class="active">공지추가${name}</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
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
									<label class="span2 control-label muted">uei</label>
									<div class="span10 controls" >
										<select  id="uei" name="uei" class="span11">
			               					
		               					</select>
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
								</div>
								<div class = "span2">
								<!-- test -->
								<%-- <table>
									<tr>
										<td>
											<c:choose>
												<c:when test="${announceList.annNews == 'Y'}">
													<td align="center">&nbsp; ${announceList.annTitle}</td>
												</c:when>
											</c:choose>
										</td>
									</tr>
								</table> --%>
								<!-- test -->
								</div>
								<div class="span4">
									<a type="button" class="btn btn-primary" title="" href="javascript:regNotification()">+ 공지등록</a>
								</div>
							</div>
						</div>
					</div>
		</div>
	</div>
			
	<!-- /container -->
	
	

				
	
</body>
</html>
