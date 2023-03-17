package article;

import java.sql.Date;

import association.Association;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import user.User;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Date date;
	private String description;
	private Association association;
	private User author;
	
	public Article() {
		
	}
	
	public Article(int id, String name, Date date, String description, Association association, User author) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.association = association;
		this.author = author;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Association getAssociation() {
		return association;
	}
	
	public void setAssociation(Association association) {
		this.association = association;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
}
