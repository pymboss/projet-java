
package modele;
import java.lang.Comparable;
/**
 *
 * @author mathi
 */
public class Comparaison implements Comparable<Fonds> {

  
    
    private double amount;
    private String cle;
   

    public Comparaison()

    {

        amount=0.0;
        cle="";

    }

    


    public Comparaison(String key,double montant)

    {

        amount=montant;

        cle = key;

    } 



    public boolean equals(Fonds f)

    {

        if (this.getAmount()==f.getAmount())

        {

            return true;

        }

        else {

            return false;

        }

    } 
  public double getAmount()

    {

        return this.amount;

    }

    @Override
    public int compareTo(Fonds o) {
          

        if (this.getAmount()>o.getAmount())

        {
            return 1;

        }
        else if ( equals (o) ) {

            return 0;
        }

        else {
            return -1;

        }
    }
    
}
