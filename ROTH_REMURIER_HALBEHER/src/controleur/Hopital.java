/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import vue.Connection_Fen;
/**
 * Main du projet qui va servir à lancer le projet et la fenêtre de connexion à la base de données
 * @author basileroth
 */
public class Hopital{

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
                       
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {                
                try {
                    new Connection_Fen();
                } catch (SQLException ex) {
                    Logger.getLogger(Hopital.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
   
}
