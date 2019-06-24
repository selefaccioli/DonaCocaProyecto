package data;
import java.sql.*;

import util.DonaCocaException;


public class FactoryConexion {
	
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	/*private String port="3309";
	private String user="sfaccioli";
        private String password="f123";
	private String db="finaljava"; */
      private String user="donac_sol";
	private String password="f37453481";
	private String db="donacoca_entrega";
        private String port="3306";
	
	private static FactoryConexion instancia;
		
	private FactoryConexion(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static FactoryConexion getInstancia(){
		if (FactoryConexion.instancia == null){		
			FactoryConexion.instancia=new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
	}
	
	private Connection conn;
	private int cantConn=0;
	public Connection getConn() throws SQLException,DonaCocaException{
		try {
			if(conn==null || conn.isClosed()){	
				conn = DriverManager.getConnection(
			        "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password);
			}
		} catch (SQLException e) {
			throw new DonaCocaException(e, "Error al conectar a la base de datos");
		}
		cantConn++;
		return conn;
	}
	
	public void releaseConn() throws SQLException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	

}