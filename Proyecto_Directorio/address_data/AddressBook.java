package address_data;

// Importaciones para el manejo de archivos y estructuras de datos
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import address_data_test.AddressEntryTest;

// Declaración de la clase
public class AddressBook {
    // Variables de instancia
    private List<AddressEntryTest> entries; // Lista de objetos, entrada de direcciones
    private String filePath; // Cadena que almacena la ruta del archivo donde se almacenan las entradas

    // Constructor
    public AddressBook(String filePath) {
        entries = new ArrayList<>(); // Crea una lista vacia de entradas de dirección
        this.filePath = filePath;
        cargarEntriesFromFile(); // Carga las entradas existentes desde el archivo
    }

    // Método que permite agregar entradas al Directorio.
    public void agregarContacto(AddressEntryTest entry) {
        if (NoDuplicado(entry)) { // Verifica si la entrada ya existe en el Directorio
            System.out.println("---------------------------------------");
            System.out.println("El contacto ya existe en tu Directorio");
            System.out.println("---------------------------------------");
        } else {
            entries.add(entry);
            saveEntriesFiles(); // Guarda la lista de entrada y la guarda en el archivo
            System.out.println("---------------------------------------");
            System.out.println("¡Contacto agregado con exito!");
            System.out.println("---------------------------------------");

        }
    }

    // Método que compara si los datos de entrada ya existen en el directorio
    private boolean NoDuplicado(AddressEntryTest entry) {
        for (AddressEntryTest existingEntry : entries) {
            if (existingEntry.getNombre().equals(entry.getNombre())
                    && existingEntry.getApellido().equals(entry.getApellido())) {
                return true;
            }
        }
        return false;
    }

    // Método que permite eliminar un contacto del directorio
    public void eliminarContacto(AddressEntryTest entry) {
        entries.remove(entry);
        saveEntriesFiles(); // Guarda los cambios en el archivo de Directorio
        System.out.println("Contacto Eliminado Exitosamente");
    }

    // Método que permite la busqueda de contactos por apellido
    public List<AddressEntryTest> buscarApellido(String Apellido) {
        List<AddressEntryTest> searchResults = new ArrayList<>();
        for (AddressEntryTest entry : entries) {
            if (entry.getApellido().startsWith(Apellido)) {
                searchResults.add(entry);
            }
        }
        return searchResults;
    }

    // Método que devuelve una lista con todos los contactos guardados
    public List<AddressEntryTest> getAllEntries() {
        return entries;
    }

    // Método que carga los contactos guardados en el archivo
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

    // Método que guarda los contactos en el archivo
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

    // Método que crea una cadena en un objeto para dividirlo por partes
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

    // Método que convierte una cadena de texto en formato CSV
    private String entryToString(AddressEntryTest entry) {
        return entry.getNombre() + "," + entry.getApellido() + "," + entry.getCalle() + ","
                + entry.getCiudad() + "," + entry.getEstado() + "," + entry.getcodigoPostal() + ","
                + entry.getEmail() + "," + entry.getTelefono();
    }

    // Método que ordena las entradas en orden Alfabético
    public void ordenarContactos() {
        Collections.sort(entries, new Comparator<AddressEntryTest>() {
            @Override
            public int compare(AddressEntryTest entry1, AddressEntryTest entry2) {
                // Compara las entradas por el nombre en orden alfabético
                String nombre1 = entry1.getApellido();
                String nombre2 = entry2.getApellido();
                return nombre1.compareTo(nombre2);
            }
        });
    }
}