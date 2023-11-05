package kageflo.services;

import kageflo.entities.Anime;
import java.util.List;

public interface AnimeService {

    List<Anime> getAllAnime(boolean sorted);

    List<Anime> getAnime(int page, int pageSize);

    List<Anime> searchAnimeByTitleIgnoreCase(String keyword);

    List<Anime> getAnimeByFields(Integer id,
                                        String title,
                                        String startDate,
                                        String type,
                                        Integer episodes,
                                        String status,
                                        String source,
                                        String studio);

    void addAnime(Anime anime);
}
