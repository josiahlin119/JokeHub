<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>

<meta charset="UTF-8">
<title>Josiah's Joke Hub</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Nunito'>

<link rel="stylesheet" href='css/component.css'>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  background-color: pink;
  font-family: cursive;
}

.glow {
  font-size: 50px;
  color: #fff;
  text-align: center;
  -webkit-animation: glow 1s ease-in-out infinite alternate;
  -moz-animation: glow 1s ease-in-out infinite alternate;
  animation: glow 1s ease-in-out infinite alternate;
}

@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px #e60073, 0 0 40px #e60073, 0 0 50px #e60073, 0 0 60px #e60073, 0 0 70px #e60073;
  }
  
  to {
    text-shadow: 0 0 20px #fff, 0 0 30px #ff4da6, 0 0 40px #ff4da6, 0 0 50px #ff4da6, 0 0 60px #ff4da6, 0 0 70px #ff4da6, 0 0 80px #ff4da6;
  }
}
</style>


<h3 class="glow">Data Management </h3>
<body>

	<c:url var="logoutLink" value ="UserControlServlet">
	
	<c:param name="action" value = "logout"/>
	
	</c:url>

	<nav class="cl-effect-13">
		<div id="right">
			<a href= "${logoutLink }"> Log Out</a>
		</div>
	</nav>



	
	</body>
	</html>