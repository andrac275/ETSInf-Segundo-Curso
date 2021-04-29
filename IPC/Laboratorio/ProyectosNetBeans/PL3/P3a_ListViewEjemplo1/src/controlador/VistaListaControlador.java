package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;

import modelo.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VistaListaControlador implements Initializable {
	
	
    @FXML 
    private ListView<Persona> listaVista;
    @FXML 
    private Button addButton;
    @FXML 
    private Button borrarButton;
    @FXML
    private TextField nombreTextF;
    @FXML
    private TextField apellidosTextF;
    
    @FXML
    void addAccion(ActionEvent event) {
        // añade a la colección si los campos no son vacíos y no contienen únicamente blancos
        if ((nombreTextF.getText().trim().length() != 0)
                && (apellidosTextF.getText().trim().length() != 0)) {
            datos.add(new Persona(nombreTextF.getText(), apellidosTextF.getText()));
        }

        nombreTextF.clear();
        apellidosTextF.clear();
        nombreTextF.requestFocus();  //cambio del foco al textfield.

    }

    @FXML
    void borrarAccion(ActionEvent event) {
        int i = listaVista.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            datos.remove(i);
        }
    }

    private ObservableList<Persona> datos = null; // Colecci�n vinculada a la vista.

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		ArrayList<Persona> misdatos = new ArrayList<Persona>();
		misdatos.add(new Persona("Pepe", "García"));
		misdatos.add(new Persona("María", "Pérez"));
                //-----------------------------------------------------------
                
                // ANDREU:crea la llista observable i enllaça amb el listvier   
                datos=FXCollections.observableList(misdatos);
                listaVista.setItems(datos);
                    //Es per a que s'aplique la clase PErsonaCelda que es lo que aplica a totes les celes
                    // a la llista, si no eixiran objectes amb el nom de memoria.
                listaVista.setCellFactory(c-> new PersonaCelda());
                //-----------------------------------------------------------
                // ANDREU: crea el binding per a que el boto afegir estiga disable si no tenim nom y cognom
                addButton.disableProperty().bind(Bindings.or( //Les següents linies fan que si no posem nom i cognoms, no ens deixe afegir.
                        //RECORDA ANDREU: En els Bindings se treballa amb ALGOProperty sempre
                        //Opcio 1 del or
                        Bindings.equal(nombreTextF.textProperty(),""),
                        //Opcio 2 del or
                        Bindings.equal(apellidosTextF.textProperty(),""))
                    );
                
                

                //Binding del boto borrar estiga actiu o desactiu.
                borrarButton.disableProperty().bind(Bindings.or(nombreTextF.focusedProperty(), 
                        Bindings.or(apellidosTextF.focusedProperty(),
                                Bindings.or(Bindings.greaterThan(listaVista.editingIndexProperty(),-1),
                                     Bindings.isEmpty(listaVista.getItems())))));	
	}
	
}


//ANDREU: La següent clase aplica a totes les celdes. Cada linia de la taula de nom+apellido
// La linia 82: listaVista.setCellFactory(c-> new PersonaCelda()); es per a aquesta clase.
class PersonaCelda extends ListCell<Persona>{
    //updateItem, necessari per a ListCell
    @Override
    protected void updateItem(Persona item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        if(empty || item==null){ //Si esta buida la celda... PErque ne tenim 15, pero inicialment tenim 2 ocupades
            setText("-----");
        }else{
            setText(item.getApellidos() + ", " + item.getNombre());
        }
    }

    //ANDREU: CrearVistaEdicion y manejadorTexto ens la ha passat el profe. Es per a crear unes caixes per a poder editar el nom al fer doble click
    private VBox vbox;
    private TextField nombreT;
    private TextField apellT;

    private void crearVistaEdicion() {
    //------------------------------------------------
    // grafo de escena: VBox
    //                  - HBox
    //                      - Label TexField
    //                  - Hbox
    //                      - Label TexField
    if (vbox == null) {
        // primer HBox
        HBox nombreBox = new HBox(6);
        Label nombreL = new Label("Nombre:");
        nombreL.setPrefWidth(70);
        nombreL.setAlignment(Pos.CENTER_RIGHT);
        nombreT = new TextField();
        nombreT.setOnKeyPressed(this::manejadorTexto);
        nombreBox.getChildren().addAll(nombreL, nombreT);
        
        // segundo HBox
        HBox apellBox = new HBox(6);
        Label apellL = new Label("Apellido:");
        apellL.setPrefWidth(70);
        apellL.setAlignment(Pos.CENTER_RIGHT);
        apellT = new TextField();
        apellT.setOnKeyPressed(this::manejadorTexto);
        apellBox.getChildren().addAll(apellL, apellT);
        
        // VBox
        vbox = new VBox(6);
        vbox.getChildren().addAll(nombreBox, apellBox);
    }
        //Reiniciar el nom i cognom inicial per si al modificar polsem ESCAPE
        nombreT.setText(getItem().getNombre());
        apellT.setText(getItem().getApellidos());
        
        //---------------------------------------------
        // el significado del runLater lo veremos en la ultima sesión
        // requestFocus() asigna el foco para empezar a escribir
        Platform.runLater(() -> {
            nombreT.requestFocus();
        });
    }


    private void manejadorTexto(KeyEvent t) {
        //--------------------------------------
        // si code== ENTER guardo los datos pero si code== ESCAPE cancelo edicion
        if (t.getCode() == KeyCode.ENTER) {
            Persona nuevo = new Persona(nombreT.getText(), apellT.getText());
            commitEdit(nuevo);
        } else if (t.getCode() == KeyCode.ESCAPE) {
            cancelEdit();
        }
    }
    
    

    //Estos dos metodes son per a que apareguen caixetes al fer doble click sobre el nom i cognom
    @Override
    public void startEdit() {
        super.startEdit(); //To change body of generated methods, choose Tools | Templates.
        crearVistaEdicion();
        setGraphic(vbox);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

        @Override
    public void cancelEdit() {
        super.cancelEdit(); //To change body of generated methods, choose Tools | Templates.
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
    
}