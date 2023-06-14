package address_data;

import java.util.List;
import java.util.Scanner;

public class AddressBookApplication {
    private AddressBook addressBook;
    private Scanner scanner;

    // Instancia de la clase AddressBook
    // Utiliza un archivo txt en una ruta especifica para el almacenamiento de los
    // datos
    public AddressBookApplication() {
        String filePath = "D:/yahir/Documentos/Proyecto_Directorio/Directorio.txt"; // Ruta del archivo TXT
        addressBook = new AddressBook(filePath);
        scanner = new Scanner(System.in);
    }

    // Crea una nueva instancia de Menú para mostrar las opciones disponibles al
    // usuario.
    // El bucle continuará hasta que el usuario elija salir del programa.
    public void run() {
        Menu menu = new Menu();
        boolean exit = false;

        while (!exit) {
            menu.mostrarMenu();
            String opcion = menu.getUserOpcion();

            switch (opcion) {
                case "1":
                    agregarContacto();
                    break;
                case "2":
                    eliminarContacto();
                    break;
                case "3":
                    buscarContacto();
                    break;
                case "4":
                    mostrarContactos();
                    break;
                case "5":
                    exit = true;
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("¡Directorio Cerrado con éxito! Que tengas buen día...");
                    System.out.println("-------------------------------------------------------------");
                    break;
                default:
                    System.out.println("Opcion no disponible. Inténtalo de nuevo.");
                    break;
            }
        }
    }

    // Método que solicita al usuario los datos del contacto a agregar
    private void agregarContacto() {
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

        AddressEntry entry = new AddressEntry(Nombre, Apellido, Calle, Ciudad, Estado,
                codigoPostal, email, Telefono);
        addressBook.agregarContacto(entry);
    }

    // Método que elimina un contacto de la lista Directorio solicitando el apellido
    private void eliminarContacto() {
        System.out.println("Ingrese el apellido del contacto a eliminar: ");
        String Apellido = scanner.nextLine();
        List<AddressEntry> searchResults = addressBook.buscarApellido(Apellido);
        if (!searchResults.isEmpty()) {
            System.out.println("Se encontraron las siguientes entradas:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i));
            }
            System.out.print("Selecciona el contacto a eliminar: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            if (opcion >= 1 && opcion <= searchResults.size()) {
                AddressEntry entryToRemove = searchResults.get(opcion - 1);
                addressBook.eliminarContacto(entryToRemove);
            } else {
                System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("No se encontraron contactos con ese a");
        }
    }

    // Método que permite buscar a un contacto solicitando el apellido
    private void buscarContacto() {
        System.out.println("Ingrese el inicio del apellido para buscar:");
        String Apellido = scanner.nextLine();
        List<AddressEntry> searchResults = addressBook.buscarApellido(Apellido);
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

    // Método que muestra todos los contactos dentro del archivo Directorio
    private void mostrarContactos() {
        List<AddressEntry> entries = addressBook.getAllEntries();
        if (!entries.isEmpty()) {
            System.out.println("Lista de entradas:");
            for (AddressEntry entry : entries) {
                System.out.println(entry);
                System.out.println("---------------");
            }
        } else {
            System.out.println("La libreta de direcciones está vacía.");
        }
    }

    // Método que llama al método "Run" para iniciar el programa
    public static void main(String[] args) {
        AddressBookApplication application = new AddressBookApplication();
        application.run();
    }
}