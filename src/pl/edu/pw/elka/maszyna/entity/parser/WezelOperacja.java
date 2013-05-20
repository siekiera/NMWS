package pl.edu.pw.elka.maszyna.entity.parser;


/**
 * Węzeł, który jest operacją
 */
public abstract class WezelOperacja extends Wezel
{
    protected final Dzialanie dzialanie;

    Dzialanie getDzialanie()
    {
        return dzialanie;
    }

    protected WezelOperacja(Dzialanie dzialanie)
    {
        this.dzialanie = dzialanie;
    }
}
