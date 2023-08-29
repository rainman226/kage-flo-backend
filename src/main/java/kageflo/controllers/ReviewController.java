package kageflo.controllers;

import jakarta.transaction.Transactional;
import kageflo.entities.Review;
import kageflo.services.ReviewService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Transactional
    @PostMapping("/save")
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        Review result = reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviewsByFields(@RequestParam(required = false) Integer id,
                                                           @RequestParam(required = false) Integer userID,
                                                           @RequestParam(required = false) Integer animeID){
        List<Review> result = reviewService.getReviewsByFields(id, userID, animeID);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
    @GetMapping("/all")
    public List<Review> getAllReviews() { return reviewService.findAll(); }
}
