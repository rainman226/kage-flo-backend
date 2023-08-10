package kageflo.repositories;

import kageflo.entities.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByOrderByUsernameAsc();
    List<User> findAll(Specification<User> spec);
}
