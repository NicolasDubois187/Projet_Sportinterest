package sportinterest.user;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.*;
import sportinterest.association.Association;
import sportinterest.role.Role;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String lastname;
	private String firstname;
	private String mail;
	private String password;
	@ManyToOne
	private Association association;
	@ManyToMany
	protected List<Role> roles = new ArrayList<>();

	public User() {
		
	}

	public User(int id, String lastname, String firstname, String mail, String password, Association association, List<Role> roles) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.mail = mail;
		this.password = password;
		this.association = association;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
