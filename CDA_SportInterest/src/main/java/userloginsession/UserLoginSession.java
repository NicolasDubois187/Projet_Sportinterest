package userloginsession;

import java.sql.Date;

import sportinterest.user.User;

public class UserLoginSession {
	
	private User user;
	private Date connectionDate;
	
	public UserLoginSession() {
		
	}
	
	public UserLoginSession(User user, Date connectionDate) {
		super();
		this.user = user;
		this.connectionDate = connectionDate;
	}
		

}
