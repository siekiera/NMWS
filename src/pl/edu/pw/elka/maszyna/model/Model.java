package pl.edu.pw.elka.maszyna.model;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.elka.maszyna.entity.Drzewo;
import pl.edu.pw.elka.maszyna.entity.Klauzula;

/**
 * Model MVC
 * @author siekiera
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
		List<Drzewo> las = new ArrayList<Drzewo>(); 
		
		for(String zdanie : zdania)
		{
			//parsujemy
			las.add(new Drzewo(zdanie));
			//TODO jeśli nie uda się sparsować, to pewnie powinno rzucać jakimś wyjątkiem
		}
		
		//przerabiamy drzewa na klauzule
		List<Klauzula> klauzule = drzewaNaKlauzule(las);
		//wnioskujemy
		wnioskuj(klauzule);
		
		//TODO no i teraz to pewnie powinno coś zwrócić, tudzież zakomunikować, że się udało lub nie
		
	}
	
	/**
	 * Przerabia wyrażenie w postaci drzewa na listę klauzul
	 * @param las
	 * @return
	 */
	private List<Klauzula> drzewaNaKlauzule(final List<Drzewo> las)
	{
		//TODO zrobić
		return null;
	}
	
	
	/**
	 * Nasz algorytm
	 * @param listaKlauzul
	 */
	private void wnioskuj(final List<Klauzula> listaKlauzul)
	{
		//TODO zrobić
		//TODO może to też powinno coś zwracać, na razie nie wiem co
	}
}
