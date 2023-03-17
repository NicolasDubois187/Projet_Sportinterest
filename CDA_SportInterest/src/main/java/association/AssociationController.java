package association;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssociationController {

	@Autowired
	AssociationService associationService;
}
