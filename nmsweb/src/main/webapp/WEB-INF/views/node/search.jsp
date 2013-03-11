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
	<jsp:param value="노드관리" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
	var pageNum = "1";
	$(document).ready(function() {
		
		getNodeList();
		
	});
	
	//callback 함수 jsonObj를 이용 파싱 후 append
	function addNodeLists(jsonObj) {
		console.log(jsonObj);
		$('#nodeListTable').empty();

		var nodeObj = jsonObj["node"];
		var str = "<tr>";

		if(1 == jsonObj["@totalCount"]){
			str += "<td><a href='#'>"+nodeObj["@id"]+"</a></td>";
			str += "<td><a href='#'>"+nodeObj["@label"]+"</a></td>";
		}else{
			
			for ( var i in nodeObj) {
				
				str += "<td><a href='#'>"+nodeObj[i]["@id"]+"</a></td>";
				str += "<td><a href='#'>"+nodeObj[i]["@label"]+"</a></td>";
				
				if( i % 2 == 1 ){
					str += "</tr><tr>";
				}
				
			}	
			
		}
		
		str += "</tr>";

		$('#nodeListTable').append(str);
		
		//페이징 HTML 가져오기
		getPagingHtml($('#pagingDiv'), "goSearchPageing", jsonObj["@totalCount"], pageNum, "10", "10" );
	}
	
	//노드 리스트 가져오기
	function getNodeList(){
		
		var data = getFromToInputValue($('#searchNodeFrm'));

		getNodeTotalList(addNodeLists,data);
		
	}
	
	function goSearchPageing(pageNm){
		
		pageNum = pageNm;
		
		$('#searchNodeFrm input[name=offset]').val(
				(parseInt(pageNm)-1) * $('#searchNodeFrm input[name=limit]').val()
		);
		
		getNodeList();
		
	}
	
	//검색 버튼
	function searchNode(){
		
		//기본설정으로 되돌리기
		pageNum = "1";
		$('#searchNodeFrm input[name=limit]').val("10");
		$('#searchNodeFrm input[name=orderBy]').val("id");
		$('#searchNodeFrm input[name=offset]').val("0");
			
		getNodeList();
		
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" />Home</a> <span class="divider">/</span></li>
					<li class="active">노드검색</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<form id="searchNodeFrm" name="searchNodeFrm">
			<input type="hidden" id="limit" name="limit" value="10"/>
			<input type="hidden" id="orderBy" name="orderBy" value="id"/>
			<input type="hidden" id="offset" name="offset" value="0"/>
			<div class="row-fluid">
				<div class="span10 well well-small">
					<div class="row-fluid">
						<div class="span12">
							<h4>노드 검색</h4>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<div class="row-fluid" id="cateDiv">
								<div class="span12">
									<label class="span2 control-label">노드명</label>
									<div class="span4 controls">
										<input type="text" id="label" name="label" value=""/>
									</div>
									<label class="span2 control-label">노드 ID</label>
									<div class="span4 controls">
										<input type="text" id="id" name="id" value=""/>
									</div>
								</div>
							</div>
							<div class="row-fluid" id="cateDiv">
								<div class="span12">
									<label class="span2 control-label">제공 서비스</label>
									<div class="span4 controls">
										<select class="span12" id="seviceName" name="seviceName">
											<option value="">선택해주세요.</option>
											<option value="DNS">DNS</option>
											<option value="Dell-OpenManage">Dell-OpenManage</option>
											<option value="FTP">FTP</option>
											<option value="HP Insight Manager">HP Insight Manager</option>
											<option value="HTTP">HTTP</option>
											<option value="HTTP-8080">HTTP-8080</option>
											<option value="HTTPS">HTTPS</option>
											<option value="HypericAgent">HypericAgent</option>
											<option value="HypericHQ">HypericHQ</option>
											<option value="ICMP">ICMP</option>
											<option value="IMAP">IMAP</option>
											<option value="MSExchange">MSExchange</option>
											<option value="MySQL">MySQL</option>
											<option value="NRPE">NRPE</option>
											<option value="NRPE-NoSSL">NRPE-NoSSL</option>
											<option value="OpenNMS-JVM">OpenNMS-JVM</option>
											<option value="Oracle">Oracle</option>
											<option value="POP3">POP3</option>
											<option value="Postgres">Postgres</option>
											<option value="Router">Router</option>
											<option value="SMTP">SMTP</option>
											<option value="SNMP">SNMP</option>
											<option value="SQLServer">SQLServer</option>
											<option value="SSH">SSH</option>
											<option value="SVC">SVC</option>
											<option value="StrafePing">StrafePing</option>
											<option value="Telnet">Telnet</option>
											<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>
										</select>
									</div>
									<label class="span2 control-label">TCP/IP 주소</label>
									<div class="span4 controls">
										<input type="text" placeholder="*.*.*.*" id="ipAddress"  name="ipAddress" value=""/>
									</div>
								</div>
							</div>
							<!-- <div class="row-fluid" id="cateDiv">
								<div class="span12">
									<label class="span2 control-label">MAC 주소</label>
									<div class="span4 controls">
										<input type="text" />
									</div>
									<label class="span2 control-label">외부 별명</label>
									<div class="span4 controls">
										<input type="text" />
									</div>
								</div>
							</div>
							<div class="row-fluid" id="cateDiv">
								<div class="span12">
									<label class="span2 control-label">날짜</label>
									<div class="span3 controls">
										<input type="text" class="span12" />
									</div>
									<div class="span1" style="text-align: center;">~</div>
									<div class="span3 controls">
										<input type="text" class="span12" />
									</div>
									<div class="span3">
										<button type="button" class="btn btn-primary span12"
											title="검색" onclick="javascript:searchNode();">검색</button>
									</div>
								</div>
							</div> -->
							<div class="row-fluid" id="cateDiv">
								<div class="span12">
									<div class="span9"></div>
									<div class="span3">
										<button type="button" class="btn btn-primary span6"
											title="검색" onclick="javascript:location.reload();">Clean</button>
										<button type="button" class="btn btn-primary span6"
											title="검색" onclick="javascript:searchNode();">검색</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="row-fluid">
			<div class="span10 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>노드 목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped" id="nodeListTable"></table>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12" id="pagingDiv"></div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
