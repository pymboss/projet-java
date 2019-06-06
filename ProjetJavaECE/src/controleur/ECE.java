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
    
    public static void main(String[] args) {
    
    Connect c = new Connect();
        try {
        Connexion_Win connexion_Win = new Connexion_Win();
        } catch (SQLException ex) {
            Logger.getLogger(ECE.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
