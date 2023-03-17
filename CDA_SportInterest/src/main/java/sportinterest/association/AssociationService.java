package sportinterest.association;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociationService {

	@Autowired
	AssociationRepository associationRepository;
}
