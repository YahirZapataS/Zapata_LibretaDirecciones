package address_data_test;

public class AddressEntryTest {
    private String Nombre;
    private String Apellido;
    private String Calle;
    private String Ciudad;
    private String Estado;
    private String codigoPostal;
    private String email;
    private String Telefono;

    public AddressEntryTest(String Nombre, String Apellido, String Calle, String Ciudad, String Estado,
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

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getCalle() {
        return Calle;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public String getEstado() {
        return Estado;
    }

    public String getcodigoPostal() {
        return codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return Telefono;
    }

    @Override
    public String toString() {
        return "Nombre: " + Nombre + " " + Apellido + "\n" +
                "Dirección: " + Calle + ", " + Ciudad + ", " + Estado + " " + codigoPostal + "\n" +
                "Email: " + email + "\n" +
                "Teléfono: " + Telefono;
    }

    public static AddressEntryTest fromString(String entryString) {
        String[] parts = entryString.split(",");
        if (parts.length == 8) {
            String Nombre = parts[0];
            String Apellido = parts[1];
            String Calle = parts[2];
            String Ciudad = parts[3];
            String Estado = parts[4];
            String codigoPostal = parts[5];
            String email = parts[6];
            String Telefono = parts[7];
            return new AddressEntryTest(Nombre, Apellido, Calle, Ciudad, Estado, codigoPostal, email, Telefono);
        }
        return null;
    }
}