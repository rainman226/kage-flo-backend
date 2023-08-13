package kageflo.controllers;

import kageflo.entities.Anime;
import kageflo.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping("/getAnime")
    public ResponseEntity<List<Anime>> getAnime(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "5") int pageSize){
        List<Anime> animes = animeService.getAnime(page, pageSize);
        return ResponseEntity.ok(animes);
    }

    @GetMapping("/all")
    public List<Anime> getAllAnime(@RequestParam( defaultValue = "false", required = false) boolean sorted){
        return animeService.getAllAnime(sorted);
    }
}
