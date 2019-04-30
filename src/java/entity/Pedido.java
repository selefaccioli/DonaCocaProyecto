package entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Pedido implements Serializable{
        private int id;
	private Date fechaPedido;
        private Date fechaEntrega;
	private float total;
	private String estado;
	private Usuario usuario;
        private boolean cerrado;
        private ArrayList<LineaPedido> lineasPedido;
        
     
        public Pedido(){
        lineasPedido = new ArrayList<LineaPedido>();
        estado = "Nuevo";
        DateFormat hoyFormato = new SimpleDateFormat("yyyy/MM/dd");      
        Date hoy=new Date();
        hoyFormato.format(hoy);
        fechaPedido = hoy;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoy); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, 1);  
        //fechaEntrega = calendar.getTime();
        
        
        
        
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

   

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    public void setLineasPedido(ArrayList<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }
    
     public void setLinea(LineaPedido lp)
    {
        getLineasPedido().add(lp);
    }
    
   
        
        
}
