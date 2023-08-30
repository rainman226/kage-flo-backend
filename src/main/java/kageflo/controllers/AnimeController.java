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

    /**
     * Endpoint to retrieve a given number of anime using pagination.
     *
     * @param page The page number for pagination (default: 1).
     * @param pageSize The number of items per page (default: 15).
     * @return A ResponseEntity containing a list of Anime objects based on the provided pagination.
     */
    @GetMapping("/getAnime")
    public ResponseEntity<List<Anime>> getAnime(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "15") int pageSize){
        List<Anime> animes = animeService.getAnime(page, pageSize);
        return ResponseEntity.ok(animes);
    }

    /**
     * Endpoint to retrieve anime based on different fields using query parameters.
     *
     * @param id The unique identifier of the anime.
     * @param title The title of the anime.
     * @param startDate The start date of the anime's release.
     * @param type The type of the anime (e.g., TV, Movie, OVA).
     * @param episodes The number of episodes in the anime.
     * @param status The status of the anime (e.g., WATCHING, COMPLETED, ONHOLD, DROPPED, PTW).
     * @param source The source material of the anime (e.g., Manga, Light Novel).
     * @param studio The studio responsible for producing the anime.
     * @return A ResponseEntity containing a list of Anime objects based on the provided query parameters.
     */
    @GetMapping
    public ResponseEntity<List<Anime>> getAnimeByFields(@RequestParam(required = false) Integer id,
                                                        @RequestParam(required = false) String title,
                                                        @RequestParam(required = false) String startDate,
                                                        @RequestParam(required = false) String type,
                                                        @RequestParam(required = false) Integer episodes,
                                                        @RequestParam(required = false) String status,
                                                        @RequestParam(required = false) String source,
                                                        @RequestParam(required = false) String studio){
        List<Anime> animeList = animeService.getAnimeByFields(id, title, startDate, type, episodes, status, source, studio);

        if(animeList.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(animeList);
        }
    }

    /**
     * Endpoint to search for anime by titles.
     *
     * @param keyword The partial or full title to search for in anime.
     * @return A ResponseEntity containing a list of Anime objects that match the search criteria.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Anime>> searchAnime(@RequestParam String keyword){
        List<Anime> animeList = animeService.searchAnimeByTitleIgnoreCase(keyword);
        return ResponseEntity.ok(animeList);
    }

    /**
     * Endpoint to retrieve all anime, optionally sorted by title.
     *
     * @param sorted Set to true to sort the anime by title (default: false).
     * @return A list of all Anime objects, potentially sorted by title if requested.
     */
    @GetMapping("/all")
    public List<Anime> getAllAnime(@RequestParam( defaultValue = "false", required = false) boolean sorted){
        return animeService.getAllAnime(sorted);
    }
}