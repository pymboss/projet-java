/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;
import java.sql.*;

/**
 *
 * @author pymas
 */
public class Connect {
    
    public Connect(){
        
    }
    
    public void connect() {      
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver O.K.");

      String url = "jdbc:mysql://localhost/famille";
      String user = "root";
      String passwd = "";
      
      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connexion effective !");         
         
        try ( //CrÃ©ation d'un objet Statement
                Statement state = conn.createStatement()) {
            // chargement des bases de données 
        }
     
    } catch (ClassNotFoundException | SQLException e) {
    }      
  }
    
}
