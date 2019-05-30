/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.*;
import java.awt.*;
import java.sql.Connection;
import javax.swing.*;

/**
 * Classe d'interface graphique pour gérer le reporting avec les boutons 
 * qui seront gérer dans ButtonController
 * @author basileroth
 */
public class Report_Fen extends JFrame {
    
    private JPanel Haut = new JPanel();
    private JPanel Milieu = new JPanel();
    private JPanel Milieu1 = new JPanel();
    private JPanel Milieu2 = new JPanel();
    private JPanel GRAPHE = new JPanel();
    private JPanel Bas = new JPanel();
    private Connection connection;    
    private JButton btn[];
    private JRadioButton camenbert;
    private JRadioButton barre;
    private JRadioButton ring;
    private JLabel titre = new JLabel();
    
    
    public Report_Fen(Connection conn)
    {
        super("Reporting");
        this.setSize(900, 500);  
        this.connection = conn;
        this.btn = new JButton[10];
        this.camenbert = new JRadioButton("Camembert");
        this.barre = new JRadioButton("Histogramme");
        this.ring = new JRadioButton("Ring Chart");
        
        Dimension d = new Dimension(50,50);
        for(int i = 0 ; i < 7 ; i++)
        {
            btn[i] = new JButton();
            btn[i].setSize(d);
        }
        btn[0].setText("Malades / Service");
        btn[1].setText("Docteurs / Spécialité");
        btn[2].setText("Salaires des infirmiers");
        btn[3].setText("Infirmiers / Service");
        btn[4].setText("Proportion de docteurs/infirmiers");
        btn[5].setText("Malades / Docteur");
        btn[6].setText("Retour à l'acceuil");
        titre.setText("Veuillez choisir quelle diagramme vous voulez afficher"); 
        
        for(int i = 0; i < 7 ; i++)
        {
            btn[i].addActionListener(new ButtonController(this,connection));
        }
        
        this.setLayout(new BorderLayout());
        
        ButtonGroup group = new ButtonGroup();
        group.add(camenbert);
        group.add(barre);
        group.add(ring);
               
        Haut.add(camenbert);
        Haut.add(barre);
        Haut.add(ring);
        
        GridLayout gl = new GridLayout();
        gl.setColumns(2);
        gl.setRows(3);
        gl.setHgap(5); 
        gl.setVgap(5);
        Milieu1.setLayout(gl);
        for(int i = 0 ; i < 6 ; i++)
        {
            Milieu1.add(btn[i]);
        }
        Milieu2.setLayout(new BorderLayout());
        
        Milieu.setLayout(new BorderLayout());
        Milieu.add(Milieu1, BorderLayout.NORTH);
        Milieu.add(Milieu2, BorderLayout.CENTER);
        
        Bas.add(btn[6]);
        this.getContentPane().add(Haut, BorderLayout.NORTH);
        this.getContentPane().add(Bas, BorderLayout.SOUTH);
        this.getContentPane().add(Milieu, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);    
    }
    
    public JButton getbtn(int i)
    {
        return btn[i];
    }
    
    public JRadioButton getradio1()
    {
        return camenbert;
    }
    
    public JRadioButton getradio2()
    {
        return barre;
    }

    public JRadioButton getradio3()
    {
        return ring;
    }
    
    public void setPanel(JPanel a)
    {
        Milieu2.add(a, BorderLayout.CENTER);
        Milieu2.validate();
    }
    
    public JPanel getPanel()
    {
        return this.Milieu2;
        
    }
        
}
