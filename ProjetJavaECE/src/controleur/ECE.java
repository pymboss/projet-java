/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vue.Connexion_Win;

/**
 *
 * @author pymas
 */
public class ECE {
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable(){
         
            public void run(){
                
               Connexion_Win c = new Connexion_Win();
               c.setVisible(true);
            }  
     
        });
        
    }
    
}
