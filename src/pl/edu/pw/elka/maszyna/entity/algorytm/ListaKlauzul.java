package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.ArrayList;
import java.util.Iterator;
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
    
    
    /**
     * Usowa klauzule, które maj¹ w sobie literal i zaprzeczony ten sam literal. To oznacza prawdê i taka klauzula nic nie wnosi.
     */
    public void usunPrawdziweKlauzule(){
    	
    	Iterator klauzuleIterator = klauzule.iterator();
    	
    	while(klauzuleIterator.hasNext()){
    		Klauzula element = (Klauzula)klauzuleIterator.next();
    		
    		outerloop:
    		for(Object zew: element.literaly){
    			for(Object wew: element.literaly){
    				Literal lLiteral = (Literal)zew;
    				Literal pLiteral = (Literal)wew;
    				if(lLiteral.rezolucjowalny(pLiteral)){
    					klauzule.remove(element);
    					klauzuleIterator = klauzule.iterator();
    					break outerloop;
    				}
    			}
    		}
    	}
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
