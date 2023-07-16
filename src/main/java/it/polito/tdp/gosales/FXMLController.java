package it.polito.tdp.gosales;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.gosales.model.Arco;
import it.polito.tdp.gosales.model.Model;
import it.polito.tdp.gosales.model.Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaPercorso;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<Integer> cmbAnno;

    @FXML
    private ComboBox<String> cmbColore;

    @FXML
    private ComboBox<Products> cmbProdotto;

    @FXML
    private TextArea txtArchi;

    @FXML
    private TextArea txtResGrafo;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaPercorso(ActionEvent event) {
    	//Prima, bisogna leggre gli input, gestendo gli errori
    	Products prodotto = this.cmbProdotto.getValue();
    	if (prodotto==null) {
    		this.txtResult.setText("Selezionare un prodotto\n");
    		return;
    	}
    	List<Arco> cammino = this.model.trovaPercorso(prodotto);
    	this.txtResult.clear();
    	if(cammino.isEmpty()) {
    		this.txtResult.setText("Il prodotto selezionato non fa parte di alcun arco!\n\n");
    		return;
    	}
    	this.txtResult.setText("Ho trovato un cammino di lunghezza " + cammino.size()+"\n");
    	for (Arco a : cammino) {
    		this.txtResult.appendText(a + "\n");
    	}
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	//Prima, bisogna leggre gli input, gestendo gli errori
    	Integer anno = this.cmbAnno.getValue();
    	if (anno==null) {
    		this.txtResult.setText("Selezionare un anno\n");
    		return;
    	}
    	String colore = this.cmbColore.getValue();
    	if (colore==null) {
    		this.txtResult.setText("Selezionare un colore\n");
    		return;
    	}
    	
    	//Si crea il grafo
    	this.model.creaGrafo(anno, colore);
    	
    	//stampa delle statistiche
    	this.txtResGrafo.setText("Grafo creato.\n");
    	this.txtResGrafo.appendText("Ci sono " + this.model.getNVertici() + " vertici.\n");
    	this.txtResGrafo.appendText("Ci sono " + this.model.getNArchi() + " archi.\n\n");
    	
    	//stampa dei top-3 archi, con annessi nodi ripetuti
    	List<Arco> top3_archi = this.model.getTop3Archi();
    	this.txtArchi.clear();
    	for(Arco a : top3_archi) {
    		this.txtArchi.appendText(a + "\n");
    	}
    	
    	Set<Products> nodi_ripetuti = this.model.getNodiRipetutiInArchi(top3_archi);
    	if(nodi_ripetuti.isEmpty()) {
    		this.txtArchi.appendText("\nNon ci sono nodi ripetuti.\n");
    	} else {
    		this.txtArchi.appendText("\nI nodi ripetuti sono: \n");
    		this.txtArchi.appendText(nodi_ripetuti+"\n");
    	}
    	
    	this.cmbProdotto.getItems().clear();
    	this.cmbProdotto.getItems().addAll(this.model.getVertici());
    }
    
    
    @FXML
    void initialize() {
        assert btnCercaPercorso != null : "fx:id=\"btnCercaPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbColore != null : "fx:id=\"cmbColore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProdotto != null : "fx:id=\"cmbProdotto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArchi != null : "fx:id=\"txtArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResGrafo != null : "fx:id=\"txtResGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
        this.cmbAnno.getItems().add(2015);
        this.cmbAnno.getItems().add(2016);
        this.cmbAnno.getItems().add(2017);
        this.cmbAnno.getItems().add(2018);

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.cmbColore.getItems().addAll(this.model.getColori());
    }

}
