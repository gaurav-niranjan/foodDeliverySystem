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
    <link rel = "stylesheet" href = "customerFound.css">
    
    <title>Customer Details</title>
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
            <a class="navbar-brand" href="home.jsp">Daily Grill</a>
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
      
     
      
      <div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
                <img class="profile-img" src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_account_circle_48px-512.png"
                    alt="">
                <div class="center">
				      <span>Name:</span> ${customerObj.getCustomer_name() }<br>
				      <span>Customer ID:</span> ${customerObj.getCustomer_id() } <br>
				      <span>Address:</span> ${customerObj.getCustomer_address() } <br>
				      <span>Email:</span> ${customerObj.getCustomer_email() } <br>
				      <span>Contact no:</span> ${customerObj.getCustomer_contact() } <br>
		        </div>
            </div>
        </div>
    	</div>
	</div>
	
	<div class="container-fluid" id="listContainer">
   			Recent orders:
			<ul class="list-group">
			<c:forEach items="${customerLogins}" var="entry">
				
	  				<li class="list-group-item">
	    				<span class="badge">${entry.value }</span>
	    					OrderID: ${entry.key}
	  				</li>
   				
   				
			</c:forEach>
			</ul>
			<hr class="solid">
   </div>
      

</body>
</html>