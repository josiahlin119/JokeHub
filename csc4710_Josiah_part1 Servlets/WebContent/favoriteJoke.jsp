<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Joke's World</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel='stylesheet'
	href='https://fonts.googleapis.com/css?family=Nunito'>
<link rel="stylesheet" href='css/searchBar.css'>
<link rel="stylesheet" href='css/component.css'>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Joke List</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add Student -->

			<table>

				<tr>
					<th>Title</th>
					<th>Author</th>
					<th>Description</th>
					<th>Content</th>
				</tr>

				<c:forEach var="tempJoke" items="${Joke_List}">

					<!-- set up a link for each friend use verify to direct the user to "see - user- profile
					a friend is just a user, so I use user.id to act as authorId so that i can use verify to load the see-use-profile page for me. 
					  -->
					<c:url var="tempLink" value="UserControlServlet">
						<c:param name="action" value="load" />
						<c:param name="jokeId" value="${tempJoke.id}" />
					</c:url>

					<c:url var="verifyFriendLink" value="UserControlServlet">
						<c:param name="action" value="verifyFriend" />
						<c:param name="authorId" value="${tempJoke.authorId}" />
					</c:url>

					<c:url var="deleteFavoriteJokeLink" value="UserControlServlet">
						<c:param name="action" value="deleteFavoriteJoke" />
						<c:param name="jokeId" value="${tempJoke.id}" />
					</c:url>


					<tr>
						<td>${tempJoke.title}</td>
						<td>${tempJoke.authorName}</td>
						<td>${tempJoke.description }</td>
						<td>${tempJoke.content}</td>

						<td><a href="${tempLink}">review</a></td>
						<!-- I need to verify whether this author is current user's friend or not then forward his information to the "see-user-profile.jsp -->
						<td><a href="${verifyFriendLink}"> See Author' Profile</a></td>


						<td>
							
						



						</td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>

	<form>
		<input type="button" value="Go back!" onclick="history.back()">
	</form>
</body>


</html>
