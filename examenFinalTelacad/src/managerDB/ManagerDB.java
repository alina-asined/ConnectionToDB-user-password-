/*
 *create  class who contain the manager for data base
 *create a single instance for the class
 *create a connection for DB
 */
package managerDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Guga
 */
public class ManagerDB {
    
    private Connection con;
    
    private ManagerDB(){
        
        String url = "jdbc:mysql://localhost/java1pexamen";
        try {
                con = DriverManager.getConnection(url,"root","");
                                                   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    private static final class SingletonHolder{
        private static ManagerDB SINGLETON = new ManagerDB();
    }
    
    public static ManagerDB getInstance(){
        return SingletonHolder.SINGLETON;
    }
    public boolean userExist(String user){
    
        String sql = "SELECT user FROM users WHERE user = ?'" + user + "'";
        try (
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

                    while(rs.next()){
                        if(rs.getString("user") != null){
                               return true;
                        }
                    }
                
            
        }catch (Exception e) {
        }
        return false;
    }
}
