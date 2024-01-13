package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile4 implements PileI, Cloneable {
    /** la liste des Maillons/Elements */
    private Maillon stk;
    /** la capacité de la pile */
    private int capacite;
    /** le nombre */
    private int nombre;

    /**
     * Classe interne "statique" contenant chaque élément de la chaine c'est une
     * proposition, vous pouvez l'ignorer !
     */
    private static class Maillon implements Cloneable {
        private Object element;
        private Maillon suivant;

        public Maillon(Object element, Maillon suivant) {
            this.element = element;
            this.suivant = suivant;
        }

        public Maillon suivant() {
            return this.suivant;
        }

        public Object element() {
            return this.element;
        }

        public Object clone() throws CloneNotSupportedException {
            Maillon m = (Maillon) super.clone();
            m.element = element;
            return m;
        }
    }

    /**
     * Création d'une pile.
     * 
     * @param taille
     *            la taille de la pile, la taille doit être > 0
     */
    public Pile4(int taille) {
        if (taille <= 0)
            taille = CAPACITE_PAR_DEFAUT;
        this.stk = null;
        this.capacite = taille;
    }

    public Pile4() {
        this(PileI.CAPACITE_PAR_DEFAUT);
    }

    public void empiler(Object o) throws PilePleineException {
        if (estPleine())
            throw new PilePleineException();
        if(stk == null) stk =  new Maillon(o, null);
        
        else
        {  Maillon premier = stk;
            while(premier.suivant != null)
            {
                premier = premier.suivant;
              }
              
            premier.suivant = new Maillon(o, null);
              
          }
          this.nombre ++;
    }

    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        // à compléter
        Maillon res = stk;
        if(stk.suivant == null)
        {
            stk = null;
            nombre--;
            return res.element;
        }
        else
        {
            Maillon premier = stk;
            while(premier.suivant != null)
            {
                if(premier.suivant.suivant ==null)
                  {
                      res = premier.suivant;
                      premier.suivant = null;
                      break;
                   }
                premier = premier.suivant;
                res = premier;
            }
            nombre --;
            return res.element;
        }
        
    }

    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();
       Maillon premier = stk;
       if(premier.suivant == null) return premier.element;
       
       while(premier.suivant != null)
       {
           premier = premier.suivant;
        }
        
        return premier.element;
            
    }

    /**
     * Effectue un test de l'état de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide() {
        return (stk == null);
    }

    /**
     * Effectue un test de l'état de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
       return (nombre >= capacite);
    }

    /**
     * Retourne une représentation en String d'une pile, contenant la
     * représentation en String de chaque élément.
     * 
     * @return une représentation en String d'une pile
     */
    public String toString() {

       String s = "[";
        if (taille() != 0) {

            if (taille() == 1) {
                s += stk.element;
            } else {
                Maillon premier = stk;

                Object[] tab = new Object[taille()];
                int position = 0;
                while (premier.suivant != null) {
                    tab[position] = premier.element;
                    position++;
                    premier = premier.suivant;

                }
                tab[position] = premier.element;
                for (int i = tab.length - 1; i >= 0; i--) {
                    s += tab[i];

                    if (i > 0)
                        s += ", ";
                }

            }

        }

        return s + "]";

    }

    public boolean equals(Object o) {
        if (o instanceof Pile4) {
           
           Pile4 op = (Pile4) o;
           if(op.taille()!= this.taille() || op.capacite() != this.capacite())
           return false;
           
           else
           {
              return this.toString().equals(o.toString());
               
            }
           
        }
        return false;
    }

    public int capacite() {
        return this.capacite;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public int taille() {
        return nombre;
    }
}