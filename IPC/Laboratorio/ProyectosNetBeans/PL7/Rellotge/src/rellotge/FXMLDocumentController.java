/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rellotge;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 *
 * @author Andrac
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Text rellotgeText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Possar la hora actual per a començar al rellotgeText
        rellotgeText.setText(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
        
        
        TareaReloj tarea = new TareaReloj();
        Thread filSecundari = new Thread(tarea);
        //El Daemon mata els fils secundaris quan tanquem la maquina
        filSecundari.setDaemon(true);
        
        //Metodo 1 UpdateMessage: Fer el binding amb el fil que actulitza l'hora i la etiqueta
        //rellotgeText.textProperty().bind(tarea.messageProperty());
        
        //Metodo 2 Platform:
            //setTexto li pasa a la clase TareaReloj el rellotgeText com a StringProperty
        tarea.setTexto(rellotgeText.textProperty());
        
        filSecundari.start();
    }    
    
}
//Task es Void perque no retorna res. No acaba mai
class TareaReloj extends Task<Void>{
    //Per al metodo 2. Comentar si gastem metode 1.
            //Atribut per al metodo 2, perque cal passarli la hora del rellotge com a property amb el set de despres
            private StringProperty texto;
            public void setTexto(StringProperty txt){
                texto = txt;
            }
    
    
    //Variable que se gasta per a esperar dormint.
    private long DELAY = 1000;
    @Override
    protected Void call() throws Exception {
        //Bucle infinit per a que cada 'DELAY' temps, comprove l'hora del sistema.
        do {       
            //Temps que te que dormir el fil
            Thread.sleep(DELAY);
         
            //Metodo 1: Amb updateMessage
            //Obtenir l'hora actual
//            updateMessage(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));

            //Metodo 2: Amb Platform
            Platform.runLater(() -> {
                texto.set(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));                 
            });

        } while (true);
        
    }

}