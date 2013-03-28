<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.bluecapsystem.nms.define.NMSProperties"%>
<%@ page session="true"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/graphUtil/css/graph.css" />" />
<script src="<c:url value="/resources/graphUtil/js/modernizr.custom.04022.js" />"></script>
<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/notification.js" />"></script>
<!-- <style>
.graph-container>li:nth-child(1) .bar-inner {
	height: 25%;
	bottom: 0;
}

.graph-container>li:nth-child(2) .bar-inner {
	height: 75%;
	bottom: 0;
}

.graph-container>li:nth-child(3) .bar-inner {
	height: 100%;
	bottom: 0;
}

.graph-container>li:nth-child(4) .bar-inner {
	height: 50%;
	bottom: 0;
}

.graph-container>li:nth-child(5) .bar-inner {
	height: 25%;
	bottom: 0;
}

.graph-container>li:nth-child(6) .bar-inner {
	height: 0%;
	bottom: 0;
}

.graph-container>li:nth-child(7) .bar-inner {
	height: 0%;
	bottom: 0;
}
</style> -->
<script type="text/javascript">
	$(document).ready(function() {

		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-7243260-2' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = "<c:url value="/resources/graphUtil/js/ga.js" />";
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();

		
	});
</script>
	<ul class="graph-container" style="width:885px;">
		<li><span>Network Interfaces<div></div></span>
			<div class="bar-wrapper" Style="width: 120px;">
				<div class="bar-container">
					<div class="bar-background"></div>
					<div class="bar-inner"></div>
					<div class="bar-foreground"></div>
				</div>
			</div></li>
		<li><span>Web Servers<div></div></span>
			<div class="bar-wrapper" Style="width: 120px;">
				<div class="bar-container">
					<div class="bar-background"></div>
					<div class="bar-inner"></div>
					<div class="bar-foreground"></div>
				</div>
			</div></li>
		<li><span>Email Servers<div></div></span>
			<div class="bar-wrapper" Style="width: 120px;">
				<div class="bar-container">
					<div class="bar-background"></div>
					<div class="bar-inner"></div>
					<div class="bar-foreground"></div>
				</div>
			</div></li>
		<li><span>DNS and DHCP Servers<div></div></span>
			<div class="bar-wrapper" Style="width: 120px;">
				<div class="bar-container">
					<div class="bar-background"></div>
					<div class="bar-inner"></div>
					<div class="bar-foreground"></div>
				</div>
			</div></li>
		<li><span>Database Servers<div></div></span>
			<div class="bar-wrapper" Style="width: 120px;">
				<div class="bar-container">
					<div class="bar-background"></div>
					<div class="bar-inner"></div>
					<div class="bar-foreground"></div>
				</div>
			</div></li>
		<li><span>JMX Servers<div></div></span>
			<div class="bar-wrapper" Style="width: 120px;">
				<div class="bar-container">
					<div class="bar-background"></div>
					<div class="bar-inner"></div>
					<div class="bar-foreground"></div>
				</div>
			</div></li>
		<li><span>Other Servers<div></div></span>
			<div class="bar-wrapper" Style="width: 120px;">
				<div class="bar-container">
					<div class="bar-background"></div>
					<div class="bar-inner"></div>
					<div class="bar-foreground"></div>
				</div>
			</div></li>
		<li>
			<ul class="graph-marker-container">
				<li style="bottom: 25%;"><span>25%</span></li>
				<li style="bottom: 25%;"><span>25%</span></li>
				<li style="bottom: 50%;" class="text-info"><span>50%</span></li>
				<li style="bottom: 50%;" class="text-info"><span>50%</span></li>
				<li style="bottom: 75%;"><span>75%</span></li>
				<li style="bottom: 100%;" class="text-error"><span>100%</span></li>
			</ul>
		</li>
	</ul>
