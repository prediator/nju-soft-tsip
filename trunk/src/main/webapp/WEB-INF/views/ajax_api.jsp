<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.4.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/resources/js/json.min.js" /> "></script>
<script type="text/javascript">
$.postJSON2 = function(url, data, callback) {
    return jQuery.ajax({
        'type': 'POST',
        'url': url,
        'contentType': 'application/json',
        'data': data,
        'dataType': 'json',
        'success': callback
    });
};
$(document).ready(function(){	
	$("#b").click(function(){
		$.postJSON2($("#text2").val(), $("#text").val(), function(user) {
			alert(user.status)
		})
	})
})
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" name="text" id="text2"/>
<input type="text" name="text" id="text"/>
<input type="button" name="submit" id="b"/>
</body>
</html>