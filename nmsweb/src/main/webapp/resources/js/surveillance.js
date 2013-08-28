	


/*surveillance 카테고리  정보 갖고오기  */
function getsurveillanceLabel(callback){
	var str = ""; 
	$.ajax({
		type : 'get',
		url : '/' + version + '/getSurveillanceCategories.do',
		dataType : 'json',
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 카테고리 정보 가져오기 실패');
		},
		success : function(data) {
			getCount(callback,data);
		}
	});
}

function getCount(callback,data){
	

	$('#surveillenceLabel').empty();
	for(var i in data["CategoriesItem"]){
		
		var  categoryid = data["CategoriesItem"][i]["categoryid"];
		var categoryname = data["CategoriesItem"][i]["categoryname"];
		
		$.ajax({
			type : 'get',
			url : '/' + version + '/Categories/getCount.do',
			data: 'categoryid='+categoryid,
			dataType : 'json',
			async : false,
			contentType : 'application/json',
			error : function(data) {
				alert('모든 카테고리 정보 가져오기 실패');
			},
			success : function(data) {
				
				if (typeof callback == "function") {
					callback(data,categoryid,categoryname);
				}
			}
		}); 
	}
}

function countStr(jsonObj,categoryid,categoryname){
	
	var str = ""; 
	str += '	<tr>';
	str += '		<td class="text "><a href=/'+version+'/surveillanceNode.do?categoryid='+categoryid+'&categoryname=' + categoryname + '>' + categoryname + '</a></td>';
	str += '		<td class="text ">&nbsp;&nbsp;&nbsp;&nbsp;'+ jsonObj["CategoriesCount"] + '개</td>';
	str += '		<td class="text-error" id="'+categoryid+'"></td>';
	str += '	</tr>';
	return str;
}

function getNodeToSurveillance(callback,categoryId){
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/getRegNodeList.do',
		data: 'categoryid='+categoryId,
		dataType : 'json',
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('등록된 노드 정보 가져오기 실패');
		},
		success : function(data) {
			
			if (typeof callback == "function") {
				callback(data,categoryId);
			}
		}
	}); 
	
}

function regNodeListStr(jsonObj){
	
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
		
	}else{
		$('#nodeListTable').empty();
		
		var nodeObj = jsonObj["RegNodeItems"];
		console.log(jsonObj);
		var str = "";
		str += '<table class="table table-striped" style="margin-bottom: -16px;" id="nodeInfoTable">';
		str+='<input type ="hidden" name="emptyNode" value="notEmptyNode">';
	
		for( var i in nodeObj){
				str += '	<tr >';
				str += '		<td class="span3"><h5>노드 라벨&nbsp;:&nbsp;&nbsp;<a href="/'+version+'/search/node/nodeDesc.do?nodeId='+nodeObj[i]["nodeid"]+'">'+nodeObj[i]["nodelabel"]+'</a></h5></td>';
				str += '	</tr>';
			$("#checkboxPopup input[name=nodeid][value=" + nodeObj[i]["nodeid"] + "]").attr("checked", true);
		}
		str += '</table>';
		
	}
	return str;
}

function NodeListAjax(callback,nodeId,nodelabel,categoryId){
	
	var recentCount =10;
	var query = encodeURI("query=this_.nodeId = '" + nodeId + "'");
	var filter = "&orderBy=ifLostService&order=desc&limit=" + recentCount;
	var data =query + filter;
	
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
			if (typeof callback == "function") {
				callback(data,nodeId,nodelabel,categoryId);
			}
		}
	});
}

