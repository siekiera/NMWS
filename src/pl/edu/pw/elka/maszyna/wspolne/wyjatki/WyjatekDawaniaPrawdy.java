package pl.edu.pw.elka.maszyna.wspolne.wyjatki;

import pl.edu.pw.elka.maszyna.entity.algorytm.Literal;

public class WyjatekDawaniaPrawdy extends Exception {

	private Literal literalZGornejKlauzuli;
	private Literal literalZDolnejKlauzuli;
	
	public WyjatekDawaniaPrawdy(Literal literalZGornejKlauzuli,
			Literal literalZDolnejKlauzuli) {
		this.literalZDolnejKlauzuli = literalZDolnejKlauzuli;
		this.literalZGornejKlauzuli = literalZGornejKlauzuli;
	}
	
	public Literal getLiteralZDolnejKlauzuli() {
		return literalZDolnejKlauzuli;
	}
	
	public Literal getLiteralZGornejKlauzuli() {
		return literalZGornejKlauzuli;
	}

}
