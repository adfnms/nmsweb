var version = getVersion();
function getTotalRequisitionsList(callback) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions/deployed.json',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('필요조건 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});

}