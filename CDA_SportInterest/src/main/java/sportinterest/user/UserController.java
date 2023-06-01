package sportinterest.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
/**
 * get all users	
 * @return
 */
    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

/**
 * get all users by association id	
 * @return
 * @param association id
 */
     @GetMapping("users/association/{id}")
     public List<User> getUsersByAssociationId(@PathVariable("id") Integer id){
           return userService.getUsersByAssociationId(id);
     }
     
/**
 * add one user
 * @param newUser
 */
    @PostMapping("users")
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
 * get one user by his id    
 * @param id
 * @return
 */
    @GetMapping("users/{id}")
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
 * update one user by his id    
 * @param id
 * @param user
 */
    @PutMapping("users/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
    	Optional<User> oUser = userService.getOneUser(id);
    	if(oUser.isPresent()) {
				    		
    	userService.updateUser(id, user);
    	}
    }

/**
 * delete one user by his id    
 * @param id
 */
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
    	userService.deleteUser(id);
    }
    
    @PostMapping("connection")
    public boolean loginUser(@RequestBody User user) {
    	String mail = user.getMail();
    	String password = user.getPassword();
    	
        // Verify if mail exist
        Optional<User> existingUser = userService.getOneUserByMail(mail);
        if(!existingUser.isPresent()) {
            return false;
        }
        
        // Verify if mail match with password
        if(!existingUser.get().getPassword().equals(password)) {
           return false;
        }
		return true;
    }
}
