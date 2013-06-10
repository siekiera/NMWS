package pl.edu.pw.elka.maszyna.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import pl.edu.pw.elka.maszyna.entity.algorytm.Klauzula;
import pl.edu.pw.elka.maszyna.entity.algorytm.LaczenieKlauzul;
import pl.edu.pw.elka.maszyna.entity.algorytm.ListaKlauzul;
import pl.edu.pw.elka.maszyna.entity.algorytm.Literal;
import pl.edu.pw.elka.maszyna.wspolne.DrzewoWnioskowania;
import sun.reflect.generics.tree.Tree;

/**
 * @author lpielasz
 *
 */
public class ModulWnioskowania
{

	private final List<LaczenieKlauzul> laczeniaKlauzul = new ArrayList<LaczenieKlauzul>();
	
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
		// poki jeszcze sa rezolucje, to tworzymy nowe klauzule
		while (istniejeRezolucjowalnosc) {
			
			istniejeRezolucjowalnosc = false;
			for (ListIterator<Klauzula> gornyIterator = klauzule.listIterator(); 
					gornyIterator.hasNext();) {
				Klauzula gornaKlauzula = (Klauzula) gornyIterator.next();
				for (ListIterator<Klauzula> dolnyIterator = klauzule.listIterator(gornyIterator.nextIndex()); dolnyIterator
						.hasNext();) {
					Klauzula dolnaKlauzula = (Klauzula) dolnyIterator.next();
					
					boolean rezolucjowalna = sprawdzRezolucjowalnosc(gornaKlauzula, dolnaKlauzula);
					//jesli jest rezolucjowalna oraz ta rezolucja nie zaszla wczesniej
					if (rezolucjowalna && !zaszlaRezolucja(gornaKlauzula, dolnaKlauzula)) {
						istniejeRezolucjowalnosc = true;
//						
//						System.out.println(gornaKlauzula + " i " + dolnaKlauzula);
						Klauzula nowaKlauzula = gornaKlauzula.przeprowadzRezolucje(dolnaKlauzula);
						System.out.println(gornaKlauzula + " i " + dolnaKlauzula + " => " + nowaKlauzula);
						laczeniaKlauzul.add(new LaczenieKlauzul(gornaKlauzula, dolnaKlauzula, nowaKlauzula));
						if (nowaKlauzula == null) {
							System.out.println("Udowodniono");
							return true;
						}
						dodaneKlauzule.add(nowaKlauzula);
					}
				}
			}
			if (istniejeRezolucjowalnosc) {
				klauzule.addAll(dodaneKlauzule);
			}
		}

		System.out.println("Nieudowodniono (Nie ma juz co laczyc)");
		System.out.println("Wszystkie klauzule:");
		for (Klauzula klauzula : klauzule) {
			System.out.println(klauzula);
		}
		return false;
	}

	/**
	 * sprawdzenie czy z danymi klauzulami juz zachodzila rezolucja
	 * @param gornaKlauzula
	 * @param dolnaKlauzula
	 * @return
	 */
	private boolean zaszlaRezolucja(Klauzula gornaKlauzula,
			Klauzula dolnaKlauzula) {
		for (LaczenieKlauzul laczenieKlauzul : laczeniaKlauzul) {
			if (laczenieKlauzul.maRodzicow(gornaKlauzula, dolnaKlauzula)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sprawdzanie czy da sie przeprowadzic rezolucje na podanych klauzulach
	 * @param gornaKlauzula
	 * @param dolnaKlauzula
	 * @return
	 */
	private boolean sprawdzRezolucjowalnosc(Klauzula gornaKlauzula,
			Klauzula dolnaKlauzula) {
    	boolean rezolucjaZaszla = false;
    	for (Literal literalZGornejKlauzuli : gornaKlauzula.literaly) {
    		for (Literal literalZDolnejKlauzuli : dolnaKlauzula.literaly) {
//    			System.out.print("\n" + literalZGornejKlauzuli.toString() + " - " + literalZDolnejKlauzuli.toString());
    			if (literalZGornejKlauzuli.rezolucjowalny(literalZDolnejKlauzuli)) {
//    				System.out.print(" : rezolucja");
    				// TODO polaczyc klauzule
    				rezolucjaZaszla = true;
    			}
    		}	
		}
    	return rezolucjaZaszla;
	}


	public DrzewoWnioskowania pobierzDrzewoWnioskowania() {
        //TODO to powinno coś zwracać pewnie trzeba będzie jakiś nowy typ tu zadeklarować
		return new DrzewoWnioskowania(laczeniaKlauzul);
    }
}
