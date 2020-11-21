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
	<link rel="stylesheet" href="register.css">
	
    <title>Login</title>

</head>
<body>
<nav class="navbar navbar-inverse">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="home.jsp">Daily Grill</a>
          </div>
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Register</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
          </ul>
        </div>
      </nav>


<form action="Register" method="POST">
<div class="form-group">
    <label for="Username">Username</label>
    <input type="text" class="form-control" name="Username" id="Username" placeholder="Enter Username" required>
  </div>
  <div class="form-group">
    <label for="email">Email address</label>
    <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email" required>
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" name="password" id="password" placeholder="Password" minlength="6" required>
  </div>
  <div class="form-group">
    <label for="Address">Address</label>
    <input type="text" class="form-control" name="Address" id="Address" placeholder="Address" required>
  </div>
  <div class="form-group">
    <label for="contact">Contact number</label>
    <input type="text" class="form-control" name="contact" id="contact" pattern="[0-9]+" maxlenght="10" placeholder="Contact Number" required>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
</html>