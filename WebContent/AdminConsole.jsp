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
	<link rel="stylesheet" href="AdminConsole.css">
	
    <title>Admin Console</title>

</head>
<body>

<%
response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
//HTTP 1.1	

response.setHeader("Pragma","no-cache"); //HTTP 1.0

response.setHeader("Expires","0"); //Proxies

	if(session.getAttribute("adminObj") == null){
		response.sendRedirect("home.jsp");
		return;
	}


%>

<nav class="navbar navbar-inverse">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="home.jsp">WebSiteName</a>
          </div>
          <ul class="nav navbar-nav">
            <li class="active"><a href="AdminConsole.jsp">Admin Console</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="AdminConsole.jsp"><span class="glyphicon glyphicon-cog"></span> ${adminObj.getUsername()}</a></li>
            <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
          </ul>
        </div>
      </nav>
      
      <div class="container-fluid">
      	<ul class="list-group">
		  <li class="list-group-item"><a href="addFood.jsp">Add Food Item</a></li>
		  <li class="list-group-item"><a href="EnableFoodPage">Enable Food Item</a></li>
		  <li class="list-group-item"><a href="DisableFoodPage">Disable Food Item</a></li>
		  <li class="list-group-item"><a href="addDeliveryMan.jsp">Add delivery man</a></li>
		  <li class="list-group-item"><a href="DisableDeliveryManPage">Disable delivery man</a></li>
		  <li class="list-group-item"><a href="EnableDeliveryManPage">Enable delivery man</a></li>
		  <li class="list-group-item"><a href="TopFoods">View top 3 foods ordered</a></li>
		  <li class="list-group-item"><a href="TopCustomers">View top 3 customers who places most orders</a></li>
		  <li class="list-group-item"><a href="RecentOrders">View 5 most recent Orders</a></li>
		  <li class="list-group-item"><a href="getCustomerDetails.jsp">Search Customer by email</a></li>
		  <li class="list-group-item"><a href="getOrderDetails.jsp">Search Order by OrderID</a></li>
		  <li class="list-group-item"><a href="AdminActivity">View admin activity</a></li>
		  <li class="list-group-item"><a href="CustomerActivity">View customer activity</a></li>
		</ul>
      </div>




<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</body>
</html>