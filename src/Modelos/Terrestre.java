package Modelos;

import java.text.DecimalFormat;

public class Terrestre extends Envio {
    public Terrestre(String codigo, String cliente, double peso, double distancia){
        super(codigo, cliente, peso, distancia);
    }
    @Override
    public double calcularFlete() {
        return (getDistancia() * 1500) + (getPeso() * 2000);
    }
    @Override
    public String[] mostrarValores() {
       DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
        "Terrestre",
        getCodigo(),
        getCliente(),
        df.format(getPeso()),
        df.format(getDistancia()),
        df.format(calcularFlete())
        };
    }
    


}
