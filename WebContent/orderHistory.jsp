<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
//HTTP 1.1	

response.setHeader("Pragma","no-cache"); //HTTP 1.0

response.setHeader("Expires","0"); //Proxies

if(session.getAttribute("usermail") == null || session.getAttribute("foodCart") == null){
	response.sendRedirect("login.jsp");
	return;
}


%>

<c:forEach items="${order_history}" var="orderObj">
	${orderObj.orderID } : ${orderObj.created_time }<br>
	<c:forEach items="${orderObj.cart.getCart()}" var="entry">
		key = ${entry.key.name}, value = ${entry.value } <br>
	</c:forEach>
</c:forEach>
	



</body>
</html>