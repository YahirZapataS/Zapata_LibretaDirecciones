package address_data_test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import address_data.AddressEntry;

public class AddressBookTest {
    private List<AddressEntryTest> entries;
    private String filePath;

    public AddressBookTest(String filePath) {
        entries = new ArrayList<>();
        this.filePath = filePath;
        cargarEntriesFromFile();
    }

    public void addEntry(AddressEntryTest entry) {
        if (NoDuplicado(entry)) {
            System.out.println("---------------------------------------");
            System.out.println("El contacto ya existe en tu Directorio");
            System.out.println("---------------------------------------");
        } else {
            entries.add(entry);
            saveEntriesFiles();
            System.out.println("---------------------------------------");
            System.out.println("¡Contacto agregado con exito!");
            System.out.println("---------------------------------------");

        }
    }

    private boolean NoDuplicado(AddressEntryTest entry) {
        for (AddressEntryTest existingEntry : entries) {
            if (existingEntry.getNombre().equals(entry.getNombre())
                    && existingEntry.getApellido().equals(entry.getApellido())) {
                return true;
            }
        }
        return false;
    }

    public void removeEntry(AddressEntryTest entry) {
        entries.remove(entry);
        saveEntriesFiles();
        System.out.println("Contacto Eliminado Exitosamente");
    }

    public List<AddressEntryTest> searchEntriesByApellidogetApellido(String ApellidogetApellido) {
        List<AddressEntryTest> searchResults = new ArrayList<>();
        for (AddressEntryTest entry : entries) {
            if (entry.getApellido().startsWith(ApellidogetApellido)) {
                searchResults.add(entry);
            }
        }
        return searchResults;
    }

    public List<AddressEntryTest> getAllEntries() {
        return entries;
    }

    private void cargarEntriesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                AddressEntryTest entry = createAddressEntryTestFromString(line);
                if (entry != null) {
                    entries.add(entry);
                }
            }
        } catch (IOException e) {
            System.out.println("¡Error! No se han podido cargar los datos del Directorio");
        }
    }

    private void saveEntriesFiles() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (AddressEntryTest entry : entries) {
                writer.write(entryToString(entry));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("¡Error! No se han podido guardar los cambios.");
        }
    }

    private AddressEntryTest createAddressEntryTestFromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 8) {
            String Nombre = parts[0];
            String ApellidogetApellido = parts[1];
            String Calle = parts[2];
            String Ciudad = parts[3];
            String Estado = parts[4];
            String codigoPostal = parts[5];
            String email = parts[6];
            String Telefono = parts[7];
            return new AddressEntryTest(Nombre, ApellidogetApellido, Calle, Ciudad, Estado, codigoPostal, email,
                    Telefono);
        }
        return null;
    }

    private String entryToString(AddressEntryTest entry) {
        return entry.getNombre() + "," + entry.getApellido() + "," + entry.getCalle() + ","
                + entry.getCiudad() + "," + entry.getEstado() + "," + entry.getcodigoPostal() + ","
                + entry.getEmail() + "," + entry.getTelefono();
    }

    public void ordenarEntradasAlfabeticamente() {
        Collections.sort(entries, new Comparator<AddressEntry>() {
            @Override
            public int compare(AddressEntry entry1, AddressEntry entry2) {
                // Compara las entradas por el nombre en orden alfabético
                String nombre1 = entry1.getApellido().toUpperCase();
                String nombre2 = entry2.getApellido().toUpperCase();
                return nombre1.compareTo(nombre2);
            }
        });
    }
}