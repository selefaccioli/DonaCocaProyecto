package data;

import java.sql.*;
import util.DonaCocaException;

public class Conexion {
    
    public Connection getConn() throws DonaCocaException{
        Connection conn= null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String usuarioDB= "tp_sele";
            String passwordDB= "6BjeUP*_ZFjN";
            String servidor= "jdbc:mysql://localhost:3306/tp_sele";
            
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
