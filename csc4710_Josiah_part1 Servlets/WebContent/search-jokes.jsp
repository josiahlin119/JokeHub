
<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>

<jsp:include page="staticDirective.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Joke Search Results</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href='css/searchBar.css'>
<link rel="stylesheet" href='css/component.css'>
<style>
a {
	color: #28a2ee;
} /* CSS link color */
</style>
</head>

<body>




	<div id="wrapper">
		<div id="header">
			<h2>Jokes With Input Tag</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<table>

				<tr>
					<th>Title</th>
					<th>Author</th>
					<th>Description</th>
					<th>Content</th>

				</tr>

				<c:forEach var="tempJoke" items="${jokes}">

					<!-- Set up a link for each joke so that
				other users can review each joke and give rating -->
					<c:url var="tempLink" value="UserControlServlet">
						<c:param name="action" value="load" />
						<c:param name="jokeId" value="${tempJoke.id}" />
					</c:url>
					
					<c:url var="verifyFriendLink" value="UserControlServlet">
						<c:param name="action" value="verifyFriend" />
						<c:param name="authorId" value="${tempJoke.authorId}" />
</c:url>



					<tr>
						<td>${tempJoke.title}</td>
						<td>${tempJoke.authorName}</td>
						<td>${tempJoke.description }</td>
						<td>${tempJoke.content}</td>
										
					<td> 	<nav class="cl-effect-21">	<a href="${tempLink}">Review</a>  </nav></td>
						<!-- I need to verify whether this author is current user's friend or not then forward his information to the "see-user-profile.jsp -->
						<td> <nav class ="cl-effect-21"> <a href="${verifyFriendLink}"> See Author' Profile</a> </nav></td>



					</tr>

				</c:forEach>

			</table>

		</div>

	</div>





</body>


</html>

