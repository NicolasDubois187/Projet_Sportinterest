package sportinterest.article;

import java.util.Date;

import jakarta.persistence.*;

import sportinterest.association.Association;
import sportinterest.user.User;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(length = 1000)
	private String description;
	@ManyToOne
	private Association association;
	@ManyToOne
	private User author;
	private Date creationDate;
	
	public Article() {
		
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date date) {
		this.creationDate = date;
	}
}
