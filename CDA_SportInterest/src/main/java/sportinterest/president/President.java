package sportinterest.president;

import jakarta.persistence.*;
import sportinterest.user.User;

@Entity
public class President extends User {

	public President() {
		super();
	}
	
	public President(int id, String lastname, String firstname, String mail, String password) {
		super(id, lastname, firstname, mail, password);
	}
}
