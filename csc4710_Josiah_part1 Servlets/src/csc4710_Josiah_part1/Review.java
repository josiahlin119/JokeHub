package csc4710_Josiah_part1;

import java.sql.Date;

public class Review {
private java.sql.Date create_at;
private	int id;
private String reviewer;
private String comment;
private String rating;

private int jokeId;

public String getComment() {
	return comment;
}



public void setComment(String comment) {
	this.comment = comment;
}



public String getRating() {
	return rating;
}



public void setRating(String rating) {
	this.rating = rating;
}



public Date getCreate_at() {
	return create_at;
}


 
public Review(Date create_at, String reviewer, String comment,String rating, int jokeId) {
	super();
	this.create_at = create_at;
	this.reviewer = reviewer;
	this.jokeId = jokeId;
	this.comment = comment;
	this.rating = rating;
}



public void setCreate_at(java.sql.Date create_at) {
	this.create_at = create_at;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getReviewer() {
	return reviewer;
}



public void setReviewer(String reviewer) {
	this.reviewer = reviewer;
}



public int getJokeId() {
	return jokeId;
}



public void setJokeId(int jokeId) {
	this.jokeId = jokeId;
}
}
