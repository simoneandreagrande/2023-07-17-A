package it.polito.tdp.gosales;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {

    private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<?> cmbAnno;

    @FXML
    private ComboBox<?> cmbColore;

    @FXML
    private ComboBox<?> cmbProdotto;

    @FXML
    private TextArea txtArchi;

    @FXML
    private TextArea txtResGrafo;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCreaGrafo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
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
    }

}
