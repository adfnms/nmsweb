<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="노드목록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/surveillance.js" />"></script>
<script type="text/javascript">
var pageNum = 1;
var limit = 10;
var offset = 0;
var orderBy = "id";


	$(document).ready(function() {
		
		// getNodeListToCategoryName(addNodeLists, "${cateNm}"); 
		
		//해당 카테고리에 등록되어있는 노드정보를 가져온다.
		getNodeToSurveillance(getRegNodeList, "${categoryId}");
	});
	
	
	function getRegNodeList(jsonObj) {
		
		var str = regNodeListStr(jsonObj);
		
		console.log(jsonObj);
		/* var nodeObj = jsonObj["RegNodeItems"];
		
		if(jsonObj["RegNodeItems"].length==0){
			
			$("input[name=nodeid][value=" + nodeObj[i]["nodeid"] + "]").attr("checked", false);
			
		}else{
			
			for( var i in nodeObj){
				
				$("input[name=nodeid][value=" + nodeObj[i]["nodeid"] + "]").attr("checked", true);
					
				}
		}
		 */
		
		
		$('#nodeListTable').append(str);
		
		
	}
	
	/*노드 리스트 정보 갖고와서 POPUP창에 보여주기  */
	function addNodeCategory(){
		
	
		getNodeTotalList(NodeCheckBoxStr,  "orderBy="+orderBy+"&limit="+limit);
		
		/*등록된 노드의 정보를 갖고와서 체크해주기  */
		getNodeToSurveillance(getRegNodeList, "${categoryId}");
	}
	function NodeCheckBoxStr(jsonObj){
		var categoryid=("${categoryId}");
		
		$('#checkboxNode').empty();
		var str = nodeCheckBoxStr(jsonObj,categoryid );
		
		$('#checkboxNode').prepend(str);	
	}
	
	
	// POPUP창에 노드 CheckBox의 노드아이디를 1)삭제 후  2)저장 <트랜젝션>
	function regNodePop(){
		
		//var chkObj = $('#checkboxPopup');
		var data = $("#checkboxPopup").serialize();
		
		 $.ajax({
	      	url : '<c:url value="/regNodePop.do" />',
	        type:'post',
	        dataType:'json',
	        data:data,
	        error:function(data, status, err){
	            alert('Error, service not found');
	        },
	        success:function(res){
	        	if(res.isSuccess == false)
	       		{
	        		alert(res.errorMessage);        		
	        		return;
	       		}
	        	getNodeToSurveillance(getRegNodeList, "${categoryId}");
	        }
		});  
		
	}
	
	
	
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" >Home</a> <span class="divider">/</span></li>
					<li class="active">노드 목록</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
				<li class="active"><h4><a href="<c:url value="/index.do" />">surveillance : </a></h4></li>
				<li><h4 class="text-success">${categoryname}</h4></li>
				</ul>
			</div>
			
		</div>
		<div class="row-fluid">
			<div class="span9 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped" id="nodeListTable"></table>
					</div>
				</div>
			</div>
			<div class="span2" style=" margin-left: 73px; margin-top: 23px;" >
					 <a type="button" class="btn btn-primary span12" data-toggle="modal" title="노드추가"
						href="#mySurvaillenceModal" onclick="javascript:addNodeCategory();">+ 노드추가</a>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="span10">
				</div>
				<div class="span2">
				<!--  -->
				
					 <a type="button" class="btn btn-primary span12" data-toggle="modal" title="노드추가"
						href="#mySurvaillenceModal" onclick="javascript:addNodeCategory();">+ 노드추가</a>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
<!-- Modal -->
<div id="mySurvaillenceModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalSurvaillence" aria-hidden="true" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalSurvaillence" >노드 추가</h3>
	</div>
	<div class="modal-body">
		<div class="row-fluid" >
		<!----------------->
		<form id="checkboxPopup">
			<table id="checkboxNode" class="table table-striped">
				
			</table>
		
		</form>
			
		<!----------------->
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true" onclick="javascript:regNodePop();" >+ 등록</button>
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>
<!-- Modal -->
</html>