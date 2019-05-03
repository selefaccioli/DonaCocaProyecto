package data;

import java.sql.*;
import util.DonaCocaException;

public class Conexion {
    
    public Connection getConn() throws DonaCocaException{
        Connection conn= null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
           // String usuarioDB= "tp_sele";
            //String passwordDB= "6BjeUP*_ZFjN";
            //String servidor= "jdbc:mysql://localhost:3306/tp_sele";
            /*String usuarioDB="sfaccioli";
	    String passwordDB="f123";
            String servidor = "jdbc:mysql://localhost:3309/finaljava"; */
            String usuarioDB= "root";
            String passwordDB= "MGSbgy37491";
            String servidor= "jdbc:mysql://node31552-donacoca.jelastic.saveincloud.net/finalJava";
            
            conn= DriverManager.getConnection(servidor, usuarioDB, passwordDB);
            
        }catch(ClassNotFoundException e){
            throw new DonaCocaException(e);
        }
        catch(SQLException e){
            throw new DonaCocaException(e);
        }
        catch(Exception e){
            throw new DonaCocaException(e);
        }
        
        return conn;
        
    }
    
}
