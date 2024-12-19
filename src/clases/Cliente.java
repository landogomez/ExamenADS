package src.clases;

public class Cliente {
    private String nombre;
    private Integer idUsr;

    public Cliente(String nombre, int idUsr) {
        this.nombre = nombre;
        this.idUsr = idUsr;
    }

    public Cliente(int idUsr) {
        this.idUsr = idUsr;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdUsr() {
        return idUsr;
    }
}
