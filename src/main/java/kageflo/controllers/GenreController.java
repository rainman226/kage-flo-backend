package kageflo.controllers;

import kageflo.entities.AnimeGenre;
import kageflo.services.AnimeGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    AnimeGenreService animeGenreService;

    @GetMapping("/all")
    public List<AnimeGenre> getAllAnimeGenre(){
        return animeGenreService.getAll();
    }
}
