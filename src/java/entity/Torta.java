
package entity;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Torta implements Serializable{
    private int id;
    private float precio;
    private String nombre;
    private InputStream imagen;
    ArrayList<Detalle> detalles;
    private boolean activo;
    
    public Torta(){
    this.detalles = new ArrayList<Detalle>();
    }

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

    public ArrayList<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<Detalle> detalles) {
        this.detalles = detalles;
    }
    
       public boolean contieneDetalle(Detalle det)
    {
        int cont=0;
        for(Detalle d:detalles)
        {
            if(d.getId()== det.getId()){
               cont++;
            }
                
        }
        if(cont>0){
            return true;
        }
        else{
            return false;
        }
    }
       
       public void agregarDetalle(Detalle detalle)
    {
        this.detalles.add(detalle);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
       
       
      
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Torta other = (Torta) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
