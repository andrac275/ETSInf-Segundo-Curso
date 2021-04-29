/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3_puzzle8;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.lang.Math.*;
import java.util.Random;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class Puzzle8Controller implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private Button boton5;
    @FXML
    private Button boton6;
    @FXML
    private Button boton7;
    @FXML
    private Button boton8;
    @FXML
    private Button botonIniciar;
    @FXML
    private Text movimientos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    


    @FXML
    private void clickBoto(MouseEvent event) {
        moverFicha((Node) event.getSource());
        if(comprobar()){movimientos.setText("Lo has conseguido!!!");}
    }
    
    @FXML
    private void clickIniciar(MouseEvent event) {
        movimientos.setText("0");
        contador.setValue(0);
        reiniciarJuego();
    }
    
    //Atributos
    int columnaVacia;
    int filaVacia;
    int columna;
    int fila;
    boolean correcto;
    int[] vectorInicial;
    IntegerProperty contador = new SimpleIntegerProperty(0); //Inicialitza contador amb envolcall a 0.
    
    public void moverFicha(Node boton){
        correcto = false;
        columna = grid.getColumnIndex(boton);
        fila = grid.getRowIndex(boton);
        
        //Comprobar casilla vacia es accesible.
        if((Math.abs(columnaVacia - columna) == 1) && ((filaVacia- fila) == 0)){correcto = true;}
        if((Math.abs(filaVacia - fila) == 1) && ((columnaVacia - columna) == 0)){correcto = true;}
        
        if(correcto){    
            
            grid.setColumnIndex(boton, columnaVacia);
            grid.setRowIndex(boton, filaVacia);
            
            columnaVacia = columna;
            filaVacia = fila;
            //Augmentat en 1 el wrapper del contador
            contador.setValue(contador.getValue() +1);
            
            //Binding.
            //Cal fer un _Bindings format perque contador es de un tipus i te que ser de tipus String 
            movimientos.textProperty().bind(Bindings.format("%d",contador));
        }
        
    }

    private void reiniciarJuego() {
        vectorInicial = generarVectorAleatorio(8);
        int i=0;
        for (Node node: grid.getChildren()){
            Button ficha = (Button) node;
            int col=i%3;
            int row= i/3;
            ficha.setText(Integer.toString(vectorInicial[i]));
            grid.setConstraints(ficha,col,row);
            i++;
        }
        columnaVacia=2;
        filaVacia= 2;
    }
    
    static public int[] generarVectorAleatorio(int n) {
        int inversiones;
        int[] numeros = new int[n];
        int[] resultado = new int[n];
        Random rnd = new Random();
        do {
            //se rellena una matriz ordenada (1..n)
            for (int i = 0; i < n; i++) {
                numeros[i] = i + 1;
            }
            
            //se se genera el vector aleatorio sin elementos repetidos)
            int k = n;
            int res;
            for (int i = 0; i < n; i++) {
                res = rnd.nextInt(k);
                resultado[i] = numeros[res];
                numeros[res] = numeros[k - 1];
                k--;
            }
        // calculamos el numero de inversiones del vector resultante
            inversiones = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (resultado[i] > resultado[j]) {
                        inversiones++;
                    }
                }
            }
        } while (inversiones % 2 != 0);
        
        // el estado incial tiene solucion si el numero de inversiones es par
        return resultado;
        }

    private boolean comprobar() {
    //Comprobar si les fixes estan en la seva posicio.
        for (Node i : grid.getChildren()) {//Recorre totes les celes de grid.
            Button boto = ((Button) i);
            //Obte la posicio lineal del botó que estem estudiant en aquesta iteracio.
            int posBoto = ((grid.getRowIndex(boto) * 3) + grid.getColumnIndex(boto)) + 1;
            //Obtenir posicio lineal del hueco, on está dins la cuadricula
            int posHueco = filaVacia * 3 + columnaVacia + 1;
            if (posBoto > posHueco) {
                posBoto--;
            }
            if (Integer.parseInt(boto.getText()) != posBoto) {
                //Ha trobat algo que no estava en el lloc.
                return false;
            }
        }
        //Estava tot en el lloc perque el bucle anterior no ha eixit amb false.
        return true;
    }
      
}
