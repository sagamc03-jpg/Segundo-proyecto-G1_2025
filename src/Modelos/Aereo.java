package Modelos;

import java.text.DecimalFormat;

public class Aereo extends Envio{
    public Aereo(String codigo, String cliente, double peso, double distancia){
        super(codigo, cliente, peso, distancia);

    }
    @Override
    public double calcularFlete() {
        return (getDistancia() * 5000) + (getPeso() * 4000);
    }
    @Override
    public String[] mostrarValores() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
        "Aéreo",
        getCodigo(),
        getCliente(),
        df.format(getPeso()),
        df.format(getDistancia()),
        df.format(calcularFlete())
        };
    }

}
