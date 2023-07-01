package sportinterest.association;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssociationService {

	@Autowired
	AssociationRepository associationRepository;

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
	 * Update attributes of one association
	 * @param id
	 * @param association
	 */
	public void updateAssociation(Integer id, Association association) {

		associationRepository.save(association);
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
