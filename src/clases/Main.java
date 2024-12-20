package src.clases;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creación de las cuentas
        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.add(new Cuenta("1234567890123456", "1234", 10000.0, "debito"));
        cuentas.add(new Cuenta("6543210987654321", "5678", 20000.0, "credito"));

        Cuenta cuenta = cuentas.get(0);

        // Crear tarjeta de crédito
        TarjetaCredito credito = new TarjetaCredito("1478523691234567", "04/12", 1200000.0);

        // Crear el cajero con la lista de cuentas
        Cajero cajero = new Cajero(cuenta, credito);

        // Iniciar sesión y mostrar el menú
        //cajero.iniciarSesion();
        cajero.mostrarMenu();
    }
}
