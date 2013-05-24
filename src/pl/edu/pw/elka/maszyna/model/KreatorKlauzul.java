package pl.edu.pw.elka.maszyna.model;

import pl.edu.pw.elka.maszyna.entity.algorytm.Klauzula;
import pl.edu.pw.elka.maszyna.entity.algorytm.ListaKlauzul;
import pl.edu.pw.elka.maszyna.entity.algorytm.Literal;
import pl.edu.pw.elka.maszyna.entity.algorytm.Predykat;
import pl.edu.pw.elka.maszyna.entity.parser.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        ListaKlauzul listaKlauzul = new ListaKlauzul();

        for(DrzewoParsowania drzewo : las) {
            Wezel korzen = drzewo.getKorzenDrzewa();
            korzen = likwidujRownowaznosc(korzen);
            System.out.println("Likw. rownoważności: " + korzen.toString());
            korzen = likwidujImplikacje(korzen);
            System.out.println("Likw. implikacji: " + korzen);
            korzen = ograniczNegacje(korzen);
            System.out.println("Ograniczenie negacji: " + korzen);
            korzen = naKoniunkcjeAlternatyw(korzen);
            System.out.println("Na koniunkcję alternatyw: " + korzen + "\n");

            listaKlauzul.klauzule.addAll(drzewoNaKlauzule(korzen));
            System.out.println("Lista klauzul:\n" + listaKlauzul.toString());
        }
        return listaKlauzul;
    }

    /**
     * Likwiduje z drzewa równoważności, zamieniając na koniunkcję implikacji
     * @param staryWezel
     * @return
     */
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

    /**
     * Likwiduje z drzewa implikację wg formuły (X=>Y) == (-X|Y)
     * @param staryWezel
     * @return
     */
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

                Wezel nowyLewy = ograniczNegacje(neguj(operacja.getLewy()));
                Wezel nowyPrawy = ograniczNegacje(neguj(operacja.getPrawy()));

                switch (operacja.getDzialanie())
                {
                    case I: //koniunkcja -> zanegowane alternatywy
                        return new WezelOperacja2Arg(Dzialanie.LUB, nowyLewy, nowyPrawy);
                    case LUB: //alternatywa -> zanegowane koniunkcje
                        return new WezelOperacja2Arg(Dzialanie.I, nowyLewy, nowyPrawy);
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

    /**
     * Przekształca formułę na koniunkcję alternatyw
     * @param wezel
     * @return wynikowa koniunkcja lub literał (WezelNegacja/WezelPredykat)
     */
    private Wezel naKoniunkcjeAlternatyw(final Wezel wezel) {

        //musimy coś robić tylko przy operacji 2-arg
        if(wezel.getClass() == WezelOperacja2Arg.class) {
            WezelOperacja2Arg operacja = (WezelOperacja2Arg)wezel;
            switch (operacja.getDzialanie()) {
                case LUB: //alternatywa działań
                    return rozbijAlternatywe(operacja.getLewy(), operacja.getPrawy());
                case I:
                    operacja.setLewy(naKoniunkcjeAlternatyw(operacja.getLewy()));
                    operacja.setPrawy(naKoniunkcjeAlternatyw(operacja.getPrawy()));
                    return operacja;
                default:
                    throw new RuntimeException("Błąd - tu już nie powinno być innych działań");
            }
        }

        return wezel;
    }

    /**
     * Rozbija alternatywę wg formuły
     * (X&Y)|(Z&T) <==> (X|Z) & (Y|Z) & (X|T) & (Y|T)
     * @param lewy - (X&Y)
     * @param prawy - (Z&T)
     * @return wynikowa koniunkcja
     */
    private WezelOperacja2Arg rozbijAlternatywe(final Wezel lewy, final Wezel prawy) {

        if(lewy.getClass() == WezelOperacja2Arg.class) {
            WezelOperacja2Arg rozbicieLewej = rozbijAlternatyweJednostronna((WezelOperacja2Arg)lewy, prawy);
            if(rozbicieLewej.getDzialanie() == Dzialanie.I) return rozbicieLewej;
        }
        if(prawy.getClass() == WezelOperacja2Arg.class) {
            return rozbijAlternatyweJednostronna((WezelOperacja2Arg)prawy, lewy);
        }
        //żaden - doszliśmy do poziomu literałów - alternatywa pozostaje
        return new WezelOperacja2Arg(Dzialanie.LUB, lewy, prawy);
    }

    /**
     * Rozbija jednostronną alternatywę wg wzoru
     * (X&Y)|Z <=> (X|Z)&(Y|Z)
     * @param operacja
     * @param drugiWezel
     * @return wynikowa koniunkcja
     */
    private WezelOperacja2Arg rozbijAlternatyweJednostronna(final WezelOperacja2Arg operacja, final Wezel drugiWezel) {

        WezelOperacja2Arg koniunkcja;

        switch(operacja.getDzialanie()) {
            case I:
                koniunkcja = operacja;
                break;
            case LUB:
                koniunkcja = rozbijAlternatywe(operacja.getLewy(), operacja.getPrawy());
                if(koniunkcja.getDzialanie() == Dzialanie.LUB) {//tylko jeśli dotarliśmy do literałów
                    return new WezelOperacja2Arg(Dzialanie.LUB, operacja, drugiWezel);
                }
                break;
            default:
                throw new RuntimeException("Błąd - tu już nie powinno być innych działań");
        }

        Wezel nowyDrugiWezel = naKoniunkcjeAlternatyw(drugiWezel);

        WezelOperacja2Arg alt1 = new WezelOperacja2Arg(Dzialanie.LUB, koniunkcja.getLewy(), nowyDrugiWezel);
        WezelOperacja2Arg alt2 = new WezelOperacja2Arg(Dzialanie.LUB, koniunkcja.getPrawy(), nowyDrugiWezel);

        alt1 = rozbijAlternatywe(alt1.getLewy(), alt1.getPrawy());
        alt2 = rozbijAlternatywe(alt2.getLewy(), alt2.getPrawy());

        return new WezelOperacja2Arg(Dzialanie.I, alt1, alt2);
    }

    /**
     * Przerabia drzewo w postaci koniunkcji alternatyw na klauzulę(-e)
     * @param wezel
     * @return
     */
    private List<Klauzula> drzewoNaKlauzule(Wezel wezel) {

        if(wezel.getClass() == WezelOperacja2Arg.class) {
            WezelOperacja2Arg operacja = (WezelOperacja2Arg)wezel;

            //koniunkcja -> lista klauzul (rozdzielenie)
            if(operacja.getDzialanie() == Dzialanie.I) {
                List<Klauzula> lista1 = drzewoNaKlauzule(operacja.getLewy());
                List<Klauzula> lista2 = drzewoNaKlauzule(operacja.getPrawy());
                lista1.addAll(lista2);
                return lista1;
            }
        }

        //alternatywa lub terminalny
        List<Klauzula> lista = new ArrayList<Klauzula>();
        lista.add(alternatywyNaKlauzule(wezel));
        return lista;
    }

    /**
     *
     * @param wezel
     * @return
     */
    private Klauzula alternatywyNaKlauzule(Wezel wezel) {
        if(wezel.getClass() == WezelOperacja2Arg.class)
        {
            //alternatywa
            WezelOperacja2Arg alternatywa = (WezelOperacja2Arg)wezel;
            if(alternatywa.getDzialanie() != Dzialanie.LUB) throw new RuntimeException("Bład");

            Klauzula k1 = alternatywyNaKlauzule(alternatywa.getLewy());
            Klauzula k2 = alternatywyNaKlauzule(alternatywa.getPrawy());
            k1.dodaj(k2);
            return k1;
        } else { //węzeł terminalny
            Set<Literal> literalSet = new HashSet<Literal>();
            literalSet.add(wezelKoncowyNaLiteral(wezel));
            return new Klauzula(literalSet);
        }
    }

    /**
     * @param wezel - WezelNegacja, bądź WezelPredykat
     * @return
     */
    private Literal wezelKoncowyNaLiteral(Wezel wezel) {
        WezelPredykat wezelPredykat;
        boolean zaprzeczony;
        if(wezel.getClass() == WezelNegacja.class) {
            wezelPredykat = (WezelPredykat)((WezelNegacja)wezel).getDziecko();
            zaprzeczony = true;
        } else {
            wezelPredykat = (WezelPredykat)wezel;
            zaprzeczony = false;
        }

        return new Literal(Predykat.parsuj(wezelPredykat.getPredykat()), zaprzeczony);
    }

    /**
     * Neguje węzeł
     * @param wezel
     * @return
     */
    private Wezel neguj(final Wezel wezel) {
        if(wezel.getClass() == WezelNegacja.class) return ((WezelNegacja)wezel).getDziecko();
        else return new WezelNegacja(wezel);
    }
}
