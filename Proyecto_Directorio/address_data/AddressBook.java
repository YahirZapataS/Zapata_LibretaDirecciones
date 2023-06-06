package address_data;

import address_data.AddressEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBook {
    private List<AddressEntry> entries;

    public AddressBook() {
        entries = new ArrayList<>();
    }

    public void addEntry(AddressEntry entry) {
        entries.add(entry);
    }

    public void removeEntry(AddressEntry entry) {
        entries.remove(entry);
    }

    public List<AddressEntry> searchEntriesByApellido(String Apellido) {
        List<AddressEntry> searchResults = new ArrayList<>();
        for (AddressEntry entry : entries) {
            if (entry.getApellido().startsWith(Apellido)) {
                searchResults.add(entry);
            }
        }
        return searchResults;
    }

    public List<AddressEntry> getAllEntries() {
        return entries;
    }

    public void sortEntriesByApellido() {
        Collections.sort(entries, (entry1, entry2) -> entry1.getApellido().compareToIgnoreCase(entry2.getApellido()));
    }
}