package kageflo.repositories;

import kageflo.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {
    boolean existsByUserID_IdAndAnimeID_Id(int userID, int animeID);
}
