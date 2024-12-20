package src.clases;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Cajero {
    private Cuenta cuenta;
    private TarjetaCredito credito;
    
    public Cajero(Cuenta cuenta, TarjetaCredito credito) {
        this.cuenta = cuenta;
        this.credito = credito;
    }
	
	public void mostrarMenu() {
		Scanner scanner = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("\n\n   MENÚ DEL CAJERO AUTOMÁTICO");
			System.out.println("********************************");
			System.out.println("1. Retirar Efectivo");
			System.out.println("2. Depositar Efectivo");
			System.out.println("3. Pagar Servicios");
			System.out.println("4. Consultar Saldo");
			System.out.println("5. Mostrar Movimientos");
			System.out.println("6. Salir");
			System.out.print("Selecciona una opción: ");
			
			while (!scanner.hasNextInt()) {
				System.out.println("\n***************************************************************");
				System.out.println("   ENTRADA NO VÁLIDA. POR FAVOR, INGRESE UN NÚMERO DEL MENÚ:");
				System.out.println("***************************************************************");
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
					System.out.println("\n**********************************************");
					System.out.println("   GRACIAS POR USAR EL CAJERO. ¡HASTA LUEGO!");
					System.out.println("**********************************************");
					break;
				default:
					System.out.println("\n*****************************************");
					System.out.println("   OPCIÓN NO VÁLIDA. INTENTA DE NUEVO.");
					System.out.println("*****************************************");
			}
		} while (opcion != 6);
		scanner.close();
	}

	private void retirarEfectivo(Scanner scanner) {
		double monto;
		System.out.println("\n\n   RETIRAR EFECTIVO");	
		System.out.println("********************************");
		do {
			try {
				System.out.print("Ingresa el monto a retirar (Solo denominaciones de 50) o presiona 0 para cancelar: ");
				monto = scanner.nextDouble();
				
				if (monto == 0) {
					System.out.println("\n********************************");
					System.out.println("   OPERACIÓN CANCELADA.");
					System.out.print("********************************");
					return;
				}
				
				if (monto <= 0 || monto % 50 != 0 || monto >= 20000) {
					System.out.println("\n***************************************");
					System.out.println("   MONTO INVÁLIDO. INTENTA DE NUEVO.");
					System.out.println("***************************************\n");
					continue;
				}
				
				if (monto > cuenta.getSaldo()) {
					System.out.println("\n*******************************************");
					System.out.println("   SALDO INSUFICIENTE. INTENTA DE NUEVO.");
					System.out.println("*******************************************\n");
					continue;
				}
				
				cuenta.setSaldo(cuenta.getSaldo() - monto);
				cuenta.agregarMovimiento(new Movimiento("Retiro", monto));
				System.out.println("\n*******************************************");
				System.out.println("   RETIRO EXITOSO. SALDO ACTUAL: $" + String.format("%,.2f",(double)cuenta.getSaldo()));
				System.out.println("*******************************************");
				System.out.println("\nPresiona ENTER Para Continuar...");
				scanner.nextLine();
				scanner.nextLine();
				break;
				
			} catch (InputMismatchException e) {
				System.out.println("\n************************************************************");
				System.out.println("   ENTRADA INVÁLIDA. POR FAVOR, INGRESA UN NÚMERO ENTERO.");
				System.out.println("************************************************************\n");
				scanner.next(); // Limpiar el buffer del scanner
			}
		} while (true);
	}

	private void depositarEfectivo(Scanner scanner) {
		double monto;
		System.out.println("\n\n   DEPOSITAR EFECTIVO");	
		System.out.println("********************************");
		do {
			try {
				System.out.print("Ingresa el monto a depositar o presiona 0 para cancelar: ");
				monto = scanner.nextDouble();
				
				if (monto == 0) {
					System.out.println("\n********************************");
					System.out.println("   OPERACIÓN CANCELADA.");
					System.out.print("********************************");
					return;
				}
				
				if (monto <= 0 || monto % 50 != 0 || monto > 20000) {
					System.out.println("\n***************************************");
					System.out.println("   MONTO INVÁLIDO. INTENTA DE NUEVO.");
					System.out.println("***************************************\n");
					continue;
				}
				
				cuenta.setSaldo(cuenta.getSaldo() + monto);
				cuenta.agregarMovimiento(new Movimiento("Depósito", monto));
				System.out.println("\n**********************************************");
				System.out.println("   DEPÓSITO EXITOSO. SALDO ACTUAL: $" + String.format("%,.2f", (double) cuenta.getSaldo()));
				System.out.println("**********************************************");
				System.out.println("\nPresiona ENTER Para Continuar...");
				scanner.nextLine();
				scanner.nextLine();
				break;
				
			} catch (InputMismatchException e) {
				System.out.println("\n************************************************************");
				System.out.println("   ENTRADA INVÁLIDA. POR FAVOR, INGRESA UN NÚMERO ENTERO.");
				System.out.println("************************************************************\n");
				scanner.next(); // Limpiar el buffer del scanner
			}
		} while (true);
	}
	
    private void pagarServicios(Scanner scanner) {
        int servicio;
        double monto;

        do {
            try {
                System.out.println("--- Menu de servicios ---");
                System.out.println("1. Agua");
                System.out.println("2. Luz");
                System.out.println("3. Gas");
                System.out.println("4. Tarjeta de crédito");
                System.out.println("5. Cancelar");
                System.out.println("Selecciona el servicio a pagar:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada no válida. Por favor, ingrese un numero del menu:");
                    scanner.next(); // Descartar la entrada no válida
                }
                servicio = scanner.nextInt();
                
                if (servicio == 5) {
                    System.out.println("Operación cancelada.");
                    return;
                }

                if (servicio < 1 || servicio > 4) {
                    System.out.println("Opción de servicio no válida. Intenta de nuevo.");
                    continue;
                }
                if(servicio == 4){
                    System.out.println("\nDeuda actual: $" + String.format("%,.2f", (double) credito.getDeuda()));
                    while(true){
                        System.out.print("Ingresa el monto a pagar O escribe 'salir' para cancelar: ");
                        String ent = scanner.next();
                        if (ent.equalsIgnoreCase("salir")) {
                            System.out.println("Operación cancelada.");
                            break;
                        }
                        try{
                            double mont = Double.parseDouble(ent);
                            if (mont <= 0 || mont> credito.getDeuda()) {
                                System.out.println("Monto inválido. Intenta de nuevo.");
                                continue;
                            }
                            if (cuenta.getSaldo() - mont < 0) { System.out.println("Saldo insuficiente. Intenta de nuevo."); continue; }
                            credito.setDeuda(credito.getDeuda()-mont);
                            cuenta.setSaldo(cuenta.getSaldo() - mont);
                            cuenta.agregarMovimiento(new Movimiento("Pago de Tarjeta de Credito", mont));
                            System.out.println("\nPago exitoso. Deuda actual: $" + String.format("%,.2f", (double) credito.getDeuda()));
                            break;
                        }catch (NumberFormatException e){
                             System.out.println("Entrada no válida. Por favor, ingresa un número del menu o 'salir'.");
                        }
                    }
                }
                if(servicio==1){
                    System.out.print("Ingresa el monto a pagar: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Entrada no válida. Por favor, ingrese numeros:");
                        scanner.next(); // Descartar la entrada no válida
                    }
                    monto = scanner.nextDouble();
                    if (monto <= 0) {
                        System.out.println("Monto inválido. Debe ser mayor a 0. Intenta de nuevo.");
                        continue;
                    }
                    if (monto <= cuenta.getSaldo()) {
                        cuenta.setSaldo(cuenta.getSaldo() - monto);
                        cuenta.agregarMovimiento(new Movimiento("Pago de Servicio Agua", monto));
                        System.out.println("Pago exitoso. Saldo actual: $" + String.format("%,.2f", (double) cuenta.getSaldo()));
                        break;
                    } else {
                        System.out.println("Saldo insuficiente. Intenta de nuevo.");
                    }
                }
                
                if(servicio==2){
                    System.out.print("Ingresa el monto a pagar: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Entrada no válida. Por favor, ingrese numeros:");
                        scanner.next(); // Descartar la entrada no válida
                    }
                    monto = scanner.nextDouble();
                    if (monto <= 0) {
                        System.out.println("Monto inválido. Debe ser mayor a 0. Intenta de nuevo.");
                        continue;
                    }
                    if (monto <= cuenta.getSaldo()) {
                        cuenta.setSaldo(cuenta.getSaldo() - monto);
                        cuenta.agregarMovimiento(new Movimiento("Pago de Servicio Luz", monto));
                        System.out.println("Pago exitoso. Saldo actual: $" + String.format("%,.2f", (double) cuenta.getSaldo()));
                        break;
                    } else {
                        System.out.println("Saldo insuficiente. Intenta de nuevo.");
                    }
                }
                if(servicio==3){
                    System.out.print("Ingresa el monto a pagar: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Entrada no válida. Por favor, ingrese numeros:");
                        scanner.next(); // Descartar la entrada no válida
                    }
                    monto = scanner.nextDouble();
                    if (monto <= 0) {
                        System.out.println("Monto inválido. Debe ser mayor a 0. Intenta de nuevo.");
                        continue;
                    }
                    if (monto <= cuenta.getSaldo()) {
                        cuenta.setSaldo(cuenta.getSaldo() - monto);
                        cuenta.agregarMovimiento(new Movimiento("Pago de Servicio Gas", monto));
                        System.out.println("Pago exitoso. Saldo actual: $" + String.format("%,.2f", (double) cuenta.getSaldo()));
                        break;
                    } else {
                        System.out.println("Saldo insuficiente. Intenta de nuevo.");
                    }
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        } while (true);
    }




    private void consultarSaldo() {
        System.out.println("\nSaldo actual: $" + String.format("%,.2f", (double) cuenta.getSaldo()));
    }

    private void mostrarMovimientos() {
        if (cuenta.getMovimientos().isEmpty()) {
            System.out.println("No hay movimientos por mostrar.");
        } else {
            System.out.println("Últimos movimientos:");
            for (Movimiento movimiento : cuenta.getMovimientos()) {
                System.out.println(movimiento.getTipo() + ": $" + movimiento.getMonto() + " en " + movimiento.getFecha());
            }
        }
    }

    public void ValidarCuenta() {
        Scanner scanner = new Scanner(System.in);
        String NumeroCuenta;
        String PIN;
        boolean cuentaValida = false;
        while (true) {
            // Solicitar número de cuenta
            System.out.print("Ingrese el número de cuenta: ");
            NumeroCuenta = scanner.nextLine();
            // Validar número de cuenta
            if (NumeroCuenta.equals(cuenta.getNumeroCuenta())) {
                for (int i = 0; i < 3; i++) {
                    System.out.print("Ingrese el PIN (4 dígitos): ");
                    PIN = scanner.nextLine();
                    if (PIN.equals(cuenta.getPin())) {
                        System.out.println("PIN correcto. Bienvenido.");
                        return;
                    } else {
                        System.out.println("PIN incorrecto. Intente de nuevo.");
                        System.out.println("Intentos restantes: " + (2 - i));
                    }
                }
                System.out.println("Demasiados intentos fallidos.");
                System.exit(2);
            } else {
                System.out.println("Número de cuenta inválido. Intente de nuevo.");
            }
        }
    }

}
