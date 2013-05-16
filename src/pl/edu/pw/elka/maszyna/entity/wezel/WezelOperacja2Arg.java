package pl.edu.pw.elka.maszyna.entity.wezel;

/**
 * Created with IntelliJ IDEA.
 * User: michalek
 * Date: 16.05.13
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class WezelOperacja2Arg extends WezelOperacja
{
    private final Wezel lewy;
    private final Wezel prawy;

    public WezelOperacja2Arg(Dzialanie dzialanie, Wezel lewy, Wezel prawy)
    {
        super(dzialanie);
        this.lewy = lewy;
        this.prawy = prawy;
    }

    public Wezel getLewy()
    {
        return lewy;
    }

    public Wezel getPrawy()
    {
        return prawy;
    }
    
    @Override
    public String toString() {
    	return lewy.toString() + dzialanie.getZnak() + prawy.toString();
    }
    
}
