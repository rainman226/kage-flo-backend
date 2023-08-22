package kageflo.services;

import kageflo.repositories.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
