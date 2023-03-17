package sportinterest.article;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
}
