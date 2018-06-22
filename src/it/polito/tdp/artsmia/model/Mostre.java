package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Mostre {

	private int id; //id mostra
	private String descrizione;
	private int inizio;
	private int fine;
	private int numOpere;
	private Set<Integer> opere;
	
	//Lista con gli id degli oggetti che espone
	private List<Integer> artObjectsId;
	
	public Mostre(int id, String descrizione, int inizio, int fine) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.inizio = inizio;
		this.fine = fine;
		
		numOpere=0;
		artObjectsId = new ArrayList<Integer>();
	}

   //per aggiungere e ritornare la lista di id delle opere esposte
	public List<Integer> getArtObjectsId() {
		return artObjectsId;
	}

	public void setArtObjectsId(List<Integer> artObjectsId) {
		this.artObjectsId = artObjectsId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getInizio() {
		return inizio;
	}

	public void setInizio(int inizio) {
		this.inizio = inizio;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public int getNumOpere() {
		return numOpere;
	}

	public void setNumOpere(int numOpere) {
		this.numOpere += numOpere;
	}

	public Set<Integer> getOpere() {
		return opere;
	}

	public void setOpere(Set<Integer> opere) {
		this.opere = opere;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mostre other = (Mostre) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mostra [id=" + id + ", descrizione=" + descrizione + ", inizio=" + inizio + "]";
	}
	
	
	
}
