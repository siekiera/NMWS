package pl.edu.pw.elka.maszyna.kontroler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pl.edu.pw.elka.maszyna.model.Model;
import pl.edu.pw.elka.maszyna.widok.Widok;
import pl.edu.pw.elka.maszyna.widok.ZdarzenieWidoku;


/**
 * Kontroler - MVC
 * @author siekiera
 */
public class Kontroler
{
	/** widok */
	private final Widok widok;
	/** model */
	private final Model model;
	/** kolejkaZdarzen */
	private final BlockingQueue<ZdarzenieWidoku> kolejkaZdarzen;

	/**
	 * Konstruktor klasy Kontroler
	 */
	public Kontroler()
	{
		this.kolejkaZdarzen = new LinkedBlockingQueue<ZdarzenieWidoku>();		
		
		this.widok = new Widok(this.kolejkaZdarzen);
		this.model = new Model();
	}

	/**
	 * Główna pętla obsługująca zdarzenia przesyłane z widoku
	 */
	public void petla()
	{
		ZdarzenieWidoku z;
		while(true)
		{
			try
			{
				//weź zdarzenie
				z = kolejkaZdarzen.take();
				//odpal funkcję modelu robiącą to, co chcemy
				model.rob(z.getTekst());
				//TODO jak już wywnioskujemy, to przydałoby się zaktualizować widok
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
	}
}
