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
 * 
 * @return all associations
 */
	public List<Association> getAssociations() {
			
		return associationRepository.findAll();
	}
/**
 * add one association	
 * @param association
 */
	public void addAssociation(Association association) {

		associationRepository.save(association);
	}
/**
 * get one association by his id	
 * @param id
 * @return
 */
	public Optional<Association> getOneAssociation(Integer id) {

		return associationRepository.findById(id);
	}
/**
 * update attributes of one association
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
	
}
