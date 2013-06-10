package pl.edu.pw.elka.maszyna.wspolne;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.elka.maszyna.entity.algorytm.LaczenieKlauzul;

public class DrzewoWnioskowania {

	private WezelDrzewaWnioskowania korzen;


	public DrzewoWnioskowania(List<LaczenieKlauzul> laczeniaKlauzul) {
		korzen = new WezelDrzewaWnioskowania("fałsz");

		budujDrzewo(laczeniaKlauzul);
	}
	
	public WezelDrzewaWnioskowania getKorzen() {
		return korzen;
	}
	
	private void budujDrzewo(List<LaczenieKlauzul> laczeniaKlauzul) {
		LaczenieKlauzul laczenieFalszu = szukajFalsz(laczeniaKlauzul);
		
		WezelDrzewaWnioskowania pierwszyDzieciak = new WezelDrzewaWnioskowania(laczenieFalszu.getPierwszaKlauzula().toString());
		korzen.dzieci.add(pierwszyDzieciak);
		dodajDzieci(pierwszyDzieciak, laczeniaKlauzul);
		
		WezelDrzewaWnioskowania drugiDzieciak = new WezelDrzewaWnioskowania(laczenieFalszu.getDrugaKlauzula().toString());
		korzen.dzieci.add(drugiDzieciak);
		dodajDzieci(drugiDzieciak, laczeniaKlauzul);
	}

	private void dodajDzieci(WezelDrzewaWnioskowania ojciec,
			List<LaczenieKlauzul> laczeniaKlauzul) {
		LaczenieKlauzul laczenieKlauzul = szukajKlauzule(ojciec, laczeniaKlauzul);
		
//		System.out.println(laczenieKlauzul);
		if (laczenieKlauzul != null) {
			WezelDrzewaWnioskowania pierwszyDzieciak = new WezelDrzewaWnioskowania(laczenieKlauzul.getPierwszaKlauzula().toString());
			ojciec.dzieci.add(pierwszyDzieciak);
			dodajDzieci(pierwszyDzieciak, laczeniaKlauzul);
			
			WezelDrzewaWnioskowania drugiDzieciak = new WezelDrzewaWnioskowania(laczenieKlauzul.getDrugaKlauzula().toString());
			ojciec.dzieci.add(drugiDzieciak);
			dodajDzieci(drugiDzieciak, laczeniaKlauzul);
		}
	}

	private LaczenieKlauzul szukajKlauzule(WezelDrzewaWnioskowania dzieciak,
			List<LaczenieKlauzul> laczeniaKlauzul) {
		for (LaczenieKlauzul laczenieKlauzul : laczeniaKlauzul) {
			if (laczenieKlauzul.getDzieckoKlauzula() != null && laczenieKlauzul.getDzieckoKlauzula().toString().equals(dzieciak.klauzula)) {
				return laczenieKlauzul;
			}
		}
		return null;
	}

	private LaczenieKlauzul szukajFalsz(List<LaczenieKlauzul> laczeniaKlauzul) {
		for (LaczenieKlauzul laczenieKlauzul : laczeniaKlauzul) {
			if (laczenieKlauzul.getDzieckoKlauzula() == null) {
				return laczenieKlauzul;
			}
		}
		throw new RuntimeException("Nie jest dozwolone");
	}

	public class WezelDrzewaWnioskowania {
		
		public WezelDrzewaWnioskowania(String klauzula) {
			this.klauzula = klauzula;
			dzieci = new ArrayList<DrzewoWnioskowania.WezelDrzewaWnioskowania>();
		}
		
		private String klauzula;
//		private WezelDrzewaWnioskowania rodzic;
		private List<WezelDrzewaWnioskowania> dzieci;
		
		public String getKlauzula() {
			return klauzula;
		}
		
		public List<WezelDrzewaWnioskowania> getDzieci() {
			return dzieci;
		}
		
	}
	
}
