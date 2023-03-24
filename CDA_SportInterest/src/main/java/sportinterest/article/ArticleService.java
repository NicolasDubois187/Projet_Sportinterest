package sportinterest.article;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
/**
 * 
 * @return all articles
 */
	public List<Article> getArticles() {
		
		return articleRepository.findAll();
	}
/**
 * add one article	
 * @param article
 */
	public void addArticle(Article article) {

		articleRepository.save(article);
	}
/**
 * get one article by his id	
 * @param id
 * @return
 */
	public Optional<Article> getOneArticle(Integer id) {

		return articleRepository.findById(id);
	}
/**
 * update attributes of one article	
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
