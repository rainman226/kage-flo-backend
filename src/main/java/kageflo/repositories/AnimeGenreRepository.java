package kageflo.repositories;

import kageflo.entities.AnimeGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeGenreRepository extends JpaRepository<AnimeGenre, AnimeGenre.AnimeGenreID> {
}
