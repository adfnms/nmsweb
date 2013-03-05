<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="사용자등록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/js/userMng.js" />"></script>
<script type="text/javascript">
	
	// Reg User Info
	function regMember(){
		
		
		//Get userInfo 
		var userId = $("#userInfoFrm input[name=user-id]").val();
		
		checkUserId(userId);
		
		var fullName = $("#userInfoFrm input[name=full-name]").val();
		var userComments = $("#userInfoFrm input[name=user-comments]").val();
		var password = $("#userInfoFrm input[name=password]").val();
		
		
	
		//Post Json Info String url method
		var str = getJSONStrToUser(userId, fullName, userComments, password);
	
		$.ajax({
	
			type : 'post',
			url : 'http://localhost:8080/v1/users',
			contentType : 'application/json',
			data : str,
			error : function() {
				alert('유저 리스트 가져오기 서비스 실패');
			},
			success : function(data) {
				//alert(data);
			}
		});
	}
	
	
	function checkUserId(userId){
		
		
		alert("-------------checkUserId------------"+userId);
		
		$.ajax({
			type:'post',
			
		 	//url:'/v1/admin/userMng/checkUserId.do', 
			//대체 url:'<c:url value="/admin/userMng/checkUserId.do" />',
			
		 	url:'<c:url value="/admin/userMng/checkUserId.do" />',
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
			<div class="span9">
			</div>

			<jsp:include page="/include/sideBar.jsp" />
		</div>
		
		<div class="row-fluid">
			<div class="span9">
				<h2 class="muted">개인정보</h2>
			</div>
			<div class="span9">	
			<form id="testfrm" name = "memberInfoFrm">
				<input type="hidden"   id="test"   name="test"   value=""> 
			</FORM>
			<form id="userInfoFrm" name = "userInfoFrm">
				<div class="span6 control-group error">
					<fieldset>
						<label  class="muted" for="inputWarning">*사용자 ID</label>
						<input type="text"   id="user-id"   name="user-id"   placeholder="사용자 ID" value=""> 
						<label  class="muted" for="inputWarning">*이름</label>
						<input type="text"    id="full-name"   name="full-name"  placeholder="이름" value=""> 
						<label  class="muted"  for="inputWarning">Telephone PIN</label>
						<input type="text"    id=""   name=""  placeholder="Telephone PIN"  value=""> 
					</fieldset>
				</div>
				<div class="span6  control-group error">
					<fieldset>
						<label  class="muted"  for="inputWarning">*비밀번호</label>
						<input type="text"   id="password"   name="password"  placeholder="비밀번호"  value=""> 
						<label  class="muted" for="inputWarning">*소개</label>
						<input type="text"   id="user-comments"   name="user-comments"  placeholder="소개"  value=""> 
						<label>&nbsp;</label>
						<input type="hidden"> 
					</fieldset>
				</div>
				</form>
			</div>
			
		</div>
		<div class="row-fluid">
			<div class="span9">
				<h2 class="muted">알림정보</h2>
			</div>
			<div class="span9">	
			<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
				<div class="span6 control-group info">
					<fieldset>
						<label  class="muted" for="inputInfo">이메일</label>
						<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
						<label  class="muted" for="inputInfo">XMPP Address</label>
						<input type="text"   id="xmppAddress"   name="xmppAddress"  placeholder="XMPP Address"> 
						<label  class="muted" for="inputInfo">Numeric Service</label>
						<input type="text"   id="numericPage"   name="numericPage"  placeholder="Numeric Service"> 
						<label  class="muted" for="inputInfo">Text Service</label>
						<input type="text"   id="textPage"   name="textPage"  placeholder="Text Service"> 
						<label  class="muted" for="inputInfo">Work Phone</label>
						<input type="text"   id="workPhone"   name="workPhone"  placeholder="Work Phone"> 
						<label  class="muted" for="inputInfo">Home Phone</label>
						<input type="text"   id="homePhone"   name="homePhone"  placeholder="Home Phone"> 
					</fieldset>
				</div>
				<div class="span6 control-group info">
					<fieldset>
						<label  class="muted" for="inputInfo">Pager Email</label>
						<input type="text"   id="pagerEmail"   name="pagerEmail"  placeholder="Pager Email"> 
						<label  class="muted" for="inputInfo">Microblog Username</label>
						<input type="text"   id="microblog"   name="microblog"  placeholder="Microblog Username"> 
						<label  class="muted" for="inputInfo">Numeric PIN</label>
						<input type="text"   id="numericPin"   name="numericPin"  placeholder="Numeric PIN"> 
						<label  class="muted" for="inputInfo">Text PIN</label>
						<input type="text"   id="textPin"   name="textPin"  placeholder="Text PIN"> 
						<label  class="muted" for="inputInfo">Mobile Phone</label>
						<input type="text"   id="mobilePhone"   name="mobilePhone"  placeholder="Mobile Phone"> 
						<label>&nbsp;</label>
						<label>&nbsp;</label>
							<div>
								<table>
									<colgroup><col width="20%"/><col width="60%"/><col width="20%"/></colgroup>
									<tr>
										<td></td>
										<td><a class="btn btn-large btn-primary" href="javascript:regMember()">사용자 등록</a></td>
										<td><td>
									</tr>
								</table>
							</div>
					</fieldset>
				</div>
				</form>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
