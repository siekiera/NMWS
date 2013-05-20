package pl.edu.pw.elka.maszyna.model;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.elka.maszyna.entity.algorytm.ListaKlauzul;
import pl.edu.pw.elka.maszyna.entity.parser.DrzewoParsowania;
import pl.edu.pw.elka.maszyna.entity.algorytm.Klauzula;
import sun.security.krb5.internal.KrbErrException;

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
	 */
	public void rob(final String tekstWejsciowy)
	{
		//to powinno podzielić tekst na linie
		String zdania[] = tekstWejsciowy.split("\\r?\\n");
		//lista drzew
		List<DrzewoParsowania> las = new ArrayList<DrzewoParsowania>();
		
		for(String zdanie : zdania)
		{
			//parsujemy
			las.add(new DrzewoParsowania(zdanie));
			//TODO jeśli nie uda się sparsować, to pewnie powinno rzucać jakimś wyjątkiem
		}
		
		//przerabiamy drzewa na klauzule
        KreatorKlauzul kreatorKlauzul = new KreatorKlauzul();
		ListaKlauzul listaKlauzul = kreatorKlauzul.drzewaNaKlauzule(las);
		//wnioskujemy
        ModulWnioskowania modulWnioskowania = new ModulWnioskowania();
        modulWnioskowania.wnioskuj(listaKlauzul);
        modulWnioskowania.pobierzDrzewoWnioskowania();
		
		//TODO no i teraz to pewnie powinno coś zwrócić, tudzież zakomunikować, że się udało lub nie
		
	}
}
