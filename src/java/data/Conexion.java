package data;

import java.sql.*;
import util.DonaCocaException;

public class Conexion {
    
    public Connection getConn() throws DonaCocaException{
        Connection conn= null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String usuarioDB= "root";
            String passwordDB= "YHZmeq25645";
            String servidor= "jdbc:mysql://node31527-donacoca.jelastic.saveincloud.net/donaCocaFinalJava";
            
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
