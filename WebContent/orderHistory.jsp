<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Raleway:wght@500&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
	<link rel = "stylesheet" href = "orderHistory.css">
    <title>Order History</title>
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

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="home.jsp">Website Name</a>
          </div>
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<c:if test="${usermail!=null}">
            	<li><a href="userPage.jsp"><span class="glyphicon glyphicon-user"></span>${customerObj.getCustomer_name()}</a></li>
            </c:if>
            <c:if test="${usermail!=null}" >
            	<li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </c:if>
          </ul>
        </div>
      </nav>
      
      
      
   
   
   <div class="container-fluid" id="listContainer">
   		<c:forEach items="${order_history}" var="orderObj">
   		<p class="h3">Order ID : ${orderObj.orderID }, Order Date : ${orderObj.created_time }</p>
			<ul class="list-group">
			<c:forEach items="${orderObj.cart.getCart()}" var="entry">
				
	  				<li class="list-group-item">
	    				<span class="badge">${entry.value }</span>
	    					${entry.key.name}
	  				</li>
   				
   				
			</c:forEach>
			<p class="h4">Total Price = ${orderObj.cart.calculateTotalPrice()}</p>
			</ul>
			<hr class="solid">
		</c:forEach>
   </div>






	



</body>
</html>