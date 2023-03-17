package sportinterest.association;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import sportinterest.event.Event;
import sportinterest.role.Role;
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
	@OneToOne
	private User president;
	@OneToMany
	private ArrayList<Event> events;
	
	public Association() {
		
	}
	
	public Association(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/*
	 * Méthode qui retourne tout les users qui ont le role passé en paramètre
	 */
	public ArrayList<User> getUsersByRole(Role role){
		
		return null;
	}
	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<User> getUsers() {
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

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
