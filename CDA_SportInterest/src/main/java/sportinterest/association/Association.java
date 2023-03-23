package sportinterest.association;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import sportinterest.event.Event;
import sportinterest.user.User;


@Entity
public class Association {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@OneToMany
	private ArrayList<User> users;
//	private List<User> users = new ArrayList<>();
	@OneToOne
	private User president;
	@OneToMany
	private ArrayList<Event> events;
//	private List<Event> events = new ArrayList<>();
	
	public Association() {
		
	}
	
	public Association(int id, String name, String description, ArrayList<User> users, User president, ArrayList<Event> events) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.users = users;
		this.president = president;
		this.events = events;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public User getPresident() {
		return president;
	}

	public void setPresident(User president) {
		this.president = president;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

}
