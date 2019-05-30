/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
import controleur.Connexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vue.Accueil_Fen;
import vue.Ajouter_Fen;
import vue.Modifier_Fen;

/**
 * Classe chambre pour ajout/suppression/modifications et blindage
 * @author basileroth
 */
public class Chambre {
    
    private static int NbreColonnes = 4;
    private static String NomTable = "chambre";
    private static Connection conn;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;

    /**
     * Constructeur avec la connection
     * @param pconn pour envoyer la connection créée auparavant
     */
    public Chambre(Connection pconn)
    {
        this.conn = pconn;
    }
    
    /**
     * Méthode qui permet de supprimer les chambres dans la BDD ainsi que les 
     * hospitalisations concernées par la suppression de ces chambres.
     * @param NumChambre
     * @throws SQLException
     */
    public void SupprimerChambre(int NumChambre) throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        int Rs = stmt.executeUpdate("DELETE FROM `"+this.NomTable+"` WHERE no_chambre = '"+NumChambre+"'");      
        Rs = stmt.executeUpdate("DELETE FROM `hospitalisation` WHERE no_chambre = '"+NumChambre+"'");
    }
    
    /**
     * Pour le blindage du service lors de l'ajout d'une chambre
     * @param pCode
     * @return
     * @throws SQLException
     */
    public boolean CheckService(String pCode) throws SQLException
    {
        boolean a = true;
        Statement stmt = this.conn.createStatement();
        ResultSet Rs = stmt.executeQuery("SELECT FROM `service` WHERE code = '"+pCode+"'");
        if(Rs.next()){a = true;}
        else{a = false;}
        return a;
    }
    
    /**
     * Pour le blindage de la chambre lors de l'ajout d'une chambre
     * @param pNo_Chambre
     * @return
     * @throws SQLException
     */
    public boolean CheckChambre(int pNo_Chambre) throws SQLException
    {
        boolean a = true;
        Statement stmt = this.conn.createStatement();
        ResultSet Rs = stmt.executeQuery("SELECT FROM `chambre` WHERE no_chambre = '"+pNo_Chambre+"'");
        if(Rs.next()){a = true;}
        else{a = false;}
        return a;
    }
    
    /**
     * Pour le blindage de la chambre lors de l'ajout d'une chambre
     * @param pNumSurveillant
     * @return
     * @throws SQLException
     */
    public boolean CheckSurveillant(String pNumSurveillant) throws SQLException
    {
        boolean a = true;
        Statement stmt = this.conn.createStatement();
        ResultSet Rs = stmt.executeQuery("SELECT FROM `service` WHERE surveillant = '"+pNumSurveillant+"'");
        if(Rs.next()){a = true;}
        else{a = false;}
        return a;
    }
    
    /**
     * Ajout d'une chambre
     * @param pfenajouter
     */
    public void AjouterChambre(Ajouter_Fen pfenajouter)
    {
        this.fenajouter = pfenajouter;
        try 
        {
            Statement stmt = conn.createStatement();
            String requete = "INSERT INTO chambre VALUES ('"+fenajouter.getComboBoxChambre().getSelectedItem()+"', '"+fenajouter.getJTexField(1).getText()+"', '"+fenajouter.getJTexField(2).getText()+"', '"+fenajouter.getJTexField(3).getText()+"')"; 
            stmt.executeUpdate(requete);
            new Accueil_Fen(conn).setVisible(true);
            fenajouter.dispose();   
        } catch (SQLException ex) 
        {Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    /**
     * Mise à jour d'une chambre
     * @param pfenmodifier
     */
    public void MajChambre(Modifier_Fen pfenmodifier)
    {
        this.fenmodifier = pfenmodifier;
        try 
        {
            Statement stmt = conn.createStatement();
            int Rs = stmt.executeUpdate("UPDATE `chambre` SET `code_service`='"+fenmodifier.getComboBoxChambre().getSelectedItem()+"',`no_chambre`='"+fenmodifier.getJTexField(1).getText()+"',`surveillant`='"+fenmodifier.getComboBoxChambre2().getSelectedItem()+"',`nb_lits`='"+fenmodifier.getJTexField(3).getText()+"'WHERE no_chambre = '"+fenmodifier.getitem2()+"' AND surveillant = '"+fenmodifier.getitem3()+"'");
            new Accueil_Fen(conn).setVisible(true);
            fenmodifier.dispose();   
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
}
