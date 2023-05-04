package sportinterest.president;

import jakarta.persistence.*;
import sportinterest.association.Association;
import sportinterest.user.User;


@Entity
public class President extends User {
	
	@OneToOne
	private Association association;
	
	public President() {
		super();
	}
	
}
