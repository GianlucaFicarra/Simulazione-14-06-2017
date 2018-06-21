package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.Set;

public class Studente implements Comparable<Studente> {

		private int id;
		private Set<Integer> artObjectSet;
		    //I Set si utilizzano nel caso in cui i dati non devono avere valori replicati. Benché i set non siano ordinati, 

		public Studente(int id) {
			this.id = id;
			this.artObjectSet = new HashSet<Integer>();
		}

		public Set<Integer> getArtObjectSet() {
			return artObjectSet;
		}

		public void setArtObjectSet(Set<Integer> artObjectSet) {
			this.artObjectSet = artObjectSet;
		}

		public int getId() {
			return id;
		}

		@Override
		public String toString() {
			return "Studente (" + id + ") opere viste: " + artObjectSet.size();
		}

		@Override
		public int compareTo(Studente o) {
			return Integer.compare(this.artObjectSet.size(), o.getArtObjectSet().size());
		}
	}

