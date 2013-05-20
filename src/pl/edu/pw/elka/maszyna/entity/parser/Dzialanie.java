package pl.edu.pw.elka.maszyna.entity.parser;

public enum Dzialanie
{
	LUB('|'), I('&'), JESLI_TO('>'), ROWNOWAZN('='), NIE('-');

    private final char znak;

    Dzialanie(final char znak)
    {
        this.znak = znak;
    }

	public char getZnak() {
		return znak;
	}
    
    
}
