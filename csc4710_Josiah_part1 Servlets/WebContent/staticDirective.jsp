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

<body>
<h1 >
<font face="verdana" color="mediumblue">
	<%
		String account = request.getSession().getAttribute("firstName").toString();
		out.println("Hello, " + account);
	%>
</font>
</h1>

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