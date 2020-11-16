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
	<link rel = "stylesheet" href = "mainPage.css">
    <title>Order</title>
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
	
	List<EnabledObject> objects = new ArrayList<>();
	List<Food> allFoods = (List)session.getAttribute("allFoods");
	Cart cart = (Cart)session.getAttribute("foodCart"); 
	for(Food f : allFoods){
		if(cart.cart.containsKey(f)){
			objects.add(new EnabledObject(f,1));
		}
		else{
			objects.add(new EnabledObject(f,0));
		}
	}

	session.setAttribute("cart", cart);
	
	session.setAttribute("objects", objects);
	
	
	System.out.println(objects.size());
	
	
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
      
      

<form action="orderHandler" method="POST">
      <div class = "container-fluid">
          <div class = "row" id="row1" style="display:flex; flex-wrap: wrap;">
          
          <c:forEach items = "${allFoods}" var="foodItem">
         
         
          <div class = "col-md-3" id="foodDisplay" style="margin-bottom: 30px">
                  <img src="${foodItem.url }" class="img-thumbnail" >
                  <div>${foodItem.name} ${foodItem.price}</div>
                  
                  <c:if test="${cart.getCart().get(foodItem) != null}">
                  	<c:out value="${cart.getCart().get(foodItem)}"></c:out>
                  </c:if>
                  
                  <div>
                  <input type = "submit" class = "btn btn-default" name="${foodItem.id}_add" value="+" ></input>
                  <c:forEach items="${objects}" var="object">
                  	<c:if test="${object.f.equals(foodItem) && object.getIsEnabled() == 1}" >
                  		<input type = "submit" class = "btn btn-default" name="${foodItem.id}_sub" value="-" ></input>
                  		
                  	</c:if>
                  </c:forEach>
                  
                  
                  </div>
              </div>
          
          
          </c:forEach>
          
          
          
          
              <%-- <div class = "col-md-3">
              
              <input type = "submit" class = "btn btn-default" name="${foodItem.id}_sub" value="-" ${object.isEnabled == 0 ? 'disabled="disabled"' : '' }></input>
              
              
                  <img src="media/pizza.jpeg" class="img-thumbnail" >
                  <div>Pizza</div>
                  
                  <div>
                  <input type = "submit" class = "btn btn-default" name="food1Add" value="+" ></input>
                  <input type = "submit" class = "btn btn-default" name="food1Sub" value="-" ${cart.food1==0 ? 'disabled="disabled"' : '' }></input>
                  </div>
              </div>
              <div class = "col-md-3">
                  <img src="./media/momos.jpeg" class="img-thumbnail" >
                  <div>Momos</div>
                    <div>
                    <input type = "submit" class = "btn btn-default" name="food2Add" value="+"></input>
                    <input type = "submit" class = "btn btn-default" name="food2Sub" value="-" ${cart.food2==0 ? 'disabled="disabled"' : '' } ></input>
                    </div>
              </div>
              <div class = "col-md-3">
                <img src="./media/sandwitch.jpeg" class="img-thumbnail" >
                    <div>Sandwitch</div>
                    <div>
                    <input type = "submit" class = "btn btn-default" name="food3Add" value="+"></input>
                    <input type = "submit" class = "btn btn-default" name="food3Sub" value="-" ${cart.food3==0 ? 'disabled="disabled"' : '' }></input>
                    </div>
              </div>
              <div class = "col-md-3">
                <img src="./media/burger.jpeg" class="img-thumbnail" >
                    <div>Burger</div>
                    <div>
                    <input type = "submit" class = "btn btn-default" name="food4Add" value="+"></input>
                    <input type = "submit" class = "btn btn-default" name="food4Sub" value="-" ${cart.food4==0 ? 'disabled="disabled"' : '' }></input>
                    </div>
              </div>
          </div> --%>
      </div> 
      
      <div class="container" id="orderFinalDiv">
      	<input type="submit" class="btn btn-primary" name="finalize" value="Order Now" />
      </div>
      
</form>






    
    
    
    
    
    
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>