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
	
	var data = $("#form-logIn").serialize();

	$.ajax({
			type:'post',
			url:'/v1/login.do',
			data: data,
			dataType:'json',
			error:function(res){
				
				alert("서비스 실패.");
				
	        },
	        success: function(res){
	        	
	        	if(res.result == true){
	        	
	        		alert("로그인 성공");
	        
		   		}else{
		   			
		   			alert(res.message);
		   			
		   		}
			}		
	});
 }
</script>
</head>
<body>
	<div class="container">
		<form class="form-signin" id="form-logIn" name="form-logIn">
			<h2 class="form-signin-heading">로그인</h2>
			<input type="text"  id="user-id" name="user-id" class="input-block-level" placeholder="ID">
			<input type="password"  id="password" name="password" class="input-block-level" placeholder="Password">
			<div>
			<table>
			<colgroup><col width="50%"/><col width="40%"/><col width="10%"/></colgroup>
				<tr>
					<td></td>
					<td><a class="btn btn-large btn-primary" href="javascript:memberLogIn()">로그인</a></td>
					<td></td>
				</tr>
			</table>
			</div>
		</form>
	</div>

</body>
</html>
