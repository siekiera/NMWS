package pl.edu.pw.elka.maszyna.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import pl.edu.pw.elka.maszyna.entity.algorytm.Klauzula;
import pl.edu.pw.elka.maszyna.entity.algorytm.ListaKlauzul;
import pl.edu.pw.elka.maszyna.entity.algorytm.Literal;

/**
 * @author lpielasz
 *
 */
public class ModulWnioskowania
{

    public ModulWnioskowania() {
    }

	
	/**
	 * @param listaKlauzul
	 * @return wynik wnioskowania
	 * Funkcja swoim działaniem generuje nowe Klauzule
	 * i umieszcza je w {@code listaKlauzul}. {@code listaKlauzul} zawiera klauzule, 
	 * w ktorych predykaty maja argumenty bedace stalymi.
	 */
	public boolean wnioskuj(ListaKlauzul listaKlauzul){
		//TODO algorytm - do zaimplementowania
        //TODO być może tę listę klauzul będzie lepiej dać w konstruktorze - jak chcesz
		//int liczbaPoczatkowychKlauzul = listaKlauzul.getKlauzule().size();
		List<Klauzula> klauzule = listaKlauzul.getKlauzule();
		List<Klauzula> dodaneKlauzule = new ArrayList<Klauzula>();
		
		System.out.println("Testowanie systemu wnioskowania:");
		boolean istniejeRezolucjowalnosc = true;
//		while (istniejeRezolucjowalnosc) {
			istniejeRezolucjowalnosc = false;
			for (ListIterator<Klauzula> gornyIterator = klauzule.listIterator(); 
					gornyIterator.hasNext();) {
				Klauzula gornaKlauzula = (Klauzula) gornyIterator.next();
				for (ListIterator<Klauzula> dolnyIterator = klauzule.listIterator(gornyIterator.nextIndex()); dolnyIterator
						.hasNext();) {
					Klauzula dolnaKlauzula = (Klauzula) dolnyIterator.next();
					
//					sprawdzanaKlauzula.dodaj(gornaKlauzula);
//					sprawdzanaKlauzula.dodaj(dolnaKlauzula);
					istniejeRezolucjowalnosc = sprawdzRezolucjowalnosc(gornaKlauzula, dolnaKlauzula);
					
				}
			}
			
//		}
		
		return false;
	}

    private boolean sprawdzRezolucjowalnosc(Klauzula gornaKlauzula,
			Klauzula dolnaKlauzula) {
    	boolean rezolucjaZaszla = false;
    	for (Literal literalZGornejKlauzuli : gornaKlauzula.literaly) {
    		for (Literal literalZDolnejKlauzuli : dolnaKlauzula.literaly) {
    			System.out.print("\n" + literalZGornejKlauzuli.toString() + " - " + literalZDolnejKlauzuli.toString());
    			if (literalZGornejKlauzuli.rezolucjowalny(literalZDolnejKlauzuli)) {
    				System.out.print(" : rezolucja");
    				// TODO polaczyc klauzule
    				rezolucjaZaszla = true;
    			}
    		}	
		}
    	return rezolucjaZaszla;
	}


	public void pobierzDrzewoWnioskowania() {
        //TODO to powinno coś zwracać pewnie trzeba będzie jakiś nowy typ tu zadeklarować
    }
}
