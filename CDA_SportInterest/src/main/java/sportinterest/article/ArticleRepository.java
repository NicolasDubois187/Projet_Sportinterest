package sportinterest.article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

	List<Article> findArticlesByAssociationId(Integer id);
	
	List<Article> findArticlesByAuthorId(Integer id);
}
