package pl.edu.pw.elka.maszyna.wspolne.wyjatki;

import java.util.List;

import pl.edu.pw.elka.maszyna.entity.algorytm.ListaUnifikacji;
import pl.edu.pw.elka.maszyna.entity.algorytm.Literal;
import pl.edu.pw.elka.maszyna.entity.algorytm.Unifikacja;

/**
 * 
 * @author bogdan
 * Stworzony dla unifikacji (zamiany zmiennej w Predykacie na inna Zmienna lub Stala) 
 */
public class WyjatekRezolucji extends Exception {
	
	private final ListaUnifikacji listaUnifikacji;
	private final Literal literalZGornejKlauzuli;
	private final Literal literalZDolnejKlauzuli;
	
	public WyjatekRezolucji(Literal literalZGornejKlauzuli, Literal literalZDolnejKlauzuli, ListaUnifikacji listaUnifikacji) {
		this.listaUnifikacji = listaUnifikacji;
		this.literalZGornejKlauzuli = literalZGornejKlauzuli;
		this.literalZDolnejKlauzuli = literalZDolnejKlauzuli;
	}

	public ListaUnifikacji getListaUnifikacji() {
		return listaUnifikacji;
	}
	
	public Literal getLiteralZGornejKlauzuli() {
		return literalZGornejKlauzuli;
	}
	
	public Literal getLiteralZDolnejKlauzuli() {
		return literalZDolnejKlauzuli;
	}
	
}
