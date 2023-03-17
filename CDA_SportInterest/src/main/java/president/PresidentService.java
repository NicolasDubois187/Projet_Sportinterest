package president;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresidentService {

	@Autowired
	PresidentRepository presidentRepository;
}
