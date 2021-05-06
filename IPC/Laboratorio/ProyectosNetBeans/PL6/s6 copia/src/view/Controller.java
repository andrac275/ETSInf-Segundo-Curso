/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.Model;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class Controller implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private BarChart<String, Number> bar;
    private Model modelLocal;
    @FXML
    private HBox cajaBotones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(Node node : cajaBotones.getChildren()){
            ((Button)node).defaultButtonProperty().bind(((Button)node).focusedProperty());
        }
        
        bar.setTitle("Notas");
        pie.setTitleSide(Side.LEFT);
        pie.setTitle("Notas");
               
    }    

    @FXML
    private void botoPremut(ActionEvent event) {
        modelLocal.sumarTipoNota(((Button)event.getSource()).getText());
    }

    public void setModel(Model model) {
        modelLocal=model;
        pie.setData(modelLocal.getPieData());
        XYChart.Series<String,Number> serie = new XYChart.Series<>();
        serie.setData(modelLocal.getBarData());
        bar.getData().add(serie);
    }
    
}
