<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.bluecapsystem.nms.define.NMSProperties"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="사용자 설정" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>3D 그래프 CSS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Animated 3D Bar Chart with CSS3" />
        <meta name="keywords" content="css3, bar chart, animation, 3d" />
        <meta name="author" content="Sergey Lukin for Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/graphUtil/css/demo.css" />" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/graphUtil/css/graph.css" />" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:700,300,300italic' rel='stylesheet' type='text/css'>
		<script src="<c:url value="/resources/graphUtil/js/modernizr.custom.04022.js" />"></script>
		<script src="<c:url value="/resources/js/users.js" />"></script>
		<script src="<c:url value="/resources/js/notification.js" />"></script>
		 <style>
			input#f-none:checked ~ .graph-container > li:nth-child(1) .bar-inner { height: 0; bottom: -2.5em; }
			input#f-none:checked ~ .graph-container > li:nth-child(2) .bar-inner { height: 0; bottom: -2.5em; }
			input#f-none:checked ~ .graph-container > li:nth-child(3) .bar-inner { height: 0; bottom: -2.5em; }
			input#f-none:checked ~ .graph-container > li:nth-child(4) .bar-inner { height: 0; bottom: -2.5em; }
			input#f-none:checked ~ .graph-container > li:nth-child(5) .bar-inner { height: 0; bottom: -2.5em; }
			input#f-none:checked ~ .graph-container > li:nth-child(6) .bar-inner { height: 0; bottom: -2.5em; }
			input#f-none:checked ~ .graph-container > li:nth-child(7) .bar-inner { height: 0; bottom: -2.5em; }
			/* Product 1 */
			input#f-product1:checked ~ .graph-container > li:nth-child(1) .bar-inner { height: 50%; bottom: 0; }
			input#f-product1:checked ~ .graph-container > li:nth-child(2) .bar-inner { height: 53%; bottom: 0; }
			input#f-product1:checked ~ .graph-container > li:nth-child(3) .bar-inner { height: 55%; bottom: 0; }
			input#f-product1:checked ~ .graph-container > li:nth-child(4) .bar-inner { height: 56%; bottom: 0; }
			input#f-product1:checked ~ .graph-container > li:nth-child(5) .bar-inner { height: 57%; bottom: 0; }
			input#f-product1:checked ~ .graph-container > li:nth-child(6) .bar-inner { height: 58%; bottom: 0; }
			input#f-product1:checked ~ .graph-container > li:nth-child(7) .bar-inner { height: 59%; bottom: 0; }
			/* Product 2 */
			input#f-product2:checked ~ .graph-container > li:nth-child(1) .bar-inner { height: 40%; bottom: 0; }
			input#f-product2:checked ~ .graph-container > li:nth-child(2) .bar-inner { height: 50%; bottom: 0; }
			input#f-product2:checked ~ .graph-container > li:nth-child(3) .bar-inner { height: 60%; bottom: 0; }
			input#f-product2:checked ~ .graph-container > li:nth-child(4) .bar-inner { height: 70%; bottom: 0; }
			input#f-product2:checked ~ .graph-container > li:nth-child(5) .bar-inner { height: 80%; bottom: 0; }
			input#f-product2:checked ~ .graph-container > li:nth-child(6) .bar-inner { height: 90%; bottom: 0; }
			input#f-product2:checked ~ .graph-container > li:nth-child(7) .bar-inner { height: 100%; bottom: 0; }
			/* Product 3 */
			input#f-product3:checked ~ .graph-container > li:nth-child(1) .bar-inner { height: 25%; bottom: 0; }
			input#f-product3:checked ~ .graph-container > li:nth-child(2) .bar-inner { height: 75%; bottom: 0; }
			input#f-product3:checked ~ .graph-container > li:nth-child(3) .bar-inner { height: 100%; bottom: 0;}
			input#f-product3:checked ~ .graph-container > li:nth-child(4) .bar-inner { height: 50%; bottom: 0; }
			input#f-product3:checked ~ .graph-container > li:nth-child(5) .bar-inner { height: 25%; bottom: 0; }
			input#f-product3:checked ~ .graph-container > li:nth-child(6) .bar-inner { height: 0%; bottom: 0; }
			input#f-product3:checked ~ .graph-container > li:nth-child(7) .bar-inner { height: 0%; bottom: 0; }
			
		</style>
