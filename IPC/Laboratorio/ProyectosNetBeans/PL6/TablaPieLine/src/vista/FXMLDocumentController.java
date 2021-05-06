/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import modelo.ListaDatos;
import modelo.Registro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 *
 * @author jose
 */
public class FXMLDocumentController implements Initializable {

    private  ListaDatos listaLocal;

    @FXML
    private TableView<Registro> tableView;

    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private TableColumn<Registro, String> diaColumn;

    @FXML
    private TableColumn<Registro, Double> valor1Column;

    @FXML
    private TableColumn<Registro, Double> valor2Column;
    
 

    public void initListas(ListaDatos ml) {
        listaLocal = ml;
        //===================TableView==================
        tableView.setItems(listaLocal.getDataList());

        //====================PieChart===================
        pieChart.setData(listaLocal.getPieData());

        //=================LineChart=====================
        XYChart.Series XYSeries2 = new XYChart.Series();
        XYSeries2.setData(listaLocal.getXYData());
        lineChart.getData().add(XYSeries2);


    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //=================Columna diaColumn====================
        diaColumn.setCellValueFactory((e) -> e.getValue().campoDiaProperty());
        
        //=================Columna var1Column====================
        valor1Column.setCellValueFactory((e) -> e.getValue().campoValor1Property().asObject());
        valor1Column.setCellFactory(c -> new EditingCell());
        valor1Column.setOnEditCommit(t -> {
            int pos = t.getTablePosition().getRow();
            listaLocal.updatepieList(pos, t.getNewValue());
        });
        
        //=================Columna var2Column====================
        valor2Column.setCellValueFactory((e) -> e.getValue().campoValor2Property().asObject());
        valor2Column.setCellFactory(c -> new EditingCell());
        valor2Column.setOnEditCommit(t -> {
            int pos = t.getTablePosition().getRow();
            listaLocal.updatexyList(pos, t.getNewValue());
        });


    }

  
}
