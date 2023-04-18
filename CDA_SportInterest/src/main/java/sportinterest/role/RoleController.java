package sportinterest.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sportinterest.user.User;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;
/**
 * get all roles	
 * @return
 */
	@GetMapping("roles")
	public List<Role> getRoles(){
		
		return roleService.getRoles();
	}
	
/**
 * add one role
 * @param newRole
 */
	@PostMapping("roles")
	public void postRole(Role newRole){
		
		roleService.addRole(newRole);
	}
	
/**
 * get one role by his id    
 * @param id
 * @return
 */
    @GetMapping("roles/{id}")
    public ResponseEntity<Role> getOneUser(@PathVariable("id") Integer id){
    	Optional<Role> oRole = roleService.getOneRole(id);
    	if(oRole.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		Role r = oRole.get();
    		return ResponseEntity.ok(r);
    	}
    }
/**
 * update one role by his id    
 * @param id
 * @param role
 */
    @PutMapping("roles/{id}")
    public void updateRole(@PathVariable("id") Integer id, @RequestBody Role role) {
    	Optional<Role> oRole = roleService.getOneRole(id);
    	if(oRole.isPresent()) {
				    		
    	roleService.updateRole(id, role);
    	}
    }
/**
* delete one role by his id    
* @param id
*/
    @DeleteMapping("roles/{id}")
    public void deleteRole(@PathVariable("id") Integer id) {
    	roleService.deleteRole(id);
    }
}    
