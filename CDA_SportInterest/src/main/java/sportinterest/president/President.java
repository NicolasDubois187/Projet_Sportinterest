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

}
