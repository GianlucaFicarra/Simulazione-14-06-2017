package it.polito.tdp.artsmia.model;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graphs;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Simulazione {
	
	//PARAMETRI (variabili per modificare la simulazione)
	private int numStud;
	private int anno;
	private List<Mostre> mostre;
	    //l’anno di inizio mostre coincide con quello tendina. (vertici grafo)
	
	private Model model;
	private ArtsmiaDAO dao;
	Random random;
	
	//MODELLO DEL MONDO (fotografia del sistema)
	private List<Studente> studenti;

	
	//CODA DEGLI EVENTI
	LinkedList<Event> queue;
	
	//VALORI OUTPUT
	//è la lista stessa di studenti aggiornata con le opere viste

	

	public void init(int anno, int numeroStudenti, ArtsmiaDAO dao, Model model) {
		this.anno=anno;
		this.numStud=numeroStudenti;
		this.dao=dao;
		this.model=model;
		
		queue = new LinkedList();
		random = new Random();
		studenti=new ArrayList<>();
		mostre= new LinkedList<>();
		
		//Carico oggetti_id nelle mostre(vertici grafo) direttamente dal dao
			for (Mostre m : model.grafo.vertexSet()) {
				m.setArtObjectsId(dao.getOggetiDaMostra(m));
				
				//filtro i vertici grafo per la data di interesse
				if(m.getInizio()==anno)
					mostre.add(m);
			}
		
		
	    //assegnare numStud studenti in modo casuale ad una mostra random
		int x = random.nextInt(mostre.size()); // nextInit da un numero da 0 al numero inserito tra () escluso
		Mostre casualMostra = mostre.get(x); //prendo mostra num casuale
		
		for(int i=0; i<numStud; i++) {
			// Genera un nuovo studente ed aggiungilo alla lista
			Studente s= new Studente(i+1);
			studenti.add(s);
			
			// Crea un nuovo evento ed aggiungilo alla coda degli eventi
		    Event e= new Event(s, casualMostra);
		    queue.add(e);
		}
		
	}

	public void run() {
		Event e;
		while((e = this.queue.poll()) != null) {
			processEvent(e);  //evento: studente visita mostra:
		}
		
	}

	private void processEvent(Event e) {
		// aggiungo al SET(no doppi) dello studente tutte le opere in esposizione per quella mostra
		 e.getStudente().getOpereVisteSet().addAll(e.getExhibition().getArtObjectsId());
		 

		// Controllo se la simulazione deve andare avanti
		 List<Mostre> successori = Graphs.successorListOf(model.grafo, e.getExhibition()); //essendo orientato
		 if(successori.size()>0) {
			int x2 = random.nextInt(successori.size());
			Mostre casualMostra2 = successori.get(x2);
			
			// Crea un nuovo evento ed aggiungilo alla coda degli eventi
		    Event e2= new Event(e.getStudente(), casualMostra2);
		    queue.add(e2);
			
		}
		 
		//continuerò a creare eventi ed a farli accadere, 
		//finche non avrò più seccessori e li event finiranno!!!
		
	}

	public List<Studente> getRisultatiSimulazione() {
		return studenti;
	}

	
}
