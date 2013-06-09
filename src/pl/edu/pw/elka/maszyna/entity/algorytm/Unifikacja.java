package pl.edu.pw.elka.maszyna.entity.algorytm;

public class Unifikacja {

	private final String nazwaArgumentuPierwszegoPredykatu;
	private final String nazwaArgumentuDrugiegoPredykatu;
	
	public Unifikacja(String nazwaArgumentuPierwszegoPredykatu, String nazwaArgumentuDrugiegoPredykatu) {
		this.nazwaArgumentuPierwszegoPredykatu = nazwaArgumentuPierwszegoPredykatu;
		this.nazwaArgumentuDrugiegoPredykatu = nazwaArgumentuDrugiegoPredykatu;
	}
	
	public String getNazwaArgumentuPierwszegoPredykatu() {
		return nazwaArgumentuPierwszegoPredykatu;
	}
	
	public String getNazwaArgumentuDrugiegoPredykatu() {
		return nazwaArgumentuDrugiegoPredykatu;
	}

	public Unifikacja odwroc() {
		return new Unifikacja(nazwaArgumentuDrugiegoPredykatu, nazwaArgumentuPierwszegoPredykatu);
	}
	
	@Override
	public String toString() {
		return nazwaArgumentuPierwszegoPredykatu + " -> " + nazwaArgumentuDrugiegoPredykatu;
	}
	
}
