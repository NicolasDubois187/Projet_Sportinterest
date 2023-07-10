package sportinterest.association;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequestMapping("association/")
@RestController
public class AssociationController {

	@Autowired
	AssociationService associationService;

    /**
     * Get all associations
     * @return
     */
    @GetMapping("associations")
    public List<Association> getAssociations(){
        return associationService.getAssociations();
    }

    /**
     * Add one association
     * @param newAssociation
     */
    @PostMapping("create")
    public boolean postAssociation(@RequestBody Association newAssociation){
        try {
        associationService.addAssociation(newAssociation);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get one association by his id
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
     * Update one association by his id
     * @param id
     * @param association
     */
    @PutMapping("edit")
    public void updateAssociation(@RequestBody Association associationUpdated) {
    	Optional<Association> oAssociation = associationService.getOneAssociation(associationUpdated.getId());
        oAssociation.ifPresent(association -> associationService.updateAssociation(associationUpdated, association));
    }

    /**
     * Delete one association by his id
     * @param id
     */
    @DeleteMapping("delete/{id}")
    public void deleteAssociation(@PathVariable("id") Integer id) {
    	associationService.deleteAssociation(id);
    }

    /**
     * Get one association by his president id
     * @param id president
     * @return
     */
    @GetMapping("president/{pdtid}")
    public ResponseEntity<Association> getAssociationByPresidentId(@PathVariable("pdtid") Integer id){
    	Optional<Association> oAssociation = associationService.getAssociationByPresidentId(id);
    	if(oAssociation.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		Association a = oAssociation.get();
    		return ResponseEntity.ok(a);
    	}
    }

    /**
     * Get one association by his name
     * @param name
     * @return
     */
    @GetMapping("name/{name}")
    public ResponseEntity<Association> getAssociationbyName(@PathVariable("name") String name){
    	Optional<Association> oAssociation = associationService.getAssociationByName(name);
    	if(oAssociation.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		Association a = oAssociation.get();
    		return ResponseEntity.ok(a);
    	}
    }
}
