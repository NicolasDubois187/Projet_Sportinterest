package sportinterest.association;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sportinterest.user.ERole;
import sportinterest.user.User;
import sportinterest.user.UserRepository;
import sportinterest.user.UserService;


@Service
public class AssociationService {

	@Autowired
	AssociationRepository associationRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;


	/**
	 * Get all associations
	 * @return
	 */
	public List<Association> getAssociations() {
		return associationRepository.findAll();
	}

	/**
	 * Add one association
	 * @param association
	 */
	public void addAssociation(Association association) {

		associationRepository.save(association);

//		Object test = (UserDetails) ((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
//
//		try {
//			User president = (User) test;
//			assert president != null;
//			president.setRole(ERole.ADMIN);
//
//			associationRepository.save(association);
//			userRepository.save((User) president);
//		} catch (Exception e) {
//			System.out.println("Error while adding association");
//		}
	}

	/**
	 * Get one association by his id
	 * @param id
	 * @return
	 */
	public Optional<Association> getOneAssociation(Integer id) {

		return associationRepository.findById(id);
	}

	/**
	 * Update one association
	 * @param associationUpdated
	 * @param existingAssociation
	 */
	public void updateAssociation(Association associationUpdated, Association existingAssociation) {
		if (associationUpdated.getDescription() != null && !associationUpdated.getDescription().equals(existingAssociation.getDescription())) {
			existingAssociation.setDescription(associationUpdated.getDescription());
		}
		if (associationUpdated.getPresidentId() != 0 && associationUpdated.getPresidentId() != existingAssociation.getPresidentId()) {
			existingAssociation.setPresidentId(associationUpdated.getPresidentId());
		}
		associationRepository.save(existingAssociation);
	}

	/**
	 * Delete one association by his id
	 * @param id
	 */
	public void deleteAssociation(Integer id) {
		associationRepository.deleteById(id);
	}

	/**
	 * Get all associations by president id
	 * @param id
	 * @return
	 */
    public Optional<Association> getAssociationByPresidentId(Integer id) {
		return associationRepository.findByPresidentId(id);
    }

	/**
	 * Get association by name
	 * @param name
	 * @return
	 */
    public Optional<Association> getAssociationByName(String name) {
		return associationRepository.findByName(name);
    }
}
