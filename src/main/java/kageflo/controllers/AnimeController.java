package kageflo.controllers;

import kageflo.entities.Anime;
import kageflo.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    /**
     * Endpoint to add an anime to the database.
     *
     * @param anime The Anime object containing the details of the anime to be added.
     * @return A ResponseEntity with a success message if the anime is added successfully,
     *         or an error message if there's a problem during the process.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addAnime(@RequestBody Anime anime){
        try {
            animeService.addAnime(anime);
            return new ResponseEntity<>("Anime added succesfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding anime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAnime")
    public ResponseEntity<List<Anime>> getAnime(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "15") int pageSize){
        List<Anime> animes = animeService.getAnime(page, pageSize);
        return ResponseEntity.ok(animes);
    }

    @GetMapping
    public ResponseEntity<List<Anime>> getAnimeByFields(@RequestParam(required = false) Integer id,
                                                        @RequestParam(required = false) String title,
                                                        @RequestParam(required = false) String startDate,
                                                        @RequestParam(required = false) String type,
                                                        @RequestParam(required = false) Integer episodes,
                                                        @RequestParam(required = false) String status,
                                                        @RequestParam(required = false) String source,
                                                        @RequestParam(required = false) String studio) {
        List<Anime> animeList = animeService.getAnimeByFields(id, title, startDate, type, episodes, status, source, studio);

        if(animeList.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(animeList);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Anime>> searchAnime(@RequestParam String keyword) {
        List<Anime> animeList = animeService.searchAnimeByTitleIgnoreCase(keyword);
        return ResponseEntity.ok(animeList);
    }

    @GetMapping("/all")
    public List<Anime> getAllAnime(@RequestParam( defaultValue = "false", required = false) boolean sorted){
        return animeService.getAllAnime(sorted);
    }
}