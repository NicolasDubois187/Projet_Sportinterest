package sportinterest.article;

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

import sportinterest.user.User;



@RestController
public class ArticleController {
	
	@Autowired
	ArticleService articleService;

/**
 * get all articles	
 * @return
 */
    @GetMapping("articles")
    public List<Article> getArticles(){
        return articleService.getArticles();
    }
    
/**
 * get all articles by association id	
 * @return
 * @param association id
 */
   @GetMapping("articles/association/{id}")
    public List<Article> getArticlesByAssociationId(@PathVariable("id") Integer id){
         return articleService.getArticlesByAssociationId(id);
    }
   
/**
 * add one article
 * @param newArticle
 */
    @PostMapping("articles")
    public void postArticle(@RequestBody Article newArticle){
    
        articleService.addArticle(newArticle);
    }
/**
 * get one article by his id    
 * @param id
 * @return
 */
    @GetMapping("articles/{id}")
    public ResponseEntity<Article> getOneArticle(@PathVariable("id") Integer id){
    	Optional<Article> oArticle = articleService.getOneArticle(id);
    	if(oArticle.isEmpty()) {
    		// 404
    		return ResponseEntity.notFound().build();
    	} else {
    		Article a = oArticle.get();
    		return ResponseEntity.ok(a);
    	}
    }
/**
 * update one article by his id    
 * @param id
 * @param article
 */
    @PutMapping("articles/{id}")
    public void updateArticle(@PathVariable("id") Integer id, @RequestBody Article article) {
    	Optional<Article> oArticle = articleService.getOneArticle(id);
    	if(oArticle.isPresent()) {
    		
    		articleService.updateArticle(id, article);
    	}
    }
/**
 * delete one article by his id    
 * @param id
 */
    @DeleteMapping("articles/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
    	articleService.deleteArticle(id);
    }
    
    
}
