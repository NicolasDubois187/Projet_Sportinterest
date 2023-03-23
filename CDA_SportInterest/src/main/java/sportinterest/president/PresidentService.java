package sportinterest.president;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PresidentService {

	@Autowired
	PresidentRepository presidentRepository;
	
/**
 * 
 * @return all presidents
 */
	public List<President> getPresidents() {
							
		return presidentRepository.findAll();
	}
/**
 * add one president	
 * @param president
 */
	public void addPresident(President president) {

		presidentRepository.save(president);
	}
/**
 * get one president by his id	
 * @param id
 * @return
 */
	public Optional<President> getOnePresident(Integer id) {

		return presidentRepository.findById(id);
	}
/**
 * update attributes of one president
 * @param id
 * @param president
 */
	public void updatePresident(Integer id, President president) {

		presidentRepository.save(president);
	}
/**
 * Delete one president by his id	
 * @param id
 */
	public void deletePresident(Integer id) {

		presidentRepository.deleteById(id);
	}	
}
