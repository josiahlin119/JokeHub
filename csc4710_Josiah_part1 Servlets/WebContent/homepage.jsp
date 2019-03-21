<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
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

	<%
		String account = request.getSession().getAttribute("firstName").toString();
		out.println("Hello" + account);
		out.println(request.getSession().getId());
	%>


	<!-- 
	<form action="UserControlServlet" method="post">
		 <input
			type="text" name="tag"> <br /> <br /> <br /> <br /> <input
			type="submit" value="search" class="save" /> <br /> <br />
	</form>
	
	 -->

	<div id="cover">
		<form method="Post" action="UserControlServlet">
			<input type="hidden" name="action" value="retrieveJokesWithTag" />
			<div class="tb">
				<div class="td">
					<input type="text" name="tag" placeholder="Tag" required>
				</div>
				<div class="td" id="s-cover">
					<button type="submit" value="search">
						<div id="s-circle"></div>
						<span></span>
					</button>
				</div>
			</div>
		</form>
	</div>


	<br />
	<br />

	<!-- The id of the session user is hidden in request so i dont need to explicity add it here -->

	<c:url var="loadFriendsLink" value="UserControlServlet">
		<c:param name="action" value="loadFriends" />
	</c:url>

	<c:url var="loadJokesLink" value="UserControlServlet">
		<c:param name="action" value="loadJokes" />
	</c:url>



	<nav class="cl-effect-13">
		<div id="right">
			<a href="logout.jsp"> Log Out</a>
		</div>
	</nav>



	<nav class="cl-effect-1">

		<a href="post-jokes.jsp"> Go post your own jokes</a> 
		<a
			href="${loadFriendsLink}">Friend List</a> 
			
			<a href="${loadJokesLink}">
			View Your Favoirte Jokes</a>


	</nav>

	<br />
	<br />
	<br />

	<c:if test="${theJoke != null}">
		<font size="6" color="red">You have successfully posted your
			joke</font>
	</c:if>



</body>



</html>


<!-- Step 1: use Interface and we need to prepopulate the form of all our 
favorite friends and jokes -->

<!-- Step 2: give two bottons, one for adding new friend while another one is for adding new favorite jokes  -->

<!-- <body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
				
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				
				<c:forEach var="friend" items="${FRIENDS_LIST}">
					
					<tr>
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName} </td>
						<td> ${tempStudent.email} </td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>Step3: give two bottons for deleting operation, referencing to CRUD.   -->
