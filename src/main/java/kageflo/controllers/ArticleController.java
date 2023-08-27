package kageflo.controllers;

import kageflo.entities.Article;
import kageflo.entities.Entry;
import kageflo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/get")
    public ResponseEntity<Optional<Article>> getById(@RequestParam int id){
        Optional<Article> result = articleService.getById(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/all")
    public List<Article> getAll(){
        return articleService.getAll();
    }
}
