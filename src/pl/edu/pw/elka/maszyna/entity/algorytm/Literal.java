package pl.edu.pw.elka.maszyna.entity.algorytm;

/**
 * Literał, czyli predykat, bądź zaprzeczony predykat
 * @author Michał Toporowski
 */
public class Literal
{
    private final Predykat predykat;
    private final boolean zanegowany;

    public Literal(Predykat predykat, boolean zanegowany)
    {
        this.predykat = predykat;
        this.zanegowany = zanegowany;
    }

    public Predykat getPredykat()
    {
        return predykat;
    }

    public boolean isZanegowany()
    {
        return zanegowany;
    }
}
