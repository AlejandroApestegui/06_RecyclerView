package apestegui.alejandro.com.a06_recyclerview;

/**
 * Created by Alejandro on 14/05/2017.
 */

public class Persona {

    private String id;
    private String nombre;
    private String apellido;
    private int edad;
    private String documento;

    public Persona() {
    }

    public Persona(String id, String nombre, String apellido, int edad, String documento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.documento = documento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
