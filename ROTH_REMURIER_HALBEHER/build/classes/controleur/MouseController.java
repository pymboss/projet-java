/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import vue.Accueil_Fen;
import vue.Ajouter_Fen;
import vue.Connection_Fen;
import vue.Modifier_Fen;
import vue.Report_Fen;

/**
 * Classe qui va gérer certaines des interractions avec la souris 
 * @author basileroth
 */
public class MouseController implements MouseListener{
    
    private Report_Fen fen = null;
    private Connection_Fen fenconn = null;
    private Connection connection = null;
    private Accueil_Fen fenprincipale = null;
    private Ajouter_Fen fenajouter = null;
    private Modifier_Fen fenmodifier = null;


    public MouseController(Report_Fen fen, Connection conn)
    {
        this.fen = fen;
        this.connection = conn;
    }
    
    public MouseController(Connection_Fen fen)
    {
        this.fenconn = fen;
    }
    
    public MouseController(Accueil_Fen fen,Connection conn)
    {
        this.fenprincipale = fen;
        this.connection = conn;
    }
    
    public MouseController(Ajouter_Fen fen,Connection conn)
    {
        this.fenajouter = fen;
        this.connection = conn;
    }
    
    public MouseController(Modifier_Fen fen,Connection conn)
    {
        this.fenmodifier = fen;
        this.connection = conn;
    }
    
    
    public void mouseClicked(MouseEvent e) 
    {
        if(!(this.fenconn == null))
        {
            fenconn.getAlerte1().setText("");  
            fenconn.getAlerte2().setText("");  
        }         
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
         
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
               
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {

    }
    
}
