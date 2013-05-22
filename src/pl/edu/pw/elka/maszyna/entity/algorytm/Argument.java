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
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null) return false;
		if (this == arg0) return true;
		else if (!(arg0 instanceof Argument)) return false;
		else return this.nazwa.equals(((Argument)arg0).nazwa);
	}
}
