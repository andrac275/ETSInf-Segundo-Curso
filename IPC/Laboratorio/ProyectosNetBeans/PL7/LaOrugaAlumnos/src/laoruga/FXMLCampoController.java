/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laoruga;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author DSIC_jsoler
 */
public class FXMLCampoController implements Initializable {

    @FXML
    private Slider sliderVelocitat;

    enum Direccion {
        ARRIBA, ABAJO, IZQUIERDA, DERECHA
    }

    class Posicion {

        private int x;

        public Posicion() {
        }

        public Posicion(Integer columnIndex, Integer rowIndex) {
            x = columnIndex;
            y = rowIndex;
        }

        /**
         * Get the value of x
         *
         * @return the value of x
         */
        public int getX() {
            return x;
        }

        /**
         * Set the value of x
         *
         * @param x new value of x
         */
        public void setX(int x) {
            this.x = x;
        }

        private int y;

        /**
         * Get the value of y
         *
         * @return the value of y
         */
        public int getY() {
            return y;
        }

        /**
         * Set the value of y
         *
         * @param y new value of y
         */
        public void setY(int y) {
            this.y = y;
        }

        public boolean isVecina(Posicion p) {
            int difY = Math.abs(p.getY() - y);
            if (((p.getX() == x) && (Math.abs(p.getY() - y) == 1)) || ((p.getY() == y) && (Math.abs(p.getX() - x) == 1))) {
                return true;
            }
            return false;
        }

        public Direccion toVecina(Posicion p) {
            if (p.y < y) {
                return Direccion.ARRIBA;
            } else if (p.y > y) {
                return Direccion.ABAJO;
            } else if (p.x < x) {
                return Direccion.IZQUIERDA;
            } else if (p.x > x) {
                return Direccion.DERECHA;
            } else {
                return null;
            }

        }

        @Override
        public String toString() {
//            return super.toString(); //To change body of generated methods, choose Tools | Templates.
            String resultado = x + "," + y;
            return resultado;
        }

    }

    class TramoOruga {

        private final Rectangle rect;

        private final ArrayList<Posicion> celdasCambio = new ArrayList();

        private Direccion movimiento;

        public ArrayList<Posicion> getCeldasCambio() {
            return celdasCambio;
        }

        public Rectangle getRectangle() {
            return rect;
        }

        /**
         * Get the value of movimiento
         *
         * @return the value of movimiento
         */
        public Direccion getMovimiento() {
            return movimiento;
        }

        /**
         * Set the value of movimiento
         *
         * @param movimiento new value of movimiento
         */
        public void setMovimiento(Direccion movimiento) {
            this.movimiento = movimiento;
        }

        public TramoOruga(Rectangle rec, Direccion movimiento) {
            this.rect = rec;
            this.movimiento = movimiento;
        }

    }

    @FXML
    private GridPane grid;
    @FXML
    private ToggleGroup onof;
    private Button moveButton;
    
    
    private static int TRAMOS_ORUGA = 6;
    private int numero_col, numero_row;
    private IntegerProperty celda_width = new SimpleIntegerProperty();
    private IntegerProperty celda_heigth = new SimpleIntegerProperty();
    private ArrayList<TramoOruga> oruga = new ArrayList<>(); //la cabeza de la oruga es oruga(0)
    private MiServicio moverOruga;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Slider oruga
        sliderVelocitat.valueProperty().addListener((a,b,c)->{
            if((int)c==0){sliderVelocitat.setValue(1);}
        });
        
        moverOruga = new MiServicio();
        moverOruga.start();
        
        //inializo el tamaño de la celda del grid
        numero_col = grid.getColumnConstraints().size();
        numero_row = grid.getRowConstraints().size();
        celda_width.bind(Bindings.add(Bindings.divide(grid.widthProperty(), numero_col), -2));
        celda_heigth.bind(Bindings.add(Bindings.divide(grid.heightProperty(), numero_row), -2));
        //----------------------------------------------------------------------------------------
        // añadimos pane a cada celdad para poder pintar lineas
        for (int i = 0; i < numero_col; i++) {
            for (int j = 0; j < numero_row; j++) {
                Pane pane = new Pane();
                pane.getStyleClass().add("my-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                grid.add(pane, i, j);
            }
        }
        //-------------------------------------------------------------
        //inicializamos la oruga
        for (int i = 0; i < TRAMOS_ORUGA; i++) {
            Rectangle rec = new Rectangle();
            oruga.add(new TramoOruga(rec, Direccion.IZQUIERDA));
            // añadimos binding para que el tamaño se ajuste al tamaño de la celda
            rec.widthProperty().bind(celda_width);
            rec.heightProperty().bind(celda_heigth);
            rec.getStyleClass().add("my-oruga");
            grid.add(rec, i, 0);
        }


    }
    private void movecell(ActionEvent event) {
        mueveOruga();
    }
    
