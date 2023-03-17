package sportinterest.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("roles")
	public Role getRoles(){
		System.out.println("GettAllRoles");
		return new Role( 1 ,"Président", "Gérant du club");
	}

	@PostMapping("roles")
	public void postRole(Role role){
		System.out.println("New Role");
		roleService.addRole(role);
	}
}
