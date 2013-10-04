/**get Service total list
 * @param callback
 */
function getServiceList(callback){
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/serviceList',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('이벤트 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}

/**Get the IP interface for the given node
 * @param callback
 * @param nodeId
 * @param ipAddress
 */
function getServiceFromNodeidIpaddress(callback, nodeId, ipAddress){
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/' + nodeId
				+ '/ipinterfaces/'
				+ ipAddress
				+ '/services',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("[" + ipAddress + '] 아이피 정보 검색 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data, nodeId, ipAddress);
			}

		}
	});
	
}





/**
 * Get the IP interface for the given node 
 * 
 * @param callback
 * @param nodeId
 * @param ipAddress
 */
function getServiceInfo(callback, nodeId, ipAddress, serviceNm) {

	if (nodeId == "") {
		alert("노드 ID가 없습니다.");
		return;
	}

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/' + nodeId + '/ipinterfaces/' + ipAddress + '/services/' + serviceNm,
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("[" + nodeId + '] 서비스 정보 검색 실패');
		},
		success : function(data) {

			// 콜백함수
			if (typeof callback == "function") {
				callback(data, nodeId, ipAddress);
			}

		}
	});

}


/************************** view String edit *****************************/

/** 서비스 리스트를 <option> 테그 str로 바꿔줌
 * @param jsonObj
 * @returns {String}
 */
function getOptiontagToServiceList(jsonObj) {

	var services = jsonObj["services"];

	var optionStr = "";
	if (services.length > 1) {

		for ( var i in services) {
			optionStr += "<option value='"+services[i]["serviceid"]+"'>"
					+ services[i]["servicename"] + "</option>";
		}

	} else {
		optionStr += "<option value='"+services["serviceid"]+"'>"
				+ services["servicename"] + "</option>";

	}

	return optionStr;
}

/** 이벤트 정보를 table 테그 Str로 만들어준다. 
 * @param jsonObj
 */
function getServiceInfoBox(jsonObj, nodeId, ipAddress, serviceNm){
	
	var nodeObj = getSpecificNode(null, nodeId);
	var stats = statsToStringFromStatoCode(jsonObj["@status"]);
		
	var serviceInfoStr = 	'<div class="row-fluid">'+
							'	<h5>일반</h5>'+
							'</div>'+
							'<div class="row-fluid">'+
							'	<div class="span12 well well-small">'+
							'		<table class="table table-striped">'+
							'			<tr>'+
							'				<th>노드</th>'+
							'				<td>'+
							'					<a href="/'+version+'/search/node/nodeDesc.do?nodeId='+nodeId+'">'
													+nodeObj["@label"]+
							'					</a>'+
							'				</td>'+
							'			</tr>'+
							'			<tr>'+
							'				<th>인터페이스</th>'+
							'				<td>'+
							'					<a href="/v1/search/node/interfaceDesc.do?nodeId='+nodeId+'&intf='+ipAddress+'">'
													+ipAddress+
							'					</a>'+
							'				</td>'+
							'			</tr>'+
							'			<tr>'+
							'				<th>폴링 상태</th>'+
							'				<td>'+stats+'</td>'+
							'			</tr>'+
							'		</table>'+
							'	</div>'+
							'</div>';
	
	return serviceInfoStr;
}