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
	<jsp:param value="사용자등록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>

<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/assets.js" />"></script>
<script type="text/javascript">
	
$(document).ready(function() {
	
	/* ASSETS 데이터를 검색해서 가져온다 */
	getAssetInfo(assetInfo, "${nodeId}");
	
});
	
//callback 함수 jsonObj를 이용 파싱 후 append
function assetInfo(jsonObj) {
	
	console.log(jsonObj);
	
	/* alert(jsonObj.AssetInfo[0].category);
	alert(jsonObj.AssetInfo[0].id);
	alert(jsonObj.AssetInfo[0].userlastmodified);
	alert(jsonObj.AssetInfo[0].nodeid);
	alert(jsonObj.AssetInfo[0].nodeLabel);
	alert(jsonObj.AssetInfo[0].lastmodifieddate); */
	//document.getElementById('아이디입력').value = (data.etcProgramInfo[0].etcTitle==null?'':data.etcProgramInfo[0].etcTitle);
	
}
	
	

	
	

	
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="/v1/admin/userMng.do">사용자관리</a> <span class="divider">/</span></li>
					<li class="active">사용자 등록</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<form id="userInfoFrm" name = "userInfoFrm">
					<input type="hidden" name="regrId" value="<%= userId %>"  protect="true" />
					<input type="hidden" name="modrId" value="<%= userId %>"  protect="true" />
		</form>
		
		<div class="row-fluid">
		
	
		
		
				<!-- test -->
			<div class="accordion" id="accordion2">
				
				
			<div class="accordion-group">
					<div class="accordion-heading" style="height: 32px;">
						<h5>
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"  href="#eventStepOne" id= StepOne>
							Configuration&nbsp;Categories</a>
						</h5>
			    	</div>
			    <div id="eventStepOne" class="accordion-body collapse in">
			      <div class="accordion-inner">
			        <div class="span12 well well-small">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Display Category</label>
									<div class="span4 controls">
										<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
									</div>
									<label class="span2 control-label">Notification Category</label>
									<div class="span4 controls">
										<input type="text"   id="xmppAddress"   name="xmppAddress"  placeholder="XMPP Address"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Poller Category</label>
									<div class="span4 controls">
										<input type="text"   id="numericPage"   name="numericPage"  placeholder="Numeric Service"> 
									</div>
									<label class="span2 control-label">Threshold Category</label>
									<div class="span4 controls">
										<input type="text"   id="textPage"   name="textPage"  placeholder="Text Service"> 
									</div>
								</div>
							</div>
						</form>
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-group">
					<div class="accordion-heading" style="height: 32px;">
						<h5>
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"  href="#eventStepTwo" id= StepTwo>
							Identification</a>
						</h5>
			    	</div>
			    <div id="eventStepTwo" class="accordion-body collapse in">
			      <div class="accordion-inner">
			        <div class="span12 well well-small">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Description</label>
									<div class="span4 controls">
										<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
									</div>
									<label class="span2 control-label">Category</label>
									<div class="span4 controls">
										<input type="text"   id="xmppAddress"   name="xmppAddress"  placeholder="XMPP Address"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Manufacturer</label>
									<div class="span4 controls">
										<input type="text"   id="numericPage"   name="numericPage"  placeholder="Numeric Service"> 
									</div>
									<label class="span2 control-label">Model Number</label>
									<div class="span4 controls">
										<input type="text"   id="textPage"   name="textPage"  placeholder="Text Service"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Serial Number</label>
									<div class="span4 controls">
										<input type="text"   id="workPhone"   name="workPhone"  placeholder="Work Phone"> 
									</div>
									<label class="span2 control-label">Asset Number</label>
									<div class="span4 controls">
										<input type="text"   id="homePhone"   name="homePhone"  placeholder="Home Phone"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Date Installed</label>
									<div class="span4 controls">
										<input type="text"   id="pagerEmail"   name="pagerEmail"  placeholder="Pager Email"> 
									</div>
									<label class="span2 control-label">Operating System</label>
									<div class="span4 controls">
										<input type="text"   id="microblog"   name="microblog"  placeholder="Microblog Username"> 
									</div>
								</div>
							</div>
						</form>
					</div>
			      </div>
			    </div>
			  </div>
			<div class="accordion-group">
					<div class="accordion-heading" style="height: 32px;">
						<h5>
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#eventStepThree" id=StepThree >
							Location</a>
						</h5>
			    	</div>
			    <div id="eventStepThree" class="accordion-body collapse in">
			      <div class="accordion-inner">
			        <div class="span12 well well-small">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">State</label>
									<div class="span4 controls">
										<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
									</div>
									<label class="span2 control-label">Region</label>
									<div class="span4 controls">
										<input type="text"   id="xmppAddress"   name="xmppAddress"  placeholder="XMPP Address"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Address 1</label>
									<div class="span4 controls">
										<input type="text"   id="numericPage"   name="numericPage"  placeholder="Numeric Service"> 
									</div>
									<label class="span2 control-label">Address 2</label>
									<div class="span4 controls">
										<input type="text"   id="textPage"   name="textPage"  placeholder="Text Service"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">City</label>
									<div class="span4 controls">
										<input type="text"   id="workPhone"   name="workPhone"  placeholder="Work Phone"> 
									</div>
									<label class="span2 control-label">ZIP</label>
									<div class="span4 controls">
										<input type="text"   id="homePhone"   name="homePhone"  placeholder="Home Phone"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Division</label>
									<div class="span4 controls">
										<input type="text"   id="pagerEmail"   name="pagerEmail"  placeholder="Pager Email"> 
									</div>
									<label class="span2 control-label">Department</label>
									<div class="span4 controls">
										<input type="text"   id="microblog"   name="microblog"  placeholder="Microblog Username"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Building</label>
									<div class="span4 controls">
										<input type="text"   id="numericPin"   name="numericPin"  placeholder="Numeric PIN"> 
									</div>
									<label class="span2 control-label">Floor</label>
									<div class="span4 controls">
										<input type="text"   id="textPin"   name="textPin"  placeholder="Text PIN"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Room</label>
									<div class="span4 controls">
										<input type="text"   id="mobilePhone"   name="mobilePhone"  placeholder="Mobile Phone"> 
									</div>
									<label class="span2 control-label">Rack</label>
									<div class="span4 controls">
										<!-- <input type="text"    id=""   name=""  placeholder="Telephone PIN" > -->
										<input type="text"    id=""   name=""  placeholder="Telephone PIN"  value="">
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Rack unit height</label>
									<div class="span4 controls">
										<input type="text"   id="mobilePhone"   name="mobilePhone"  placeholder="Mobile Phone"> 
									</div>
									<label class="span2 control-label">Slot</label>
									<div class="span4 controls">
										<!-- <input type="text"    id=""   name=""  placeholder="Telephone PIN" > -->
										<input type="text"    id=""   name=""  placeholder="Telephone PIN"  value="">
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Port</label>
									<div class="span4 controls">
										<input type="text"   id="mobilePhone"   name="mobilePhone"  placeholder="Mobile Phone"> 
									</div>
									<label class="span2 control-label">Circuit ID</label>
									<div class="span4 controls">
										<!-- <input type="text"    id=""   name=""  placeholder="Telephone PIN" > -->
										<input type="text"    id=""   name=""  placeholder="Telephone PIN"  value="">
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Admin</label>
									<div class="span4 controls">
										<input type="text"   id="mobilePhone"   name="mobilePhone"  placeholder="Mobile Phone"> 
									</div>
									<label class="span2 control-label"></label>
									<div class="span4 controls">
										
										
									</div>
								</div>
							</div>
						</form>
					</div>
			      </div>
			    </div>
			  </div>
			  	<div class="accordion-group">
					<div class="accordion-heading" style="height: 32px;">
						<h5>
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#eventStepFour" id=StepFour >
							Vendor</a>
						</h5>
			    	</div>
			    <div id="eventStepFour" class="accordion-body collapse in">
			      <div class="accordion-inner">
			        <div class="span12 well well-small">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Name</label>
									<div class="span4 controls">
										<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
									</div>
									<label class="span2 control-label">Phone</label>
									<div class="span4 controls">
										<input type="text"   id="xmppAddress"   name="xmppAddress"  placeholder="XMPP Address"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Fax</label>
									<div class="span4 controls">
										<input type="text"   id="numericPage"   name="numericPage"  placeholder="Numeric Service"> 
									</div>
									<label class="span2 control-label">Lease</label>
									<div class="span4 controls">
										<input type="text"   id="textPage"   name="textPage"  placeholder="Text Service"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Lease Expires</label>
									<div class="span4 controls">
										<input type="text"   id="workPhone"   name="workPhone"  placeholder="Work Phone"> 
									</div>
									<label class="span2 control-label">Vendor Asset</label>
									<div class="span4 controls">
										<input type="text"   id="homePhone"   name="homePhone"  placeholder="Home Phone"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Maint Contract Number</label>
									<div class="span4 controls">
										<input type="text"   id="pagerEmail"   name="pagerEmail"  placeholder="Pager Email"> 
									</div>
									<label class="span2 control-label">Contract Expires</label>
									<div class="span4 controls">
										<input type="text"   id="microblog"   name="microblog"  placeholder="Microblog Username"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Maint Phone</label>
									<div class="span4 controls">
										<input type="text"   id="numericPin"   name="numericPin"  placeholder="Numeric PIN"> 
									</div>
									<label class="span2 control-label"></label>
									<div class="span4 controls">
										
									</div>
								</div>
							</div>
						</form>
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-group">
					<div class="accordion-heading" style="height: 32px;">
						<h5>
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#eventStepFive" id=StepFive >
							Authentication</a>
						</h5>
			    	</div>
			    <div id="eventStepFive" class="accordion-body collapse in">
			      <div class="accordion-inner">
			        <div class="span12 well well-small">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Username</label>
									<div class="span4 controls">
										<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
									</div>
									<label class="span2 control-label">Password</label>
									<div class="span4 controls">
										<input type="text"   id="xmppAddress"   name="xmppAddress"  placeholder="XMPP Address"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Enable Password</label>
									<div class="span4 controls">
										<input type="text"   id="numericPage"   name="numericPage"  placeholder="Numeric Service"> 
									</div>
									<label class="span2 control-label">Connection</label>
									<div class="span4 controls">
										<input type="text"   id="textPage"   name="textPage"  placeholder="Text Service"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">AutoEnable</label>
									<div class="span4 controls">
										<input type="text"   id="workPhone"   name="workPhone"  placeholder="Work Phone"> 
									</div>
									<label class="span2 control-label">SNMP community</label>
									<div class="span4 controls">
										<input type="text"   id="homePhone"   name="homePhone"  placeholder="Home Phone"> 
									</div>
								</div>
							</div>
						</form>
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-group">
					<div class="accordion-heading" style="height: 32px;">
						<h5>
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#eventStepSix" id=StepSix >
							Hardware</a>
						</h5>
			    	</div>
			    <div id="eventStepSix" class="accordion-body collapse in">
			      <div class="accordion-inner">
			        <div class="span12 well well-small">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">CPU</label>
									<div class="span4 controls">
										<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
									</div>
									<label class="span2 control-label">RAM</label>
									<div class="span4 controls">
										<input type="text"   id="xmppAddress"   name="xmppAddress"  placeholder="XMPP Address"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Additional hardware</label>
									<div class="span4 controls">
										<input type="text"   id="numericPage"   name="numericPage"  placeholder="Numeric Service"> 
									</div>
									<label class="span2 control-label">Number of power supplies</label>
									<div class="span4 controls">
										<input type="text"   id="textPage"   name="textPage"  placeholder="Text Service"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Inputpower</label>
									<div class="span4 controls">
										<input type="text"   id="workPhone"   name="workPhone"  placeholder="Work Phone"> 
									</div>
									<label class="span2 control-label">Storage Controller</label>
									<div class="span4 controls">
										<input type="text"   id="homePhone"   name="homePhone"  placeholder="Home Phone"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">HDD 1</label>
									<div class="span4 controls">
										<input type="text"   id="pagerEmail"   name="pagerEmail"  placeholder="Pager Email"> 
									</div>
									<label class="span2 control-label">HDD 2</label>
									<div class="span4 controls">
										<input type="text"   id="microblog"   name="microblog"  placeholder="Microblog Username"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">HDD 3</label>
									<div class="span4 controls">
										<input type="text"   id="numericPin"   name="numericPin"  placeholder="Numeric PIN"> 
									</div>
									<label class="span2 control-label">HDD 4</label>
									<div class="span4 controls">
										<input type="text"   id="textPin"   name="textPin"  placeholder="Text PIN"> 
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">HDD 5</label>
									<div class="span4 controls">
										<input type="text"   id="mobilePhone"   name="mobilePhone"  placeholder="Mobile Phone"> 
									</div>
									<label class="span2 control-label">HDD 6</label>
									<div class="span4 controls">
										<!-- <input type="text"    id=""   name=""  placeholder="Telephone PIN" > -->
										<input type="text"    id=""   name=""  placeholder="Telephone PIN"  value="">
									</div>
								</div>
							</div>
						</form>
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-group">
					<div class="accordion-heading" style="height: 32px;">
						<h5>
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#eventStepSeven" id=StepSeven >
							Comments</a>
						</h5>
			    	</div>
			    <div id="eventStepSeven" class="accordion-body collapse in">
			      <div class="accordion-inner">
			        <div class="span12 well well-small">
						<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
							<div class="row-fluid">
								<div class="span12">
									<label class="span2 control-label">Comment</label>
									<div class="span4 controls">
										<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
									</div>
									<label class="span2 control-label"></label>
									<div class="span4 controls">
									</div>
								</div>
							</div>
						</form>
						<hr>
					</div>
			      </div>
			    </div>
			  </div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class = "span10"></div>
					<div class="span2" style="width: 99px; margin-left: 71px;">
						<a type="button" class="btn btn-primary" title="" href="javascript:regNotification()">+ 공지등록</a>
					</div>
				</div>
			</div>
			<!-- test -->
		
			<hr>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
