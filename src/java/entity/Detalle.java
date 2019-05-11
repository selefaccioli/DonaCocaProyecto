
package entity;

import java.io.Serializable;


public class Detalle {
    private int id;
    private String nombre;
    private Boolean eligeUsuario;
    private Boolean multiple;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEligeUsuario() {
        return eligeUsuario;
    }

    public void setEligeUsuario(Boolean eligeUsuario) {
        this.eligeUsuario = eligeUsuario;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }


    
    
}
