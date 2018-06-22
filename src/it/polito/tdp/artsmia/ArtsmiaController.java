/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Mostre;
import it.polito.tdp.artsmia.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    private Model model;
    
    
	public void setmodel(Model model) {
		this.model=model;
		
		boxAnno.getItems().addAll(model.getAnniMostre());
	}
    
    @FXML
    void handleCreaGrafo(ActionEvent event) {

    	txtResult.clear();

    	Integer anno=boxAnno.getValue();
    	if(anno==null) {
    		txtResult.setText("Inserire anno");
    	    return;
    	}
    	
			model.createGraph(anno);
			txtResult.setText("Grafo creato!\n"); 
			
			//GRAFO è FORTEMENTE CONNESSO se esiste una strada da AB e da BA oper i vari nodi                                        
			if(model.isStronglyConnected()) {
	    		txtResult.appendText("Il grafo è fortemente connesso!\n");
	    	} else {
	    		txtResult.appendText("Il grafo non è fortemente connesso.\n");
	    	}
			//oppure in breve txtResult.appendText("Grafo connesso? " + model.isStronglyConnected() + "\n"); //true o false come risposta
			
			//MOSTRA PIù GRANDE
			Mostre m= model.getMostraPiuGrande(anno);
			if(m == null) {
	    		txtResult.appendText("Nessuna esibizione trovata nell'anno inserito!\n");
	    		return;
	    	}
	    	txtResult.appendText("La mostra "+m.getDescrizione()+" vanta il maggior numero di opere d'arte in esposizione ("+m.getOpere()+").\n");
	    	

		
    }

    @FXML
    void handleSimula(ActionEvent event) {
    	txtResult.clear();

    	//seleziono anno di cui deve essere la mostra
    	Integer anno=boxAnno.getValue();
    	if(anno==null) {
    		txtResult.setText("Inserire anno");
    	    return;
    	}

		//seleziono il num di studenti con cui fare la simulazione
		int numeroStudenti;
		try {
			numeroStudenti = Integer.valueOf(txtFieldStudenti.getText());
		} catch (NumberFormatException nfe) {
			txtResult.setText("Inserire Valore numerico per il numero di studenti da simulare");
			return;
		}
		
		//creo grafo ed avvio simulazione
		try {
			model.createGraph(anno); //se facessi direttamente la simulazione senza il primo punto
			txtResult.setText("Grafo creato!\n");

			model.simula(anno, numeroStudenti);
			List<Studente> simulationResult = model.getRisultatiSimulazione();
			if(simulationResult.isEmpty()) {
    			txtResult.setText("Simulazione fallita\n");
    			return;
    		}
			
			//stampo risultato
			txtResult.setText("Risultato simulazione: \n");
    	    for(Studente s : simulationResult) {
    			txtResult.appendText("Lo studente "+s.getId()+" ha visto "+s.getOpereVisteSet().size()+" opere\n");
    		}

		} catch (RuntimeException e) {
			System.out.println("Errore simulazione!");
		}


    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

}
