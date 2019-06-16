package Views;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CAWindowController {

    private Controller controller;

    public void setController(Controller c){
        controller=c;
    }

    public void handelCreateCategory(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("CreateCategoryWindow.fxml").openStream());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Create Category");
            stage.setResizable(false);
            CreateCategoryController sceneController = loader.getController();
            sceneController.setController(controller);
            stage.showAndWait();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }
}
