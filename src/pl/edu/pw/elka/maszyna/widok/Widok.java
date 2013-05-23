package pl.edu.pw.elka.maszyna.widok;

import java.util.concurrent.BlockingQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

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

	public void pokazKomunikatOBledzieWParsowaniu(String komunikat) {
		JOptionPane.showMessageDialog(okno, komunikat, "Parser", JOptionPane.ERROR_MESSAGE);
	}
	
	
}
