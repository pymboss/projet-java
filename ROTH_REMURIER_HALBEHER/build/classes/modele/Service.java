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
 * Classe service pour ajout/suppression/modifications et blindage
 * @author basileroth
 */
public class Service {
    
    private static int NbreColonnes = 4;
    private static String NomTable = "service";
    private static Connection conn;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;

    /**
     * Constructeur 
     * @param pconn pour envoyer la connection créée auparavant
     */
    public Service(Connection pconn)
    {
        this.conn = pconn;
    }
    
    /**
     * Méthode qui permet de supprimer les services 
     * @param NumEmploye
     * @throws SQLException
     */
    public void SupprimerService(int NumEmploye) throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        int Rs = stmt.executeUpdate("DELETE FROM `"+this.NomTable+"` WHERE numero = '"+NumEmploye+"'");      
    }
    
    /**
     * Méthode pour ajouter un service
     * @param pfenajouter
     */
    public void AjouterService(Ajouter_Fen pfenajouter)
    {
        this.fenajouter = pfenajouter;
        try {
            Statement stmt = conn.createStatement();
            String requete = "INSERT INTO service VALUES ('"+fenajouter.getJTexField(29).getText()+"', '"+fenajouter.getJTexField(30).getText()+"', '"+fenajouter.getJTexField(31).getText()+"', '"+fenajouter.getJTexField(32).getText()+"')";
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);     
            fenajouter.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Méthode pour modifier un service
     * @param pfenmodifier
     */
    public void MajService(Modifier_Fen pfenmodifier)
    {
        this.fenmodifier = pfenmodifier;
        try 
        {
            Statement stmt = conn.createStatement();                            
            String requete = "UPDATE service SET code = '"+fenmodifier.getJTexField(29).getText()+"', nom = '"+fenmodifier.getJTexField(30).getText()+"', batiment = '"+fenmodifier.getJTexField(31).getText()+"', directeur = '"+fenmodifier.getJTexField(32).getText()+"' WHERE code = '"+fenmodifier.getitem1()+"'";
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenmodifier.dispose();   
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
