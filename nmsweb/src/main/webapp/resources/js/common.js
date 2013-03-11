var version = getVersion();
/** URl을 이용하여 현재의 버젼을 가져온다
 * @returns
 */
function getVersion() {
	var url = location.href;
	url = url.split('//');
	url = url[1].split('/')[1];
	return url;
}


/** 파라미터 맟에 "/"를 넣어줌
 * @param urlData
 * @returns {String}
 */
function setUrlData(urlData){
	
	var _return = "";
	
	if(urlData != "" || urlData != null){
		
		_return = "/"+urlData;
	}
	
	return _return;
}

/**
 * @param jsonObj    페이징을 넣을 json object
 * @param totalCount  총 갯수
 * @param crruntPageNm	현재 페이지 번호
 * @param rowSize	한번에 보여질 갯수
 * @param pageBlockSize  페이징에서 보여질 갯수
 */
function getPagingHtml(jsonObj, callback, totalCount, crruntPageNm, rowSize, pageBlockSize ){
	
	var startPage = Math.floor(crruntPageNm / pageBlockSize) + 1;
	
	var endPage =  Math.ceil( totalCount / rowSize ) > parseInt(startPage) + parseInt(pageBlockSize) -1 
						? parseInt(startPage) + parseInt(pageBlockSize) -1 
						: Math.ceil( totalCount / rowSize );
	
	var str = "<div class='pagination' style='text-align:center;'><ul>";
	if( (startPage * pageBlockSize * 2) <= startPage ){
		str += '<li><a href="javascript:'+callback+'(\'1\');"><<</a></li>';
	}
	
	if(startPage > pageBlockSize){
		str += '<li><a href="javascript:'+callback+'(\''+parseInt(startPage)-paresInt(pageBlockSize)+'\');"><</a></li>';
	}
	
	for(var i = startPage ; i <= endPage ; i++){
		
		if(i == crruntPageNm){
			str += '<li class="disabled"><a href="javascript:'+callback+'(\''+i+'\');">'+i+'</a></li>';
		}else{
			str += '<li><a href="javascript:'+callback+'(\''+i+'\');">'+i+'</a></li>';
		}
		
	}
	
	if( endPage * rowSize < totalCount){
		str += '<li><a href="javascript:'+callback+'(\''+ (parseInt(endPage)+1) +'\');">></a></li>';
	}
	
	if( endPage * rowSize < totalCount ){
		str += '<li><a href="javascript:'+callback+'(\''+Math.ceil( totalCount / rowSize )+'\');">>></a></li>';
	}
	str += "</ul></div>";
	
	$(jsonObj).empty();
	$(jsonObj).append(str);
	
}

/**value의 값이 있는 파라미터만 serialize
 * @param frm : json Object
 * @returns {String}  
 */
function getFromToInputValue(frm){
	
	var dataArray  = $(frm).find("input,select");
	var _return = ""; 
	
	for( var i = 0 ; i < dataArray.length ; i++){
		
		if($(dataArray[i]).val() != ""){
			
			if(_return == ""){
				_return = $(dataArray[i]).attr("name")+"="+$(dataArray[i]).val();
			}else{
				_return = _return + "&"+$(dataArray[i]).attr("name")+"="+$(dataArray[i]).val();
			}
			
		}
		

	}
	
	return _return;
	
}
