<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.4.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/resources/js/json.min.js" /> "></script>
<script type="text/javascript">
$(document).ready(function(){	
	$.postJSON("create", {name:"梁建均",loginName:"ljj",password:"123"}, function(user) {
		alert(user.status)
	})
})
</script>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>
</body>
</html>
