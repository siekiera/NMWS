package pl.edu.pw.elka.maszyna;

import pl.edu.pw.elka.maszyna.kontroler.Kontroler;

/**
 * Najmądrzejsza maszyna wnioskująca świata
 */
public class Maszyna
{
	public static void main(String[] args)
	{
		Kontroler kontroler = new Kontroler();
		kontroler.petla();
	}
}
