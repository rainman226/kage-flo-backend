package kageflo.repositories;

import kageflo.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {
    List<Anime> findAllByOrderByTitleAsc();

    @Query("SELECT a FROM Anime a WHERE LOWER(a.title) LIKE %:keyword%")
    List<Anime> searchAnimeByTitleIgnoreCase(String keyword);
}
