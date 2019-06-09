/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.sort;

import java.util.Comparator;

/**
 *
 * @author mathi
 */
public class Instrument {
    
    private ArrayList<Fonds> valeursFonds;
    public Instrument(){
        
        valeursFonds=new ArrayList<>();
    }
    
    public Instrument(ArrayList<Fonds> newFonds){
        
        valeursFonds=newFonds;
    }
    
      //question 1.2
   public void ajouterFonds(Fonds f){
       
       valeursFonds.add(f);
   } 
    
   //question 1.9
   
   public void trieFonds(){
       
for (int i=0;i<valeursFonds.size();i++)
{
    
sort(valeursFonds);
}
   
   }
   
   public void AfficherValeurFonds(){
       
       for (int i=0;i<this.valeursFonds.size();i++)
       {
           System.out.println(this.valeursFonds.get(i).getAmount());
       }
   }
   
    public ArrayList<Fonds>getValeursFonds(){
        
        return valeursFonds;
    }
    public void setValeurFonds(ArrayList<Fonds> newFonds){
        
        valeursFonds=newFonds;
    }

  

   
}
