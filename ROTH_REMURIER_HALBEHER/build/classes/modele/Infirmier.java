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
 * Classe infirmier pour ajout/suppression/modifications et blindage
 * @author basileroth
 */
public class Infirmier {
    
    private static int NbreColonnes = 4;
    private static String NomTable = "infirmier";
    private static Connection conn;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;

    /**
     * Constructeur 
     * @param pconn pour envoyer la connection créée auparavant
     */
    public Infirmier(Connection pconn)
    {
        this.conn = pconn;
    }
    
    /**
     * Méthode qui permet de supprimer les infirmiers dans la BDD ainsi que les 
     * infirmier/docteurs qui sont ces mêmes employés
     * @param NumEmploye
     * @throws SQLException
     */
    public void SupprimerInfirmier(int NumEmploye) throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        int Rs = stmt.executeUpdate("DELETE FROM `"+this.NomTable+"` WHERE numero = '"+NumEmploye+"'");      
        Rs = stmt.executeUpdate("DELETE FROM `employe` WHERE numero = '"+NumEmploye+"'");
        Rs = stmt.executeUpdate("DELETE FROM `employe` WHERE numero = '"+NumEmploye+"'");
    }
    
    /**
     * Méthode pour ajouter un infimier
     * @param pfenajouter
     */
    public void AjouterInfirmier(Ajouter_Fen pfenajouter)
    {
        this.fenajouter = pfenajouter;
        try {
            Statement stmt = conn.createStatement();                            
            String requete = "INSERT INTO employe VALUES ('"+fenajouter.getJTexField(14).getText()+"', '"+fenajouter.getJTexField(15).getText()+"', '"+fenajouter.getJTexField(16).getText()+"', '"+fenajouter.getJTexField(17).getText()+"', '"+fenajouter.getJTexField(18).getText()+"')";
            stmt.executeUpdate(requete);
            requete = "INSERT INTO infirmier VALUES ('"+fenajouter.getJTexField(14).getText()+"', '"+fenajouter.getJTexField(19).getText()+"', '"+fenajouter.getJTexField(20).getText()+"', '"+fenajouter.getJTexField(21).getText()+"')";
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenajouter.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Méthode pour modifier un infimier
     * @param pfenmodifier
     */
    public void MajInfirmier(Modifier_Fen pfenmodifier)
    {
        this.fenmodifier = pfenmodifier;
        try 
        {
            Statement stmt = conn.createStatement();                            
            String requete = "UPDATE employe SET numero = '"+fenmodifier.getJTexField(14).getText()+"', nom = '"+fenmodifier.getJTexField(15).getText()+"', prenom = '"+fenmodifier.getJTexField(16).getText()+"', adresse = '"+fenmodifier.getJTexField(17).getText()+"', tel = '"+fenmodifier.getJTexField(18).getText()+"' WHERE numero = '"+fenmodifier.getitem1()+"'";
            stmt.executeUpdate(requete);
            requete = "UPDATE infirmier SET numero = '"+fenmodifier.getJTexField(14).getText()+"', code_service = '"+fenmodifier.getJTexField(19).getText()+"', rotation = '"+fenmodifier.getJTexField(20).getText()+"', salaire = '"+fenmodifier.getJTexField(14).getText()+"' WHERE numero = '"+fenmodifier.getitem1()+"'"; 
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenmodifier.dispose();   
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
