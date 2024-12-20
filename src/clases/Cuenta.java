package src.clases;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private List<Movimiento> movimientos;
    private String tipoTarjeta; // "credito" o "debito"

    public Cuenta(String numeroCuenta, String pin, double saldoInicial, String tipoTarjeta) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldoInicial;
        this.movimientos = new ArrayList<>();
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void agregarMovimiento(Movimiento movimiento) {
        if (movimientos.size() >= 5) {
            movimientos.remove(0);
        }
        movimientos.add(movimiento);
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }
}

