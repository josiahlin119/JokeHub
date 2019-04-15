<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>


<head>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href='css/searchBar.css'>
<link rel="stylesheet" href='css/component.css'>
<title>Josiah's Administrator Page</title>



<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  background-color: pink;
  font-family: cursive;
}

.glow {
  font-size: 80px;
  color: #fff;
  text-align: center;
  -webkit-animation: glow 1s ease-in-out infinite alternate;
  -moz-animation: glow 1s ease-in-out infinite alternate;
  animation: glow 1s ease-in-out infinite alternate;
}

@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px #e60073, 0 0 40px #e60073, 0 0 50px #e60073, 0 0 60px #e60073, 0 0 70px #e60073;
  }
  
  to {
    text-shadow: 0 0 20px #fff, 0 0 30px #ff4da6, 0 0 40px #ff4da6, 0 0 50px #ff4da6, 0 0 60px #ff4da6, 0 0 70px #ff4da6, 0 0 80px #ff4da6;
  }
}
</style>

<h2 class="glow">Josiah's User Management System </h2>
<c:url var="logoutLink" value ="UserControlServlet">
	
	<c:param name="action" value = "logout"/>
	
	</c:url>
	


	<nav class="cl-effect-13">
		<div id="right">
			<a href= "${logoutLink }"> Log Out</a>
		</div>
	</nav>
	

	
</head>

<%
	List<Users> theUsers = (List<Users>) session.getAttribute("USER_LIST");
%>

<body>
<div align ="center">

	<div id="wrapper">
		<div id="header">
			

			<div id="container">

				<div id="content">

					<table>
                      

						<tr>
							<th>Name </th>
							<th>Account</th>
							<th>Address</th>
							<th>Age</th>
							<th>Gender</th>
							<th>Status</th>

						</tr>
						<%
							for (Users temp : theUsers) {
								
						%>

						<tr>
						<td> <%=temp.getFirstName()  %> <%=temp.getLastName() %> </td>
							<td><%=temp.getAccount()%></td>
							<td><%=temp.getAddress()%></td>
							<td><%=temp.getAge()%></td>
							<td><%=temp.getGender() %></td>
							<td><%= temp.getStatus()%></td>
							
							<%
								}
							%>
					</table>
					<c:url var= "tempLink" value ="UserControlServlet">
					
					<c:param name ="action" value = "initialize"/>
					
					
					</c:url>
					
				
					</br>
					</br>
					<nav class="cl-effect-17">
					<a href="DataManagement.jsp"> Data  </a>
					<a href = "${tempLink}">Initialize</a>
					</nav>
					 </form>
					 
					 
				
				</div>
			</div>
		</div>
	</div>
</div>
</body>


</html>