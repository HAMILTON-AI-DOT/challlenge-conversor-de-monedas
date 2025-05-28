package conversor;

public class ResultadoConversion {
    private final double montoConvertido;

    public ResultadoConversion(double tasa, double montoOriginal) {
        this.montoConvertido = tasa * montoOriginal;
    }

    @Override
    public String toString() {
        return "Monto convertido: " + montoConvertido;
    }
}
