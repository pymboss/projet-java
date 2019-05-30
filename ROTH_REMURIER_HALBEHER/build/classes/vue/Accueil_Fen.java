/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ButtonController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Fenêtre Principale d'interface graphique qui va afficher la page principale ainsi que gérer les interractions
 * avec les autres modules du projet
 *
 * @author basileroth
 */
public class Accueil_Fen extends JFrame {

    private  JLabel error;
    private final JSplitPane Cote_Gauche;
    private final JScrollPane Scroll_Gauche;
    private final JPanel Cote_Droit;
    private final JTextField Element_A_Rechercher;
    private final JTextField Element_A_Rechercher2;
    private final GridBagLayout Layout_Gauche;
    private final JRadioButton[] Radio_Tab;
    private final JScrollPane Scroll_Recherche;
    private final JPanel Recherche_G;
    private final GridBagLayout Layout_Recherche;
    private final JComboBox jComboBoxChoix1;
    private final JComboBox jComboBoxChoix2;
    private final JComboBox jComboBoxChoix11;
    private final JComboBox jComboBoxChoix;
    private final JComboBox jComboBoxChoix22;
    private final JButton Rechercher;
    private final Connection connection;
    private final JTable jTable1;
    private final JPanel ChoixAffiche;
    private final ButtonGroup group;
    private final JButton Ajouter;
    private final JButton Modifier;
    private final JButton Reporting;
    private final JButton Supprimer;
    private final JButton Rechercher2;
    //private final JComboBox Rechercher3;
    

