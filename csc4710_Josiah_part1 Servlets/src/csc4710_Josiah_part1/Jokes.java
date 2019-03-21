package csc4710_Josiah_part1;

import java.sql.Date;

public class Jokes {
	
	private int id, authorId;
	private String title,content,description, authorName;
	private java.sql.Date create_at;
	private String tag;
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	protected Jokes(int id, int authorId, String authorName, String title, String content, String description, Date create_at) {
		super();
		this.authorName = authorName;
		this.id = id;
		this.authorId = authorId;
		this.title = title;
		this.content = content;
		this.description = description;
		this.create_at = create_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.sql.Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(java.sql.Date create_at) {
		this.create_at = create_at;
	}

	
	
}
