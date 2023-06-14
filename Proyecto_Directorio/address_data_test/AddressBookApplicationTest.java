package address_data_test;

import java.util.List;
import java.util.Scanner;

public class AddressBookApplicationTest {
    private AddressBookTest addressBookTest;
    private Scanner scanner;

    // Instancia de la clase AddressBook
    // Utiliza un archivo txt en una ruta especifica para el almacenamiento de los
    // datos
    public AddressBookApplicationTest() {
        String filePath = "C:/Users/yahir/OneDrive/Documentos/Directorio/Directorio.txt"; // Ruta del archivo TXT
        addressBookTest = new AddressBookTest(filePath);
        scanner = new Scanner(System.in);
    }

    // Crea una nueva instancia de Menú para mostrar las opciones disponibles al usuario.
    // El bucle continuará hasta que el usuario elija salir del programa.
    public void run() {
        Menu menu = new Menu();
        boolean exit = false;

        while (!exit) {
            menu.mostrarMenu();
            String opcion = menu.getUserOpcion();

            switch (opcion) {
                case "1":
                    addEntry();
                    break;
                case "2":
                    removeEntry();
                    break;
                case "3":
                    searchEntries();
                    break;
                case "4":
                    displayEntries();
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no disponible. Inténtalo de nuevo.");
                    break;
            }
        }
    }

    
    private void addEntry() {
        System.out.println("Complete los datos del contacto en los siguientes campos:");
        System.out.print("Nombre(s): ");
        String Nombre = scanner.nextLine();
        System.out.print("Apellido(s): ");
        String Apellido = scanner.nextLine();
        System.out.print("Calle: ");
        String Calle = scanner.nextLine();
        System.out.print("Ciudad: ");
        String Ciudad = scanner.nextLine();
        System.out.print("Estado: ");
        String Estado = scanner.nextLine();
        System.out.print("Código postal: ");
        String codigoPostal = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String Telefono = scanner.nextLine();

        AddressEntryTest entry = new AddressEntryTest(Nombre, Apellido, Calle, Ciudad, Estado,
                codigoPostal, email, Telefono);
        addressBookTest.addEntry(entry);
    }

    private void removeEntry() {
        System.out.println("Ingrese el apellido del contacto a eliminar: ");
        String Apellido = scanner.nextLine();
        List<AddressEntryTest> searchResults = addressBookTest.searchEntriesByApellidogetApellido(Apellido);
        if (!searchResults.isEmpty()) {
            System.out.println("Se encontraron las siguientes entradas:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i));
            }
            System.out.print("Selecciona el contacto a eliminar: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            if (opcion >= 1 && opcion <= searchResults.size()) {
                AddressEntryTest entryToRemove = searchResults.get(opcion - 1);
                addressBookTest.removeEntry(entryToRemove);
            } else {
                System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("No se encontraron contactos con ese a");
        }
    }

    private void searchEntries() {
        System.out.println("Ingrese el inicio del apellido para buscar:");
        String Apellido = scanner.nextLine();
        List<AddressEntryTest> searchResults = addressBookTest.searchEntriesByApellidogetApellido(Apellido);
        if (!searchResults.isEmpty()) {
            System.out.println("Se encontraron las siguientes entradas:");
            for (AddressEntryTest entry : searchResults) {
                System.out.println(entry);
                System.out.println("---------------");
            }
        } else {
            System.out.println("No se encontraron entradas con ese apellido.");
        }
    }

    private void displayEntries() {
        List<AddressEntryTest> entries = addressBookTest.getAllEntries();
        if (!entries.isEmpty()) {
            System.out.println("Lista de entradas:");
            for (AddressEntryTest entry : entries) {
                System.out.println(entry);
                System.out.println("---------------");
            }
        } else {
            System.out.println("La libreta de direcciones está vacía.");
        }
    }

    public static void main(String[] args) {
        AddressBookApplicationTest application = new AddressBookApplicationTest();
        application.run();
    }
}