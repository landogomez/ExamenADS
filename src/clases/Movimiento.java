package src.clases;

import java.util.Date;

public class Movimiento {
    private Integer idMovimiento;
    private Integer monto;
    private Date fecha;
    private String descripcion;

    public Movimiento(Integer idMovimiento, Integer monto, Date fecha, String descripcion) {
        this.idMovimiento = idMovimiento;
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public Integer getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
