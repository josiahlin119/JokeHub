package csc4710_Josiah_part1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//////dddddddd
/**
 * Servlet implementation class ControlServelet
 */
@WebServlet("/UserControlServlet")
public class UserControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private boolean successfullyRegistered;

	public void init() {
		userDAO = new UserDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String action = request.getServletPath(); we use this if we confirgure xml pattern we append the action to the 
		// end of our url

		String action = request.getParameter("action");
//		if (action ==null) {
//			action = "new";
//		}
		System.out.println(action);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {

//		listUsers(request,response);

			switch (action) {
			case "new": // use this for sign form call this action showSignUpForm(request, response);
				registration(request, response);
				break;
			case "login": // check whether the user exists in Josiah_db.
				login(request, response);
				System.out.println("This is Login");
				break;
			case"logout":
				logout(request,response);
				break;
			case "initialize":
				initialize(request, response);
				break;
			case "updateFriends":
				updateFavoriteFriends(request, response);
				break;
			case "postJoke":
				postNewJokes(request, response);
				break;
			case "retrieveJokesWithTag":
				retrieveJoke_tag(request, response);
				break;
			case "load":
				loadJoke(request, response);
				break;
			case "review":
				reviewJokes(request, response);
				break;
			case "verifyFriend":
				verifyFriend(request, response);
				break;
			case "addFriend":
				System.out.print("AddFriend action accepted");
				addFriend(request, response);
				break;
			case "deleteFriend":
				System.out.print("deletefriend action accepted");
				deleteFriend(request, response);
				break;
			case "addFavoriteJoke":
				likeJokes(request, response);
				break;
			case "deleteFavoriteJoke":
				deleteFavoriteJoke(request, response);
				break;
			case "loadFriends":
				loadFriends(request, response);
				break;
			case "loadJokes":
				loadJokes(request, response);
				break;
			case "loadCurrentUserReview":
				retrieveReviewByCurrentUser(request,response);
			break;
			case "updateReview":
				updateCurrentUserReview(request,response);
				break;
			case "task1":
				task_1(request,response);
				break;
			case "loadAdminPage":
				listUsers(request,response);
				break;
			default:
				out.println("404");
			}

		} catch (SQLException ex) {
			System.out.print("Doget");
			throw new ServletException(ex);
		}
	}

	
	private void task_1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TagX = request.getParameter("TagX");
		String TagY = request.getParameter("TagY");
		try {
			ArrayList<Users> userListFunc1 = userDAO.task_1(TagX,TagY);
			request.setAttribute("userListFunc1", userListFunc1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/DataManagement.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect("login-form.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void deleteFavoriteJoke(HttpServletRequest request, HttpServletResponse response) {
		try {
			int jokeId = Integer.parseInt(request.getParameter("jokeId"));
			int userId = (int) request.getSession().getAttribute("id");

			// we need follower and followee's ids to pair a relationship. Remember, adding
			// friend in this case
			// is equal to following a friend since we dont need verification from the
			// counterpart.
			boolean jokeDeleted = userDAO.deleteFavorite(userId, jokeId);
			System.out.print("delete Jokes");
			if (jokeDeleted) {
				request.getSession().setAttribute("isFavorite", "No");
			} else {
				request.getSession().setAttribute("isFavorite", "Yes");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/review-jokes.jsp");
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void likeJokes(HttpServletRequest request, HttpServletResponse response) {
		try {
			int jokeId = Integer.parseInt(request.getParameter("jokeId"));
			int userId = (int) request.getSession().getAttribute("id");

			// we need follower and followee's ids to pair a relationship. Remember, adding
			// friend in this case
			// is equal to following a friend since we dont need verification from the
			// counterpart.
			boolean jokeAdded = userDAO.addFavoriteJoke(userId, jokeId);
			System.out.print("Friend added ready to forward");
			if (jokeAdded) {
				request.getSession().setAttribute("isFavorite", "Yes");
			} else {
				request.getSession().setAttribute("isFavorite", "No");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/review-jokes.jsp");
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadJokes(HttpServletRequest request, HttpServletResponse response) {
		try {

			int userId = (int) request.getSession().getAttribute("id");

			ArrayList<Jokes> allJokes = userDAO.getJokes_id(userId);
			request.setAttribute("Joke_List", allJokes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/favoriteJoke.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadFriends(HttpServletRequest request, HttpServletResponse response) {
		try {
			int userId = (int) request.getSession().getAttribute("id");
			ArrayList<Users> allFriends = userDAO.getFriends(userId);
			request.getSession().setAttribute("Friend_List", allFriends);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/friends.jsp");
			dispatcher.forward(request, response);

		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteFriend(HttpServletRequest request, HttpServletResponse response) {

		try {
			int followeeId = Integer.parseInt(request.getParameter("authorId"));
			int followerId = (int) request.getSession().getAttribute("id");
			Users theUser = userDAO.getUsers(followeeId);
			boolean friendDeleted = userDAO.deleteFriend(followerId, followeeId);
			System.out.print("Deleted Friend added ready to forward");
			if (friendDeleted) {
				request.getSession().setAttribute("authorId", followeeId);
				request.getSession().setAttribute("isFriend", "No");
				request.getSession().setAttribute("theUser", theUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/see-user-profile.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verifyFriend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int followeeId = Integer.parseInt(request.getParameter("authorId"));
		int followerId = (int) request.getSession().getAttribute("id");
		Users theUser = userDAO.getUsers(followeeId);
		boolean isFriend = userDAO.verifyFriend(followeeId, followerId);
		System.out.print("are they friend or not " + isFriend);
		if (isFriend) {
			request.getSession().setAttribute("isFriend", "Yes");
		} else {
			request.getSession().setAttribute("isFriend", "No");
		}
		request.getSession().setAttribute("authorId", followeeId);
		request.getSession().setAttribute("theUser", theUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/see-user-profile.jsp");
		dispatcher.forward(request, response);

	}

	private void addFriend(HttpServletRequest request, HttpServletResponse response) {
		try {
			int followeeId = Integer.parseInt(request.getParameter("authorId"));
			int followerId = (int) request.getSession().getAttribute("id");
			Users theUser = userDAO.getUsers(followeeId);
			// we need follower and followee's ids to pair a relationship. Remember, adding
			// friend in this case
			// is equal to following a friend since we dont need verification from the
			// counterpart.
			boolean friendAdded = userDAO.addFriend(followerId, followeeId);
			System.out.print("Friend added ready to forward");
			if (friendAdded) {
				request.getSession().setAttribute("authorId", followeeId);
				request.getSession().setAttribute("isFriend", "Yes");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/see-user-profile.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadJoke(HttpServletRequest request, HttpServletResponse response) {
		try {
			int jokeId = Integer.parseInt(request.getParameter("jokeId"));

			System.out.print("The id of joke" + jokeId);
			/* Step 1 retrieve all the jokes and load */
			Jokes theJoke = userDAO.retrieveJoke_id(jokeId);
			request.getSession().setAttribute("joke", theJoke);
			/* Step 2 retrieve all the reviews about this joke and load */
			
			ArrayList<Review> allReviewOfThisJoke = loadReviews(jokeId);     //  The review has userId information  
			request.getSession().setAttribute("reviews", allReviewOfThisJoke);

			// Step 3 after this, i need to check whether the joke is in the user's favorite
			// joke list.
			int userId = (int) request.getSession().getAttribute("id");

			boolean isFavorite = userDAO.verifyFavorite(userId, jokeId);
			if (isFavorite) {
				request.getSession().setAttribute("isFavorite", "Yes");
			} else {
				request.getSession().setAttribute("isFavorite", "No");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/review-jokes.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}


	private void updateCurrentUserReview(HttpServletRequest request, HttpServletResponse response) {
	
		 int currentUserId = (int) request.getSession().getAttribute("id");
		 int jokeId = Integer.parseInt(request.getParameter("jokeId"));
		 String rating = request.getParameter("rating");
			String comment = request.getParameter("comment");
		 try {
			userDAO.updateReview(currentUserId,jokeId,rating, comment);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 //call load joke to reload the review joke page with updated review
		 loadJoke(request,response);
		 
		 
	}

	private void retrieveReviewByCurrentUser(HttpServletRequest request, HttpServletResponse response) {
	 int currentUserId = (int) request.getSession().getAttribute("id");
	 int jokeId = Integer.parseInt(request.getParameter("jokeId"));
	 
	 System.out.print("currentUser REview 1111111111111111111");
	 ArrayList<Review> allReviewsOfThisJoke = loadReviews(jokeId);
	 //Find current user's single review
	 Review currentUserReview;
	 
	 for(Review r: allReviewsOfThisJoke) {
		 if(r.getCommenter_id() == currentUserId) {
			 currentUserReview = r;
			 request.setAttribute("currentUserReview", currentUserReview);
			 System.out.print("currentUser REview 22222222222222222");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("ModifyReview.jsp");
			
			 try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			 
		 }
	 }
	 
	
	 
	 
		
	}

	private ArrayList<Review> loadReviews(int jokeId) {
		ArrayList<Review> allReviewsOfThisJoke = new ArrayList<>();
		try {
			allReviewsOfThisJoke = userDAO.retrieveReviews_jokeId(jokeId);   // i need to provide a modification button for the current user 
			// to modify his or her previous review.  
			

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return allReviewsOfThisJoke;

	}
	

	private void reviewJokes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		try {
		String rating = request.getParameter("rating");
		String comment = request.getParameter("comment");
		int jokeId = Integer.parseInt(request.getParameter("jokeId"));
		int reviewerId = (int) request.getSession().getAttribute("id");
		userDAO.insertReview(jokeId, reviewerId, rating, comment);
		loadJoke(request,response);
		}
		catch(SQLException e) {
			String warning = e.getMessage();
		  
			System.out.print("review error:  " + warning + e.getErrorCode());
			if(warning.equalsIgnoreCase("Cannot post more than five jokes per day") ) {
			request.setAttribute("warning2", warning);
			}
			else if(warning.equalsIgnoreCase("You cannot write more than one review to the same joke")){
				request.setAttribute("warning3", warning);
			}
			else if(warning.equalsIgnoreCase("Sorry, self-reviewing is not supported")){
				request.setAttribute("warning4", warning);
			};
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/review-jokes.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void retrieveJoke_tag(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String tag = request.getParameter("tag");
		List<Jokes> allJokesWithTag = new ArrayList<>();
		allJokesWithTag = userDAO.retrieveJokeList_tag(tag);
		request.getSession().setAttribute("jokes", allJokesWithTag);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search-jokes.jsp");
		dispatcher.forward(request, response);

	}

	private void postNewJokes(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		try {
			int authorId = (int) request.getSession().getAttribute("id");

			System.out.print(authorId);
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String content = request.getParameter("content"); // I need to make sure that textarea can also be passed to
																// //
																// controlservlet.
			String tags = request.getParameter("tag");
			
			String [] tagsAfterSplit  = tags.split("#");
			// Step 1: Insert joke information into the database;
			userDAO.insertJokes(authorId, title, description, content, tagsAfterSplit);
			// Step 2: Present the joke on the homepage with date specified.
			System.out.print("insert Succesffully");
			Jokes theJoke = userDAO.retrieveJoke_title(title);
			System.out.print("Retrieve jokes successfully");

			request.setAttribute("theJoke", theJoke);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
			dispatcher.forward(request, response);

		} 
		catch(SQLException e) {
			String warning = e.getSQLState();
			System.out.println(warning);
			request.setAttribute("warning", warning);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
			dispatcher.forward(request, response);
		}
		
		
		catch (Exception e) {
			e.printStackTrace();
		}

//		PrintWriter out = response.getWriter();
//		out.print("you have successfully added your joke to db");
//		Jokes newJoke = userDAO.retrieveJoke_title(title);
//		out.println("<p>" + newJoke.getAuthorName() + "</p>");
//		out.println("<p>" + newJoke.getTitle() + "</p>");
//		out.println("<p>" + newJoke.getContent() + "</p>");
//		out.println("<p>" + newJoke.getCreate_at() + "</p>");
	}

	protected void initialize(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		userDAO.initializeDB();
		listUsers(request, response);
	}

	private void updateFavoriteFriends(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");

		try {
			List<Users> favoriteFriends = userDAO.getFavoriteFriends(id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateFavoriteJokes(HttpServletRequest request, HttpServletResponse response) {

	}

	// check if the user logging is is administrator
	private boolean isAdmin(String account, String password) throws SQLException {

		Users admin = userDAO.getAdmin(account, password);
		if (admin != null) {
			return true;
		}

		else {
			return false;
		}
	}

	// show a guest sign up form for the new user who does not have a account.

	private void registration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("guest-signup.jsp");
//			dispatcher.forward(request, response);
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String[] allAttributes = { account, password, address, firstName, lastName, birthday };
//		boolean isFieldEmpty =	checkNullPointer(allAttributes);
//			if(isFieldEmpty) {
//				response.sendRedirect("guest-signup.jsp");
//			}
		/*
		 * Note: registration is the opposite of sign in. If the account already exists,
		 * we should warn the user that the account cannot be created again
		 */
		Users user = userDAO.getUsers(account, password);
		Users admin = userDAO.getAdmin(account, password);
		if (user != null) {
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("guest-signup.jsp");
			dispatcher.forward(request, response);
		} else if (admin != null) {
			request.setAttribute("user", admin);
			RequestDispatcher dispatcher = request.getRequestDispatcher("guest-signup.jsp");
			dispatcher.forward(request, response);
		} else {
			successfullyRegistered = userDAO.getRegisterted(account, password, address, firstName, lastName, birthday,gender);
			// create a new user tuple, we need to direct him or her to the homepage.
			if (successfullyRegistered) {
				Users newUser = userDAO.getUsers(account, password);
				System.out.print(newUser.getAccount());
				response.sendRedirect("login-form.jsp");
			}

		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PrintWriter out = response.getWriter();
		System.out.println("Login method");
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		// if the the user is Josiah, then direct him to
		// Administrator.jsp which has been embedded in listUsers();
		// out.println("This is login"); check admin before we check common users;

		if (isAdmin(account, password)) {
			listUsers(request, response);
			return;
		}

		// System.out.println("before call getUsers aaaaaaaaaaaaaaaaaaaa");

		Users user = userDAO.getUsers(account, password);

		// System.out.println("after call getUsers bbbbbbbbbbbbbbbbbbbb");

		out.print("Login");
		if (user == null) { // case 1: there is no such user
			response.sendRedirect("login-form.jsp");
		}

		else if (user.isAccess()) { // case 2: the user exists, and she can access it
			System.out.print(user.getAccount());
			HttpSession session = request.getSession(true);
			session.setAttribute("id", user.getId());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("firstName", user.getFirstName());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("homepage.jsp");
		} else { // case 3: the user exists, but she cannot access it
			response.sendRedirect("login-form.jsp");
		}
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Users> allUsers = userDAO.getUserList();
		HttpSession session = request.getSession(true);
		session.setAttribute("id", 10000);
		request.setAttribute("USER_LIST", allUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Administrator.jsp");
		dispatcher.forward(request, response);
	}

}
