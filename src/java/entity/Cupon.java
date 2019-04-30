/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author selef
 */
public class Cupon {
    private int id;
    private String codigo;
    private boolean activo;
    private float porcDescuento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public float getPorcDescuento() {
        return porcDescuento;
    }

    public void setPorcDescuento(float porcDescuento) {
        this.porcDescuento = porcDescuento;
    }
    
    
    
}
