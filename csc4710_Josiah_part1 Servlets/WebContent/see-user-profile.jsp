



<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Student Tracker App</title>

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


	<%
		Users theUser = (Users) request.getSession().getAttribute("theUser");
		out.print(theUser.getFirstName() + theUser.getLastName() + "<br/>" + "<br/>");
	%>




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


	<%
		String isFriend = (String) request.getSession().getAttribute("isFriend");
		if (isFriend == "Yes") {
			out.print("is your friend");
	%>

	<a href="${deleteFriendLink}"> Unfriend</a>

	<%
		} else {
			out.print(" is not your friend");
	%>
	<a href="${addFriendLink}"> Add as a friend</a>


	<%
		}
	%>

	<h2>The Author's Profile</h2>
	<%
		out.print("Age    :" + theUser.getAge() + "<br/> <br/>");
		out.print("Account:" + theUser.getAccount() + "<br/> <br/>");
		out.print("Address: " + theUser.getAddress() + "<br/> <br/>");
	%>

	<c:if test=" ${isFriend== 'Yes'}">

	</c:if>

	<c:if test=" ${isFriend=='No'}">
		<a href="${addFriendLink}"> Add This Friend</a>
	</c:if>

	
	<a href="homepage.jsp">  Home</a>

</body>

</html>