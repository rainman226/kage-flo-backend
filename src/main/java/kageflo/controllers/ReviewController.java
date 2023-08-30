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

    /**
     * Endpoint to save a new review.
     *
     * @param review The Review object containing the details of the review to be saved.
     * @return A ResponseEntity containing the added Review object with a status of 201 Created.
     */
    @Transactional
    @PostMapping("/save")
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        Review result = reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * Endpoint to retrieve reviews based on different fields using query parameters.
     *
     * @param id The unique identifier of the review.
     * @param userID The ID of the user associated with the review.
     * @param animeID The ID of the anime associated with the review.
     * @return A ResponseEntity containing a list of Review objects based on the provided query parameters.
     */
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

    /**
     * Endpoint to retrieve all reviews.
     *
     * @return A list of all Review objects.
     */
    @GetMapping("/all")
    public List<Review> getAllReviews() { return reviewService.findAll(); }
}
