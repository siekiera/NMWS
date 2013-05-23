package pl.edu.pw.elka.maszyna.model;

import java.util.List;

import pl.edu.pw.elka.maszyna.entity.algorytm.Klauzula;
import pl.edu.pw.elka.maszyna.entity.algorytm.ListaKlauzul;

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
		return false;
	}

    public void pobierzDrzewoWnioskowania() {
        //TODO to powinno coś zwracać pewnie trzeba będzie jakiś nowy typ tu zadeklarować
    }
}
