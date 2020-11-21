<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.foodDelivery.Cart, java.util.List, com.foodDelivery.Food, java.util.*, com.foodDelivery.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Raleway:wght@500&display=swap" rel="stylesheet">
	<link rel = "stylesheet" href = "OrderSuccess.css">
	
    <title>Order Placed</title>
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



Cart cart = (Cart)session.getAttribute("foodCart");

if(cart.cart.size() == 0){
	response.sendRedirect("home.jsp");
	return;
}

cart.cart = new HashMap<>();

%>

	<nav class="navbar navbar-inverse">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="home.jsp">Daily Grill</a>
          </div>
          <ul class="nav navbar-nav">
            <li class="active"><a href="home.jsp">Home</a></li>
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
      
      
      
      <div class="success-animation">
<svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"><circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none" /><path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8" /></svg>
</div>

	<div class="container-fluid" align="center">
		<h3>Order Placed Successfully</h3>
		<div><h4>
			Your Order will be delivered by: <br>
			${deliveryMan.name}: ${deliveryMan.contact_num}</h4>
		</div>
	</div>
	
	
      
      
      
      
      
      
      
      
      
      <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" scr="OrderSuccess.js"></script>
</body>
</html>