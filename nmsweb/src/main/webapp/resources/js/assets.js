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
			str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ CatagoryList[0]["nodeid"] + "'>" + CatagoryList[0]["nodeLabel"]+ "</a></td>";
			str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ CatagoryList[0]["nodeid"]+ "'>" + CatagoryList[0]["nodeLabel"]+ "</a></td>";
			
		}else if(CatagoryList.length >0) {
			for ( var i in CatagoryList) {

				str += "<tr>";
				str += "<td>" + CatagoryList[i]["category"]+ "</td>";
				str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ CatagoryList[i]["nodeid"] + "'>" + CatagoryList[i]["nodeLabel"]+ "</a></td>";
				str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ CatagoryList[i]["nodeid"]+ "'>" + CatagoryList[i]["nodeLabel"]+ "</a></td>";
			}
		}
		return str;
	}
	
	function getAssetInfo(callback,nodeId) {
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
	
