<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/include/header.jsp">
		<jsp:param value="로그인" name="title"/>
		<jsp:param value="N" name="styleFlag"/>	
	</jsp:include>
    
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
 <script type="text/javascript">
 
 function memberLogIn(){
 	var str = '<c:url value="/login.do"/>';
 	var url = str.split('.')[0] + '.do'; 
	var data = $("#form-logIn").serialize();

	$.ajax({
			type:'post',
			url: url,
			data: data,
			dataType:'json',
			error:function(res){
				console.log(res);				
				alert("서비스 실패.");
	        },
	        success: function(res){
	        	if(res.result == false){
	        		alert(res.message);
	        		return;
	        	}
	        	
        		var str = '<c:url value="/index.do" />';
        		document.location.href = str.split('.')[0] + '.do';
        		//document.location.href = '<c:url value="/startscreen.do" />';
			}		
	});
 }
</script>
</head>
<body>
	<div class="container">
		<form class="form-signin" id="form-logIn" name="form-logIn">
			<h2 class="form-signin-heading">로그인</h2>
			<input type="text"  id="user-id" name="user-id" class="input-block-level" placeholder="ID" value="admin"/>
			<input type="password"  id="password" name="password" class="input-block-level" placeholder="Password" value="admin"/>
			<!------------------------------------------>
			<div class="row-fluid">
				<div class="span12">
					<div class="span7 controls" style="margin-left: 25px;">
					<%-- <a class="btn btn-large btn-primary" href="<c:url value="/admin/userMng/userReg.do" />">사용자등록</a> --%>
					</div>
					<div class=" controls">
						
						<a class="btn btn-large btn-primary" href="javascript:memberLogIn()">로그인</a>
					</div>
				</div>
			</div>
			<!------------------------------------------>
		</form>
	</div>

</body>
</html>
