/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.FondsExistantException;
import controleur.FondsInexistantException;
import controleur.InstrumentInexistantException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mathi
 */
public class Portefeuille {
   
    //attribut
    HashMap<String,Fonds> fondsMap;
    HashMap<String,Instrument>instrumentMap;
    
    //constructeur
    
    public Portefeuille(){
        
        fondsMap=new HashMap<String,Fonds>();
        instrumentMap=new HashMap<String,Instrument>();
        
    }
    
    public Portefeuille(HashMap<String,Fonds> newFondsMap, 
            HashMap<String,Instrument>newInstrumentMap){
        
        fondsMap=new HashMap<>();
        fondsMap.putAll(newFondsMap);
        instrumentMap=new HashMap<>();
        instrumentMap.putAll(newInstrumentMap);
}
  //question 1.3
    
    public double rechercherFonds(String cle) throws FondsInexistantException{
        
        double montant=0.0;
        
        //rechercher un fonds dans le hashmap de fonds
        
        Fonds f;
        f= fondsMap.get(cle);
        if (f==null)
        {
            throw new FondsInexistantException();
        } else {
          montant= f.getAmount();
          return montant;
        }
      
    }
    
    //question 1.4 
    
    public ArrayList<Fonds>rechercherInstrument(String cle) throws InstrumentInexistantException
    {
        ArrayList<Fonds>myArrayListFonds;
        
        myArrayListFonds=new ArrayList<Fonds>();
        //rechercher un instrument dans notre hashmap des instruments
        
      Instrument i =  instrumentMap.get(cle);
      
      
      if(i==null)
      {
          throw new InstrumentInexistantException();
      }else {
     myArrayListFonds   =  i.getValeursFonds();
       return myArrayListFonds;
      }
      
    }
    
    //question 1.5
    public void ajouterFonds(String cle,double montant) throws FondsExistantException{
        
        Fonds f=new Fonds();
        //detecrer si le fond est deja dans hashmap
        
        f=fondsMap.get(cle);
        if(f !=null){
            
            throw new FondsExistantException();
        }else{
            f=new Fonds(montant);
            fondsMap.put(cle,f);
        }
    }
    
    //question 1.6
    
    public void ajouterFondsInstrument(String cle, Fonds f)
            
    {
       try 
       {
           
           this.rechercherInstrument(cle);
       }
      catch (InstrumentInexistantException e)
      {
        Instrument i=new Instrument();
        i.ajouterFonds(f);
        this.instrumentMap.put(cle, i);
        System.out.println("fond ajouté");
          
      }
    }
    
    //question 1.7
    
    public void supprimerFonds(String cle)
    {
       try {
           
           double a= this.rechercherFonds(cle);
           this.fondsMap.remove(cle,a);
           System.out.println("les fonds ont été correctement supprimé");
       } catch (FondsInexistantException e)
       {
           
           e.getMessage();
       }
        
    }
    
    //suite question 1.7
    
    public void supprimerInstrument(String cle)
    {
        
        try{
            
            ArrayList<Fonds>fond=this.rechercherInstrument(cle);
            for (int i=0;i<fond.size();i++)
            {
                fond.remove(i);
            }
            this.instrumentMap.remove(cle);
            System.out.println("instrument supprimé");
        }
        catch(InstrumentInexistantException e)
        {
            e.getMessage();
        }
    }
    
    //setter et getter
    public HashMap<String,Fonds> getFondsMap(){
        return fondsMap;
    }
    
    public HashMap<String,Instrument> getInstrumentMap(){
        
        return instrumentMap;
    }
    
    
    
    public void setFondsMap(HashMap<String,Fonds> newFondsMap){
         fondsMap=new HashMap<>();
        fondsMap.putAll(newFondsMap);
        
    }
    
    public void setInstrumentMap(HashMap<String,Instrument> newInstrumentMap)
{
    instrumentMap=new HashMap<>();
    instrumentMap.putAll(newInstrumentMap);
    
}
    
}


