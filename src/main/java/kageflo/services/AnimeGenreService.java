package kageflo.services;

import kageflo.entities.AnimeGenre;
import kageflo.repositories.AnimeGenreRepository;
import kageflo.repositories.AnimeRepository;
import kageflo.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeGenreService {
    AnimeGenreRepository animeGenreRepository;
    AnimeRepository animeRepository;
    GenreRepository genreRepository;

    public AnimeGenreService(AnimeGenreRepository animeGenreRepository,
                             AnimeRepository animeRepository,
                             GenreRepository genreRepository) {
        this.animeGenreRepository = animeGenreRepository;
        this.animeRepository = animeRepository;
        this.genreRepository = genreRepository;
    }

    public List<AnimeGenre> getAll(){
        List<AnimeGenre> genres = animeGenreRepository.findAll();
        return genres;
    }
}
