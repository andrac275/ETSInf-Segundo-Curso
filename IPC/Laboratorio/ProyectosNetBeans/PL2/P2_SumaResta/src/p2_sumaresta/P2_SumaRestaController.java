/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2_sumaresta;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class P2_SumaRestaController implements Initializable {

    @FXML
    private Text pantalla;
    @FXML
    private TextField valor;
    @FXML
    private Text textoRestando;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void boton1(ActionEvent event) {
        operacion(1);
    }

    @FXML
    private void boton5(ActionEvent event) {
        operacion(5);
    }

    @FXML
    private void boton10(ActionEvent event) {
        operacion(10);
    }

    @FXML
    private void botonRestar(ActionEvent event) {
        if (pulsarRestar()){        
            textoRestando.setText("Estás restando!!!");
        }else{
            textoRestando.setText("");
        } 
    }

    @FXML
    private void botonSuma(ActionEvent event) {
        botonSuma();
    }
    
    private int resultado;
    private int operandoNuevo;
    private boolean restando = false;
        
    public boolean pulsarRestar(){        
        restando = !restando;
        return restando;
    }
    
    public void operacion(int codigo){
        
        if(!restando){ //Si suma
            switch (codigo){
                case 1:
                    resultado = resultado + 1;
                    break;
                case 5:
                    resultado = resultado + 5;
                    break;
                case 10:
                    resultado = resultado + 10;
                    break;
            }
        }else{ //Si resta
            switch (codigo){
                case 1:
                    resultado = resultado - 1;
                    break;
                case 5:
                    resultado = resultado - 5;
                    break;
                case 10:
                    resultado = resultado - 10;
                    break;
            }
        }
        pantalla.setText(String.valueOf(resultado));
    }

    public void botonSuma(){
        String aux = pantalla.getText();
        resultado = Integer.parseInt(aux);
        aux = valor.getText();
        operandoNuevo=Integer.parseInt(aux);
        if(!restando){
            resultado = resultado + operandoNuevo;
        }else{
            resultado = resultado - operandoNuevo;
        }
        pantalla.setText(String.valueOf(resultado));
    }
    
}

