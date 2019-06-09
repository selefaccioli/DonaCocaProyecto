
package entity;

import java.util.ArrayList;


public class LineaPedido {
    private int idPedido;
    private int cantidad;
    private Torta torta;
    private double subtotal;
    private ArrayList<Variante> variantes;
    
    public LineaPedido(){ }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Torta getTorta() {
        return torta;
    }

    public void setTorta(Torta torta) {
        this.torta = torta;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public ArrayList<Variante> getVariantes() {
        return variantes;
    }

    public void setVariantes(ArrayList<Variante> variantes) {
        this.variantes = variantes;
    }

    
    
    
    
}
