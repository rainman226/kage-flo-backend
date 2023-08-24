package kageflo.repositories;

import kageflo.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {
    boolean existsByUserID_IdAndAnimeID_Id(int userID, int animeID);

    List<Entry> findByUserID_Id(int userID);

    Entry findByUserID_IdAndAnimeID_Id(int userID, int animeID);
}
