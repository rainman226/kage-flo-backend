package kageflo.services;

import kageflo.entities.Anime;
import kageflo.entities.User;
import kageflo.repositories.AnimeRepository;
import org.springframework.stereotype.Service;

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
}
