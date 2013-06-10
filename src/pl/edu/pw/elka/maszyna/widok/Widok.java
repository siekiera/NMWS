package pl.edu.pw.elka.maszyna.widok;

import java.util.concurrent.BlockingQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import pl.edu.pw.elka.maszyna.wspolne.DrzewoWnioskowania;

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
//		pokazDrzewoWnioskowania();
	}

	public void pokazKomunikatOBledzieWParsowaniu(final String komunikat) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JOptionPane.showMessageDialog(okno, komunikat, "Parser", JOptionPane.ERROR_MESSAGE);				
			}
		});
		
	}
	
	public void pokazDrzewoWnioskowania(final DrzewoWnioskowania drzewoWnioskowania) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new OknoDrzewaWnioskowania(drzewoWnioskowania);
//				JOptionPane.showMessageDialog(okno, komunikat, "Parser", JOptionPane.ERROR_MESSAGE);				
			}
		});
		
	}

	public void pokazKomunikatONiepowodzeniu() {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JOptionPane.showMessageDialog(okno, "Wnioskowanie wykazało, że podana teza nie wynika logicznie z faktów.", "Niepowodzenie", JOptionPane.ERROR_MESSAGE);
			}
		});
		
	}
	
	
}
