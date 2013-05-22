package pl.edu.pw.elka.maszyna.model;

import pl.edu.pw.elka.maszyna.entity.algorytm.ListaKlauzul;
import pl.edu.pw.elka.maszyna.entity.parser.*;

import java.util.List;

/**
 *  Moduł przerabiający drzewa na listę klauzul
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
        for(DrzewoParsowania drzewo : las) {
            Wezel korzen = drzewo.getKorzenDrzewa();
            korzen = likwidujRownowaznosc(korzen);
            System.out.println("Likw. rownoważności: " + korzen.toString());
            korzen = likwidujImplikacje(korzen);
            System.out.println("Likw. implikacji: " + korzen);
            korzen = ograniczNegacje(korzen);
            System.out.println("Ograniczenie negacji: " + korzen);
        }
        return null;
    }

    private Wezel likwidujRownowaznosc(final Wezel staryWezel) {
        Wezel wezel = staryWezel;

        if(wezel.getClass() == WezelNegacja.class) {
            WezelNegacja operacja = (WezelNegacja)wezel;
            operacja.setDziecko(likwidujRownowaznosc(operacja.getDziecko()));
        }
        else if(wezel.getClass() == WezelOperacja2Arg.class) {
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
        return wezel;
    }

    private Wezel likwidujImplikacje(final Wezel staryWezel) {
        Wezel wezel = staryWezel;
        if(wezel.getClass() == WezelNegacja.class) {
            WezelNegacja operacja = (WezelNegacja)wezel;
            operacja.setDziecko(likwidujImplikacje(operacja.getDziecko()));
        }
        else if(wezel.getClass() == WezelOperacja2Arg.class) {
            WezelOperacja2Arg operacja = (WezelOperacja2Arg)wezel;

            Wezel lewy = likwidujImplikacje(operacja.getLewy());
            Wezel prawy = likwidujImplikacje(operacja.getPrawy());

            operacja.setLewy(lewy);
            operacja.setPrawy(prawy);

            if(operacja.getDzialanie() == Dzialanie.JESLI_TO) {

                wezel = new WezelOperacja2Arg(Dzialanie.LUB, neguj(lewy), prawy);
            }


        }
        return wezel;
    }

    /**
     * Ograniczenie negacji do literału
     * @param staryWezel
     * @return
     */
    private Wezel ograniczNegacje(final Wezel staryWezel) {
        Wezel wezel = staryWezel;

        if(wezel.getClass() == WezelNegacja.class) {
            Wezel dziecko = ((WezelNegacja)wezel).getDziecko();
            //literał - OK
            if(dziecko.getClass() == WezelPredykat.class) return wezel;

            //negacja - likwiduj 2 negacje
            else if(dziecko.getClass() == WezelNegacja.class) {
                Wezel wlasciwyWezel = ((WezelNegacja)dziecko).getDziecko();
                return ograniczNegacje(wlasciwyWezel);
            }
            //op 2 arg.
            else {
                WezelOperacja2Arg operacja = (WezelOperacja2Arg)dziecko;

                operacja.setLewy(ograniczNegacje(operacja.getLewy()));
                operacja.setPrawy(ograniczNegacje(operacja.getPrawy()));

                switch (operacja.getDzialanie())
                {
                    case I: //koniunkcja -> zanegowane alternatywy
                        return new WezelOperacja2Arg(Dzialanie.LUB, neguj(operacja.getLewy()), neguj(operacja.getPrawy()));
                    case LUB: //alternatywa -> zanegowane koniunkcje
                        return new WezelOperacja2Arg(Dzialanie.I, neguj(operacja.getLewy()), neguj(operacja.getPrawy()));
                    default:
                        throw new RuntimeException("Błąd - tu już nie powinno być innych działań");
                }

            }

        }
        else if(wezel.getClass() == WezelOperacja2Arg.class) {
            //op 2. arg - odpal dla lewego i prawego
            WezelOperacja2Arg operacja = (WezelOperacja2Arg)wezel;

            operacja.setLewy(ograniczNegacje(operacja.getLewy()));
            operacja.setPrawy(ograniczNegacje(operacja.getPrawy()));
            return operacja;
        }
        else { //predykat - nic nie rób
           return wezel;
        }
    }

    private Wezel neguj(final Wezel wezel) {
        if(wezel.getClass() == WezelNegacja.class) return ((WezelNegacja)wezel).getDziecko();
        else return new WezelNegacja(wezel);
    }
}
