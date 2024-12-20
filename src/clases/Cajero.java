package src.clases;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Cajero {
    private List<Cuenta> cuentas;  // Lista para almacenar las cuentas
    private Cuenta cuentaActual;    // Cuenta actual del usuario
    private TarjetaCredito credito;

    public Cajero(List<Cuenta> cuentas, TarjetaCredito credito) {
        this.cuentas = cuentas;
        this.credito = credito;
    }

    public void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        boolean cuentaValida = false;

        while (!cuentaValida) {
            System.out.print("Ingrese el número de cuenta: ");
            String numeroCuenta = scanner.nextLine();

            Cuenta cuenta = encontrarCuentaPorNumero(numeroCuenta);
            if (cuenta != null) {
                cuentaActual = cuenta;
                cuentaValida = validarPIN();
            } else {
                System.out.println("Número de cuenta no encontrado. Intente de nuevo.");
            }
        }
    }

    private boolean validarPIN() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese el PIN (4 dígitos): ");
            String PIN = scanner.nextLine();

            if (PIN.equals(cuentaActual.getPin())) {
                System.out.println("PIN correcto. Bienvenido.");
                return true;
            } else {
                System.out.println("PIN incorrecto. Intentos restantes: " + (2 - i));
            }
        }
        System.out.println("Demasiados intentos fallidos.");
        return false;
    }

    private Cuenta encontrarCuentaPorNumero(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú del Cajero Automático ---");
            System.out.println("1. Retirar Efectivo");
            System.out.println("2. Depositar Efectivo");
            System.out.println("3. Pagar Servicios");
            System.out.println("4. Consultar Saldo");
            System.out.println("5. Mostrar Movimientos");
            System.out.println("6. Cerrar sesión");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingrese un número del menú:");
                scanner.next();
            }

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    retirarEfectivo(scanner);
                    break;
                case 2:
                    depositarEfectivo(scanner);
                    break;
                case 3:
                    pagarServicios(scanner);
                    break;
                case 4:
                    consultarSaldo();
                    break;
                case 5:
                    mostrarMovimientos();
                    break;
                case 6:
                    System.out.println("Cerrando sesión...");
                    cuentaActual = null;
                    iniciarSesion();
                case 7:
                    System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 7);
        scanner.close();
    }

    private void retirarEfectivo(Scanner scanner) {
        double monto;
        do {
            try {
                System.out.print("Ingresa el monto a retirar (Solo denominaciones de 50) o presiona 0 para cancelar: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Entrada no válida. Por favor, ingrese números:");
                    scanner.next(); // Descartar la entrada no válida
                }
                monto = scanner.nextDouble();

                if (monto == 0) {
                    System.out.println("Operación cancelada.");
                    return;
                }

                if (monto <= 0 || monto % 50 != 0 || monto > cuentaActual.getSaldo()) {
                    System.out.println("Monto inválido o saldo insuficiente. Intenta de nuevo.");
                    continue;
                }

                cuentaActual.setSaldo(cuentaActual.getSaldo() - monto);
                cuentaActual.agregarMovimiento(new Movimiento("Retiro", monto));
                System.out.println("Retiro exitoso. Saldo actual: $" + String.format("%,.2f", cuentaActual.getSaldo()));
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        } while (true);
    }

    private void depositarEfectivo(Scanner scanner) {
        double monto;
        do {
            try {
                System.out.print("Ingresa el monto a depositar o presiona 0 para cancelar: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Entrada no válida. Por favor, ingrese números:");
                    scanner.next(); // Descartar la entrada no válida
                }
                monto = scanner.nextDouble();

                if (monto == 0) {
                    System.out.println("Operación cancelada.");
                    return;
                }

                if (monto <= 0 || monto > 20000) {
                    System.out.println("Monto inválido. Intenta de nuevo.");
                    continue;
                }

                cuentaActual.setSaldo(cuentaActual.getSaldo() + monto);
                cuentaActual.agregarMovimiento(new Movimiento("Depósito", monto));
                System.out.println("Depósito exitoso. Saldo actual: $" + String.format("%,.2f", cuentaActual.getSaldo()));
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        } while (true);
    }

    private void pagarServicios(Scanner scanner) {
        // Similar a las opciones anteriores, puedes implementar el pago de servicios aquí.
    }

    private void consultarSaldo() {
        System.out.println("\nSaldo actual: $" + String.format("%,.2f", cuentaActual.getSaldo()));
    }

    private void mostrarMovimientos() {
        if (cuentaActual.getMovimientos().isEmpty()) {
            System.out.println("No hay movimientos por mostrar.");
        } else {
            System.out.println("Últimos movimientos:");
            for (Movimiento movimiento : cuentaActual.getMovimientos()) {
                System.out.println(movimiento.getTipo() + ": $" + movimiento.getMonto() + " en " + movimiento.getFecha());
            }
        }
    }
}
