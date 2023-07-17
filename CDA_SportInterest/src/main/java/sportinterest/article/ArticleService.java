package sportinterest.article;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	/**
	 * Get all articles
	 * @return
	 */
	public List<Article> getArticles() {
		
		return articleRepository.findAll();
	}
	
	/**
	 * Get all articles by association id
	 * @param id
	 * @return
	 */
	public List<Article> getArticlesByAssociationId(Integer id) {
			
			return articleRepository.findArticlesByAssociationId(id);
	}
	
	/**
	 * Add one article
	 * @param article
	 */
	public void addArticle(Article article) {
		article.setCreationDate(new Timestamp(new Date().getTime()));
		articleRepository.save(article);
	}

	/**
	 * Get one article by his id
	 * @param id
	 * @return
	 */
	public Optional<Article> getOneArticle(Integer id) {

		return articleRepository.findById(id);
	}

	/**
	 * Update attributes of one article
	 * @param id
	 * @param article
	 */
	public void updateArticle(Integer id, Article article) {

		articleRepository.save(article);
	}

	/**
	 * Delete one article by his id
	 * @param id
	 */
	public void deleteArticle(Integer id) {
		articleRepository.deleteById(id);
	}
	
}
