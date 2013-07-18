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
<script src="<c:url value="/resources/js/category.js" />"></script>
<%-- <script  src="<c:url value="/dhtmlx/dhtmlx.js"/>"></script>
<link rel="STYLESHEET" type="text/css" href="<c:url value="/dhtmlx/dhtmlx.css"/>">
<script src="<c:url value="/dhtmlx/dhtmlxCalendar/codebase/dhtmlxcalendar.js" />"></script> --%>


 <script src="<c:url value="/resources/js/common/calendar/ui/ui.core.js" />"></script>
<script src="<c:url value="/resources/js/common/calendar/ui/ui.datepicker.js" />"></script>
<script src="<c:url value="/resources/js/common/calendar/ui/ui.datepicker-ko.js" />"></script>
<link rel="STYLESHEET" type="text/css" href="<c:url value="/resources/js/common/calendar/ui/demos.css"/>">
<link rel="STYLESHEET" type="text/css" href="<c:url value="/resources/js/common/calendar/themes/base/ui.all.css"/>">
<script type="text/javascript">
var cal1 = null;
$(document).ready(function() {
/* 	cal1 = new dhtmlXCalendarObject(["dateinstalled","leaseexpires","maintcontractexpires"]); */	
	/* ASSETS 데이터를 검색해서 가져온다 */
	getAssetInfo(assetInfo, "${nodeId}");
	
});
	
	
$(function() {
	$('#dateinstalled').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '2009:2020'
	});	
	$('#leaseexpires').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '2009:2020'
	});	
	$('#maintcontractexpires').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '2009:2020'
	});	
});
$(function() {
	$('#leaseexpires').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '2009:2020'
	});	
});
$(function() {
	$('#maintcontractexpires').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '2009:2020'
			

	});/* $('#ui-datepicker-div').css('top','500px');	 */
}

);

	
//callback 함수 jsonObj를 이용 파싱 후 append
function assetInfo(jsonObj) {
	console.log("----------assetInfo----------");
	console.log(jsonObj);
	/* Configuration Categories */
	$('#assetsInfoFrm input[name=displaycategory]').val(jsonObj.AssetInfo[0].displaycategory);
	$('#assetsInfoFrm input[name=notifycategory]').val(jsonObj.AssetInfo[0].notifycategory);
	$('#assetsInfoFrm input[name=pollercategory]').val(jsonObj.AssetInfo[0].pollercategory);
	$('#assetsInfoFrm input[name=thresholdcategory]').val(jsonObj.AssetInfo[0].thresholdcategory);
	
	/* Identification */
	$('#assetsInfoFrm input[name=description]').val(jsonObj.AssetInfo[0].description);
	$("select[name=category] option[value="+jsonObj.AssetInfo[0].category+"]").attr("selected",true);
	$('#assetsInfoFrm input[name=manufacturer]').val(jsonObj.AssetInfo[0].manufacturer);
	$('#assetsInfoFrm input[name=modelnumber]').val(jsonObj.AssetInfo[0].modelnumber);
	$('#assetsInfoFrm input[name=serialnumber]').val(jsonObj.AssetInfo[0].serialnumber);
	$('#assetsInfoFrm input[name=assetnumber]').val(jsonObj.AssetInfo[0].assetnumber);
	$('#assetsInfoFrm input[name=dateinstalled]').val(jsonObj.AssetInfo[0].dateinstalled);
	$('#assetsInfoFrm input[name=operatingsystem]').val(jsonObj.AssetInfo[0].operatingsystem);
	
	/* Location */
	$('#assetsInfoFrm input[name=state]').val(jsonObj.AssetInfo[0].state);
	$('#assetsInfoFrm input[name=region]').val(jsonObj.AssetInfo[0].region);
	$('#assetsInfoFrm input[name=address1]').val(jsonObj.AssetInfo[0].address1);
	$('#assetsInfoFrm input[name=address2]').val(jsonObj.AssetInfo[0].address2);
	$('#assetsInfoFrm input[name=city]').val(jsonObj.AssetInfo[0].city);
	$('#assetsInfoFrm input[name=zip]').val(jsonObj.AssetInfo[0].zip);
	$('#assetsInfoFrm input[name=division]').val(jsonObj.AssetInfo[0].division);
	$('#assetsInfoFrm input[name=department]').val(jsonObj.AssetInfo[0].department);
	$('#assetsInfoFrm input[name=building]').val(jsonObj.AssetInfo[0].building);
	$('#assetsInfoFrm input[name=floor]').val(jsonObj.AssetInfo[0].floor);
	$('#assetsInfoFrm input[name=room]').val(jsonObj.AssetInfo[0].room);
	$('#assetsInfoFrm input[name=rack]').val(jsonObj.AssetInfo[0].rack);
	$('#assetsInfoFrm input[name=rackunitheight]').val(jsonObj.AssetInfo[0].rackunitheight);
	$('#assetsInfoFrm input[name=slot]').val(jsonObj.AssetInfo[0].slot);
	$('#assetsInfoFrm input[name=port]').val(jsonObj.AssetInfo[0].port);
	$('#assetsInfoFrm input[name=circuitid]').val(jsonObj.AssetInfo[0].circuitid);
	$('#assetsInfoFrm input[name=admin]').val(jsonObj.AssetInfo[0].admin);
	
	/* Vendor */
	$('#assetsInfoFrm input[name=vendor]').val(jsonObj.AssetInfo[0].vendor);
	$('#assetsInfoFrm input[name=vendorphone]').val(jsonObj.AssetInfo[0].vendorphone);
	$('#assetsInfoFrm input[name=vendorfax]').val(jsonObj.AssetInfo[0].vendorfax);
	$('#assetsInfoFrm input[name=lease]').val(jsonObj.AssetInfo[0].lease);
	$('#assetsInfoFrm input[name=leaseexpires]').val(jsonObj.AssetInfo[0].leaseexpires);
	$('#assetsInfoFrm input[name=vendorassetnumber]').val(jsonObj.AssetInfo[0].vendorassetnumber);
	$('#assetsInfoFrm input[name=maintcontractexpires]').val(jsonObj.AssetInfo[0].maintcontractexpires);
	$('#assetsInfoFrm input[name=maintcontract]').val(jsonObj.AssetInfo[0].maintcontract);
	$('#assetsInfoFrm input[name=supportphone]').val(jsonObj.AssetInfo[0].supportphone);
	
	/* Authentication */
	$('#assetsInfoFrm input[name=username]').val(jsonObj.AssetInfo[0].username);
	$('#assetsInfoFrm input[name=password]').val(jsonObj.AssetInfo[0].password);
	$('#assetsInfoFrm input[name=enable]').val(jsonObj.AssetInfo[0].enable);
	$("select[name=connection] option[value="+jsonObj.AssetInfo[0].connection+"]").attr("selected",true);
	$("select[name=autoenable] option[value="+jsonObj.AssetInfo[0].autoenable+"]").attr("selected",true);
	$('#assetsInfoFrm input[name=snmpcommunity]').val(jsonObj.AssetInfo[0].snmpcommunity);
	
	/* Hardware */
	$('#assetsInfoFrm input[name=cpu]').val(jsonObj.AssetInfo[0].cpu);
	$('#assetsInfoFrm input[name=ram]').val(jsonObj.AssetInfo[0].ram);
	$('#assetsInfoFrm input[name=additionalhardware]').val(jsonObj.AssetInfo[0].additionalhardware);
	$('#assetsInfoFrm input[name=numpowersupplies]').val(jsonObj.AssetInfo[0].numpowersupplies);
	$('#assetsInfoFrm input[name=inputpower]').val(jsonObj.AssetInfo[0].inputpower);
	$('#assetsInfoFrm input[name=storagectrl]').val(jsonObj.AssetInfo[0].storagectrl);
	$('#assetsInfoFrm input[name=hdd1]').val(jsonObj.AssetInfo[0].hdd1);
	
	$('#assetsInfoFrm input[name=hdd2]').val(jsonObj.AssetInfo[0].hdd2);
	$('#assetsInfoFrm input[name=hdd3]').val(jsonObj.AssetInfo[0].hdd3);
	$('#assetsInfoFrm input[name=hdd4]').val(jsonObj.AssetInfo[0].hdd4);
	$('#assetsInfoFrm input[name=hdd5]').val(jsonObj.AssetInfo[0].hdd5);
	$('#assetsInfoFrm input[name=hdd6]').val(jsonObj.AssetInfo[0].hdd6);
	
	/*Comments*/
	$('#assetsInfoFrm input[name=comment]').val(jsonObj.AssetInfo[0].comment);
	//document.getElementById('아이디입력').value = (data.etcProgramInfo[0].etcTitle==null?'':data.etcProgramInfo[0].etcTitle);
	
}
	
	
function modifyAssets(){

	var frm = document.getElementById("assetsInfoFrm");
	
	frm.action = "/v1/assets/regAssets.do";
   
	frm.submit();
	
}
	
	

	
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		<form id="userInfoFrm" name = "userInfoFrm">
				<input type="hidden" name="regrId" value="<%= userId %>"  protect="true" />
				<input type="hidden" name="modrId" value="<%= userId %>"  protect="true" />
		</form>
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="/v1/assets.do">ASSETS</a><span class="divider">/</span></li>
					<li class="active">Search&nbsp;ASSETS</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li>${nodeLabel}&nbsp;<span class="divider">/</span></li>
					<li>node ID&nbsp;:&nbsp;${nodeId}</li>
				</ul>
			</div>
		</div>	
		
		<div class="row-fluid">
				<!-- test -->
			<form id="assetsInfoFrm" name = "assetsInfoFrm">
				<input type="hidden" name="nodeid" value="${nodeId}"  protect="true" />
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
									<div class="row-fluid">
										<div class="span12">
											<label class="span2 control-label">Display Category</label>
											<div class="span4 controls">
												<input  type="text"   id="displaycategory"   name="displaycategory"  placeholder="Display Category"> 
											</div>
											<label class="span2 control-label">Notification Category</label>
											<div class="span4 controls">
												<input type="text"   id="notifycategory"   name="notifycategory"  placeholder="Notification Category"> 
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label class="span2 control-label">Poller Category</label>
											<div class="span4 controls">
												<input type="text"   id="pollercategory"   name="pollercategory"  placeholder="Poller Category"> 
											</div>
											<label class="span2 control-label">Threshold Category</label>
											<div class="span4 controls">
												<input type="text"   id="thresholdcategory"   name="thresholdcategory"  placeholder="Threshold Category"> 
											</div>
										</div>
									</div>
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
									<div class="row-fluid">
										<div class="span12">
											<label class="span2 control-label">Description</label>
											<div class="span4 controls">
												<input type="text"   id="description"   name="description"  placeholder="Description"> 
											</div>
											<label class="span2 control-label">Category</label>
											<div class="span4 controls">
											<!-- <input type="text"   id="category"   name="category"  placeholder="Category">  -->
											<select class="span9"    id="category"   name="category">
												<option value=""></option> 
												<option value="Unspecified">Unspecified</option>
												<option value="Infrastructure">Infrastructure</option>
												<option value="Server">Server</option>
												<option value="Desktop">Desktop</option>
												<option value="Loptop">Loptop</option>
												<option value="Printer">Printer</option>
												<option value="Telephony">Telephony</option>
												<option value="Other">Other</option>
											</select>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label class="span2 control-label">Manufacturer</label>
											<div class="span4 controls">
												<input type="text"   id="manufacturer"   name="manufacturer"  placeholder="Manufacturer"> 
											</div>
											<label class="span2 control-label">Model Number</label>
											<div class="span4 controls">
												<input type="text"   id="modelnumber"   name="modelnumber"  placeholder="Model Number"> 
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label class="span2 control-label">Serial Number</label>
											<div class="span4 controls">
												<input type="text"   id="serialnumber"   name="serialnumber"  placeholder="Serial Number"> 
											</div>
											<label class="span2 control-label">Asset Number</label>
											<div class="span4 controls">
												<input type="text"   id="assetnumber"   name="assetnumber"  placeholder="Asset Number"> 
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label class="span2 control-label">Date Installed</label>
											<div class="span4 controls">
												<input  class="date" type="text"   id="dateinstalled"   name="dateinstalled"  placeholder="Date Installed" value=""> 
											</div>
											<label class="span2 control-label">Operating System</label>
											<div class="span4 controls">
												<input type="text"   id="operatingsystem"   name="operatingsystem"  placeholder="Operating System"> 
											</div>
										</div>
									</div>
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
									<div class="row-fluid">
										<div class="span12">
											<label class="span2 control-label">State</label>
											<div class="span4 controls">
												<input type="text"   id="state"   name="state"  placeholder="State">  
											</div>
											<label class="span2 control-label">Region</label>
											<div class="span4 controls">
												<input type="text"   id="region"   name="region"  placeholder="Region">   
											</div>
										</div>
									</div>
								<div class="row-fluid">
									<div class="span12">
										<label class="span2 control-label">Address 1</label>
										<div class="span4 controls">
											<input type="text"   id="address1"   name="address1"  placeholder="Address 1">   
										</div>
										<label class="span2 control-label">Address 2</label>
										<div class="span4 controls">
											<input type="text"   id="address2"   name="address2"  placeholder="Address 2"> 
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span12">
										<label class="span2 control-label">City</label>
										<div class="span4 controls">
											<input type="text"   id="city"   name="city"  placeholder="City"> 
										</div>
										<label class="span2 control-label">ZIP</label>
										<div class="span4 controls">
											<input type="text"   id="zip"   name="zip"  placeholder="ZIP"> 
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span12">
										<label class="span2 control-label">Division</label>
										<div class="span4 controls">
											<input type="text"   id="division"   name="division"  placeholder="Division"> 
										</div>
										<label class="span2 control-label">Department</label>
										<div class="span4 controls">
											<input type="text"   id="department"   name="department"  placeholder="Department"> 
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span12">
										<label class="span2 control-label">Building</label>
										<div class="span4 controls">
											<input type="text"   id="building"   name="building"  placeholder="Building"> 
										</div>
										<label class="span2 control-label">Floor</label>
										<div class="span4 controls">
											<input type="text"   id="floor"   name="floor"  placeholder="Floor"> 
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span12">
										<label class="span2 control-label">Room</label>
										<div class="span4 controls">
											<input type="text"   id="room"   name="room"  placeholder="Room"> 
										</div>
										<label class="span2 control-label">Rack</label>
										<div class="span4 controls">
											<input type="text"   id="rack"   name="rack"  placeholder="Rack">
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span12">
										<label class="span2 control-label">Rack unit height</label>
										<div class="span4 controls">
											<input type="text"   id="rackunitheight"   name="rackunitheight"  placeholder="Rack unit height"> 
										</div>
										<label class="span2 control-label">Slot</label>
										<div class="span4 controls">
											<input type="text"   id="slot"   name="slot"  placeholder="Slot">
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span12">
										<label class="span2 control-label">Port</label>
										<div class="span4 controls">
											<input type="text"   id="port"   name="port"  placeholder="Port"> 
										</div>
										<label class="span2 control-label">Circuit ID</label>
										<div class="span4 controls">
											<input type="text"   id="circuitid"   name="circuitid"  placeholder="Circuit ID">
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span12">
										<label class="span2 control-label">Admin</label>
										<div class="span4 controls">
											<input type="text"   id="admin"   name="admin"  placeholder="Admin"> 
										</div>
										<label class="span2 control-label"></label>
										<div class="span4 controls">
										</div>
									</div>
								</div>
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
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Name</label>
												<div class="span4 controls">
													<input type="text"   id="vendor"   name="vendor"  placeholder="Name"> 
												</div>
												<label class="span2 control-label">Phone</label>
												<div class="span4 controls">
													<input type="text"   id="vendorphone"   name="vendorphone"  placeholder="Phone"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Fax</label>
												<div class="span4 controls">
													<input type="text"   id="vendorfax"   name="vendorfax"  placeholder="Fax"> 
												</div>
												<label class="span2 control-label">Lease</label>
												<div class="span4 controls">
													<input type="text"   id="lease"   name="lease"  placeholder="Lease"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Lease Expires</label>
												<div class="span4 controls">
													<input type="text"   id="leaseexpires"   name="leaseexpires"  placeholder="Lease Expires"> 
												</div>
												<label class="span2 control-label">Vendor Asset</label>
												<div class="span4 controls">
													<input type="text"   id="vendorassetnumber"   name="vendorassetnumber"  placeholder="Vendor Asset"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Maint Contract Number</label>
												<div class="span4 controls">
													<input type="text"   id="maintcontract"   name="maintcontract"  placeholder="Maint Contract Number"> 
												</div>
												<label class="span2 control-label">Contract Expires</label>
												<div class="span4 controls">
													<input type="text"   id="maintcontractexpires"   name="maintcontractexpires"  placeholder="Contract Expires"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Maint Phone</label>
												<div class="span4 controls">
													<input type="text"   id="supportphone"   name="supportphone"  placeholder="Maint Phone"> 
												</div>
												<label class="span2 control-label"></label>
												<div class="span4 controls">
												</div>
											</div>
										</div>
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
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Username</label>
												<div class="span4 controls">
													<input type="text"   id="username"   name="username"  placeholder="Username"> 
												</div>
												<label class="span2 control-label">Password</label>
												<div class="span4 controls">
													<input type="text"   id="password"   name="password"  placeholder="Password"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Enable Password</label>
												<div class="span4 controls">
													<input type="text"   id="enable"   name="enable"  placeholder="Enable Password"> 
												</div>
												<label class="span2 control-label">Connection</label>
												<div class="span4 controls">
													<select class="span9"     id="connection"   name="connection" > 
														<option value=""></option>
														<option value="telent">telent</option>
														<option value="ssh">ssh</option>
														<option value="rsh">rsh</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">AutoEnable</label>
												<div class="span4 controls">
											<!-- <input type="text"   id="autoenable"   name="autoenable"  placeholder="AutoEnable"> -->
													<select class="span9"     id="autoenable"   name="autoenable" > 
														<option value=""></option>
														<option value="A">A</option>
													</select> 
												</div>
												<label class="span2 control-label">SNMP community</label>
												<div class="span4 controls">
													<input type="text"   id="snmpcommunity"   name="snmpcommunity"  placeholder="SNMP community">		 
												</div>
											</div>
										</div>
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
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">CPU</label>
												<div class="span4 controls">
													<input type="text"   id="cpu"   name="cpu"  placeholder="CPU"> 
												</div>
												<label class="span2 control-label">RAM</label>
												<div class="span4 controls">
													<input type="text"   id="ram"   name="ram"  placeholder="RAM"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Additional hardware</label>
												<div class="span4 controls">
													<input type="text"   id="additionalhardware"   name="additionalhardware"  placeholder="Additional hardware"> 
												</div>
												<label class="span2 control-label">Number of power supplies</label>
												<div class="span4 controls">
													<input type="text"   id="numpowersupplies"   name="numpowersupplies"  placeholder="Number of power supplies"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">Inputpower</label>
												<div class="span4 controls">
													<input type="text"   id="inputpower"   name="inputpower"  placeholder="Inputpower">
												</div>
												<label class="span2 control-label">Storage Controller</label>
												<div class="span4 controls">
													<input type="text"   id="storagectrl"   name="storagectrl"  placeholder="Storage Controller"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">HDD 1</label>
												<div class="span4 controls">
													<input type="text"   id="hdd1"   name="hdd1"  placeholder="HDD 1" value=""> 
												</div>
												<label class="span2 control-label">HDD 2</label>
												<div class="span4 controls">
													<input type="text"   id="hdd2"   name="hdd2"  placeholder="HDD 2"> 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">HDD 3</label>
												<div class="span4 controls">
													<input type="text"   id="hdd3"   name="hdd3"  placeholder="HDD 3">
												</div>
												<label class="span2 control-label">HDD 4</label>
												<div class="span4 controls">
													<input type="text"   id="hdd4"   name="hdd4"  placeholder="HDD 4">			 
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span12">
												<label class="span2 control-label">HDD 5</label>
												<div class="span4 controls">
													<input type="text"   id="hdd5"   name="hdd5"  placeholder="HDD 5">
												</div>
												<label class="span2 control-label">HDD 6</label>
												<div class="span4 controls">
												<!-- <input type="text"    id=""   name=""  placeholder="Telephone PIN" > -->
												<input type="text"   id="hdd6"   name="hdd6"  placeholder="HDD 6">
												</div>
											</div>
										</div>
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
										<div class="row-fluid">
											<div class="span12">
												<div class="span12 controls">
													<textarea rows="9"    id="Comment"   name="Comment" class="span11"  style="margin-left: 41px;"  placeholder="Comments"></textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			<div class="row-fluid">
				<div class="span12">
					<div class = "span10"></div>
					<div class="span2" style="width: 99px; margin-left: 71px;">
						<a type="button" class="btn btn-primary" title="" href="javascript:modifyAssets()">등록</a>
					</div>
				</div>
			</div>
			<!-- test -->
		</div>
	</div>
	<!-- /container -->
</body>
</html>
