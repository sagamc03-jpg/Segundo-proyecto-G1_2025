package Servicios;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelos.Aereo;
import Modelos.Envio;
import Modelos.Terrestre;
import Modelos.TipoTransporte;
import Modelos.Maritimo;

import java.util.ArrayList;
import java.util.List;

public class GestionEnvios {
    private static List<Envio> envios = new ArrayList<>();
    private static String[] encabezadoEnvios = new String[] { "Transporte", "Código", "Cliente", "Peso (Kg)", "Distancia (Km)", "Costo"};

    public static void mostrar(JTable tblEnvios){
        String[][] datos = null;
        if(envios.size()>0) {
            datos = new String[envios.size()][encabezadoEnvios.length];

            int fila = 0; 
            for (Envio e: envios){
            if (e != null) {
                datos[fila] = e.mostrarValores();
                }
                fila++;   
            }  
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezadoEnvios);
        tblEnvios.setModel(dtm);       
    }
    //Válida si el código está repetidos
    public static boolean existeCodigo(String codigo) {
    for (Envio e : envios) {
        if (e.getCodigo().equalsIgnoreCase(codigo)) {
            return true;
        }
    }
    return false;
    }

    public static Envio Agregar(TipoTransporte tipo,String codigo, String cliente, double peso, double distancia) {
        if (existeCodigo(codigo)) {
        throw new IllegalArgumentException("El código " + codigo + " ya existe. Por favor use un código distinto.");
        }
        Envio envio = null;
        switch (tipo) {
            case Terrestre: 
                envio = new Terrestre(codigo, cliente, peso, distancia);
                break;
            case Aéreo: 
                envio = new Aereo(codigo, cliente, peso, distancia);
                break;
            case Marítimo: 
                envio = new Maritimo(codigo, cliente, peso, distancia);
                break;
        }
        if (envio !=null){
            envios.add(envio);
        }
        return envio;   
    }
    public static boolean quitar(int posicion){
        if (posicion >=0 && posicion < envios.size()) {
            envios.remove(posicion);
            return true;
        }
        return false;
    }
}
