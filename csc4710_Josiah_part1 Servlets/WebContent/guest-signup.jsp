<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>Josiah's Joke Website Login Page</title>
</head>



<link type="text/css" rel="stylesheet" href="css/style.css">
<body>

<!-- 
				boolean checkNullPointer(ArrayList attributeList) {
					for(int i = 0;  i < attributes.length;i++) {
						if((attributes[i] == null) ||( attributes[i].split("")==null)){
							return true;
						}
					}
					
					return false;
					
				}
				 -->
		
	<div align="center">
		<div id="wrapper">
			<div id="header">                               
				<h2>Sign up form</h2>

				<div id="container">

					<div id="content">
				
						<form action="UserControlServlet" method="post">
							<input type="hidden" name="action" value="new" required/>
							 Your First Name: <input type= "text" name = "firstName"required/> <br/><br/>
							Your LastName : <input type ="text" name = "lastName" required/> <br/><br/>
							Your Address: <input type="text" name ="address" required/> <br/><br/>
							Gender: <input type ="radio" name = "gender" value ="male"/> male
									<input type = "radio" name ="gender" value ="female"/> female<br/><br/>
							Your birthday: <input type="date" name ="birthday"required/> <br/><br/>
							
							Please provide your email:
								<input
								type="text" name="account" required/> <br/> <br/>
								
								 Create a safe password <input
								type="password" name="password" required/>
								<!---->
								 <br /> <br />
								<input type="submit" value="Sign up" class="save" /> <br />
								<br />
						</form>
						<div style="clear: both;"></div>
						<p>
						<h3>
						
						
							<c:if test="${user !=null }">You already have an account, Go to sign in 
						
						<input type="button" value="Sign In" 
									onclick="window.location.href='login-form.jsp';return false;"
									class="add-student-button" />

							</c:if>
							
							
						</h3>


	<input type="button" value="Back to Sign" 
									onclick="window.location.href='login-form.jsp';return false;"
									class="add-student-button" />

						

					</div>
				</div>
</body>

</html>
