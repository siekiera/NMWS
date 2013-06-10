package pl.edu.pw.elka.maszyna.model;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.elka.maszyna.entity.algorytm.ListaKlauzul;
import pl.edu.pw.elka.maszyna.entity.parser.DrzewoParsowania;
import pl.edu.pw.elka.maszyna.wspolne.DrzewoWnioskowania;
import pl.edu.pw.elka.maszyna.wspolne.wyjatki.WyjatekParsowaniaNiepoprawnychDanych;
import pl.edu.pw.elka.maszyna.wspolne.wyjatki.WyjatekParsowaniaPustejLinii;

/**
 * Model MVC
 * @author Michał Toporowski
 */
public class Model
{
	
	public Model()
	{
		
	}
	
	/**
	 * Funkcja, która ze zdań w postaci stringów wywnioskuje o co chodzi
	 * @param tekstWejsciowy
	 * @throws WyjatekParsowaniaNiepoprawnychDanych 
	 */
	public DrzewoWnioskowania rob(final String tekstWejsciowy) throws WyjatekParsowaniaNiepoprawnychDanych
	{
		//to powinno podzielić tekst na linie
		String zdania[] = tekstWejsciowy.split("\\r?\\n");
		//lista drzew
		List<DrzewoParsowania> las = new ArrayList<DrzewoParsowania>();
		
		for(String zdanie : zdania)
		{
			//parsujemy
			if(zdanie.length() != 0) {
				try {
					las.add(new DrzewoParsowania(zdanie));
				} catch (WyjatekParsowaniaPustejLinii e) {
					//TODO mozna podac jakis komunikat (niekoniecznie)
				}
			}
			//TODO jeśli nie uda się sparsować, to pewnie powinno rzucać jakimś wyjątkiem
		}
		
		//przerabiamy drzewa na klauzule
        KreatorKlauzul kreatorKlauzul = new KreatorKlauzul();
		ListaKlauzul listaKlauzul = kreatorKlauzul.drzewaNaKlauzule(las);
		//wnioskujemy
        ModulWnioskowania modulWnioskowania = new ModulWnioskowania();
        if (modulWnioskowania.wnioskuj(listaKlauzul)) {
        	return modulWnioskowania.pobierzDrzewoWnioskowania();
        }
        else {
        	return null;
        }
		
		//TODO no i teraz to pewnie powinno coś zwrócić, tudzież zakomunikować, że się udało lub nie
		
	}
}
