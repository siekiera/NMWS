package pl.edu.pw.elka.maszyna.entity.parser;

import pl.edu.pw.elka.maszyna.wspolne.wyjatki.WyjatekParsowaniaNiepoprawnychDanych;


/**
 * Węzeł, który jest liściem
 */
public class WezelPredykat extends Wezel
{
    //public Predykat pred;
    private final String predykat;

    public WezelPredykat(String predykat) throws WyjatekParsowaniaNiepoprawnychDanych
    {
    	zatwierdzPoprawnoscPredykatu(predykat);
        this.predykat = predykat;
    }

    private void zatwierdzPoprawnoscPredykatu(String predykat) throws WyjatekParsowaniaNiepoprawnychDanych {
    	
    	int indeksNawiasuOtwierajacago = predykat.indexOf('(');
    	if(indeksNawiasuOtwierajacago == -1) {
    		throw new WyjatekParsowaniaNiepoprawnychDanych("Predykat \"" + predykat + "\" nie jest poprawny");
    	}
    	else {
    		for(int i = 0; i < indeksNawiasuOtwierajacago; i++) {
    			if(!Character.isUpperCase(predykat.charAt(i))) {
    				throw new WyjatekParsowaniaNiepoprawnychDanych("Nazwa predykatu \"" + predykat + "\" zawiera małą literę albo niepoprawny znak");
    			}
    		}
    		
    		zatwierdzPoprawnoscArgumentow(predykat.substring(indeksNawiasuOtwierajacago + 1, predykat.length() - 1));
    	}

	}

	private void zatwierdzPoprawnoscArgumentow(String argumentyPredykatu) throws WyjatekParsowaniaNiepoprawnychDanych {
		if(argumentyPredykatu.length() == 0) {
			return;
		}
		
		int indeksPierwszegoPrzecinku = argumentyPredykatu.indexOf(',');
		
		while(indeksPierwszegoPrzecinku != -1) {
			String argument = argumentyPredykatu.substring(0, indeksPierwszegoPrzecinku);
			zatwierdzPoprawnoscArgumentu(argument);
			
			argumentyPredykatu = argumentyPredykatu.substring(indeksPierwszegoPrzecinku + 1);
			indeksPierwszegoPrzecinku = argumentyPredykatu.indexOf(',');
		}
		zatwierdzPoprawnoscArgumentu(argumentyPredykatu);
		
	}

	private void zatwierdzPoprawnoscArgumentu(String argument) throws WyjatekParsowaniaNiepoprawnychDanych {
		if(argument.length() == 0) {
			throw new WyjatekParsowaniaNiepoprawnychDanych("Opuszczono argument");
		}
		
		boolean czyZmienna = sprawdzCzyZmienna(argument);
		boolean czyStala = sprawdzCzyStala(argument);
		
		if(!czyZmienna && !czyStala) {
			throw new WyjatekParsowaniaNiepoprawnychDanych("Argument \"" + argument + "\" nie jest poprawny");
		}
	}

	private boolean sprawdzCzyStala(String argumentPredykatu) {
		if(!Character.isUpperCase(argumentPredykatu.charAt(0))) {
			return false;
		}
		for(int i = 1; i < argumentPredykatu.length(); i++) {
			if(!Character.isLowerCase(argumentPredykatu.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean sprawdzCzyZmienna(String argumentPredykatu) {
		for(int i = 0; i < argumentPredykatu.length(); i++) {
			if(!Character.isLowerCase(argumentPredykatu.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public String getPredykat()
    {
        return predykat;
    }
    
    @Override
    /**
     * zwraca samą nazwę predykatu (bez nawiasów i argumentów)
     * do pobrania pełnego predykatu w postaci stringa należy użyć getPredykat
     */
    public String toString() {
    	return predykat.substring(0, predykat.indexOf('('));
    }
    
}