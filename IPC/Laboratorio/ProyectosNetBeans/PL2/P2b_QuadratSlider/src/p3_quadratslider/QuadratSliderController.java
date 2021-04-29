/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3_quadratslider;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class QuadratSliderController implements Initializable {

    @FXML
    private Rectangle rectangle;
    @FXML
    private Slider h_slider;
    @FXML
    private Slider v_slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO el codigo de la iniciliacion de la interfaz
        
        //CODI AMB LISTENER
//        h_slider.valueProperty().addListener((a,b,c)->{   
//            rectangle.setWidth((double) c);
//        });
//        
//        v_slider.valueProperty().addListener((a,b,c)->{
//            rectangle.setHeight((double) c);
//        });
        
        //CODI AMB BINDING. Es com lo anterior pero mes rapid, sense lambda ni res
        
        rectangle.widthProperty().bind(h_slider.valueProperty());
        rectangle.heightProperty().bind(v_slider.valueProperty());
        
        
    }    
    
}
