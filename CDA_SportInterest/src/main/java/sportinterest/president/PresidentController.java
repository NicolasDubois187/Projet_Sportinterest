package sportinterest.president;

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


@RestController
public class PresidentController {

	@Autowired
	PresidentService presidentService;
	
/**
 * get all presidents	
 * @return
 */
    @GetMapping("presidents")
    public List<President> getPresidents(){
        return presidentService.getPresidents();
    }

/**
 * add one president
 * @param newPresident
 */
    @PostMapping("presidents")
    public void postPresident(@RequestBody President newPresident){
					    
        presidentService.addPresident(newPresident);
    }

/**
 * get one president by his id    
 * @param id
 * @return
 */
    @GetMapping("presidents/{id}")
    public ResponseEntity<President> getOneUser(@PathVariable("id") Integer id){
    	Optional<President> oPresident = presidentService.getOnePresident(id);
    	if(oPresident.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		President p = oPresident.get();
    		return ResponseEntity.ok(p);
    	}
    }

/**
 * update one president by his id    
 * @param id
 * @param president
 */
    @PutMapping("users/{id}")
    public void updatePresident(@PathVariable("id") Integer id, @RequestBody President president) {
    	Optional<President> oPresident = presidentService.getOnePresident(id);
    	if(oPresident.isPresent()) {
					    		
    	presidentService.updatePresident(id, president);
    	}
    }

/**
 * delete one president by his id    
 * @param id
 */
    @DeleteMapping("presidents/{id}")
    public void deletePresident(@PathVariable("id") Integer id) {
    	presidentService.deletePresident(id);
    }

    /**
     * Créer méthodes qui permettent à un président d'ajouter / supprimer des roles (List<Role>) à un utilisateur de son association
     * ( ! attention au NullPointerException )
     */
}
