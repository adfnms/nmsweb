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

<script src="<c:url value="/resources/js/users.js" />"></script>
<script type="text/javascript">
	
	// Reg User Info
	function regMember(){
		
		//Get userInfo 
		var userId = $("#userInfoFrm input[name=user-id]").val();
		
		checkUserId(userId);
		
		var fullName = $("#userInfoFrm input[name=full-name]").val();
		var userComments = $("#userInfoFrm input[name=user-comments]").val();
		var password = $("#userInfoFrm input[name=password]").val();
		
		//alert("------userId-----"+userId);
		//alert("------fullName-----"+fullName);
		//alert("------userComments-----"+userComments);
		//alert("------password-----"+password);
	
		//Post Json Info String url method
		var str = getJSONStrToUser(userId, fullName, userComments, password);
	
		//alert(str);
		
		$.ajax({
	
			type : 'post',
			url : 'http://localhost:8080/v1/users',
			contentType : 'application/json',
			data : str,
			error : function() {
				alert('유저 리스트 가져오기 서비스 실패');
			},
			success : function(data) {
				alert("등록되었습니다.");
			}
		});
	}
	
	
	function checkUserId(userId){
		
		
		//alert("-------------checkUserId------------"+userId);
		
		$.ajax({
			type:'post',
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
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li class="active">사용자 등록</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span9 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>
							개인정보 <span class="label label-info">필수 입력 사항</span>
						</h4>
					</div>
				</div>
				<form id="userInfoFrm" name = "userInfoFrm">
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input type="text">
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text"   id="user-id"   name="user-id"   placeholder="사용자 ID" value=""> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input type="text"    id="full-name"   name="full-name"  placeholder="이름" value=""> 
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text"   id="password"   name="password"  placeholder="비밀번호"  value=""> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input type="text"   id="user-comments"   name="user-comments"  placeholder="소개"  value=""> 
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text"    id=""   name=""  placeholder="Telephone PIN"  value=""> 
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row-fluid">
		
			<div class="span9 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>
							알림정보 <span class="label label-alert">선택 입력 사항</span>
						</h4>
					</div>
				</div>
				<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input  type="text"   id="email"   name="email"  placeholder="이메일"> 
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text"   id="xmppAddress"   name="xmppAddress"  placeholder="XMPP Address"> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input type="text"   id="numericPage"   name="numericPage"  placeholder="Numeric Service"> 
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text"   id="textPage"   name="textPage"  placeholder="Text Service"> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input type="text"   id="workPhone"   name="workPhone"  placeholder="Work Phone"> 
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text"   id="homePhone"   name="homePhone"  placeholder="Home Phone"> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input type="text"   id="pagerEmail"   name="pagerEmail"  placeholder="Pager Email"> 
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text"   id="microblog"   name="microblog"  placeholder="Microblog Username"> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input type="text"   id="numericPin"   name="numericPin"  placeholder="Numeric PIN"> 
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text"   id="textPin"   name="textPin"  placeholder="Text PIN"> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">IP 주소</label>
							<div class="span4 controls">
								<input type="text"   id="mobilePhone"   name="mobilePhone"  placeholder="Mobile Phone"> 
							</div>
							<label class="span2 control-label">노드명</label>
							<div class="span4 controls">
								<input type="text">
							</div>
						</div>
					</div>
				</form>
			
			<hr>
		
		</div>
		<div class="row-fluid">
				<div class="span12">
					<div class="span8"></div>
					<div class="span1">
						<a type="button" class="btn btn-primary" title="" href="javascript:regMember()">저장</a>
					</div>
				</div>
			</div>
		
		
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
