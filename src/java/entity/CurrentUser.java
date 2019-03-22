package entity;

import java.io.Serializable;

public class CurrentUser implements Serializable {
	
	private static CurrentUser currentUser;
	
	 private Usuario usuario;
	 
	 private CurrentUser() {
	        
	    }
	 public static CurrentUser getSingletonInstance() {
	        if (currentUser == null){
	        	currentUser = new CurrentUser();
	        }
	        else{
	            System.out.println("No se puede crear el objeto porque ya existe un objeto de la clase CurrentUser");
	        }
	        
	        return currentUser;
	    }
	public static CurrentUser getCurrentUser() {
		return currentUser;
	}
	public static void setCurrentUser(CurrentUser currentUser) {
		CurrentUser.currentUser = currentUser;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario u) {
		this.usuario = u;
	}
	
	 
}
