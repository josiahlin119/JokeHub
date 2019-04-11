<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>
<jsp:include page="staticDesignForInput.jsp" />
<!DOCTYPE html>
<html>

<head>
<title>Review the Joke</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
<link rel="stylesheet" href='css/component.css'>

<style>

a {text-decoration: none; }

h4 {
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
					<td>     <c:if test="${isFavorite=='No'}"> 
	<h3>
		<a href="${addFavoriteJokeLink}" style= "color:#FF1493 " class = "button1"> Like</a>
	</h3>
</c:if>

								<c:if test="${isFavorite =='Yes'}">
								<h3>
									<a href="${deleteFavoriteJokeLink}" style= "color:#FF1493" class ="button1"
										>
										Unlike</a>
										
										</h3>
								</c:if></td>
				</tr>

				

				<tr>
				
		
				
				</tr>
			</tbody>
		</table>

		<!-- loop though all the reviews exist -->
		<div id="WriteReview">
			<h3>Write Your Own Review</h3>
			
				

						

		</div>
		Give a Score <select name="rating">
			<option>Excellent</option>
			<option>Good</option>
			<option>Fair</option>
			<option>Poor</option>
	

		</select> 
	
		
		<br />  Leave Your Comment</label>
		
		
		
		<tr>
			</td>
			<input type="text" name="comment" />
			</td>
		</tr>

		<td><input type="submit" value="Save" class="save" /></td>

	</form>





	<table>
	
      <c:if test="${warning2 !=null }">
<font size="3" color="blue"> 
			Warning! You cannot review more than five Jokes per day</font>
	</c:if>
	
	     
		<c:if test="${warning3 !=null }">
	
	<font size="3" color="blue"> 
			 You have already reviewed this joke</font>
	</c:if>


		<div id="reviewSection">
			<h3>Reviews in the past</h3>
		</div>
		
			<c:url var="loadReview" value="UserControlServlet">
						<c:param name="action" value="loadCurrentUserReview" />
						<c:param name="jokeId" value="${joke.id}" />
					</c:url>

		<c:forEach var="tempReview" items="${reviews}">
			
			<ul style="list-style-type: circle;">
				<li>Date: ${tempReview.create_at}</li>
				<li>Reviewer: ${tempReview.reviewer }</li>
				<li>Rating: ${tempReview.rating }  
				<c:if test="${tempReview.commenter_id == id }">  
				
				<a href ="${loadReview}" > <font size="3" color="#4169E1"> &nbsp  &nbsp  &nbsp  Edit Your Comment</font> </a>
			
				</c:if>
			
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