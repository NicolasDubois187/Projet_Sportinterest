package sportinterest.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sportinterest.security.token.Token;
import sportinterest.security.token.TokenRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TokenRepository tokenRepository;

	/**
	 * Get all users
	 * @return all users
	 */
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	/**
	 * Get all users by association id
	 * @param id association
	 * @return
	 */
	public List<User> getUsersByAssociationId(Integer id) {
		
		return userRepository.findUsersByAssociationId(id);
	}
		
	/**
	 * Add one user
	 * @param user
	 */
	public void addUser(User user) {

		userRepository.save(user);
	}

	/**
	 * Get one user by his id
	 * @param id
	 * @return
	 */
	public Optional<User> getOneUser(Integer id) {

		return userRepository.findById(id);
	}
	
	/**
	 * Get one user by his mail
	 * @param mail
	 * @return
	 */
	public Optional<User> getOneUserByMail(String mail) {
	
		return userRepository.findByMail(mail);
	}

	/**
	 * Update one user by his id
	 * @param newUser
	 * @param existingUser
	 */
	public void updateUser(User newUser, User existingUser) {
		if ((newUser.getFirstname() != null) && !newUser.getFirstname().equals(existingUser.getFirstname()))
			existingUser.setFirstname(newUser.getFirstname());
		if ((newUser.getLastname() != null) && !newUser.getLastname().equals(existingUser.getLastname()))
			existingUser.setLastname(newUser.getLastname());
		if (newUser.getAssociation() != null)
			existingUser.setAssociation(newUser.getAssociation());
		if ((newUser.getRole() != null ) && !newUser.getRole().equals(existingUser.getRole()))
			existingUser.setRole(newUser.getRole());
		userRepository.save(existingUser);
	}

	/**
	 * Delete one user by his id
	 * @param id
	 */
	public void deleteUser(Integer id) {
		List<Token> tokens = tokenRepository.findAllByUserId(id);

		for (Token token : tokens) {
			token.setUser(null);
			token.setRevoked(true);
			tokenRepository.save(token);
		}

		userRepository.deleteById(id);
	}

	/**
	 * Get the current user from the security context
	 * @return
	 */
	public static ResponseEntity<UserDetails> getCurrentUser() {
		Object test = (UserDetails) ((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getPrincipal();

		System.out.println(test);

		try {
			UserDetails result = (UserDetails) test;
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}


	}

	/**
	 * Check if a mail is already taken
	 * @param mail
	 * @return
	 */
	public boolean mailTaken(String mail) {
		Optional<User> oUser = userRepository.findByMail(mail);
		return oUser.isPresent();
	}
}
