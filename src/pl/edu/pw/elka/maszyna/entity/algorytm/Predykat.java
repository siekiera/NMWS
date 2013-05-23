package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.ArrayList;
import java.util.List;

/**
 * Predykat
 */
public class Predykat 
{
	/** nazwa predykatu - drukowanymi literami*/
	private final String nazwa;
	private final List<Argument> argumenty;
	public Predykat(String nazwa, List<Argument> argumenty)
	{
		super();
		this.nazwa = nazwa;
		this.argumenty = argumenty;
	}

    /**
     * "Factory method" - ma sparsować napis i wyprodukować predykat
     * @param napis
     * @return
     */
    public static Predykat parsuj(String napis) {
        //TODO trzeba wyodrębnić argumenty - na razie wrzyca wszystko do nazwy
        return new Predykat(napis, new ArrayList<Argument>());
    }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if(this == obj) return true;
		Predykat o = (Predykat)obj;
		if (!(obj instanceof Predykat)) return false;
		else if(!o.nazwa.equals(this.nazwa)) return false;
		
		int i, j;
		i = o.argumenty.size();
		j = this.argumenty.size();
		if(i != j) return false;
		
		for(int a = 0; a<i; a++){
			if(!o.argumenty.get(a).equals(this.argumenty.get(a))) return false;
		}

		return true;
	}
}

