/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vue.Accueil_Fen;
import vue.Ajouter_Fen;
import vue.Modifier_Fen;

/**
 * Classe malade pour ajout/suppression/modifications et blindage
 * @author basileroth
 */
public class Malade {
    
    private static int NbreColonnes = 6;
    private static String NomTable = "malade";
    private static Connection conn;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;

    /**
     * Constructeur 
     * @param pconn pour envoyer la connection créée auparavant
     */
    public Malade(Connection pconn)
    {
        this.conn = pconn;
    }
    
    /**
     * Méthode qui permet de supprimer les malades dans la BDD ainsi que les 
     * hospitalisations concernées par la suppression de ces chambres.
     * @param NumMalade
     * @throws SQLException
     */
    public void SupprimerMalade(int NumMalade) throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        int Rs = stmt.executeUpdate("DELETE FROM `"+this.NomTable+"` WHERE numero = '"+NumMalade+"'");      
        Rs = stmt.executeUpdate("DELETE FROM `soigne` WHERE no_malade = '"+NumMalade+"'");
        Rs = stmt.executeUpdate("DELETE FROM `hospitalisation` WHERE no_malade = '"+NumMalade+"'");
    }
    
    /**
     * Méthode pour ajouter un malade
     * @param pfenajouter
     */
    public void AjouterMalade(Ajouter_Fen pfenajouter)
    {
        this.fenajouter = pfenajouter;
        try {
            Statement stmt = conn.createStatement();
            String requete = "INSERT INTO malade VALUES ("+fenajouter.getJTexField(23).getText()+", '"+fenajouter.getJTexField(24).getText()+"', '"+fenajouter.getJTexField(25).getText()+"', '"+fenajouter.getJTexField(26).getText()+"', '"+fenajouter.getJTexField(27).getText()+"', '"+fenajouter.getJTexField(28).getText()+"')";
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);     
            fenajouter.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Méthode pour modifier un malade
     * @param pfenmodifier
     */
    public void MajMalade(Modifier_Fen pfenmodifier)
    {
        this.fenmodifier = pfenmodifier;
        try 
        {
            Statement stmt = conn.createStatement();                            
            String requete = "UPDATE malade SET numero = '"+fenmodifier.getJTexField(23).getText()+"', nom = '"+fenmodifier.getJTexField(24).getText()+"', prenom = '"+fenmodifier.getJTexField(25).getText()+"', adresse = '"+fenmodifier.getJTexField(26).getText()+"', tel = '"+fenmodifier.getJTexField(27).getText()+"', mutuelle = '"+fenmodifier.getJTexField(28).getText()+"' WHERE numero = '"+fenmodifier.getitem1()+"'";
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenmodifier.dispose();   
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
