package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.ArrayList;
import java.util.List;

public class ListaKlauzul {
	public List<Klauzula> klauzule;

    public ListaKlauzul()
    {
        klauzule = new ArrayList<Klauzula>();
    }
    
    public List<Klauzula> getKlauzule() {
		return klauzule;
	}
    
    @Override
    public String toString() {
    	String wynik = "";
    	for (Klauzula klauzula : klauzule) {
    		wynik += klauzula.toString() + "\n";
    	}
    	return wynik;
//    	return super.toString();
    }
    
}
