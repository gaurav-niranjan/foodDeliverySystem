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
	<link rel="stylesheet" href="deleteFood.css">
	
    <title>Delete Food Item</title>

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
            <li class="active"><a href="#">Admin Console</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="adminLogin.jsp"><span class="glyphicon glyphicon-cog"></span> Admin Login</a></li>
            <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
          </ul>
        </div>
      </nav>
      
      
      <div class="container-fluid">
      	<h2>Delete Food Item</h2>
      	<div class="container">
      		<form action="DeleteFoodHandler" method="POST">
      			<select name="item">
      				<c:forEach items = "${allFoods}" var="foodItem">
      					<option value="${foodItem.name}">${foodItem.name}</option>
      				</c:forEach>
      			</select>
      			<input type="submit" value="Delete selected food item" />
      		</form>
      	</div>
      </div>

</body>
</html>