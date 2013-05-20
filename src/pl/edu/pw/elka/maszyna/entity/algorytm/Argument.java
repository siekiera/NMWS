package pl.edu.pw.elka.maszyna.entity.algorytm;

/**
 * Argument predykatu
 * @author Michał Toporowski
 */

class Argument
{
	/** nazwa */
	public String nazwa;

	/**
	 * @return true - jeśli argument jest zmienną (nazwa zaczyna się małą literą)<br>
	 * false, jeśli argument jest stałą/obiektem (nazwa zaczyna się wielką literą)
	 */
	public boolean jestZmienna()
	{
		return Character.isLowerCase(nazwa.charAt(0));
	}
}
