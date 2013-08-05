/** Total Category info
 * @param callback
 * @param data
 */
function getTotalIndexInfo(callback, data){
	$.ajax({
		type : 'get',
		url : '/' + version + '/dashboard/Category',
		dataType : 'json',
		contentType : 'application/json',
		data : data,
		error : function(data) {
			
			
			console.log(data);
			
			
			alert('카테고리 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}

/** Total Category info
 * @param callback
 * @param data
 */
function getCategoryToName(callback, cateNm, idx){
	$.ajax({
		type : 'get',
		url : '/' + version + '/dashboard/Category/'+cateNm,
		dataType : 'json',
		contentType : 'application/json',
		async :false,
		error : function(data) {
			alert('카테고리 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data,idx);
			}
		}
	});
	
}

/** Total Category info
 * @param callback
 * @param data
 */
function getNodeListToCategoryName(callback, cateNm){
	$.ajax({
		type : 'get',
		url : '/' + version + '/dashboard/Category/Detail/'+cateNm,
		dataType : 'json',
		contentType : 'application/json',
		async :false,
		error : function(data) {
			alert('카테고리 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}