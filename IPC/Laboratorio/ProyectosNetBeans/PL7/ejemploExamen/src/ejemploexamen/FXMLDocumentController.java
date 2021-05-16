/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploexamen;

import static java.lang.String.valueOf;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ToggleGroup menu;
    @FXML
    private TextField cantitatIntroduida;
    @FXML
    private TextField cantitatResultant;
    @FXML
    private RadioMenuItem libra;
    @FXML
    private RadioMenuItem dolar;
    @FXML
    private RadioMenuItem rupia;
    @FXML
    private RadioMenuItem yuan;
    
    //Atributs
   private boolean libraTog;
   private boolean dolarTog;
   private boolean rupiaTog;
   private boolean yuanTog;
   
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        menu.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                 if(new_toggle.equals(libra)){
                     System.out.println("lib");
                     libraTog = true; dolarTog = false; rupiaTog = false; yuanTog = false;
                 } else if(new_toggle.equals(dolar)){
                     System.out.println("Dolar");
                     libraTog = false; dolarTog = true; rupiaTog = false; yuanTog = false;
                 }else if(new_toggle.equals(rupia)){
                     System.out.println("rupia");
                     libraTog = false; dolarTog = false; rupiaTog = true; yuanTog = false;
                 }else if(new_toggle.equals(yuan)){
                     System.out.println("yuan");
                     libraTog = false; dolarTog = false; rupiaTog = false; yuanTog = true;
                 }
            } 
        });
        final StringProperty borrar = cantitatIntroduida.textProperty();
        borrar.addListener((a,b,c)->{
            if(!c.equals(b)){
                cantitatResultant.setText("");
            }
        });   
    }    

    
    
    @FXML
    private void salirAction(ActionEvent event) {
        ((Stage)cantitatIntroduida.getScene().getWindow()).close();
    }

    @FXML
    private void enterPolsat(KeyEvent event) {
        KeyCode codi = event.getCode();
        String str= cantitatIntroduida.getText();
        boolean numeros = false;
               
        if(codi.equals(KeyCode.ENTER)){             
            if(str.matches("^\\d+(\\,\\d+)*$") && str.length() > 2) {                
                numeros = true;
            }
            if(numeros){
                double valor = Double.parseDouble(str.replace(",","."));
                conversio(valor);                
                System.out.println(valor);
            }else{
                cantitatResultant.setText("Error en formato de numeros");
            }  
        }
    
    }

    private void conversio(double valor) {
        double dolar = 1.19;
        double libra = 0.88;
        double rupia = 80.22;
        double yuan =7.5;
        double res;
        DecimalFormat formatter = new DecimalFormat("0.000");
        
        if (libraTog){
            res = valor*libra;
            //formatter.format(res);
            cantitatResultant.setText(valueOf(formatter.format(res)));
        } else if (dolarTog){
            res = valor*dolar;
            cantitatResultant.setText(valueOf(formatter.format(res)));
        }else if (rupiaTog){
            res = valor*rupia;
            cantitatResultant.setText(valueOf(formatter.format(res)));
        }else if (yuanTog){
            res = valor*yuan;
            cantitatResultant.setText(valueOf(formatter.format(res)));
        }else{
            cantitatResultant.setText("Selecciona una moneda");
        }
                
    }

    
    
}
