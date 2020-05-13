package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Memorizza e gestisce un insieme di voti superati.
 * 
 * @author Fulvio
 *
 */
public class Libretto {

	private List<Voto> voti = new ArrayList<>();

	public Libretto() {
		super();
	}

	// Copy constructor, se non presente bisogna aggiungere anche il costruttore
	/**
	 * Copy Constructor "Shallow" (copia superficiale) mantiene gli stessi oggetti
	 * ma li mette in una lista diversa (qui basta perchè non devo modificare
	 * l'oggetto voto)
	 * 
	 * @param lib
	 */
	public Libretto(Libretto lib) {
		super();
		this.voti.addAll(lib.voti); // condivido gli oggetti
	}

	/**
	 * riordina i voti presenti nel libretto corrente alfabeticamente per corso
	 */
	public void ordinaPerCorso() {
		Collections.sort(this.voti); // delego al metodo sort l'ordinamento
	}

	public void ordinaPerVoto() {
		Collections.sort(voti, new ConfrontaVotiPerValutazione());
		// oppure this.voti.sort(new ConfrontaVotiPerValutazione());

	}

	/*
	 * public Libretto() { this.voti = new ArrayList<Voto>(); }
	 */
	/**
	 * Aggiunge un nuovo voto al libretto
	 * 
	 * @param v Voto da aggiungere
	 * @return true se il voto è stato aggiunto, {@code false} se il voto non è
	 *         stato aggiunto
	 */
	public boolean add(Voto v) {
		if (this.isConflitto(v) || this.isDuplicato(v)) {
			// piccola inefficenza perchè sia isConflitto che isDuplicato chiamano
			// cercaNOmeCorso
			// non inserire il voto -> il chiamante come fa a sapere se l'operazione va a
			// buon fine
			// Per semplificare possiamo segnalare restituendo un valore booleano
			return false; // segnalo al chiamante che l'operazione non ha avuto successo
		} else {
			// inserisco il voto perchè non è in conflitto nè duplicato
			this.voti.add(v);
			return true;
		}

	}

	/**
	 * Dato un Libretto, restituisce una stringa nella quale vi sono solamente i
	 * voti pari al valore specificato
	 * 
	 * @param voto valore specificato
	 * @return stringa formattata per visualizzare il sotto-libretto
	 */
	public String stampaVotiUguali(int voto) {
		String s = "";
		for (Voto v : this.voti) {
			if (v.getVoto() == voto) {
				s += v.toString() + "\n";
			}
		}
		return s;
	}

	// MEGLIO LAVORARE CON OGGETTI CHE CON STRINGHE!
	/**
	 * Genera un nuovo libretto, a partire da quello esistente, che conterrà
	 * esclusivamenti i voti con votazione pari a quella specificata.
	 * 
	 * @param voto votazione specificata
	 * @return nuovo Libretto "ridotto"
	 */
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo = new Libretto();
		for (Voto v : this.voti) {
			if (v.getVoto() == voto) {
				nuovo.add(v);
			}
		}
		return nuovo;
	}

	public String toString() {
		String s = "";
		for (Voto v : this.voti) {
			s += v.toString() + "\n";
		}
		return s;
	}

	/**
	 * Dato il nome di un corso, ricerca se quell'esame esiste nel libretto, ed in
	 * caso affermativo restituisce l'oggetto {@link Voto} corrispondente Se l'esame
	 * non esiste (genera un'eccezione o) restituisce {@code null}
	 * 
	 * @param nomeCorso nome esame da cercare
	 * @return il {@link Voto} corrispondente, oppure {@code null} se non esiste
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		/*
		 * for (Voto v : this.voti) { if (nomeCorso.equals(v.getCorso())) { return v; }
		 * } return null;
		 */
		// esiste un modo più evoluto?
		// occorre specificare il metodo equals nella classe VOto
		// in questo caso possiamo considerare due voti uguali se
		// hanno lo stesso nome del corso

		// creo un nuovo oggetto Voto che abbia lo stesso nome del corso
		int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null));
		if (pos != -1) {
			return this.voti.get(pos);
		} else {
			return null;
		}
	}
	// 4-5)

	/**
	 * Ritorna {@code true} se il corso, spedificato dal voto {@code v} esiste nel
	 * libretto con la stessa valutazione; Se non esiste, o se la valutazione è
	 * diversa, ritorna {@code false}.
	 * 
	 * @param v il {@link Voto} da ricercare
	 * @return l'esistenza di un duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if (esiste == null) {
			// se non l'ho trovato -> non è un duplicato
			return false;
		}
		/*
		 * if (esiste.getVoto() == v.getVoto()) return true; else return false;
		 */
		return (esiste.getVoto() == v.getVoto());
	}

	public boolean isConflitto(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if (esiste == null)
			return false;

		return (esiste.getVoto() != v.getVoto());
	}

	/**
	 * Restituisce un nuovo libretto migliorando i voti del libretto attuale.
	 * 
	 * @return
	 */
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo = new Libretto();

		for (Voto v : this.voti) {
			// Attenzione non sto creando un nuovo voto ma un altro modo per chiamare i voti
			// precedenti
			// v2 punta allo stesso oggetto che c'erano prima nel libretto vecchio
			// E' utile creare oggetti che facciano riferimento a strutture dati diverse
			// Come si fa una copia di un oggetto?
			// 1. copy constructor
			// 2. implementare un metodo clone() in Voto
			Voto v2 = new Voto(v);
			// o ugualmente Voto: v2 = v.clone();
			if (v2.getVoto() >= 24) {
				v2.setVoto(v2.getVoto() + 2);
				if (v2.getVoto() > 30)
					v2.setVoto(30);
			} else if (v2.getVoto() >= 18) {
				v2.setVoto(v2.getVoto() + 1);
			}
			nuovo.add(v2);
		}
		return nuovo;

	}

	/**
	 * Elimina dal libretto tutti i voti <24
	 */
	public void cancellaVotiScarsi() {
		// Attenzione, non posso rimuovere (o aggiungere) degli elementi da una lista
		// mentre sto iterando dalla stessa lista: ConcurrentModificationException
		List<Voto> daRimuovere = new ArrayList<>();

		for (Voto v : this.voti) {
			if (v.getVoto() < 24)
				daRimuovere.add(v);
		}
		
		//Rimuovo dalla lista voti mentre sto iterando sulla lista daRimuovere
		//for (Voto v: daRimuovere) {
		//	this.voti.remove(v);
		//}
		this.voti.removeAll(daRimuovere);
	}

}
