package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.Iterator;
import java.util.Set;

/**
 * Klauzula - alternatywa literałów (predykatów lub zapreczonych pred.)
 *
 */
public class Klauzula 
{
	public Set<Literal> literaly;
	
	
	
	/**
	 * Sprawdza czy na dwoch klauzulach mozna przeprowadzic rezolucje.
	 * @param inna
	 * @return
	 */
	public boolean sprawdzRezolucjowalnosc(Klauzula inna){
		
		Iterator gorna = literaly.iterator();
		Iterator dolna = inna.literaly.iterator();
		
		while(gorna.hasNext()){
			Literal literalGornej = (Literal)gorna.next();
			while(dolna.hasNext()){
				if(dolna.next().equals(literalGornej)) return true;
			}
			dolna = inna.literaly.iterator();
		}
		
		return false;
	}

    public Klauzula(Set<Literal> literaly)
    {
        this.literaly = literaly;
    }

    /**
     * Suma logiczna 2 klauzul
     * @param k2
     */
    public void dodaj(Klauzula k2) {
        literaly.addAll(k2.literaly);
    }
}


