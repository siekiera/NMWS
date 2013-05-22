package pl.edu.pw.elka.maszyna.entity.parser;


/**
 *
 */
public class WezelNegacja extends WezelOperacja
{
    private Wezel dziecko;

    public WezelNegacja(Wezel dziecko)
    {
        super(Dzialanie.NIE);
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
