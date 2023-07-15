package it.polito.tdp.gosales;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.Arco;
import it.polito.tdp.gosales.model.Model;
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
    private ComboBox<?> cmbProdotto;

    @FXML
    private TextArea txtArchi;

    @FXML
    private TextArea txtResGrafo;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaPercorso(ActionEvent event) {

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {

        String brand = cmbColore.getValue();
        Integer year = cmbAnno.getValue();

        this.model.buildGraph(brand, year);

        txtResGrafo.setText("Num Nodi: " + this.model.getNumNodi()+"\n");
        txtResGrafo.appendText("Num Archi: " + this.model.getNumArchi());

        txtArchi.setText("Top 3 archi: \n");
        List<Arco> topArchi = this.model.getTopArchi();

        for (Arco a : topArchi)
            txtArchi.appendText(a+"\n");

        txtArchi.appendText("Nodi che compaiono almeno due volte:\n");
        List<Integer> repeatedProdsID = this.model.repeatedProducts();

        for (Integer i : repeatedProdsID)
            txtArchi.appendText("Product ID : " + i + "\n");

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

    }
    
    public void setModel(Model model) {
    	this.model = model;
        cmbAnno.getItems().setAll(this.model.getAllYears());
        cmbColore.getItems().setAll(this.model.getAllColors());
    }

}
