package sportinterest.article;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequestMapping("article/")
@RestController
public class ArticleController {
	
	@Autowired
	ArticleService articleService;

    /**
     * Get all articles
     * @return
     */
    @GetMapping("articles")
    public List<Article> getArticles(){
        return articleService.getArticles();
    }
    
    /**
     * Get all articles by association id
     * @return
     * @param id association
     */
   @GetMapping("association/{id}")
    public List<Article> getArticlesByAssociationId(@PathVariable("id") Integer id){
         return articleService.getArticlesByAssociationId(id);
    }
   
    /**
     * Add one article
     * @param newArticle
     */
    @PostMapping("create")
    public void postArticle(@RequestBody Article newArticle){
    
        articleService.addArticle(newArticle);
    }

    /**
     * Get one article by his id
     * @param id
     * @return
     */
    @GetMapping("id/{id}")
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
     * Update one article by his id
     * @param id
     * @param article
     */
    @PutMapping("edit/{id}")
    public void updateArticle(@PathVariable("id") Integer id, @RequestBody Article article) {
    	Optional<Article> oArticle = articleService.getOneArticle(id);
    	if(oArticle.isPresent()) {
    		
    		articleService.updateArticle(id, article);
    	}
    }

    /**
     * Delete one article by his id
     * @param id
     */
    @DeleteMapping("delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
    	articleService.deleteArticle(id);
    }

}
