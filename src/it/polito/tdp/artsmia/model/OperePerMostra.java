package it.polito.tdp.artsmia.model;

public class OperePerMostra {

	private int id;  //id mostra
	private String descrizione;
	private int opere; //num opere nella mostra


	public OperePerMostra(int id, String descrizione, int opere) {
		super();
		this.id=id;
		this.descrizione = descrizione;
		this.opere = opere;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getOpere() {
		return opere;
	}


	public void setOpere(int opere) {
		this.opere = opere;
	}


	@Override
	public String toString() {
		return "Mostra " + descrizione + " ospita " + opere + " opere";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
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
		OperePerMostra other = (OperePerMostra) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		return true;
	}
	
	
	
}
