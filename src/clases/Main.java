package src.clases;

import src.clases.Cajero;
import src.clases.Cuenta;

public class Main {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta("1234567890123456", "1234", 10000.0, "debito");
        TarjetaCredito credito = new TarjetaCredito("1478523691234567", "04/12", 1200000.0);
        Cajero cajero = new Cajero(cuenta);
        cajero.mostrarMenu();
    }
}
