package src.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        //cajero.ValidarCuenta();
        //cajero.mostrarMenu();

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (cajero.ValidarCuenta()) {
                        cajero.mostrarMenu();
                    } else {
                        System.out.println("Autenticación fallida. Intenta de nuevo.");
                    }
                    break;
                case 2:
                    System.out.println("Adiós.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 2);
        scanner.close();
    }
}
