package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;

public class Mostre {

	private int id; //id mostra
	private String dipartimento;
	private String descrizione;
	private int inizio;
	private int fine;
	
	//Lista con gli id degli oggetti che espone
	private List<Integer> artObjectsId;
	
	public Mostre(int id, String dipartimento, String descrizione, int inizio, int fine) {
		super();
		this.id = id;
		this.dipartimento = dipartimento;
		this.descrizione = descrizione;
		this.inizio = inizio;
		this.fine = fine;
		
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

	public String getDipartimento() {
		return dipartimento;
	}

	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
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
