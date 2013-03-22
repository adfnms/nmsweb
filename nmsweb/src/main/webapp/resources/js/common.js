var version = getVersion();
/**
 * URl을 이용하여 현재의 버젼을 가져온다
 * 
 * @returns
 */
function getVersion() {
	var url = location.href;
	url = url.split('//');
	url = url[1].split('/')[1];
	return url;
}

/**
 * replaceAll
 * 
 */
String.prototype.replaceAll = function(str1, str2) {
	var str = this;
	var result = str.replace(eval("/" + str1 + "/gi"), str2);
	return result;
}
/**
 * 파라미터 맟에 "/"를 넣어줌
 * 
 * @param urlData
 * @returns {String}
 */
function setUrlData(urlData) {

	var _return = "";

	if (urlData != "" || urlData != null) {

		_return = "/" + urlData;
	}

	return _return;
}

/**
 * @param jsonObj
 *            페이징을 넣을 json object
 * @param totalCount
 *            총 갯수
 * @param crruntPageNm
 *            현재 페이지 번호
 * @param rowSize
 *            한번에 보여질 갯수
 * @param pageBlockSize
 *            페이징에서 보여질 갯수
 */
function getPagingHtml(jsonObj, callback, totalCount, crruntPageNm, rowSize,
		pageBlockSize) {

	var startPage = Math.floor(crruntPageNm / pageBlockSize) + 1;

	var endPage = Math.ceil(totalCount / rowSize) > parseInt(startPage)
			+ parseInt(pageBlockSize) - 1 ? parseInt(startPage)
			+ parseInt(pageBlockSize) - 1 : Math.ceil(totalCount / rowSize);

	var str = "<div class='pagination' style='text-align:center;'><ul>";
	if ((startPage * pageBlockSize * 2) <= startPage) {
		str += '<li><a href="javascript:' + callback + '(\'1\');"><<</a></li>';
	}

	if (startPage > pageBlockSize) {
		str += '<li><a href="javascript:' + callback + '(\''
				+ parseInt(startPage) - paresInt(pageBlockSize)
				+ '\');"><</a></li>';
	}

	for ( var i = startPage; i <= endPage; i++) {

		if (i == crruntPageNm) {
			str += '<li class="disabled"><a href="javascript:' + callback
					+ '(\'' + i + '\');">' + i + '</a></li>';
		} else {
			str += '<li><a href="javascript:' + callback + '(\'' + i + '\');">'
					+ i + '</a></li>';
		}

	}

	if (endPage * rowSize < totalCount) {
		str += '<li><a href="javascript:' + callback + '(\''
				+ (parseInt(endPage) + 1) + '\');">></a></li>';
	}

	if (endPage * rowSize < totalCount) {
		str += '<li><a href="javascript:' + callback + '(\''
				+ Math.ceil(totalCount / rowSize) + '\');">>></a></li>';
	}
	str += "</ul></div>";

	$(jsonObj).empty();
	$(jsonObj).append(str);

}

/**
 * value의 값이 있는 파라미터만 serialize
 * 
 * @param frm :
 *            json Object
 * @returns {String}
 */
function getFromToInputValue(frm) {

	var dataArray = $(frm).find("input,select");
	var _return = "";

	for ( var i = 0; i < dataArray.length; i++) {

		if ($(dataArray[i]).val() != "") {

			if (_return == "") {
				_return = $(dataArray[i]).attr("name") + "="
						+ $(dataArray[i]).val();
			} else {
				_return = _return + "&" + $(dataArray[i]).attr("name") + "="
						+ $(dataArray[i]).val();
			}

		}

	}

	return _return;

}

/**
 * 날자 포멧을 변경 한다 params - f : date 표시 format auth : byun
 */
Date.prototype.format = function(f) {
	if (!this.valueOf())
		return " ";

	var weekName = [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ];
	var d = this;

	return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
		switch ($1) {
		case "yyyy":
			return d.getFullYear();
		case "yy":
			return (d.getFullYear() % 1000).zf(2);
		case "MM":
			return (d.getMonth() + 1).zf(2);
		case "dd":
			return d.getDate().zf(2);
		case "E":
			return weekName[d.getDay()];
		case "HH":
			return d.getHours().zf(2);
		case "hh":
			return ((h = d.getHours() % 12) ? h : 12).zf(2);
		case "mm":
			return d.getMinutes().zf(2);
		case "ss":
			return d.getSeconds().zf(2);
		case "a/p":
			return d.getHours() < 12 ? "오전" : "오후";
		default:
			return $1;
		}
	});
};

String.prototype.string = function(len) {
	var s = '', i = 0;
	while (i++ < len) {
		s += this;
	}
	return s;
};
String.prototype.zf = function(len) {
	return "0".string(len - this.length) + this;
};
Number.prototype.zf = function(len) {
	return this.toString().zf(len);
};

/**
 * json Object null check
 * 
 * @param parentObj
 * @param childName
 * @returns {String}
 */
function nullCheckJsonObject(parentObj, childName) {

	var strValue = "";

	if (parentObj != null) {
		
		
		if(parentObj[childName] != "null"){
			
			strValue = parentObj[childName];
		}
	}

	return strValue;

}


/** 상태에 대한 메세지알려준다.
 * @param code
 * @returns {String}
 */
function statsToStringFromStatoCode(code){
	var statsStr = "";

	switch(code){
		case 'N':
			statsStr ="모니터링 되지 않음";
			break;
		case 'R':
			statsStr ="Rescan to Resume";
			break;
		case 'F':
			statsStr ="Forced Unmanaged";
			break;
		default:
			statsStr ="모니터링 중";
			break;
	}

	return statsStr;
}

/**상태에 대하여 메세지를 알려준다.
 * @param code
 * @param avail
 * @returns {String}
 */
function availToStringFromStatoCode(code,avail){
	var statsStr = "";
	
	statsStr =statsToStringFromStatoCode(code);
	statsStr = statsStr == "모니터링 중" ? avail+"%" : statsStr;

	return statsStr;
}

function dateDiff(FromTime, ToTime){
	var _return;
	
	var fromDate = FromTime;
		       
	var toDate = ToTime;
	    
	var day = 1000*60*60*24;
	var hour = 1000*60*60;
	var min = 1000*60;
	var sec = 1000;
		
	var daysAfter = (toDate.getTime() - fromDate.getTime()) / day;
	var hourAfter = (toDate.getTime() - fromDate.getTime()) / hour;
	var minAfter = (toDate.getTime() - fromDate.getTime()) / min;
	var secAfter = (toDate.getTime() - fromDate.getTime()) / sec;

	daysAfter = Math.round(daysAfter);
	hourAfter = Math.round(hourAfter);
	minAfter = Math.floor(minAfter);
	secAfter = Math.floor(secAfter);
	
	if(daysAfter > 1){
		_return = Math.round(daysAfter) + "일"; // 지난 날짜 출력	
	}else if(hourAfter < 24 && hourAfter != 0){
		_return = hourAfter + "시간"; // 지난 시간 출력	
	}else if(minAfter < 60){
		_return = minAfter + "분"; // 지난 분 출력
	}else{
		_return = secAfter + "초"; // 지난 초 출력	
	}
	
	return _return;
	
}