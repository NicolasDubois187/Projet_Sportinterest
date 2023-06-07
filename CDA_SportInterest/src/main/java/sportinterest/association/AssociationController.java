package sportinterest.association;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping("association/")
@RestController
public class AssociationController {

	@Autowired
	AssociationService associationService;

/**
 * get all associations
 * @return
 */
    @GetMapping("associations")
    public List<Association> getAssociations(){
        return associationService.getAssociations();
    }
/**
 * add one association
 * @param newAssociation
 */
    @PostMapping("create")
    public void postAssociation(@RequestBody Association newAssociation){
	    
        associationService.addAssociation(newAssociation);
    }

/**
 * get one association by his id    
 * @param id
 * @return
 */
    @GetMapping("id/{id}")
    public ResponseEntity<Association> getOneAssociation(@PathVariable("id") Integer id){
    	Optional<Association> oAssociation = associationService.getOneAssociation(id);
    	if(oAssociation.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		Association a = oAssociation.get();
    		return ResponseEntity.ok(a);
    	}
    }

/**
 * update one association by his id    
 * @param id
 * @param association
 */
    @PutMapping("edit/{id}")
    @PreAuthorize(("hasRole('president')"))
    public void updateAssociation(@PathVariable("id") Integer id, @RequestBody Association association) {
    	Optional<Association> oAssociation = associationService.getOneAssociation(id);
    	if(oAssociation.isPresent()) {
    
    		associationService.updateAssociation(id, association);
    	
    	}
    }
/**
 * delete one association by his id    
 * @param id
 */
    @DeleteMapping("delete/{id}")
    public void deleteAssociation(@PathVariable("id") Integer id) {
    	associationService.deleteAssociation(id);
    }
	
}
