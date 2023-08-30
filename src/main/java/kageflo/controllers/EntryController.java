package kageflo.controllers;

import jakarta.transaction.Transactional;
import kageflo.entities.Entry;
import kageflo.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    EntryService entryService;

    /**
     * Endpoint to save a new entry.
     *
     * @param entry The Entry object containing the details of the entry to be saved.
     * @return A ResponseEntity containing the added Entry object with a status of 201 Created.
     */
    @Transactional
    @PostMapping("/save")
    public ResponseEntity<Entry> addEntry(@RequestBody Entry entry){
        Entry result = entryService.addEntry(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * Endpoint to check if a user has an entry for a specific anime using user ID and anime ID.
     *
     * @param userID The ID of the user.
     * @param animeID The ID of the anime.
     * @return A ResponseEntity containing a boolean indicating whether the user has an entry for the specified anime.
     */
    @GetMapping("/hasEntry")
    public ResponseEntity<Boolean> hasEntry(@RequestParam int userID,
                                            @RequestParam int animeID){
        boolean hasEntry = entryService.hasEntry(userID, animeID);
        return ResponseEntity.ok(hasEntry);
    }

    /**
     * Endpoint to update an existing entry.
     *
     * @param id The ID of the entry to be updated.
     * @param updatedEntry The updated Entry object containing the new details.
     * @return A ResponseEntity containing the updated Entry object if the update is successful,
     *         or a not found response if the entry with the provided ID does not exist.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Entry> updateEntry(@PathVariable int id,
                                             @RequestBody Entry updatedEntry){
        Entry updated = entryService.updateEntry(id, updatedEntry);

        if(updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to retrieve all entries associated with a specific user.
     *
     * @param id The ID of the user whose entries are to be retrieved.
     * @return A ResponseEntity containing a list of Entry objects associated with the specified user.
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Entry>> getUserEntries(@PathVariable int id){
        List<Entry> result = entryService.findByUserId(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    /**
     * Endpoint to retrieve an entry by its ID.
     *
     * @param id The ID of the entry to be retrieved.
     * @return A ResponseEntity containing an Optional containing the entry with the specified ID,
     *         or an empty Optional if no entry is found.
     */
    @GetMapping("/get")
    public ResponseEntity<Optional<Entry>> getEntryById(@RequestParam int id){
        Optional<Entry> result = entryService.getEntryById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint to retrieve the ID of an entry based on the user ID and anime ID.
     *
     * @param userID The ID of the user.
     * @param animeID The ID of the anime.
     * @return A ResponseEntity containing the ID of the entry associated with the specified user and anime,
     *         or a not found response if no entry is found for the provided IDs.
     */
    @GetMapping("/getEntryId")
    public ResponseEntity<Integer> getEntryId(@RequestParam int userID,
                                              @RequestParam int animeID){
        int entryID = entryService.getEntryId(userID, animeID);
        return ResponseEntity.ok(entryID);
    }

    /**
     * Endpoint to delete an entry using its ID.
     *
     * @param id The ID of the entry to be deleted.
     * @return A ResponseEntity containing a success message if the entry is deleted successfully,
     *         or a not found message if no entry is found for the provided ID.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEntry(@RequestParam int id){
        boolean deleted = entryService.deleteEntry(id);

        if (deleted) {
            return ResponseEntity.ok("Entry deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found");
        }
    }

    /**
     * Endpoint to retrieve all entries.
     *
     * @return A list of all Entry objects.
     */
    @GetMapping("/all")
    public List<Entry> getAllEntries(){
        return entryService.findAll();
    }
}
