package sportinterest.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
/**
 * 
 * @return all roles
 */
	public List<Role> getRoles() {
						
		return roleRepository.findAll();
	}
/**
 * add one role	
 * @param role
 */
	public void addRole(Role role){
		
		roleRepository.save(role);
	}
/**
 * get one role by his id	
 * @param id
 * @return
 */
	public Optional<Role> getOneRole(Integer id) {

		return roleRepository.findById(id);
	}
/**
 * update attributes of one role
 * @param id
 * @param role
 */
	public void updateRole(Integer id, Role role) {

		roleRepository.save(role);
	}
/**
 * Delete one role by his id	
 * @param id
 */
	public void deleteRole(Integer id) {

		roleRepository.deleteById(id);
	}
}
