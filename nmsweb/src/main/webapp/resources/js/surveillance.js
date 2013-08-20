	


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
	
	
	console.log(data);
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
	str += '		<th><a href=/'+version+'/surveillanceNode.do?categoryid='+categoryid+'&categoryname=' + categoryname + '>' + categoryname + '</a></th>';
	str += '		<th class="text-error">&nbsp;&nbsp;&nbsp;&nbsp;'+ jsonObj["CategoriesCount"] + '개</th>';
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
				callback(data);
			}
		}
	}); 
	
}

function regNodeListStr(jsonObj){
	console.log("---------getNodeToSurveillance--------");
	console.log(jsonObj);
	
	if(jsonObj["RegNodeItems"].length==0){
		$('#nodeListTable').empty();
		var nodeObj = jsonObj["RegNodeItems"];
		var str = "";
		str += '<table class="table table-striped ">';
		str += '	<tr>';
		str += '		<td class="span3"></td>';
		str += '		<td class="span6" style ="text-align: center;" >등록된 노드가 없습니다.</td>';
		str += '		<td class="span3"></td>';
		str += '	</tr>';
		str += '</table>';
		
	}else{
		$('#nodeListTable').empty();
		
		var nodeObj = jsonObj["RegNodeItems"];
		var str = "";
		str += '<table class="table table-striped" style="margin-bottom: -16px;">';
		for( var i in nodeObj){
			var status = Number(nodeObj[i]["nodelabel"]).toFixed(2) >= 100 ? "normal" :  "critical";
			str += '	<tr >';
			str += '		<td class="span2"></td>';
			str += '		<td class="span3">노드 라벨&nbsp;:&nbsp;&nbsp;<a>'+nodeObj[i]["nodelabel"]+'</a></td>';
			str += '		<td class="span3"> 노드 아이디&nbsp;:&nbsp;&nbsp;'+nodeObj[i]["nodeid"]+'</td>';
			str += '	</tr>';
		}
		str += '</table>';
		
	}

	return str;

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
	
	
	
	
	
	function assetsListStr(jsonObj) {
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
	}
	
	
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
	
