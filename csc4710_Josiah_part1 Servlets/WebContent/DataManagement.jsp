<jsp:include page="AdminStaticDesign.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href='css/popup.css'>
<link rel="stylesheet" href="css/fullScreenSearch.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>


	<!-- Here is the overlay searching button -->
	<div id="myOverlay" class="overlay">
		<span class="closebtn" onclick="closeSearch()" title="Close Overlay">x</span>
		<div class="overlay-content">
			<form action="DataManagement.jsp" method="Get">
				<input type="text" placeholder="Task #..." name="Load">


				<button type="submit">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>
	</div>
	<!-- if we go back home we have to reload the admin page with attribute values -->
	<c:url var="loadAdminPage" value="UserControlServlet">
		<c:param name="action" value="loadAdminPage" />
	</c:url>
	<nav class="cl-effect-6">


		<a href="${loadAdminPage}" style="color: #FF1493">Home</a>

	</nav>

	<h2>Task 1</h2>
	<p>List the users who posted at least two jokes that are posted on
		the same day, one has a tag of X and another has tag of Y</p>
	<p>Note that the button and the form is fixed - they will always be
		positioned to the bottom of the browser window.</p>

	<!--  Here is to output the result of the first Functionality -->

	<h2>Task 2</h2>
	<p>List all the jokes posted by user X, such that all the comments
		are "Excellent" or "good" for these jokes.</p>

	<br />


	<p>Click on the button to open the search box.</p>
	<button class="openBtn" onclick="openSearch()">Display
		the task according to the manual</button>
	<br />
	<br />


	<%
		String taskId = request.getParameter("Load");

		if (taskId == null) {
			taskId = (String) session.getAttribute("taskId");
		} 
		if(taskId ==null){
			taskId= "1";
		}
		else {
			session.setAttribute("taskId", taskId);
			taskId = (String) session.getAttribute("taskId");
		}

	
		System.out.println("111111111" + taskId);
	%>
	<%
		if (taskId.equals("2")) {
	%>

	<button class="open-button" onclick="openForm()">Task 2</button>

	<div class="form-popup" id="task">
		<form action="UserControlServlet" method="Get" class="form-container">
			<input type="hidden" name="action" value="task2" /> <label
				for="UserX"><b>AuthorName</b></label> <input type="text"
				placeholder="UserName" name="UserX" required>

			<button type="submit" class="btn">Comfirm</button>

			<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
		</form>
	</div>


	<table>
		<tr>
			<th>Joke Title</th>
			<th>Author Name</th>
			<th>Description</th>
			<th>Content</th>
			<th>Create_At</th>
		</tr>

		<c:forEach var="tempJoke" items="${jokeListFunc2}">
			<tr>
				<td>${tempJoke}${tempUser.title }</td>

				<td>${tempJoke.authorName }</td>
				<td>${tempJoke.description }</td>
				<td>${tempJoke.content }</td>
				<td>${ tempJoke.create_at}</td>
			</tr>
		</c:forEach>
	</table>


	<%
		} ;
	%>

	<%
		if (taskId.equals("1")) {
	%>




	<!--  Here is to input the tags for first funtionality -->

	<button class="open-button" onclick="openForm()">Task 1 </button>

	<div class="form-popup" id="task">
		<form action="UserControlServlet" method="Get" class="form-container">
			<input type="hidden" name="action" value="task1" /> <label
				for="TagX"><b>Tag X</b></label> <input type="text"
				placeholder="Tag X" name="TagX" required> <label for="TagY"><b>Tag
					Y</b></label> <input type="text" placeholder="Tag Y" name="TagY" required>

			<button type="submit" class="btn">Comfirm</button>

			<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
		</form>
	</div>


	<table>
		<tr>
			<th>AuthorName</th>
			<th>Account</th>
			<th>Address</th>
			<th>Gender</th>
			<th>Status</th>
		</tr>

		<c:forEach var="tempUser" items="${userListFunc1}">
			<tr>
				<td>${tempUser.firstName }${tempUser.lastName }</td>

				<td>${tempUser.account }</td>
				<td>${tempUser.address }</td>
				<td>${tempUser.gender }</td>
				<td>${ tempUser.status}</td>
			</tr>
		</c:forEach>
	</table>
	<%
		} ;
	%>





	<script>
		function openSearch() {
			document.getElementById("myOverlay").style.display = "block";
		}

		function closeSearch() {
			document.getElementById("myOverlay").style.display = "none";
		}
	</script>



	<script>
		function openForm() {
			document.getElementById("task").style.display = "block";
		}

		function closeForm() {
			document.getElementById("task").style.display = "none";
		}
	</script>
	

</body>
</html>