package pl.edu.pw.elka.maszyna.entity.wezel;


/**
 *
 */
public class WezelOperacja1Arg extends WezelOperacja
{
    private final Wezel dziecko;

    public WezelOperacja1Arg(Dzialanie dzialanie, Wezel dziecko)
    {
        super(dzialanie);
        this.dziecko = dziecko;
    }

    public Wezel getDziecko()
    {
        return dziecko;
    }
}
