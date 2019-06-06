/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ButtonController;
import controleur.MouseController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

/**
 * Classe d'interface graphique de connection avec la fenêtre ou va être créée la connection qui sera
 * passée aux autres pages 
 * @author basileroth
 */
public class Connexion_Win extends JFrame{
    
    private static JTabbedPane principal;
    private JPanel ligne;
    private JPanel local;
    private JButton submit[];
    private JTextField jTextField[];    
    private JLabel jLabel[];
    private JLabel Alerte1;
    private JLabel Alerte2;
    private ImageIcon image;
    private JPasswordField mdpBDD;
    private JPasswordField mdpECE;
    private JPasswordField mdpSQL;
  
    /**
     * Constructeur par défaut qui va créer la classe
     * @throws SQLException
     */
    public Connexion_Win() throws SQLException {
        
        super("Connection à la BDD");
        this.jTextField = new JTextField[10];
        this.jLabel = new JLabel[10];
        this.submit = new JButton[3];
        this.setLayout(new BorderLayout());
                
        image = new ImageIcon("images/ece.jpg");
        //image = new ImageIcon("images/hopital2.png");
        jLabel[0] = new JLabel(image);
        this.add(jLabel[0], BorderLayout.NORTH);        
        
        local = new JPanel(new GridLayout(0,1,-3,3));
        ligne = new JPanel(new GridLayout(0,1,-3,3));
        
        
        jLabel[1] = new JLabel("Nom de la BDD");
        jLabel[2] = new JLabel("Identifiant");
        jLabel[3] = new JLabel("Mot de passe");
        jTextField[1] = new JTextField("",30); 
        jTextField[2] = new JTextField("",30); 
        this.mdpBDD = new JPasswordField("",30); 
        Alerte1 = new JLabel("");

        local.add(Alerte1);
        local.add(jLabel[1]);
        local.add(jTextField[1]); 
        local.add(jLabel[2]);
        local.add(jTextField[2]);  
        local.add(jLabel[3]);
        local.add(mdpBDD);
        submit[1] = new JButton("Accéder à la BDD localement");

        local.add(submit[1]);
 
            
        //-- Ajout des éléments dans la fenêtre --//
        principal = new JTabbedPane();        
        principal.addTab("En local", local);
        this.add(principal, BorderLayout.CENTER);
        this.pack();       
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
    
    public String getTextField(int i)
    {
        return jTextField[i].getText();
    }
    
    public String getPassword1()
    {
        char[] c = mdpBDD.getPassword();
        String s = new String(c);
        return s;
    }
    
    public String getPassword2()
    {
        char[] c = mdpECE.getPassword();
        String s = new String(c);
        return s;
    }
    
    public String getPassword3()
    {
        char[] c = mdpSQL.getPassword();
        String s = new String(c);
        return s;
    }
    
    public JButton getSubmitLocal()
    {
        return submit[1];
    }
    
    public JButton getSubmitLigne()
    {
        return submit[2];
    }
    
    public JLabel getAlerte1()
    {
        return this.Alerte1;
    }
    
    public JLabel getAlerte2()
    {
        return this.Alerte2;
    }
}