    /**
     * Constructeur avec la connection en paramètre  
     *
     * @param conn pour la connection 
     * @throws java.sql.SQLException
     */
    public Accueil_Fen(Connection conn) throws SQLException 
    {
        
        // Initialisation des paramètres
        this.connection = conn;
        int vertical_value = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
        int horizontal_value = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        
        // Pour couper le panel en 2       
        this.Cote_Gauche = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
        this.Cote_Gauche.setOneTouchExpandable(false);

        //LAYOUT DE GAUCHE
        Layout_Gauche = new GridBagLayout();
        Cote_Droit = new JPanel(Layout_Gauche);

        
        // On crée les contraintes pour toutes la page
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        // GROUP BUTTON POUR LE TABLEAU   
        Statement stmt = connection.createStatement();
        Radio_Tab = new JRadioButton[9];
        stmt = connection.createStatement();
        ResultSet Rs = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'hopital'");
        int i =0;
        group = new ButtonGroup();
        while (Rs.next())
        { 
            Radio_Tab[i] = new JRadioButton(Rs.getString("TABLE_NAME"));
            group.add(Radio_Tab[i]);
            i++;
        }
        Radio_Tab[0].setSelected(true);
        ChoixAffiche = new JPanel(new GridLayout(0, 1));
        for (int a = 0; a < 8 ; a++) 
        {
            ChoixAffiche.add(Radio_Tab[a]);
        }
        ChoixAffiche.setBorder(new TitledBorder("Table à afficher"));

        Layout_Gauche.setConstraints(ChoixAffiche, constraints);
        Cote_Droit.add(ChoixAffiche);

        
        
        // POUR LA RECHERCHE
        Element_A_Rechercher = new JTextField(20);
        JPanel Recherche = new JPanel(new GridLayout(0,1,-1,1));
        jComboBoxChoix1 = new JComboBox<>();
        jComboBoxChoix2 = new JComboBox<>();
        stmt = conn.createStatement();
        Rs = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='hopital'");
        while (Rs.next())
        { 
            jComboBoxChoix1.addItem(Rs.getString("TABLE_NAME"));           
        }
        stmt = conn.createStatement();
        Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = 'chambre'");
        while (Rs.next())
        { 
            jComboBoxChoix2.addItem(Rs.getString("column_name"));           
        }
        
        this.Rechercher = new JButton("Rechercher");
        this.Rechercher2 = new JButton("Recherche Avancée");
        Recherche.add(jComboBoxChoix1);
        Recherche.add(jComboBoxChoix2);
        Recherche.add(Element_A_Rechercher);
        Recherche.add(Rechercher); 
        Recherche.setBorder(new TitledBorder("Rechercher par :"));
        Layout_Gauche.setConstraints(Recherche, constraints);
        Cote_Droit.add(Recherche);
        error = new JLabel("",SwingConstants.CENTER);
        
        
        for(int k = 0; k<8 ; k++)
        {
            Radio_Tab[k].addActionListener(new ButtonController(this,connection));
        }
                
        jComboBoxChoix1.addActionListener(new ButtonController(this,connection));
        

        // POUR LA RECHERCHE AVANCÉE
        Element_A_Rechercher2 = new JTextField(20);
        JPanel Recherche2 = new JPanel(new GridLayout(0,1,-1,1));
        jComboBoxChoix = new JComboBox<>();
        jComboBoxChoix11 = new JComboBox<>();
        jComboBoxChoix22 = new JComboBox<>();
        JLabel table1 = new JLabel("Table 1 : ");
        JLabel table2 = new JLabel("Table 2 : ");
        stmt = conn.createStatement();
        Rs = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='hopital'");
        while (Rs.next())
        { 
            jComboBoxChoix11.addItem(Rs.getString("TABLE_NAME"));  
            jComboBoxChoix.addItem(Rs.getString("TABLE_NAME"));  
        }
        jComboBoxChoix.setSelectedIndex(6);
        stmt = conn.createStatement();
        Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = 'chambre'");
        while (Rs.next())
        { 
            jComboBoxChoix22.addItem(Rs.getString("column_name"));           
        }        

        Recherche2.add(table1);
        Recherche2.add(jComboBoxChoix);
        Recherche2.add(table2);
        Recherche2.add(jComboBoxChoix11);
        Recherche2.add(jComboBoxChoix22);
        Recherche2.add(Element_A_Rechercher2);
        Recherche2.add(Rechercher2); 
        Recherche2.setBorder(new TitledBorder("Recherche avancée :"));
        Layout_Gauche.setConstraints(Recherche2, constraints);
        Cote_Droit.add(Recherche2);
        error = new JLabel("",SwingConstants.CENTER);
        error.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        for(int k = 0; k<8 ; k++)
        {
            Radio_Tab[k].addActionListener(new ButtonController(this,connection));
        }
                
        jComboBoxChoix1.addActionListener(new ButtonController(this,connection));
        jComboBoxChoix11.addActionListener(new ButtonController(this,connection));
        
        //Cote_Droit.add(error);

        // POUR LE TABLEAU 
        DefaultTableModel dt = new DefaultTableModel();
        stmt = connection.createStatement();
            
        jTable1 = new JTable(); 
        Rs = stmt.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = 'chambre'");
        while(Rs.next())
        {
            dt.addColumn(Rs.getString("column_name"));                    
        }                           
        jTable1.setModel(dt);
       
        ResultSet Ps = stmt.executeQuery("Select * from chambre");
        if(jTable1.getColumnCount()==4)
        {
            while(Ps.next())
            {
                dt.addRow(new Object[]
                {Ps.getString(jTable1.getColumnName(0)),
                    Ps.getString(jTable1.getColumnName(1)),
                    Ps.getString(jTable1.getColumnName(2)),
                    Ps.getString(jTable1.getColumnName(3))
                });
            }}
        jTable1.setModel(dt);
        jTable1.setGridColor(Color.black);


        //EAST RECHERCHE
        Layout_Recherche = new GridBagLayout();
        Recherche_G = new JPanel(Layout_Recherche);
        Recherche_G.setBorder(new TitledBorder("BDD"));

        //SCROLL RECHERCHE
        Scroll_Gauche = new JScrollPane(Cote_Droit, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, horizontal_value);
        Scroll_Recherche = new JScrollPane(jTable1, vertical_value, horizontal_value);
        Scroll_Gauche.getVerticalScrollBar().setUnitIncrement(16);
        Scroll_Recherche.getVerticalScrollBar().setUnitIncrement(16);
        
        //SPLIT RECHERCHE
        Cote_Gauche.add(Scroll_Gauche);

        Cote_Gauche.add(Scroll_Recherche);

        //MAIN TABBED PAN
        //JPanel Principal = new JPanel(new BorderLayout());
        GridLayout gl = new GridLayout();
        gl.setColumns(4);
        gl.setRows(1);
        gl.setHgap(15); 
        gl.setVgap(15);
        JPanel bas = new JPanel(gl);

        JLabel titre = new JLabel("ACCUEIL",SwingConstants.CENTER);
        titre.setFont(new Font("Arial", 1, 40)); 
        titre.setSize(900, 100);
        titre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        Ajouter = new JButton("Ajouter");
        Ajouter.setIcon(new ImageIcon("images/ajouter1.png")); 
        Ajouter.setVerticalTextPosition(SwingConstants.BOTTOM); 
        Ajouter.setHorizontalTextPosition(SwingConstants.CENTER);  
        
        Modifier = new JButton("Modifier");
        Modifier.setIcon(new ImageIcon("images/modifierpetit.png")); 
        Modifier.setVerticalTextPosition(SwingConstants.BOTTOM); 
        Modifier.setHorizontalTextPosition(SwingConstants.CENTER);

        Supprimer = new JButton("Supprimer");
        Supprimer.setIcon(new ImageIcon("images/supprimerpetit.png")); 
        Supprimer.setVerticalTextPosition(SwingConstants.BOTTOM); 
        Supprimer.setHorizontalTextPosition(SwingConstants.CENTER);

        Reporting = new JButton("Reporting");
        Reporting.setIcon(new ImageIcon("images/statspetite.png")); 
        Reporting.setVerticalTextPosition(SwingConstants.BOTTOM); 
        Reporting.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel hautt = new JPanel(new BorderLayout());
        hautt.add(titre,BorderLayout.NORTH);
        hautt.add(error,BorderLayout.SOUTH);
        add(hautt,BorderLayout.NORTH);
        bas.add(Ajouter);
        bas.add(Modifier);
        bas.add(Supprimer);
        bas.add(Reporting);        
        
        add(Cote_Gauche, BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);

        this.pack();
        this.setSize(900, 550);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Ajouter.addActionListener(new ButtonController(this,connection));
        Rechercher.addActionListener(new ButtonController(this,connection));
        Rechercher2.addActionListener(new ButtonController(this,connection));
        Modifier.addActionListener(new ButtonController(this,connection));
        Supprimer.addActionListener(new ButtonController(this,connection));
        Reporting.addActionListener(new ButtonController(this,connection));
    }



