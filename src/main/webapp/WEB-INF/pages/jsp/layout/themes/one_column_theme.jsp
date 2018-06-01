<%@ page pageEncoding="UTF-8" %>
<%@ include file='/WEB-INF/pages/jsp/layout/material/globle.jsp' %>
<!DOCTYPE html>
<html>
<head>
<%@ include file='/WEB-INF/pages/jsp/layout/material/lib.jsp' %>
<script src="${contextPath}/pages/js/layout/themes/container.js?v=${now}"></script>
<title></title>
</head>
<body class="bg-dark">
<!-- container -->
<div id='container' class="container">
	<tiles:insertAttribute name="content" />
</div>
</body>
</html>
