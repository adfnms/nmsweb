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
<script type="text/javascript">
	
	
	function checkUserId(userId){
		
		$.ajax({
			type:'post',
		 	url:'<c:url value="/admin/userMng/checkUserId.do"/>',
			data:'user-Id='+userId,
			dataType:'json',
			error:function(res){
				
				alert("서비스 실패");
					
	        },
	        success: function(res){
	        	if(res.result == false){
	        		alert(res.message);
	        		
		   		}else{
 			//------------성공 내용 추가-------------
		   		}
			}		
		});
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
						<form id="userInfoFrm" name = "userInfoFrm" action="<c:url value="/assets/searchAssets.do" />">
							<input type="hidden" name="regrId" value="<%= userId %>"  protect="true" />
							<input type="hidden" name="modrId" value="<%= userId %>"  protect="true" />
							<table class="table table-striped table-hover table-condensed table-stacked" id="recodTable" >
								<div class="row-fluid">
									<div class="span12" style="margin-top: 15px; margin-bottom: -20px;">
										<label class="text-center span5 control-label">ASSETS in Category</label>
										<div class="span4 controls" style ="margin-right: 16px;">
											<select class="span12"    id="category"   name="category"> 
												<option value="Unspecified">Unspecified</option>
												<option value="Infrastructure">Infrastructure</option>
												<option value="Server">Server</option>
												<option value="desktop">desktop</option>
												<option value="Loptop">Loptop</option>
												<option value="printer">printer</option>
												<option value="Telephony">Telephony</option>
												<option value="Other">Other</option>
											</select>
										</div>
										<div class="span2 controls">
											<!-- <a type="button" class="btn" title="" href="javascript:searchAssets()">Search</a> -->
											<input type="submit" class="btn" title="Quick Search" value="Search">
											<%-- <a type="button" class="btn" title="" href="<c:url value="/assets/searchAssets.do" />">Search</a> --%>
										</div>
									</div>
									<div class="span12">
									</div>
									<div class="span12">
										<a><label class="text-center span5 control-label">All nodes with asset info</label></a>
									</div>
								</div>
							</table>
						</form>
					</div>
				</div>
			</div>
			</div>
			<!-- tree -->
			 <div class="span6 well ">
			 <div class="span12 well well-small">
		 		<div class="span12 text-center">
					<h4 id="nodeLabel">Assets in category, select the desired category, and click [Search] to retrieve a list of all assets associated with that category. And for a complete list of nodes, whether or not they have associated asset numbers, simply click on the List all nodes with asset information link.</h4>
				</div>
			</div>
		      </div>  
			
			<!-- tree -->
			
			
			</div>
		
		
	</div>
	<!-- /container -->
</body>
</html>
