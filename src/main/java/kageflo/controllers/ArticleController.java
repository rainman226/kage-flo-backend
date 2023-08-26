package kageflo.controllers;

import kageflo.entities.Article;
import kageflo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Article article){
        try {
            articleService.addArticle(article);
            return new ResponseEntity<>("Article added succesfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding article", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public List<Article> getAll(){
        return articleService.getAll();
    }
}
