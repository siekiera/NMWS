package pl.edu.pw.elka.maszyna.wspolne.wyjatki;

public class WyjatekParsowaniaDanych extends Exception {

	private final String komunikat;

	public WyjatekParsowaniaDanych(String komunikat) {
		this.komunikat = komunikat;
	}
	
	public String getKomunikat() {
		return komunikat;
	}
}
