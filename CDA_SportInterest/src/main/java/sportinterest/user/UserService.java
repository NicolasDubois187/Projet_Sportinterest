package sportinterest.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

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
	 * Update attributes of one user
	 * @param id
	 * @param user
	 */
	public void updateUser(Integer id, User user) {

		userRepository.save(user);
	}

	/**
	 * Delete one user by his id
	 * @param id
	 */
	public void deleteUser(Integer id) {

		userRepository.deleteById(id);
	}	
}
