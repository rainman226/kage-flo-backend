package kageflo.services;

import kageflo.entities.Anime;
import kageflo.entities.User;
import kageflo.repositories.AnimeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public class AnimeService {
    private AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAllAnime(boolean sorted){
        List<Anime> animes;
        if(sorted) animes = animeRepository.findAllByOrderByTitleAsc();
        else animes = animeRepository.findAll();
        return animes;
    }

    public List<Anime> getAnime(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Anime> animePage = animeRepository.findAll(pageable);
        return animePage.getContent();
    }

    public List<Anime> searchAnimeByTitleIgnoreCase(String keyword){
        String lowercaseKeyword = keyword.toLowerCase();
        List<Anime> animeList = animeRepository.searchAnimeByTitleIgnoreCase(lowercaseKeyword);
        return animeList;
    }
}
