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

    /**
     * Endpoint to add an article to the database.
     *
     * @param article The Article object containing the details of the article to be added.
     * @return A ResponseEntity with a success message if the article is added successfully,
     *         or an error message if there's a problem during the process.
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Article article){
        try {
            articleService.addArticle(article);
            return new ResponseEntity<>("Article added succesfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding article", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to retrieve an article by its ID.
     *
     * @param id The ID of the article to be retrieved.
     * @return A ResponseEntity containing an Optional containing the article with the specified ID,
     *         or an empty Optional if no article is found.
     */
    @GetMapping("/get")
    public ResponseEntity<Optional<Article>> getById(@RequestParam int id){
        Optional<Article> result = articleService.getById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint to retrieve all articles.
     *
     * @return A list of all Article objects.
     */
    @GetMapping("/all")
    public List<Article> getAll(){
        return articleService.getAll();
    }
}
