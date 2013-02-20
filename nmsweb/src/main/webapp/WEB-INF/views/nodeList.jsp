
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<title>Nodes</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="../assets/css/bootstrap.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="../assets/ico/favicon.png">


</head>

<body>


	<div class="row">
		<div class="span4">
				<input id=inID type="text" class="span8">
				<button class="btn btn-primary" onclick="searchNode()" >검색</button>

		</div>
		<div class="span4">
			<p>
				<button class="btn btn-primary" type="button" onclick="allNode()">All
					Nodes</button>
			</p>
		</div>

		<div id="message"></div>

		<table id="nodeTable" class="table table-striped">
			<tr>
				<td>Type</td>
				<td>ID</td>
				<td>label</td>
				<td>Category</td>
				<td>DisplayCategory</td>
				<td>LastModifiedBy</td>
				<td>LastModifiedDate</td>
				<td>Node</td>
				<td>NotifyCategory</td>
				<td>PollerCategory</td>
				<td>thresholdCategory</td>
				<td>createTime</td>
				<td>labelSource</td>
				<td>lastCapsdPoll</td>
			</tr>
		</table>
	</div>




	<!-- Le javascript
    ================================================== -->

	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/demo.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/json2.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/date.format.js" />"></script>
	<script>
	function searchNode() {
		var inid = $('#inID').val();
//		alert(aaa);

		/*			$.ajax({
		 type : "GET",
		 url : 'nodesAll', //데이터를 요청할 페이지
		 dataType : 'json', //데이터 유형
		 contentType: 'application/json',
		 data : '', //요청할 페이지에 전송할 파라메터
		
		 error : function(xhr, status, e) { //에러 발생시 처리함수
		 alert('Error');
		 },
		 success : function(jdata) { //성공시 처리 함수, 인수는 위에서 data를 사용한 경우 
		 //			alert(jdata);        //data라는 변수명을 하면 에러를 뱉습니다.
		 res = JSON.stringify(ttt);
		 var aaa = jdata.@totalCount;

		 //		test(jdata);
		 alert(aaa); 
		 }
		 });
		 */
		
		 
		var ur="nodes/"+inid ;
		alert(ur);
		$.getJSON(ur, function(data) {
			
			var mydoc = document;
			var p = mydoc.getElementById("nodeTable");
			var tbodyElement = p.getElementsByTagName("tbody")[0];
			
//			alert ("ddd");
			
			
			for (var i=1; i <tbodyElement.rows.length; i++){
	               tbodyElement.deleteRow(i);
//				 alert("aaaa: "+i);  
			   }
			   
			   
			   
			   
					var trElement = "";
					var tdElement = "";
					
                        
						trElement = mydoc.createElement("tr");
						
						$.each(data, function(key2, val2) {
							

							if(key2=="assetRecord"){
								
								$.each(val2, function(key3, val3) {
									
									tdElement = mydoc.createElement("td");
									txtElement = mydoc.createTextNode(val3);
									tdElement.appendChild(txtElement);
									trElement.appendChild(tdElement);
//									alert("key3="+key3+", val3="+val3);


								});
								
							} else {
								
								tdElement = mydoc.createElement("td");
								txtElement = mydoc.createTextNode(val2);
								tdElement.appendChild(txtElement);
								trElement.appendChild(tdElement);
								
							}
							
							
							
						});
						
//						alert("key2="+key2+", val2="+val2);
						tbodyElement.appendChild(trElement);
						


				
				p.appendChild(tbodyElement);
//				alert("key="+key+", val="+val);
			});


	

	}

	
	
		function allNode() {

	 
			$.getJSON('nodes', function(data) {
				
				var mydoc = document;
				var p = mydoc.getElementById("nodeTable");
				var tbodyElement = p.getElementsByTagName("tbody")[0];
				
                var count = 0;
                
				$.each(data, function(key, val) {
					
					
					
					if(key=="@count"){
						count = val;
						
					}
					
					if(key=="node"){
//						alert("aaa :"+ count);
						
						var trElement = "";
						var tdElement = "";
						
						for ( var i = 0; i < count ; i++) {
                            
							trElement = mydoc.createElement("tr");
							
							$.each(val[i], function(key2, val2) {
								

								if(key2=="assetRecord"){
									
									$.each(val2, function(key3, val3) {
										
										tdElement = mydoc.createElement("td");
										txtElement = mydoc.createTextNode(val3);
										tdElement.appendChild(txtElement);
										trElement.appendChild(tdElement);
//										alert("key3="+key3+", val3="+val3);


									});
									
								} else {
									
									tdElement = mydoc.createElement("td");
									txtElement = mydoc.createTextNode(val2);
									tdElement.appendChild(txtElement);
									trElement.appendChild(tdElement);
									
								}
								
								
								
							});
							
//							alert("key2="+key2+", val2="+val2);
							tbodyElement.appendChild(trElement);
							
						}
				}
					
					p.appendChild(tbodyElement);
//					alert("key="+key+", val="+val);
				});

			});

		

		}

	</script>

</body>
</html>
