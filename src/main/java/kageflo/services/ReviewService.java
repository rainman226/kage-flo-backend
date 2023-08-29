package kageflo.services;

import kageflo.entities.Anime;
import kageflo.entities.Entry;
import kageflo.entities.Review;
import kageflo.entities.User;
import kageflo.repositories.AnimeRepository;
import kageflo.repositories.ReviewRepository;
import kageflo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimeRepository animeRepository;

    public List<Review> findAll() { return reviewRepository.findAll(); }
    public Review addReview(Review review) {
        User managedUser = userRepository.findById(review.getUserID().getId()).orElse(null);
        Anime managedAnime = animeRepository.findById(review.getAnimeID().getId()).orElse(null);
        if (managedUser != null && managedAnime != null) {
            review.setUserID(managedUser);
            review.setAnimeID(managedAnime);

            // Now you can persist the Entry entity
            return reviewRepository.save(review);
        } else {
            // Handle case where User or Anime entity is not found
            return null;
        }
    }

    public List<Review> getReviewsByFields(Integer id,
                                           Integer userID,
                                           Integer animeID) {
        Specification<Review> spec = Specification.where(null);

        if(id != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id));
        }

        if (userID != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userID").get("id"), userID));
        }

        if (animeID != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("animeID").get("id"), animeID));
        }

        List<Review> result = reviewRepository.findAll(spec);

        if (result.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return result;
    }
}
