package csc4710_Josiah_part1;

import java.util.ArrayList;

public class Users {
	
	public  int id ;//this is the id that is automatically created when creating the user to help mysql to do searching. 
	
	protected String account,password, firstName, lastName, address,email;
	protected String fullName;
	protected int age;
	protected boolean status = true; /*to check if the existed user is banned
	or not. only the administer has the authority to check this status. 
	*/
	//protected ArrayList<String>favoriteJokes = new ArrayList();
	protected boolean exist;   //this is to check if the user exists. 

	private String gender;

	private int numberOfJokes;
	
//This is used to create a new user.   

	//this is a contructor to build a friend instance; 
	protected Users(int id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
	}
	
	 // Use the below one when DAO is trying to pull all user info out of database and pass
 // this user object to the Servlet.
	
	public String getAccount() {
		return account;
	}
	

	public String getFullName() {
		fullName = firstName + " " + lastName;
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public  int getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public  void setId(int id) {
		this.id = id;
	}


	public boolean isAccess() {
		return status;
	}
	public boolean isExist() {
		if(account !=null || password !=null) {
		
		return true;
		}
		else {
			return false;
		}
	}
	


	public void setStatus(boolean status) {
		this.status = status;
	}
public boolean getStatus() {
	return status;
}

	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

//
//	public ArrayList<String> getFavoriteJokes() {
//		return favoriteJokes;
//	}


//	public void setFavoriteJokes(ArrayList<String> favoriteJokes) {
//		this.favoriteJokes = favoriteJokes;
//	}


	protected Users(String account, String address, int age){
		super();
		this.account = account;
		this.address = address;
		this.age = age;
		
	}

	public Users(int id, String anotherAccount, String anotherPassword, String firstName, String lastName,
			String address, boolean status, int age, String gender) {
		this.id = id; // id is generated by mysql
		this.account = anotherAccount;
		this.password = anotherPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.setGender(gender);
		this.status = status;
		this.age = age;
	}
	public Users(int id, String anotherAccount, String anotherPassword, String firstName, String lastName,
			String address, boolean status, int age, String gender, int numberOfJokes) {
		 
			this.id = id; // id is generated by mysql
			this.account = anotherAccount;
			this.password = anotherPassword;
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.setGender(gender);
			this.status = status;
			this.age = age;
			this.numberOfJokes = numberOfJokes;
		}
	public int getNumberOfJokes() {
		return numberOfJokes;
	}

	public void setNumberOfJokes(int numberOfJokes) {
		this.numberOfJokes = numberOfJokes;
	}
	

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
}
