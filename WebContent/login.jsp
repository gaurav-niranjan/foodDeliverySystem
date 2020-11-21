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
	<link rel="stylesheet" href="login.css">
	
    <title>Login</title>

</head>
<body>
<%
response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
//HTTP 1.1	

response.setHeader("Pragma","no-cache"); //HTTP 1.0

response.setHeader("Expires","0"); //Proxies

	if(session.getAttribute("usermail") != null){
		response.sendRedirect("home.jsp");
		return;
	}


%>


<nav class="navbar navbar-inverse">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="home.jsp">Daily Grill</a>
          </div>
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Login</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="adminLogin.jsp"><span class="glyphicon glyphicon-cog"></span> Admin Login</a></li>
            <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
          </ul>
        </div>
      </nav>
      
      
      
      <div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue</h1>
            <div class="account-wall">
                <img class="profile-img" src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_account_circle_48px-512.png"
                    alt="">
                <form class="form-signin" action="Login" method="POST">
                <input type="text" class="form-control" name="email" placeholder="Email" required autofocus >
                <input type="password" class="form-control" name="pass" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Sign in</button>
                
                <a href="contactUs.jsp" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
                </form>
            </div>
            <a href="register.jsp" class="text-center new-account">Create an account </a>
        </div>
    	</div>
	</div>
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</body>
</html>