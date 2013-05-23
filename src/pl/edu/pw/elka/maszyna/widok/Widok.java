package pl.edu.pw.elka.maszyna.widok;

import java.util.concurrent.BlockingQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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

	public void pokazKomunikatOBledzieWParsowaniu(final String komunikat) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JOptionPane.showMessageDialog(okno, komunikat, "Parser", JOptionPane.ERROR_MESSAGE);				
			}
		});
		
	}
	
	
}
