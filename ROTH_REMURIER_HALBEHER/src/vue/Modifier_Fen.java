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
 * Classe d'interface graphique pour gérer la modification en fonction de l'élément choisi dans la bdd
 * à modifier
 * @author basileroth
 */
public class Modifier_Fen extends JFrame{

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
    private JComboBox jComboBoxChambre2 = new JComboBox();
    private JComboBox jComboBoxDocteur = new JComboBox();
    private JComboBox jComboBoxHospi1 = new JComboBox();
     private JComboBox jComboBoxHospi2 = new JComboBox();
    private JComboBox jComboBoxMalade = new JComboBox();
    private JButton submit[];
    private JTextField jTextField[];    
    private JLabel jLabel[];
    private JButton retour = new JButton("");
    private String item1;
    private String item2;
    private String item3; 
    private String pNomTable;
    private String notrecas;
    private String notrecas1;
    private String notrecas2;
    private String notrecas3;
    private String notrecas4;
    private String notrecas5;
    private String notrecas6;
    private String notrecas7;
  
    /**
     * Constructeur pour créer la classe avec en paramètres les éléments des items 
     * à sélectionner venant de la table 
     * @param conn
     * @param pNomTable
     * @param item1
     * @param item2
     * @param item3
     * @throws SQLException
     */
    public Modifier_Fen(Connection conn, String pNomTable, String item1, String item2, String item3) throws SQLException {
        
        super("Modifier");
        this.connection = conn;
        this.jTextField = new JTextField[35];
        this.jLabel = new JLabel[35];
        this.submit = new JButton[7];
        this.setLayout(new BorderLayout());
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.pNomTable = pNomTable;
        retour.setIcon(new ImageIcon("images/home.png")); 
        retour.setVerticalTextPosition(SwingConstants.BOTTOM); 
        retour.setHorizontalTextPosition(SwingConstants.CENTER);


        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour une CHAMBRE                  */
        /////////////////////////////////////////////////////////////////////
        if(pNomTable.equals("chambre"))
        {
            chambre = new JPanel(new GridLayout(0, 1, -5, 5));
            jLabel[0] = new JLabel("Code Service");
            Statement stmt = conn.createStatement();
            ResultSet Rs = stmt.executeQuery("SELECT * FROM service");                     
            while(Rs.next())
            {
                jComboBoxChambre1.addItem(Rs.getString("code"));                    
            }
            Rs = stmt.executeQuery("SELECT numero FROM infirmier");                     
            while(Rs.next())
            {
                jComboBoxChambre2.addItem(Rs.getString("numero"));                    
            }
            jLabel[1] = new JLabel("Numero Chambre");
            jTextField[1] = new JTextField("", 30);
            jLabel[2] = new JLabel("Surveillant");
            jTextField[2] = new JTextField("", 30);
            jLabel[3] = new JLabel("Nombre de lits");
            jTextField[3] = new JTextField("", 30);
            ResultSet Ps = stmt.executeQuery("SELECT * FROM `"+pNomTable+"` WHERE no_chambre = '"+item2+"' AND surveillant = '"+item3+"'");  
            while(Ps.next())
            {
                jComboBoxChambre1.setSelectedItem(Ps.getString("code_service"));
                notrecas = Ps.getString("no_chambre");
                jTextField[1].setText(Ps.getString("no_chambre"));
                jComboBoxChambre2.setSelectedItem(Ps.getString("surveillant"));
                jTextField[3].setText(Ps.getString("nb_lits"));
            }             
            submit[0] = new JButton("");
            submit[0].setIcon(new ImageIcon("images/validate.png")); 
            submit[0].setVerticalTextPosition(SwingConstants.BOTTOM); 
            submit[0].setHorizontalTextPosition(SwingConstants.CENTER);
            
            chambre.add(jLabel[0]);
            chambre.add(jComboBoxChambre1);
            chambre.add(jLabel[1]);
            chambre.add(jTextField[1]);
            chambre.add(jLabel[2]);
            chambre.add(jComboBoxChambre2);
            for (int i = 3 ; i<4;i++)
            {
                chambre.add(jLabel[i]);
                chambre.add(jTextField[i]);
            }
            JPanel bas1 = new JPanel(new GridLayout(1,2));            
            bas1.add(submit[0]);
            bas1.add(retour);
            chambre.add(bas1);
        

        // Pour une chambre
        jTextField[1].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[1].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});          
        submit[0].addActionListener(new ButtonController(this,connection));                    
        }
                        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un DOCTEUR                   */
        /////////////////////////////////////////////////////////////////////       
        else if(pNomTable.equals("docteur"))
        {
            docteur = new JPanel(new GridLayout(0, 1, -2, 2));
            jLabel[4] = new JLabel("Numero");
            jTextField[4] = new JTextField("", 30);
            jLabel[5] = new JLabel("Nom");
            jTextField[5] = new JTextField("", 30);
            jLabel[6] = new JLabel("Prenom");
            jTextField[6] = new JTextField("", 30);
            jLabel[7] = new JLabel("Adresse");
            jTextField[7] = new JTextField("", 30);
            jLabel[8] = new JLabel("Téléphone");
            jTextField[8] = new JTextField("", 30);        
            jLabel[9] = new JLabel("Spécialité");
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
            docteur.add(jComboBoxDocteur);
            Statement stmt = conn.createStatement();
            jComboBoxDocteur.setSelectedItem(item2);
            ResultSet Ps = stmt.executeQuery("SELECT * FROM `employe` WHERE numero = '"+item1+"'"); 
            while(Ps.next())
            {
                this.notrecas1 = Ps.getString("numero");
                jTextField[4].setText(Ps.getString("numero"));
                jTextField[5].setText(Ps.getString("nom"));
                jTextField[6].setText(Ps.getString("prenom"));
                jTextField[7].setText(Ps.getString("adresse"));
                jTextField[8].setText(Ps.getString("tel"));
            }
            submit[1] = new JButton("");
            submit[1].setIcon(new ImageIcon("images/validate.png")); 
            submit[1].setVerticalTextPosition(SwingConstants.BOTTOM); 
            submit[1].setHorizontalTextPosition(SwingConstants.CENTER);
            JPanel bas2 = new JPanel(new GridLayout(1,2));            
            bas2.add(submit[1]);
            bas2.add(retour);
            docteur.add(bas2);
            

        // Pour un docteur
        jTextField[4].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[4].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        submit[1].addActionListener(new ButtonController(this,connection)); 
        }
        

          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour une HOSPITALISATION          */
        /////////////////////////////////////////////////////////////////////
        else if(pNomTable.equals("hospitalisation"))
        {
            hospitalisation = new JPanel(new GridLayout(0, 1, -5, 5));
            jLabel[10] = new JLabel("Numero malade");
            jTextField[10] = new JTextField("", 30);
            jLabel[11] = new JLabel("Numero Service");
            Statement stmt = connection.createStatement();
            ResultSet Rs = stmt.executeQuery("SELECT * FROM service");                     
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
            Rs = stmt.executeQuery("SELECT * FROM hospitalisation WHERE no_malade = '"+item1+"'");
            while(Rs.next())
            {
                jTextField[10].setText(Rs.getString("no_malade"));
                jComboBoxHospi1.setSelectedItem(Rs.getString("code_service"));
                jTextField[12].setText(Rs.getString("no_chambre"));
                jTextField[13].setText(Rs.getString("lit"));
            }          
            hospitalisation.add(submit[2]);
            JPanel bas3 = new JPanel(new GridLayout(1,2));            
            bas3.add(submit[2]);
            bas3.add(retour);
            hospitalisation.add(bas3);
            
                    // Pour une hospitalisation
        //jTextField[10].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[10].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        // jTextField[12].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,(String) jComboBoxHospi1.getSelectedItem());}});
        jTextField[12].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        submit[2].addActionListener(new ButtonController(this,connection)); 
        }
         
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un INFIRMIER                 */
        /////////////////////////////////////////////////////////////////////
        else if(pNomTable.equals("infirmier"))
        {
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
            infirmier.add(submit[3]);
            JPanel bas4 = new JPanel(new GridLayout(1,2));            
            bas4.add(submit[3]);
            bas4.add(retour);
            infirmier.add(bas4);
            Statement stmt = connection.createStatement();
            ResultSet Ps = stmt.executeQuery("SELECT * FROM `employe` WHERE numero = '"+item1+"'"); 
            while(Ps.next())
            {
                jTextField[14].setText(Ps.getString("numero"));
                notrecas2 = Ps.getString("numero");
                jTextField[15].setText(Ps.getString("nom"));
                jTextField[16].setText(Ps.getString("prenom"));
                jTextField[17].setText(Ps.getString("adresse"));
                jTextField[18].setText(Ps.getString("tel"));
            }
            Ps = stmt.executeQuery("SELECT * FROM `infirmier` WHERE numero = '"+item1+"'"); 
            while(Ps.next())
            {
                jTextField[19].setText(Ps.getString("code_service"));
                notrecas3 = Ps.getString("code_service");
                jTextField[20].setText(Ps.getString("rotation"));
                jTextField[21].setText(Ps.getString("salaire"));
            }
            
        // Pour un infirmier
        jTextField[14].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[14].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        jTextField[19].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[19].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        submit[3].addActionListener(new ButtonController(this,connection)); 

        }
        
        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un MALADE                    */
        /////////////////////////////////////////////////////////////////////
        
        else if(pNomTable.equals("malade"))
        {
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
            malade.add(submit[4]);
            JPanel bas5 = new JPanel(new GridLayout(1,2));            
            bas5.add(submit[4]);
            bas5.add(retour);
            malade.add(bas5);
            
        
            Statement stmt = connection.createStatement();
            ResultSet Ps = stmt.executeQuery("SELECT * FROM `malade` WHERE numero = '"+item1+"'"); 
            while(Ps.next())
            {
                jTextField[23].setText(Ps.getString("numero"));
                this.notrecas4 = Ps.getString("numero");
                jTextField[24].setText(Ps.getString("nom"));
                jTextField[25].setText(Ps.getString("prenom"));
                jTextField[26].setText(Ps.getString("adresse"));
                jTextField[27].setText(Ps.getString("tel"));
                jTextField[28].setText(Ps.getString("mutuelle"));
            } 
            
        // Pour un malade
        jTextField[23].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[23].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        submit[4].addActionListener(new ButtonController(this,connection));
  
        }
        
        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un SERVICE                   */
        /////////////////////////////////////////////////////////////////////
        else if(pNomTable.equals("service"))
        {
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
            service.add(submit[5]);
            JPanel bas6 = new JPanel(new GridLayout(1,2));            
            bas6.add(submit[5]);
            bas6.add(retour);
            service.add(bas6);
            
            Statement stmt = connection.createStatement();
            ResultSet Ps = stmt.executeQuery("SELECT * FROM `service` WHERE code = '"+item1+"'"); 
            while(Ps.next())
            {
                jTextField[29].setText(Ps.getString("code"));
                jTextField[30].setText(Ps.getString("nom"));
                jTextField[31].setText(Ps.getString("batiment"));
                jTextField[32].setText(Ps.getString("directeur"));
                notrecas5 = Ps.getString("directeur");
            } 
            
        // Pour un service
        jTextField[32].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,"");}});
        jTextField[32].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        submit[5].addActionListener(new ButtonController(this,connection));
                  
        }
        
          /////////////////////////////////////////////////////////////////////
         /*              Champs à remplir pour un SOIGNE                    */
        /////////////////////////////////////////////////////////////////////
        else if(pNomTable.equals("soigne"))
        {       
            soigne = new JPanel(new GridLayout(0, 1, -5, 5));
            jLabel[33] = new JLabel("Numero docteur");        
            Statement stmt = connection.createStatement();
            ResultSet Rs = stmt.executeQuery("SELECT DISTINCT no_docteur FROM soigne");                     
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
            soigne.add(submit[6]);
            JPanel bas7 = new JPanel(new GridLayout(1,2));            
            bas7.add(submit[6]);
            bas7.add(retour);
            soigne.add(bas7);

            ResultSet Ps = stmt.executeQuery("SELECT * FROM `soigne` WHERE no_docteur = '"+item1+"' AND no_malade = '"+item2+"'"); 
            while(Ps.next())
            {
                jComboBoxSoigne.setSelectedItem(Ps.getString("no_docteur"));
                notrecas6 = Ps.getString("no_docteur");
                jTextField[34].setText(Ps.getString("no_malade"));
                notrecas5 = Ps.getString("no_malade");
            } 
            
        //Pour un soigne
        jTextField[34].addMouseListener(new MouseAdapter() {public void mouseExited(MouseEvent evt) {jTextFieldMouseExited(evt,(String) jComboBoxSoigne.getSelectedItem());}});
        jTextField[34].addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent evt) {jTextFieldMouseClicked(evt);}});
        submit[6].addActionListener(new ButtonController(this,connection));  
        }

        //-- Ajout des éléments dans la fenêtre --//
        tabbed_pan = new JTabbedPane(); 
        
        if(pNomTable.equals("chambre"))
        {
            tabbed_pan.addTab("Chambre", chambre);
        }
        
        else if(pNomTable.equals("docteur"))
        {
            tabbed_pan.addTab("Docteur", docteur);
        }
        
        else if(pNomTable.equals("hospitalisation"))
        {
            tabbed_pan.addTab("Hospitalisation", hospitalisation);         
        }
        
        else if(pNomTable.equals("infirmier"))
        {
            tabbed_pan.addTab("Infirmier", infirmier); 
        }
        
        else if(pNomTable.equals("malade"))
        {
            tabbed_pan.addTab("Malade", malade);
        }
        
        else if(pNomTable.equals("service"))
        {
            tabbed_pan.addTab("Service", service);
        }
        
        else if(pNomTable.equals("soigne"))
        {
            tabbed_pan.addTab("Soigne", soigne); 
        }

        this.add(tabbed_pan, BorderLayout.CENTER);

    
                retour.addActionListener(new ButtonController(this,connection));

        this.setSize(900, 500); 
        this.pack();       
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

    }



    /**
     *
     * @param i
     * @return the submit
     */
    public JButton getSubmit(int i) {
        return submit[i];
    }


    /**
     * @return the userField
     */
    public JTextField getJTexField(int i) {
        return jTextField[i];
    }

    /**
     * @return the userField
     */
    public JLabel getJLabel(int i) {
        return jLabel[i];
    }
    
    public JTabbedPane getTabbed_pan() {
        return tabbed_pan;
    }

    public JPanel getChambre() 
    {
       return chambre;
    }

    public JPanel getDocteur() 
    {
       return docteur;
    }
    
    public JPanel getHospitalisation() 
    {
       return hospitalisation;
    }
    
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
            if(jTextField[1].getText().equals(notrecas))
            {

            }
            else if(jTextField[1].getText().equals(""))
            {
                
            }
            else
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
                Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        }
                
        // Pour un docteur on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà
        if(evt.getSource() == jTextField[4])
        {
            if(jTextField[4].getText().equals(this.notrecas1))
                    {
                        
                    }
                    else if(jTextField[4].getText().equals(""))
                    {
                        
                    }
                    else
                    { 
                    try 
                    {
                
                Statement stmt = connection.createStatement();       
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
            } catch (SQLException ex) {
                Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
        }

        
        // Pour un infirmier on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà        
        if(evt.getSource() == jTextField[14])
        {
            
            
            if(jTextField[14].getText().equals(this.notrecas2))
                    {
                        
                    }
                    else if(jTextField[14].getText().equals(""))
                    {
                        
                    }
                    else
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
                Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
        }
        
        // Pour un infirmier on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà        
        if(evt.getSource() == jTextField[19])
        {                        
                if(jTextField[19].getText().equals(this.notrecas3))
                    {
                        
                    }
                    else if(jTextField[19].getText().equals(""))
                    {
                        
                    }
                    else
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
                Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
        }
        
        // Pour un malade on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà        
        if(evt.getSource() == jTextField[23])
        {
            
            if(jTextField[23].getText().equals(this.notrecas4))
                    {
                        
                    }
                    else if(jTextField[23].getText().equals(""))
                    {
                        
                    }
                    else
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
                Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
        }
        
        // Pour un service on va vérifier si l'identifiant rentré ne correspond pas à un identifiant existant déjà        
        if(evt.getSource() == jTextField[32])
        {
            
            if(jTextField[32].getText().equals(this.notrecas5))
                    {
                        
                    }
                    else if(jTextField[32].getText().equals(""))
                    {
                        
                    }
                    else
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
                                String nouv = jTextField[32].getText()+" -> Il n'existe pas, impossible de le promouvoir directeur !";
                                jTextField[32].setText(nouv);
                                jTextField[32].setForeground(Color.red);
                            }                       
                    }    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
        }
                
        // Pour un soigné on va vérifier si le couple no_malade/no_docteur rentré ne correspond pas à un couple existant déjà
        if(evt.getSource() == jTextField[34])
        {    
            
            if(jTextField[34].getText().equals(this.notrecas5))
                    {
                        
                    }
                    else if(jTextField[34].getText().equals(""))
                    {
                        
                    }
                    else
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
                Logger.getLogger(Modifier_Fen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
    
    public JComboBox getComboBoxChambre2() 
    {
         return jComboBoxChambre2;
    }
    
    public JComboBox getComboBoxDocteur() 
    {
         return jComboBoxDocteur;
    }
    
    public JButton getretour()
    {
        return retour;
    }
    
    public JTextField getfield(int i)
    {
        return jTextField[i];
    }
    
    public String getitem1()
    {
        return item1;
    }
    
    public String getitem2()
    {
        return item2;
    }
    
    public String getitem3()
    {
        return item3;
    }
    
    public String getpNomTable()
    {
        return pNomTable;
    }
    
    public String getcas()
    {
        return this.notrecas;
    }
    
}
