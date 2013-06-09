package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import pl.edu.pw.elka.maszyna.wspolne.wyjatki.WyjatekRezolucji;

/**
 * Klauzula - alternatywa literałów (predykatów lub zapreczonych pred.)
 *
 */
public class Klauzula 
{
	public Set<Literal> literaly;
	
	public Klauzula(Klauzula klauzula) {
		literaly = new HashSet<Literal>(klauzula.literaly);
	}
	
	/**
	 * Sprawdza czy na dwoch klauzulach mozna przeprowadzic rezolucje.
	 * Jesli mozna to zwraca jej wynik.
	 * @param inna
	 * @return
	 */
	public Klauzula sprawdzRezolucjowalnosc(Klauzula inna){
		
		Iterator gorna = literaly.iterator();
		Iterator dolna = inna.literaly.iterator();
		
		Set<Literal> powstalaKlauzula = new TreeSet<Literal>();
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
		
		if(tworzymyRezolucje){ 
			//TODO trzeba sprawdzic czy klauzula != TRUE
			Iterator zewnetrzny = powstalaKlauzula.iterator();
			Iterator wewnetrzny = powstalaKlauzula.iterator();
			while(zewnetrzny.hasNext()){
				while(wewnetrzny.hasNext()){
					if( ( (Literal)wewnetrzny.next()).jestZaprzeczeniem(((Literal)zewnetrzny.next() ) )) return null;
				}
				wewnetrzny = powstalaKlauzula.iterator();
			}
			
			return new Klauzula(powstalaKlauzula);
		}
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
    
    @Override
    public String toString() {
    	String wynik = "";
    	int licznikPetli = 0;
    	for(Literal literal : literaly) {
    		if(licznikPetli != 0) {
    			wynik += "|";
    		}
    		wynik += literal.toString();
    		licznikPetli++;
    	}
    	return wynik;
    }

    /**
     * Operacja przeprowadzenia rezolucji
     * @param innaKlauzula
     * @return
     */
	public Klauzula przeprowadzRezolucje(Klauzula innaKlauzula) {
		
		Klauzula gornaKlauzula = new Klauzula(innaKlauzula);
		Klauzula dolnaKlauzula = new Klauzula(this);
		
		boolean rezolucjowalny = true;
		while (rezolucjowalny) {
			rezolucjowalny = false;
			try {
				for (Literal literalZGornejKlauzuli : gornaKlauzula.literaly) {
		    		for (Literal literalZDolnejKlauzuli : dolnaKlauzula.literaly) {
		    			if (literalZGornejKlauzuli.rezolucjowalny(literalZDolnejKlauzuli)) {
		    				//tworzymy liste unifikacji
		    				ListaUnifikacji listaUnifikacji = literalZGornejKlauzuli.stworzListeUnifikacji(literalZDolnejKlauzuli);
//		    				System.out.println("Lista unifikacji:\n" + listaUnifikacji);
		    				throw new WyjatekRezolucji(literalZGornejKlauzuli, literalZDolnejKlauzuli, listaUnifikacji);
		    			}
		    		}
				}
			} catch (WyjatekRezolucji e) {
				rezolucjowalny = true;
				
				gornaKlauzula.literaly.remove(e.getLiteralZGornejKlauzuli());
				dolnaKlauzula.literaly.remove(e.getLiteralZDolnejKlauzuli());
				//TODO zaimplementowac unifikacje
				
				if (dolnaKlauzula.literaly.size() + gornaKlauzula.literaly.size() == 0) {
					return null;
				}
				
//				if (!e.getListaUnifikacji().czyPusta()) {
//					for (Literal literalZTejKlauzuli : this.literaly) {
//						literalZTejKlauzuli.przeprowadzUnifikacje(e.getListaUnifikacji());
//					}
//					for (Literal literalZInnejKlauzuli : innaKlauzula.literaly) {
//						literalZInnejKlauzuli.przeprowadzUnifikacje(e.getListaUnifikacji().odwroc());
//					}
//				}
			}
		}
		Klauzula powstalaKlauzula = new Klauzula(gornaKlauzula);
		powstalaKlauzula.dodaj(dolnaKlauzula);
		
		return powstalaKlauzula;
	}
    
}


