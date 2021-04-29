/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaventanas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class Finestra1Controller implements Initializable {

    @FXML
    private Button anterior;

    private Stage primaryStage;
    private Scene escenaPrincipal;
    
    
    void initStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        escenaPrincipal = primaryStage.getScene();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void siguienteAction(ActionEvent event) {
        
    }

    @FXML
    private void anteriorAction(ActionEvent event) {
        primaryStage.setTitle("Multifinestres");
        primaryStage.setScene(escenaPrincipal);
    }

    
    
}
