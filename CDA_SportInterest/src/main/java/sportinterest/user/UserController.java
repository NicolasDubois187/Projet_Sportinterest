package sportinterest.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
     * @param user
     */
    @PutMapping("edit")
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            Optional<User> oUser = userService.getOneUser(user.getId());
            if(oUser.isPresent()){
                userService.updateUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Delete one user by his id
     * @param id
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Integer id) {
        try {
    	    userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
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

    /**
     * Get current user
     */
    @GetMapping("current")
    public ResponseEntity<UserDetails> getCurrentUser(){
        return UserService.getCurrentUser();
    }
}
