package kageflo.services;

import kageflo.entities.Anime;
import kageflo.entities.Entry;
import kageflo.entities.User;
import kageflo.repositories.AnimeRepository;
import kageflo.repositories.EntryRepository;
import kageflo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    @Autowired
    EntryRepository entryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimeRepository animeRepository;

    public EntryService(EntryRepository entryRepository, UserRepository userRepository, AnimeRepository animeRepository) {
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
        this.animeRepository = animeRepository;
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public Entry addEntry(Entry entry) {
        User managedUser = userRepository.findById(entry.getUserID().getId()).orElse(null);
        Anime managedAnime = animeRepository.findById(entry.getAnimeID().getId()).orElse(null);
        if (managedUser != null && managedAnime != null) {
            entry.setUserID(managedUser);
            entry.setAnimeID(managedAnime);

            // Now you can persist the Entry entity
            Entry savedEntry = entryRepository.save(entry);
            return savedEntry;
        } else {
            // Handle case where User or Anime entity is not found
            return null;
        }
    }

    public Entry updateEntry(int id, Entry updatedEntry) {
        Entry existingEntry = entryRepository.findById(id).orElse(null);

        if (existingEntry != null) {
            // Update the fields in existingEntry based on updatedEntry
            // Perform necessary business logic
            existingEntry.setStatus(updatedEntry.getStatus());
            existingEntry.setWatchedEpisodes(updatedEntry.getWatchedEpisodes());
            existingEntry.setGrade(updatedEntry.getGrade());

            // Save and return the updated entry
            return entryRepository.save(existingEntry);
        } else {
            return null;
        }
    }

    public boolean hasEntry(int userID, int animeID) {
        return entryRepository.existsByUserID_IdAndAnimeID_Id(userID, animeID);
    }
}