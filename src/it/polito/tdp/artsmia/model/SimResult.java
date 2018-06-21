package it.polito.tdp.artsmia.model;

import java.util.List;

public class SimResult {

	private Studente studente;
	private List<ArtObject> oggetti;
	
	public SimResult(Studente studente, List<ArtObject> opere) {
		super();
		this.studente = studente;
		this.oggetti = opere;
	}

	public Studente getStudente() {
		return studente;
	}

	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	public List<ArtObject> getOggetti() {
		return oggetti;
	}

	public void setOggetti(List<ArtObject> opere) { //aggiunge solo nuovi oggetti
		for(ArtObject a: opere) {
			if(!this.oggetti.contains(a))
				oggetti.add(a);
		}
	}
	
	
	

}
