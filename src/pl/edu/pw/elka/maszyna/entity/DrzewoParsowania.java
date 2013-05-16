package pl.edu.pw.elka.maszyna.entity;

import pl.edu.pw.elka.maszyna.entity.wezel.*;

import java.util.HashMap;

public class DrzewoParsowania
{
    public enum LogicznyByt {ZMIENNA, PREDYKAT, SUMA, PRZECIECIE, IMPLIKACJA, ROWNOWAZNOSC};

    private static HashMap<Character, Integer> priorytety = new HashMap<Character, Integer>();
    private static HashMap<Character, Dzialanie> dzialania = new HashMap<Character, Dzialanie>();

    //	private Operacja nieistniajacaOperacja = new Operacja(null);
    //private Wezel aktualnyWezel;
    private Wezel korzenDrzewa = null;



	//private Wezel korzen;
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
	 */
	public DrzewoParsowania(String napis)
	{
		this.parsuj(napis);
	}
	
	/**
	 * Parsuje wyrażenie w postaci napisu do drzewa
	 * @param napis
	 */
	private void parsuj(String napis)
	{
        napis = pominBialeZnaki(napis);

        korzenDrzewa = budujDrzewo(napis);
        System.out.println(korzenDrzewa.toString());
    }

    private Wezel budujDrzewo(final String napis) {

        int index = szukajNajwyzszyPriorytet(napis);
        if(index == -1)
        {
        	if(napis.length() > 0 && napis.charAt(0) == '(')
        	{
        		return budujDrzewo(napis.substring(1, napis.length() - 1));
        	}
            WezelPredykat wezelPredykat = new WezelPredykat(napis);
            System.out.println("Nie ma operatorow: " + napis);
            return wezelPredykat;
        }
        else {
            Dzialanie dzialanie = dzialania.get(napis.charAt(index));

            if(dzialanie == Dzialanie.NIE)
            {
                WezelOperacja1Arg wezel = new WezelOperacja1Arg(dzialanie,
                       budujDrzewo(napis.substring(index + 1)));
                System.out.println(index + ": " + napis.charAt(index));
                return wezel;
            }
            else
            {
                WezelOperacja2Arg wezel = new WezelOperacja2Arg(dzialanie,
                        budujDrzewo(napis.substring(0, index)),
                        budujDrzewo(napis.substring(index + 1)));
                System.out.println(index + ": " + napis.charAt(index));
                return wezel;
            }

        }

    }

    private int szukajNajwyzszyPriorytet(String napis)
    {
        int indexOperatoraONajwyzszymPriorytecie = -1;
        int najwyzszyPriorytet = 0;
        int poziomNawiasow = 0;
        for(int i = 0; i < napis.length(); i++)
        {
            char symbol = napis.charAt(i);
            if(symbol == '(')
            {
                poziomNawiasow++;
            }
            else if(symbol == ')')
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
