package it.polito.tdp.artsmia.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	ArtsmiaDAO dao= new ArtsmiaDAO();
	
	//Vertici: mostre Archi:mostre sono collegate se sono temporalmente successive dopo annoselezionato
	SimpleDirectedGraph<Mostre, DefaultEdge> grafo;
	List <Mostre> mostre= new LinkedList<>();

	Simulazione sim= new Simulazione();
	
	
	
	public List<Integer> getAnniMostre() {
		return dao.getAnniMostre();
	}

	
	public void createGraph(Integer anno) {
		grafo = new SimpleDirectedGraph<Mostre, DefaultEdge>(DefaultEdge.class);
				
		//carico le mostre
		mostre= dao.getMostreDaAnno(anno);
		
		//aggiungo i vertici
		Graphs.addAllVertices(this.grafo, mostre);
		
		//cerco gli archi
		for(Mostre m: mostre) {
						
			// due mostre sono collegate se sono temporalmente successive 
			//(utilizzare la data di inizio), ma hanno almeno un anno in comune
			for(Mostre mo: mostre) {
				if(!mo.equals(m) && mo.getInizio()<=m.getFine() 
						&& mo.getInizio()>m.getInizio()) {
			
				grafo.addEdge(m, mo);
				      //grafo orientato da origine a destinazione
				}
			}
		}
		
		System.out.println("Grafo creato! (year: " + anno + ")");
		System.out.println("# Vertici: " + grafo.vertexSet().size());
		System.out.println("# Archi: " + grafo.edgeSet().size());
	}


	//un grafo diretto è fortemente connesso se e solo se ha una sola componente connessa
	//cioè se tra tutti i nodi esiste una strada da AB e da BA--> uso Kosaraju
	public boolean isStronglyConnected() {
		KosarajuStrongConnectivityInspector<Mostre, DefaultEdge> ksci = new KosarajuStrongConnectivityInspector<Mostre, DefaultEdge>(grafo);
		return ksci.isStronglyConnected();
	}	

	public Mostre getMostraPiuGrande(Integer anno) {
		return dao.getMostraPiuGrande(anno);
	}
	
	
	
	//2 PUNTO
	public void simula(Integer anno, int numeroStudenti) {
		sim.init(anno, numeroStudenti, dao, this);
		sim.run();		
	}
	
	 //studente = sim result con studente e lista oggetti
	public List<Studente> getRisultatiSimulazione() {
		
		return sim.getRisultatiSimulazione();
	}
	
}
