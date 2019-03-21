<%@ page import="java.util.*, csc4710_Josiah_part1.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>


<head>


<title>Josiah's Administrator Page</title>
</head>
<link type="text/css" rel="stylesheet" href="css/style.css">
<%
	List<Users> theUsers = (List<Users>) request.getAttribute("USER_LIST");
%>

<body>
<div align ="center">

	<div id="wrapper">
		<div id="header">
			<h2>Josiah's User Management System</h2>

			<div id="container">

				<div id="content">

					<table>

						<tr>
							<th>Account</th>
							<th>Address</th>
							<th>Age</th>
							<th>Status</th>

						</tr>
						<%
							for (Users temp : theUsers) {
								
						%>

						<tr>
							<td><%=temp.getAccount()%></td>
							<td><%=temp.getAddress()%></td>
							<td><%=temp.getAge()%></td>
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
					<a href = "${tempLink}">Initialize</a>
					 </form>
					 
					 
				
				</div>
			</div>
		</div>
	</div>
</div>
</body>


</html>