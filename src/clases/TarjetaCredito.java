package src.clases;


public class TarjetaCredito {
    private String numTarjeta;
    private String expira;
    private double deuda;

    public TarjetaCredito(String numTarjeta, String expira, double deuda) {
        this.numTarjeta = numTarjeta;
        this.expira = expira;
        this.deuda = deuda;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public String getExpira() {
        return expira;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda){
        this.deuda=deuda;
    }
}
