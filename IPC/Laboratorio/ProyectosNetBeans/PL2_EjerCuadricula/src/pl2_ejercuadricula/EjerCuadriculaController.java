/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl2_ejercuadricula;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class EjerCuadriculaController implements Initializable {

    @FXML
    private GridPane gridAndreu;
    @FXML
    private Circle circuloAndreu;
    private double X_inici;
    private double Y_inici;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void moure(KeyEvent event) {
        KeyCode codi = event.getCode();
        int fila = gridAndreu.getRowIndex(circuloAndreu);
        int columna = gridAndreu.getColumnIndex(circuloAndreu);
        
        switch (codi){
            case UP: 
                fila = ((fila-1)+5) % 5;
                gridAndreu.setRowIndex(circuloAndreu, fila); 
                break;
            
            case DOWN:
                fila = ((fila+1)+5) % 5;
                gridAndreu.setRowIndex(circuloAndreu, fila);
                break;
                
            case RIGHT:
                columna = ((columna + 1) + 5) % 5;
                gridAndreu.setColumnIndex(circuloAndreu, columna);
                break;
                
            case LEFT:
                columna = ((columna - 1) + 5) % 5;
                gridAndreu.setColumnIndex(circuloAndreu, columna);
                break;
        }
         if (event.isAltDown() && (codi == KeyCode.UP || codi == KeyCode.DOWN)){
           columna = ((columna + 1) + 5) % 5; 
                gridAndreu.setColumnIndex(circuloAndreu, columna);
                
        }
        
    }

    @FXML
    private void moureRatoli(MouseEvent event) {
        int columna = (int) (event.getSceneX() / (gridAndreu.getWidth() / 5));
        int fila = (int) (event.getSceneY() / (gridAndreu.getHeight() / 5));
        
        gridAndreu.setConstraints(circuloAndreu, columna, fila);
    }

    @FXML
    private void saleCirculo(MouseEvent event) { //Cuando quitas el raton del circulo cambia la forma
        circuloAndreu.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void entraCirculo(MouseEvent event) {//Cuando pones el raton en el circulo aparece una mano
        circuloAndreu.setCursor(Cursor.OPEN_HAND);
    }

    @FXML
    private void sueltaCirculo(MouseEvent event) {
        circuloAndreu.setTranslateX(0);
        circuloAndreu.setTranslateY(0);
        moureRatoli(event);
    }

    @FXML
    private void arrastraCirculo(MouseEvent event) {
        circuloAndreu.setTranslateX(event.getSceneX()- X_inici);
        circuloAndreu.setTranslateY(event.getSceneY()- Y_inici);
    }

    @FXML
    private void aprietaCirculo(MouseEvent event) {
        X_inici = event.getSceneX();
        Y_inici = event.getSceneY();
    }
    
}
