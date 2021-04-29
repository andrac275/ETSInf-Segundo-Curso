/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class FXMLPersonasTableController implements Initializable {

    
    private ObservableList<Persona> datos = null; // Colecci�n vinculada a la vista.
    
    @FXML
    private Button addButton;
    @FXML
    private Button modButton;
    @FXML
    private Button delButton;
    @FXML
    private TableView<Persona> personasTable;
    @FXML
    private TableColumn<Persona, String> nomColum;
    @FXML
    private TableColumn<Persona, String> apellidoColumn;


    
    private void inicializaModelo() {
        ArrayList<Persona> misdatos = new ArrayList<Persona>();
        misdatos.add(new Persona("Pepe", "García"));
        misdatos.add(new Persona("María", "Pérez"));
        datos=FXCollections.observableList(misdatos);
        
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        inicializaModelo();

        // ANDREU ACI: inicializa la tabla y las columnas
        personasTable.setItems(datos);
        nomColum.setCellValueFactory(datoPersona -> datoPersona.getValue().NombreProperty());
        apellidoColumn.setCellValueFactory(datoPersona -> datoPersona.getValue().ApellidosProperty());

        
        //ANDREU: Bindings
        //Desactivar boto borrar si no tenim res marcat.
        delButton.disableProperty().bind(
                Bindings.equal(-1, personasTable.getSelectionModel().selectedIndexProperty()));
        //Desactivar boto modificar si no tenim res marcat.
        modButton.disableProperty().bind(
                Bindings.equal(-1, personasTable.getSelectionModel().selectedIndexProperty()));
        
    }

    @FXML
    private void addPersona(ActionEvent event) throws IOException {
        //Andreu
        //Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLPersona.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXMLPersona.fxml"));
        Parent root = loader.load();
        FXMLPersonaController controladorFinestra2=loader.getController();
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        //ANDREU:La seguent linia bloqueja la finestra de la que venim, per a que no puguem fer res des de la que vvenim
        stage.initModality(Modality.APPLICATION_MODAL);      
        stage.setTitle("Afegir persona");
        stage.setScene(scene);
        //stage.show();
        stage.showAndWait();    //Quan se tanca l'altra finestra, torna aci
        
        if(controladorFinestra2.isOKPulsado()){
            datos.add(controladorFinestra2.getPersonaNueva());
        }
        
    }

    @FXML
    private void modPersona(ActionEvent event) throws IOException {
        //Andreu
        //Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLPersona.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXMLPersona.fxml"));
        Parent root = loader.load();
        FXMLPersonaController controladorFinestra2=loader.getController();
         Scene scene = new Scene(root);  
        //Passar la persona que volem modificar
        controladorFinestra2.setPersonaModificar(
                personasTable.getSelectionModel().getSelectedItem());
        
             
        Stage stage = new Stage();
        //ANDREU:La seguent linia bloqueja la finestra de la que venim, per a que no puguem fer res des de la que vvenim
        stage.initModality(Modality.APPLICATION_MODAL);      
        stage.setTitle("Modificar persona");
        stage.setScene(scene);
        stage.show();    //Quan se tanca l'altra finestra, torna aci
        
        
    }

    @FXML
    private void borPersona(ActionEvent event) {
        //Andreu:
        datos.remove(personasTable.getSelectionModel().getSelectedIndex());
    }
    
      
}
