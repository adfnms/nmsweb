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
	<jsp:param value="ASSETS" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>

<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/assets.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	


	/*	Asset 카테고리 검색 */
function searchCategory(){
	
	var category = $("#userInfoFrm select[name=category]").val();
	getSearchAssetsList(searchAssetsList, category);
	
}
//getSearchAssetsList callback 함수
function searchAssetsList(jsonObj) {
	var str = "<tr><th>ASSETS</th><th>ASSET LINK</th><th>NODE LINK</th></tr>";
	str += assetsListStr(jsonObj);
	 $('#assetsListTable').empty();
	 $('#assetsListTable').append(str);
}
/* 필드리스트 검색  */
function searchField(){
	
	var fieldText = $("#fieldFrm input[name=field]").val();
	var fieldSelect = $("#fieldFrm select[name=fieldSelect]").val();
	getFieldSearchAssets(FieldListStr,fieldSelect,fieldText);
}	
//getFieldSearchAssets callback 함수
function FieldListStr(jsonObj) {
	
	var str = "<tr><th>ASSETS</th><th>ASSET LINK</th><th>NODE LINK</th></tr>";
	str += FieldStr(jsonObj);
	 $('#assetsListTable').empty();
	 $('#assetsListTable').append(str);
}	
</script>
</head>

<body>
<form id="searchFieldFrm" name = "searchFieldFrm">
	<input type="hidden" name="fieldName" value=""  protect="true" />
	<input type="hidden" name="fieldValue" value=""  protect="true" />
</form>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="/v1/index.do">Home</a> <span class="divider">/</span></li>
					<li><a href="/v1/assets.do">ASSETS</a></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		
		<div class="row-fluid">	
		<div class=" span6 well">
		<div class="row-fluid">
					<div class="span12 text-center">
						<h4>
							ASSETS in Category &nbsp;<span class="label label-info">카테고리&nbsp;별&nbsp;검색</span>
						</h4>
					</div>
				</div>
			<div class="span12 well well-small" style=" height:120px;margin-left: 0px;">
				
				<div class="row-fluid" >
					<div class="span12 text-center" id="recodDiv">
						<form id="userInfoFrm" name = "userInfoFrm" <%-- action="<c:url value="/assets/searchAssets.do" />" --%>>
							<input type="hidden" name="regrId" value="<%= userId %>"  protect="true" />
							<input type="hidden" name="modrId" value="<%= userId %>"  protect="true" />
								<div class="row-fluid">
									<div class="span12" style="margin-top: 15px; margin-bottom: -20px;">
										<label class="text-center span5 control-label">ASSETS in Category</label>
										<div class="span4 controls" style ="margin-right: 13px;">
											<select class="span12"    id="category"   name="category"> 
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
										<div class="span2 controls">
											<!-- <input type="submit" class="btn" title="Quick Search" value="Search"> -->
											<a type="button" class="btn" title="" href="javascript:searchCategory()">Search</a>
										</div>
									</div>
								</div>
						</form>
						<form id="fieldFrm" name = "fieldFrm">
							<div class="row-fluid" style ="margin-left: 15px;">
								<div class="span12" style="margin-top: 5px; margin-bottom: -14px;">
									<!-- <label class="text-center span1 control-label">field</label> -->
									<div class="span4 controls" style ="margin-right: 22px;">
										<select class="span12"    id="fieldSelect"   name="fieldSelect"> 
											<option value="">Field Search</option>
											<option value="address1">Address 1</option>
											<option value="address2">Address 2</option>
											<option value="assetnumber">Asset Number</option>
											<option value="building">Building</option>
											<option value="circuitid">Circuit ID</option>
											<option value="city">City</option>
											<option value="comment">Comments</option>
											<option value="dateinstalled">Date Installed</option>
											<option value="department">Department</option>
											<option value="description">Description</option>
											<option value="displaycategory">Display Category</option>
											<option value="division">Division</option>
											<option value="floor">Floor</option>
											<option value="lease">Lease</option>
											<option value="leaseexpires">Lease Expires</option>
											<option value="maintcontractexpires">Contract Expires</option>
											<option value="maintcontract">Maint Contract Number</option>
											<option value="supportphone">Maint Phone</option>
											<option value="manufacturer">Manufacturer</option>
											<option value="modelnumber">Model Number</option>
											<option value="notifycategory">Notification Category</option>
											<option value="operatingsystem">Operating System</option>
											<option value="port">Port</option>
											<option value="pollercategory">Poller Category</option>
											<option value="rack">Rack</option>
											<option value="region">Region</option>
											<option value="room">Room</option>
											<option value="serialnumberther">Serial Number</option>
											<option value="slot">Slot</option>
											<option value="state">State</option>
											<option value="thresholdcategory">Threshold Category</option>
											<option value="userlastmodified">User Last Modified</option>
											<option value="vendor">Vendor</option>
											<option value="vendorassetnumber">Vendor Asset Number</option>
											<option value="vendorfax">Vendor Fax</option>
											<option value="vendorphone">Vendor Phone</option>
											<option value="zip">ZIP Code</option>
											<option value="username">Username</option>
											<option value="password">Password</option>
											<option value="enable">Enable Password</option>
											<option value="connection">Connection type</option>
											<option value="AutoEnable">Auto Enable</option>
											<option value="cpu">CPU</option>
											<option value="ram">RAM</option>
											<option value="storagectrl">Storage Controller</option>
											<option value="hdd1">HDD 1</option>
											<option value="hdd1">HDD 2</option>
											<option value="hdd1">HDD 3</option>
											<option value="hdd1">HDD 4</option>
											<option value="hdd1">HDD 5</option>
											<option value="hdd1">HDD 6</option>
											<option value="numpowersupplies">Number of power supplies</option>
											<option value="inputpower">Inputpower</option>
											<option value="additionalhardware">Additional hardware</option>
											<option value="admin">Admin</option>
											<option value="snmpcommunity">SNMP community</option>
											<option value="rackunitheight">Rack unit height</option>
										</select>
									</div>
									<div class="span4 controls">
										<input class="span12" type="text"  id="field" name="field" value="">
									</div>
									<div class="span2 controls" style="margin-left: 24px;">
										<a type="button" class="btn" title="" href="javascript:searchField()">Search</a>
									</div>
								</div>
								<div class="span12" style ="margin-top: 33px; margin-left: -13px;">
									<a href="<c:url value="/assets/searchAssets.do" />"><label class="text-center span5 control-label">All nodes with asset info</label></a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			</div>
			<!-- tree -->
			 <div class="span6 well ">
			 <div class="span12 well well-small">
		 		<div class="span12">
					<h4 id="nodeLabel">Assets in category는&nbsp;원하는&nbsp;카테고리를&nbsp;선택하고&nbsp;&nbsp;[Search]을&nbsp;클릭합니다.</h4>
					<h4 id="nodeLabel">또는&nbsp;해당&nbsp;범주와&nbsp;관련된&nbsp;모든&nbsp;Assets의&nbsp;목록을&nbsp;검색하려면&nbsp;Field Search의&nbsp;원하&nbsp;항목을&nbsp;선택하여&nbsp;[Search]을&nbsp;클릭합니다.</h4>
				
				</div>
			</div>
		      </div>  
			
			<!-- tree -->
			
			
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<div class="span12">
							<table class="table table-striped" id="assetsListTable">
								<tr>
									<th>ASSETS</th>
									<th>ASSET LINK</th>
									<th>NODE LINK</th>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		
	</div>
	<!-- /container -->
</body>
</html>
