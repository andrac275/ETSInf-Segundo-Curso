/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barramenu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Andrac
 */
public class FXMLBarraMenuController implements Initializable {

    @FXML
    private ToggleGroup compras;
    
    @FXML
    private BorderPane borderP;
    
    
    //Atributs
     private WebView webview;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        webview = new WebView();
    }    

    @FXML
    private void amazonAction(ActionEvent event) {
        webview.getEngine().load("http://amazon.es");
        borderP.setCenter(webview);
    }
    
}