<script type="text/javascript">
	$(document).ready(function() {
	});
	
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-7243260-2']);
	_gaq.push(['_trackPageview']);

	(function() {
			var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
	
</script>
</head>

<body>
	<div class="container">
	
	<section class="main">
				
				<!-- <span class="button-label">Size:</span>
                <input type="radio" name="resize-graph" id="graph-small"  /><label for="graph-small">Small</label>
				<input type="radio" name="resize-graph" id="graph-normal" checked="checked" /><label for="graph-normal">Normal</label>
                <input type="radio" name="resize-graph" id="graph-large" /><label for="graph-large">Large</label> -->

				
                

				<span class="button-label">Product:</span>
                <input type="radio" name="fill-graph" id="f-none" checked="checked" /><label for="f-none">None</label>
                <input type="radio" name="fill-graph" id="f-product1"  /><label for="f-product1">Product 1</label>
                <input type="radio" name="fill-graph" id="f-product2" /><label for="f-product2">Product 2</label>
                <input type="radio" name="fill-graph" id="f-product3" /><label for="f-product3">Product 3</label>

                <ul class="graph-container" Style = "width: 888px;">
                    <li class="critical-graph">
                        <span>Network Interfaces</span>
                        <div class="bar-wrapper" Style = "width: 120px;">
                            <div class="bar-container">
                                <div class="bar-background"></div>
                                <div class="bar-inner"></div>
                                <div class="bar-foreground"></div>
                            </div>
                        </div>
                    </li>
                    <li class="major-graph">
                        <span>Web Servers</span>
                        <div class="bar-wrapper" Style = "width: 120px;">
                            <div class="bar-container">
                                <div class="bar-background"></div>
                                <div class="bar-inner"></div>
                                <div class="bar-foreground"></div>
                            </div>
                        </div>
                    </li>
                    <li  class="minor-graph">
                        <span>Email Servers</span>
                        <div class="bar-wrapper" Style = "width: 120px;">
                            <div class="bar-container">
                                <div class="bar-background"></div>
                                <div class="bar-inner"></div>
                                <div class="bar-foreground"></div>
                            </div>
                        </div>
                    </li>
                    <li class="warning-graph">
                        <span>DNS and DHCP Servers</span>
                        <div class="bar-wrapper" Style = "width: 120px;">
                            <div class="bar-container">
                                <div class="bar-background"></div>
                                <div class="bar-inner"></div>
                                <div class="bar-foreground"></div>
                            </div>
                        </div>
                    </li>
                    <li class="normal-graph">
                        <span>Database Servers</span>
                        <div class="bar-wrapper" Style = "width: 120px;">
                            <div class="bar-container">
                                <div class="bar-background"></div>
                                <div class="bar-inner"></div>
                                <div class="bar-foreground"></div>
                            </div>
                        </div>
                    </li>
                    <li class="cleared-graph">
                        <span>JMX Servers </span>
                        <div class="bar-wrapper" Style = "width: 120px;">
                            <div class="bar-container">
                                <div class="bar-background"></div>
                                <div class="bar-inner"></div>
                                <div class="bar-foreground"></div>
                            </div>
                        </div>
                    </li>
                    <li class="indeterminate-graph">
                        <span>Other Servers</span>
                        <div class="bar-wrapper" Style = "width: 120px;">
                            <div class="bar-container">
                                <div class="bar-background"></div>
                                <div class="bar-inner"></div>
                                <div class="bar-foreground"></div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <ul class="graph-marker-container">
                            <li style="bottom:25%;"><span>25%</span></li>
                            <li style="bottom:50%;" class="text-info"><span>50%</span></li>
                            <li style="bottom:75%;"><span>75%</span></li>
                            <li style="bottom:100%;" class="text-error"><span>100%</span></li>
                        </ul>
                    </li>
                    
                </ul>
            </section>
            <!----------------------------------------------------------------------------------------------------------------  -->
          <div class="">
            <ul class="nav nav-pills">
              <li class="active"><a href="#">Home</a></li>
              <li class="dropdown">
                <a class="dropdown-toggle" id="drop4" role="button" data-toggle="dropdown" href="<c:url value="/dashboard.do" />">DashBoard <b class="caret"></b></a>
                
              </li>
              <li class="dropdown">
                <a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="<c:url value="/monitoring/nodelist.do" />">모니터링 <b class="caret"></b></a>
                <ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">지도보기</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/monitoring/nodelist.do" />">노드목록</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">검색<b class="caret"></b></a>
                <ul id="menu3" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/search/node.do" />">노드검색</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/search/outage.do" />">장애 검색</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">리포트<b class="caret"></b></a>
                <ul id="menu3" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">자원별 리포트</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">사용자 지정 리포트</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">DB 리포트</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">통계리포트</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="<c:url value="/admin/node.do" />">운영관리<b class="caret"></b></a>
                <ul id="menu3" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/admin/node.do" />">노드관리</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/admin/groupMng.do" />">그룹관리</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/admin/userMng.do" />">사용자 관리</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/admin/setting.do" />">사용자 설정</a></li>
                </ul>
              </li>
            </ul> <!-- /tabs -->
          </div> 
	<!----------------------------------------------------------------------------------------------------------------------------------  -->
	</div>
	<!-- /container -->
</body>
</html>
