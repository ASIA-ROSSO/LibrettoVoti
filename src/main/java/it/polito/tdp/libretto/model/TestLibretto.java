package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	Libretto lib;

	private void run() {
		this.lib = new Libretto(); // crea libretto vuoto

		// 1 inserire alcuni voti
		Voto v1 = new Voto("Tecniche di programmazione", 30, LocalDate.of(2020, 06, 15));
		Voto v2 = new Voto("Analisi II", 28, LocalDate.of(2020, 06, 28));

		lib.add(v1);
		lib.add(v2);
		if (lib.add(new Voto("Economia", 24, LocalDate.of(2020, 02, 14))) == false)
			System.out.println("Errore di inserimento!");

		System.out.println(this.lib); // serve toString altrimenti stamperebbe lo UID

		// 2) stampa tutti i voti uguali a 28
		System.out.println(this.lib.stampaVotiUguali(28));

		System.out.println(this.lib.estraiVotiUguali(28));

		// 3)Cerca il voto di un esame, conoscendo il corso
		String nomeCorso = "Analisi II";
		// int voto = lib.cercaNomeCorso(nomeCorso); //28
		// però potrebbe servirci in seguito la data, conviene passare l'oggetto
		Voto votoAnalisi = lib.cercaNomeCorso(nomeCorso);
		System.out.println("Il voto di " + nomeCorso + " è " + votoAnalisi.getVoto());

		// ricerca di un voto mancante
		Voto votoMancante = lib.cercaNomeCorso("Fisica I");
		System.out.println("Il voto di Fisica I è " + votoMancante);

		// 4-5) Verifica voti duplicati o voti in conflitto
		Voto economia2 = new Voto("Economia", 24, LocalDate.now());
		Voto economia3 = new Voto("Economia", 21, LocalDate.now());
		System.out.println("Econommia con 24 è duplicato: " + lib.isDuplicato(economia2) + ". E' in conflitto: "
				+ lib.isConflitto(economia2));
		System.out.println("Econommia con 22 è duplicato: " + lib.isDuplicato(economia3) + ". E' in conflitto: "
				+ lib.isConflitto(economia3));

		// 7) migliora libretto
		Libretto migliorato = lib.creaLibrettoMigliorato();
		System.out.println("Miglioramento del libretto");
		System.out.println(lib);
		System.out.println(migliorato);
		
		//8) Stampare in ordine alfabetico d'esame ed in ordine numerico decrescene di voto
		//posso ordinare il libreto di partenza o una copia
		Libretto alfabetico = new Libretto(lib);
		alfabetico.ordinaPerCorso();
		System.out.println(alfabetico);
		//8) stampa in ordine di voto
		Libretto votidecrescenti = new Libretto(lib);
		votidecrescenti.ordinaPerVoto();
		System.out.println(votidecrescenti);
		
		//9) Elimina voti bassi
		lib.add(new Voto("Chimica", 19, LocalDate.now()));
		lib.ordinaPerCorso();
		System.out.println(lib);
		lib.cancellaVotiScarsi();
		System.out.println(lib);
	}

	// nel main l'oggetto testlibretto non esiste ancora
	public static void main(String[] args) {
		TestLibretto test = new TestLibretto();
		test.run();
	}

}
