package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Model.Model;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("MainScreenForm.fxml").openStream());
        View mainView = loader.getController();

        primaryStage.setTitle("Emer-Agency");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        Controller controller = new Controller();

        controller.setView(mainView);
        controller.setModel(new Model());
        mainView.setCurrentStage(primaryStage);
        mainView.initializeListeners();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
