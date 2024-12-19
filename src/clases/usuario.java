package src.clases;

public class usuario {
    private String nombre;
    private int id;

    public usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "src.clases.usuario{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
