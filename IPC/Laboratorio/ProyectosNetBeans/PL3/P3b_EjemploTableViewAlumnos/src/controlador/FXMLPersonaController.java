/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class FXMLPersonaController implements Initializable {

    @FXML
    private TextField nombreText;
    @FXML
    private TextField apellidosText;
 
    private boolean isOK = false;
    private Persona persona_local;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    private void okPersona(ActionEvent event) {
          isOK = true;
          if(persona_local == null){//si es null es que estem creant una nova.
            persona_local=new Persona();
          }//si no es null es que l'estem modificant
          persona_local.setNombre(nombreText.getText());
          persona_local.setApellidos(apellidosText.getText());
          
          //Per tancar la finestra
          ((Stage)nombreText.getScene().getWindow()).close();
          
    }

    @FXML
    private void cancel(ActionEvent event) {
        //Per tancar la finestra
          ((Stage)nombreText.getScene().getWindow()).close();
    }

    boolean isOKPulsado() {
        return isOK;
    }

    public Persona getPersonaNueva(){
        return persona_local;
    }

    void setPersonaModificar(Persona selectedItem) {
        persona_local = selectedItem;
        apellidosText.setText(persona_local.getApellidos());
        nombreText.setText(persona_local.getNombre());
    }
}
