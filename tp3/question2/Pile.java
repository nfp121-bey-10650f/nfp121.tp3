package question2;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pile implements PileI {

    private Object[] zone;
    private int ptr;

    public Pile(int taille) {
        if(taille <=0) 
            taille =PileI.CAPACITE_PAR_DEFAUT;
        zone = new Object[taille];
        ptr = 0;
    }

    public Pile() {
        this(0);
    }

    public void empiler(Object o) throws PilePleineException {
        if(estPleine()) 
            throw new PilePleineException();
        zone[ptr]= o;
        ptr ++;
    }

    public Object depiler() throws PileVideException {
       if(estVide())
            throw new PileVideException ();
       ptr--;
       return zone[ptr];
        
    }

    public Object sommet() throws PileVideException {
        if(estVide()) 
            throw new PileVideException();
        return zone[ptr-1];
    }

    public int capacite() {
        return this.zone.length;
        
    }

    public int taille() {
        return ptr;
    }

    public boolean estVide() {
        return (ptr == 0);
    }

    public boolean estPleine() {
        return (ptr == zone.length);
    }

    public boolean equals(Object o) {
        if(! (o instanceof PileI) ) return false;
        PileI op = (PileI)o;
        if(op.taille() != this.taille()) return false;
        if(op.capacite() != this.capacite()) return false; 
        return op.toString().equals(o.toString());
        
    }

    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        // a completer
        String res = "[";
       for(int i = ptr-1; i>=0; i--)
       {
           res+= zone[i];
           
           if(i>0) res+=", ";
       }
        
        res += "]";
        
        return res;
        
    }
}