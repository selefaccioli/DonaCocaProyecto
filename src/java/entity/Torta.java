
package entity;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Torta {
    private int id;
    private float precio;
    private String nombre;
    private InputStream imagen;
    ArrayList<Variante> variantes;
    private boolean activo;
    private String rutaImg;
    private ArrayList<Imagen> rutasImg;
    private Boolean eliminado;
    
    public Torta(){
    this.variantes = new ArrayList<Variante>();
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

    public ArrayList<Variante> getVariantes() {
        return variantes;
    }

    public void setVariantes(ArrayList<Variante> variantes) {
        this.variantes = variantes;
    }
    
       public boolean contieneVariante(Variante var)
    {
        int cont=0;
        for(Variante v:variantes)
        {
            if(v.getId()== var.getId()){
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
       
       public void agregarVariante(Variante variante)
    {
        this.variantes.add(variante);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public ArrayList<Imagen> getRutasImg() {
        return rutasImg;
    }

    public void setRutasImg(ArrayList<Imagen> rutasImg) {
        this.rutasImg = rutasImg;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
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
