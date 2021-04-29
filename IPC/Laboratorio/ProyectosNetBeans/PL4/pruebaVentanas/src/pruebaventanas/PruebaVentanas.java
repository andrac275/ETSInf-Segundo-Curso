/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaventanas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Andrac
 */
public class PruebaVentanas extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Finestra Inicial");
            primaryStage.setScene(scene);
            
            //Acceso al controlador
            MainController controladorPrincipal = loader.<MainController>getController();
            controladorPrincipal.initStage(primaryStage);
                        
            primaryStage.show();           
            
        }catch(Exception e){}
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
