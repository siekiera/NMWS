package pl.edu.pw.elka.maszyna.widok;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Główne okno aplikacji
 * @author Michał Toporowski
 * @author Bogdan Szkoła
 * @author Łukasz Pielaszek
 */
class OknoAplikacji extends JFrame
{
	private static final long serialVersionUID = -5580453288877473242L;
	private static final Dimension ROZMIAR = new Dimension(500, 350);
	
	private final BlockingQueue<ZdarzenieWidoku> kolejkaZdarzen;
	
	
	private JTextArea poleWprowadzania;
	private JButton przycisk;
	
	public OknoAplikacji(final BlockingQueue<ZdarzenieWidoku> kolejkaZdarzen)
	{
		this.kolejkaZdarzen = kolejkaZdarzen;
		
		inicjujWidok();
		inicjujSluchacze();
	}


	private void inicjujSluchacze()
	{
		//TODO
		przycisk.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//wyślij tekst do kontrolera (umieść nowe zdarzenie w kolejce)
				String tekst = poleWprowadzania.getText();
				try
				{
					kolejkaZdarzen.put(new ZdarzenieWidoku(tekst));
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});
	}


	private void inicjujWidok()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(ROZMIAR);
		setTitle("Najmądrzejsza maszyna wnioskująca świata");
		
		poleWprowadzania = new JTextArea();
		przycisk = new JButton("Rób!!!");
		
		add(poleWprowadzania, BorderLayout.CENTER);
		add(przycisk, BorderLayout.SOUTH);
		setVisible(true);
	}

}
