

<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>
<jsp:include page="staticDirective.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>User's Information</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href='css/searchBar.css'>
<link rel="stylesheet" href='css/component.css'>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 300px;
  margin: auto;
  text-align: center;
  font-family: arial;
}

.title {
  color: grey;
  font-size: 18px;
}

button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 18px;
}

a {
  text-decoration: none;
  font-size: 22px;
  color: black;
}

button:hover, a:hover {
  opacity: 0.7;
}
</style>
<body>

<center>
										
	




	<!-- I need to make sure giving wrong button to the current user. if the guy whose information the current user is check has been added to his
friend list, i give the user an 'unfriend' button, otherwise 'add friend'. I will set an attribute of user type to this jsp. if it is null, then add, if is not null then unfriend. 

 -->



	<c:url var="addFriendLink" value="UserControlServlet">
		<c:param name="action" value="addFriend" />
		<c:param name="authorId" value="${authorId}" />
	</c:url>

	<c:url var="deleteFriendLink" value="UserControlServlet">
		<c:param name="action" value="deleteFriend" />
		<c:param name="authorId" value="${authorId}" />
	</c:url>

<div class="card">
<h2 style= " color: 	darkturquoise" ><%
		Users theUser = (Users) request.getSession().getAttribute("theUser");
		out.print(theUser.getFirstName() + " " + theUser.getLastName() + "<br/>" + "<br/>");
	%></h2>


	<%
		String isFriend = (String) request.getSession().getAttribute("isFriend");
		if (isFriend == "Yes") {
			
	%>
<nav class="cl-effect-17">
	<a href="${deleteFriendLink}"> Unfriend</a>
	
</nav>
	<%
		} else {
	
	%>
	<nav class="cl-effect-17">
	<a href="${addFriendLink}"> Add as a friend</a>
</nav>
	<%
		}
	%>

	<p>
	<%
		out.print("Age    :" + theUser.getAge() + "<br/> <br/>");
		out.print("Account:" + theUser.getAccount() + "<br/> <br/>");
		out.print("Address: " + theUser.getAddress() + "<br/> <br/>");
	%>

</p>
	<c:if test=" ${isFriend== 'Yes'}">

	</c:if>


	<c:if test=" ${isFriend=='No'}">
		<a href="${addFriendLink}"> Add This Friend</a>
	</c:if>




	
	</div>
</center>
 
</body>

<form>
		
	
	</form>
</html>