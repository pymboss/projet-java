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
 * Classe hospitalisation pour ajout/suppression/modifications et blindage
 * @author basileroth
 */
public class Hospitalisation {
    
    private static int NbreColonnes = 4;
    private static String NomTable = "chambre";
    private static Connection conn;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;
    

    /**
     * Constructeur 
     * @param pconn pour envoyer la connection créée auparavant
     */
    public Hospitalisation(Connection pconn)
    {
        this.conn = pconn;
    }
    
    /**
     * Méthode qui permet de supprimer les chambres dans la BDD ainsi que les 
     * hospitalisations concernées par la suppression de ces chambres.
     * @param NumMalalde
     * @throws SQLException
     */
    public void SupprimerHospitalisation(int NumMalalde) throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        int Rs = stmt.executeUpdate("DELETE FROM `"+this.NomTable+"` WHERE no_chambre = '"+NumMalalde+"'");      
        Rs = stmt.executeUpdate("DELETE FROM `hospitalisation` WHERE no_chambre = '"+NumMalalde+"'");
    }
    
    /**
     * Méthode pour ajouter une hospitalisation
     * @param pfenajouter
     */
    public void AjouterHospitalisation(Ajouter_Fen pfenajouter)
    {
        this.fenajouter = pfenajouter;
        try {
            Statement stmt = conn.createStatement();
            String requete = "INSERT INTO hospitalisation VALUES ("+fenajouter.getJTexField(10).getText()+", '"+fenajouter.getComboBoxHospi().getSelectedItem()+"', '"+fenajouter.getJTexField(12).getText()+"', '"+fenajouter.getJTexField(13).getText()+"')";
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);     
            fenajouter.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Méthode pour modifier une hospitalisation
     * @param pfenmodifier
     */
    public void MajHospitalisation(Modifier_Fen pfenmodifier)
    {
        this.fenmodifier = pfenmodifier;
        try 
        {
            Statement stmt = conn.createStatement();                            
            String requete = "UPDATE hospitalisation SET no_malade = '"+fenmodifier.getJTexField(10).getText()+"', code_service = '"+fenmodifier.getComboBoxHospi().getSelectedItem()+"', no_chambre = '"+fenmodifier.getJTexField(12).getText()+"', lit = '"+fenmodifier.getJTexField(13).getText()+"' WHERE no_malade = '"+fenmodifier.getitem1()+"'";
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenmodifier.dispose();   
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
