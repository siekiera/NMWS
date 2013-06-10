package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Predykat
 */
public class Predykat 
{
	/** nazwa predykatu - drukowanymi literami*/
	private final String nazwa;
	private final Argument[] argumenty;
	public Predykat(String nazwa, Argument[] argumenty)
	{
		this.nazwa = nazwa;
		this.argumenty = argumenty;
	}

    public Predykat(Predykat predykat) {
    	this.nazwa = predykat.nazwa;
    	this.argumenty = new Argument[predykat.argumenty.length];
    	for (int i = 0; i < argumenty.length; i++) {
			this.argumenty[i] = new Argument(predykat.argumenty[i]);
		}
    
    }

	/**
     * "Factory method" - ma sparsować napis i wyprodukować predykat
     * @param napis
     * @return
     */
    public static Predykat parsuj(String napis) {
        int poczNawiasu = napis.indexOf('(');
        String nazwa = napis.substring(0, poczNawiasu);
        String argumentStr = napis.substring(poczNawiasu + 1, napis.length() - 1);

        String[] argSplit = argumentStr.split(",");

        if (argSplit[0].equals("")) {
        	return new Predykat(nazwa, new Argument[0]);
        }
        else {
		    Argument[] argumenty = new Argument[argSplit.length];
		
		    for(int i = 0; i < argSplit.length; i++) {
		        argumenty[i] = new Argument(argSplit[i]);
		    }
		
		    return new Predykat(nazwa, argumenty);
        }
    }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if(this == obj) return true;
		Predykat o = (Predykat)obj;
		if (!(obj instanceof Predykat)) return false;
		else if(!o.nazwa.equals(this.nazwa)) return false;
		
		int i, j;
		i = o.argumenty.length;
		j = this.argumenty.length;
		if(i != j) return false;
		
		for(int a = 0; a<i; a++){
			if(!o.argumenty[a].equals(this.argumenty[a])) return false;
		}

		return true;
	}
	
	@Override
	public String toString() {
		String wynik = nazwa + "(";
		for(int i = 0; i < argumenty.length; i++) {
			wynik += argumenty[i].toString();

			if(i != argumenty.length - 1) {
				wynik += ",";
			}
		}
		wynik += ")";
		return wynik;
	}

	/**
	 * Sprawdza czy da sie przeprowadzic rezolucje
	 * @param innyPredykat
	 * @return
	 */
	public boolean rezolucjowalny(Predykat innyPredykat) {
		
		if (!this.nazwa.equals(innyPredykat.nazwa)) {
			return false;
		}
		
		if (this.argumenty.length != innyPredykat.argumenty.length) {
			return false;
		}
		
		if (this.argumenty.length == 0) {
			return true;
		}
		
		for (int i = 0; i < this.argumenty.length; i++) {
			if (this.argumenty[i].czyStala() && innyPredykat.argumenty[i].czyStala()) {
				if (!this.argumenty[i].equals(innyPredykat.argumenty[i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Tworzy liste unifikacji
	 * @param innyPredykat
	 * @return
	 */
	public ListaUnifikacji stworzListeUnifikacji(Predykat innyPredykat) {
		List<Unifikacja> listaUnifikacji = new ArrayList<Unifikacja>();
		for (int i = 0; i < this.argumenty.length; i++) {
			// jesli nazwy argumentow sa rozne - zachodzi unifikacja
			if (!this.argumenty[i].nazwa.equals(innyPredykat.argumenty[i].nazwa)) {
				listaUnifikacji.add(new Unifikacja(this.argumenty[i].nazwa, innyPredykat.argumenty[i].nazwa));
			}
		}
		return new ListaUnifikacji(listaUnifikacji);
	}

	public void przeprowadzUnifikacje(ListaUnifikacji listaUnifikacji) {
		for (int i = 0; i < this.argumenty.length; i++) {
			for (Unifikacja unifikacja : listaUnifikacji.getListaUnifikacji()) {
				if (argumenty[i].nazwa.equals(unifikacja.getNazwaArgumentuPierwszegoPredykatu())) {
					argumenty[i].nazwa = unifikacja.getNazwaArgumentuDrugiegoPredykatu();
				}
			}
		}
	}

	public boolean dajacyPrawde(Predykat innyPredykat) {
		if (!this.nazwa.equals(innyPredykat.nazwa)) {
			return false;
		}
		
		if (this.argumenty.length != innyPredykat.argumenty.length) {
			return false;
		}
		
		if (this.argumenty.length == 0) {
			return true;
		}
		
		for (int i = 0; i < this.argumenty.length; i++) {
			if (this.argumenty[i].czyStala() && innyPredykat.argumenty[i].czyStala()) {
				if (!this.argumenty[i].equals(innyPredykat.argumenty[i])) {
					return false;
				}
			}
			
			if ((this.argumenty[i].czyZmienna() && innyPredykat.argumenty[i].czyStala()) ||
					(this.argumenty[i].czyStala() && innyPredykat.argumenty[i].czyZmienna())) {
					return false;
			}
		}
		return true;
	}
	
}

