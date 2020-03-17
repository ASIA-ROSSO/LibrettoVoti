package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	Libretto lib;

	private void run() {
		this.lib = new Libretto(); // crea libretto vuoto

		Voto v1 = new Voto("Tecniche di programmazione", 30, LocalDate.of(2020, 06, 15));
		Voto v2 = new Voto("Analisi II", 28, LocalDate.of(2020, 06, 28));

		lib.add(v1);
		lib.add(v2);
		lib.add(new Voto("Economia", 24, LocalDate.of(2020, 02, 14)));

		System.out.println(this.lib); //serve toString altrimenti stamperebbe lo UID

		System.out.println(this.lib.stampaVotiUguali(28));

		System.out.println(this.lib.estraiVotiUguali(28));
	}

	//nel main l'oggetto testlibretto non esiste ancora
	public static void main(String[] args) {
		TestLibretto test = new TestLibretto();
		test.run();
	}

}
