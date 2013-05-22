package pl.edu.pw.elka.maszyna.entity.parser;


/**
 * Węzeł, który jest operacją
 */
public abstract class WezelOperacja extends Wezel
{
    protected Dzialanie dzialanie;

    public Dzialanie getDzialanie()
    {
        return dzialanie;
    }

    public void setDzialanie(Dzialanie dzialanie)
    {
        this.dzialanie = dzialanie;
    }

    protected WezelOperacja(Dzialanie dzialanie)
    {
        this.dzialanie = dzialanie;
    }
}