    @FXML
    private void run_stop(ActionEvent event) {
        if (((ToggleButton) event.getSource()).isSelected()) {
            moverOruga.cancel();
            //moveButton.setDisable(true);
        } else {
            moverOruga.restart();
            //moveButton.setDisable(false);
        }
    }

    @FXML
    private void pulsaCelda(MouseEvent event) {
        Posicion celdaPulsada = calculaCelda(event.getSceneX(), event.getSceneY());
        // si la celda esta al lado de la oruga la añade al tramo 0
        if (celdaPulsada.isVecina(celdaGrid(oruga.get(0).getRectangle()))) {
            //anyadir una celda de cambio a la lista de celdas de cambio del tramo 0 de la oruga
            oruga.get(0).getCeldasCambio().add(celdaPulsada);
        }
    }

    public void mueveOruga() {
        for (int i = 0; i < TRAMOS_ORUGA; i++) {
            TramoOruga tramoOruga = oruga.get(i);
            //si hay cambio de direccion antes de moverme actualizo mi direccion y le paso el cambio al tramo posterior
            if (giro(tramoOruga)) {
                // sin tengo que girar y no soy el ultimo tramo le paso a mi tramos sucesor la celda de giro
                // y lo consumo, es decir lo borro de mi lista de celdas de giro
                if (i < (TRAMOS_ORUGA-1)) { //no es el ultimo tramo // tamano de la oruga
                    oruga.get(i + 1).getCeldasCambio().add(tramoOruga.getCeldasCambio().get(0));
                }
                tramoOruga.getCeldasCambio().remove(0);
            }
            //según mi dirección me muevo a la siguiente celda del gridpane
            switch (tramoOruga.getMovimiento()) {
                case ARRIBA:
                    grid.setRowIndex(tramoOruga.getRectangle(),(grid.getRowIndex(tramoOruga.getRectangle()) - 1 + numero_row) % numero_row);
                    break;
                case ABAJO:
                    grid.setRowIndex(tramoOruga.getRectangle(),(grid.getRowIndex(tramoOruga.getRectangle()) + 1 + numero_row) % numero_row);
                    break;
                case IZQUIERDA:
                    grid.setColumnIndex(tramoOruga.getRectangle(),(grid.getColumnIndex(tramoOruga.getRectangle()) - 1 + numero_col) % numero_col);
                    break;
                case DERECHA:
                    grid.setColumnIndex(tramoOruga.getRectangle(),(grid.getColumnIndex(tramoOruga.getRectangle()) + 1 + numero_col) % numero_col);
                    break;
            }
        }
    }

    // comprueba si hay que girar y cambia la direccion en el tramo, Despues siempre se repinta
    private boolean giro(TramoOruga tramoOruga) {
        if (tramoOruga.getCeldasCambio().size() > 0) {
            Posicion celdaGiro = tramoOruga.getCeldasCambio().get(0);
            Posicion celdaActual = celdaGrid(tramoOruga.getRectangle());
            if (celdaActual.isVecina(celdaGiro)) {
                tramoOruga.setMovimiento(celdaActual.toVecina(celdaGiro));
                return true;
            } 
        }
        return false;
    }
    
    private Posicion calculaCelda(double x_raton, double y_raton) {
        return new Posicion((int)(x_raton/(grid.getWidth()/numero_col)),(int)(y_raton/(grid.getHeight()/numero_row)));
    }
    
    private Posicion celdaGrid(Rectangle rec){
        return new Posicion(grid.getColumnIndex(rec), grid.getRowIndex(rec));
    }

    class MiServicio extends Service<Void>{

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    do {                    
                        Thread.sleep(sliderVelocitat.valueProperty().longValue());
                        mueveOruga();
                    } while (true);

                }
            };
        }
    }
    
}