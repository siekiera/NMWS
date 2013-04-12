package pl.edu.pw.elka.maszyna.entity;

/**
 * Węzeł drzewa reprezentującego wyrażenie
 */
public abstract class Wezel 
{
	
}

/**
 * Węzeł, który jest liściem
 */
class Lisc extends Wezel
{
	public Predykat pred;
}

/**
 * Węzeł, który jest operacją
 */
class Operacja extends Wezel
{
	public Dzialanie dzialanie;
	public Wezel lewy, prawy;
}


