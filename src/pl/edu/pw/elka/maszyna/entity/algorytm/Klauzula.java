package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Klauzula - alternatywa literałów (predykatów lub zapreczonych pred.)
 *
 */
public class Klauzula 
{
	public Set<Literal> literaly;
	
	
	
	/**
	 * Sprawdza czy na dwoch klauzulach mozna przeprowadzic rezolucje.
	 * Jesli mozna to zwraca jej wynik.
	 * @param inna
	 * @return
	 */
	public Klauzula sprawdzRezolucjowalnosc(Klauzula inna){
		
		Iterator gorna = literaly.iterator();
		Iterator dolna = inna.literaly.iterator();
		
		Set<Literal> powstalaKlauzula = new TreeSet<>();
		boolean tworzymyRezolucje = false;
		
		
		while(gorna.hasNext()){
			Literal literalGornej = (Literal)gorna.next();
			powstalaKlauzula.add(literalGornej);
			while(dolna.hasNext()){
				Literal literalDolnej = (Literal) dolna.next();
				powstalaKlauzula.add(literalDolnej);
				if(literalDolnej.jestZaprzeczeniem(literalGornej)){
					if(tworzymyRezolucje == true){
						System.out.println("Wi�cej ni� jedna rezolucja. Z�e dane. Powstala klauzula by�aby TRUE");
					}
					powstalaKlauzula.remove(literalDolnej);
					powstalaKlauzula.remove(literalGornej);
					tworzymyRezolucje = true;
				}
				
			}
			dolna = inna.literaly.iterator();
		}
		
		if(tworzymyRezolucje) return new Klauzula(powstalaKlauzula);
		else return null;
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


