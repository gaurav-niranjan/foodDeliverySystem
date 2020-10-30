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
    <link rel="stylesheet" href="FinalizeOrder.css">
	<link href="https://fonts.googleapis.com/css2?family=Raleway:wght@500&display=swap" rel="stylesheet">
	
    <title>Your Order</title>

</head>
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
	session.setAttribute("cart", cart);


	if(session.getAttribute("usermail") == null){
		response.sendRedirect("login.jsp");
		return;
	}
	
%>

<nav class="navbar navbar-inverse">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
          </div>
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
          </ul>
        </div>
      </nav>
      <c:choose>
      	<c:when test="${cart.getCart().size() == 0}">
      		<div class="container-fluid">
	      		<div class="jumbotron">
	  				<h1>Your Cart is Empty</h1>
	  					<p>Please add something to to your cart to place an order.</p>
	  					<p><a class="btn btn-primary btn-lg" href="createOrderPage" role="button">Order Now</a></p>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container-fluid" id="foodList">
      			<ul class="list-group">
					<c:forEach items="${cart.getCart().keySet()}" var="foodItem">
						<li class="list-group-item">
    					<span class="badge">${cart.getCart().get(foodItem)}</span>
    						${foodItem.name}
  						</li>
					</c:forEach>
				</ul>
				<div class="buttons">
					<a href="orderPlaceDao"><button type="button" class="btn btn-success">Place Order</button>	</a>
					<a href="order.jsp"><button type="button" class="btn btn-info">Modify</button></a>
					<a href="Logout"><button type="button" class="btn btn-danger">Cancel and Logout</button></a>
				</div>
					
      		</div>
		</c:otherwise>
      </c:choose>
      
















<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>