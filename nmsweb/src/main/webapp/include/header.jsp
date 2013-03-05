<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String titleStr = request.getParameter("title") != null ? " - "+request.getParameter("title") :"";
	String styleFlag = request.getParameter("styleFlag") != null ? request.getParameter("styleFlag") :"N";
%>
<title>Network Manage System<%=titleStr %></title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css" />" rel="stylesheet">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/bootstrap/ico/apple-touch-icon-144-precomposed.png" />">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value="/resources/bootstrap/ico/apple-touch-icon-114-precomposed.png" />">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value="/resources/bootstrap/ico/apple-touch-icon-72-precomposed.png" />">
    <link rel="apple-touch-icon-precomposed" href="<c:url value="/resources/bootstrap/ico/apple-touch-icon-57-precomposed.png" />">
    <link rel="shortcut icon" href="<c:url value="/resources/bootstrap/ico/favicon.png" />">

	<script src="<c:url value="/resources/bootstrap/js/jquery-1.8.2.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-transition.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-alert.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-modal.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-dropdown.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-scrollspy.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-tab.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-tooltip.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-popover.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-button.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-collapse.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-carousel.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap-typeahead.js" />"></script>
	<script src="<c:url value="/resources/js/common/json2.js" />"></script>

	<% if(styleFlag.equals("Y")){ %> 
	<style type="text/css">
      body {
        padding-top: 20px;
        padding-bottom: 60px;
      }

      /* Custom container */
      .container {
        margin: 0 auto;
        max-width: 1000px;
      }
      .container > hr {
        margin: 60px 0;
      }

      /* Main marketing message and sign up button */
      .jumbotron {
        margin: 80px 0;
        text-align: center;
      }
      .jumbotron h1 {
        font-size: 100px;
        line-height: 1;
      }
      .jumbotron .lead {
        font-size: 24px;
        line-height: 1.25;
      }
      .jumbotron .btn {
        font-size: 21px;
        padding: 14px 24px;
      }

      /* Supporting marketing content */
      .marketing {
        margin: 60px 0;
      }
      .marketing p + h4 {
        margin-top: 28px;
      }


      /* Customize the navbar links to be fill the entire space of the .navbar */
      .navbar .navbar-inner {
        padding: 0;
      }
      .navbar .nav {
        margin: 0;
        display: table;
        width: 100%;
      }
      .navbar .nav li {
        display: table-cell;
        width: 1%;
        float: none;
      }
      .navbar .nav li a {
        font-weight: bold;
        text-align: center;
        border-left: 1px solid rgba(255,255,255,.75);
        border-right: 1px solid rgba(0,0,0,.1);
      }
      .navbar .nav li:first-child a {
        border-left: 0;
        border-radius: 3px 0 0 3px;
      }
      .navbar .nav li:last-child a {
        border-right: 0;
        border-radius: 0 3px 3px 0;
      }
    </style>
    <% }%>