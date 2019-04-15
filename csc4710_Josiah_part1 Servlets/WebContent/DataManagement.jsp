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
<style>
div.a {
	text-align: center;
}

div.ex1 {
	background-color: lightblue;
	width: 50%;
	height: 25%;
	overflow: scroll;
}

div.ex2 {
	background-color: lightblue;
	width: 110px;
	height: 110px;
	overflow: hidden;
}

div.ex3 {
	background-color: lightblue;
	width: 70%;
	height: 50%;
	overflow: auto;
}

div.ex4 {
	background-color: lightblue;
	width: 110px;
	height: 110px;
	overflow: visible;
}
</style>


<style>
a:link, a:visited {
	background-color: LightSkyBlue;
	color: SpringGreen;
	padding: 15px 25px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
}

a:hover, a:active {
	background-color: DarkCyan;
}
</style>

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
	<div class="ex1">
		<h2>Task 1</h2>
		<p>List the users who posted at least two jokes that are posted on
			the same day, one has a tag of X and another has tag of Y</p>


		<!--  Here is to output the result of the first Functionality -->

		<h2>Task 2</h2>
		<p>List all the jokes posted by user X, such that all the comments
			are "Excellent" or "good" for these jokes.</p>

		<br />
		<h2>Task 3</h2>
		<p>4. List the users who posted the most number of jokes since
			3/1/2018 (inclusive); if there is a tie, list all the users who have
			a tie.</p>

		<h2>Task 4</h2>
		<p>List the other users who are befriended by both users X and Y.
			Usernames X and Y will be selected from dropdown menus by the TA.
		<p>
		<h2>Task 5</h2>
		<p>6. Display all the users who never posted any "excellent"
			jokes: a joke is excellent if at least three reviews are excellent.</p>
		<h2>Task 6</h2>
		<p>Display all the users who never posted a "poor" review</p>
		<h2>Task 7</h2>
		<p>Display all the users who posted some reviews but each of them
			is “poor
		<p>
		<h2>Task 8</h2>
		<p>Display those users such that each joke they posted so far
			never received any “poor” reviews.</p>
		<h2>Task 9</h2>
		<p>List a user pair (A, B) such that they always gave each other
			"excellent” review for every single joke they posted.</p>
		<br /> <br />
	</div>





	<p>Click on the button to open the search box.</p>
	<button class="openBtn" onclick="openSearch()">Display the
		task according to the manual</button>
	<p>Note that the button and the form is fixed - they will always be
		positioned at the center of the browser window.</p>

	<%
		String taskId = request.getParameter("Load");

		if (taskId == null) {
			taskId = (String) session.getAttribute("taskId");
		}
		if (taskId == null) {
			taskId = "1";
		} else {
			session.setAttribute("taskId", taskId);
			taskId = (String) session.getAttribute("taskId");
		}

		System.out.println("111111111" + taskId);
	%>

	<%
		if (taskId.equals("9")) {
	%>

	<div class="a">
		<form>
			<c:url var="task9" value="UserControlServlet">
				<c:param name="action" value="task9" />
			</c:url>
			<nav class="cl-effect-21">
			
				<a href="${task9} "> Task9 </a>

			</nav>
			<h3 style="float: left">List a user pair (A, B) such that they
				always gave each other "excellent” review for every single joke they
				posted.</h3>

			<table style="float: none">

				<tr>
					<th>Cheater A</th>
					<th>Cheater B</th>
				</tr>

				<c:forEach var="tempUser" items="${userListTask9}">
					<tr>
						<td>${tempUser.cheaterA }</td>
						<td> ${tempUser.cheaterB }</td>

					</tr>
				</c:forEach>
			</table>
		</form>
	</div>



	<%
		} ;
	%>
	<%
		if (taskId.equals("8")) {
	%>
	<div class="a">
		<form>
			<c:url var="task8" value="UserControlServlet">
				<c:param name="action" value="task8" />
			</c:url>
			<nav class="cl-effect-21">




				<a href="${task8} "> Task8 </a>

			</nav>
			<h3 style="float: left">Display those users such that each joke
				they posted so far never received any Previews.</h3>
			<table style="float: none">

				<tr>
					<th>AuthorName</th>
					<th>Account</th>
					<th>Address</th>
					<th>Gender</th>
					<th>Status</th>
					<th>Age</th>
				</tr>

				<c:forEach var="tempUser" items="${userListTask8}">
					<tr>
						<td>${tempUser.firstName }${tempUser.lastName }</td>
						<td>${tempUser.account }</td>
						<td>${tempUser.address }</td>
						<td>${tempUser.gender }</td>
						<td>${tempUser.status}</td>
						<td>${tempUser.age }</td>

					</tr>
				</c:forEach>
			</table>
		</form>
	</div>



	<%
		} ;
	%>

	<%
		if (taskId.equals("7")) {
	%>

	<div class="a">
		<form>
			<c:url var="task7" value="UserControlServlet">
				<c:param name="action" value="task7" />
			</c:url>
			<nav class="cl-effect-21">




				<a href="${task7} "> Task7 </a>

			</nav>
			<h3>Display all the users who posted some reviews but each of
				them is poor</h3>
			<table>

				<tr>
					<th>AuthorName</th>
					<th>Account</th>
					<th>Address</th>
					<th>Gender</th>
					<th>Status</th>
					<th>Age</th>
				</tr>

				<c:forEach var="tempUser" items="${userListTask7}">
					<tr>
						<td>${tempUser.firstName }${tempUser.lastName }</td>
						<td>${tempUser.account }</td>
						<td>${tempUser.address }</td>
						<td>${tempUser.gender }</td>
						<td>${tempUser.status}</td>
						<td>${tempUser.age }</td>

					</tr>
				</c:forEach>
			</table>
		</form>
	</div>


	<%
		} ;
	%>

	<%
		if (taskId.equals("6")) {
	%>

	<div class="a">
		<form>
			<c:url var="task6" value="UserControlServlet">
				<c:param name="action" value="task6" />
			</c:url>
			<nav class="cl-effect-21">

				<a href="${task6} "> Task6 </a>

			</nav>
			<h3>Display all the users who never posted a "poor" review</h3>
			<table>

				<tr>
					<th>AuthorName</th>
					<th>Account</th>
					<th>Address</th>
					<th>Gender</th>
					<th>Status</th>
					<th>Age</th>
				</tr>

				<c:forEach var="tempUser" items="${userListTask6}">
					<tr>
						<td>${tempUser.firstName }${tempUser.lastName }</td>
						<td>${tempUser.account }</td>
						<td>${tempUser.address }</td>
						<td>${tempUser.gender }</td>
						<td>${tempUser.status}</td>
						<td>${tempUser.age }</td>

					</tr>
				</c:forEach>
			</table>
		</form>
	</div>





	<%
		} ;
	%>



	<%
		if (taskId.equals("5")) {
	%>

	<div class="a">
		<c:url var="task5" value="UserControlServlet">
			<c:param name="action" value="task5" />
		</c:url>
		<nav class="cl-effect-21">

			<a href="${task5} "> Task5 </a>

		</nav>
		<h3>Users who never posted excellent jokes</h3>
		<table>

			<tr>
				<th>AuthorName</th>
				<th>Account</th>
				<th>Address</th>
				<th>Gender</th>
				<th>Status</th>
				<th>Age</th>
			</tr>

			<c:forEach var="tempUser" items="${userListTask5}">
				<tr>
					<td>${tempUser.firstName }${tempUser.lastName }</td>
					<td>${tempUser.account }</td>
					<td>${tempUser.address }</td>
					<td>${tempUser.gender }</td>
					<td>${tempUser.status}</td>
					<td>${tempUser.age }</td>

				</tr>
			</c:forEach>
		</table>





		</form>









	</div>





	<%
		} ;
	%>


	<%
		if (taskId.equals("4")) {
	%>

	<!-- I use individual names for two drop down lists, so that i can get them in the controlServlet -->
	<div class="a">
		<form action="UserControlServlet" method="Get">
			<input type="hidden" name="action" value="task4" />


			<h3>User X</h3>
			<select name="UserX" required>
				<c:forEach var="tempUser" items="${USER_LIST}">
					<option value="${tempUser.account}">${tempUser.account }</option>
				</c:forEach>
			</select>

			<h3>User Y</h3>
			<select name="UserY" required>
				<c:forEach var="tempUser" items="${USER_LIST}">
					<option value="${tempUser.account }">${tempUser.account }</option>


				</c:forEach>

			</select> <br /> <input type="submit" value="Submit">

		</form>
	</div>
	<div class="a">
		<h3>Common Friends By User X and User Y</h3>
		<table>

			<tr>
				<th>AuthorName</th>
				<th>Account</th>
				<th>Address</th>
				<th>Gender</th>
				<th>Status</th>
				<th>Age</th>
			</tr>

			<c:forEach var="tempUser" items="${UserListTask4}">
				<tr>
					<td>${tempUser.firstName }${tempUser.lastName }</td>
					<td>${tempUser.account }</td>
					<td>${tempUser.address }</td>
					<td>${tempUser.gender }</td>
					<td>${tempUser.status}</td>
					<td>${tempUser.age }</td>

				</tr>
			</c:forEach>
		</table>
	</div>






	<%
		} ;
	%>

	<%
		if (taskId.equals("3")) {
	%>
	<div class="a">
		<c:url var="task3" value="UserControlServlet">
			<c:param name="action" value="task3" />
		</c:url>

		<nav class="cl-effect-21">

			<a href="${task3 }"> Task3 </a>

		</nav>
	</div>
	<div class="a">
		<table>
			<tr>
				<th>AuthorName</th>
				<th>Account</th>
				<th>Address</th>
				<th>Gender</th>
				<th>Status</th>
				<th>NumberOfJokes</th>
			</tr>

			<c:forEach var="tempUser" items="${userListAndCountTask3}">
				<tr>
					<td>${tempUser.firstName }${tempUser.lastName }</td>
					<td>${tempUser.account }</td>
					<td>${tempUser.address }</td>
					<td>${tempUser.gender }</td>
					<td>${tempUser.status}</td>
					<td>${tempUser.numberOfJokes }</td>

				</tr>
			</c:forEach>
		</table>
	</div>


	<%
		} ;
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

	<div class="a">
		<table>
			<tr>
				<th>Joke Title</th>
				<th>Author Name</th>
				<th>Description</th>
				<th>Content</th>
				<th>Create_At</th>
			</tr>

			<c:forEach var="tempJoke" items="${jokeListTask2}">
				<tr>
					<td>${tempJoke.title }</td>
					<td>${tempJoke.authorName }</td>
					<td>${tempJoke.description }</td>
					<td>${tempJoke.content }</td>
					<td>${ tempJoke.create_at}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%
		} ;
	%>

	<%
		if (taskId.equals("1")) {
	%>

	<!--  Here is to input the tags for first funtionality -->

	<button class="open-button" onclick="openForm()">Task 1</button>

	<div class="form-popup" id="task">
		<form action="UserControlServlet" method="Get" class="form-container">
			<input type="hidden" name="action" value="task1" /> <label
				for="TagX"><b>Tag X</b></label> <input type="text"
				placeholder="Tag X" name="TagX" required> <label for="TagY"><b>Tag
					Y</b></label> <input type="text" placeholder="Tag Y" name="TagY" required>

			<button type="submit" class="btn">Confirm</button>

			<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
		</form>
	</div>

	<div class="a">
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
	</div>
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