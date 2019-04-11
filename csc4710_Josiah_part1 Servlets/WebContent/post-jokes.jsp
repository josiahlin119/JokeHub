
<jsp:include page="staticDesignForInput.jsp" />

<!DOCTYPE html>
<html>

<head>
<title>Post Your Own Jokes</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

<link rel = "stylesheet" href ='css/component.css'>

</head>


<body>
	<div id="wrapper">
		<div id="header">
			<h2>Your Joke Time</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			<h3>Post Your Joke Today</h3>

			<form action="UserControlServlet" method="Post">
				<input type="hidden" name="action" value="postJoke" />

				<table>
					<tbody>
						<tr>

							<td><label>Title</label></td>
							<td><input type="text" name="title" ></td>

						</tr>

						<tr>

							<td><label>Description</label></td>
							<td><input type="text" name="description"></td>

						</tr>
						
						<tr>

							<td><label>Tag</label></td>
							<td><input type="text" name="tag"></td>

						</tr>
						

						<tr>
							<td><label> Content</label></td>
							<td><textarea rows="20" cols="100" id="content" name="content">
							</textarea>
							<td />

						</tr>

						<tr>
							<td><label></label></td>
							<td><input type="submit" value="post" class="save" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			



		</div>

	</div>
	

</body>
</html>
