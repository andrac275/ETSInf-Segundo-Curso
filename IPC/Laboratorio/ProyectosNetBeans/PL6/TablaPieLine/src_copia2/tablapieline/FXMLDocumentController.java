/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablapieline;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 *
 * @author jose
 */
public class FXMLDocumentController implements Initializable {

    private ListaDatos listaLocal;

    @FXML
    private Label label;

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
    
    final CategoryAxis xAxis2 = new CategoryAxis();
    final NumberAxis yAxis2 = new NumberAxis();
    XYChart.Series XYSeries2 = new XYChart.Series();

    public void initListas(ListaDatos ml) {
        listaLocal = ml;
        tableView.setItems(listaLocal.getDatosTabla());
        pieChart.setData(listaLocal.getDatosPie());
        XYSeries2.getData().add(listaLocal.getDatosXY());
        lineChart.getData().add(XYSeries2);
        
        
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         
        diaColumn.setCellValueFactory((e)->e.getValue().campoDiaProperty());
        Callback<TableColumn<Registro, Double>, TableCell<Registro, Double>> cellFactory
                = new Callback<TableColumn<Registro, Double>, TableCell<Registro, Double>>() {

            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };
        valor1Column.setCellValueFactory((e)->e.getValue().campoValor1Property().asObject());
        valor1Column.setCellFactory(cellFactory);  
        valor1Column.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Registro, Double>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Registro, Double> t) {
                ((Registro) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setcampoValor1(t.getNewValue());

                int pos = t.getTablePosition().getRow();
                listaLocal.updatepieList(pos, t.getNewValue());
            }
        });
        valor2Column.setCellValueFactory((e)->e.getValue().campoValor2Property().asObject());
        valor2Column.setCellFactory(cellFactory);
        valor2Column.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Registro, Double>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Registro, Double> t) {
                ((Registro) t.getTableView()
                        .getItems()
                        .get(t.getTablePosition().getRow())).setcampoValor2(t.getNewValue());

                int pos = t.getTablePosition().getRow();
                listaLocal.updatexyList(pos, t.getNewValue());
            }
        });


    }

    class EditingCell extends TableCell<Registro, Double> {

        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }

            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }

                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(Double.parseDouble(textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
