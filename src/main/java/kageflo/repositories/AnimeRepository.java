package kageflo.repositories;

import kageflo.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {
    List<Anime> findAllByOrderByTitleAsc();
}
