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
			<h2>Friend List</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add Student -->

			<table>

				<tr>
					<th>First Name</th>
					<th>Address</th>
					<th>Email</th>
				
				</tr>

				<c:forEach var="tempFriend" items="${Friend_List}">
				
					<!-- set up a link for each friend use verify to direct the user to "see - user- profile
					a friend is just a user, so I use user.id to act as authorId so that i can use verify to load the see-use-profile page for me. 
					  -->
					<c:url var="verifyFriendLink" value="UserControlServlet">
						<c:param name="action" value="verifyFriend" />
						<c:param name="authorId" value="${tempFriend.id}" />
					</c:url>

	


					<!--  set up a link to delete a friend -->
					<c:url var="deleteFriendLink" value="UserControlServlet">
						<c:param name="action" value="deleteFriend" />
						<c:param name="authorId" value="${tempFriend.id}" />
					</c:url>
	
					<tr>
						<td><nav class="cl-effect-16">
					<a href="${verifyFriendLink}">${tempFriend.fullName}</a>
						</nav></td>
						<td>${tempFriend.address }</td>
						<td>${tempFriend.account }</td>
					
					<td> 
					
						
						
							<nav class="cl-effect-15">
					<a href="${deleteFriendLink}">
						Unfriend</a>
						</nav>
						
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


