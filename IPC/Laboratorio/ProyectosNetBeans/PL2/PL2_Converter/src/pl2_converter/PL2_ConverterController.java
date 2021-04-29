/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl2_converter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class PL2_ConverterController implements Initializable {

    @FXML
    private TextField campoInput;
    @FXML
    private TextField campoOutput;
    @FXML
    private Slider slider;
    @FXML
    private Text ratioText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
            
    @FXML
    private void botonConvert(ActionEvent event) {
        calculoOidora();
    }

    @FXML
    private void botonClear(ActionEvent event) {
        campoInput.setText("0");
        campoOutput.setText("0");
    }

    @FXML
    private void botonAutomatico(ActionEvent event) {
        automatico();
    }
    
    //Atributos
    private double multiplicando, multiplicador, resultado;
    private boolean automatico = false;
    
    public void automatico(){
        automatico = !automatico;
    }
    
    public void calculoEnlace(){
        multiplicando = Double.parseDouble(campoInput.getText());
               
        slider.valueProperty().addListener((observable, ildVal,newVal) -> 
            {ratioText.setText(newVal +"");});
                
        multiplicador = Double.parseDouble(ratioText.getText());
        
        resultado = multiplicando * multiplicador;
        campoOutput.setText(String.valueOf(resultado));
    }
    
    public void calculoOidora(){
        multiplicando = Double.parseDouble(campoInput.getText());
               
        slider.valueProperty().addListener((observable, ildVal,newVal) -> 
            {ratioText.setText(newVal +"");});
                
        multiplicador = Double.parseDouble(ratioText.getText());
        
        resultado = multiplicando * multiplicador;
        campoOutput.setText(String.valueOf(resultado));
    }
    
}
