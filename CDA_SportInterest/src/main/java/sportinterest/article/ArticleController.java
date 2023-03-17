package sportinterest.article;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
    @GetMapping("articles")
    public List<Article> getArticles(){
        return articleService.getArticles();
    }
}
