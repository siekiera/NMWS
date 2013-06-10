package pl.edu.pw.elka.maszyna.entity.algorytm;

/**
 * Argument predykatu
 * @author Michał Toporowski
 */

class Argument
{
	/** nazwa */
	public String nazwa;

    Argument(String nazwa)
    {
        this.nazwa = nazwa;
    }

    public Argument(Argument argument) {
    	this.nazwa = argument.nazwa;
    }

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
	
	@Override
	public String toString() {
		return nazwa;
	}
	
	public boolean czyStala() {
		if(!Character.isUpperCase(nazwa.charAt(0))) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean czyZmienna() {
		for(int i = 0; i < nazwa.length(); i++) {
			if(!Character.isLowerCase(nazwa.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
}
