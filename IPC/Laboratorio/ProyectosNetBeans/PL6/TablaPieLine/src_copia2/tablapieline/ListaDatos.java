/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablapieline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author jose
 */
public class ListaDatos {
    
         
        private  ObservableList<Registro>  dataList = FXCollections.observableArrayList();
   
        private  ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        private  ObservableList<XYChart.Data> xyList = FXCollections.observableArrayList();;
         
    ListaDatos() {

    }

    public ObservableList<Registro> getDatosTabla() {
        return dataList;
    }

    public ObservableList<PieChart.Data> getDatosPie() {
        return pieChartData;
    }

    public ObservableList<XYChart.Data> getDatosXY() {
        return xyList;
         }
        public void add(Registro r){
            dataList.add(r);
            pieChartData.add(new PieChart.Data(r.getcampoDia(), r.getcampoValor1()));
            xyList.add(new XYChart.Data(r.getcampoDia(), r.getcampoValor2()));
        }
         
        public void updatepieList(int pos, Double val){
            pieChartData.set(pos, new PieChart.Data(pieChartData.get(pos).getName(), val));
        }
         
        public void updatexyList(int pos, Double val){
            xyList.set(pos, new XYChart.Data(xyList.get(pos).getXValue(), val));
        }
    }
     
    

