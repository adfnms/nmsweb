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
	
	
	/* 카테고리에 속하는 노드 정보 얻기 */
	function getRegNodeList(jsonObj,categoryId) {
		if(jsonObj["RegNodeItems"].length==0){
			$('#nodeListTable').empty();
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
			var nodeObj = jsonObj["RegNodeItems"];
			$('#nodeListTable').empty();
			surveillanceGetNodeListAjax(showRegNodeList,nodeObj,categoryId);			
		}
	}
	
	/* 카테고리에 속하는 노드 정보 화면에 표시 */
	function showRegNodeList(data,nodeId,nodelabel,categoryId){
		var strInfo = regNodeInfoStr(data,nodeId,nodelabel);
		$('#nodeListTable').append(strInfo);
	}
	
	
	/*노드 리스트 정보 갖고와서 등록  POPUP 창  */
	function addNodeCategoryPop(){
		
		getNodeTotalList(NodeTotalListPop,  "orderBy="+orderBy+"&limit="+limit);
		
		getNodeToSurveillance(NodeIsSurveillanceItemCheck, "${categoryId}");
	}
	
	/* 노드 전체 정보 가지고 화면에 보여줌 */
	function NodeTotalListPop(jsonObj){
		var categoryid=("${categoryId}");
		$('#checkboxNode').empty();
		var str = NodeTotalListPopObj(jsonObj,categoryid );
		$('#checkboxNode').prepend(str);	
	}
	
	/* 노드 전체 중 카테고리에 속한 노드를 체크 */
	function NodeIsSurveillanceItemCheck(jsonObj,categoryId)
	{
		var nodeObj = jsonObj["RegNodeItems"];
		for(var i in nodeObj)
			$("#checkboxPopup input[name=nodeid][value=" + nodeObj[i]["nodeid"]+ "]").attr("checked", true);
	}
	
	/* 삭제 노드  POPUP */
	function delNodeCategoryPop(){
		getNodeToSurveillance(getNodeToSurveillanceInfo, "${categoryId}");
	}
	
	/* 카테고리에 속하는 노드 정보  */
	function getNodeToSurveillanceInfo(jsonObj,categoryid) {
		var nodeObj = jsonObj["RegNodeItems"];
		$('#delNode').empty();
		var str = NodeToSurveillancePopObj(nodeObj, categoryid );
		$('#delNode').prepend(str);
	}
	
	/* 등록 POPUP 창 노드 CheckBox의 노드아이디를 이용하여 등록(전체 삭제후 선택된 노드 등록)저장 <트랜젝션> */
	function regNodePop(){
		
		//var chkObj = $('#checkboxPopup');
		var data = $("#checkboxPopup").serialize();
		if(!$("#checkboxPopup input[type=checkbox]").is(":checked"))
		{
			alert('선택된 노드가 없습니다.');
			return;
		}
		
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
	
	/* 삭제 POPUP 창  노드 CheckBox의 노드아이디로 이용하여 삭제 */
	function delNodePop(){
		
		//var chkObj = $('#checkboxPopup');
		var data = $("#delPopup").serialize();
		
		if(!$("#delPopup input[type=checkbox]").is(":checked"))
		{
			alert('선택된 노드가 없습니다.');
			return;
		}
		
		 $.ajax({
	      	url : '<c:url value="/delNodePop.do" />',
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

	/* 카테고리 삭제, 노드 삭제후 카테고리 삭제.. 메인페이지 이동 */
	function delCategory(){
		
		var emptyNode  = $("#nodeListTable input[name=emptyNode]").val();
		
		if(confirm("삭제하시겠습니까?") == false){
			return;
		}
		
		if(emptyNode=="notEmptyNode"){
			
			if(confirm("등록된 노드가 있습니다. 삭제하시겠습니까?") == false){
				return;
			}
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
					<li class="active">노드 목록 <span class="divider">/</span></li>
					<li><a href="#" onclick="javascript:beforeUrl()">이전 화면</a> <span class="divider">/</span></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span3">
						<h4 id="nodeLabel" style="width: 234px;"><a href="<c:url value="/index.do" />">System 분류 : </a>${categoryname}</h4>
					</div>
					<div class="span9">
						<jsp:include page="/include/statsBar.jsp" />
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span12" >
						<table class="table table-striped" id="nodeListTable"></table>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="span6">
				</div>
				<div class="span2">
					<a type="button" class="btn btn-primary span12" data-toggle="modal" title="suveillance 삭제"
						 onclick="javascript:delCategory();">System 분류 삭제</a>
				</div>
				<div class="span2">
					<a type="button" class="btn btn-primary span12" data-toggle="modal" title="노드 추가"
						href="#mySurvaillenceModal" onclick="javascript:addNodeCategoryPop();">+ 노드 추가</a>
				</div>
				<div class="span2">
					<a type="button" class="btn btn-primary span12" data-toggle="modal" title="노드 삭제"
						href="#deleteSurvaillenceModal" onclick="javascript:delNodeCategoryPop();">- 노드 삭제</a>
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
		<input  value='${categoryId}'  name='categoryid' id='categoryid'  type='hidden'/>
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
<div id="deleteSurvaillenceModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="deleteModalSurvaillence" aria-hidden="true" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalSurvaillence" >노드 제거</h3>
	</div>
	<div class="modal-body" style="height:400px;  overflow-y:auto;">
		<div class="row-fluid" >
		<form id="delPopup">
		<input  value='${categoryId}'  name='categoryid' id='categoryid'  type='hidden'/>
			<table id="delNode" class="table table-striped">
			</table>
		</form>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true" onclick="javascript:delNodePop();" >- 제거</button>
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>
<!-- Modal -->
</html>