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
	
	
	/*	메뉴의 검색 -> ASSETS 검색의 ASSETS in Category에 선택항목 중 Infrastructure와 Server를 선택하고 Search를 클릭 시 변화된 하단부의 소스코드
	 * 
	 * */
	function assetsListStr(jsonObj) {
		//var str = "";
		console.log(jsonObj);
		var CatagoryList=jsonObj["CatagoryList"];
		/***********************************************************************************/
		var TRobj = $("<tr></tr>");
		var TDobj = $("<td></td>");
		var Aobj = $("<a></a>");
		var TBODYobj = $("<tbody></tbody>");
		var Bobj = $("<b></b>");
		/***********************************************************************************/
		if (CatagoryList.length == 0) {
			/*str += "<tr>";
			str += "<td></td>";
			str += "<td>데이터가 없습니다</td>";
			str += "<td></td>";*/
			/***********************************************************************************/
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Bobj.clone().append("ASSETS")
					),
					TDobj.clone().append(
						Bobj.clone().append("ASSET LINK")
						),
					TDobj.clone().append(
						Bobj.clone().append("NODE LINK")
					)
				)	
			);
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().text(),
					TDobj.attr("style", "width:260px").clone().text("데이터가 없습니다")
				)
			);
			/***********************************************************************************/
		}else if(CatagoryList.length ==1) {
			/*str += "<tr>";
			str += "<td>" + CatagoryList[0]["category"]+ "</td>";
			str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ CatagoryList[0]["nodeid"] + "&nodeLabel="+CatagoryList[0]["nodeLabel"]+"'>" + CatagoryList[0]["nodeLabel"]+ "</a></td>";
			str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ CatagoryList[0]["nodeid"]+ "'>" + CatagoryList[0]["nodeLabel"]+ "</a></td>";*/
			/***********************************************************************************************************************************************************************************************/
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Bobj.clone().append("ASSETS")
					),
					TDobj.clone().append(
						Bobj.clone().append("ASSET LINK")
						),
					TDobj.clone().append(
						Bobj.clone().append("NODE LINK")
					)
				)	
			);
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().text(CatagoryList[0]["category"]),
					TDobj.clone().append(
						Aobj.attr("href", "/"  + version + "/assets/modifyAssets?nodeId=" + CatagoryList[0]["nodeid"] + "&nodeLabel=" + CatagoryList[0]["nodeLabel"]).clone().text(CatagoryList[0]["nodeLabel"])
					),
					TDobj.clone().append(
						Aobj.attr("href", "/"  + version + "/search/node/nodeDesc.do?nodeId=" + CatagoryList[0]["nodeid"]).clone().text(CatagoryList[0]["nodeLabel"])
					)
				)
			);
			/***********************************************************************************************************************************************************************************************/
		}else if(CatagoryList.length >0) {
				TBODYobj.append(
					TRobj.clone().append(
						TDobj.clone().append(
							Bobj.clone().append("ASSETS")
						),
						TDobj.clone().append(
							Bobj.clone().append("ASSET LINK")
							),
						TDobj.clone().append(
							Bobj.clone().append("NODE LINK")
						)
					)	
				);
			for ( var i in CatagoryList) {
				/*str += "<tr>";
				str += "<td>" + CatagoryList[i]["category"]+ "</td>";
				str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ CatagoryList[i]["nodeid"] + "&nodeLabel="+CatagoryList[i]["nodeLabel"]+"'>" + CatagoryList[i]["nodeLabel"]+ "</a></td>";
				str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ CatagoryList[i]["nodeid"]+ "'>" + CatagoryList[i]["nodeLabel"]+ "</a></td>";*/
				/***********************************************************************************************************************************************************************************************/
				TBODYobj.append(
					TRobj.clone().append(
						TDobj.clone().text(CatagoryList[i]["category"]),
						TDobj.clone().append(
							Aobj.attr("href", "/"  + version + "/assets/modifyAssets?nodeId=" + CatagoryList[i]["nodeid"] + "&nodeLabel="+CatagoryList[i]["nodeLabel"]).clone().text(CatagoryList[i]["nodeLabel"])
						),
						TDobj.clone().append(
							Aobj.attr("href", "/"  + version + "/search/node/nodeDesc.do?nodeId=" + CatagoryList[i]["nodeid"]).clone().text(CatagoryList[i]["nodeLabel"])
						)
					)
				);
				/***********************************************************************************************************************************************************************************************/
			}
		}
		//return str;
		return TBODYobj;
	}
	
	/* 메뉴의 검색 -> ASSETS 검색의 ASSETS in Category의 Field Search 선택항목 중 Address 1을 선택하고 Search를 클릭 시 변화된 하단부의 소스코드
	 * 
	 * */
	function FieldStr(jsonObj) {

		//var str = "";
		var FieldList=jsonObj["fieldData"];
		/***********************************************************************************/
		var TRobj = $("<tr></tr>");
		var TDobj = $("<td></td>");
		var Aobj = $("<a></a>");
		var TBODYobj = $("<tbody></tbody>");
		var Bobj = $("<b></b>");
		/***********************************************************************************/
		if (FieldList.length == 0) {
			/*str += "<tr>";
			str += "<td></td>";
			str += "<td>데이터가 없습니다</td>";
			str += "<td></td>";*/
			/***********************************************************************************/
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Bobj.clone().append("ASSETS")
					),
					TDobj.clone().append(
						Bobj.clone().append("ASSET LINK")
						),
					TDobj.clone().append(
						Bobj.clone().append("NODE LINK")
					)
				)	
			);
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().text(),
					TDobj.attr("style", "width:260px").clone().text("데이터가 없습니다")
				)
			);
			/***********************************************************************************/
		}else if(FieldList.length ==1) {
			/*str += "<tr>";
			str += "<td>" + FieldList[0]["category"]+ "</td>";
			str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ FieldList[0]["nodeid"] + "&nodeLabel="+FieldList[0]["nodeLabel"]+"'>" + FieldList[0]["nodeLabel"]+ "</a></td>";
			str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ FieldList[0]["nodeid"]+ "'>" + FieldList[0]["nodeLabel"]+ "</a></td>";*/
			/***********************************************************************************************************************************************************************************************/
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Bobj.clone().append("ASSETS")
					),
					TDobj.clone().append(
						Bobj.clone().append("ASSET LINK")
						),
					TDobj.clone().append(
						Bobj.clone().append("NODE LINK")
					)
				)	
			);
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().text(FieldList[0]["category"]),
					TDobj.clone().append(
						Aobj.attr("href", "/"  + version + "/assets/modifyAssets?nodeId=" + FieldList[0]["nodeid"] + "&nodeLabel=" + FieldList[0]["nodeLabel"]).clone().text(FieldList[0]["nodeLabel"])
					),
					TDobj.clone().append(
						Aobj.attr("href", "/"  + version + "/search/node/nodeDesc.do?nodeId=" + FieldList[0]["nodeid"]).clone().text(FieldList[0]["nodeLabel"])
					)
				)
			);
			/***********************************************************************************************************************************************************************************************/
		}else if(FieldList.length >0) {
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Bobj.clone().append("ASSETS")
					),
					TDobj.clone().append(
						Bobj.clone().append("ASSET LINK")
						),
					TDobj.clone().append(
						Bobj.clone().append("NODE LINK")
					)
				)	
			);
			for ( var i in FieldList) {
				/*str += "<tr>";
				str += "<td>" + FieldList[i]["category"]+ "</td>";
				str += "<td><a href='/" + version+ "/assets/modifyAssets?nodeId="+ FieldList[i]["nodeid"] + "&nodeLabel="+FieldList[0]["nodeLabel"]+"'>" + FieldList[i]["nodeLabel"]+ "</a></td>";
				str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ FieldList[i]["nodeid"]+ "'>" + FieldList[i]["nodeLabel"]+ "</a></td>";*/
				/***********************************************************************************************************************************************************************************************/
				TBODYobj.append(
					TRobj.clone().append(
						TDobj.clone().text(FieldList[i]["category"]),
						TDobj.clone().append(
							Aobj.attr("href", "/"  + version + "/assets/modifyAssets?nodeId=" + FieldList[i]["nodeid"] + "&nodeLabel="+FieldList[0]["nodeLabel"]).clone().text(FieldList[i]["nodeLabel"])
						),
						TDobj.clone().append(
							Aobj.attr("href", "/"  + version + "/search/node/nodeDesc.do?nodeId=" + FieldList[i]["nodeid"]).clone().text(FieldList[i]["nodeLabel"])
						)
					)
				);
				/***********************************************************************************************************************************************************************************************/
			}
		}
		//return str;
		return TBODYobj;
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
	