    /**
     * @return Cote_Gauche
     */
    public JSplitPane getCote_Gauche() {
        return Cote_Gauche;
    }

    /**
     * @return Scroll_Gauche
     */
    public JScrollPane getScroll_Gauche() {
        return Scroll_Gauche;
    }

    /**
     * @return Cote_Droit
     */
    public JPanel getCote_Droit() {
        return Cote_Droit;
    }

    /**
     * @return Element_A_Rechercher
     */
    public JTextField getElement_A_Rechercher() {
        return Element_A_Rechercher;
    }

    /**
     * @return Scroll_Recherche
     */
    public JScrollPane getScroll_Recherche() {
        return Scroll_Recherche;
    }

    /**
     * @return Recherche_G
     */
    public JPanel getRecherche_G() {
        return Recherche_G;
    }

    /**
     * @return Layout_Recherche
     */
    public GridBagLayout getLayout_Recherche() {
        return Layout_Recherche;
    }
    
    /**
     *
     * @return
     */
    public JComboBox getComboBoxChoix1()
    {
        return this.jComboBoxChoix1;
    }
    
    public JComboBox getComboBoxChoix11()
    {
        return this.jComboBoxChoix11;
    }
    
    public JComboBox getComboBoxChoix()
    {
        return this.jComboBoxChoix;
    }
    
    /**
     *
     * @return
     */
    public JComboBox getComboBoxChoix2()
    {
        return this.jComboBoxChoix2;
    }
    
    public JComboBox getComboBoxChoix22()
    {
        return this.jComboBoxChoix22;
    }
    
    public JTable getTable()
    {
        return this.jTable1;
    }
    
    public String getSelectionne()
    {
        int j = 0;
        for(int i = 0; i<8; i++)
        {
           if(Radio_Tab[i].isSelected())
           {
               j=i;
           }
        }
        return Radio_Tab[j].getText();
    }
    
    public JRadioButton getRadio(int i)
    {
        return Radio_Tab[i];
    }
    
    public JButton getAjouter()
    {
        return Ajouter;
    }
    
    public JButton getModifier()
    {
        return Modifier;
    }
    
    public JButton getSupprimer()
    {
        return Supprimer;
    }
    
    public JButton getReporting()
    {
        return Reporting;
    }
    
    public JButton getRechercher()
    {
        return Rechercher;
    }
    
    /**
     * Selectionne le radiobutton correspondant au choix
     * @param a nom de la table à selectionner
     */
    public void setSelectionne(String a)
    {
        int i = 0;
        for(int k = 0 ; k<8; k++)
        {
            if(Radio_Tab[k].getText().equals(a))
            {
                i = k;
            }
        }
        
        for(int k = 0 ; k<8; k++)
        {
            Radio_Tab[k].setSelected(false);
        }
        Radio_Tab[i].setSelected(true);
        
    }
    
    /**
     *
     * @return le TextField de recherche
     */
    public JTextField getTextfield()
    {
        return Element_A_Rechercher;
    }
    
    public JTextField getTextfield2()
    {
        return Element_A_Rechercher2;
    }
    
    public JLabel geterror()
    {
        return error;
    }
    
    public JButton getrechercher2()
    {
        return Rechercher2;
    }


}
