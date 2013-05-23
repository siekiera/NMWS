package pl.edu.pw.elka.maszyna.wspolne.wyjatki;

public class WyjatekParsowaniaNiepoprawnychDanych extends Exception {

	private final String komunikat;

	public WyjatekParsowaniaNiepoprawnychDanych(String komunikat) {
		this.komunikat = komunikat;
	}
	
	public String getKomunikat() {
		return komunikat;
	}
}
