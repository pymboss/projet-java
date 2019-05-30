/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ButtonController;
import controleur.TabController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MenuComponent;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Classe d'interface graphique pour permetter de se connecter à la base de données 
 * en ligne ou localement
 * @author basileroth
 */
public class Ajouter_Fen extends JFrame{

    private static JTabbedPane tabbed_pan;
    private JPanel chambre;
    private JPanel docteur;
    private JPanel hospitalisation;
    private JPanel infirmier;
    private JPanel malade;
    private JPanel service;
    private JPanel soigne;
    private Connection connection;
    private JComboBox jComboBoxSoigne = new JComboBox();
    private JComboBox jComboBoxChambre1 = new JComboBox();
    private JComboBox jComboBoxDocteur = new JComboBox();
    private JComboBox jComboBoxHospi1 = new JComboBox();
    private JComboBox jComboBoxMalade = new JComboBox();
    private JButton submit[];
    private JTextField jTextField[];    
    private JLabel jLabel[];
    private JButton retour[];
    private JPanel bas1;
    private JPanel bas2;
    private JPanel bas3;
    private JPanel bas4;
    private JPanel bas5;
    private JPanel bas6;
    private JPanel bas7;

    /**
     * Constructeur avec la connection
     * @param conn
     * @throws SQLException
     */
    public Ajouter_Fen(Connection conn) throws SQLException {
        
        super("Ajouter");
        this.connection = conn;
        this.jTextField = new JTextField[35];
        this.jLabel = new JLabel[35];
        this.submit = new JButton[8];
        this.setLayout(new BorderLayout());
        
        this.retour = new JButton[10];
        
        for(int i = 0 ; i < 8 ; i++)
        {
            this.retour[i] = new JButton("");
            retour[i].setIcon(new ImageIcon("images/home.png")); 
            retour[i].setVerticalTextPosition(SwingConstants.BOTTOM); 
            retour[i].setHorizontalTextPosition(SwingConstants.CENTER);
        }

        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour une CHAMBRE                  */
        /////////////////////////////////////////////////////////////////////
        chambre = new JPanel(new GridLayout(0, 1, -5, 5));
        jLabel[0] = new JLabel("Code Service");
        Statement stmt = conn.createStatement();
        ResultSet Rs = stmt.executeQuery("SELECT * FROM service");                     
        while(Rs.next())
        {
            jComboBoxChambre1.addItem(Rs.getString("code"));                    
        }        
        jLabel[1] = new JLabel("Numéro Chambre");
        jTextField[1] = new JTextField("", 30);
        jLabel[2] = new JLabel("Surveillant");
        jTextField[2] = new JTextField("", 30);
        jLabel[3] = new JLabel("Nombre lits");
        jTextField[3] = new JTextField("", 30);
        submit[0] = new JButton("");
        submit[0].setIcon(new ImageIcon("images/validate.png")); 
        submit[0].setVerticalTextPosition(SwingConstants.BOTTOM); 
        submit[0].setHorizontalTextPosition(SwingConstants.CENTER);
        
        chambre.add(jLabel[0]);
        chambre.add(jComboBoxChambre1);
        for (int i = 1 ; i<4;i++)
        {
            chambre.add(jLabel[i]);
            chambre.add(jTextField[i]);
        }
        //chambre.add(submit[0]);
        bas1 = new JPanel(new GridLayout(1,2));            
        bas1.add(submit[0]);
        bas1.add(retour[0]);
        chambre.add(bas1);

        
        
        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un DOCTEUR                   */
        /////////////////////////////////////////////////////////////////////
        docteur = new JPanel(new GridLayout(0, 1, -2, 2));
        jLabel[4] = new JLabel("Numero");
        jTextField[4] = new JTextField("", 30);
        jLabel[5] = new JLabel("Nom");
        jTextField[5] = new JTextField("", 30);
        jLabel[6] = new JLabel("Prenom");
        jTextField[6] = new JTextField("", 30);
        jLabel[7] = new JLabel("Adresse");
        jTextField[7] = new JTextField("", 30);
        jLabel[8] = new JLabel("Telephone");
        jTextField[8] = new JTextField("", 30);        
        jLabel[9] = new JLabel("Specialite");
        jComboBoxDocteur.addItem("Anesthesiste");
        jComboBoxDocteur.addItem("Cardiologue");
        jComboBoxDocteur.addItem("Generaliste");
        jComboBoxDocteur.addItem("Orthopediste");
        jComboBoxDocteur.addItem("Pneumologue");
        jComboBoxDocteur.addItem("Radiologue");
        jComboBoxDocteur.addItem("Traumatologue"); 
        for (int i = 4 ; i<9;i++)
        {
            docteur.add(jLabel[i]);
            docteur.add(jTextField[i]);
        }
        docteur.add(jLabel[9]);        
        submit[1] = new JButton("");
        submit[1].setIcon(new ImageIcon("images/validate.png")); 
        submit[1].setVerticalTextPosition(SwingConstants.BOTTOM); 
        submit[1].setHorizontalTextPosition(SwingConstants.CENTER);
        docteur.add(jComboBoxDocteur);
        //docteur.add(submit[1]); 
        bas2 = new JPanel(new GridLayout(1,2));            
        bas2.add(submit[1]);
        bas2.add(retour[1]);
        docteur.add(bas2);
        
        
        
        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un EMPLOYE                   */
        /////////////////////////////////////////////////////////////////////
       /* employe = new JPanel(new GridLayout(0, 1, -5, 5));
        jLabel[6] = new JLabel("Numero");
        jTextField[6] = new JTextField("", 30);
        jLabel[7] = new JLabel("Nom");
        jTextField[7] = new JTextField("", 30);
        jLabel[8] = new JLabel("Prenom");
        jTextField[8] = new JTextField("", 30);
        jLabel[9] = new JLabel("Adresse");
        jTextField[9] = new JTextField("", 30);
        jLabel[10] = new JLabel("Telephone");
        jTextField[10] = new JTextField("", 30);        
        submit[2] = new JButton("Valider employe");
        for (int i = 6 ; i<11;i++)
        {
            employe.add(jLabel[i]);
            employe.add(jTextField[i]);
        }
        employe.add(submit[2]);
        */               
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour une HOSPITALISATION          */
        /////////////////////////////////////////////////////////////////////
        hospitalisation = new JPanel(new GridLayout(0, 1, -5, 5));
        jLabel[10] = new JLabel("Numero malade");
        jTextField[10] = new JTextField("", 30);
        jLabel[11] = new JLabel("Numero Chambre");
        Rs = stmt.executeQuery("SELECT * FROM service");                     
        while(Rs.next())
        {
            jComboBoxHospi1.addItem(Rs.getString("code"));                    
        }                   
        jLabel[12] = new JLabel("Numéro Chambre");
        jTextField[12] = new JTextField("", 30);  
        jLabel[13] = new JLabel("Lit");
        jTextField[13] = new JTextField("", 30);      
        submit[2] = new JButton("");
        submit[2].setIcon(new ImageIcon("images/validate.png")); 
        submit[2].setVerticalTextPosition(SwingConstants.BOTTOM); 
        submit[2].setHorizontalTextPosition(SwingConstants.CENTER);
        hospitalisation.add(jLabel[10]);
        hospitalisation.add(jTextField[10]);
        hospitalisation.add(jLabel[11]);
        hospitalisation.add(jComboBoxHospi1);
        hospitalisation.add(jLabel[12]);
        hospitalisation.add(jTextField[12]);      
        hospitalisation.add(jLabel[13]);
        hospitalisation.add(jTextField[13]);
        //hospitalisation.add(submit[2]);
        bas3 = new JPanel(new GridLayout(1,2));            
        bas3.add(submit[2]);
        bas3.add(retour[2]);
        hospitalisation.add(bas3);
        
         
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un INFIRMIER                 */
        /////////////////////////////////////////////////////////////////////
        infirmier = new JPanel(new GridLayout(0, 1, -5, 5));
        jLabel[14] = new JLabel("Numero");
        jTextField[14] = new JTextField("", 30);
        jLabel[15] = new JLabel("Nom");
        jTextField[15] = new JTextField("", 30);
        jLabel[16] = new JLabel("Prenom");
        jTextField[16] = new JTextField("", 30);
        jLabel[17] = new JLabel("Adresse");
        jTextField[17] = new JTextField("", 30);
        jLabel[18] = new JLabel("Telephone");
        jTextField[18] = new JTextField("", 30);
        jLabel[19] = new JLabel("Code Service");
        jTextField[19] = new JTextField("", 30);
        jLabel[20] = new JLabel("Rotation");
        jTextField[20] = new JTextField("", 30);
        jLabel[21] = new JLabel("Salaire");
        jTextField[21] = new JTextField("", 30);      
        submit[3] = new JButton("");
        submit[3].setIcon(new ImageIcon("images/validate.png")); 
        submit[3].setVerticalTextPosition(SwingConstants.BOTTOM); 
        submit[3].setHorizontalTextPosition(SwingConstants.CENTER);
        for (int i = 14 ; i<22;i++)
        {
            infirmier.add(jLabel[i]);
            infirmier.add(jTextField[i]);
        }
        //infirmier.add(submit[3]);
        bas4 = new JPanel(new GridLayout(1,2));            
        bas4.add(submit[3]);
        bas4.add(retour[3]);
        infirmier.add(bas4);
        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un MALADE                    */
        /////////////////////////////////////////////////////////////////////
        malade = new JPanel(new GridLayout(0, 1, -5, 5));
        jLabel[23] = new JLabel("Numero");
        jTextField[23] = new JTextField("", 30);
        jLabel[24] = new JLabel("Nom");
        jTextField[24] = new JTextField("", 30);
        jLabel[25] = new JLabel("Prenom");
        jTextField[25] = new JTextField("", 30);
        jLabel[26] = new JLabel("Adresse");
        jTextField[26] = new JTextField("", 30); 
        jLabel[27] = new JLabel("Telephone");
        jTextField[27] = new JTextField("", 30);
        jLabel[28] = new JLabel("Mutuelle");
        jTextField[28] = new JTextField("", 30);
        submit[4] = new JButton("");
        submit[4].setIcon(new ImageIcon("images/validate.png")); 
        submit[4].setVerticalTextPosition(SwingConstants.BOTTOM); 
        submit[4].setHorizontalTextPosition(SwingConstants.CENTER);
        for (int i = 23 ; i<29;i++)
        {
            malade.add(jLabel[i]);
            malade.add(jTextField[i]);
        }
        //malade.add(submit[4]);
        bas5 = new JPanel(new GridLayout(1,2));            
        bas5.add(submit[4]);
        bas5.add(retour[4]);
        malade.add(bas5);
        
        
        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un SERVICE                   */
        /////////////////////////////////////////////////////////////////////
        service = new JPanel(new GridLayout(0, 1, -5, 5));
        jLabel[29] = new JLabel("Code");
        jTextField[29] = new JTextField("", 30);
        jLabel[30] = new JLabel("Nom");
        jTextField[30] = new JTextField("", 30);
        jLabel[31] = new JLabel("Batiment");
        jTextField[31] = new JTextField("", 30);
        jLabel[32] = new JLabel("Directeur");
        jTextField[32] = new JTextField("", 30);      
        submit[5] = new JButton("");
        submit[5].setIcon(new ImageIcon("images/validate.png")); 
        submit[5].setVerticalTextPosition(SwingConstants.BOTTOM); 
        submit[5].setHorizontalTextPosition(SwingConstants.CENTER);
        for (int i = 29 ; i<33;i++)
        {
            service.add(jLabel[i]);
            service.add(jTextField[i]);
        }
        //service.add(submit[5]);
        bas6 = new JPanel(new GridLayout(1,2));            
        bas6.add(submit[5]);
        bas6.add(retour[5]);
        service.add(bas6);
        
        
        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un SOIGNE                    */
        /////////////////////////////////////////////////////////////////////
        soigne = new JPanel(new GridLayout(0, 1, -5, 5));
        jLabel[33] = new JLabel("Numero docteur");        
        JComboBox<Object> jComboBoxSoigne = new JComboBox<>();
        stmt = conn.createStatement();
        Rs = stmt.executeQuery("SELECT DISTINCT no_docteur FROM soigne");                     
        while(Rs.next())
        {
            jComboBoxSoigne.addItem(Rs.getString("no_docteur"));                    
        }        
        jLabel[34] = new JLabel("Numero Malade");
        jTextField[34] = new JTextField("", 30);
        submit[6] = new JButton("");
        submit[6].setIcon(new ImageIcon("images/validate.png")); 
        submit[6].setVerticalTextPosition(SwingConstants.BOTTOM); 
        submit[6].setHorizontalTextPosition(SwingConstants.CENTER);
        soigne.add(jLabel[33]);
        soigne.add(jComboBoxSoigne);
        soigne.add(jLabel[34]);
        soigne.add(jTextField[34]);
        //soigne.add(submit[6]);
        bas7 = new JPanel(new GridLayout(1,2));            
        bas7.add(submit[6]);
        bas7.add(retour[6]);
        soigne.add(bas7);

        
        //-- Ajout des éléments dans la fenêtre --//
        tabbed_pan = new JTabbedPane();        
        tabbed_pan.addTab("Docteur", docteur);
        tabbed_pan.addTab("Chambre", chambre);
        tabbed_pan.addTab("Hospitalisation", hospitalisation); 
        tabbed_pan.addTab("Infirmier", infirmier); 
        tabbed_pan.addTab("Malade", malade); 
        tabbed_pan.addTab("Service", service); 
        tabbed_pan.addTab("Soigne", soigne); 
        this.add(tabbed_pan, BorderLayout.CENTER);

        for(int i = 0 ; i < 8 ; i++)
        {
            retour[i].addActionListener(new ButtonController(this,connection));
        }
        
      
        for( int i = 0 ; i < 7 ; i ++)
        {
            submit[i].addActionListener(new ButtonController(this,connection)); 
        }

        tabbed_pan.addChangeListener(new TabController(this));
        
        // Pour une chambre
        jTextField[1].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[1].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        jTextField[2].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[2].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});

        
        // Pour un docteur
        jTextField[4].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[4].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
      
               
        // Pour une hospitalisation
        jTextField[10].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[10].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        jTextField[12].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,(String) jComboBoxHospi1.getSelectedItem());}});
        jTextField[12].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
             
              
        // Pour un infirmier
        jTextField[14].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[14].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        jTextField[19].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[19].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});

        // Pour un malade
        jTextField[23].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[23].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        
        // Pour un service
        jTextField[32].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[32].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});

        // Pour un soigne
        jTextField[34].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,(String) jComboBoxSoigne.getSelectedItem());}});
        jTextField[34].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        
        this.pack();       
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

    }

    /**
     * @return le submit
     */
    public JButton getSubmit(int i) {
        return submit[i];
    }


    /**
     * @return le jTextField
     */
    public JTextField getJTexField(int i) {
        return jTextField[i];
    }

    /**
     * @return le jLabel
     */
    public JLabel getJLabel(int i) {
        return jLabel[i];
    }
    
    /**
     *
     * @return tabbed_pan
     */
    public JTabbedPane getTabbed_pan() {
        return tabbed_pan;
    }

    /**
     *
     * @return chambre
     */
    public JPanel getChambre() 
    {
       return chambre;
    }

    /**
     *
     * @return docteur
     */
    public JPanel getDocteur() 
    {
       return docteur;
    }
    
    /**
     *
     * @return hospitalisation
     */
    public JPanel getHospitalisation() 
    {
       return hospitalisation;
    }
    
    /**
     *
     * @return infirmier
     */
    public JPanel getInfirmier() 
    {
       return infirmier;
    }
    
    public JPanel getMalade() 
    {
       return malade;
    }
    
    public JPanel getService() 
    {
       return service;
    }
    
    public JPanel getSoigne() 
    {
       return soigne;
    }
    
    public Connection getConnection() 
    {
       return connection;
    } 
    
    public JComboBox getBoxSoigne() 
    {
       return jComboBoxSoigne;
    }  
    
    public String getSelected() 
    {
       return (String) jComboBoxChambre1.getSelectedItem();
    } 
    
    
    /**
     * Méthode qui va permettre de gérer les interractions de 
     * la souris avec les différents cases quand on quitte une case 
     * POUR LE BLINDAGE
     */
    private void jTextFieldMouseExited(MouseEvent evt, String var) 
    {    
        // Pour une chambre on va vérifier si le numéro rentré n'est pas utilisé
        if(evt.getSource() == jTextField[1])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {      
                    // Requête pour vérifier si le numéro existe déjà dans la table
                    ResultSet Rs = stmt.executeQuery("SELECT * FROM `chambre` WHERE `no_chambre` = '"+jTextField[1].getText()+"'");           
                    if (Rs.next()) 
                    {
                        // Message d'erreur si il existe déjà
                        String nouv = jTextField[1].getText()+" -> ATTENTION CETTE CHAMBRE EXISTE DÉJÀ !";
                        jTextField[1].setText(nouv);
                        jTextField[1].setForeground(Color.red);
                    }              
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pour une chambre on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà
        if(evt.getSource() == jTextField[2])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {      
                   if(!jTextField[2].getText().equals(""))
                   {
                       // Requête pour vérifier si le numéro existe déjà dans la table
                       ResultSet Rs = stmt.executeQuery("SELECT * FROM `infirmier` WHERE `numero` = '"+jTextField[2].getText()+"'");           
                       if (!Rs.next()) 
                       {
                           // Message d'erreur si il existe déjà
                           String nouv = jTextField[2].getText()+" -> CET EMPLOYÉ N'EXISTE PAS OU NE PEUT ÊTRE SURVEILLANT !";
                           jTextField[2].setText(nouv);
                           jTextField[2].setForeground(Color.red);
                       }   
                   }             
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pour un docteur on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà
        if(evt.getSource() == jTextField[4])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {   
                    if(!jTextField[4].getText().equals(""))
                    {   
                        if(!jTextField[4].getText().equals(""))
                        {
                            // Requête pour vérifier si le numéro existe déjà dans la table
                            ResultSet Rs = stmt.executeQuery("SELECT * FROM `employe` WHERE `numero` = '"+jTextField[4].getText()+"'");           
                            if (Rs.next()) 
                            {
                                // Message d'erreur si il existe déjà
                                String nouv = jTextField[4].getText()+" -> ATTENTION CE NUMÉRO EST DÉJÀ UTILISÉ !";
                                jTextField[4].setText(nouv);
                                jTextField[4].setForeground(Color.red);
                            }
                        }
                        
                    }    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pour une hospitalisation on va vérifier si le numero malade n'existe pas
        if(evt.getSource() == jTextField[10])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {      
                    if(!jTextField[10].getText().equals(""))
                    {
                        // Requête pour vérifier si le numéro existe dans la table
                        ResultSet Rs = stmt.executeQuery("SELECT * FROM `malade` WHERE `numero` = '"+jTextField[10].getText()+"'");           
                        if (!Rs.next()) 
                        {
                            // Message d'erreur si il existe déjà
                            String nouv = jTextField[10].getText()+" -> CE MALADE N'EXISTE PAS !";
                            jTextField[10].setText(nouv);
                            jTextField[10].setForeground(Color.red);
                        }
                        else
                        {                     
                            // Requête pour vérifier si le numéro existe déjà dans la table
                            Rs = stmt.executeQuery("SELECT * FROM `hospitalisation` WHERE `no_malade` = '"+jTextField[10].getText()+"'");           
                            if (Rs.next()) 
                            {
                                // Message d'erreur si il existe déjà
                                String nouv = jTextField[10].getText()+" -> CE MALADE EST DÉJÀ HOSPITALISÉ !";
                                jTextField[10].setText(nouv);
                                jTextField[10].setForeground(Color.red);
                            }       
                        } 
                    }                 
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pour hospitalisation on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà
        if(evt.getSource() == jTextField[12])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {   
                    if(!jTextField[12].getText().equals(""))
                    {   
                        ResultSet Rs = stmt.executeQuery("SELECT * FROM `chambre` WHERE `no_chambre` = '"+jTextField[12].getText()+"'");           
                        if (!Rs.next()) 
                        {
                            // Message d'erreur si il existe déjà
                            String nouv = jTextField[12].getText()+" -> CETTE CHAMBRE N'EXISTE PAS !";
                            jTextField[12].setText(nouv);
                            jTextField[12].setForeground(Color.red);
                        }
                        else
                        {
                            // Requête pour vérifier si le numéro existe déjà dans la table
                            Rs = stmt.executeQuery("SELECT * FROM `chambre` WHERE `code_service` = '"+var+"' AND `no_chambre` = '"+jTextField[12].getText()+"'");           
                            if (!Rs.next()) 
                            {
                                // Message d'erreur si il existe déjà
                                String nouv = jTextField[12].getText()+" -> CETTE CHAMBRE N'APPARTIENT PAS À CE SERVICE !";
                                jTextField[12].setText(nouv);
                                jTextField[12].setForeground(Color.red);
                            }
                        }                    
                    }    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pour un infirmier on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà        
        if(evt.getSource() == jTextField[14])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {   
                    if(!jTextField[14].getText().equals(""))
                    {   
                            // Requête pour vérifier si le numéro existe déjà dans la table
                            ResultSet Rs = stmt.executeQuery("SELECT * FROM `employe` WHERE `numero` = '"+jTextField[14].getText()+"'");           
                            if (Rs.next()) 
                            {
                                // Message d'erreur si il existe déjà
                                String nouv = jTextField[14].getText()+" -> ATTENTION CE NUMÉRO EST DÉJÀ UTILISÉ !";
                                jTextField[14].setText(nouv);
                                jTextField[14].setForeground(Color.red);
                            }  
                    }    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pour un infirmier on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà        
        if(evt.getSource() == jTextField[19])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {   
                    if(!jTextField[19].getText().equals(""))
                    {   
                            // Requête pour vérifier si le numéro existe déjà dans la table
                            if (!jTextField[19].getText().equals("CAR") && !jTextField[19].getText().equals("CHG") && !jTextField[19].getText().equals("REA")) 
                            {
                                // Message d'erreur si il existe déjà
                                String nouv = jTextField[19].getText()+" -> N'EXISTE PAS !";
                                jTextField[19].setText(nouv);
                                jTextField[19].setForeground(Color.red);
                            }                       
                    }    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pour un malade on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà        
        if(evt.getSource() == jTextField[23])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {   
                    if(!jTextField[23].getText().equals(""))
                    {   
                            // Requête pour vérifier si le numéro existe déjà dans la table
                            ResultSet Rs = stmt.executeQuery("SELECT * FROM `malade` WHERE `numero` = '"+jTextField[23].getText()+"'");           
                            if (Rs.next()) 
                            {
                                // Message d'erreur si il existe déjà
                                String nouv = jTextField[23].getText()+" -> ATTENTION CE NUMÉRO EST DÉJÀ UTILISÉ !";
                                jTextField[23].setText(nouv);
                                jTextField[23].setForeground(Color.red);
                            }                       
                    }    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pour un service on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà        
        if(evt.getSource() == jTextField[32])
        {
            try 
            {
                Statement stmt = connection.createStatement();       
                {   
                    if(!jTextField[32].getText().equals(""))
                    {   
                            // Requête pour vérifier si le numéro existe déjà dans la table
                            ResultSet Rs = stmt.executeQuery("SELECT * FROM `malade` WHERE `numero` = '"+jTextField[32].getText()+"'");           
                            if (!Rs.next()) 
                            {
                                // Message d'erreur si il existe déjà
                                String nouv = jTextField[32].getText()+" -> Cet employé n'existe pas, vous ne pouvez pas le promouvoir directeur !";
                                jTextField[32].setText(nouv);
                                jTextField[32].setForeground(Color.red);
                            }                       
                    }    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        // Pour un soigné on va vérifier si le couple no_malade/no_docteur rentré ne correspond pas à un couple existant déjà
        if(evt.getSource() == jTextField[34])
        {                        
            try 
            {
                Statement stmt = connection.createStatement();
                    if(!jTextField[34].getText().equals(""))
                    {
                        String b = "SELECT * FROM `malade` WHERE numero = '"+jTextField[34].getText()+"'";           
                        ResultSet Ts = stmt.executeQuery(b);
                        if (!Ts.next())  
                        {
                            // Message d'erreur si il n'existe pas
                            String nouv = jTextField[34].getText()+" -> CE MALADE N'EXISTE PAS !";
                            jTextField[34].setText(nouv);
                            jTextField[34].setForeground(Color.red);
                        }                
                        String a = "SELECT * FROM `soigne` WHERE no_malade = '"+jTextField[34].getText()+"' AND no_docteur = '"+var+"'";           
                        ResultSet Rs = stmt.executeQuery(a);
                        if (Rs.next()) 
                        {
                            // Message d'erreur si il existe déjà
                            String nouv = jTextField[34].getText()+" -> ATTENTION CE DOCTEUR SOIGNE DÉJÀ CE PATIENT !";
                            jTextField[34].setText(nouv);
                            jTextField[34].setForeground(Color.red);
                        }
                    }    
            } catch (SQLException ex) {
                Logger.getLogger(Ajouter_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    /**
     * Méthode qui va permettre de gérer les interractions de 
     * la souris avec les différents cases quand on clique sur une case 
     * POUR LE BLINDAGE
     */
    private void jTextFieldMouseClicked(MouseEvent evt) 
    {   
        // Pour chambre on réinitialise les cases 
        if(evt.getSource() == jTextField[1])
        {
            jTextField[1].setText("");
            jTextField[1].setForeground(Color.black);
        }        
        if(evt.getSource() == jTextField[2])
        {
            jTextField[2].setText("");
            jTextField[2].setForeground(Color.black);
        }
        
        // Pour docteur on réinitialise les cases 
        if(evt.getSource() == jTextField[4])
        {
            jTextField[4].setText("");
            jTextField[4].setForeground(Color.black);
        }

        // Pour hospitalisation on réinitialise la case identifiant avec le message d'erreur
        if(evt.getSource() == jTextField[10])
        {
            jTextField[10].setText("");
            jTextField[10].setForeground(Color.black);
        } 
        
        // Pour hospitalisation on réinitialise la case identifiant avec le message d'erreur
        if(evt.getSource() == jTextField[12])
        {
            jTextField[12].setText("");
            jTextField[12].setForeground(Color.black);
        } 
        
        // Pour infirmier on réinitialise la case identifiant avec le message d'erreur
        if(evt.getSource() == jTextField[14])
        {
            jTextField[14].setText("");
            jTextField[14].setForeground(Color.black);
        } 
       
        // Pour infirmier on réinitialise la case identifiant avec le message d'erreur
        if(evt.getSource() == jTextField[19])
        {
            jTextField[19].setText("");
            jTextField[19].setForeground(Color.black);
        } 
        
        // Pour infirmier on réinitialise la case identifiant avec le message d'erreur
        if(evt.getSource() == jTextField[23])
        {
            jTextField[23].setText("");
            jTextField[23].setForeground(Color.black);
        } 
        
        // Pour service on réinitialise la case identifiant avec le message d'erreur
        if(evt.getSource() == jTextField[32])
        {
            jTextField[32].setText("");
            jTextField[32].setForeground(Color.black);
        }
                
        // Pour soigne on réinitialise la case no_malade avec le message d'erreur
        if(evt.getSource() == jTextField[34])
        {            
            jTextField[34].setText("");
            jTextField[34].setForeground(Color.black);
        }

    }

    public JComboBox getComboBoxHospi() 
    {
        return jComboBoxHospi1;
    }

    public JComboBox getComboBoxChambre() 
    {
         return jComboBoxChambre1;
    }
    
    public JComboBox getComboBoxDocteur() 
    {
         return jComboBoxDocteur;
    }
    
    public JButton getretour(int i)
    {
        return retour[i];
    }
    
    public JTextField getfield(int i)
    {
        return jTextField[i];
    }
    
    public JButton getsubmit(int i)
    {
        return submit[i];
    }
    
    public JPanel getbas1()
    {
        return bas1;
    }
    
    public JPanel getbas2()
    {
        return bas2;
    }

    public JPanel getbas3()
    {
        return bas3;
    }

    public JPanel getbas4()
    {
        return bas4;
    }

    public JPanel getbas5()
    {
        return bas5;
    }

    public JPanel getbas6()
    {
        return bas6;
    }

    public JPanel getbas7()
    {
        return bas7;
    }


}
