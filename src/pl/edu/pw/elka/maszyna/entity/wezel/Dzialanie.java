package pl.edu.pw.elka.maszyna.entity.wezel;

public enum Dzialanie
{
	LUB('|'), I('&'), JESLI_TO('>'), ROWNOWAZN('='), NIE('-');

    private final char znak;

    Dzialanie(final char znak)
    {
        this.znak = znak;
    }
}
