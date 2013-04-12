package pl.edu.pw.elka.maszyna.widok;

/**
 * To będzie wysyłane z widoku do kontrolera, jeśli coś zostanie naciśnięte
 * @author siekiera
 */
public class ZdarzenieWidoku
{
	private final String tekst;

	public ZdarzenieWidoku(String tekst)
	{
		this.tekst = tekst;
	}

	public String getTekst()
	{
		return tekst;
	}
	
}
