/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vue.Ajouter_Fen;
import vue.Modifier_Fen;

/**
 * Classe employe pour ajout/suppression/modifications et blindage
 * @author basileroth
 */
public class Employe {
    
    private static int NbreColonnes = 5;
    private static String NomTable = "employe";
    private static Connection conn;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;

    /**
     * Constructeur 
     * @param pconn pour envoyer la connection créée auparavant
     */
    public Employe(Connection pconn)
    {
        this.conn = pconn;
    }
    
    /**
     * Méthode qui permet de supprimer les employés dans la BDD ainsi que les 
     * infirmier/docteurs qui sont ces mêmes employés et les soignés concernés.
     * @param NumEmploye
     * @throws SQLException
     */
    public void SupprimerEmploye(int NumEmploye) throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        int Rs = stmt.executeUpdate("DELETE FROM `"+this.NomTable+"` WHERE numero = '"+NumEmploye+"'");      
        Rs = stmt.executeUpdate("DELETE FROM `docteur` WHERE numero = '"+NumEmploye+"'");
        Rs = stmt.executeUpdate("DELETE FROM `infirmier` WHERE numero = '"+NumEmploye+"'");
        Rs = stmt.executeUpdate("DELETE FROM `soigne` WHERE no_docteur = '"+NumEmploye+"'");
    }
    
    /**
     * Pour le blindage du numéro des employés 
     * @param pNumEmploye
     * @return
     * @throws SQLException
     */
    public boolean CheckNumEmploye(String pNumEmploye) throws SQLException
    {
        boolean a = true;
        Statement stmt = this.conn.createStatement();
        ResultSet Rs = stmt.executeQuery("SELECT FROM `employe` WHERE surveillant = '"+pNumEmploye+"'");
        if(Rs.next()){a = true;}
        else{a = false;}
        return a;
    }
    
}