function regNodeInfoStr(data,nodeId,nodelabel){
		console.log("----------regNodeInfoStr---------");
		console.log(data);
		console.log(nodelabel);
		var outageObj = data["outage"];
		var totalCount = data["@totalCount"];
		var strInfo = "";
		
		if(totalCount == 0){
			strInfo += '<div class="accordion-group" id=test>';
			strInfo += '		<div class="accordion-heading" >';
			strInfo += '			<h3><a style="margin-left: 18px;">'+nodelabel+'&nbsp;: 노드는 현재 장애 목록이 없습니다.</a></h3>';
			strInfo += '		</div>';
			strInfo += '</div>';
			$("#checkboxPopup input[name=nodeid][value=" + nodeId+ "]").attr("checked", true);
			return strInfo;
		}else if(totalCount > 1){
				
				strInfo += '<div class="accordion-group" id=test>';
				strInfo += '		<div class="accordion-heading" >';
				strInfo += '			<h3><a class="accordion-toggle text-error" data-toggle="collapse" data-parent="#accordion2"  href="#'+nodeId+'" id= '+nodelabel+'>'+nodelabel+'</a></h3>';
				strInfo += '		</div>';
				strInfo += '		<div id="'+nodeId+'" class="accordion-body collapse in">';
				strInfo += '			<div class="accordion-inner">';
				strInfo += '				<div class="well well-small">';
				strInfo += '<table class="table table-striped ">';
					
				for( var i in outageObj){
					
					stat = outageObj[i]["serviceLostEvent"]["@severity"];
					if(stat=="CRITICAL"){
						 statusProgress = "progress-danger";
					}
					else if(stat=="MAJOR"){
						 statusProgress = "progress-caution";						
					}
					else if(stat=="MINOR"){
						 statusProgress = "progress-warning";
					}
					else if(stat=="WARNING"){
						 statusProgress = "progress-gray";
					}
					else if(stat=="NORMAL"){
						 statusProgress = "progress-info";
					}
					else if(stat=="CLEARED"){
						 statusProgress = "progress";
					}
					else if(stat=="INDETERMINATE"){
						 statusProgress = "progress-success";
					}
					
						strInfo += '	<tr >';
						strInfo += '		<td class="span3"><h5 style="width: 195px;">인터페이스&nbsp;:&nbsp;&nbsp;<a href="/'+version+'/search/node/nodeDesc.do?nodeId='+outageObj[i]["ipAddress"]+'">'+outageObj[i]["ipAddress"]+'</a></h5></td>';
						strInfo += '		<td class="" style ="width: 455px;"><div class="progress progress-striped active '+statusProgress+' " style="margin-bottom: 0px;margin-top: 10px; ">';
						strInfo += '		<div class="bar" style="width:100%">'+stat+'</div>';
						strInfo += '		</div></td>';
						strInfo += '		<td class="span4 "><h5>발생시간&nbsp;:&nbsp;&nbsp;<a  class="text-error">'+new Date(outageObj[i]["serviceLostEvent"]["createTime"]).format('yy-MM-dd hh:mm:ss')+'</a></h5></td>';
						strInfo += '		<td class="span3"><h5>서비스&nbsp;:&nbsp;&nbsp;<a class="text-success">'+outageObj[i]["monitoredService"]["serviceType"]["name"]+'</a></h5></td>';
						strInfo += '	</tr>';
				}
				strInfo += '</table>';
				strInfo += '				</div>';
				strInfo += '			</div>';
				strInfo += '		</div>';
				strInfo += '	</div>';
				strInfo += '<table class="table table-striped" style="margin-bottom: -16px;" >';
				strInfo+='<input type ="hidden" name="emptyNode" value="notEmptyNode">';
				
				$("#checkboxPopup input[name=nodeid][value=" + nodeId+ "]").attr("checked", true);
			
				return strInfo;
			
		}else if(totalCount = 1){
			
			strInfo += '<div class="accordion-group" id=test>';
			strInfo += '		<div class="accordion-heading" >';
			strInfo += '			<h3><a class="accordion-toggle text-error" data-toggle="collapse" data-parent="#accordion2"  href="#'+nodeId+'" id= '+nodelabel+'>'+nodelabel+'</a></h3>';
			strInfo += '		</div>';
			strInfo += '		<div id="'+nodeId+'" class="accordion-body collapse in">';
			strInfo += '			<div class="accordion-inner">';
			strInfo += '				<div class="well well-small">';
			strInfo += '<table class="table table-striped ">';
				
			console.log(outageObj["serviceLostEvent"]["@severity"]);
			
			
				
				stat = outageObj["serviceLostEvent"]["@severity"];
				if(stat=="CRITICAL"){
					 statusProgress = "progress-danger";
				}
				else if(stat=="MAJOR"){
					 statusProgress = "progress-caution";						
				}
				else if(stat=="MINOR"){
					 statusProgress = "progress-warning";
				}
				else if(stat=="WARNING"){
					 statusProgress = "progress-gray";
				}
				else if(stat=="NORMAL"){
					 statusProgress = "progress-info";
				}
				else if(stat=="CLEARED"){
					 statusProgress = "progress";
				}
				else if(stat=="INDETERMINATE"){
					 statusProgress = "progress-success";
				}
				
					strInfo += '	<tr >';
					strInfo += '		<td class="span3"><h5 style="width: 195px;">인터페이스&nbsp;:&nbsp;&nbsp;<a href="/'+version+'/search/node/nodeDesc.do?nodeId='+outageObj["ipAddress"]+'">'+outageObj["ipAddress"]+'</a></h5></td>';
					strInfo += '		<td class="" style ="width: 455px;"><div class="progress progress-striped active '+statusProgress+' " style="margin-bottom: 0px;margin-top: 10px; ">';
					strInfo += '		<div class="bar" style="width:100%">'+stat+'</div>';
					strInfo += '		</div></td>';
					strInfo += '		<td class="span4"><h5>발생시간&nbsp;:&nbsp;&nbsp;<a  class="text-error">'+new Date(outageObj["serviceLostEvent"]["createTime"]).format('yy-MM-dd hh:mm:ss')+'</a></h5></td>';
					strInfo += '		<td class="span3"><h5>서비스&nbsp;:&nbsp;&nbsp;<a  class="text-success">'+outageObj["monitoredService"]["serviceType"]["name"]+'</a></h5></td>';
					strInfo += '	</tr>';
			
			strInfo += '</table>';
			strInfo += '				</div>';
			strInfo += '			</div>';
			strInfo += '		</div>';
			strInfo += '	</div>';
			strInfo += '<table class="table table-striped" style="margin-bottom: -16px;" >';
			strInfo+='<input type ="hidden" name="emptyNode" value="notEmptyNode">';
			
			$("#checkboxPopup input[name=nodeid][value=" + nodeId+ "]").attr("checked", true);
		
			return strInfo;
			
		}
		
}




