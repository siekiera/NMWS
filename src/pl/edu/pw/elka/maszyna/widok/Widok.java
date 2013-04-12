package pl.edu.pw.elka.maszyna.widok;

import java.util.concurrent.BlockingQueue;

/**
 * Klasa widoku w MVC
 * @author siekiera
 */
public class Widok
{
	private final OknoAplikacji okno;
	
	public Widok(final BlockingQueue<ZdarzenieWidoku> kolejkaZdarzen)
	{
		this.okno = new OknoAplikacji(kolejkaZdarzen);
	}
	
	
}
