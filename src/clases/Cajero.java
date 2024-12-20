package src.clases;

import java.util.Scanner;

public class Cajero {
    private Cuenta cuenta;

    public Cajero(Cuenta cuenta) {
        this.cuenta = cuenta;
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
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
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
                    System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 6);
        scanner.close();
    }

    private void retirarEfectivo(Scanner scanner) {
        System.out.print("Ingresa el monto a retirar: ");
        double monto = scanner.nextDouble();
        if (monto > 0 && monto % 50 == 0 && monto <= 20000 && monto <= cuenta.getSaldo()) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            cuenta.agregarMovimiento(new Movimiento("Retiro", monto));
            System.out.println("Retiro exitoso. Saldo actual: $" + cuenta.getSaldo());
        } else {
            System.out.println("Monto inválido o saldo insuficiente.");
        }
    }

    private void depositarEfectivo(Scanner scanner) {
        System.out.print("Ingresa el monto a depositar: ");
        double monto = scanner.nextDouble();
        if (monto > 0 && monto % 50 == 0 && monto <= 20000) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            cuenta.agregarMovimiento(new Movimiento("Depósito", monto));
            System.out.println("Depósito exitoso. Saldo actual: $" + cuenta.getSaldo());
        } else {
            System.out.println("Monto inválido.");
        }
    }

    private void pagarServicios(Scanner scanner) {
        System.out.println("Selecciona el servicio a pagar:");
        System.out.println("1. Agua");
        System.out.println("2. Luz");
        System.out.println("3. Gas");
        System.out.println("4. Tarjeta de crédito");
        int servicio = scanner.nextInt();
        System.out.print("Ingresa el monto a pagar: ");
        double monto = scanner.nextDouble();
        if (monto > 0 && monto <= cuenta.getSaldo()) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            cuenta.agregarMovimiento(new Movimiento("Pago de Servicio", monto));
            System.out.println("Pago exitoso. Saldo actual: $" + cuenta.getSaldo());
        } else {
            System.out.println("Monto inválido o saldo insuficiente.");
        }
    }

    private void consultarSaldo() {
        System.out.println("Saldo actual: $" + cuenta.getSaldo());
    }

    private void mostrarMovimientos() {
        System.out.println("Últimos movimientos:");
        for (Movimiento movimiento : cuenta.getMovimientos()) {
            System.out.println(movimiento.getTipo() + ": $" + movimiento.getMonto() + " en " + movimiento.getFecha());
        }
    }
}

