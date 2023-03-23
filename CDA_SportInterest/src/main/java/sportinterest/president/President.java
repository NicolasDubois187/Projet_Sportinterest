package sportinterest.president;

import jakarta.persistence.*;

import sportinterest.role.Role;
import sportinterest.user.User;

import java.util.ArrayList;

@Entity
public class President extends User {

	public President() {
		super();
	}
	
	public President(int id, String lastname, String firstname, String mail, String password, ArrayList<Role> roles) {
		super(id, lastname, firstname, mail, password, roles);
	}
}
