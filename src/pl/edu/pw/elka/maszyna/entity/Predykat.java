package pl.edu.pw.elka.maszyna.entity;

import java.util.List;

/**
 * Predykat, bądź zaprzeczony predykat
 */
public class Predykat 
{
	/** nazwa predykatu - drukowanymi literami*/
	private final String nazwa;
	private final List<Argument> argumenty;
	public Predykat(String nazwa, List<Argument> argumenty)
	{
		super();
		this.nazwa = nazwa;
		this.argumenty = argumenty;
	}
	

}

/**
 * Argument predykatu
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
