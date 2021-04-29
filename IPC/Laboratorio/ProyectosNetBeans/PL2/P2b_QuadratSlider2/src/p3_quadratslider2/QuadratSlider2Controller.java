/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3_quadratslider2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class QuadratSlider2Controller implements Initializable {

    @FXML
    private StackPane contenedor;
    @FXML
    private Rectangle rectangle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Aço es per a que quan redimensiones, el quadrat se redimensione tambe.
        rectangle.widthProperty().bind(Bindings.divide(contenedor.widthProperty(), 2)); //<---Divideix una propietat entre 2. Se te que fer aixi.
        rectangle.heightProperty().bind(Bindings.divide(contenedor.heightProperty(), 2));
    }    
    
}
