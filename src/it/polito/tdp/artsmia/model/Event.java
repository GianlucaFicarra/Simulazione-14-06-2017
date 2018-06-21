package it.polito.tdp.artsmia.model;

public class Event {

	private Studente studente;
	private Mostre mostra; //prossima mostra da visitare

	public Event(Studente studente, Mostre mostra) {
		super();
		this.studente = studente;
		this.mostra = mostra;
	}

	public Studente getStudente() {
		return studente;
	}

	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	public Mostre getExhibition() {
		return mostra;
	}

	public void setExhibition(Mostre mostra) {
		this.mostra = mostra;
	}
}
