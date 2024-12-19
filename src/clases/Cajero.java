package src.clases;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cajero {
    String tarjeta;
    String nip;
    debito trajetaDebito;

    public boolean validarCuenta(String tarjetaDebito) {
        //5 numeros, deben ser int
        try {
            int numTarjeta = Integer.parseInt(tarjetaDebito);
            if (tarjetaDebito.length() == 5) {
                this.tarjeta = tarjetaDebito;
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validarNip(String nip) {
        //4 numeros, deben ser int
        try {
            int numNip = Integer.parseInt(nip);
            if (nip.length() == 4) {
                this.nip = nip;
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checarSiExisteCuenta() {
        this.tarjetaDebito = new debito(this.tarjeta);
        if (this.tarjetaDebito.getIdCuenta() != null) {
            if (this.tarjetaDebito.getNip().equals(this.nip)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public String mostrarSaldo() {
        return "" + this.tarjetaDebito.getSaldo();
    }

    public String retirar(String cantidad) {
        try {
            int minDeposito = 100;
            if (cantidad != null) {
                double cantidadRetirar = Double.parseDouble(cantidad);
                double saldoActual = this.tarjetaDebito.getSaldo();
                if(cantidadRetirar<minDeposito){
                    return "Cantidad minima a retirar $100!!!";
                }
                if(cantidadRetirar>5000){
                    return "Se puede retirar máximo $5000";
                }

                if (cantidadRetirar > saldoActual) {
                    return "Saldo insuficiente, intenta de nuevo!!";
                } else {
                    double saldoNuevo = saldoActual - cantidadRetirar;
                    boolean setSaldo = this.tarjetaDebito.setSaldo(saldoNuevo);
                    if (setSaldo) {
                        return "Retiro de $" + cantidad + " actualizado";
                    } else {
                        return "error al actualizar saldo!!!";
                    }
                }
            } else {
                return "Agrega una cantidad a retirar por favor!!";
            }
        } catch (NumberFormatException e) {
            return "Cantidad a retirar no valida!!!";
        }
    }

    public String depositar(String cantidad) {
        try {
            if (cantidad != null) {
                int minDeposito = 100;
                int maxDeposito = 6000;
                double deposito = Double.parseDouble(cantidad);
                if(deposito<minDeposito){
                    return "Cantidad minima a depositar $100!!!";
                }
                if(deposito>maxDeposito){
                    return "Cantidad máxima a depositar es: $"+maxDeposito;
                }
                double saldoActual = this.tarjetaDebito.getSaldo();

                double saldoNuevo = saldoActual + deposito;
                boolean setSaldo = this.tarjetaDebito.setSaldo(saldoNuevo);
                if (setSaldo) {

                    return "Deposito de $" + cantidad + " actualizado";
                } else {
                    return "error al actualizar saldo!!!";
                }

            } else {
                return "Agrega una cantidad a retirar por favor!!";
            }
        } catch (NumberFormatException e) {
            return "Cantidad a retirar no valida!!!";
        }
    }

    public String mostrarNumTarjCred() {
        return "" + this.tarjetaDebito.getTarjetaCredito().getNumTarjeta();
    }

    public String mostrarSaldoTarjCred() {
        return "" + this.tarjetaDebito.getTarjetaCredito().getDeuda();
    }

    public boolean pagarTotalTarjCred() {
        try {
            double pagoT = 0.0;
            this.tarjetaDebito.getTarjetaCredito().actualizarDeuda(pagoT);
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean pagarTarjCred(String pago) {
        try {
            double deudaF;
            double pagoP = Double.parseDouble(pago);
            double deudaT = this.tarjetaDebito.getTarjetaCredito().getDeuda();
            deudaF = deudaT - pagoP;
            this.tarjetaDebito.getTarjetaCredito().actualizarDeuda(deudaF);
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public String pagarServicio(String pago) {
        double saldo = this.tarjetaDebito.getSaldo();
        try {
            double pagoP = Double.parseDouble(pago);
            if (pagoP > saldo) {
                return "El pago es mayor que salgo, intenta de nuevo!!!";
            } else {
                double saldoF = saldo - pagoP;
                this.tarjetaDebito.setSaldo(saldoF);
                return "Pago de $" + pago + " fue exitoso.";
            }
        } catch (NumberFormatException e) {
            return "Cantidad a pagar no valida!!!";
        }
    }

    public StringBuilder obtenerMovimientos(int ultimosN) {
        StringBuilder sb = new StringBuilder();
        List<Movimientos> movimientos = this.tarjetaDebito.getMovimientos(ultimosN);
        if (movimientos == null) {
            sb.append("No hay movimientos en esta cuenta");
        } else {
            for (Movimientos movimiento : movimientos) {
                sb.append("Tipo: ").append(movimiento.getTipo()).append(", Monto: ").append(movimiento.getMonto()).append(", Fecha y hora: ").append(movimiento.getFechaHora()).append("\n");
            }
        }
        return sb;
    }

    public boolean generarTransaccion(String monto, int tipo) {
        try {
            //case "0" -> tipo = "Deposito";
            //case "1" -> tipo = "Retiro";
            //case "2" -> tipo = "Pago de Tarjeta de credito";
            //case "3" -> tipo = "Pago de servicios";
            double montoT = Double.parseDouble(monto);
            new Transaccion(montoT, tipo, this.tarjetaDebito);

            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void imprimirTicket(String monto, int tipo) {
        //case "0" -> tipo = "Deposito";
        //case "1" -> tipo = "Retiro";
        //case "2" -> tipo = "Pago de Tarjeta de credito";
        //case "3" -> tipo = "Pago de servicios";
        Date fecha = new Date();
        String tipoTransaccion;
        switch (tipo) {
            case 0 ->
                    tipoTransaccion = "Deposito";
            case 1 ->
                    tipoTransaccion = "Retiro";
            case 2 ->
                    tipoTransaccion = "Pago de Tarjeta de credito";
            case 3 ->
                    tipoTransaccion = "Pago de servicios";
            default -> {
                tipoTransaccion = "Desconocida";
            }
        }
        System.out.println("----------------Ticket-------------------");
        System.out.println("Fecha: " + fecha);
        System.out.println("Nombre completo: " + this.tarjetaDebito.getCliente().getNombreCompleto());
        System.out.println("Tipo de transaccion: " + tipoTransaccion);
        System.out.println("Cantidad: " + monto);
        System.out.println("------------------------------------------");
    }


}
