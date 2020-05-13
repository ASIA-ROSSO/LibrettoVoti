package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * Classe Voto, contiene le informazioni su un esame superato.
 * 
 * @author Fulvio
 *
 */
public class Voto implements Comparable<Voto>{

	private String corso; // "Tecniche di Programmazione"
	private int voto; // 28
	private LocalDate data; // 15/06/2020
	//non usare Date come classe.
	
	/**
	 * Costruisce un nuovo Voto.
	 * 
	 * @param corso nome del corso superato
	 * @param voto  valore del voto acquisito
	 * @param data  data di superamento dell'esame
	 */
	public Voto(String corso, int voto, LocalDate data) {
		super();
		this.corso = corso;
		this.voto = voto;
		this.data = data;
	}
	
	/**
	 * Copy constructor di {@link Voto}: crea un nuovo {@link Voto}, copiando 
	 * il contenuto del parametro {@code v.
	 * @param v il voto da copiare
	 */
	public Voto(Voto v) {
		super();
		//condivido il riferimento; qui posso farlo perchè i tipi degli attributi sono immutabili
		this.corso = v.corso; //posso non usare .getCorso(), semplificazione di java 
		this.voto = v.voto;
		this.data = v.data;
	}
	
	/**
	 * Crea un clone dell'oggetto presente (this), come nuovo oggetto
	 */
	public Voto clone() {
		Voto v =  new Voto(this.corso, this.voto, this.data);
		return v;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return corso + ": " + voto + " (" + data + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		return result;
	}

	
	//La classe voto delega la propria uguaglianza al nome del corso
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		return true;
	}

	@Override
	public int compareTo(Voto other) {
		/*
		 * <0 se this < other
		 * =0 se this == other
		 * >0 se this > other
		 */
		return this.corso.compareTo(other.corso);
	}


	

}
