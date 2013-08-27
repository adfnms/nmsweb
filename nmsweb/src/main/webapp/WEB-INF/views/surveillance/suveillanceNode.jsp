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
		
		//var str = regNodeListStr(jsonObj);
	
		var nodeObj = jsonObj["RegNodeItems"];
		
		if(jsonObj["RegNodeItems"].length==0){
			$('#nodeListTable').empty();
			var nodeObj = jsonObj["RegNodeItems"];
			var str = "";
			str += '<table class="table table-striped ">';
			str += '	<tr>';
			str += '		<td class="span3"></td>';
			str += '		<td class="span6" style ="text-align: center;" ><input type ="hidden" name="emptyNode" value="emptyNode">등록된 노드가 없습니다.</td>';
			str += '		<td class="span3"></td>';
			str += '	</tr>';
			str += '</table>';
			$('#nodeListTable').append(str);
		}else{
			
			for( var i in nodeObj){
				$('#nodeListTable').empty();
				var nodeId =  nodeObj[i]["nodeid"];
				var recentCount =10;
				var query = encodeURI("query=this_.nodeId = '" + nodeId + "'");
				var filter = "&orderBy=ifLostService&order=desc&limit=" + recentCount;
				var data =query + filter;
				var strInfo = "";
				$.ajax({
					type : 'get',
					url : '/' + version + '/outages',
					dataType : 'json',
					data : data,
					contentType : 'application/json',
					error : function(data) {
						alert("장애목록 가져오기 실패");
					},
					success : function(data) {
						//성공 시 데이터 불러오기
					var	outageObj = data["outage"];
					
					console.log(outageObj);
					
					var strInfo = regNodeInfoStr(data);
					
					$('#nodeListTable').append(strInfo);
						
					}
				});
				
			
			
		}
		
		}
		
		//$('#nodeListTable').append(str);
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
	
function delCategory(){
		
		var emptyNode  = $("#nodeListTable input[name=emptyNode]").val();
		
		if(!confirm("삭제하시겠습니까?")){
			return;
		}else{
			if(emptyNode=="notEmptyNode"){
				
				if(!confirm("등록된 노드가 있습니다. 삭제하시겠습니까?")){
					return;
				}
				
				var categoryid=("${categoryId}");
				$.ajax({
			      	url : '<c:url value="/delCategory.do" />',
			        type:'post',
			        dataType:'json',
			        data:'categoryid='+categoryid,
			        error:function(data, status, err){
			            alert('Error, service not found');
			        },
			        success:function(res){
			        	if(res.isSuccess == false)
			       		{
			        		alert(res.errorMessage);        		
			        		return;
			       		}
			        	alert("삭제되었습니다. 메인화면으로 이동합니다.");
			        	$(location).attr('href', "/v1/index.do");
			        }
				});  
				
			}else{
				var categoryid=("${categoryId}");
				
				
				$.ajax({
			      	url : '<c:url value="/delCategory.do" />',
			        type:'post',
			        dataType:'json',
			        data:'categoryid='+categoryid,
			        error:function(data, status, err){
			            alert('Error, service not found');
			        },
			        success:function(res){
			        	if(res.isSuccess == false)
			       		{
			        		alert(res.errorMessage);        		
			        		return;
			       		}
			        	alert("삭제되었습니다. 메인화면으로 이동합니다.");
			        	$(location).attr('href', "/v1/index.do");
			        }
				});  
			}
		}
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
				<li class="active"><h4><a href="<c:url value="/index.do" />">System 분류 : </a></h4></li>
				<li><h4 class="text-success">${categoryname}</h4></li>
				</ul>
			</div>
			
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
			
			
				<div class="row-fluid">
					<div class="span12" >
						<table class="table table-striped" id="nodeListTable"></table>
					</div>
				</div>
			</div>
			<!-- <div class="span2" style=" margin-left: 73px; margin-top: 7px;" >
					 <a type="button" class="btn btn-primary span12" data-toggle="modal" title="suveillance 삭제"
						 onclick="javascript:delCategory();">System 분류 삭제</a>
			</div>
			<div class="span2" style=" margin-left: 73px; margin-top: 23px;" >
					 <a type="button" class="btn btn-primary span12" data-toggle="modal" title="노드추가"
						href="#mySurvaillenceModal" onclick="javascript:addNodeCategory();">+ 노드추가</a>
			</div> -->
		</div>
		
		<div class="row-fluid">
			<div class="span12">
				<div class="span8">
				</div>
				<div class="span2">
					<a type="button" class="btn btn-primary span12" data-toggle="modal" title="suveillance 삭제"
						 onclick="javascript:delCategory();">System 분류 삭제</a>
				</div>
				<div class="span2">
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
	<div class="modal-body" style="height:400px;  overflow-y:auto;">
		<div class="row-fluid" >
		<form id="checkboxPopup">
			<table id="checkboxNode" class="table table-striped">
				
			</table>
		
		</form>
			
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true" onclick="javascript:regNodePop();" >+ 등록</button>
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>
<!-- Modal -->
</html>