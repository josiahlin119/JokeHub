<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Review the Joke</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
<link rel="stylesheet" href='css/component.css'>

<style>
h3 {
	display: inline-block;
}
</style>
</head>
<!-- Same thing for the joke, if already exists in your favorite list, then you have a button to delete it, otherwise, add it -->



<c:url var="addFavoriteJokeLink" value="UserControlServlet">
	<c:param name="action" value="addFavoriteJoke" />
	<c:param name="jokeId" value="${joke.id}" />
</c:url>


<c:url var="deleteFavoriteJokeLink" value="UserControlServlet">
	<c:param name="action" value="deleteFavoriteJoke" />
	<c:param name="jokeId" value="${joke.id}" />
</c:url>




<nav class="cl-effect-4">
<c:if test="${isFavorite=='No'}"> 
	<h3>
		<a href="${addFavoriteJokeLink}"> Add This Joke to Your List</a>
	</h3>
</c:if>


<c:if test="${isFavorite=='Yes'}">

	<h3>
		<a href="${deleteFavoriteJokeLink}"> Remove This Joke from Your List</a>
	</h3>
</c:if>
	<h3>
		<a href="homepage.jsp">Back to Homepage</a>
	</h3>





	



</nav>




<body>








	<%
		//out.print(request.getSession().getAttribute("id"));
	%>


	<div id="wrapper">
		<div id="header">
			<h2>Joke World</h2>
		</div>
	</div>


	<form action="UserControlServlet" method="GET">

		<input type="hidden" name="action" value="review" /> <input
			type="hidden" name="jokeId" value="${joke.id}" />

		<table>
			<tbody>
				<tr>

					<th>Title</th>
					<th>Author</th>
					<th>Description</th>
					<th>Content</th>

				</tr>

				<tr>
					<td>${joke.title}</td>
					<td>${joke.authorName}</td>
					<td>${joke.description }</td>
					<td>${joke.content}</td>

				</tr>



				<tr>
			</tbody>
		</table>

		<!-- loop though all the reviews exist -->
		<div id="WriteReview">
			<h3>Write Your Own Review</h3>
		</div>
		Give a raiting <select name="rating">
			<option>Excellent</option>
			<option>Good</option>
			<option>Fair</option>
			<option>Poor</option>



		</select> <br /> <br /> Leave Your Comment</label>
		<tr>
			</td>
			<input type="text" name="comment" />
			</td>
		</tr>

		<td><input type="submit" value="Save" class="save" /></td>

	</form>





	<table>




		<div id="reviewSection">
			<h3>Reviews in the past</h3>
		</div>

		<c:forEach var="tempReview" items="${reviews}">


			<ul style="list-style-type: circle;">
				<li>Date: ${tempReview.create_at}</li>
				<li>Reviewer: ${tempReview.reviewer }</li>
				<li>Rating: ${tempReview.rating }</li>
				<li>Comment: ${tempReview.comment }</li>

			</ul>
		</c:forEach>






	</table>



	<div style="clear: both;"></div>

	<p>
	<form>
		<input type="button" value="Go back!" onclick="history.back()">
	</form>

	</p>

	</div>
</body>

</html>