package sportinterest.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public void addRole(Role role){
		roleRepository.save(role);
	}
}
