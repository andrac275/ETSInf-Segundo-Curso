/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsole
 */
public class FXMLPersonaController implements Initializable {

    private boolean ok_polsat = false;
    
    @FXML
    private TextField nombreTextF;
    @FXML
    private TextField apellidoTextF;
    @FXML
    private Button okCambiosBoton;
    
    
    private Persona mipersona;
    private ObservableList<Persona> datos = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Habilitat boto OK nomes si els camps tenen text.
        okCambiosBoton.disableProperty().bind(Bindings.or(
                Bindings.equal(nombreTextF.textProperty(),""),
                Bindings.equal(apellidoTextF.textProperty(),"")));
        
    }    

    @FXML
    private void okCambios(ActionEvent event) {
        mipersona.setNombre(nombreTextF.getText());
        mipersona.setApellidos(apellidoTextF.getText());
        ok_polsat = true;
        //Per a poder tancar la finestra se necesita l'identificador de la escena/finestra i
        //cal fer-li un casting per que siga de tipus Stage. Ara ja se li pot fer el close().
        //Si no posem esta linea, despres de modificar o afegir, si apretem OK no tanca la finestra
        //i no torna el control
        ((Stage) apellidoTextF.getScene().getWindow()).close();
    }

    @FXML
    private void cancelCambios(ActionEvent event) {
        //Per a poder tancar la finestra se necesita l'identificador de la escena/finestra i
        //cal fer-li un casting per que siga de tipus Stage. Ara ja se li pot fer el close().
        //Si no posem esta linea, despres de modificar o afegir, si apretem Cancel no tanca la finestra
        //i no torna el control
        ((Stage) apellidoTextF.getScene().getWindow()).close();
    }

    void setPersona(Persona selectedItem) {
        nombreTextF.setText(selectedItem.getNombre());
        apellidoTextF.setText(selectedItem.getApellidos());
        //guardarla en un atribut de la clase. No la guardem com a local de la funcio per a que no se perga.
        mipersona = selectedItem;
    }

    
    
    boolean isOK() {
        return ok_polsat;        
    }

    void addPersona(ObservableList<Persona> datos) {
       mipersona = new Persona(nombreTextF.getText(), apellidoTextF.getText());
       datos.add(mipersona);
    }
    
}
