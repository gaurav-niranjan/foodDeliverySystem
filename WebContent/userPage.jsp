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
	<link rel="stylesheet" href="userPage.css">
	
    <title>Profile</title>

</head>
<body>

<%
response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
//HTTP 1.1	

response.setHeader("Pragma","no-cache"); //HTTP 1.0

response.setHeader("Expires","0"); //Proxies

	if(session.getAttribute("usermail") == null){
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
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<c:if test="${usermail!=null}">
            	<li><a href="userPage.jsp"><span class="glyphicon glyphicon-user"></span> ${customerObj.getCustomer_name()}</a></li>
            </c:if>
            <c:if test="${usermail!=null}" >
            	<li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </c:if>
          </ul>
        </div>
      </nav>
      
      
      <div class="container bootstrap snippets">
<div class="row">
    <div class="col-lg-3 col-md-3 col-sm-4">
        <div class="panel rounded shadow">
            <div class="panel-body">
                <div class="inner-all">
                    <ul class="list-unstyled">
                        <li class="text-center">
                            <img data-no-retina="" class="img-circle img-responsive img-bordered-primary" src="https://image.flaticon.com/icons/svg/1177/1177568.svg" alt="John Doe">
                        </li>
                        <li class="text-center">
                            <h4 class="text-capitalize">${customerObj.getCustomer_name()}</h4>
                            
                        </li>
                        <li>
                            
                        </li>
                        <li><br></li>
                        <li>
                            <div class="btn-group-vertical btn-block">
                                <a href="userPage.jsp" class="btn btn-default"><i class="fa fa-cog pull-right"></i>Edit Account</a>
                                <a href="Logout" class="btn btn-default"><i class="fa fa-sign-out pull-right"></i>Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div><!-- /.panel -->

        <div class="panel panel-theme rounded shadow">
            <div class="panel-heading">
                <div class="pull-left">
                    <h3 class="panel-title">Contact</h3>
                </div>
                <div class="clearfix"></div>
            </div><!-- /.panel-heading -->
            <div class="panel-body no-padding rounded">
                <ul class="list-group no-margin">
                    <li class="list-group-item"><i class="fa fa-envelope mr-5"></i> ${customerObj.getCustomer_email()}</li>
                    <li class="list-group-item"><i class="fa fa-map-marker"></i> ${customerObj.getCustomer_address()}</li>
                    <li class="list-group-item"><i class="fa fa-phone mr-5"></i> ${customerObj.getCustomer_contact()}</li>
                </ul>
            </div><!-- /.panel-body -->
        </div><!-- /.panel -->

    </div>
    <div class="col-lg-9 col-md-9 col-sm-9">
        <div class="row caixinha1">
          <div id="chartHigh">
            <div class="container-fluid">
		      	<ul class="list-group">
				  <li class="list-group-item"><a href="OrderHistoryHandler">View Order History</a></li>
				  <li class="list-group-item"><a href="changeAddress.jsp">Change Address</a></li>
				  <li class="list-group-item"><a href="changeContact.jsp">Change Contact number</a></li>
				</ul>
	       </div>
          <div>
        </div>
        <div class="row caixinha2">
      		
        </div>
    </div>
    <div class="col-lg-9 col-md-9 col-sm-8"> 
    </div>
	</div>
</div>
      
      




<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</body>
</html>