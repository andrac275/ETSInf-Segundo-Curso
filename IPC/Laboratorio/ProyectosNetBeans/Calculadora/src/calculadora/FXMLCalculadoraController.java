/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class FXMLCalculadoraController implements Initializable {

    @FXML
    private TextField pantalla;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_7(ActionEvent event) {
        digitoPantalla("7");
    }

    @FXML
    private void click_8(ActionEvent event) {
        digitoPantalla("8");
    }

    @FXML
    private void click_9(ActionEvent event) {
        digitoPantalla("9");
    }

    @FXML
    private void click_4(ActionEvent event) {
        digitoPantalla("4");
    }

    @FXML
    private void click_5(ActionEvent event) {
        digitoPantalla("5");
    }

    @FXML
    private void click_6(ActionEvent event) {
        digitoPantalla("6");
    }

    @FXML
    private void click_1(ActionEvent event) {
        digitoPantalla("1");
    }

    @FXML
    private void click_2(ActionEvent event) {
        digitoPantalla("2");
    }

    @FXML
    private void click_3(ActionEvent event) {
        digitoPantalla("3");
    }

    @FXML
    private void click_0(ActionEvent event) {
        digitoPantalla("0");
    }
    
    @FXML
    private void click_punto(ActionEvent event) {
        if(!Digito && !Punto){
            pantalla.setText("0.");
            Digito = true;
        }else if(!Punto){
            String valActual=pantalla.getText();
            pantalla.setText(valActual + ".");
        }
        Punto = true;
    }
    
    private boolean Digito = false; //Indica si se ha presionado un digito.
    private boolean Punto = false;  //Indica si se ha presionado el punto.
    private int numOperandos = 0;
    private double Operando1, Operando2;
    private char Operador = ' ';
    
    private void digitoPantalla(String numero){
        if (!Digito && numero.equals("0")){ return;}
        
        if(!Digito){
            pantalla.setText("");
            Punto = false;
        }
        
        String valActual = pantalla.getText();
        pantalla.setText(valActual + numero);
        Digito = true;
        
    }

    @FXML
    private void click_borrar(ActionEvent event) {
        Digito = false; 
        Punto = false;  
        numOperandos = 0;
        Operando1 = 0; 
        Operando2 = 0;
        Operador = ' ';
        pantalla.setText("0");
    }

    @FXML
    private void click_dividir(ActionEvent event) {
        evalOperador("/");
    }

    @FXML
    private void click_multiplicar(ActionEvent event) {
        evalOperador("*");
    }

    @FXML
    private void click_restar(ActionEvent event) {
        evalOperador("-");
    }

    @FXML
    private void click_suma(ActionEvent event) {
        evalOperador("+");
    }

    @FXML
    private void click_igual(ActionEvent event) {
        evalOperador("=");
    }
    
    private void evalOperador(String Operador){
        if (Digito){numOperandos++;}
        
        if(numOperandos == 1) {
            Operando1=Double.parseDouble(pantalla.getText());
        }   
        
        if(numOperandos == 2){
            Operando2=Double.parseDouble(pantalla.getText());
            
            switch(this.Operador){
                case '+':
                    Operando1 = Operando1 + Operando2;
                    break;
                case '-':
                    Operando1 = Operando1 - Operando2;
                    break;
                case '*':
                    Operando1 = Operando1 * Operando2;
                    break;
                case '/':
                    Operando1 = Operando1 / Operando2;
                    break;
                case '=':
                    Operando1 = Operando2;
                    break;
            }
            
            pantalla.setText(String.valueOf(Operando1));
            numOperandos--;
            Punto = false;            
        }
        Digito = false;
        this.Operador = Operador.charAt(0);
    }
    
}
