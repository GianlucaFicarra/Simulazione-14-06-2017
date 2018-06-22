package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.Set;

public class Studente implements Comparable<Studente> {

		private int id;
		private Set<Integer> opereVisteSet;
		    //I Set si utilizzano nel caso in cui i dati non devono avere valori replicati. Benché i set non siano ordinati, 

		public Studente(int id) {
			this.id = id;
			this.opereVisteSet = new HashSet<Integer>();
		}

		public Set<Integer> getOpereVisteSet() {
			return opereVisteSet;
		}

		public void setOpereViste(Set<Integer> artObjectSet) {
			opereVisteSet.addAll(artObjectSet);
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
		
		@Override
		public int compareTo(Studente o) {  //ordine crescente di studenti
			return Integer.compare(this.opereVisteSet.size(), o.getOpereVisteSet().size());
		}
	}

