package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.List;

/**
 * Predykat
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

