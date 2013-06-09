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
	
}
