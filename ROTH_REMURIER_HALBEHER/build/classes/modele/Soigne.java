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
 * Classe soigne pour ajout/suppression/modifications et blindage
 * @author basileroth
 */
public class Soigne {
    
    private static int NbreColonnes = 2;
    private static String NomTable = "soigne";
    private static Connection conn;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;

    /**
     * Constructeur 
     * @param pconn pour envoyer la connection créée auparavant
     */
    public Soigne(Connection pconn)
    {
        this.conn = pconn;
    }
    
    /**
     * Méthode qui permet de supprimer les soignes 
     * @param NumDoc
     * @throws SQLException
     */
    public void SupprimerSoigne(int NumDoc) throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        int Rs = stmt.executeUpdate("DELETE FROM `"+this.NomTable+"` WHERE numero = '"+NumDoc+"'");      

    }
    
    /**
     * Méthode pour ajouter un soigne
     * @param pfenajouter
     */
    public void AjouterSoigne(Ajouter_Fen pfenajouter)
    {
        this.fenajouter = pfenajouter;
        try 
        {
            Statement stmt = conn.createStatement();
            String requete = "INSERT INTO soigne VALUES ('"+fenajouter.getBoxSoigne().getSelectedItem()+"', '"+fenajouter.getJTexField(34).getText()+"')";
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);                
            fenajouter.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Méthode pour modifier un soigne
     * @param pfenmodifier
     */
    public void MajSoigne(Modifier_Fen pfenmodifier)
    {
        this.fenmodifier = pfenmodifier;
        try 
        {
            Statement stmt = conn.createStatement();                            
            String requete = "UPDATE soigne SET no_docteur = '"+fenmodifier.getBoxSoigne().getSelectedItem()+"', no_malade = '"+fenmodifier.getJTexField(34).getText()+"' WHERE no_docteur = '"+fenmodifier.getitem1()+"' AND no_malade = '"+fenmodifier.getitem2()+"'"; 
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenmodifier.dispose();   
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
