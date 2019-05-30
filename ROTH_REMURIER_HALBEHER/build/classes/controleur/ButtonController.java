/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modele.Chambre;
import modele.Docteur;
import modele.Hospitalisation;
import modele.Infirmier;
import modele.Malade;
import modele.Reporting;
import modele.Service;
import modele.Soigne;
import vue.Ajouter_Fen;
import vue.Connection_Fen;
import vue.Accueil_Fen;
import vue.Modifier_Fen;
import vue.Report_Fen;

/**
 * Classe qui va gérer toutes les interractions avec les boutons 
 * @author basileroth
 */
public class ButtonController implements ActionListener
{
    private Report_Fen fenreport = null;
    private Connection_Fen fenconn = null;
    private Accueil_Fen fenprincipale = null;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;
    private Connection connection = null;
    
    /**
     * Constructeur pour la fenêtre de reporting
     * @param fen
     * @param conn
     */
    public ButtonController(Report_Fen fen, Connection conn)
    {
        this.fenreport = fen;
        this.connection = conn;
    }

    /**
     * Constructeur pour la fenêtre de connexion
     * @param fen
     */
    public ButtonController(Connection_Fen fen)
    {
        this.fenconn = fen;
    }
    
    /**
     * Constructeur pour la fenêtre d'accueil
     * @param fen
     * @param conn
     */
    public ButtonController(Accueil_Fen fen,Connection conn)
    {
        this.fenprincipale = fen;
        this.connection = conn;
    }
    
    /**
     * Constructeur pour la fenêtre d'ajout
     * @param fen
     * @param conn
     */
    public ButtonController(Ajouter_Fen fen,Connection conn)
    {
        this.fenajouter = fen;
        this.connection = conn;
    }
    
    /**
     * Constructeur pour la fenêtre de modifications
     * @param fen
     * @param conn
     */
    public ButtonController(Modifier_Fen fen,Connection conn)
    {
        this.fenmodifier = fen;
        this.connection = conn;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // ActionListener avec la fenêtre d'ajout
        if(!(this.fenajouter == null))
        {
            // Boutons de retour vers la fenêtre principale
            if(e.getSource() == fenajouter.getretour(0) || e.getSource() == fenajouter.getretour(1) || e.getSource() == fenajouter.getretour(2) || e.getSource() == fenajouter.getretour(3) || e.getSource() == fenajouter.getretour(4) || e.getSource() == fenajouter.getretour(5) || e.getSource() == fenajouter.getretour(6) || e.getSource() == fenajouter.getretour(7))
            {
                try {
                    fenajouter.dispose();
                    new Accueil_Fen(connection);
                } catch (SQLException ex) {Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);}
            }
            
            // CHAMBRE
            if (e.getSource().equals(fenajouter.getSubmit(0))) 
            {
                new Chambre(connection).AjouterChambre(fenajouter);
            }

            // DOCTEUR
            if (e.getSource().equals(fenajouter.getSubmit(1))) 
            {
                new Docteur(connection).AjouterDocteur(fenajouter);
            }

            // HOSPITALISATION
            if (e.getSource().equals(fenajouter.getSubmit(2))) 
            {
                new Hospitalisation(connection).AjouterHospitalisation(fenajouter);
            }

            // INFIRMIER
            if (e.getSource().equals(fenajouter.getSubmit(3))) 
            {
                new Infirmier(connection).AjouterInfirmier(fenajouter);
            }

            // MALADE
            if (e.getSource().equals(fenajouter.getSubmit(4))) 
            {
                new Malade(connection).AjouterMalade(fenajouter);
            }

            // SERVICE
            if (e.getSource().equals(fenajouter.getSubmit(5))) 
            {
                new Service(connection).AjouterService(fenajouter);
            }

            // Ajout dans la BDD d'un SOIGNE en fonction des champs rentrés
            if (e.getSource().equals(fenajouter.getSubmit(6))) 
            {
                new Soigne(connection).AjouterSoigne(fenajouter);
            }
        }
        
        
        // ActionListener avec la fenêtre d'ajout
        if(!(this.fenmodifier == null))
        {
            if(e.getSource() == fenmodifier.getretour())
            {
                try {
                    fenmodifier.dispose();
                    new Accueil_Fen(connection);
                } catch (SQLException ex) {Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);}
            }
            