function getSearchAssetsList(callback,categorynm) {
		$.ajax({
			type : 'get',
			url : '/' + version + '/assets/selectSearchAssets',
			contentType : 'application/json', 
			data:'category='+categorynm,
			error : function(data) {
				alert('Get Assets List 서비스 실패');
			},
			success : function(data) {
				if (typeof callback == "function") {
					callback(data);
				}
				
			}
		});   	 
	}
	
	/*/test/*/
	function getFieldSearchAssets(callback,name,value) {
		
		$.ajax({
			type : 'get',
			url : '/' + version + '/assets/fieldSearch',
			contentType : 'application/json', 
			data:name+'='+value,
			error : function(data) {
				alert('Get Assets List 서비스 실패');
			},
			success : function(data) {
				if (typeof callback == "function") {
					callback(data);
				}
			}
		});   	
	} 
	/*/test/*/
	
	/*function assetsListStr(jsonObj) {
		var str = "";
		
		console.log(jsonObj);
		
		var CatagoryList=jsonObj["CatagoryList"];
		
		if (CatagoryList.length == 0) {
			str += "<tr>";
			str += "<td></td>";
			str += "<td>데이터가 없습니다</td>";
			str += "<td></td>";
		}else if(CatagoryList.length ==1) {
			str += "<tr>";
			str += "<td>" + CatagoryList[0]["category"]+ "</td>";
			str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ CatagoryList[0]["nodeid"] + "&nodeLabel="+CatagoryList[0]["nodeLabel"]+"'>" + CatagoryList[0]["nodeLabel"]+ "</a></td>";
			str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ CatagoryList[0]["nodeid"]+ "'>" + CatagoryList[0]["nodeLabel"]+ "</a></td>";
			
		}else if(CatagoryList.length >0) {
			for ( var i in CatagoryList) {

				str += "<tr>";
				str += "<td>" + CatagoryList[i]["category"]+ "</td>";
				str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ CatagoryList[i]["nodeid"] + "&nodeLabel="+CatagoryList[i]["nodeLabel"]+"'>" + CatagoryList[i]["nodeLabel"]+ "</a></td>";
				str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ CatagoryList[i]["nodeid"]+ "'>" + CatagoryList[i]["nodeLabel"]+ "</a></td>";
			}
		}
		return str;
	}*/
	
	
	function FieldStr(jsonObj) {

		var str = "";
		var FieldList=jsonObj["fieldData"];
		
		if (FieldList.length == 0) {
			str += "<tr>";
			str += "<td></td>";
			str += "<td>데이터가 없습니다</td>";
			str += "<td></td>";
		}else if(FieldList.length ==1) {
			str += "<tr>";
			str += "<td>" + FieldList[0]["category"]+ "</td>";
			str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ FieldList[0]["nodeid"] + "&nodeLabel="+FieldList[0]["nodeLabel"]+"'>" + FieldList[0]["nodeLabel"]+ "</a></td>";
			str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ FieldList[0]["nodeid"]+ "'>" + FieldList[0]["nodeLabel"]+ "</a></td>";
			
		}else if(FieldList.length >0) {
			for ( var i in FieldList) {

				str += "<tr>";
				str += "<td>" + FieldList[i]["category"]+ "</td>";
				str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ FieldList[i]["nodeid"] + "&nodeLabel="+FieldList[0]["nodeLabel"]+"'>" + FieldList[i]["nodeLabel"]+ "</a></td>";
				str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ FieldList[i]["nodeid"]+ "'>" + FieldList[i]["nodeLabel"]+ "</a></td>";
			}
		}
		return str;
	}
	
	
	function getAssetInfo(callback,nodeId) {
		
		console.log("---------getAssetInfo----------");
		console.log(nodeId);
		
		$.ajax({
			type : 'get',
			url : '/' + version + '/assets/getAssetInfo',
			contentType : 'application/json', 
			data:'nodeId='+nodeId,
			error : function(data) {
				alert('Get Assets List 서비스 실패');
			},
			success : function(data) {
				if (typeof callback == "function") {
					callback(data);
				}
				
			}
		});   	 
		
		
	}
	function nodeCheckBoxStr(jsonObj, categoryid ){
		console.log("-----jsonObj------");
		console.log(jsonObj);
		
		var str = "";
		var node=jsonObj["node"];
		
		if (node.length == 0) {
			str += "<tr>";
			str += "<td></td>";
			str += "<td>노드가 없습니다</td>";
			str += "<td></td>";
		}else if(node.length ==1) {
			str += "<tr>";
			str += "	<td class='span1'>";
			str += "		<label class='checkbox'>";
			str += "			<input  value='"+categoryid+"'  name='categoryid' id='categoryid'  type='hidden'/>	"; 
			str += "			<input style='margin-left: 0px;;' value='"+node["@id"]+"' name='nodeid' id='nodeid'  type='checkbox' />"; 
			str += "		</label>";
			str += "</td>";
			str += "	<td class=''><h5>노드아이디 : "+node["@id"]+"</h5></td>";
			str += "	<td class='span1'>노드 라벨 :"+node["@label"] +"</td>";
		}else if(node.length >0) {
			for ( var i in node) {
				str += "<tr>";
				str += "	<td class=''>";
				str += "		<label class='checkbox'>";
				str += "			<input  value='"+categoryid+"'  name='categoryid' id='categoryid'  type='hidden'/>	"; 
				str += "			<input style='margin-left: 0px;;'  value='"+node[i]["@id"]+"' name='nodeid' id='nodeid'  type='checkbox' />"; 
				str += "		</label>";
				str += "</td>";
				str += "	<td class=''><h5>노드아이디 : "+node[i]["@id"]+"</h5></td>";
				str += "	<td class=''><h5>노드 라벨 :"+node[i]["@label"] +"</h5></td>";
			}
		}
		return str;
		
		
	}
