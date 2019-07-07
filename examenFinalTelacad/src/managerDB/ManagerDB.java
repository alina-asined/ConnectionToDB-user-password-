/*
 *create  class who contain the manager for data base
 *create a single instance for the class
 *create a connection for DB
 */
package managerDB;

import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
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
    public boolean userExist(String name){
    
        String sql = "SELECT user FROM users WHERE user = '" + name + "'";
        try (
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

                    while(rs.next()){
                        if( rs.getString("user") != null){
                               return true;
                        }
                    }
                    
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void addUser(String user, String password){
        String sql = "INSERT INTO users VALUES(null, ?, ?)";
        try (
                PreparedStatement stmt = con.prepareStatement(sql);
                
                ){
            stmt.setString(1, user);
            stmt.setString(2, password);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public boolean verifyUserPassword(String user, String password){
    
        String sql = "SELECT user FROM users WHERE (user='" + user + "') AND (password ='"+password+"')";
                    //SELECT user FROM users WHERE (user='user') AND (password='password')
                
                    
        try (
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){
//            stmt.setString(1, user);
//            stmt.setString(2, password);
            

            while(rs.next()){    
                if(rs.getString("user") != null){
                    return true;
                }
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    //method to delete
    public void delete(String nume){
        String sql = "DELETE FROM users WHERE user = '" + nume + "'";
            try (
                    PreparedStatement stmt = con.prepareStatement(sql);
                    
                    ){
                
                stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
}
