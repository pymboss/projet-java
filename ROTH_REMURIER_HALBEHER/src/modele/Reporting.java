/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
/*import org.jfree.data.general.DefaultPieDataset;*/
import vue.*;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import javax.swing.JFrame;
import java.awt.*; 
import java.awt.event.*; 
import java.sql.Connection;
import javax.swing.*; 
import org.jfree.chart.*; 
import org.jfree.chart.plot.*; 
import org.jfree.data.*; 
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
/**
 * Classe Reporting pour la création des graphiques 
 * @author basileroth
 */
public class Reporting extends JPanel 
{


   private String Nom[];
   private int Valeur[];
   private String Titre;
   private String Choix;
   private JPanel panel1;
   private Connection conn;
   private Report_Fen fenmodifier;
   

   public Reporting(Connection conn, Report_Fen fen)
   {
       this.fenmodifier = fen;
       this.conn =conn;
   }


    public Reporting(String nomDesVariables[],int valeurDesVariables[],String titre,String choix) 
    { 
        Nom=nomDesVariables;
        Valeur=valeurDesVariables;
        Titre=titre;
        Choix=choix;
       
        this.panel1 = new JPanel(new BorderLayout()); 
        setSize(400, 250); 
        
        DefaultPieDataset pieDataset = new DefaultPieDataset(); 
        for(int i=0;i<Nom.length;i++)
        {
            pieDataset.setValue(Nom[i]+" = "+Valeur[i],Valeur[i]); 
        }
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i <Nom.length; i++)
        {
            dataset.addValue(Valeur[i], Nom[i],"");
        }

        JFreeChart pieChartBar = ChartFactory.createBarChart(Titre,"", "", dataset,PlotOrientation.VERTICAL, true, true, true); 
        JFreeChart pieChart = ChartFactory.createPieChart3D(Titre, pieDataset, true, true, true); 
        JFreeChart ringChart = ChartFactory.createRingChart(Titre, pieDataset, true, true, true);

        ChartPanel PIE = new ChartPanel(pieChart); 
        ChartPanel BAR = new ChartPanel(pieChartBar); 
        ChartPanel RING = new ChartPanel(ringChart);

        if(Choix.equals("camembert"))
        {
           panel1.add(PIE);
        }    
        else if(Choix.equals("barre"))
        {
          panel1.add(BAR);
        }
        else if(Choix.equals("camembert3"))
        {
          panel1.add(RING);
        }
        
        
  } 
    
    public JPanel getPanel()
    {
        return this.panel1;
        
    }


    
}
