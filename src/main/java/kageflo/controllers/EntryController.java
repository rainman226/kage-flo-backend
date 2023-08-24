package kageflo.controllers;

import jakarta.transaction.Transactional;
import kageflo.entities.Entry;
import kageflo.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @Transactional
    @PostMapping("/save")
    public ResponseEntity<Entry> addEntry(@RequestBody Entry entry){
        Entry result = entryService.addEntry(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/hasEntry")
    public ResponseEntity<Boolean> hasEntry(@RequestParam int userID,
                                            @RequestParam int animeID){
        boolean hasEntry = entryService.hasEntry(userID, animeID);
        return ResponseEntity.ok(hasEntry);
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<List<Entry>> getUserEntries(@PathVariable int id){
        List<Entry> result = entryService.findByUserId(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/getEntryId")
    public ResponseEntity<Integer> getEntryId(@RequestParam int userID,
                                              @RequestParam int animeID){
        int entryID = entryService.getEntryId(userID, animeID);
        return ResponseEntity.ok(entryID);
    }
    @GetMapping("/all")
    public List<Entry> getAllEntries(){
        return entryService.findAll();
    }
}
