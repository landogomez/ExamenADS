package src.clases;

public class TarjetaCredito {
    private String numCuenta;
    private Integer numTarjeta;
    private String expira;
    private double creditoTotal;
    private double deuda;

    public TarjetaCredito(String numCuenta, int numTarjeta, String expira, double creditoTotal, double deuda) {
        this.numCuenta = numCuenta;
        this.numTarjeta = numTarjeta;
        this.expira = expira;
        this.creditoTotal = creditoTotal;
        this.deuda = deuda;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public String getExpira() {
        return expira;
    }

    public double getCreditoTotal() {
        return creditoTotal;
    }

    public double getDeuda() {
        return deuda;
    }

    public void actualizarDeuda(double monto) {
        this.deuda += monto;
    }
}
