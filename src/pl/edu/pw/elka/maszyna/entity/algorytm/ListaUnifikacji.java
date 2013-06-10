package pl.edu.pw.elka.maszyna.entity.algorytm;

import java.util.ArrayList;
import java.util.List;

public class ListaUnifikacji {

	private final List<Unifikacja> listaUnifikacji;
	
	public ListaUnifikacji(List<Unifikacja> listaUnifikacji) {
		this.listaUnifikacji = listaUnifikacji;
	}
	
	public List<Unifikacja> getListaUnifikacji() {
		return listaUnifikacji;
	}

	public boolean czyPusta() {
		return listaUnifikacji.isEmpty();
	}

	public ListaUnifikacji odwroc() {
		List<Unifikacja> odwroconaListaUnifikacji = new ArrayList<Unifikacja>();
		for (Unifikacja unifikacja : listaUnifikacji) {
			odwroconaListaUnifikacji.add(unifikacja.odwroc());
		}
		return new ListaUnifikacji(odwroconaListaUnifikacji);
	}
	
	@Override
	public String toString() {
		if (listaUnifikacji.size() == 0) {
			return "No elements";
		}
		else {
			String wynik = "\n";
			for (Unifikacja unifikacja : listaUnifikacji) {
				wynik += unifikacja.toString() + "\n";
			}
			return wynik;
		}
	}

	/**
	 * Filtruje tylko te unifikacje, ktore jako drugi argument maja wpisana Stala
	 * @return
	 */
	public ListaUnifikacji doStalych() {
		//FIXME SPRAWDZIC!!!
		List<Unifikacja> odfiltrowanaListaUnifikacji = new ArrayList<Unifikacja>();
		for (Unifikacja unifikacja : listaUnifikacji) {
			if (Character.isUpperCase(unifikacja.getNazwaArgumentuDrugiegoPredykatu().charAt(0))) {
				odfiltrowanaListaUnifikacji.add(unifikacja);
			}
		}
		
		return new ListaUnifikacji(odfiltrowanaListaUnifikacji);
	}

	/**
	 * Filtruje tylko te unifikacje, ktore jeko pierwszy argument maja Zmienna, a drugi - Stala
	 * @return
	 */
	public ListaUnifikacji odZmiennychDoStalych() {
		List<Unifikacja> odfiltrowanaListaUnifikacji = new ArrayList<Unifikacja>();
		for (Unifikacja unifikacja : listaUnifikacji) {
			if (Character.isLowerCase(unifikacja.getNazwaArgumentuPierwszegoPredykatu().charAt(0)) && 
					Character.isUpperCase(unifikacja.getNazwaArgumentuDrugiegoPredykatu().charAt(0))) {
				odfiltrowanaListaUnifikacji.add(unifikacja);
			}
		}
		return new ListaUnifikacji(odfiltrowanaListaUnifikacji);	
	}

	public ListaUnifikacji odZmiennychDoZmiennych() {
		List<Unifikacja> odfiltrowanaListaUnifikacji = new ArrayList<Unifikacja>();
		for (Unifikacja unifikacja : listaUnifikacji) {
			if (Character.isLowerCase(unifikacja.getNazwaArgumentuPierwszegoPredykatu().charAt(0)) && 
					Character.isLowerCase(unifikacja.getNazwaArgumentuDrugiegoPredykatu().charAt(0))) {
				odfiltrowanaListaUnifikacji.add(unifikacja);
			}
		}
		return new ListaUnifikacji(odfiltrowanaListaUnifikacji);
	}
	
}
