package pl.edu.pw.elka.maszyna.model;

import pl.edu.pw.elka.maszyna.entity.algorytm.Klauzula;
import pl.edu.pw.elka.maszyna.entity.algorytm.ListaKlauzul;
import pl.edu.pw.elka.maszyna.entity.parser.*;

import java.util.List;

/**
 *  Moduł przerabiający drzewa na listę klauzul
 *  @author Michał Toporowski
 */
public class KreatorKlauzul
{

    public KreatorKlauzul()
    {

    }


    /**
     * Przerabia wyrażenie w postaci drzewa na listę klauzul
     * @param las
     * @return ListaKlauzul
    */
    public ListaKlauzul drzewaNaKlauzule(final List<DrzewoParsowania> las)
    {
        //TODO zrobić
        likwidujRownowaznosc(las.get(0).getKorzenDrzewa());
        return null;
    }

    private Wezel likwidujRownowaznosc(Wezel wezel) {
        if(wezel.getClass() == WezelOperacja2Arg.class) {
            WezelOperacja2Arg operacja = (WezelOperacja2Arg)wezel;

            Wezel lewy = likwidujRownowaznosc(operacja.getLewy());
            Wezel prawy = likwidujRownowaznosc(operacja.getPrawy());

            operacja.setLewy(lewy);
            operacja.setPrawy(prawy);

            if(operacja.getDzialanie() == Dzialanie.ROWNOWAZN) {
                Wezel implikacja1 = new WezelOperacja2Arg(Dzialanie.JESLI_TO, lewy, prawy);
                Wezel implikacja2 = new WezelOperacja2Arg(Dzialanie.JESLI_TO, prawy, lewy);
                wezel = new WezelOperacja2Arg(Dzialanie.I, implikacja1, implikacja2);
            }


        }
        System.out.println("Likw. równoważn. :" + wezel.toString());
        return wezel;
    }
}
