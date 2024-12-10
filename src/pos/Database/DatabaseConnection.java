
package pos.Database;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class DatabaseConnection {

    public DatabaseConnection() {
    }
     public Connection getCConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/point_of_sale_db", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Error");
        
        return null;
    }
    }
      public void closeConnection(Connection connection){
        try{
            if (connection != null&& !connection.isClosed()){
               connection.close(); 
            }
        } catch (SQLException e){
          e.printStackTrace();
        }
    }
}
