<%@ page import="java.util.*"%>
<jsp:include page="staticDirective.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<body>

	<!-- Step 1 create HTML form -->


	<form action="user-favoriteJokes.jsp">

		Add new jokes:<input type="text" name="newJokes" /> <input
			type="submit" value="Add" />
		
	</form>
	
	<!-- Step 2 add new item -->
	<%

		List<String> myJokes = (List<String>) session.getAttribute("myFavoriteJokeList");

		if (myJokes == null) {
			myJokes = new ArrayList<String>();
			session.setAttribute("myFavoriteJokeList", myJokes);

		}

		String newJokes = request.getParameter("newJokes");
		if ((newJokes != null)&& (!newJokes.trim().equals(""))) {
			myJokes.add(newJokes);
		}
	%>

	<!-- Display my FavoriteJokeList -->
	<hr>
	<b>My Favorite Joke List</b>
	<br />

	<ol>
	
	<c:forEach var= "temp" items = "${myJokes}"   >
	
	<tr>
		<td> ${temp} </td>
	</c:forEach>
	


	</ol>
</body>
</html>