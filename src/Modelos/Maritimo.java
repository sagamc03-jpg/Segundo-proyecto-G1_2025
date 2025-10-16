package Modelos;

import java.text.DecimalFormat;

public class Maritimo extends Envio{
    public Maritimo(String codigo, String cliente, double peso, double distancia){
        super(codigo, cliente, peso, distancia);

    }
    @Override
    public double calcularFlete() {
        return (getDistancia() * 800) + (getPeso() * 1000);
    }
      @Override
      public String[] mostrarValores() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
        "Marítimo",
        getCodigo(),
        getCliente(),
        df.format(getPeso()),
        df.format(getDistancia()),
        df.format(calcularFlete())
        };
    }
  
}
