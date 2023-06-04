package sportinterest.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sportinterest.article.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByMail(String mail);

	List<User> findUsersByAssociationId(Integer id);
		
}
