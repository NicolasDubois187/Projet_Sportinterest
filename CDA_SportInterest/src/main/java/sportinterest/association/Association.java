package sportinterest.association;

import jakarta.persistence.*;

import sportinterest.user.User;


@Entity
public class Association {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@OneToOne
	private User president;
	
	public Association() {
		
	}
	
	public Association(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	
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

	public User getPresident() {
		return president;
	}

	public void setPresident(User president) {
		this.president = president;
	}

	
}
