/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaventanas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage primaryStage;
    
    public void initStage(Stage primaryStage) {
       this.primaryStage = primaryStage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void siguienteAction(ActionEvent event) throws IOException {
        primaryStage.setTitle("Finestra 1");
        FXMLLoader miCargador = new FXMLLoader (getClass().getResource("Finestra1.fxml"));
        VBox root = (VBox) miCargador.load();
        Finestra1Controller finestra1 = miCargador.<Finestra1Controller>getController();
                finestra1.initStage(primaryStage);
                Scene scene = new Scene (root,400,400);
                primaryStage.setScene(scene);
                primaryStage.show();
        }
    }

   
