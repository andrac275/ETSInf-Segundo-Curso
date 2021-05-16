package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;

import modelo.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VistaListaControlador implements Initializable {

    private ObservableList<Persona> datos = null; // Colecci�n vinculada a la vista.

    @FXML
    private ListView<Persona> listaLV;
    private TextField nombreTextF;
    private TextField apellidoTextF;
    @FXML
    private Button addB;
    @FXML
    private Button borrarB;
    @FXML
    private Button modificarB;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //---------------------------------------------------
        //creamos dos personas inicialmente
        ArrayList<Persona> misdatos = new ArrayList<Persona>();
        misdatos.add(new Persona("Pepe", "García"));
        misdatos.add(new Persona("María", "Pérez"));
        //----------------------------------------------------------------
        //  crear la listaobservable datos a partir del arraylist misdatos
        
        //ANDREU: private ObservableList<Persona> datos = null;
        datos= FXCollections.observableList(misdatos);
        
        //----------------------------------------------------------------
        //asociar la listaobservable datos al listview listaLV  
        //ANDREU:  private ListView<Persona> listaLV;
        listaLV.setItems(datos);
        //----------------------------------------------------------------
        // asignar aqui el nuevo estilo de la celda*/
        listaLV.setCellFactory(c -> new MiCeldaPersona());

        //----------------------------------------------------------------
        // inhabilitar botones al arrancar y bindings que los habilitan.
        modificarB.disableProperty().bind(Bindings.equal(-1, listaLV.getSelectionModel().selectedIndexProperty()));
        
        borrarB.disableProperty().bind(Bindings.equal(-1, listaLV.getSelectionModel().selectedIndexProperty()));
        
        
    }

    @FXML
    void addAccion(ActionEvent event) throws IOException {
        //--------------------------------------------------------------------
        // anadir aqui el codigo para mostrar la nueva ventana
        //ANDREU: Les linies següents son la copia de  per a crear stage del main.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXMLPersona.fxml"));
        
        
        Parent root = loader.load();
        FXMLPersonaController controlador = loader.getController(); //el controlador per a poder pasar el parametre.
        controlador.addPersona(datos);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //ANDREU:La seguent linia bloqueja la finestra de la que venim, per a que no puguem fer res des de la que vvenim
        stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.setScene(scene);
        stage.setTitle("Demo vista Añadir Persona");
        stage.setMinWidth(400);
        stage.setMinHeight(500);
        //ShowandWait asegura que al tancar la segona finestra en modalitat APPLICATION_MODAL, s'execute la linea
           //seguent, es a dir... faça el if i comprove...
        stage.showAndWait();
        
        if(controlador.isOK()){//Si en la 2ª finestra polsem OK...
            listaLV.refresh();
        }
        
        //-----------------------------------------------------------------------
        //obtenemos la referencia del controlador par poder invocar el metodo publico initText()
      
        
        
        //-----------------------------------------------------
        // paso de parametros invocando un metodo publico de la clase controladora de la ventana 2
        // pasamos el texto




    }

    @FXML
    void borrarAccion(ActionEvent event) {
        //--------------------------------------------------------------------
        // anadir aqui el codigo para borrar la persona seleccionada
        datos.remove(listaLV.getSelectionModel().getSelectedIndex());
        listaLV.refresh();
        
        
    }

    @FXML
    private void modificarAccion(ActionEvent event) throws IOException {
        //--------------------------------------------------------------------
        // anadir aqui el codigo para mostrar la nueva ventana
        // // Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLPersona.fxml"));
        
        //ANDREU:Per a pasar parametres entre finestres...
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FXMLPersona.fxml"));
        Parent root = loader.load();
        FXMLPersonaController controlador = loader.getController(); //el controlador per a poder pasar el parametre.
        controlador.setPersona(listaLV.getSelectionModel().getSelectedItem());
        ////Fins aci///
        
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        //ANDREU:La seguent linia bloqueja la finestra de la que venim, per a que no puguem fer res des de la que vvenim
        stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.setScene(scene);
        stage.setTitle("Demo vista MODIFICAR Persona");
        stage.setMinWidth(400);
        stage.setMinHeight(500);
        //Les dos linies següents son per a que quan MODIFICAR un nom/cognoms, s'actualitze en la llista inicial.
            //ShowandWait asegura que al tancar la segona finestra en modalitat APPLICATION_MODAL, s'execute la linea
            //seguent, es a dir... faça el refresh.
        stage.showAndWait();
        listaLV.refresh();

        
    }
}
/*-------------------------------------------------------*/
 /* crear aqui la nueva clase que extiende a ListCell     */


class MiCeldaPersona extends ListCell<Persona> {

    @Override
    protected void updateItem(Persona item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        if (item==null||empty   ){
            setText("");
        }
        else {
            setText(item.getNombre()+" "+item.getApellidos());
        }
    }
    
}


 /*-------------------------------------------------------*/
