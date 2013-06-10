package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.HashMap;
import java.util.List;

/**
 * Literał, czyli predykat, bądź zaprzeczony predykat
 * @author Michał Toporowski
 */
public class Literal
{
    private final Predykat predykat;
    private final boolean zanegowany;

    public Literal(Predykat predykat, boolean zanegowany)
    {
        this.predykat = predykat;
        this.zanegowany = zanegowany;
    }

    public Literal(Literal literal) {
    	this.zanegowany = literal.zanegowany;
    	this.predykat = new Predykat(literal.predykat);
    }

	public Predykat getPredykat()
    {
        return predykat;
    }

    public boolean isZanegowany()
    {
        return zanegowany;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null) return false;
    	if (obj == this) return true;
    	Literal l = (Literal)obj;
    	if (l.zanegowany == this.zanegowany && l.predykat.equals(this.predykat)) return true;
    	else return false;
    }
    
    public boolean jestZaprzeczeniem(Literal innego){
    	
    	if(this.getPredykat().equals(innego.getPredykat()) && this.zanegowany == innego.isZanegowany()) return false;
    	else return true;
    	
    }
    
    @Override
    public String toString() {
    	String wynik = "";
    	if(zanegowany) {
    		wynik += "-";
    	}
    	wynik += predykat.toString(); 
    	return wynik;
    }

    /**
     * Sprawdza czy da sie przeprowadzic rezolucje
     * @param innyLiteral
     * @return
     */
	public boolean rezolucjowalny(Literal innyLiteral) {
		
		if (this.zanegowany == innyLiteral.zanegowany) {
			return false;
		}
		return this.predykat.rezolucjowalny(innyLiteral.predykat);
	}
	
    /**
     * Sprawdza czy da sie przeprowadzic rezolucje
     * @param innyLiteral
     * @return
     */
	public boolean dajacyPrawde(Literal innyLiteral) {
		
		if (this.zanegowany == innyLiteral.zanegowany) {
			return false;
		}
		return this.predykat.dajacyPrawde(innyLiteral.predykat);
	}

	/**
	 * Tworzy liste unifikacji
	 * @param literalZInnejKlauzuli
	 * @return
	 */
	public ListaUnifikacji stworzListeUnifikacji(Literal literalZInnejKlauzuli) {
		return this.predykat.stworzListeUnifikacji(literalZInnejKlauzuli.predykat);
	}

	public void przeprowadzUnifikacje(ListaUnifikacji listaUnifikacji) {
		predykat.przeprowadzUnifikacje(listaUnifikacji);
	}
    
}
