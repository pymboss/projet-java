/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;
import java.sql.*;
public class Connect {

    
    // attributs si besoin
    
    
             
    public static void main(String[] args) {      
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver O.K.");

      String url = "jdbc:mysql://localhost/ + nom  base de donn�e ";
      String user = "root";
      String passwd = "";
      
      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connexion effective !");         
         
        try ( //Création d'un objet Statement
                Statement state = conn.createStatement()) {
            // chargement des bases de donn�es 
        }
     
    } catch (ClassNotFoundException | SQLException e) {
    }      
  }
    
   
  
}



