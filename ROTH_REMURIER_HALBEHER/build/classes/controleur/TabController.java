/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import vue.Ajouter_Fen;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Classe pour gérer le fait que la taille de la fenêtre d'ajout change
 * @author basileroth
 */
public class TabController implements ChangeListener {
    Ajouter_Fen fen;
    public TabController(Ajouter_Fen fen){
        this.fen=fen;
    }
    @Override
    public void stateChanged(ChangeEvent ce) {
        
        if (fen.getTabbed_pan().getSelectedIndex() == 6) 
        {
            for (int i = 3 ; i<4;i++){fen.getChambre().remove(fen.getJLabel(i));fen.getChambre().remove(fen.getJTexField(i));}
            fen.getChambre().remove(fen.getbas1());
            fen.getChambre().remove(fen.getJLabel(0));            
            fen.getChambre().remove(fen.getComboBoxChambre());
            
            for (int i = 6 ; i<9;i++){fen.getDocteur().remove(fen.getJLabel(i));fen.getDocteur().remove(fen.getJTexField(i));}
            fen.getDocteur().remove(fen.getbas2());
            fen.getDocteur().remove(fen.getComboBoxDocteur());
            fen.getDocteur().remove(fen.getJLabel(9));
            
            for (int i = 12 ; i<14;i++){fen.getHospitalisation().remove(fen.getJLabel(i));fen.getHospitalisation().remove(fen.getJTexField(i));}
            fen.getHospitalisation().remove(fen.getbas3());
           
            for (int i = 16 ; i<22;i++){fen.getInfirmier().remove(fen.getJLabel(i));fen.getInfirmier().remove(fen.getJTexField(i));}
            fen.getInfirmier().remove(fen.getbas4());
            
            for (int i = 25 ; i<29;i++){fen.getMalade().remove(fen.getJLabel(i));fen.getMalade().remove(fen.getJTexField(i));}
            fen.getMalade().remove(fen.getbas5());
            
            for (int i = 31 ; i<33;i++){fen.getService().remove(fen.getJLabel(i));fen.getService().remove(fen.getJTexField(i));}
            fen.getService().remove(fen.getbas6());
            
            fen.setSize(fen.getPreferredSize());
            fen.revalidate();
            fen.repaint();
            
            for (int i = 3 ; i<4;i++){fen.getChambre().add(fen.getJLabel(i));fen.getChambre().add(fen.getJTexField(i));}
            fen.getChambre().add(fen.getJLabel(0));            
            fen.getChambre().add(fen.getComboBoxChambre());
            fen.getChambre().add(fen.getbas1());
            
            for (int i = 6 ; i<9;i++){fen.getDocteur().add(fen.getJLabel(i));fen.getDocteur().add(fen.getJTexField(i));}
            fen.getDocteur().add(fen.getComboBoxDocteur());
            fen.getDocteur().add(fen.getJLabel(9));
            fen.getDocteur().add(fen.getbas2());
            
            for (int i = 12 ; i<14;i++){fen.getHospitalisation().add(fen.getJLabel(i));fen.getHospitalisation().add(fen.getJTexField(i));}
            fen.getHospitalisation().add(fen.getbas3());
           
            for (int i = 16 ; i<22;i++){fen.getInfirmier().add(fen.getJLabel(i));fen.getInfirmier().add(fen.getJTexField(i));}
            fen.getInfirmier().add(fen.getbas4());
            
            for (int i = 25 ; i<29;i++){fen.getMalade().add(fen.getJLabel(i));fen.getMalade().add(fen.getJTexField(i));}
            fen.getMalade().add(fen.getbas5());
            
            for (int i = 31 ; i<33;i++){fen.getService().add(fen.getJLabel(i));fen.getService().add(fen.getJTexField(i));}
            fen.getService().add(fen.getbas6());
        }
        else if (fen.getTabbed_pan().getSelectedIndex() == 1 || fen.getTabbed_pan().getSelectedIndex() == 2 || fen.getTabbed_pan().getSelectedIndex() == 5)  
        {            
            for (int i = 8 ; i<9;i++){fen.getDocteur().remove(fen.getJLabel(i));fen.getDocteur().remove(fen.getJTexField(i));}
            fen.getDocteur().remove(fen.getbas2());
            fen.getDocteur().remove(fen.getJLabel(9));
            fen.getDocteur().remove(fen.getComboBoxDocteur());
           
            for (int i = 18 ; i<22;i++){fen.getInfirmier().remove(fen.getJLabel(i));fen.getInfirmier().remove(fen.getJTexField(i));}
            fen.getInfirmier().remove(fen.getbas4());
            
            for (int i = 27 ; i<29;i++){fen.getMalade().remove(fen.getJLabel(i));fen.getMalade().remove(fen.getJTexField(i));}
            fen.getMalade().remove(fen.getbas5());
            
            fen.setSize(fen.getPreferredSize());
            fen.revalidate();
            fen.repaint();
            
            for (int i = 8 ; i<9;i++){fen.getDocteur().add(fen.getJLabel(i));fen.getDocteur().add(fen.getJTexField(i));}
            fen.getDocteur().add(fen.getJLabel(9));
            fen.getDocteur().add(fen.getComboBoxDocteur());
            fen.getDocteur().add(fen.getbas2());
           
            for (int i = 18 ; i<22;i++){fen.getInfirmier().add(fen.getJLabel(i));fen.getInfirmier().add(fen.getJTexField(i));}
            fen.getInfirmier().add(fen.getbas4());
            
            for (int i = 27 ; i<29;i++){fen.getMalade().add(fen.getJLabel(i));fen.getMalade().add(fen.getJTexField(i));}
            fen.getMalade().add(fen.getbas5());
               
        }
        else if (fen.getTabbed_pan().getSelectedIndex() == 0 || fen.getTabbed_pan().getSelectedIndex() == 4)  
        {                       
            for (int i = 20 ; i<22;i++){fen.getInfirmier().remove(fen.getJLabel(i));fen.getInfirmier().remove(fen.getJTexField(i));}
            fen.getInfirmier().remove(fen.getbas4());
            
            fen.setSize(fen.getPreferredSize());
            fen.revalidate();
            fen.repaint();
            
            for (int i = 20 ; i<22;i++){fen.getInfirmier().add(fen.getJLabel(i));fen.getInfirmier().add(fen.getJTexField(i));}
            fen.getInfirmier().add(fen.getbas4());
        }
        
        else if (fen.getTabbed_pan().getSelectedIndex() == 3)  
        {                       
            fen.setSize(fen.getPreferredSize());
            fen.revalidate();
            fen.repaint();
        }

    }
}
