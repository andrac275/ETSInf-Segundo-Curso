package pruebaloading;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.concurrent.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServiceWithCursorControl extends Application {
    private final Button runButton = new Button("Run");
    private final Label label = new Label();

    private final Service service = new Service() {
        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override protected Void call() throws Exception {
                    // do stuff
                    Thread.sleep(5000);
                    return null;
                }
            };
        }
    };

    @Override public void start(Stage stage) {
        VBox layout = createLayout();

        Scene scene = new Scene(layout, 100, 80);
        stage.setScene(scene);

        bindUIandService(stage);

        stage.show();
    }

    private void bindUIandService(Stage stage) {
        label.textProperty()
                .bind(
                        service.stateProperty().asString()
                );

        stage.getScene()
                .getRoot()
                .cursorProperty()
                .bind(
                        Bindings
                            .when(service.runningProperty())
                                .then(Cursor.WAIT)
                                .otherwise(Cursor.DEFAULT)
                );

        runButton
                .disableProperty()
                .bind(
                        service.runningProperty()
                );

        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.restart();
                        
            }
        });
    }

    private VBox createLayout() {
        VBox layout = new VBox(10);

        layout.getChildren().setAll(runButton, label);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    public static void main(String[] args) {
        launch(args);
    }
}