package csc4710_Josiah_part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class UserDAO
 */
@WebServlet("/UserDAO")
public class UserDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement sm = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private ResultSet secondRs = null;
	private int id;
	private String anotherAccount;
	private String anotherPassword;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	int age;
	private boolean status;

	/**
	 * @param datasource
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDAO() {
		super();

	}

	// set up a bridge to the school web server
	protected void connect_func() throws SQLException {
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); // use old driver for school webserver
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
//            connect = (Connection) DriverManager
//  			      .getConnection("jdbc:mysql://141.217.48.128:3306/Josiah_db?"
//  			          + "user=shiyong&password=view1234"); 
			/* change the driver to com.mysql.jdbc.Driver if using shiyong's webserver */

			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/sample_db?" + "user=john&password=pass1234");
			System.out.println(connect);
		}
	}

	protected void disconnect() throws SQLException {
		if (connect != null && !connect.isClosed()) {
			connect.close();
		}
	}

	public Users getAdmin(String account, String password) throws SQLException { // I set Josiah has special role to
																					// manage other users. thus I obtain
																					// the
		// informaiton of Josiah from a different table named "administrator"/
		Users admin = null;
		String sql = "SELECT id,account,password, address,TIMESTAMPDIFF(YEAR,birthday,CURDATE()) AS age,status,firstName,lastName,address,"
				+ "gender FROM administrator WHERE account = ? AND password = ?";
		connect_func();
		ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setString(1, account);// use account as index
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery(); // if resultSet is returned, that means
		// the user that satisfies both conditions has been found.

		if (rs.next()) {
			id = rs.getInt("id");
			System.out.print("idididdididi");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			address = rs.getString("address");
			System.out.print("11111111111111age");
			age = rs.getInt("age");
			gender = rs.getString("gender");
			System.out.print("22222222222222age");
			status = rs.getBoolean("status");

			admin = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age, gender);
//			System.out.println("exit getUsers 2222222222222222222222");
			disconnect();
			rs.close();
			ps.close();

			return admin;

		}

		else {
//			System.out.println("exit getUsers 333333333333333333333333");
			disconnect();
			rs.close();
			ps.close();

			return null;
		}

	}

	public Users getUsers(int id) throws SQLException {
		try {
			Users user = null;
			String getAuthorName = "SELECT id,account,password, address,TIMESTAMPDIFF(YEAR,birthday,CURDATE()) AS age,status,firstName,lastName,address,gender FROM users WHERE id = ?;";
			connect_func();
			ps = connect.prepareStatement(getAuthorName);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {

				id = rs.getInt("id");
				anotherAccount = rs.getString("account");
				anotherPassword = rs.getString("password");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				address = rs.getString("address");
				age = rs.getInt("age");
				status = rs.getBoolean("status");
				gender = rs.getString("gender");
				user = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,
						gender);
				return user;
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			disconnect();
		}
		return null;

	}

	public Users getUsers(String account, String password) throws SQLException {

//		System.out.println("call getUsers 11111111111111111111111111");
		Users user = null;
		String sql = "SELECT id,account,password, address,TIMESTAMPDIFF(YEAR,birthday,CURDATE()) AS age,status,firstName,lastName,address,"
				+ "gender FROM users WHERE account = ? AND password = ?";
		connect_func();
		ps = (PreparedStatement) connect.prepareStatement(sql);
		ps.setString(1, account);// use account as index
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery(); // if resultSet is returned, that means
		// the user that satisfies both conditions has been found.

		if (rs.next()) {
			id = rs.getInt("id");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			address = rs.getString("address");
			age = rs.getInt("age");
			status = rs.getBoolean("status");
			gender = rs.getString("gender");
//			System.out.println("exit getUsers 2222222222222222222222");

			rs.close();
			ps.close();
			disconnect();
			user = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age, gender);
			return user;

		}

		else {
//			System.out.println("exit getUsers 333333333333333333333333");
			disconnect();
			rs.close();
			ps.close();

			return null;
		}

	}

	public void initializeDB() {
		try {
			connect_func();
			Statement statement = connect.createStatement();
			System.out.print("initialize db");
			String setting = "Set SQL_SAFE_UPDATES =0;";
			String dropUsers = "DROP TABLE IF EXISTS Users,Jokes,follow,joke_tags,likes,review,tags";
			String dropAdmin = "DROP TABLE IF EXISTS administrator";

			String User = "create table Users(" + "id int(10) NOT NULL auto_increment primary key,"
					+ "account varchar(20) not null unique," + "password varchar(20) not null," + "birthday DATE,"
					+ "firstName varchar(20) not null," + "lastName varchar(20) not null,"
					+ "gender varchar(6) default 'secret'," + "status boolean default true," + "address varchar(100)"
					+ ");";

			String Admin = "create table Administrator(" + "id int(10) NOT NULL auto_increment primary key,"
					+ "account varchar(20) not null unique," + "password varchar(20) not null," + "birthday DATE,"
					+ "firstName varchar(20) not null default 'Josiah',"
					+ "lastName varchar(20) not null default 'Lin' ," + "gender varchar(6) default 'secret',"
					+ "status boolean default true," + "address varchar(100)" + ");";
			String jokes = "create table Jokes(" + "id int(10) NOT NULL auto_increment primary key,"
					+ "create_at Date not null default '2018-03-07'," + "title varchar(20) NOT NULL unique,"
					+ "content text NOT NULL," + "Description text NOT NULL," + "author_ID int not null,"
					+ "foreign key(author_id)references Users(id)" + ");";
			String review = "create table review(" + "review_id int auto_increment primary key,"
					+ "comment varchar(255) not null," + "create_at date default '2019-03-07',"
					+ "User_id int not null," + "Joke_id int not null," + "foreign key(User_id)references Users(id),"
					+ "foreign key(Joke_id) references Jokes(id)," + "score enum('Excellent','Good','Fair','Poor')"
					+ ");";
			String tags = "create table tags(" + " id Int(10) auto_increment primary key," + " tag_name Varchar(20),"
					+ " create_at timestamp default now()" + ");";

			String likes = "create table likes(" + "user_id int not null," + "joke_id int not null,"
					+ "create_at date default '2019-03-07'," + "Foreign key(joke_id) references jokes(id),"
					+ "Foreign key(user_id) references users(id)," + "primary key(joke_id,user_id)" + ");";
			String joke_tags = "create table joke_tags(" + "joke_id int(20) not null," + "tag_id int(20) not null,"
					+ "Foreign key(joke_id) references jokes(id)," + "Foreign key(tag_id) references tags(id) );";
			String follow = "create table follow(" + "follower_id int(10) not null," + "followee_id int(10) not null,"
					+ "create_at timestamp default now()," + "foreign key (follower_id) references users(id),"
					+ "foreign key(followee_id) references users(id)," + "primary key(follower_id, followee_id)" + ");";

			String InsertTags = "insert into tags (tag_name) values('Tantalizing'),('fantastic');";
			String InsertJoke_tags = "Insert into joke_tags(joke_id, tag_id) values(1,1),(1,2), (4,2),(2,2);";

			String InsertUsers = "INSERT INTO Users (account,password,firstName,lastName,address,birthday) values ('hezi.wang@wayne.edu','Zeyue0119!','Hezi','Wang','5671 Fordham Circle apt 204, Canton, MI, 48187','1991-11-20'),('John@gmail.com','pass1234','John','Smith','2722','1993-11-11'),"
					+ "('jjj@gmail.com','pass1234','Yosaf','GG','27233','1997-01-10'),('111@gmail.com','pass1234','Michael','Sun','23333','1997-01-11'),('iiij@gmail.com','pass1234','Muma','Grant','2222233','1997-01-12');";
			String insertAdmin = "INSERT INTO administrator(account,password,address)values('Josiah','1119','2333');";
			String insertJokes = "insert into Jokes (title,content,Description,author_ID,create_at) values ('LLddL','ddd','eee',1,curdate()),('LddssssLL','ddd','eee',1,curdate()),('LLdsssdL','ddd','eee',1,curdate()),('LssssLL','ddd','eee',1,curdate()),('LfdsfsdLL','ddd','eee',1,curdate()),('LfdddsfsdLL','dsdd','eese',1,curdate())";

			/*
			 * I take three steps 1. delete all tables if they exist 2. recreate those
			 * tables with specified attributes 3. I insert sample tuples which needed to be
			 * > 10
			 */
			String[] stmt = { dropUsers, dropAdmin, User, Admin, InsertUsers, insertAdmin, jokes, insertJokes, review,
					tags, likes, joke_tags, follow, InsertTags, InsertJoke_tags };
			for (int i = 0; i < stmt.length; i++) {
				statement.executeUpdate(stmt[i]);
				// Use execute update because i dont need resultset here. I will list all tuples
				// using listAllUsers() later.
				// ps.close();
			}

			statement.close();
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.print("Josiah, You have failed to initialized your database"); // check statement.

			e.printStackTrace();
		}
	}

	public List<Users> getUserList() throws SQLException {
		System.out.println("getUserList");
		List<Users> listUsers = new ArrayList<Users>();
		String sql = "SELECT id,account,password, address,TIMESTAMPDIFF(YEAR,birthday,CURDATE()) AS age,status,firstName,lastName,address,"
				+ "gender FROM users";
		connect_func();
		sm = (Statement) connect.createStatement();
		ResultSet rs = sm.executeQuery(sql);

		while (rs.next()) {
			id = rs.getInt("id");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			address = rs.getString("address");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			age = rs.getInt("age");
			status = rs.getBoolean("status");
			String gender = rs.getString("gender");
			System.out.print(anotherAccount + anotherPassword + address + age + status); // use this to make sure the
																							// data
			// have been read from the database;

			Users newUser = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,
					gender);
			listUsers.add(newUser);
		}

		rs.close();
		sm.close();
		disconnect();
		return listUsers;
	}

	private static java.sql.Date convertJavaDateToSQLdate(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	/*
	 * Use the method above to convert java.util.date format to java.sql.date
	 * format.
	 */

	public boolean getRegisterted(String account, String password, String address, String firstName, String lastName,
			String birthday1, String gender) throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date formattedBirthday = format.parse(birthday1);
			java.sql.Date birthday = convertJavaDateToSQLdate(formattedBirthday);
//			 sqlBirthday = convertJavaDateToSqlDate(formattedBirthday);

			// I format this to match mysql standard date format. then we pass
			// this to jdbc to convert it to mysql date object.

			connect_func();
			// before we insert the user information into the database, let's check whether
			// the userName already
			// exists.
			String InsertUsers = "INSERT INTO Users (account,password,address,firstName,lastName,birthday,gender) values (?,?,?,?,?,?,?)";

			// Use execute update because i dont need resultset here. I will list all tuples
			// using listAllUsers() later.
			// ps.close();

			ps = (PreparedStatement) connect.prepareStatement(InsertUsers);
			ps.setString(1, account);// use account as index
			ps.setString(2, password);
			ps.setString(3, address);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setDate(6, birthday);
			ps.setString(7, gender);
			ps.executeUpdate(); // we dont need to return any value after we successfully
			// create a new user tuple, we need to direct him or her to the homepage.

			ps.close();
			disconnect();
		}

		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	public List<Users> getFavoriteFriends(String id) throws SQLException {
		int userID = Integer.parseInt(id);
		List<Users> myFavoriteFriends = new ArrayList<>();
		connect_func();
		ResultSet myRs = null;

		String sql = "Select * from Users where id in(SELECT followee_id FROM follow WHERE follower_id = ?);";
		ps = connect.prepareStatement(sql);
		ps.setInt(1, userID);
		myRs = ps.executeQuery();

		if (myRs.next()) {
			String account = myRs.getString("account");
			String firstName = myRs.getString("firstName");
			String lastName = myRs.getString("lastName");
			Users friend = new Users(userID, firstName, lastName, account);
			myFavoriteFriends.add(friend);

//			Date date = myRs.getDate("birthday");//i am not sure if this line is working  
		} // we need to receive firstName, lastName...information of all these user's
			// favorite friends.
		ps.close();
		disconnect();
		myRs.close();
		return myFavoriteFriends;
	}

	public ArrayList<Integer> viewIdsOftagsInserted(String[] tagsAfterSplit) {
		try {
			ArrayList<Integer> theIdsOfTags = new ArrayList();

			connect_func();
			ResultSet rs;
			int id = 0;
			String sqlOfRetrieveTags = "select * from tags where tag_name = ? ";
			for (String tag : tagsAfterSplit) {

				PreparedStatement ps = connect.prepareStatement(sqlOfRetrieveTags);
				ps.setString(1, tag);
				rs = ps.executeQuery();

				if (rs.next()) {
					id = rs.getInt("id");
					theIdsOfTags.add(id); // add the id of the tag retrieved by tag name and put into the integer
											// arrayList.
				}

			}
			System.out.print("the id of" + tagsAfterSplit + "is  ------------" + id);

			return theIdsOfTags;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}// return a int according to the sql command.

	public void insertTag(String[] tagsAfterSplit) {
		String insertTag = "INSERT INTO tags (tag_name) VALUES(?);";
		try {
			connect_func();
			for (String tag : tagsAfterSplit) {
				PreparedStatement ps = connect.prepareStatement(insertTag);
				ps.setString(1, tag);
				ps.executeUpdate();
				System.out.print("insert tag successfullly");
			}
			disconnect();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pairJokeAndTag(int idOfJokeInserted, ArrayList<Integer> idsOfTagInserted) {
		try {
			connect_func();

			String pair = "insert into joke_tags (joke_id, tag_id) values(?,?);";

			for (int tagId : idsOfTagInserted) {
				PreparedStatement ps = connect.prepareStatement(pair);
				ps.setInt(1, idOfJokeInserted);
				ps.setInt(2, tagId);
				ps.executeUpdate(); // pair the arrayList of tags with this joke through ids one by one.
				System.out.print(tagId + "pair joke and tag successfullly");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertJokes(int authorId, String title, String description, String content, String[] tagsAfterSplit)
			throws SQLException {
		connect_func();

		String InsertJoke = "insert into Jokes(create_at,title,content,description,author_ID) values (?,?,?,?,?)";

		/* Step 1 insert all joke information besides tag */
		ps = connect.prepareStatement(InsertJoke);
		java.util.Date today = new java.util.Date();

		java.sql.Date currentDate = convertJavaDateToSQLdate(today);
		System.out.print("insertJokes method" + currentDate.toLocalDate());
		ps.setDate(1, currentDate);
		ps.setString(2, title);// use account as index
		ps.setString(3, content);
		ps.setString(4, description);
		ps.setInt(5, authorId);
		System.out.println("the author of the current inserting joke &&&&&&&&&&&&&&&" + authorId);
		ps.executeUpdate();
		disconnect();
		ps.close();
		// Step 2 get id of joke we just inserted.

		int idOfJokeInserted = viewJokeId(title);

		System.out.print("The id of Joke just inserted" + idOfJokeInserted);
		/* Step 3 insert tag */
		insertTag(tagsAfterSplit); // insert an array of tags into database. Our next step is to get their auto
									// incremented id and
		// pair those id with the joke id just created.

		// get all ids of tags using tag array we just inserted.
		ArrayList<Integer> idsOfTagInserted = viewIdsOftagsInserted(tagsAfterSplit);
		System.out.println("the size of tags is ................." + idsOfTagInserted.size());
		// pair this two ids in joke_tags.
		pairJokeAndTag(idOfJokeInserted, idsOfTagInserted);
	}

	private int viewJokeId(String title) {
		try {
			connect_func();
			String sqlRetrieveJokeId = "select * from jokes where title = ?;";

			System.out.print("The title of the joke-----------" + title);

			PreparedStatement ps = connect.prepareStatement(sqlRetrieveJokeId);
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();
			java.sql.Date create_at;
			while (rs.next()) {
				int jokeId = rs.getInt("id");
				create_at = rs.getDate("create_at");
				System.out.print("The date" + create_at);
				System.out.print("The joke id of the joke we just inserted **************" + jokeId);
				return jokeId;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public Jokes retrieveJoke_id(int jokeId) throws SQLException {
		try {
			connect_func();

			String getJoke = "select * from Jokes where id = ?";
			ps = connect.prepareStatement(getJoke);
			ps.setInt(1, jokeId);
			rs = ps.executeQuery();

			java.sql.Date create_at;
			while (rs.next()) {

				int authorId = 0;
				String title, content, description;
				title = rs.getString("title");
				jokeId = rs.getInt("id");
				authorId = rs.getInt("author_id");
				content = rs.getString("content");
				description = rs.getString("description");
				create_at = rs.getDate("create_at");
				String getAuthorName = "select CONCAT(firstname, ' ', lastname) as fullName from Users where id = ?";
				ps = connect.prepareStatement(getAuthorName);
				ps.setInt(1, authorId);
				secondRs = ps.executeQuery();
				while (secondRs.next()) {
					String authorName = secondRs.getString("fullName");
//				String getTag = "Select tag_name from tags where id in(select tag_id from joke_tags where joke_id = ?);";
//				ps = connect.prepareStatement(getTag);
//				ps.setInt(1,jokeId);
					Jokes newJoke = new Jokes(jokeId, authorId, authorName, title, content, description, create_at);

					secondRs.close();
					ps.close();

					return newJoke;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("retrieve_by_jokeId");
		} finally {

			rs.close();

			ps.close();
			disconnect();
		}
		return null;

	}

	public Jokes retrieveJoke_title(String title) throws SQLException {

		try {
			connect_func();

			String getJoke = "select * from Jokes where title = ?";
			ps = connect.prepareStatement(getJoke);
			ps.setString(1, title);
			rs = ps.executeQuery();

			java.sql.Date create_at;
			while (rs.next()) {

				int jokeId, authorId = 0;
				String content, description;
				jokeId = rs.getInt("id");
				authorId = rs.getInt("author_id");
				content = rs.getString("content");
				description = rs.getString("description");
				create_at = rs.getDate("create_at");
				String getAuthorName = "select CONCAT(firstname, ' ', lastname) as fullName from Users where id = ?";
				ps = connect.prepareStatement(getAuthorName);
				ps.setInt(1, authorId);
				secondRs = ps.executeQuery();
				while (secondRs.next()) {
					String authorName = secondRs.getString("fullName");
//				String getTag = "Select tag_name from tags where id in(select tag_id from joke_tags where joke_id = ?);";
//				ps = connect.prepareStatement(getTag);
//				ps.setInt(1,jokeId);
					Jokes newJoke = new Jokes(jokeId, authorId, authorName, title, content, description, create_at);

					return newJoke;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			rs.close();
			secondRs.close();
			ps.close();
			disconnect();
		}
		return null;

	}

	public List<Jokes> retrieveJokeList_tag(String tag) throws SQLException {
		List<Jokes> allJokesWithThisTag = new ArrayList<>();
		connect_func();
		String getJoke = "select * from jokes where id in(select joke_id from joke_tags, tags where joke_tags.tag_id in(select tags.id from tags where tag_name = ? ));";
		ps = connect.prepareStatement(getJoke);
		ps.setString(1, tag);
		rs = ps.executeQuery();
		int i = 0;
		while (rs.next()) {
			int jokeId, authorId = 0;
			String title = rs.getString("title");
			String content, description;
			jokeId = rs.getInt("id");
			authorId = rs.getInt("author_id");
			content = rs.getString("content");
			description = rs.getString("description");
			java.sql.Date create_at = rs.getDate("create_at");
			i++;
			System.out.print(1 + " of Retrieve JOkes with tag");

			String getAuthorName = "select CONCAT(firstname, ' ', lastname) as fullName from Users where id = ?;";

			ps = connect.prepareStatement(getAuthorName);
			ps.setInt(1, authorId);
			secondRs = ps.executeQuery();
			while (secondRs.next()) {
				String authorName = secondRs.getString("fullName");
				Jokes newJoke = new Jokes(jokeId, authorId, authorName, title, content, description, create_at);
				System.out.print(newJoke.getAuthorName() + newJoke.getContent() + newJoke.getTitle());
				allJokesWithThisTag.add(newJoke);
				System.out.print("retrieve_joke with tag ------------");
			}
		}

		rs.close();
		ps.close();

		disconnect();

		return allJokesWithThisTag;
	}

	/* this method is to get all the jokes posted by one user */

	public List<Jokes> retrieveJokeList_authorId(int authorId) throws SQLException {

		List<Jokes> allJokesByThisAuthor = new ArrayList<>();
		connect_func();
		String getJoke = "select * from Jokes where author_id = ？;;";
		ps = connect.prepareStatement(getJoke);
		ps.setInt(1, authorId);
		rs = ps.executeQuery();
		int i = 0;
		int jokeId = 0;
		String content, description;
		while (rs.next()) {

			String title = rs.getString("title");

			jokeId = rs.getInt("id");
			authorId = rs.getInt("author_id");
			content = rs.getString("content");
			description = rs.getString("description");
			java.sql.Date create_at = rs.getDate("create_at");
			i++;
			System.out.print(1 + " of Retrieve JOkes with authorIdu");

			String getAuthorName = "select CONCAT(firstname, ' ', lastname) as fullName from Users where id = ?;";

			ps = connect.prepareStatement(getAuthorName);
			ps.setInt(1, authorId);
			secondRs = ps.executeQuery();
			while (secondRs.next()) {
				String authorName = secondRs.getString("fullName");
				Jokes newJoke = new Jokes(jokeId, authorId, authorName, title, content, description, create_at);
				System.out.print(newJoke.getAuthorName() + newJoke.getContent() + newJoke.getTitle());
				allJokesByThisAuthor.add(newJoke);
				System.out.print("retrieve_joke with authorId ------------");
			}

			secondRs.close();
		}

		try {
			rs.close();
			ps.close();

			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allJokesByThisAuthor;
	}

	/*
	 * TO DO List 1. Print all the Initialized tuples on my JSP page. 2.Build a
	 * doGet() method that can handle multiple actions. 3.
	 * 
	 */

	public void insertReview(int jokeId, int reviewerId, String rating, String comment) throws SQLException {

		connect_func();

		String InsertReview = "insert into review(User_id,Joke_id,score,comment,create_at) values(?,?,?,?,?);";
		java.util.Date today = new java.util.Date();
		java.sql.Date currentDate = convertJavaDateToSQLdate(today);
		/* Step 1 insert all joke information besides tag */
		ps = connect.prepareStatement(InsertReview);
		ps.setInt(1, reviewerId);
		ps.setInt(2, jokeId);
		ps.setString(3, rating);
		ps.setString(4, comment);
		ps.setDate(5, currentDate);
		ps.executeUpdate();

		rs.close();
		disconnect();
		ps.close();

	}

	public void updateReview(int currentUserId, int jokeId, String rating, String comment) throws SQLException {
		connect_func();
		String updateReview = "update Review set  comment = ? , score = ? where user_id =? AND joke_id = ?;";

		/* Step 1 insert all joke information besides tag */
		ps = connect.prepareStatement(updateReview);
		ps.setString(1, comment);
		ps.setString(2, rating);
		ps.setInt(3, currentUserId);
		ps.setInt(4, jokeId);
		ps.executeUpdate();

	}

	public ArrayList<Review> retrieveReviews_jokeId(int jokeId) throws SQLException {
		try {

			ArrayList<Review> allReviewsOfOneJoke = new ArrayList<>();
			System.out.print("retrieveReviews_jokeId");
			connect_func();
			String getReviews = "select U.firstName,U.lastname, R.create_at, R.comment,R.score,R.user_id from users U, review R where U.id = R.user_id AND joke_id = ?;";
			ps = connect.prepareStatement(getReviews);
			ps.setInt(1, jokeId);
			ResultSet myRs = ps.executeQuery();

			while (myRs.next()) {
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
				java.sql.Date create_at = myRs.getDate("create_at");
				String comment = myRs.getString("comment");
				String rating = myRs.getString("score");
				String reviewerName = firstName + " " + lastName;
				int commenter_id = myRs.getInt("user_id"); // this is important to find a modfiable review
				Review theReview = new Review(create_at, reviewerName, comment, rating, jokeId, commenter_id);

				allReviewsOfOneJoke.add(theReview);

			}

			myRs.close();
			return allReviewsOfOneJoke;
		} catch (Exception e) {
			System.out.print("wowwwhahhahaha");
			e.printStackTrace();
		} finally {

			ps.close();
			disconnect();
		}
		return null;
	}

	public boolean verifyFriend(int followeeId, int followerId) {

		try {
			connect_func();
			String getAllFolloweeId = "select * from follow where follower_id = ? AND followee_id = ?;";
			ps = connect.prepareStatement(getAllFolloweeId);
			ps.setInt(1, followerId);
			ps.setInt(2, followeeId);
			ResultSet myRs = ps.executeQuery();

			if (myRs.next()) {
				System.out.print("they are friends already");
				return true;
			}
			System.out.print("they are not friends yet");
			return false;
		} catch (Exception e) {
			System.out.print("VerifyFriends error !!!!");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;

	}

	public boolean verifyFavorite(int userId, int jokeId) {

		try {
			connect_func();
			String getJokeAdded = "select * from likes where user_id = ? AND joke_id =?;";
			ps = connect.prepareStatement(getJokeAdded);
			ps.setInt(1, userId);
			ps.setInt(2, jokeId);
			ResultSet myRs = ps.executeQuery();

			if (myRs.next()) {
				System.out.print("The joke in favorite list already");
				return true;
			}
			myRs.close();
			return false;
		} catch (Exception e) {
			System.out.print("VerifyFriends error !!!!");
			e.printStackTrace();
		} finally {
			try {

				ps.close();
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return status;

	}

	public boolean addFriend(int followerId, int followeeId) {

		try {
			connect_func();
			String followFriend = " insert into follow(follower_id, followee_id) values(?,?);";
			ps = connect.prepareStatement(followFriend);
			ps.setInt(1, followerId);
			ps.setInt(2, followeeId);
			int result = ps.executeUpdate();
			if (result == 1) {
				System.out.print("Friend successfully added");
				return true;

			}

			else {
				System.out.print("Failed to add friend");
				return false;
			}
		} catch (Exception e) {
			System.out.print("Add friend error !!!!");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;

	}

	public boolean deleteFriend(int followerId, int followeeId) {
		try {
			connect_func();
			String unfollowFriend = "delete from follow where follower_id = ? AND followee_id = ?;;";
			ps = connect.prepareStatement(unfollowFriend);
			ps.setInt(1, followerId);
			ps.setInt(2, followeeId);
			int result = ps.executeUpdate();
			if (result == 1) {
				System.out.print("Friend successfully deleted");
				return true;
			}

			else {
				System.out.print("Failed to add friend");
				return false;
			}
		} catch (Exception e) {
			System.out.print("Add friend error !!!!");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	public ArrayList<Users> getFriends(int followerId) {
		try {
			ArrayList<Users> listFriends = new ArrayList<Users>();
			String sql = "SELECT id,account,password, address,TIMESTAMPDIFF(YEAR,birthday,CURDATE()) AS age,status,firstName,lastName,address,gender from users U,follow F where F.followee_id = U.id and F.follower_id = ?;";
			connect_func();
			ps = connect.prepareStatement(sql);
			ps.setInt(1, followerId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
				anotherAccount = rs.getString("account");
				anotherPassword = rs.getString("password");
				address = rs.getString("address");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				age = rs.getInt("age");
				status = rs.getBoolean("status");
				gender = rs.getString("gender");
				System.out.print(anotherAccount + anotherPassword + address + age + status); // use this to make sure
																								// the
																								// data
				// have been read from the database;

				Users newUser = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status,
						age, gender);

				System.out.print(newUser.getAccount() + newUser.getAddress());
				listFriends.add(newUser);

			}
			return listFriends;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Jokes> getJokes_id(int userId) {

		try {
			ArrayList<Jokes> allJokesLikedByThisUser = new ArrayList<>();
			connect_func();
			String getJoke = "select * from jokes J where J.id in (select joke_id from likes where user_id = ?);";
			ps = connect.prepareStatement(getJoke);

			ps.setInt(1, userId);
			rs = ps.executeQuery();
			ResultSet secondRs;
			int i = 0;
			int jokeId = 0;
			String content, description;
			int authorId = 0;
			while (rs.next()) {

				String title = rs.getString("title");
				jokeId = rs.getInt("id");
				authorId = rs.getInt("author_id");
				content = rs.getString("content");
				description = rs.getString("description");
				java.sql.Date create_at = rs.getDate("create_at");
				i++;
				System.out.print(1 + " of Retrieve Jokes with user liking ");

				String getAuthorName = "select CONCAT(firstname, ' ', lastname) as fullName from Users where id = ?;";

				ps = connect.prepareStatement(getAuthorName);
				ps.setInt(1, authorId);
				secondRs = ps.executeQuery();
				while (secondRs.next()) {
					String authorName = secondRs.getString("fullName");
					Jokes newJoke = new Jokes(jokeId, authorId, authorName, title, content, description, create_at);
					System.out.print(newJoke.getAuthorName() + newJoke.getContent() + newJoke.getTitle());
					allJokesLikedByThisUser.add(newJoke);
					System.out.print("retrieve_joke with authorId ------------");
				}

			}
			return allJokesLikedByThisUser;

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				ps.close();
				disconnect();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	public boolean addFavoriteJoke(int userId, int jokeId) {
		try {
			connect_func();
			String likeJoke = " insert into likes (user_id,joke_id) values(?,?);";
			ps = connect.prepareStatement(likeJoke);
			ps.setInt(1, userId);
			ps.setInt(2, jokeId);
			int result = ps.executeUpdate();

			if (result == 1) {
				System.out.print("FavoriteJoke successfully added");
				return true;

			}

			else {
				System.out.print("Failed to add joke");
				return false;
			}
		} catch (Exception e) {
			System.out.print("Add Joke error !!!!");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;

	}

	public boolean deleteFavorite(int userId, int jokeId) {
		try {
			connect_func();
			String unlikeJoke = " delete from likes where user_id = ? AND joke_id = ? ;";
			ps = connect.prepareStatement(unlikeJoke);
			ps.setInt(1, userId);
			ps.setInt(2, jokeId);
			int result = ps.executeUpdate();

			if (result == 1) {
				System.out.print("FavoriteJoke successfully deleted");
				return true;

			}

			else {
				System.out.print("Failed to delete joke");
				return false;
			}
		} catch (Exception e) {
			System.out.print("Delete Joke error !!!!");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;

	}

	public ArrayList<Users> task_1(String tagX, String tagY) throws SQLException {
		connect_func();
		ArrayList<Users> listUsers = new ArrayList<Users>();
		String sql = "select  users.*, count(*) as total from users,jokes where jokes.author_id = users.id AND jokes.id IN("
				+ "select joke_id from joke_tags where tag_id in (select id from tags where tag_name in (?)) union "
				+ "select joke_id from joke_tags where tag_id in  (select id from tags where tag_name in (?)) ) GROUP BY  author_id, create_at Having total >=2;";
		PreparedStatement ps = connect.prepareStatement(sql);
		ps.setString(1, tagX);
		ps.setString(2, tagY);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			id = rs.getInt("id");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			address = rs.getString("address");
			String gender = rs.getString("gender");
			status = rs.getBoolean("status");
			gender = rs.getString("gender");
			Users user = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,
					gender);
			listUsers.add(user);
			

		}
		rs.close();
		ps.close();
		disconnect();
		return listUsers;

	}

	public ArrayList<Jokes> task_2(String account) throws SQLException {
		connect_func();
		ArrayList<Jokes> listJokes = new ArrayList<>();
		String sql = " select J.*,U.firstName, U.lastName,U.account from Jokes J,Review R, Users U where U.id = J.author_id AND  J.id = R.joke_id AND U.account = ? AND J.id  not in (( (select J.id from jokes J, review R where J.id = R.Joke_id AND R.score = 'Poor') "
				+ " union " + " (select J.id from jokes J, review R where J.id = R.Joke_id AND R.score = 'Fair') ) "
				+ " union " + " ( select J.id from jokes J where J.id not in(select Joke_id from review))) ;";
		PreparedStatement ps = connect.prepareStatement(sql);
		ps.setString(1, account);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			java.sql.Date create_at = rs.getDate("create_at");
			int jokeId = rs.getInt("id");
			int author_id = rs.getInt("author_id");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String Description = rs.getString("Description");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String authorName = firstName.concat(" " + lastName);
			Jokes newJoke = new Jokes(jokeId, author_id, authorName, title, content, Description, create_at);
			listJokes.add(newJoke);

		}
		rs.close();
		ps.close();
		disconnect();
		return listJokes;
		
		
	}

	public ArrayList<Users> task_3() throws SQLException {
		connect_func();
		ArrayList<Users> userListAndCount = new ArrayList<>();
		String sql = "select  U.*,count(author_ID) AS numberOfJokes from Jokes, Users U " + 
				"where author_ID = U.id " + 
				" Group by author_ID " + 
				" HAVING numberOfJokes= (select count(author_ID) as l from Jokes " + 
				" where create_at >= '2018-03-01' " + 
				" Group by author_ID order by l DESC limit 1  )";
		
		sm = connect.createStatement();
		ResultSet rs = sm.executeQuery(sql);
		if (rs.next()) {

			id = rs.getInt("id");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			address = rs.getString("address");
			java.sql.Date birthday = rs.getDate("birthday");
			LocalDate now = LocalDate.now();
			Period diff = Period.between(birthday.toLocalDate(), now);
			age = diff.getYears();
			status = rs.getBoolean("status");
			gender = rs.getString("gender");
			int numberOfJokes = rs.getInt("numberOfJokes");
		Users theUser = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,gender,numberOfJokes);
		   //this is a wild type with one integer and one object
		userListAndCount.add(theUser);
		
		}
		return userListAndCount;
		
	}



	public ArrayList<Users> task_4(String userX, String userY) throws SQLException {
		connect_func();
		ArrayList<Users> friends = new ArrayList<>();
		String T1 = " create view T1 AS " + 
				"   SELECT f1.followee_id from follow f1,Users U where f1.follower_id = U.id AND U.account = ?; ";
		
		String T2  =  "create view T2 as SELECT f2.followee_id from follow f2,Users u where f2.follower_id = U.id AND U.account = ? ; ";	
		
		String sql = "select * from users where id in " + 
				"(SELECT DISTINCT " + 
				"	followee_id " + 
				"FROM  T1 " + 
				"   INNER JOIN T2 USING(followee_id));  ";
		
		String dropViews =  "   drop view T1,T2;";
		
		Statement sm = connect.createStatement();
		Statement sm1 = connect.createStatement();
		
		PreparedStatement ps1 = connect.prepareStatement(T1);
		PreparedStatement ps2 = connect.prepareStatement(T2);
		ps1.setString(1, userX);
		ps2.setString(1, userY);
		ps1.executeUpdate();
		
		
		ps2.executeUpdate();
		
		
	ResultSet rs = 	sm1.executeQuery(sql);
	sm.executeUpdate(dropViews);
	// drop the views so that we can recreate ones in the next query. 
	while (rs.next()) {

		id = rs.getInt("id");
		anotherAccount = rs.getString("account");
		anotherPassword = rs.getString("password");
		firstName = rs.getString("firstName");
		lastName = rs.getString("lastName");
		address = rs.getString("address");
		java.sql.Date birthday = rs.getDate("birthday");
		LocalDate now = LocalDate.now();
		Period diff = Period.between(birthday.toLocalDate(), now);
		age = diff.getYears();
		status = rs.getBoolean("status");
		gender = rs.getString("gender");
		
	Users theUser = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,gender);
	   //this is a wild type with one integer and one object
	friends.add(theUser);
	
	
		
	}
	rs.close();
	sm.close();
	disconnect();
	return friends;
	

	
	}

	public ArrayList<Users> task_5() throws SQLException {
		connect_func();
		ArrayList<Users> userList =  new ArrayList<>();
		String sql = " select * from Users U " + 
				"where U.id not in " + 
				" (select author_ID " + 
				" from review R,jokes J,users U " + 
				" where R.Joke_id = J.id  AND score = 'Excellent' " + 
				" group by R.Joke_id  " + 
				" having count(*)>= 3) ;";
		sm = connect.createStatement();
		ResultSet rs = sm.executeQuery(sql);
		
		while (rs.next()) {

			id = rs.getInt("id");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			address = rs.getString("address");
			java.sql.Date birthday = rs.getDate("birthday");
			LocalDate now = LocalDate.now();
			Period diff = Period.between(birthday.toLocalDate(), now);
			age = diff.getYears();
			status = rs.getBoolean("status");
			gender = rs.getString("gender");
		
		Users theUser = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,gender);
		   //this is a wild type with one integer and one object
		userList.add(theUser);
		
		}
		rs.close();
		sm.close();
		disconnect();
		return userList;
	}

	public ArrayList<Users> task_6() throws SQLException {
		connect_func();
		ArrayList<Users> userList =  new ArrayList<>();
		String sql = "select U.*, count(*) as num from Review R, Users U  " + 
				"   where R.user_id = U.id AND R.user_id NOT IN(select R1.user_id from Review R1 " + 
				"   where R1.score in('Excellent','Good','Fair')) GROUP BY U.id Having num >=0;"; 
		sm = connect.createStatement();
		ResultSet rs = sm.executeQuery(sql);
		
		while (rs.next()) {

			id = rs.getInt("id");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			address = rs.getString("address");
			java.sql.Date birthday = rs.getDate("birthday");
			LocalDate now = LocalDate.now();
			Period diff = Period.between(birthday.toLocalDate(), now);
			age = diff.getYears();
			status = rs.getBoolean("status");
			gender = rs.getString("gender");
		
		Users theUser = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,gender);
		   //this is a wild type with one integer and one object
		userList.add(theUser);
		
		}
		rs.close();
		sm.close();
		disconnect();
		return userList;
	}

	public ArrayList<Users> task_7() throws SQLException {
		connect_func();
		ArrayList<Users> userList =  new ArrayList<>();
		String sql = " select U.*, count(*) as num from Review R, Users U " + 
				"   where R.user_id = U.id AND R.user_id NOT IN(select R1.user_id from Review R1 " + 
				"   where R1.score in('Excellent','Good','Fair')) GROUP BY U.id Having num >=0;" ; 
		sm = connect.createStatement();
		ResultSet rs = sm.executeQuery(sql);
		
		while (rs.next()) {

			id = rs.getInt("id");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			address = rs.getString("address");
			java.sql.Date birthday = rs.getDate("birthday");
			LocalDate now = LocalDate.now();
			Period diff = Period.between(birthday.toLocalDate(), now);
			age = diff.getYears();
			status = rs.getBoolean("status");
			gender = rs.getString("gender");
		
		Users theUser = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,gender);
		   //this is a wild type with one integer and one object
		userList.add(theUser);
		
		}
		rs.close();
		sm.close();
		disconnect();
		return userList;
	
		
	}

	public ArrayList<Users> task_8() throws SQLException {
		connect_func();
		ArrayList<Users> userList =  new ArrayList<>();
		String sql = "  select * from Users where id in ( select J.author_Id from review R, jokes J where R.joke_id = J.id "
				+ " AND R.joke_id not in  ( select R.joke_id from review R where R.score = 'Poor'));" ; 
		sm = connect.createStatement();
		ResultSet rs = sm.executeQuery(sql);
		
		while (rs.next()) {

			id = rs.getInt("id");
			anotherAccount = rs.getString("account");
			anotherPassword = rs.getString("password");
			firstName = rs.getString("firstName");
			lastName = rs.getString("lastName");
			address = rs.getString("address");
			java.sql.Date birthday = rs.getDate("birthday");
			LocalDate now = LocalDate.now();
			Period diff = Period.between(birthday.toLocalDate(), now);
			age = diff.getYears();
			status = rs.getBoolean("status");
			gender = rs.getString("gender");
		
		Users theUser = new Users(id, anotherAccount, anotherPassword, firstName, lastName, address, status, age,gender);
		   //this is a wild type with one integer and one object
		userList.add(theUser);
		
		}
		rs.close();
		sm.close();
		disconnect();
		return userList;
	
	}

	public ArrayList<cheaterPair> task_9() throws SQLException {
		connect_func();
		ArrayList<cheaterPair> pair =  new ArrayList<>();
		
		String C1 = "create view C1 AS select U.id as commenterId1, count(*) as L1, j.author_id from Users U, Review R, Jokes J  where U.id = R.user_id AND R.joke_id = J.id AND score = \"Excellent\" group by R.user_id, J.author_id;";
		String C2 = "create view C2 AS select U.id as commenterId2, count(*) as L2, j.author_id from Users U, Review R, Jokes J where U.id = R.user_id AND R.joke_id = J.id group by R.user_id, J.author_id;";
		String G1 = "create view G1 AS select commenterId1, C1.author_id from C1, C2 where C1.commenterId1 = C2.commenterId2 AND  C1.author_id = C2.author_Id AND L1 = L2;";
		String G2 = "create view G2 AS select commenterId1, C1.author_id from C1, C2 where C1.commenterId1 = C2.commenterId2 AND  C1.author_id = C2.author_Id AND L1 = L2;";
		
		String sql = " select  U.account as cheaterA, U2.account as cheaterB from G1, G2, Users U, Users U2 WHERE U.id = G1.commenterId1 AND U2.id = G2.commenterId1 AND G1.commenterId1 = G2.author_id AND G1.author_id = G2.commenterId1;";
		sm = connect.createStatement();
		ResultSet rs = sm.executeQuery(sql);
		
		
		while (rs.next()) {

			String cheaterA = rs.getString("cheaterA");
			String cheaterB = rs.getString("cheaterB");
			cheaterPair newPair = new cheaterPair(cheaterA, cheaterB);
			pair.add(newPair);
	
		
		   //this is a wild type with one integer and one object
	
		
		}
		rs.close();
		sm.close();
		disconnect();
		return pair;
	}
		

}

//select * from likes where user_id = 1 AND joke_id =1;

// create a method to list out a user's 5 favorite books;

// create another method to list our a user's favorite users.

// i need to create method to request how many jokes or comments on user posts
// today.the
// the maximum everyday is five and one user cannot post more than comment on
// each joke.
// i need to use a date() method maybe.
