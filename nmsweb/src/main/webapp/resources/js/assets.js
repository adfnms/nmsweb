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
		/*if (jsonObj["CatagoryList"].length > 0) {
			str += "<tr>";
			str += "<td>"
				+ "[&nbsp;"+nodeObj[i]["@id"]+"&nbsp;]"
				+ "</td>";
		}*/
		
		
	}