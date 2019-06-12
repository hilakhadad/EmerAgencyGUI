package Main;

import Controller.Controller;
import Models.Model;
import Views.View;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getClassLoader().getResource("sample.fxml").openStream());
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

        Model model = new Model();
        Controller controller = new Controller();
        controller.setView(mainView);
        controller.setModel(model);
        model.setController(controller);
        mainView.setCurrentStage(primaryStage);
        mainView.initializeListeners();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
