<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>

$(document).ready(function(){
	
	rePositionSideBar();
	$(window).resize( function(e){ rePositionSideBar();});
	
	/* 노드리스트 갖고오기 */
	getNodeListSideBar(addNodeListsSideBar, "orderBy=id&limit=0");
	
	getTotalIndexInfo(sidebarInfo, null);
	
});

function rePositionSideBar(){
	var conWidth = $(".container").width();
	var	width  = conWidth + ( ($(document).width() -conWidth) /2 ); 
	$("#sideBar").css("left",width+26);
	$("#sideBarOutageList").css("margin-right",-35);
	$("#sideBarOutageList").css("margin-left",-35);
	$("#sideBarOutageList").css("margin-top",-14);
}

function addNodeListsSideBar(jsonObj) {
	
	$('#sideBarNodeList').empty();
	
			var str = getNodelistJsonObj(jsonObj);
			$('#sideBarNodeList').append(str);
}

function sidebarInfo(jsonObj) {
	
	//장애 목록
	var str = "";
	var ULobj = $("<ul></ul>");
	var TRobj = $("<tr></tr>");
	var TABLEobj =$("<table></table>");
	var THobj = $("<th></th>");
	var TDobj = $("<td></td>");
	var Aobj = $("<a></a>");
	var LIobj = $("<li></li>");
	var H5obj = $("<h5></h5>");
	var STRONGobj = $("<strong></strong>");
	
	var sideOutageObj = jsonObj["Outages"];
	if(sideOutageObj=="null"){
		
		$('#sideBarOutageList').append(
				H5obj.attr("class","text-error").append( 
					STRONGobj.text("장애가 없습니다.")
					)
			);	
		
	}else{
		var current = new Date();
		var lostTime = null;
		var lastTime = null;
		
		for ( var i in sideOutageObj) {

			
			lostTime = new Date(sideOutageObj[i]["iflostservice"].replace(' ','T') + ":00");
			
			lastTime = dateDiff(lostTime, current);
			
			$('#sideBarOutageList').append(
				H5obj.clone().append( 
					STRONGobj.clone().append(
							Aobj.clone().attr("href","#popupOutageInfoMethod").attr("class","text-error").attr("data-toggle","modal").attr("onclick","javascript:popupOutageInfo('"+sideOutageObj[i]["outageid"]+"')").text(sideOutageObj[i]["ipaddr"]),
// 							Aobj.clone().attr("href","/"+version + "/search/outage/outageDesc.do?outageId="+ sideOutageObj[i]["outageid"]+"").text(" ("+lastTime+")")
							Aobj.clone().attr("href","javascript:popupOutageInfo('"+sideOutageObj[i]["outageid"]+"')").text(" ("+lastTime+")")
					)
				)
			);	 
		
		}
		
	}
	
	
	
	
}

// function outageSideBarPop(outageid){
// 	var data = "id="+outageid;
	
// 	getTotalOutagesList(addOutageSideInfo, data);
// }
// /* outageSideBarPop Callback */
// function addOutageSideInfo(jsonObj){
	
// 	$('#outageInfoDivSide').empty();
// 	var outageInfoStr = getOutageInfoBox(jsonObj);
// 	$('#outageInfoDivSide').append(outageInfoStr);

// 	//$("#myModalLabel").html(ipaddr+"&nbsp;장애&nbsp;정보");
// }

function popupOutageInfo(data)
{
	var _data = "id="+data;
	getTotalOutagesList(addOutageInfo, _data);
}

function addOutageInfo(jsonObj){
	
	var outageInfoStr = getOutageInfoBox(jsonObj);
	$('#popupOutageInfo').empty();
	$('#popupOutageInfo').append(outageInfoStr);
	$('#popupOutageInfoMethod').modal('show');
	
}

function popupEventInfo(eventId)
{
	
	var data = "id="+eventId;
	
	getTotalEvenstList(addEventInfo,data);

}

/* Event Info */
function addEventInfo(jsonObj){
	
	$('#popupEventInfo').empty();
	//Event Info
	var eventInfoStr = 	getEventinfoBox(jsonObj);
	$('#popupEventInfo').append(eventInfoStr);
	
	//Event Log
	var eventLogStr = 	getEventLogBox(jsonObj);
	$('#popupEventInfo').append(eventLogStr);

	//Event Desc
	var eventDescStr = 	getEventDescBox(jsonObj);
	$('#popupEventInfo').append(eventDescStr);
	
	$('#popupEventInfoMethod').css('left','45%');
	$('#popupOutageInfoMethod').modal('hide');
	$('#popupEventInfoMethod').modal('show');

}
/*//outage Info Callback */
/*// 장애 정보 POPUP창 */

</script>
<div class="well affix hidden-phone" id="sideBar">
	<ul class="nav nav-list">
		<li class="nav-header"><h5>장애&nbsp;목록</h5></li>
		<li class="nav-header" id="sideBarOutageList"></li>
	</ul>
</div>
<!-- Modal -->
<!-- <div id="myModalSide" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" > -->
<!--   <div class="modal-header"> -->
<!--     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
<!--     <h3 id="myModalLabelSide" >장애정보</h3> -->
<!--   </div> -->
<!--   <div class="modal-body" > -->
<!--     <div class="row-fluid" id="outageInfoDivSide"></div> -->
<!--   </div> -->
<!--   <div class="modal-footer"> -->
<!--     <button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true">Close</button> -->
<!--   </div> -->
<!-- </div> -->

<!--  						정보 Popup 창 						-->
<div id="popupEventInfoMethod" class="modal hide fade" tabindex="-1" style="width:880px;" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    	<h3 id="myModalLabelSide" >이벤트 장애 정보</h3>
	</div>
	<div class="modal-body" style="width:850px;">
		<div class="row-fluid" id="popupEventInfo"></div>
	</div>
	<div class="modal-footer">
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div> 

<div id="popupOutageInfoMethod" class="modal hide fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    	<h3 id="myModalLabelSide" >서비스 장애 정보</h3>
	</div>
	<div class="modal-body" >
		<div class="row-fluid" id="popupOutageInfo"></div>
	</div>
	<div class="modal-footer">
		<button class="btn  btn-primary" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div> 
<!-- 							정보 Popup 창 END 									-->

<!--/.well -->
