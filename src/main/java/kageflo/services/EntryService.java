package kageflo.services;

import kageflo.entities.Entry;

import java.util.List;
import java.util.Optional;

public interface EntryService {
    public List<Entry> findAll();

    public Entry addEntry(Entry entry);

    public Entry updateEntry(int id, Entry updatedEntry);

    public List<Entry> findByUserId(int userID);
    public boolean hasEntry(int userID, int animeID);

    public Optional<Entry> getEntryById(int id);
    public int getEntryId(int userID, int animeID);

    public boolean deleteEntry(int id);
}
