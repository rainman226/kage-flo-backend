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

public interface ReviewService {
    public List<Review> findAll();
    public Review addReview(Review review);

    public List<Review> getReviewsByFields(Integer id,
                                           Integer userID,
                                           Integer animeID);
}
