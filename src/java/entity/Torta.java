
package entity;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Torta implements Serializable{
    private int id;
    private float precio;
    private String nombre;
    private InputStream imagen;
    ArrayList<DetalleTorta> detalles;
    
    public Torta(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    public ArrayList<DetalleTorta> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleTorta> detalles) {
        this.detalles = detalles;
    }
    
    
    
    
    
}
