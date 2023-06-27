package sportinterest.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequestMapping("user/")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
    /**
     * Get all users
     * @return
     */
    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * Get all users by association id
     * @return
     * @param id association
     */
     @GetMapping("association/{id}")
     public List<User> getUsersByAssociationId(@PathVariable("id") Integer id){
           return userService.getUsersByAssociationId(id);
     }
     
    /**
     * Add one user
     * @param newUser
     */
    @PostMapping("create")
    public boolean postUser(@RequestBody User newUser){
		Optional<User> oUser = userService.getOneUserByMail(newUser.getMail());
		if (oUser.isPresent()) {
			return false;
		} else {
			userService.addUser(newUser);	
			return true;
		}
	}

    /**
     * Get one user by his id
     * @param id
     * @return
     */
    @GetMapping("id/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable("id") Integer id){
    	Optional<User> oUser = userService.getOneUser(id);
    	if(oUser.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		User u = oUser.get();
    		return ResponseEntity.ok(u);
    	}
    }

    /**
     * Update one user by his id
     * @param id
     * @param user
     */
    @PutMapping("edit/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
    	Optional<User> oUser = userService.getOneUser(id);
    	if(oUser.isPresent()) {
				    		
    	userService.updateUser(id, user);
    	}
    }

    /**
     * Delete one user by his id
     * @param id
     */
    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
    	userService.deleteUser(id);
    }

    /**
     * Get all roles
     * @param
     * @return
     */
    @GetMapping("roles")
    public ERole[] getRoles(){
        return ERole.values();
    }

    /**
     * Get one user by his mail
     * @param mail
     * @return
     */
    @GetMapping("mail/{mail}")
    public ResponseEntity<User> getOneUserByMail(@PathVariable("mail") String mail){
    	Optional<User> oUser = userService.getOneUserByMail(mail);
    	if(oUser.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		User u = oUser.get();
    		return ResponseEntity.ok(u);
    	}
    }
}
