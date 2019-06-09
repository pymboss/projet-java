/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author mathi
 */
public class Fonds extends Comparaison {
 
    
    
    double amount;
    //constructeur
    public Fonds(){
        
        amount=0.0;
    }
    
    public Fonds (double newAmount){
        
        amount=newAmount;
    }

    
    public double getAmount(){
        
        return amount;
    }
    public void setAmount(double newAmount)
    {
        amount=newAmount;
    }
}
