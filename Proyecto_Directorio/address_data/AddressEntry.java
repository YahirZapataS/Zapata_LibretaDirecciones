package address_data;

//Entrada de datos de contacto

public class AddressEntry {
    private String Nombre;
    private String Apellido;
    private String Calle;
    private String Ciudad;
    private String Estado;
    private String codigoPostal;
    private String email;
    private String Telefono;

    public AddressEntry(String Nombre, String Apellido, String Calle, String Ciudad, String Estado,
                        String codigoPostal, String email, String Telefono) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Calle = Calle;
        this.Ciudad = Ciudad;
        this.Estado = Estado;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.Telefono = Telefono;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Nombre: " + Nombre + " " + Apellido + "\n" +
                "Dirección: " + Calle + ", " + Ciudad + ", " + Estado + ", " + codigoPostal + "\n" +
                "Email: " + email + "\n" +
                "Número de Telefono: " + Telefono;
    }

    public String getApellido() {
        return null;
    }
}