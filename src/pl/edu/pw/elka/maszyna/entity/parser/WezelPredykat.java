package pl.edu.pw.elka.maszyna.entity.parser;


/**
 * Węzeł, który jest liściem
 */
public class WezelPredykat extends Wezel
{
    //public Predykat pred;
    private final String predykat;

    public WezelPredykat(String predykat)
    {
        this.predykat = predykat;
    }

    String getPredykat()
    {
        return predykat;
    }
    
    @Override
    public String toString() {
    	return predykat;
    }
    
}