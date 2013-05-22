package pl.edu.pw.elka.maszyna.entity.parser;


/**
 *
 */
public class WezelOperacja1Arg extends WezelOperacja
{
    private Wezel dziecko;

    public WezelOperacja1Arg(Dzialanie dzialanie, Wezel dziecko)
    {
        super(dzialanie);
        this.dziecko = dziecko;
    }

    public Wezel getDziecko()
    {
        return dziecko;
    }

    public void setDziecko(Wezel dziecko)
    {
        this.dziecko = dziecko;
    }

    @Override
    public String toString() {
    	return "(" + dzialanie.getZnak() + dziecko.toString() + ")";
    }
    
}
