package pl.edu.pw.elka.maszyna.entity.parser;

import java.util.HashMap;

import pl.edu.pw.elka.maszyna.wspolne.wyjatki.WyjatekParsowaniaNiepoprawnychDanych;
import pl.edu.pw.elka.maszyna.wspolne.wyjatki.WyjatekParsowaniaPustejLinii;

/**
 * Drzewo wykonujące parsowanie
 * @author Bogdan Szkoła
 */
public class DrzewoParsowania
{
    private static HashMap<Character, Integer> priorytety = new HashMap<Character, Integer>();
    private static HashMap<Character, Dzialanie> dzialania = new HashMap<Character, Dzialanie>();

    private Wezel korzenDrzewa = null;

    static
    {
        priorytety.put('-', 1);
        priorytety.put('&', 2);
        priorytety.put('|', 3);
        priorytety.put('>', 4);
        priorytety.put('=', 4);

        dzialania.put('&', Dzialanie.I);
        dzialania.put('|', Dzialanie.LUB);
        dzialania.put('>', Dzialanie.JESLI_TO);
        dzialania.put('=', Dzialanie.ROWNOWAZN);
        dzialania.put('-', Dzialanie.NIE);

    }
	
	
	/**
	 * Tworzy nowe drzewo, sparsowawszy wyrażenie w postaci napisu
	 * @param napis
	 * @throws WyjatekParsowaniaNiepoprawnychDanych 
	 * @throws WyjatekParsowaniaPustejLinii 
	 */
	public DrzewoParsowania(String napis) throws WyjatekParsowaniaNiepoprawnychDanych, WyjatekParsowaniaPustejLinii
	{
		this.parsuj(napis);
	}


    public Wezel getKorzenDrzewa()
    {
        return korzenDrzewa;
    }
	
	/**
	 * Parsuje wyrażenie w postaci napisu do drzewa
	 * @param napis
	 * @throws WyjatekParsowaniaNiepoprawnychDanych 
	 * @throws WyjatekParsowaniaPustejLinii 
	 */
	private void parsuj(String napis) throws WyjatekParsowaniaNiepoprawnychDanych, WyjatekParsowaniaPustejLinii
	{
        napis = pominBialeZnaki(napis);
        if (napis.length() == 0) throw new WyjatekParsowaniaPustejLinii();

        korzenDrzewa = budujDrzewo(napis);
        System.out.println(korzenDrzewa.toString());
    }

    private Wezel budujDrzewo(final String napis) throws WyjatekParsowaniaNiepoprawnychDanych {

        int index = szukajNajwyzszyPriorytet(napis);
        // jesli nie znaleziono zadnego operatora, nie zagniezdzonego w nawiasy
        if(index == -1)
        {
        	if(napis.length() == 0) throw new WyjatekParsowaniaNiepoprawnychDanych("Niezgodność argumentów operacji");
        	if(napis.charAt(napis.length() - 1) != ')') throw new WyjatekParsowaniaNiepoprawnychDanych("Brakuje nawiasu");
        	else if(napis.charAt(0) == '(')
        	{
        		return budujDrzewo(napis.substring(1, napis.length() - 1));
        	}
            WezelPredykat wezelPredykat = new WezelPredykat(napis);
            return wezelPredykat;
        }
        else {
            Dzialanie dzialanie = dzialania.get(napis.charAt(index));

            if(dzialanie == Dzialanie.NIE)
            {
                WezelNegacja wezel = new WezelNegacja(budujDrzewo(napis.substring(index + 1)));
                return wezel;
            }
            else
            {
                WezelOperacja2Arg wezel = new WezelOperacja2Arg(dzialanie,
                        budujDrzewo(napis.substring(0, index)),
                        budujDrzewo(napis.substring(index + 1)));
                return wezel;
            }

        }

    }

    private int szukajNajwyzszyPriorytet(String napis)
    {
        int indexOperatoraONajwyzszymPriorytecie = -1;
        int najwyzszyPriorytet = 0;
        int poziomNawiasow = 0;
        for(int i = napis.length() - 1; i >= 0; i--)
        {
            char symbol = napis.charAt(i);
            if(symbol == ')')
            {
                poziomNawiasow++;
            }
            else if(symbol == '(')
            {
                poziomNawiasow--;
            }
            else if(priorytety.containsKey(symbol) && poziomNawiasow == 0)
            {
                if(priorytety.get(symbol) > najwyzszyPriorytet)
                {
                    najwyzszyPriorytet = priorytety.get(symbol);
                    indexOperatoraONajwyzszymPriorytecie = i;
                }
            }
        }

        return indexOperatoraONajwyzszymPriorytecie;

    }

    private String pominBialeZnaki(String napis)
    {

        StringBuilder przetworzonyNapis = new StringBuilder("");
        for(int i = 0; i < napis.length(); i++)
        {
            if (!czyZnakBialy(napis.charAt(i))) {
                przetworzonyNapis.append(napis.charAt(i));
            }
        }
        return new String(przetworzonyNapis);

    }

    private boolean czyZnakBialy(char symbol)
    {
        return Character.isWhitespace(symbol);
    }
}
