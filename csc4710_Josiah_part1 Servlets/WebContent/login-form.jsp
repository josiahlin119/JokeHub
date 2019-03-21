<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>Josiah's Joke Website Login Page</title>
</head>



<link type="text/css" rel="stylesheet" href="css/style.css">
<body>
	<div align="center">
		<div id="wrapper">
			<div id="header">
				<h2>Login form</h2>

				<div id="container">

					<div id="content">
					
						<form action="UserControlServlet" method="post">
								<input type = "hidden" name = "action" value = "login"/>
							user account: <input type="text" name="account" /> <br /> <br />
							your password: <input type="password" name="password" /> <br /> <br />
							<!--  
<select name = "collegeYear">
<option>Fresh Man </option>
<option>Sophomore </option>
<option>Junior </option>
<option>Senior </option>
</select>

-->
							<br /> <br /> <input type="submit" value="Login" class ="save" /> <br /> <br />
</form>
<div style = "clear:both;"> </div>
<p>

							<h3>You do not have an account? Click to sign up</h3>

							<input type="button" value="Sign up"
								onclick="window.location.href='guest-signup.jsp';return false;"
								class="add-student-button" />
					</div>
				</div>
				
</body>

</html>











<!-- <html>
<head>
<title>Login page

</title></head>

<body>You login successfully: ${param.userAccount} ${param.password}

<br/><br/>

Student Year: ${param.collegeYear}
</body>
</html> 

-->