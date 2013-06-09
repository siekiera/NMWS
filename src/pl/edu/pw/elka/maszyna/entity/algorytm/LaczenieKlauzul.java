package pl.edu.pw.elka.maszyna.entity.algorytm;

public class LaczenieKlauzul {

	private final Klauzula pierwszaKlauzula;
	private final Klauzula drugaKlauzula;
	private final Klauzula dzieckoKlauzula;
	
	public LaczenieKlauzul(Klauzula pierwszaKlauzula, Klauzula drugaKlauzula, Klauzula dzieckoKlauzula) {
		this.pierwszaKlauzula = pierwszaKlauzula;
		this.drugaKlauzula = drugaKlauzula;
		this.dzieckoKlauzula = dzieckoKlauzula;
	}

	public boolean maRodzicow(Klauzula gornaKlauzula, Klauzula dolnaKlauzula) {
		if (gornaKlauzula == pierwszaKlauzula && dolnaKlauzula == drugaKlauzula) {
			return true;
		} else if (dolnaKlauzula == pierwszaKlauzula && gornaKlauzula == drugaKlauzula) {
			return true;
		}
		return false;
	}
	
}
