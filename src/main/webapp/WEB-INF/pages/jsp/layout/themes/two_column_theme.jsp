<%@ page pageEncoding="UTF-8" %>
<%@ include file='/WEB-INF/pages/jsp/layout/material/globle.jsp' %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file='/WEB-INF/pages/jsp/layout/material/lib.jsp' %>
	<script src="${contextPath}/pages/js/layout/themes/container.js"></script>
	<title></title>
</head>
<body class="bg-dark">
<!-- Header -->
<tiles:insertAttribute name="header" />

<!-- container -->
<div id='container' class="container">
	<tiles:insertAttribute name="container" />
</div>

<!-- footer -->
<tiles:insertAttribute name="footer" />
</body>
</html>