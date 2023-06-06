package address_data;

import address_data.AddressEntry;

import java.util.List;
import java.util.Scanner;

public class AddressBookApplication {
    private AddressBook addressBook;
    private Scanner scanner;

    public AddressBookApplication() {
        addressBook = new AddressBook();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = getUserChoice();
            switch (choice) {
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
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("***************");
        System.out.println("Address Book");
        System.out.println("***************");
        System.out.println("1. Agregar entrada");
        System.out.println("2. Eliminar entrada");
        System.out.println("3. Buscar entradas");
        System.out.println("4. Mostrar entradas");
        System.out.println("5. Salir");
        System.out.println("***************");
    }

    private String getUserChoice() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    private void addEntry() {
        System.out.println("Ingrese los detalles de la entrada:");
        System.out.print("Nombre: ");
        String firstName = scanner.nextLine();
        System.out.print("Apellido: ");
        String lastName = scanner.nextLine();
        System.out.print("Calle: ");
        String street = scanner.nextLine();
        System.out.print("Ciudad: ");
        String city = scanner.nextLine();
        System.out.print("Estado: ");
        String state = scanner.nextLine();
        System.out.print("Código postal: ");
        String postalCode = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phoneNumber = scanner.nextLine();

        AddressEntry entry = new AddressEntry(firstName, lastName, street, city, state, postalCode, email, phoneNumber);
        addressBook.addEntry(entry);
        System.out.println("Entrada agregada correctamente.");
    }

    private void removeEntry() {
        System.out.println("Ingrese el apellido de la entrada a eliminar:");
        String lastName = scanner.nextLine();
        List<AddressEntry> searchResults = addressBook.searchEntriesByApellido(lastName);
        if (!searchResults.isEmpty()) {
            System.out.println("Se encontraron las siguientes entradas:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i));
            }
            System.out.print("Selecciona el número de la entrada a eliminar: ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= searchResults.size()) {
                AddressEntry entryToRemove = searchResults.get(choice - 1);
                addressBook.removeEntry(entryToRemove);
                System.out.println("Entrada eliminada correctamente.");
            } else {
                System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("No se encontraron entradas con ese apellido.");
        }
    }

    private void searchEntries() {
        System.out.println("Ingrese el inicio del apellido para buscar:");
        String lastName = scanner.nextLine();
        List<AddressEntry> searchResults = addressBook.searchEntriesByLastName(lastName);
        if (!searchResults.isEmpty()) {
            System.out.println("Se encontraron las siguientes entradas:");
            for (AddressEntry entry : searchResults) {
                System.out.println(entry);
                System.out.println("---------------");
            }
        } else {
            System.out.println("No se encontraron entradas con ese apellido.");
        }
    }

    private void displayEntries() {
        addressBook.sortEntriesByApellido();
        List<AddressEntry> entries = addressBook.getAllEntries();
        if (!entries.isEmpty()) {
            System.out.println("Lista de entradas:");
            for (AddressEntry entry : entries) {
                System.out.println(entry);
                System.out.println("---------------");
            }
        } else {
            System.out.println("No hay entradas en la libreta de direcciones.");
        }
    }

    public static void main(String[] args) {
        AddressBookApplication application = new AddressBookApplication();
        application.run();
    }
}
