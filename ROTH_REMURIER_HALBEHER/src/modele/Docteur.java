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
 * Classe docteur pour ajout/suppression/modifications et blindage
 * @author basileroth
 */
public class Docteur {
    
    private static int NbreColonnes = 2;
    private static String NomTable = "docteur";
    private static Connection conn;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;

    /**
     * Constructeur 
     * @param pconn pour envoyer la connection créée auparavant
     */
    public Docteur(Connection pconn)
    {
        this.conn = pconn;
    }
    
    /**
     * Méthode qui permet de supprimer les docteur dans la BDD ainsi que les 
     * employés et les soignés concernés.
     * @param NumDoc
     * @throws SQLException
     */
    public void SupprimerDocteur(int NumDoc) throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        int Rs = stmt.executeUpdate("DELETE FROM `"+this.NomTable+"` WHERE numero = '"+NumDoc+"'");      
        Rs = stmt.executeUpdate("DELETE FROM `soigne` WHERE no_docteur = '"+NumDoc+"'");
        Rs = stmt.executeUpdate("DELETE FROM `employe` WHERE numero = '"+NumDoc+"'");
    }
     
    /**
     * Méthode pour ajouter un docteur
     * @param pfenajouter
     */
    public void AjouterDocteur(Ajouter_Fen pfenajouter)
    {
        this.fenajouter = pfenajouter;
        try 
        {
            Statement stmt = conn.createStatement();                            
            String requete = "INSERT INTO employe VALUES ("+fenajouter.getJTexField(4).getText()+", '"+fenajouter.getJTexField(5).getText()+"', '"+fenajouter.getJTexField(6).getText()+"', '"+fenajouter.getJTexField(7).getText()+"', '"+fenajouter.getJTexField(8).getText()+"')";
            stmt.executeUpdate(requete);
            requete = "INSERT INTO docteur VALUES ("+fenajouter.getJTexField(4).getText()+", '"+(String) fenajouter.getComboBoxDocteur().getSelectedItem()+"')"; 
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenajouter.dispose();   
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Méthode pour modifier un docteur
     * @param pfenmodifier
     */
    public void MajDocteur(Modifier_Fen pfenmodifier)
    {
        this.fenmodifier = pfenmodifier;
        try 
        {
            Statement stmt = conn.createStatement();                            
            String requete = "UPDATE employe SET numero = '"+fenmodifier.getJTexField(4).getText()+"', nom = '"+fenmodifier.getJTexField(5).getText()+"', prenom = '"+fenmodifier.getJTexField(6).getText()+"', adresse = '"+fenmodifier.getJTexField(7).getText()+"', tel = '"+fenmodifier.getJTexField(8).getText()+"' WHERE numero = '"+fenmodifier.getitem1()+"'";
            stmt.executeUpdate(requete);
            requete = "UPDATE docteur SET numero = '"+fenmodifier.getJTexField(4).getText()+"', specialite = '"+(String) fenmodifier.getComboBoxDocteur().getSelectedItem()+"' WHERE numero = '"+fenmodifier.getitem1()+"'"; 
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenmodifier.dispose();   
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