            // CHAMBRE
            if (e.getSource().equals(fenmodifier.getSubmit(0))) 
            {
                new Chambre(connection).MajChambre(fenmodifier);
            }

            // DOCTEUR
            if (e.getSource().equals(fenmodifier.getSubmit(1))) 
            {
                new Docteur(connection).MajDocteur(fenmodifier);
            }

            // HOSPITALISATION
            if (e.getSource().equals(fenmodifier.getSubmit(2))) 
            {
                new Hospitalisation(connection).MajHospitalisation(fenmodifier);
            }

            // infirmier
            if (e.getSource().equals(fenmodifier.getSubmit(3))) 
            {
                new Infirmier(connection).MajInfirmier(fenmodifier);
            } 

            // malade
            if (e.getSource().equals(fenmodifier.getSubmit(4))) 
            {
                new Malade(connection).MajMalade(fenmodifier);
            }
            // service
            if (e.getSource().equals(fenmodifier.getSubmit(5))) 
            {
                new Service(connection).MajService(fenmodifier);
            }

            // soigne
            if (e.getSource().equals(fenmodifier.getSubmit(6))) 
            {
                new Soigne(connection).MajSoigne(fenmodifier);
            }
        }

        // Pour les actions de Reporting
        if(!(this.fenreport == null))
        {
            
            if(e.getSource() == fenreport.getbtn(0))
            {   
                try 
                {
                    Statement stmt = connection.createStatement();
                    ResultSet resultat = stmt.executeQuery("SELECT (SELECT COUNT(*) FROM hospitalisation WHERE code_service = 'CAR') as count1, (SELECT COUNT(*) FROM hospitalisation WHERE code_service = 'CHG') as count2, (SELECT COUNT(*) FROM hospitalisation WHERE code_service = 'REA') as count3");
                    resultat.next();                
                    String newStrData[]={"Cardiologie","Chirurgie","Reanimation"};
                    int newNbrData[]={resultat.getInt("count1"),resultat.getInt("count2"),resultat.getInt("count3")};
                    String newReport="Nombre de malades par service";
                    JPanel recup = null;
                    if(fenreport.getradio1().isSelected())
                    {
                        Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert");
                        recup = tpc.getPanel();
                    }
                    else if(fenreport.getradio2().isSelected())
                    {
                        Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"barre");
                        recup = tpc.getPanel();
                    }
                    else if(fenreport.getradio3().isSelected())
                    {
                        Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert3");
                        recup = tpc.getPanel();
                    }
                    fenreport.setPanel(recup);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(e.getSource() == fenreport.getbtn(1))
            {   
                try 
                {
                    Statement stmt = connection.createStatement();
                    ResultSet resultat = stmt.executeQuery("SELECT (SELECT COUNT(*) FROM docteur WHERE specialite = 'Anesthesiste') as count1, (SELECT COUNT(*) FROM docteur WHERE specialite = 'Cardiologue') as count2, (SELECT COUNT(*) FROM docteur WHERE specialite = 'Orthopediste') as count3 , (SELECT COUNT(*) FROM docteur WHERE specialite = 'Pneumologue') as count4, (SELECT COUNT(*) FROM docteur WHERE specialite = 'Radiologue') as count5, (SELECT COUNT(*) FROM docteur WHERE specialite = 'Generaliste') as count6, (SELECT COUNT(*) FROM docteur WHERE specialite = 'Traumatologue') as count7");
                    resultat.next();                
                    String newStrData[]={"Anesthesiste","Cardiologue","Generaliste","Orthopediste","Pneumologue","Radiologue","Traumatologue"};
                    int newNbrData[]={resultat.getInt("count1"),resultat.getInt("count2"),resultat.getInt("count3"),resultat.getInt("count4"),resultat.getInt("count5"),resultat.getInt("count6"),resultat.getInt("count7")};
                    String newReport="Nombre de docteurs par spécialités";
                    JPanel recup = null;
                    if(fenreport.getradio1().isSelected())
                    {
                        Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert");
                        recup = tpc.getPanel();
                    }
                    else if(fenreport.getradio2().isSelected())
                    {
                        Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"barre"); 
                        recup = tpc.getPanel();
                    }
                    else if(fenreport.getradio3().isSelected())
                    {
                        Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert3"); 
                        recup = tpc.getPanel();
                    }
                    fenreport.setPanel(recup);
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(e.getSource() == fenreport.getbtn(2))
            {   
                try {
                        Statement stmt = connection.createStatement();
                        ResultSet resultat = stmt.executeQuery("SELECT (SELECT COUNT(*) FROM infirmier WHERE salaire < 1300) as petit, (SELECT COUNT(*) FROM infirmier WHERE salaire > 1300 AND salaire < 1600) as milieu, (SELECT COUNT(*) FROM infirmier WHERE salaire > 1600) as haut ");
                        resultat.next(); 

                        String newStrData[]={"Inferieur à 1300€","Entre 1300 et 1600€","Superieur à 1600€"};
                        int newNbrData[]={resultat.getInt("petit"),resultat.getInt("milieu"),resultat.getInt("haut")};
                        String newReport="Salaire des infirmiers";
                        JPanel recup = null;
                        if(fenreport.getradio1().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert");
                            recup = tpc.getPanel();
                        }
                        else if(fenreport.getradio2().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"barre"); 
                            recup = tpc.getPanel();
                        }
                        else if(fenreport.getradio3().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert3"); 
                            recup = tpc.getPanel();
                        }
                        fenreport.setPanel(recup);
                    } catch (SQLException ex) {
                        Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

            if(e.getSource() == fenreport.getbtn(3))
            {   
                try {
                        Statement stmt = connection.createStatement();
                        ResultSet resultat = stmt.executeQuery("SELECT (SELECT COUNT(*) FROM infirmier WHERE code_service = 'CAR') as count1, (SELECT COUNT(*) FROM infirmier WHERE code_service = 'CHG') as count2, (SELECT COUNT(*) FROM infirmier WHERE code_service = 'REA') as count3");
                        resultat.next(); 

                        String newStrData[]={"Cardiologie","Chirurgie","Reanimation"};
                        int newNbrData[]={resultat.getInt("count1"),resultat.getInt("count2"),resultat.getInt("count3")};
                        String newReport="Nombre d'infirmiers par service";
                        JPanel recup = null;
                        if(fenreport.getradio1().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert");
                            recup = tpc.getPanel();
                        }
                        else if(fenreport.getradio2().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"barre");
                            recup = tpc.getPanel();
                        }
                        else if(fenreport.getradio3().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert3"); 
                            recup = tpc.getPanel();
                        }
                        fenreport.setPanel(recup);
                    } catch (SQLException ex) {
                        Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

            if(e.getSource() == fenreport.getbtn(4))
            {   
                try {
                        Statement stmt = connection.createStatement();                    
                        ResultSet resultat = stmt.executeQuery("SELECT COUNT(*) AS resultat FROM docteur");
                        resultat.next();
                        int a = resultat.getInt("resultat");

                        resultat = stmt.executeQuery("SELECT COUNT(*) AS resultat FROM infirmier");
                        resultat.next();
                        int b = resultat.getInt("resultat");

                        String newStrData[]={"Docteur","Infirmier"};
                        int newNbrData[]={a,b};
                        String newReport="Métier des employés";
                        JPanel recup = null;
                        if(fenreport.getradio1().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert");
                            recup = tpc.getPanel();
                        }
                        else if(fenreport.getradio2().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"barre"); 
                            recup = tpc.getPanel();
                        }
                        else if(fenreport.getradio3().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert3");
                            recup = tpc.getPanel();
                        }
                        fenreport.setPanel(recup);
                    } catch (SQLException ex) {
                        Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

            if(e.getSource() == fenreport.getbtn(5))
            {   
                try {
                        Statement stmt = connection.createStatement();                    
                        ResultSet resultat = stmt.executeQuery("SELECT (SELECT COUNT(*) FROM soigne WHERE no_docteur = 4) as count1, (SELECT COUNT(*) FROM soigne WHERE no_docteur = 7) as count2, (SELECT COUNT(*) FROM soigne WHERE no_docteur = 8) as count3 , (SELECT COUNT(*) FROM soigne WHERE no_docteur = 10) as count4, (SELECT COUNT(*) FROM soigne WHERE no_docteur = 19) as count5, (SELECT COUNT(*) FROM soigne WHERE no_docteur = 24) as count6, (SELECT COUNT(*) FROM soigne WHERE no_docteur = 26) as count7");
                        resultat.next();
                        String newStrData[]={"Dr. Nadal","Dr. Bjorkman","Dr. Ferrer","Dr. Safin","Dr. Zvonareva","Dr. Hantuchova","Dr. Grosjean"};
                        int newNbrData[]={resultat.getInt("count1"),resultat.getInt("count2"),resultat.getInt("count3"),resultat.getInt("count4"),resultat.getInt("count5"),resultat.getInt("count6"),resultat.getInt("count7")};

                        String newReport="Nombre de malade par docteur";
                        JPanel recup = null;
                        if(fenreport.getradio1().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert");
                            recup = tpc.getPanel();
                        }
                        else if(fenreport.getradio2().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"barre");
                            recup = tpc.getPanel();
                        }
                        else if(fenreport.getradio3().isSelected())
                        {
                            Reporting tpc = new Reporting(newStrData,newNbrData,newReport,"camembert3");
                            recup = tpc.getPanel();
                        }
                        fenreport.setPanel(recup);
                    } catch (SQLException ex) {
                        Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

            if(e.getSource() == fenreport.getbtn(6))
            {   
                try {
                    new Accueil_Fen(connection).setVisible(true);
                    fenreport.dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
        
        // Pour gérer la fenêtre de connection
        if(!(this.fenconn == null))
        {
            if(e.getSource() == fenconn.getSubmitLocal())
            {   
                try{
                    Connexion Connectionbdd = new Connexion(fenconn.getTextField(1),fenconn.getTextField(2),fenconn.getPassword1());
                    Connection conn = Connectionbdd.getconn();               
                    if(conn == null)
                    {
                        fenconn.getAlerte1().setText("Le nom saisi ne correspond à aucune BDD stockée ou le couple iD/Mdp est faux");
                        fenconn.getAlerte1().setForeground(Color.red);
                    }
                    if(conn != null)
                    {
                        new Accueil_Fen(conn).setVisible(true);
                        fenconn.dispose();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(e.getSource() == fenconn.getSubmitLigne())
            {   
                try {
                    Connexion testconnectOnline = new Connexion(fenconn.getTextField(4),fenconn.getPassword2(),fenconn.getTextField(6),fenconn.getPassword3());
                    Connection conn = testconnectOnline.getconn();
                    if(conn == null)
                    {
                        fenconn.getAlerte2().setForeground(Color.red);
                        fenconn.getAlerte2().setText("Erreur dans la connection à la BDD ");                   
                    }
                    if(conn != null)
                    {
                        new Accueil_Fen(connection).setVisible(true);
                        fenconn.dispose();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        // Pour gérer le tableau principal et les boutons dans l'acceuil
        if(!(this.fenprincipale == null))
        {
            
            if(e.getSource() == fenprincipale.getRadio(0) || e.getSource() == fenprincipale.getRadio(1) || e.getSource() == fenprincipale.getRadio(2) || e.getSource() == fenprincipale.getRadio(3) || e.getSource() == fenprincipale.getRadio(4) || e.getSource() == fenprincipale.getRadio(5) || e.getSource() == fenprincipale.getRadio(6) || e.getSource() == fenprincipale.getRadio(7))
            {   
                
                try {
                    fenprincipale.geterror().setText("");
   
                    String monMot = (String) fenprincipale.getSelectionne();
                    DefaultTableModel dt = new DefaultTableModel();
                    Statement stmt = connection.createStatement();
                    
                    ResultSet Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = '"+monMot+"'");
                    
                    while(Rs.next())
                    {
                        dt.addColumn(Rs.getString("column_name"));
                    }
                    fenprincipale.getTable().setModel(dt);
                    
                    ResultSet Ps = stmt.executeQuery("Select * from "+monMot);
                    
                    if(fenprincipale.getTable().getColumnCount()==1){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0))});}}
                    if(fenprincipale.getTable().getColumnCount()==2){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1))});}}
                    if(fenprincipale.getTable().getColumnCount()==3){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2))});}}
                    if(fenprincipale.getTable().getColumnCount()==4){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3))});}}
                    if(fenprincipale.getTable().getColumnCount()==5){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4))});}}
                    if(fenprincipale.getTable().getColumnCount()==6){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5))});}}
                    fenprincipale.getTable().setModel(dt);
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(e.getSource() == fenprincipale.getComboBoxChoix1())
            {   
                try {
                    fenprincipale.geterror().setText("");
                    fenprincipale.getComboBoxChoix2().removeAllItems();
                    String monMot = (String) fenprincipale.getComboBoxChoix1().getSelectedItem();
                    Statement stmt = connection.createStatement();

                    ResultSet Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = '"+monMot+"'");                     
                    while(Rs.next())
                    {
                        fenprincipale.getComboBoxChoix2().addItem(Rs.getString("column_name"));                    
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(e.getSource() == fenprincipale.getComboBoxChoix11())
            {   
                try {
                    fenprincipale.geterror().setText("");
                    fenprincipale.getComboBoxChoix22().removeAllItems();
                    String monMot = (String) fenprincipale.getComboBoxChoix11().getSelectedItem();
                    Statement stmt = connection.createStatement();

                    ResultSet Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = '"+monMot+"'");                     
                    while(Rs.next())
                    {
                        fenprincipale.getComboBoxChoix22().addItem(Rs.getString("column_name"));                    
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(e.getSource() == fenprincipale.getSupprimer())
            {   
                if(fenprincipale.getTable().getSelectedRow() >= 0)
                {
                    
                int ligne = fenprincipale.getTable().getSelectedRow();
                int tab[] = fenprincipale.getTable().getSelectedRows();
                
                for(int i = 0 ; i<tab.length ;i++)
                {
                    
                
                
                String item = (String) fenprincipale.getSelectionne();
                String item2 = (String) fenprincipale.getTable().getValueAt(tab[i],0);       
                try {           
                    Statement statement = connection.createStatement();
                    Statement stmt = connection.createStatement();
                    String rqt = "";
                    if(item.equals("chambre")){rqt = "DELETE FROM `"+item+"` WHERE "+fenprincipale.getTable().getColumnName(1)+" = '"+(String) fenprincipale.getTable().getValueAt(tab[i],1)+"'";}
                    else{rqt = "DELETE FROM `"+item+"` WHERE "+fenprincipale.getTable().getColumnName(0)+" = '"+item2+"'";}
                    int Rs = statement.executeUpdate(rqt);
                    fenprincipale.setSelectionne(item);
                } catch (SQLException ex) {
                    Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
                
                try {
                    String monMot = (String) fenprincipale.getSelectionne();  
                    DefaultTableModel dt = new DefaultTableModel();
                    Statement stmt = connection.createStatement();
                    
                    ResultSet Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = '"+monMot+"'");
                    
                    while(Rs.next())
                    {
                        dt.addColumn(Rs.getString("column_name"));
                    }
                    fenprincipale.getTable().setModel(dt);
                    
                    ResultSet Ps = stmt.executeQuery("Select * from "+monMot);
                    
                    if(fenprincipale.getTable().getColumnCount()==1){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0))});}}
                    if(fenprincipale.getTable().getColumnCount()==2){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1))});}}
                    if(fenprincipale.getTable().getColumnCount()==3){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2))});}}
                    if(fenprincipale.getTable().getColumnCount()==4){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3))});}}
                    if(fenprincipale.getTable().getColumnCount()==5){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4))});}}
                    if(fenprincipale.getTable().getColumnCount()==6){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5))});}}
                    fenprincipale.getTable().setModel(dt);
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
                }

                }

                
                else
                {
                    fenprincipale.geterror().setText("Veuillez choisir un élément à supprimer!");
                    fenprincipale.geterror().setForeground(Color.red);
                }


            }
            
            if(e.getSource() == fenprincipale.getAjouter())
            {   
                try {
                new Ajouter_Fen(connection).setVisible(true);
                fenprincipale.dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(e.getSource() == fenprincipale.getModifier())
            {
                if(fenprincipale.getTable().getSelectedRow() >= 0)
                {
                    int ligne = fenprincipale.getTable().getSelectedRow();
                    String NomTable = (String) fenprincipale.getSelectionne();
                    String item2 = (String) fenprincipale.getTable().getValueAt(ligne,0);
                    String item3 = (String) fenprincipale.getTable().getColumnName(0);

                    if("chambre".equals(NomTable))
                    {
                        try {
                            new Modifier_Fen(connection,NomTable,(String) fenprincipale.getTable().getValueAt(ligne,0),(String) fenprincipale.getTable().getValueAt(ligne,1),(String) fenprincipale.getTable().getValueAt(ligne,2)).setVisible(true);
                            fenprincipale.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if("hospitalisation".equals(NomTable))
                    {
                        try {
                            new Modifier_Fen(connection,NomTable,(String) fenprincipale.getTable().getValueAt(ligne,0),(String) fenprincipale.getTable().getValueAt(ligne,1),(String) fenprincipale.getTable().getValueAt(ligne,2)).setVisible(true);
                            fenprincipale.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }      

                    if("infirmier".equals(NomTable))
                    {
                        try {
                            new Modifier_Fen(connection,NomTable,(String) fenprincipale.getTable().getValueAt(ligne,0),(String) fenprincipale.getTable().getValueAt(ligne,1),(String) fenprincipale.getTable().getValueAt(ligne,2)).setVisible(true);
                            fenprincipale.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }      

                    if("service".equals(NomTable))
                    {
                        try {
                            new Modifier_Fen(connection,NomTable,(String) fenprincipale.getTable().getValueAt(ligne,0),(String) fenprincipale.getTable().getValueAt(ligne,1),(String) fenprincipale.getTable().getValueAt(ligne,2)).setVisible(true);
                            fenprincipale.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 


                    if("docteur".equals(NomTable))
                    {
                        try {
                            new Modifier_Fen(connection,NomTable,(String) fenprincipale.getTable().getValueAt(ligne,0),(String) fenprincipale.getTable().getValueAt(ligne,1),"").setVisible(true);
                            fenprincipale.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                  

                    if("soigne".equals(NomTable))
                    {
                        try {
                            new Modifier_Fen(connection,NomTable,(String) fenprincipale.getTable().getValueAt(ligne,0),(String) fenprincipale.getTable().getValueAt(ligne,1),"").setVisible(true);
                            fenprincipale.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                  

                    if("malade".equals(NomTable))
                    {
                        try {
                            new Modifier_Fen(connection,NomTable,(String) fenprincipale.getTable().getValueAt(ligne,0),(String) fenprincipale.getTable().getValueAt(ligne,1),(String) fenprincipale.getTable().getValueAt(ligne,2)).setVisible(true);
                            fenprincipale.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                        }        
                    }

                }
                else
                {
                    fenprincipale.geterror().setText("Veuillez choisir un élément à modifier !");
                    fenprincipale.geterror().setForeground(Color.red);
                }
    
            }
            
            if(e.getSource() == fenprincipale.getReporting())
            {   
                new Report_Fen(connection).setVisible(true);
                fenprincipale.dispose();
            }
            
            if(e.getSource() == fenprincipale.getRechercher())
            {  
                try 
                {   fenprincipale.geterror().setText("");                                                    
                    String table = (String) fenprincipale.getComboBoxChoix1().getSelectedItem();
                    String elementdanstable = (String) fenprincipale.getComboBoxChoix2().getSelectedItem();
                    String NomElementRecherche = fenprincipale.getTextfield().getText();
                    fenprincipale.setSelectionne((String) fenprincipale.getComboBoxChoix1().getSelectedItem());
                    DefaultTableModel dt = new DefaultTableModel();
                    Statement stmt = connection.createStatement();

                    ResultSet Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = '"+table+"'");

                    while(Rs.next())
                    {
                        dt.addColumn(Rs.getString("column_name"));                    
                    }                           
                    fenprincipale.getTable().setModel(dt);

                    ResultSet Ps = stmt.executeQuery("Select * from "+table+" where "+elementdanstable+" = '"+NomElementRecherche+"'");

                    if(fenprincipale.getTable().getColumnCount()==1){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0))});}}
                    if(fenprincipale.getTable().getColumnCount()==2){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1))});}}
                    if(fenprincipale.getTable().getColumnCount()==3){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2))});}}
                    if(fenprincipale.getTable().getColumnCount()==4){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3))});}}
                    if(fenprincipale.getTable().getColumnCount()==5){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4))});}}
                    if(fenprincipale.getTable().getColumnCount()==6){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5))});}}                 
                    fenprincipale.getTable().setModel(dt);                      
                } 
                    catch (SQLException ex)
                {
                    Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
            
            if(e.getSource() == fenprincipale.getrechercher2())
            {  
                try 
                {   
                    fenprincipale.geterror().setText("");
                    
                    String table = (String) fenprincipale.getComboBoxChoix().getSelectedItem();
                    String elementdanstable = (String) fenprincipale.getComboBoxChoix2().getSelectedItem();
                    String NomElementRecherche = fenprincipale.getTextfield().getText();
                    String table1 = (String) fenprincipale.getComboBoxChoix11().getSelectedItem();
                    String elementdanstable2 = (String) fenprincipale.getComboBoxChoix22().getSelectedItem();
                    String NomElementRecherche2 = fenprincipale.getTextfield2().getText();
                    fenprincipale.setSelectionne((String) fenprincipale.getComboBoxChoix1().getSelectedItem());
                    DefaultTableModel dt = new DefaultTableModel();
                    DefaultTableModel dt1 = new DefaultTableModel();
                    Statement stmt = connection.createStatement();

                    
                    ResultSet Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = '"+table+"'");
                    while(Rs.next())
                    {
                        dt.addColumn(Rs.getString("column_name"));                    
                    }  
                    String id1 = (String) dt.getColumnName(0);
                    Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = '"+table1+"'");
                    while(Rs.next())
                    {
                        dt.addColumn(Rs.getString("column_name"));  
                        dt1.addColumn(Rs.getString("column_name"));  
                    }
                    String id2 = (String) (String) dt1.getColumnName(0);
                    
                    if(table.equals("chambre") && !table1.equals("service"))
                    {
                        id1 = "no_chambre";
                    }
                    if(table.equals("chambre") && !table1.equals("service"))
                    {
                        id2 = "no_chambre";
                    }
                    fenprincipale.getTable().setModel(dt);
                    String b = "SELECT * FROM "+table+" INNER JOIN "+table1+" ON "+table+"."+id1+" = "+table1+"."+id2+" WHERE "+table1+"."+elementdanstable2+" = '"+NomElementRecherche2+"'";

                    ResultSet Ps = stmt.executeQuery(b);
                    if(fenprincipale.getTable().getColumnCount()==1){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0))});}}
                    if(fenprincipale.getTable().getColumnCount()==2){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1))});}}
                    if(fenprincipale.getTable().getColumnCount()==3){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2))});}}
                    if(fenprincipale.getTable().getColumnCount()==4){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3))});}}
                    if(fenprincipale.getTable().getColumnCount()==5){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4))});}}
                    if(fenprincipale.getTable().getColumnCount()==7){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5))});}}                 
                    if(fenprincipale.getTable().getColumnCount()==8){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5)),Ps.getString(fenprincipale.getTable().getColumnName(6))});}}                 
                    if(fenprincipale.getTable().getColumnCount()==9){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5)),Ps.getString(fenprincipale.getTable().getColumnName(6)),Ps.getString(fenprincipale.getTable().getColumnName(7))});}}                 
                    if(fenprincipale.getTable().getColumnCount()==10){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5)),Ps.getString(fenprincipale.getTable().getColumnName(6)),Ps.getString(fenprincipale.getTable().getColumnName(7)),Ps.getString(fenprincipale.getTable().getColumnName(8))});}}                 
                    if(fenprincipale.getTable().getColumnCount()==10){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5)),Ps.getString(fenprincipale.getTable().getColumnName(6)),Ps.getString(fenprincipale.getTable().getColumnName(7)),Ps.getString(fenprincipale.getTable().getColumnName(8)),Ps.getString(fenprincipale.getTable().getColumnName(9))});}}                 
                    if(fenprincipale.getTable().getColumnCount()==10){while(Ps.next()){dt.addRow(new Object[]{Ps.getString(fenprincipale.getTable().getColumnName(0)),Ps.getString(fenprincipale.getTable().getColumnName(1)),Ps.getString(fenprincipale.getTable().getColumnName(2)),Ps.getString(fenprincipale.getTable().getColumnName(3)),Ps.getString(fenprincipale.getTable().getColumnName(4)),Ps.getString(fenprincipale.getTable().getColumnName(5)),Ps.getString(fenprincipale.getTable().getColumnName(6)),Ps.getString(fenprincipale.getTable().getColumnName(7)),Ps.getString(fenprincipale.getTable().getColumnName(8)),Ps.getString(fenprincipale.getTable().getColumnName(9)),Ps.getString(fenprincipale.getTable().getColumnName(10))});}}                 

                    fenprincipale.getTable().setModel(dt);  
                    if(fenprincipale.getTable().getRowCount() == 0)
                    {
                        fenprincipale.geterror().setText("Erreur dans votre choix !");
                        fenprincipale.geterror().setForeground(Color.red);
                    }
                } 
                    catch (SQLException ex)
                {
                    Logger.getLogger(Accueil_Fen.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }
    }
}
