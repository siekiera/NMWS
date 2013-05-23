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
	
}


