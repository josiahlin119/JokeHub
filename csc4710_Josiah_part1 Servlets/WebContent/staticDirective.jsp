<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>

<meta charset="UTF-8">
<title>Josiah's Joke Hub</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Nunito'>
<link rel="stylesheet" href='css/searchBar.css'>
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
  color:#FFD700;
  text-align: center;
  -webkit-animation: glow 1s ease-in-out infinite alternate;
  -moz-animation: glow 1s ease-in-out infinite alternate;
  animation: glow 1s ease-in-out infinite alternate;
}

@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px #40E0D0, 0 0 40px #40E0D0, 0 0 50px #40E0D0, 0 0 60px #40E0D0, 0 0 80px #40E0D0;
  }
  
  to {
    text-shadow: 0 0 20px #fff, 0 0 30px #00CED1, 0 0 40px #00CED1, 0 0 50px #00CED1, 0 0 60px #00CED1, 0 0 70px #00CED1, 0 0 90px #00CED1;
  }
}
</style>

<h2 >
<font face="verdana" color="mediumblue">
	<%
		String account = request.getSession().getAttribute("firstName").toString();
		out.println("Hello, " + account);
	%>
</font>
</h2>




     






<h3 class="glow">Joke World </h3>
<body>

	<c:url var="loadFriendsLink" value="UserControlServlet">
		<c:param name="action" value="loadFriends" />
	</c:url>

	<c:url var="loadJokesLink" value="UserControlServlet">
		<c:param name="action" value="loadJokes" />
	</c:url>

	<c:url var="logoutLink" value ="UserControlServlet">
	
	<c:param name="action" value = "logout"/>
	
	</c:url>

	<nav class="cl-effect-13">
		<div id="right">
			<a href= "${logoutLink }"> Log Out</a>
		</div>
	</nav>



	<nav class="cl-effect-1">

		<a href="post-jokes.jsp"> Post </a> 
		<a
			href="${loadFriendsLink}">Friends</a> 
			
			<a href="${loadJokesLink}">
			My Favorites</a>
<a href="homepage.jsp">Home</a>

	
	
	</body>
	</html>